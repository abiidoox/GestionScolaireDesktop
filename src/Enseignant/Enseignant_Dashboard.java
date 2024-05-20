/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Enseignant;

import beans.*;
import java.awt.Color;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

//import org.apache.poi.xssf.usermodel.
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author abiid
 */
public class Enseignant_Dashboard extends javax.swing.JFrame {

    private static Utilisateur u1;
    private static Anneeformation af;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");
    EntityManager em = emf.createEntityManager();

    /**
     * Creates new form Dashboard
     */
    public Enseignant_Dashboard(Utilisateur u, Anneeformation af) {
        this.u1 = u;
        this.af = af;
        em.getEntityManagerFactory().getCache().evictAll();
        initComponents();
        remplirlisteSeance();
        remplirlisteEtudiant();
        refreshTable5();
        remplirchampsListeAbs();
        remplirlisteEtudiantNote();

    }

    public void remplirchampsListeAbs() {
        try {
            PreparedStatement preparedStatement = null;
            ResultSet resultatpreparedStatement = null;
            preparedStatement = connection_db.connecter().prepareStatement("SELECT distinct f.nom FROM Filiere f inner join Groupe g on g.filiere=f.code_Filiere inner JOIN module_enseignant_groupe meg on meg.groupe=g.id_groupe  WHERE meg.enseignant=? and f.af = ? ");
            preparedStatement.setString(2, this.af.getAf());
            preparedStatement.setString(1, u1.getMatricule());
            resultatpreparedStatement = preparedStatement.executeQuery();

            List<Filiere> filieres = new LinkedList<Filiere>();

            while (resultatpreparedStatement.next()) {

                filieres.add(new Filiere(null, resultatpreparedStatement.getString("nom")));
            }

            jComboBox17.removeAllItems();
            jComboBox19.removeAllItems();
            for (Filiere f : filieres) {
                jComboBox17.addItem(f.getNom());
                jComboBox19.addItem(f.getNom());
            }

            jComboBox17.setSelectedIndex(0);
            jComboBox19.setSelectedItem(0);

            preparedStatement = connection_db.connecter().prepareStatement("SELECT distinct g.nom FROM Filiere f inner join Groupe g on g.filiere=f.code_Filiere inner JOIN module_enseignant_groupe meg on meg.groupe=g.id_groupe INNER JOIN seance S ON S.id_meg=meg.id WHERE meg.enseignant=?  and f.af = ? and f.code_filiere=? ");
            preparedStatement.setString(2, this.af.getAf());
            preparedStatement.setString(1, u1.getMatricule());
            preparedStatement.setString(3, getcodefiliereSelected(jComboBox17.getSelectedItem().toString()).getCodeFiliere());
            resultatpreparedStatement = preparedStatement.executeQuery();

            List<Groupe> grp = new LinkedList<Groupe>();

            while (resultatpreparedStatement.next()) {

                grp.add(new Groupe(null, resultatpreparedStatement.getString("nom"), 0, true));
            }
            jComboBox18.removeAllItems();
            for (Groupe g : grp) {
                jComboBox18.addItem(g.getNom());
            }

            preparedStatement = connection_db.connecter().prepareStatement("SELECT distinct g.nom FROM Filiere f inner join Groupe g on g.filiere=f.code_Filiere inner JOIN module_enseignant_groupe meg on meg.groupe=g.id_groupe INNER JOIN seance S ON S.id_meg=meg.id WHERE meg.enseignant=?  and f.af = ? and f.code_filiere=? ");
            preparedStatement.setString(2, this.af.getAf());
            preparedStatement.setString(1, u1.getMatricule());
            preparedStatement.setString(3, getcodefiliereSelected(jComboBox19.getSelectedItem().toString()).getCodeFiliere());
            resultatpreparedStatement = preparedStatement.executeQuery();
            List<Groupe> grps = new LinkedList<Groupe>();

            while (resultatpreparedStatement.next()) {

                grps.add(new Groupe(null, resultatpreparedStatement.getString("nom"), 0, true));
            }
            jComboBox20.removeAllItems();
            for (Groupe g : grps) {
                jComboBox20.addItem(g.getNom());
            }
            if (jComboBox20.getItemCount() > 0) {
                preparedStatement = connection_db.connecter().prepareStatement("SELECT distinct m.nom FROM module m inner join  module_enseignant_groupe meg on meg.module=m.id_module  WHERE meg.enseignant=?  and meg.af = ? and meg.groupe=? ");

                preparedStatement.setString(2, this.af.getAf());
                preparedStatement.setString(1, u1.getMatricule());
                preparedStatement.setInt(3, getcodegroupeselected(jComboBox20.getSelectedItem().toString()).getIdGroupe());
                resultatpreparedStatement = preparedStatement.executeQuery();
                List<beans.Module> mds = new LinkedList<beans.Module>();

                while (resultatpreparedStatement.next()) {

                    mds.add(new beans.Module(null, resultatpreparedStatement.getString("nom"), 0, true));
                }
                jComboBox21.removeAllItems();
                for (beans.Module g : mds) {
                    jComboBox21.addItem(g.getNom());
                }
                jComboBox21.setSelectedIndex(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void remplirlisteEtudiant() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            int rowCount = model.getRowCount();
//Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            if (!jTextField14.getText().equals("")) {
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT Distinct etudiant.id, matricule,Concat(nom,' ',prenom) as nom FROM module_enseignant_groupe inner join etudiant on etudiant.groupe=module_enseignant_groupe.groupe  WHERE (module_enseignant_groupe.groupe = ?) and module_enseignant_groupe.af=?");
                preparedStatement.setString(1, getcodegroupeselected(jTextField14.getText()).getIdGroupe().toString());
                preparedStatement.setString(2, this.af.getAf());
                resultatpreparedStatement = preparedStatement.executeQuery();
                Object[] row;
                while (resultatpreparedStatement.next()) {
                    if (resultatpreparedStatement.getString("matricule") != null) {
                        row = new Object[3];
                        row[0] = resultatpreparedStatement.getInt("id");
                        row[1] = resultatpreparedStatement.getString("matricule");
                        row[2] = resultatpreparedStatement.getString("nom");

                        model.addRow(row);
                    }

                }
            }
            jTable3.getColumnModel().getColumn(0).setMinWidth(0);
jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
jTable3.getColumnModel().getColumn(0).setWidth(0);



TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable3.getColumnCount()-1; i++) {
        col= jTable3.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void remplirlisteEtudiantNote() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable7.getModel();
            int rowCount = model.getRowCount();
//Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            if (jComboBox20.getItemCount() > 0) {
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT Distinct etudiant.id, matricule,Concat(nom,' ',prenom) as nom ,IFNULL(notes.note_1,0) as note1,IFNULL(notes.note_2,0) as note2,IFNULL(notes.note_f,0) as notef FROM module_enseignant_groupe left outer join etudiant on etudiant.groupe=module_enseignant_groupe.groupe left outer join  notes on notes.meg=module_enseignant_groupe.id and notes.etudiant=etudiant.id where module_enseignant_groupe.id=? and etudiant.af=?");
                preparedStatement.setInt(1, Integer.parseInt( jLabel6.getText()));
                preparedStatement.setString(2, this.af.getAf());
                resultatpreparedStatement = preparedStatement.executeQuery();
                Object[] row;
                while (resultatpreparedStatement.next()) {
                    if (resultatpreparedStatement.getString("id") != null) {
                        row = new Object[6];
                        row[0] = resultatpreparedStatement.getInt("id");
                        row[1] = resultatpreparedStatement.getString("matricule");
                        row[2] = resultatpreparedStatement.getString("nom");
                        row[3] = (resultatpreparedStatement.getFloat("note1")!=0?resultatpreparedStatement.getFloat("note1"):"");
                        row[4] = (resultatpreparedStatement.getFloat("note2")!=0?resultatpreparedStatement.getFloat("note2"):"");
                        row[5] = (resultatpreparedStatement.getFloat("notef")!=0?resultatpreparedStatement.getFloat("notef"):"");
                        model.addRow(row);
                    }

                }
            }
            jTable7.getColumnModel().getColumn(0).setMinWidth(0);
jTable7.getColumnModel().getColumn(0).setMaxWidth(0);
jTable7.getColumnModel().getColumn(0).setWidth(0);



TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable7.getColumnCount(); i++) {
        col= jTable7.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void remplirlisteSeance() {
        try {

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            int rowCount = model.getRowCount();
//Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            PreparedStatement preparedStatement = null;
            ResultSet resultatpreparedStatement = null;
            preparedStatement = connection_db.connecter().prepareStatement("SELECT DISTINCT s.id,s.hd,s.hf,m.nom as module,g.nom as groupe   from  seance s  inner join module_enseignant_groupe meg on s.id_meg=meg.id inner join module m on m.id_module=meg.module inner join groupe g on g.id_groupe=meg.groupe  where s.af=? and meg.enseignant=? and s.jour=?");
            preparedStatement.setString(1, af.getAf());
            preparedStatement.setString(2, getcodeensegnant(u1.getNom() + " " + u1.getPrenom()));
            preparedStatement.setString(3, jComboBox14.getSelectedItem().toString());
            resultatpreparedStatement = preparedStatement.executeQuery();

            Object[] row;
            while (resultatpreparedStatement.next()) {
                if (resultatpreparedStatement.getString("module") != null) {
                    row = new Object[5];
                    row[0] = resultatpreparedStatement.getInt("id");
                    row[1] = resultatpreparedStatement.getString("module");
                    row[2] = resultatpreparedStatement.getString("groupe");
                    row[3] = resultatpreparedStatement.getTime("hd");
                    row[4] = resultatpreparedStatement.getTime("hf");
                    model.addRow(row);
                }

            }
            jTable2.getColumnModel().getColumn(0).setMinWidth(0);
jTable2.getColumnModel().getColumn(0).setMaxWidth(0);
jTable2.getColumnModel().getColumn(0).setWidth(0);



TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable2.getColumnCount(); i++) {
        col= jTable2.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void refreshTable5() {
        DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
        int rowCount = model.getRowCount();
//Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        Object[] row;
        String[] days = new String[5];
        days[0] = "lundi";
        days[1] = "Mardi";
        days[2] = "Mercredi";
        days[3] = "Jeudi";
        days[4] = "Vendredi";
        String formateur = u1.getMatricule();
        for (int i = 0; i < 5; i++) {
            row = new Object[5];
            row[0] = days[i];
            row[1] = listeseanceFormateur(days[i], "08:00:00", "10:00:00", formateur);
            row[2] = listeseanceFormateur(days[i], "10:00:00", "12:00:00", formateur);
            row[3] = listeseanceFormateur(days[i], "14:00:00", "16:00:00", formateur);
            row[4] = listeseanceFormateur(days[i], "16:00:00", "18:00:00", formateur);
            model.addRow(row);
        }
        TableColumn col;
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable6.getColumnCount(); i++) {
        col= jTable6.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
    }

    public void refreshTable6() {

        try {

            DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
            int rowCount = model.getRowCount();
//Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            if (jComboBox18.getItemCount() > 0) {

                Object[] row;
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT DISTINCT e.id,Concat(e.nom,' ',e.prenom) as nom,concat(COUNT(absence.seance) * 2,' ','H') as heure from  etudiant e inner join absence on e.id=absence.etudiant where e.AF=? and e.groupe=? group by e.id,Concat(e.nom,' ',e.prenom) ");
                preparedStatement.setString(1, this.af.getAf());
                preparedStatement.setInt(2, getcodegroupeselected(jComboBox18.getSelectedItem().toString()).getIdGroupe());
                resultatpreparedStatement = preparedStatement.executeQuery();

                while (resultatpreparedStatement.next()) {
                    if (resultatpreparedStatement.getInt("id") != 0) {
                        row = new Object[3];
                        row[0] = resultatpreparedStatement.getInt("id");
                        row[1] = resultatpreparedStatement.getString("nom");
                        row[2] = resultatpreparedStatement.getString("heure");

                        model.addRow(row);
                    }
                }
            }
         


TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable5.getColumnCount(); i++) {
        col= jTable5.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String listeseance(String day, String hd, String hf, int groupe) {
        String enseignant = null;
        try {
            PreparedStatement preparedStatement = null;
            ResultSet resultatpreparedStatement = null;
            preparedStatement = connection_db.connecter().prepareStatement("select Concat(Left(u.prenom,1),'.',u.nom) as Nom,m.nom as module from utilisateur u inner join module_enseignant_groupe meg on meg.enseignant=u.matricule inner join module m on m.id_module = meg.module inner join seance s on s.id_meg=meg.id where s.af=? and s.jour=? and meg.groupe=? and s.hd=? and s.hf=?  ");
            preparedStatement.setString(1, this.af.getAf());
            preparedStatement.setString(2, day);
            preparedStatement.setString(4, hd);
            preparedStatement.setString(5, hf);
            preparedStatement.setInt(3, groupe);
            resultatpreparedStatement = preparedStatement.executeQuery();
            if (resultatpreparedStatement.next()) {
                enseignant = resultatpreparedStatement.getString("Nom").toString() + "     [ " + resultatpreparedStatement.getString("Module").toString() + "]";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return enseignant;
    }

    public String listeseanceFormateur(String day, String hd, String hf, String formateur) {
        String enseignant = null;
        try {
            PreparedStatement preparedStatement = null;
            ResultSet resultatpreparedStatement = null;
            preparedStatement = connection_db.connecter().prepareStatement("select u.nom as Nom,m.nom as module from groupe u inner join module_enseignant_groupe meg on meg.groupe=u.id_groupe inner join module m on m.id_module = meg.module inner join seance s on s.id_meg=meg.id where s.af=? and s.jour=? and meg.enseignant=? and s.hd=? and s.hf=?  ");
            preparedStatement.setString(1, this.af.getAf());
            preparedStatement.setString(2, day);
            preparedStatement.setString(4, hd);
            preparedStatement.setString(5, hf);
            preparedStatement.setString(3, formateur);
            resultatpreparedStatement = preparedStatement.executeQuery();
            if (resultatpreparedStatement.next()) {
                enseignant = resultatpreparedStatement.getString("Nom").toString() + "     [ " + resultatpreparedStatement.getString("Module").toString() + "]";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return enseignant;
    }

    public Groupe getcodegroupeselected(String nom) {
        Groupe gp = new Groupe(0);
        Query query = em.createNamedQuery("Groupe.findByNomandaf").setParameter("nom", nom).setParameter("af", this.af);
        List<Groupe> fls = query.getResultList();
        if (fls.size() > 0) {
            gp = fls.get(0);
        }
        return gp;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jSeparator7 = new javax.swing.JSeparator();
        jButton6 = new javax.swing.JButton();
        jComboBox14 = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        jComboBox17 = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel59 = new javax.swing.JLabel();
        jComboBox19 = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        jComboBox20 = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel61 = new javax.swing.JLabel();
        jComboBox21 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion Scolaire");
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        kGradientPanel1.setkEndColor(new java.awt.Color(0, 153, 153));
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 255, 255));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(1250, 750));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 188, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 750));

        jPanel8.setBackground(new java.awt.Color(0, 188, 255));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Acceuil");

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_page.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel4)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel7.setBackground(new java.awt.Color(0, 188, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Enseignant");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(u1.getPrenom().charAt(0)+"."+u1.getNom());

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(60, 60, 60))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(11, 11, 11))
        );

        jPanel9.setBackground(new java.awt.Color(0, 188, 255));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel9MousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Liste des séances -");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Saisir l'absence");

        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-health-points-30.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5))
                .addGap(67, 67, 67))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(0, 188, 255));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Liste des absences");

        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-list-view-30.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(67, 67, 67))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );

        jPanel11.setBackground(new java.awt.Color(0, 188, 255));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Saisir les notes");

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-grades-30.png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(8, 8, 8)))
                .addGap(14, 14, 14))
        );

        jButton17.setBackground(new java.awt.Color(0, 188, 255));
        jButton17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jButton17.setText("Quitter");
        jButton17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(354, 354, 354))
        );

        kGradientPanel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 281, -1));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1250, 750));

        jPanel2.setBackground(new java.awt.Color(229, 229, 229));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 750));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Acceuil ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, -1, -1));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 95, 1244, 10));

        jTable6.setAutoCreateRowSorter(true);
        jTable6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Jour/Heure", "08:00 ->10:00", "10:00 ->12:00", "14:00 ->16:00", "16:00 ->18:00"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable6.setAlignmentY(0.0F);
        jTable6.setGridColor(new java.awt.Color(204, 204, 204));
        jTable6.setRowHeight(90);
        jTable6.setSelectionBackground(new java.awt.Color(0, 102, 255));
        jTable6.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable6.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable6.setShowGrid(true);
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jPanel2.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 147, 940, 490));

        jTabbedPane1.addTab("tab1", jPanel2);

        jPanel5.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel30.setBackground(new java.awt.Color(230, 230, 230));
        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setText("Liste des Séances :");

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Code Séance", "Module", "Groupe", "Heure Début", "Heure Fin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");
        EntityManager em=emf.createEntityManager();
        Query  query = em.createNamedQuery("Utilisateur.findByRole").setParameter("role", "enseignant");

        List<Utilisateur> uti = query.getResultList();
        JComboBox<String> comboBox = new JComboBox<>();
        for (Utilisateur utilisateur : uti) {
            comboBox.addItem(utilisateur.getNom()+" "+utilisateur.getPrenom()+"");
        }
        TableColumn testColumn = jTable2.getColumnModel().getColumn(4);
        testColumn.setCellEditor(new DefaultCellEditor(comboBox));
        jTable2.setGridColor(new java.awt.Color(204, 204, 204));
        jTable2.setRowHeight(40);
        jTable2.setSelectionBackground(new java.awt.Color(0, 153, 255));
        jTable2.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setShowGrid(true);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton6.setBackground(new java.awt.Color(153, 153, 153));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-page-16.png"))); // NOI18N
        jButton6.setText("Saisir l'absence");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi" }));
        jComboBox14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox14.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox14.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox14ItemStateChanged(evt);
            }
        });
        jComboBox14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox14ActionPerformed(evt);
            }
        });

        jLabel52.setBackground(new java.awt.Color(230, 230, 230));
        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel52.setText("Jour :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator5)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(283, 283, 283)
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(462, 462, 462)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(542, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator7)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(249, 249, 249)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(498, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab2", jPanel5);

        jPanel6.setPreferredSize(new java.awt.Dimension(1200, 750));

        jPanel14.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel31.setBackground(new java.awt.Color(230, 230, 230));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setText("Liste des étudiants:");

        jButton5.setBackground(new java.awt.Color(153, 153, 153));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-validation-16.png"))); // NOI18N
        jButton5.setText("Marquer l'absence");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton5.setDoubleBuffered(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable3.setAutoCreateRowSorter(true);
        jTable3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Code Etudiant", "Matricule", "Nom - Prénom", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setGridColor(new java.awt.Color(255, 255, 255));
        jTable3.setRowHeight(40);
        jTable3.setSelectionBackground(new java.awt.Color(0, 153, 255));
        jTable3.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.setShowGrid(true);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jLabel27.setBackground(new java.awt.Color(230, 230, 230));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Id Séance :");

        jTextField6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField6.setDoubleBuffered(true);
        jTextField6.setEnabled(false);
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel51.setBackground(new java.awt.Color(230, 230, 230));
        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel51.setText("Groupe :");

        jTextField14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField14.setDoubleBuffered(true);
        jTextField14.setEnabled(false);
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(188, 188, 188)
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 225, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator6)))
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(386, 386, 386)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator8)
                    .addContainerGap()))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator10)
                    .addContainerGap()))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(126, 126, 126)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(621, Short.MAX_VALUE)))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(213, 213, 213)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(534, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel6);

        jPanel15.setPreferredSize(new java.awt.Dimension(1200, 750));

        jPanel16.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel34.setBackground(new java.awt.Color(230, 230, 230));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setText("Liste des absences :");

        jLabel57.setBackground(new java.awt.Color(230, 230, 230));
        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel57.setText("Filiére :");

        jComboBox17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox17.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox17.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox17ItemStateChanged(evt);
            }
        });
        jComboBox17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox17ActionPerformed(evt);
            }
        });

        jLabel58.setBackground(new java.awt.Color(230, 230, 230));
        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel58.setText("Groupe :");

        jComboBox18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox18.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox18.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox18ItemStateChanged(evt);
            }
        });

        jTable5.setAutoCreateRowSorter(true);
        jTable5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Matricule", "Nom - Prénom", "Nombre d'absence ( Heure )"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.setGridColor(new java.awt.Color(204, 204, 204));
        jTable5.setRowHeight(40);
        jTable5.setSelectionBackground(new java.awt.Color(0, 153, 255));
        jTable5.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable5.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable5.setShowGrid(true);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator12))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(205, 205, 205)
                                .addComponent(jLabel57)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator13)
                    .addContainerGap()))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(197, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addGap(249, 249, 249)
                    .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(498, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel15);

        jPanel17.setPreferredSize(new java.awt.Dimension(1200, 750));

        jPanel18.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel35.setBackground(new java.awt.Color(230, 230, 230));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel35.setText("Saisir les notes:");

        jLabel59.setBackground(new java.awt.Color(230, 230, 230));
        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel59.setText("Filiére :");

        jComboBox19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox19.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox19.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox19ItemStateChanged(evt);
            }
        });
        jComboBox19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox19ActionPerformed(evt);
            }
        });

        jLabel60.setBackground(new java.awt.Color(230, 230, 230));
        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel60.setText("Groupe :");

        jComboBox20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox20.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox20.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox20ItemStateChanged(evt);
            }
        });

        jTable7.setAutoCreateRowSorter(true);
        jTable7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id Etudiant", "Matricule", "Nom - Prénom", "Note CC1", "Note CC2", "Note Exam"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable7.setGridColor(new java.awt.Color(204, 204, 204));
        jTable7.setRowHeight(40);
        jTable7.setSelectionBackground(new java.awt.Color(0, 153, 255));
        jTable7.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable7.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable7.setShowGrid(true);
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable7);

        jLabel61.setBackground(new java.awt.Color(230, 230, 230));
        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel61.setText("Modules :");

        jComboBox21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox21.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox21.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox21ItemStateChanged(evt);
            }
        });

        jLabel6.setText("jLabel6");
        jLabel6.setEnabled(false);
        jLabel6.setVisible(false);

        jButton7.setBackground(new java.awt.Color(153, 153, 153));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-validation-16 (1).png"))); // NOI18N
        jButton7.setText("Valider les notes");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel59)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator14))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(277, 277, 277))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(385, 385, 385)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator15)
                    .addContainerGap()))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170))
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(249, 249, 249)
                    .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(498, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel17);

        kGradientPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, -37, 970, 920));

        getContentPane().add(kGradientPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        jTabbedPane1.setSelectedIndex(0);
//       refresdashboard();
 setbackground(jPanel8);
        resetbackground(jPanel9);        
        resetbackground(jPanel11);
        resetbackground(jPanel12);

    }//GEN-LAST:event_jPanel8MouseClicked
   public void setbackground(JPanel p)
     {
         p.setBackground(new Color(0,150,255));
     }
     public void resetbackground(JPanel p)
     {
         p.setBackground(new Color(0,188,255));
     }
   
    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
        //refresdashboard();
        setbackground(jPanel12);
        resetbackground(jPanel9);        
        resetbackground(jPanel11);
        resetbackground(jPanel8);
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
//        refresdashboard();
setbackground(jPanel11);
        resetbackground(jPanel9);        
        resetbackground(jPanel8);
        resetbackground(jPanel12);
    }//GEN-LAST:event_jPanel11MouseClicked
    public String getcodeensegnant(String Name) {
        String a = null;
        try {
            if (Name == "--------------") {
                Name = " ";
            }

            PreparedStatement preparedStatement = null;
            ResultSet resultatpreparedStatement = null;
            preparedStatement = connection_db.connecter().prepareStatement("select u.matricule as Matricule from utilisateur u where CONCAT(u.nom,' ', u.prenom)=?  and u.role='enseignant' ");
            preparedStatement.setString(1, Name);
            resultatpreparedStatement = preparedStatement.executeQuery();
            if (resultatpreparedStatement.next()) {
                a = resultatpreparedStatement.getString("Matricule").toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return a;
    }
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {

            for (int i = 0; i < jTable3.getRowCount(); i++) {
                if (jTable3.getValueAt(i, 3) != null) {
                    if ((boolean) jTable3.getValueAt(i, 3) == true) {

                        Absence s = new Absence();
                        s.setSeance(new Seance(Integer.parseInt(jTextField6.getText())));
                        s.setEtudiant(new Etudiant(Integer.parseInt(jTable3.getValueAt(i, 0).toString())));
                        s.create();

                    }

                }
            }
            JOptionPane.showMessageDialog(null, "L'absence à été enregistrer");
            refreshTable6();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        int selectedrow = -1;
        selectedrow = jTable2.getSelectedRow();
        if (selectedrow >= 0) {
            Query query = em.createNamedQuery("Seance.findById").setParameter("id", jTable2.getValueAt(selectedrow, 0));
            List<Seance> lst = query.getResultList();
            jTabbedPane1.setSelectedIndex(2);
            jTextField6.setText(lst.get(0).getId().toString());
            jTextField14.setText(lst.get(0).getIdMeg().getGroupe().getNom());
            remplirlisteEtudiant();
        } else
            JOptionPane.showMessageDialog(null, "Veillez sélectionner une Séance");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jComboBox17ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox17ItemStateChanged
        // TODO add your handling code here:
        try {
            if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT distinct g.nom FROM Filiere f inner join Groupe g on g.filiere=f.code_Filiere inner JOIN module_enseignant_groupe meg on meg.groupe=g.id_groupe INNER JOIN seance S ON S.id_meg=meg.id WHERE meg.enseignant=?  and f.af = ? and f.code_filiere=? ");
                preparedStatement.setString(2, this.af.getAf());
                preparedStatement.setString(1, u1.getMatricule());
                preparedStatement.setString(3, getcodefiliereSelected(jComboBox17.getSelectedItem().toString()).getCodeFiliere());
                resultatpreparedStatement = preparedStatement.executeQuery();

                List<Groupe> grp = new LinkedList<Groupe>();

                while (resultatpreparedStatement.next()) {

                    grp.add(new Groupe(null, resultatpreparedStatement.getString("nom"), 0, true));
                }
                jComboBox18.removeAllItems();
                for (Groupe g : grp) {
                    jComboBox18.addItem(g.getNom());
                }

                if (grp.size() == 0) {
                    DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
                    int rowCount = model.getRowCount();
//Remove rows one by one from the end of the table
                    for (int i = rowCount - 1; i >= 0; i--) {
                        model.removeRow(i);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox17ItemStateChanged

    private void jComboBox17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox17ActionPerformed

    private void jComboBox18ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox18ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            refreshTable6();
        }
    }//GEN-LAST:event_jComboBox18ItemStateChanged

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable6MouseClicked

    private void jComboBox14ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox14ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED)
            remplirlisteSeance();
    }//GEN-LAST:event_jComboBox14ItemStateChanged

    private void jComboBox14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox14ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jPanel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MousePressed

        jTabbedPane1.setSelectedIndex(1);
        //        refresdashboard();
setbackground(jPanel9);
        resetbackground(jPanel8);        
        resetbackground(jPanel11);
        resetbackground(jPanel12);
    }//GEN-LAST:event_jPanel9MousePressed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    public int getidfaffectation() {
        int id = 0;
        try {

            if (jComboBox20.getItemCount() > 0 && jComboBox21.getItemCount() > 0 && getcodeModuleselected(jComboBox21.getSelectedItem().toString(), getcodefiliereSelected(jComboBox19.getSelectedItem().toString()).getCodeFiliere()).getIdModule() > 0) {
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT distinct meg.id FROM  module_enseignant_groupe meg  WHERE meg.enseignant=?  and meg.af = ? and meg.groupe=? and meg.module=? ");

                preparedStatement.setString(2, this.af.getAf());
                preparedStatement.setString(1, u1.getMatricule());
                preparedStatement.setInt(3, getcodegroupeselected(jComboBox20.getSelectedItem().toString()).getIdGroupe());
                preparedStatement.setInt(4, getcodeModuleselected(jComboBox21.getSelectedItem().toString(), getcodefiliereSelected(jComboBox19.getSelectedItem().toString()).getCodeFiliere()).getIdModule());
                resultatpreparedStatement = preparedStatement.executeQuery();
                List<ModuleEnseignantGroupe> megs = new LinkedList<ModuleEnseignantGroupe>();

                if (resultatpreparedStatement.next()) {

                    megs.add(new beans.ModuleEnseignantGroupe(resultatpreparedStatement.getInt("id")));
                }
                id = megs.get(0).getId();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public beans.Module getcodeModuleselected(String nom, String filiere) {
        beans.Module m = new beans.Module(0);
        try {
            if (!nom.equals(null) && !filiere.equals(null)) {
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT distinct m.id_module FROM  module m  WHERE m.nom=?  and m.af = ? and m.filiere=?  ");
                preparedStatement.setString(2, this.af.getAf());
                preparedStatement.setString(1, nom);
                preparedStatement.setString(3, filiere);
                resultatpreparedStatement = preparedStatement.executeQuery();
                if (resultatpreparedStatement.next()) {
                    m = new beans.Module(resultatpreparedStatement.getInt("id_module"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return m;
    }

    private void jComboBox19ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox19ItemStateChanged
        // TODO add your handling code here:
        try {

            if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT distinct g.nom FROM Filiere f inner join Groupe g on g.filiere=f.code_Filiere inner JOIN module_enseignant_groupe meg on meg.groupe=g.id_groupe INNER JOIN seance S ON S.id_meg=meg.id WHERE meg.enseignant=?  and f.af = ? and f.code_filiere=? ");
                preparedStatement.setString(2, this.af.getAf());
                preparedStatement.setString(1, u1.getMatricule());
                preparedStatement.setString(3, getcodefiliereSelected(jComboBox19.getSelectedItem().toString()).getCodeFiliere());
                resultatpreparedStatement = preparedStatement.executeQuery();
                List<Groupe> grps = new LinkedList<Groupe>();

                while (resultatpreparedStatement.next()) {

                    grps.add(new Groupe(null, resultatpreparedStatement.getString("nom"), 0, true));
                }
                jComboBox20.removeAllItems();
                for (Groupe g : grps) {
                    jComboBox20.addItem(g.getNom());
                }
                jComboBox20ItemStateChanged(evt);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox19ItemStateChanged

    private void jComboBox19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox19ActionPerformed

    private void jComboBox20ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox20ItemStateChanged
        // TODO add your handling code here:
        try {
            if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT distinct m.nom FROM module m inner join  module_enseignant_groupe meg on meg.module=m.id_module  WHERE meg.enseignant=?  and meg.af = ? and meg.groupe=? ");

                preparedStatement.setString(2, this.af.getAf());
                preparedStatement.setString(1, u1.getMatricule());
                preparedStatement.setInt(3, getcodegroupeselected(jComboBox20.getSelectedItem().toString()).getIdGroupe());                resultatpreparedStatement = preparedStatement.executeQuery();
                List<beans.Module> mds = new LinkedList<beans.Module>();

                while (resultatpreparedStatement.next()) {

                    mds.add(new beans.Module(null, resultatpreparedStatement.getString("nom"), 0, true));
                }
                jComboBox21.removeAllItems();
                for (beans.Module g : mds) {
                    jComboBox21.addItem(g.getNom());
                }
                jComboBox21.setSelectedIndex(0);
                
            } else {
                jComboBox21.removeAllItems();
            }
            jLabel6.setText(String.valueOf(getidfaffectation()));
            remplirlisteEtudiantNote();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jComboBox20ItemStateChanged

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable7MouseClicked

    private void jComboBox21ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox21ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED)
        {jLabel6.setText(String.valueOf(getidfaffectation()));
        remplirlisteEtudiantNote();
        }
    }//GEN-LAST:event_jComboBox21ItemStateChanged

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try{
        for (int i = 0; i < jTable7.getRowCount(); i++) {
            Query q=em.createNamedQuery("Etudiant.findById").setParameter("id", Integer.parseInt(jTable7.getValueAt(i, 0).toString()));
            Etudiant e=(Etudiant)q.getResultList().get(0);
            Query query=em.createNamedQuery("ModuleEnseignantGroupe.findById").setParameter("id",Integer.parseInt( jLabel6.getText().toString()));
            ModuleEnseignantGroupe meg=(ModuleEnseignantGroupe)query.getResultList().get(0);
            String note1= (jTable7.getValueAt(i, 3).toString().contains(".")?jTable7.getValueAt(i, 3).toString().replace(".0", ""):jTable7.getValueAt(i, 3).toString());
            String note2=(jTable7.getValueAt(i, 4).toString().contains(".")?jTable7.getValueAt(i, 4).toString().replace(".0", ""):jTable7.getValueAt(i, 4).toString());
            String notef=(jTable7.getValueAt(i, 5).toString().contains(".")?jTable7.getValueAt(i, 5).toString().replace(".0", ""):jTable7.getValueAt(i, 5).toString());
            Notes n=new Notes(0,Long.parseLong(note1),Long.parseLong(note2),Long.parseLong(notef));
            n.setEtudiant(e);
            n.setMeg(meg);
            if(existeNotes(n.getMeg().getId(),n.getEtudiant().getId()))
            {
                n.update();
                
            }
            else{
                n.create();
            }
        }
        
         JOptionPane.showMessageDialog(null, "Les Notes sont enregistrer");
         remplirlisteEtudiantNote();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Gestion_Scolaire.Interface_Authantification t=new Gestion_Scolaire.Interface_Authantification();
        t.setVisible(true);

    }//GEN-LAST:event_jButton17ActionPerformed

    public boolean  existeNotes(int id,int etudiant)
    {
        boolean exist=false;
          try {
           
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT distinct n.id_note id FROM notes n  WHERE n.etudiant=?  and n.meg = ? ");

                preparedStatement.setInt(1, etudiant);
                preparedStatement.setInt(2, id);
                resultatpreparedStatement = preparedStatement.executeQuery();
                List<Notes> Nts = new LinkedList<Notes>();

                if (resultatpreparedStatement.next()) {
                    Nts.add(new beans.Notes(resultatpreparedStatement.getInt("id")));
                }
                if(Nts.size()>0)
                    if(Nts.get(0).getIdNote()>0)
                        exist=true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          return exist;
    }
    
    Filiere getcodefiliereSelected(String nom) {
        Query query = em.createNamedQuery("Filiere.findByNomAndAf").setParameter("nom", nom).setParameter("af", this.af);
        List<Filiere> fls = query.getResultList();

        return fls.get(0);

    }

    void setColor(JPanel panel) {
        panel.setBackground(new Color(135, 112, 225));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new java.awt.Color(1, 163, 158));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Enseignant_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Enseignant_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Enseignant_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Enseignant_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //  new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox19;
    private javax.swing.JComboBox<String> jComboBox20;
    private javax.swing.JComboBox<String> jComboBox21;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField6;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
