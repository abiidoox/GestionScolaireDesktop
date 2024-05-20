/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chef_scolarite;

import beans.*;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityTransaction;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

//import org.apache.poi.xssf.usermodel.
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author abiid
 */
public class Dashboard_chef_scolarite extends javax.swing.JFrame {

    private static Utilisateur u1;
    private static Anneeformation af;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");
    EntityManager em = emf.createEntityManager();

    /**
     * Creates new form Dashboard
     */
    public Dashboard_chef_scolarite(Utilisateur u, Anneeformation af) {
        this.u1 = u;
        this.af = af;
        em.getEntityManagerFactory().getCache().evictAll();
        initComponents();
        refresdashboard();
        refreshuEtudiant();
        refreshuListeEtudiant();
        refreshTable1();
        refreshTable2();
        refreshTable3();
        refreshuListeSeance();
        refreshuListeEtudiantEmprimer();
        refreshTable8();

    }

    void refreshuListeEtudiant() {
        Query query = em.createQuery("SELECT f FROM Filiere f WHERE f.af = :af");
        query.setParameter("af", af);
        List<Filiere> filieres = query.getResultList();

        jComboBox12.removeAllItems();

        for (Filiere f : filieres) {
            jComboBox12.addItem(f.getNom());
        }
        jComboBox12.setSelectedIndex(0);
        query = em.createQuery("SELECT g FROM Groupe g WHERE g.af = :af and g.etat=1 and g.filiere=:filiere");
        query.getParameters().clear();
        query.setParameter("af", af);
        query.setParameter("filiere", getcodefiliereSelected(jComboBox12.getSelectedItem().toString()));
        List<Groupe> grps = query.getResultList();

        jComboBox13.removeAllItems();
        for (Groupe g : grps) {
            jComboBox13.addItem(g.getNom());
        }
    }

    void refreshuListeSeance() {
        Query query = em.createQuery("SELECT f FROM Filiere f WHERE f.af = :af");
        query.setParameter("af", af);
        List<Filiere> filieres = query.getResultList();
        jComboBox17.removeAllItems();
        for (Filiere f : filieres) {
            jComboBox17.addItem(f.getNom());
        }
        jComboBox17.setSelectedIndex(0);
        query = em.createQuery("SELECT g FROM Groupe g WHERE g.af = :af and g.etat=1 and g.filiere=:filiere");
        query.getParameters().clear();
        query.setParameter("af", af);
        query.setParameter("filiere", getcodefiliereSelected(jComboBox17.getSelectedItem().toString()));
        List<Groupe> grps = query.getResultList();
        jComboBox18.removeAllItems();
        for (Groupe g : grps) {
            jComboBox18.addItem(g.getNom());
        }
        query = em.createNamedQuery("Utilisateur.findByRole");
        query.getParameters().clear();
        query.setParameter("role", "enseignant");
        List<Utilisateur> uti = query.getResultList();
        jComboBox19.removeAllItems();
        for (Utilisateur u : uti) {
            jComboBox19.addItem(u.getNom() + ' ' + u.getPrenom());
        }

    }

    void refreshuEtudiant() {
        Query query = em.createQuery("SELECT f FROM Filiere f WHERE f.af = :af");
        query.setParameter("af", af);
        List<Filiere> filieres = query.getResultList();

        jComboBox10.removeAllItems();

        for (Filiere f : filieres) {
            jComboBox10.addItem(f.getNom());
        }
        jComboBox10.setSelectedIndex(0);
        query = em.createQuery("SELECT g FROM Groupe g WHERE g.af = :af and g.etat=1 and g.filiere=:filiere");
        query.getParameters().clear();
        query.setParameter("af", af);
        query.setParameter("filiere", getcodefiliereSelected(jComboBox10.getSelectedItem().toString()));
        List<Groupe> grps = query.getResultList();

        jComboBox11.removeAllItems();
        for (Groupe g : grps) {
            jComboBox11.addItem(g.getNom());
        }
    }

    void refresdashboard() {
        Query query = em.createQuery("SELECT f FROM Filiere f WHERE f.af = :af");
        query.setParameter("af", af);
        List<Filiere> filieres = query.getResultList();
        jComboBox20.removeAllItems();
        jComboBox8.removeAllItems();
        for (Filiere f : filieres) {
            jComboBox20.addItem(f.getNom());
            jComboBox8.addItem(f.getNom());
        }
        jComboBox20.setSelectedIndex(0);
        jComboBox8.setSelectedIndex(0);
        query = em.createQuery("SELECT g FROM Groupe g WHERE g.af = :af and g.etat=1 and g.filiere=:filiere");
        query.getParameters().clear();
        query.setParameter("af", af);
        query.setParameter("filiere", getcodefiliereSelected(jComboBox20.getSelectedItem().toString()));
        List<Groupe> grpsa = query.getResultList();
        jComboBox21.removeAllItems();
        for (Groupe g : grpsa) {
            jComboBox21.addItem(g.getNom());
        }

        /*   jComboBox8.removeAllItems();
        
        for (Filiere f : filieres) {
            jComboBox8.addItem(f.getNom());
        }
        jComboBox8.setSelectedIndex(0);*/
        query = em.createQuery("SELECT g FROM Groupe g WHERE g.af = :af and g.etat=1 and g.filiere=:filiere");
        query.getParameters().clear();
        query.setParameter("af", af);
        query.setParameter("filiere", getcodefiliereSelected(jComboBox8.getSelectedItem().toString()));
        List<Groupe> grps = query.getResultList();
        jComboBox9.removeAllItems();
        for (Groupe g : grps) {
            jComboBox9.addItem(g.getNom());
        }
    }

    void refreshuListeEtudiantEmprimer() {
        Query query = em.createQuery("SELECT f FROM Filiere f WHERE f.af = :af");
        query.setParameter("af", af);
        List<Filiere> filieres = query.getResultList();
        jComboBox22.removeAllItems();
        for (Filiere f : filieres) {
            jComboBox22.addItem(f.getNom());
        }
        jComboBox22.setSelectedIndex(0);
        query = em.createQuery("SELECT g FROM Groupe g WHERE g.af = :af and g.etat=1 and g.filiere=:filiere");
        query.getParameters().clear();
        query.setParameter("af", af);
        query.setParameter("filiere", getcodefiliereSelected(jComboBox22.getSelectedItem().toString()));
        List<Groupe> grps = query.getResultList();
        jComboBox23.removeAllItems();
        for (Groupe g : grps) {
            jComboBox23.addItem(g.getNom());
        }
    }

    public void refreshTable1() {
        try {
            jTable1.setModel(new DefaultTableModel(new Object[][]{},
                    new String[]{"id", "CIN", "CNE", "Nom", "Prénom", "Téléphone", "Email", "Date de Naissance", "Lieu", "Adresse", "État", "Groupe"}));
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            Object[] row;
            PreparedStatement preparedStatement = null;
            ResultSet resultatpreparedStatement = null;
            preparedStatement = connection_db.connecter().prepareStatement("SELECT * from Etudiant where af=?");
            preparedStatement.setString(1, this.af.getAf());
            resultatpreparedStatement = preparedStatement.executeQuery();

            while (resultatpreparedStatement.next()) {
                Etudiant next = new Etudiant(resultatpreparedStatement.getInt("id"),
                        resultatpreparedStatement.getString("Matricule"),
                        resultatpreparedStatement.getString("cin"),
                        resultatpreparedStatement.getDate("DateN"),
                        resultatpreparedStatement.getString("lieuN"),
                        resultatpreparedStatement.getString("adresse"),
                        resultatpreparedStatement.getString("telephone"),
                        resultatpreparedStatement.getString("nom"),
                        resultatpreparedStatement.getString("prenom"),
                        resultatpreparedStatement.getString("email"),
                        resultatpreparedStatement.getBoolean("etat"));

                Query query = em.createNamedQuery("Groupe.findByIdGroupe");
                query.getParameters().clear();
                query.setParameter("idGroupe", resultatpreparedStatement.getInt("groupe"));
                List<Groupe> grps = query.getResultList();
                if (grps.size() > 0) {
                    next.setGroupe(grps.get(0));
                }

                row = new Object[12];
                row[0] = next.getId();
                row[1] = next.getCin();
                row[2] = next.getMatricule();
                row[3] = next.getNom();
                row[4] = next.getPrenom();
                row[5] = next.getTelephone();
                row[6] = next.getEmail();
                row[7] = next.getDateN();
                row[8] = next.getLieuN();
                row[9] = next.getAdresse();
                row[10] = next.getEtat() ? "Actif" : "Non Actif";
                if (grps.size() > 0) {
                    row[11] = next.getGroupe().getNom();
                }
                model.addRow(row);
            }
            
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
jTable1.getColumnModel().getColumn(0).setWidth(0);
TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable1.getColumnCount(); i++) {
        col= jTable1.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void remplirlisteEnseignant() {
        try {
            Query query = em.createNamedQuery("Utilisateur.findByRole").setParameter("role", "enseignant");
            List<Utilisateur> uti = query.getResultList();
            JComboBox<String> comboBox = new JComboBox<>();
            comboBox.addItem("--------------");
            for (Utilisateur utilisateur : uti) {
                comboBox.addItem(utilisateur.getNom() + " " + utilisateur.getPrenom() + "");
            }
            TableColumn testColumn = jTable2.getColumnModel().getColumn(4);
            testColumn.setCellEditor(new DefaultCellEditor(comboBox));
            //query.getParameters().clear();
            PreparedStatement preparedStatement = null;
            ResultSet resultatpreparedStatement = null;
            for (int i = 0; i < jTable2.getRowCount(); i++) {

                preparedStatement = connection_db.connecter().prepareStatement("SELECT matricule,Concat(nom,' ',prenom) as nom FROM module_enseignant_groupe inner join utilisateur on matricule=enseignant  WHERE (id = ?)");
                preparedStatement.setInt(1, Integer.parseInt(jTable2.getValueAt(i, 0).toString()));
                resultatpreparedStatement = preparedStatement.executeQuery();

                if (resultatpreparedStatement.next()) {
                    if (resultatpreparedStatement.getString("matricule") != null) {
                        jTable2.setValueAt(resultatpreparedStatement.getString("Nom"), i, 4);
                    }
                }
                
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void refreshTable2() {
        try {
            if (jComboBox9.getItemCount() > 0) {
                jTable2.setModel(new DefaultTableModel(new Object[][]{},
                        new String[]{"Code affectation", "Module", "Mass Horraire", "Groupe", "Enseignant"}));
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                Object[] row;
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT DISTINCT meg.id,module.nom mname,module.mh,groupe.nom gname,utilisateur.nom +' '+utilisateur.prenom as ename from module_enseignant_Groupe meg inner join groupe on id_groupe=meg.groupe inner join module on module.id_module=meg.module left outer join utilisateur on utilisateur.nom=meg.enseignant  where meg.af=? and groupe.id_groupe=?");
                preparedStatement.setString(1, this.af.getAf());
                preparedStatement.setInt(2, getcodegroupeselected(jComboBox9.getSelectedItem().toString()).getIdGroupe());
                resultatpreparedStatement = preparedStatement.executeQuery();

                while (resultatpreparedStatement.next()) {
                    row = new Object[5];
                    row[0] = resultatpreparedStatement.getInt("id");
                    row[1] = resultatpreparedStatement.getString("mname");
                    row[2] = resultatpreparedStatement.getFloat("mh");
                    row[3] = resultatpreparedStatement.getString("gname");
                    row[4] = null;
                    model.addRow(row);
                }
                remplirlisteEnseignant();
            } else {
                jTable2.setModel(new DefaultTableModel(new Object[][]{},
                        new String[]{"Code affectation", "Module", "Mass Horraire", "Groupe", "Enseignant"}));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTable3() {
        try {

            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            int rowCount = model.getRowCount();
//Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            if (jComboBox13.getItemCount() > 0) {

                Object[] row;
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT DISTINCT etudiant.matricule,concat(etudiant.nom ,' ', etudiant.prenom) as ename,groupe.nom gname from  groupe  inner join etudiant on etudiant.groupe=groupe.id_groupe  where groupe.af=? and groupe.id_groupe=?");
                preparedStatement.setString(1, this.af.getAf());
                preparedStatement.setInt(2, getcodegroupeselected(jComboBox13.getSelectedItem().toString()).getIdGroupe());
                resultatpreparedStatement = preparedStatement.executeQuery();
                JCheckBox checkbox = new JCheckBox();
                checkbox.setBackground(Color.gray);
                checkbox.setSelected(false);
                while (resultatpreparedStatement.next()) {
                    row = new Object[4];
                    row[0] = resultatpreparedStatement.getString("matricule");
                    row[1] = resultatpreparedStatement.getString("ename");
                    row[2] = resultatpreparedStatement.getString("gname");

                    model.addRow(row);
                }
            }
           
TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable3.getColumnCount()-1; i++) {
        col= jTable3.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTable4() {
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
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
        int id_groupe = getcodegroupeselected(jTextField14.getText()).getIdGroupe();
        for (int i = 0; i < 5; i++) {
            row = new Object[5];
            row[0] = days[i];
            row[1] = listeseance(days[i], "08:00:00", "10:00:00", id_groupe);
            row[2] = listeseance(days[i], "10:00:00", "12:00:00", id_groupe);
            row[3] = listeseance(days[i], "14:00:00", "16:00:00", id_groupe);
            row[4] = listeseance(days[i], "16:00:00", "18:00:00", id_groupe);
            model.addRow(row);
        }
     
TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable5.getColumnCount(); i++) {
        col= jTable5.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
    }

    void refreshTable7() {
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
        int id_groupe = getcodegroupeselected(jComboBox21.getSelectedItem().toString()).getIdGroupe();
        for (int i = 0; i < 5; i++) {
            row = new Object[5];
            row[0] = days[i];
            row[1] = listeseance(days[i], "08:00:00", "10:00:00", id_groupe);
            row[2] = listeseance(days[i], "10:00:00", "12:00:00", id_groupe);
            row[3] = listeseance(days[i], "14:00:00", "16:00:00", id_groupe);
            row[4] = listeseance(days[i], "16:00:00", "18:00:00", id_groupe);
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

    public void refreshTable5() {
        DefaultTableModel model = (DefaultTableModel) jTable7.getModel();
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
        String formateur = getcodeensegnant(jTextField11.getText());
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
                  for (int i = 0; i < jTable7.getColumnCount(); i++) {
        col= jTable7.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        
    }

    public void refreshTable6() {

        try {

            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            int rowCount = model.getRowCount();
//Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            if (jComboBox18.getItemCount() > 0 && jComboBox19.getItemCount() > 0) {

                Object[] row;
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT DISTINCT s.id,s.jour,s.hd,s.hf,m.nom  module from  seance s  inner join module_enseignant_groupe meg on s.id_meg=meg.id inner join module m on m.id_module=meg.module where s.af=? and meg.groupe=? and meg.enseignant=?");
                preparedStatement.setString(1, this.af.getAf());
                preparedStatement.setInt(2, getcodegroupeselected(jComboBox18.getSelectedItem().toString()).getIdGroupe());
                preparedStatement.setString(3, getcodeensegnant(jComboBox19.getSelectedItem().toString()));
                resultatpreparedStatement = preparedStatement.executeQuery();
                JCheckBox checkbox = new JCheckBox();
                checkbox.setBackground(Color.gray);
                checkbox.setSelected(false);
                while (resultatpreparedStatement.next()) {
                    row = new Object[5];
                    row[0] = resultatpreparedStatement.getString("id");
                    row[1] = resultatpreparedStatement.getString("jour");
                    row[2] = resultatpreparedStatement.getString("hd");
                    row[3] = resultatpreparedStatement.getString("hf");
                    row[4] = resultatpreparedStatement.getString("module");

                    model.addRow(row);
                }
            }
             jTable4.getColumnModel().getColumn(0).setMinWidth(0);
jTable4.getColumnModel().getColumn(0).setMaxWidth(0);
jTable4.getColumnModel().getColumn(0).setWidth(0);
TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable4.getColumnCount()-1; i++) {
        col= jTable4.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTable8() {
        try {

            DefaultTableModel model = (DefaultTableModel) jTable9.getModel();
            int rowCount = model.getRowCount();
//Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            if (jComboBox23.getItemCount() > 0) {

                Object[] row;
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT DISTINCT id, etudiant.matricule,concat(etudiant.nom ,' ', etudiant.prenom) as ename from  etudiant   where  groupe=?");
                preparedStatement.setInt(1, getcodegroupeselected(jComboBox23.getSelectedItem().toString()).getIdGroupe());
                resultatpreparedStatement = preparedStatement.executeQuery();
                while (resultatpreparedStatement.next()) {
                    row = new Object[3];
                    row[0] = resultatpreparedStatement.getInt("id");
                    row[1] = resultatpreparedStatement.getString("matricule");
                    row[2] = resultatpreparedStatement.getString("ename");

                    model.addRow(row);
                }
            }
             jTable9.getColumnModel().getColumn(0).setMinWidth(0);
jTable9.getColumnModel().getColumn(0).setMaxWidth(0);
jTable9.getColumnModel().getColumn(0).setWidth(0);
TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable9.getColumnCount()-1; i++) {
        col= jTable9.getColumnModel().getColumn(i);
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
        Query query = em.createNamedQuery("Groupe.findByNomandaf").setParameter("nom", nom).setParameter("af", this.af);
        List<Groupe> fls = query.getResultList();
        return fls.get(0);
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
        jLabel74 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel60 = new javax.swing.JLabel();
        jComboBox20 = new javax.swing.JComboBox<>();
        jLabel61 = new javax.swing.JLabel();
        jComboBox21 = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel47 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jButton13 = new javax.swing.JButton();
        jTextField15 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel45 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel49 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox<>();
        jLabel50 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jTextField14 = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jComboBox14 = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jComboBox15 = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        jComboBox16 = new javax.swing.JComboBox<>();
        jSeparator11 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jButton8 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        jComboBox17 = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox<>();
        jLabel59 = new javax.swing.JLabel();
        jComboBox19 = new javax.swing.JComboBox<>();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jButton9 = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel62 = new javax.swing.JLabel();
        jComboBox22 = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        jComboBox23 = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();

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

        jLabel74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_page.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74)
                    .addComponent(jLabel4))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 188, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Chef de Scolarité");

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
                .addGap(52, 52, 52))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(17, 17, 17))
        );

        jPanel9.setBackground(new java.awt.Color(0, 188, 255));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel9MousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Gestion des Etudiants");

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-student-30.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(0, 188, 255));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Affectation des Enseignants");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("et des séances");

        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-health-points-30.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel6))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)))
                .addGap(18, 18, 18))
        );

        jPanel12.setBackground(new java.awt.Color(0, 188, 255));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Liste des étudiants");

        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-list-view-30.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );

        jPanel11.setBackground(new java.awt.Color(0, 188, 255));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Suppression des séances");

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-delete-document-30.png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(32, 32, 32))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel7))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(0, 188, 255));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Imprimer les bulletins");

        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-print-30.png"))); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(51, 51, 51))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel9)))
                .addContainerGap(38, Short.MAX_VALUE))
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
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(233, 233, 233))
        );

        kGradientPanel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 281, -1));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1250, 750));

        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 750));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Acceuil ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, -1, -1));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 95, 1244, 10));

        jLabel60.setBackground(new java.awt.Color(230, 230, 230));
        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel60.setText("Filiére :");
        jPanel2.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));

        jComboBox20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox20.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox20.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox20ItemStateChanged(evt);
            }
        });
        jComboBox20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox20ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox20, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 240, 40));

        jLabel61.setBackground(new java.awt.Color(230, 230, 230));
        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel61.setText("Groupe :");
        jPanel2.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, -1, -1));

        jComboBox21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox21.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox21.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox21ItemStateChanged(evt);
            }
        });
        jComboBox21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox21ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox21, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 130, 220, 40));

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
        jTable6.setRowHeight(80);
        jTable6.setSelectionBackground(new java.awt.Color(51, 153, 255));
        jTable6.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable6.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable6.setShowGrid(true);
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jPanel2.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 940, -1));

        jTabbedPane1.addTab("tab1", jPanel2);

        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel19.setBackground(new java.awt.Color(230, 230, 230));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setText("Information des étudiants :");

        jLabel20.setBackground(new java.awt.Color(230, 230, 230));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("CNE :");

        jLabel21.setBackground(new java.awt.Color(230, 230, 230));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("Nom :");

        jTextField3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel22.setBackground(new java.awt.Color(230, 230, 230));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Prénom :");

        jTextField4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jLabel23.setBackground(new java.awt.Color(230, 230, 230));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("Téléphone :");

        jLabel24.setBackground(new java.awt.Color(230, 230, 230));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Email :");

        jTextField5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel25.setBackground(new java.awt.Color(230, 230, 230));
        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setText("Date de Naissance :");

        jTextField7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel26.setBackground(new java.awt.Color(230, 230, 230));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Lieu de Naissance :");

        jTextField8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTextField9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel28.setBackground(new java.awt.Color(230, 230, 230));
        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Adresse :");

        jTextField10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel29.setBackground(new java.awt.Color(230, 230, 230));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("État :");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Actif", "Non actif" }));
        jComboBox2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add (1).png"))); // NOI18N
        jButton1.setText("Ajouter");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changes.png"))); // NOI18N
        jButton2.setText("Modifier");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(153, 153, 153));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        jButton3.setText("Supprimer");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "CIN", "CNE", "Nom", "Prénom", "Téléphone", "Email", "Date de Naissance", "Lieu", "Adresse", "État", "Groupe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.setRowHeight(40);
        jTable1.setSelectionBackground(new java.awt.Color(0, 153, 255));
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel43.setText("");
        jLabel43.setVisible(false);

        jLabel44.setBackground(new java.awt.Color(230, 230, 230));
        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel44.setText("CIN :");

        jTextField12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jDateChooser1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel47.setBackground(new java.awt.Color(230, 230, 230));
        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel47.setText("Filiére :");

        jComboBox10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox10.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox10ItemStateChanged(evt);
            }
        });
        jComboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox10ActionPerformed(evt);
            }
        });

        jLabel48.setBackground(new java.awt.Color(230, 230, 230));
        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel48.setText("Groupe :");

        jComboBox11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox11.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox11ItemStateChanged(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(153, 153, 153));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jButton13.setText("Rechercher");
        jButton13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jTextField15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox10, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jTextField9))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jTextField4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField5)
                        .addComponent(jTextField10)
                        .addComponent(jTextField7)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel43))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 301, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addComponent(jTextField15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox2, jTextField3, jTextField4, jTextField5, jTextField7, jTextField8, jTextField9});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox2, jTextField10, jTextField3, jTextField4, jTextField5, jTextField7, jTextField8, jTextField9});

        jTabbedPane1.addTab("tab2", jPanel3);

        jPanel5.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel30.setBackground(new java.awt.Color(230, 230, 230));
        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setText("Gestion des affectation :");

        jButton4.setBackground(new java.awt.Color(153, 153, 153));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-validation-16 (1).png"))); // NOI18N
        jButton4.setText("Affecter les enseignant");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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
                "Code affectation", "Module", "Mass Horraire", "Groupe", "Enseignant"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
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

        jLabel45.setBackground(new java.awt.Color(230, 230, 230));
        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setText("Filiére :");

        jComboBox8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox8.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox8ItemStateChanged(evt);
            }
        });
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });

        jLabel46.setBackground(new java.awt.Color(230, 230, 230));
        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setText("Groupe :");

        jComboBox9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox9.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox9ItemStateChanged(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(153, 153, 153));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-page-16.png"))); // NOI18N
        jButton6.setText("Affecter Une Séance");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                .addGap(68, 68, 68)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 405, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(155, Short.MAX_VALUE))
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
        jLabel31.setText("Gestion des affectation :");

        jButton5.setBackground(new java.awt.Color(153, 153, 153));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        jButton5.setText("Supprimer");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
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
                "Code Etudiant", "Nom - Prénom", "Groupe", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setGridColor(new java.awt.Color(204, 204, 204));
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

        jLabel49.setBackground(new java.awt.Color(230, 230, 230));
        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel49.setText("Filiére :");

        jComboBox12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox12.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox12ItemStateChanged(evt);
            }
        });
        jComboBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox12ActionPerformed(evt);
            }
        });

        jLabel50.setBackground(new java.awt.Color(230, 230, 230));
        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel50.setText("Groupe :");

        jComboBox13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox13.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox13.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox13ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(437, 437, 437)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator6))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 225, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator8)
                    .addContainerGap()))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(249, 249, 249)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(498, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1250, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab2", jPanel6);

        jPanel13.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel27.setBackground(new java.awt.Color(230, 230, 230));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Id affectation :");

        jLabel51.setBackground(new java.awt.Color(230, 230, 230));
        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel51.setText("Groupe :");

        jTextField6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField6.setEnabled(false);
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel32.setBackground(new java.awt.Color(230, 230, 230));
        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("Module :");

        jLabel33.setBackground(new java.awt.Color(230, 230, 230));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setText("Enseignant :");

        jTextField11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField11.setEnabled(false);

        jTextField13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField13.setEnabled(false);

        jLabel37.setBackground(new java.awt.Color(230, 230, 230));
        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel37.setText("Gestion des séances :");

        jTextField14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField14.setEnabled(false);
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
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

        jLabel53.setBackground(new java.awt.Color(230, 230, 230));
        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel53.setText("Heure du début :");

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08:00:00", "10:00:00", "14:00:00", "16:00:00" }));
        jComboBox15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox15.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox15.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox15ItemStateChanged(evt);
            }
        });
        jComboBox15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox15ActionPerformed(evt);
            }
        });

        jLabel54.setBackground(new java.awt.Color(230, 230, 230));
        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel54.setText("Heure du fin :");

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10:00:00", "12:00:00", "16:00:00", "18:00:00" }));
        jComboBox16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox16.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox16.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox16ItemStateChanged(evt);
            }
        });
        jComboBox16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox16ActionPerformed(evt);
            }
        });

        jTable5.setAutoCreateRowSorter(true);
        jTable5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable5.setAlignmentY(0.0F);
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

        jTable7.setAutoCreateRowSorter(true);
        jTable7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
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

        jButton7.setBackground(new java.awt.Color(153, 153, 153));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-validation-16 (1).png"))); // NOI18N
        jButton7.setText("Affecter Une Séance");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel55.setBackground(new java.awt.Color(230, 230, 230));
        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel55.setText("Horaire du Groupe");

        jLabel56.setBackground(new java.awt.Color(230, 230, 230));
        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel56.setText("Horaire du professeur");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator9)
            .addComponent(jSeparator10)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField6)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jTextField13))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(493, 493, 493)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82))))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(385, 385, 385)
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(388, 388, 388)
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel56)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel13);

        jPanel15.setPreferredSize(new java.awt.Dimension(1200, 750));

        jPanel16.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel34.setBackground(new java.awt.Color(230, 230, 230));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setText("Supression des séances :");

        jButton8.setBackground(new java.awt.Color(153, 153, 153));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        jButton8.setText("Supprimer");
        jButton8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTable4.setAutoCreateRowSorter(true);
        jTable4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Code Séance", "Jour", "Heure Début", "Heure Fin", "Module", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setGridColor(new java.awt.Color(204, 204, 204));
        jTable4.setRowHeight(40);
        jTable4.setSelectionBackground(new java.awt.Color(0, 153, 255));
        jTable4.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable4.setShowGrid(true);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

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

        jLabel59.setBackground(new java.awt.Color(230, 230, 230));
        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel59.setText("Enseignant :");

        jComboBox19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox19.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox19.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox19ItemStateChanged(evt);
            }
        });

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
                                .addGap(437, 437, 437)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel57)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 961, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
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
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
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

        jPanel18.setPreferredSize(new java.awt.Dimension(1200, 750));

        jPanel19.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel35.setBackground(new java.awt.Color(230, 230, 230));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel35.setText("Impression des Bulletins : ");

        jButton9.setBackground(new java.awt.Color(153, 153, 153));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-page-16.png"))); // NOI18N
        jButton9.setText("Imprimer");
        jButton9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel62.setBackground(new java.awt.Color(230, 230, 230));
        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel62.setText("Filiére :");

        jComboBox22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox22.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox22.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox22ItemStateChanged(evt);
            }
        });
        jComboBox22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox22ActionPerformed(evt);
            }
        });

        jLabel63.setBackground(new java.awt.Color(230, 230, 230));
        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel63.setText("Groupe :");

        jComboBox23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox23.setPreferredSize(new java.awt.Dimension(200, 32));
        jComboBox23.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox23ItemStateChanged(evt);
            }
        });

        jTable9.setAutoCreateRowSorter(true);
        jTable9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable9.setModel(new javax.swing.table.DefaultTableModel(
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
                false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable9.setGridColor(new java.awt.Color(204, 204, 204));
        jTable9.setRowHeight(40);
        jTable9.setSelectionBackground(new java.awt.Color(0, 153, 255));
        jTable9.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable9.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable9.setShowGrid(true);
        jTable9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable9MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTable9);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator14))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(437, 437, 437)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(jLabel62)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156)
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(231, Short.MAX_VALUE))
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator15)
                    .addContainerGap()))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addGap(249, 249, 249)
                    .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(498, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel18);

        jPanel20.setPreferredSize(new java.awt.Dimension(1200, 750));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setPreferredSize(new java.awt.Dimension(850, 750));

        jLabel36.setBackground(new java.awt.Color(230, 230, 230));
        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setText("Le ministère de l’Enseignement supérieur");

        jLabel64.setBackground(new java.awt.Color(230, 230, 230));
        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel64.setText("Nom et Prénom :");

        jLabel65.setBackground(new java.awt.Color(230, 230, 230));
        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel65.setText("Filiére :");

        jTable10.setAutoCreateRowSorter(true);
        jTable10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Module", "Note", "Résultat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable10.setGridColor(new java.awt.Color(0, 0, 0));
        jTable10.setRowHeight(40);
        jTable10.setSelectionBackground(new java.awt.Color(153, 153, 153));
        jTable10.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable10.setShowGrid(true);
        jTable10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable10MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTable10);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("jLabel10");

        jLabel66.setBackground(new java.awt.Color(230, 230, 230));
        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel66.setText("le");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("jLabel11");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("jLabel12");

        jLabel67.setBackground(new java.awt.Color(230, 230, 230));
        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel67.setText("BULLETIN ANNUEL");

        jLabel68.setBackground(new java.awt.Color(230, 230, 230));
        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel68.setText("à");

        jLabel69.setBackground(new java.awt.Color(230, 230, 230));
        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel69.setText("Année Universitaire :");

        jLabel70.setBackground(new java.awt.Color(230, 230, 230));
        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel70.setText("Matricule :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("jLabel11");

        jLabel71.setBackground(new java.awt.Color(230, 230, 230));
        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel71.setText("CIN :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("jLabel11");

        jLabel72.setBackground(new java.awt.Color(230, 230, 230));
        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel73.setBackground(new java.awt.Color(230, 230, 230));
        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142)
                                .addComponent(jLabel66)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel68))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(332, 332, 332)
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1617, 1617, 1617))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel69))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(207, 207, 207))
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18))))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator16))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(1, 1, 1)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 1073, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel21.getAccessibleContext().setAccessibleName("2480 x 3508");

        jTabbedPane1.addTab("tab2", jPanel20);

        kGradientPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, -37, 970, 920));

        getContentPane().add(kGradientPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void clearComponent() {
        jTextField3.setText("");
        jTextField3.setEditable(true);
        jTextField12.setText("");
        jTextField9.setText("");
        jTextField8.setText("");
        jTextField4.setText("");
        jTextField7.setText("");
        jDateChooser1.setDate(new Date());
        jTextField5.setText("");
        jTextField7.setText("");
//        jPasswordField1.setText("");
        // jComboBox1.setSelectedIndex(0);
        jTextField10.setText("");
        jComboBox2.setSelectedIndex(0);

    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int index = jTable1.getSelectedRow();
        TableModel tm = jTable1.getModel();
        jLabel43.setText(tm.getValueAt(index, 0).toString());
        jTextField3.setText(tm.getValueAt(index, 2).toString());
        jTextField3.setEditable(false);
        jTextField12.setText(tm.getValueAt(index, 1).toString());
        jTextField9.setText(tm.getValueAt(index, 3).toString());
        jTextField8.setText(tm.getValueAt(index, 4).toString());
        jTextField4.setText(tm.getValueAt(index, 5).toString());
        jTextField5.setText(tm.getValueAt(index, 6).toString());
        jTextField7.setText(tm.getValueAt(index, 8).toString());
//        jPasswordField1.setText(new String(tm.getValueAt(index, 6).toString()));
//        jComboBox1.setSelectedItem(tm.getValueAt(index, 8).toString());
        jTextField10.setText(tm.getValueAt(index, 9).toString());
        jComboBox2.setSelectedItem(tm.getValueAt(index, 10).toString());
        jComboBox10.setSelectedItem(tm.getValueAt(index, 11).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        boolean delete = false;
        Etudiant u2 = new Etudiant();
        u2.setId(Integer.parseInt(jLabel43.getText()));

        delete = u2.delete();

        if (delete) {
            JOptionPane.showMessageDialog(null, "L'Etudiant à été supprimer");
            clearComponent();

        }
        refreshTable1();

        refreshTable2();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        boolean update = false;
        if (!jTextField5.getText().contains("@")) {
            JOptionPane.showMessageDialog(null, " Email Non valide!");
        } else {
            Etudiant u2 = new Etudiant();
            u2.setId(Integer.parseInt(jLabel43.getText()));
            u2.setMatricule(jTextField3.getText());
            u2.setCin(jTextField12.getText());
            u2.setNom(jTextField9.getText());
            u2.setPrenom(jTextField8.getText());
            u2.setEmail(jTextField5.getText());
            u2.setTelephone(jTextField4.getText());
            u2.setLieuN(jTextField7.getText());
            u2.setAdresse(jTextField10.getText());
            u2.setDateN(null);
            u2.setEtat((jComboBox2.getSelectedItem().toString() == "Actif") ? true : false);
            u2.setAf(af);
            u2.setGroupe(getcodegroupeselected(jComboBox11.getSelectedItem().toString()));
            update = u2.update();

            if (update) {
                JOptionPane.showMessageDialog(null, "Les données sont bien Modifier");
                clearComponent();

            }
        }

        refreshTable1();

        refreshTable2();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        boolean insert = false;
        if (!jTextField5.getText().contains("@")) {
            JOptionPane.showMessageDialog(null, " Email Non valide!");
        } else if (jDateChooser1.getDate() == null || jDateChooser1.getDate().after(new Date()))
            JOptionPane.showMessageDialog(null, " La date de Naissance Non valide!");
        else {
            Etudiant u2 = new Etudiant();
            u2.setMatricule(jTextField3.getText());
            u2.setCin(jTextField12.getText());
            u2.setNom(jTextField9.getText());
            u2.setPrenom(jTextField8.getText());
            u2.setEmail(jTextField5.getText());
            u2.setTelephone(jTextField4.getText());
            u2.setLieuN(jTextField7.getText());
            u2.setDateN(jDateChooser1.getDate());
            u2.setAf(this.af);
            u2.setAdresse(jTextField10.getText());
//            u2.setRole(jComboBox1.getSelectedItem().toString().toLowerCase());
            u2.setEtat((jComboBox2.getSelectedItem().toString() == "Actif") ? true : false);
            u2.setGroupe(getcodegroupeselected(jComboBox11.getSelectedItem().toString()));

            insert = u2.create();

            if (insert) {
                JOptionPane.showMessageDialog(null, "L'étudiant bien Inséré");
                refreshTable1();
                refreshTable2();

            } else {
                JOptionPane.showMessageDialog(null, "Etudiant Existe Déja");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c))) {
            evt.consume();// ignorer l'événement
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        for (int i = 0; i < jTable2.getRowCount(); i++) {
            if (jTable2.getValueAt(i, 4) != null) {

                Utilisateur u = new Utilisateur(getcodeensegnant(jTable2.getValueAt(i, 4).toString()));
                ModuleEnseignantGroupe u2 = new ModuleEnseignantGroupe();
                u2.setId(Integer.parseInt(jTable2.getValueAt(i, 0).toString()));
                u2.setEnseignant(u);
                u2.update();

            }
        }
        JOptionPane.showMessageDialog(null, "Les données sont bien Modifier");
        refreshTable2();
    }//GEN-LAST:event_jButton4ActionPerformed
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

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jComboBox8ActionPerformed

    private void jComboBox8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox8ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            Query query = em.createQuery("SELECT g FROM Groupe g WHERE g.af = :af and g.etat=1 and g.filiere=:filiere");
            query.getParameters().clear();
            query.setParameter("af", af);
            query.setParameter("filiere", getcodefiliereSelected(jComboBox8.getSelectedItem().toString()));
            List<Groupe> grps = query.getResultList();

            jComboBox9.removeAllItems();
            for (Groupe g : grps) {
                jComboBox9.addItem(g.getNom());
            }
            refreshTable2();
        }

    }//GEN-LAST:event_jComboBox8ItemStateChanged

    private void jComboBox9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox9ItemStateChanged
        // TODO add your handling code here:

        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            refreshTable2();
        }
    }//GEN-LAST:event_jComboBox9ItemStateChanged

    private void jComboBox10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox10ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            Query query = em.createQuery("SELECT g FROM Groupe g WHERE g.af = :af and g.etat=1 and g.filiere=:filiere");
            query.getParameters().clear();
            query.setParameter("af", af);
            query.setParameter("filiere", getcodefiliereSelected(jComboBox10.getSelectedItem().toString()));
            List<Groupe> grps = query.getResultList();

            jComboBox11.removeAllItems();
            for (Groupe g : grps) {
                jComboBox11.addItem(g.getNom());
            }
        }
    }//GEN-LAST:event_jComboBox10ItemStateChanged

    private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox10ActionPerformed

    private void jComboBox11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox11ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox11ItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            for (int i = 0; i < jTable3.getRowCount(); i++) {
                if (jTable3.getValueAt(i, 3) != null) {
                    if ((boolean) jTable3.getValueAt(i, 3) == true) {
                        Query query = em.createQuery("UPDATE Etudiant SET groupe = null WHERE matricule = :matricule AND af = :af");
                        query.setParameter("matricule", jTable3.getValueAt(i, 0).toString());
                        query.setParameter("af", this.af);
                        query.executeUpdate();
                        tx.commit();
                    }
                }
            }
            refreshTable3();
            refreshTable1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jComboBox12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox12ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            Query query = em.createQuery("SELECT g FROM Groupe g WHERE g.af = :af and g.etat=1 and g.filiere=:filiere");
            query.getParameters().clear();
            query.setParameter("af", af);
            query.setParameter("filiere", getcodefiliereSelected(jComboBox12.getSelectedItem().toString()));
            List<Groupe> grps = query.getResultList();

            jComboBox13.removeAllItems();
            for (Groupe g : grps) {
                jComboBox13.addItem(g.getNom());
            }
            refreshTable3();
        }

    }//GEN-LAST:event_jComboBox12ItemStateChanged

    private void jComboBox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox12ActionPerformed

    private void jComboBox13ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox13ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            refreshTable3();
        }
    }//GEN-LAST:event_jComboBox13ItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        int selectedrow = -1;
        selectedrow = jTable2.getSelectedRow();
        if (selectedrow >= 0) {
            if (jTable2.getValueAt(selectedrow, 4) == null) {
                JOptionPane.showMessageDialog(null, "Veillez sélectionner une affectation valide (Enseignant affecter)");
            } else {
                Query query = em.createNamedQuery("ModuleEnseignantGroupe.findById").setParameter("id", jTable2.getValueAt(selectedrow, 0));
                List<ModuleEnseignantGroupe> lst = query.getResultList();
                jTabbedPane1.setSelectedIndex(4);
                jTextField6.setText(lst.get(0).getId().toString());
                jTextField13.setText(lst.get(0).getModule().getNom());
                jTextField11.setText(lst.get(0).getEnseignant().getNom() + " " + lst.get(0).getEnseignant().getPrenom());
                jTextField14.setText(lst.get(0).getGroupe().getNom());
                refreshTable4();
                refreshTable5();
            }
        } else
            JOptionPane.showMessageDialog(null, "Veillez sélectionner une affectation");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            java.sql.Time timeValue = new java.sql.Time(formatter.parse(jComboBox15.getSelectedItem().toString()).getTime());
            java.sql.Time timeValue1 = new java.sql.Time(formatter.parse(jComboBox16.getSelectedItem().toString()).getTime());
            if (timeValue.before(timeValue1)) {
                if (timeValue1.getTime() - timeValue.getTime() != 7200000) {
                    JOptionPane.showMessageDialog(null, "Laséance ne doit pas dépasser 2h");
                } else {
                    Seance s = new Seance(null, jComboBox14.getSelectedItem().toString(), timeValue, timeValue1);
                    s.setAf(af);

                    Query query = em.createNamedQuery("ModuleEnseignantGroupe.findById");
                    query.setParameter("id", Integer.parseInt(jTextField6.getText().toString()));

                    List<ModuleEnseignantGroupe> lst = query.getResultList();

                    s.setIdMeg(lst.get(0));

                    Boolean create = s.create();
                    if (create) {
                        JOptionPane.showMessageDialog(null, "La séance a été enregistré");
                        refreshTable4();
                        refreshTable5();
                    } else {
                        JOptionPane.showMessageDialog(null, "La séance  est occupé");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lveillez vérifier l'heure du début et du fin");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable7MouseClicked

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void jComboBox16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox16ActionPerformed

    private void jComboBox16ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox16ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox16ItemStateChanged

    private void jComboBox15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox15ActionPerformed

    private void jComboBox15ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox15ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox15ItemStateChanged

    private void jComboBox14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox14ActionPerformed

    private void jComboBox14ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox14ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox14ItemStateChanged

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            for (int i = 0; i < jTable4.getRowCount(); i++) {
                if (jTable4.getValueAt(i, 5) != null) {
                    if (((boolean) jTable4.getValueAt(i, 5) == true) == true) {
                        Query query = em.createQuery("DELETE from Seance where id=:id");
                        query.setParameter("id", Integer.parseInt(jTable4.getValueAt(i, 0).toString()));
                        query.executeUpdate();

                    }
                }
            }
            tx.commit();
            refreshTable6();
            refreshTable4();
            refreshTable5();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void jComboBox17ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox17ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            Query query = em.createQuery("SELECT g FROM Groupe g WHERE g.af = :af and g.etat=1 and g.filiere=:filiere");
            query.getParameters().clear();
            query.setParameter("af", af);
            query.setParameter("filiere", getcodefiliereSelected(jComboBox17.getSelectedItem().toString()));
            List<Groupe> grps = query.getResultList();

            jComboBox18.removeAllItems();
            for (Groupe g : grps) {
                jComboBox18.addItem(g.getNom());
            }
            refreshTable6();
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

    private void jComboBox19ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox19ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            refreshTable6();
        }
    }//GEN-LAST:event_jComboBox19ItemStateChanged

    private void jComboBox20ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox20ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            Query query = em.createQuery("SELECT g FROM Groupe g WHERE g.af = :af and g.etat=1 and g.filiere=:filiere");
            query.getParameters().clear();
            query.setParameter("af", af);
            query.setParameter("filiere", getcodefiliereSelected(jComboBox20.getSelectedItem().toString()));
            List<Groupe> grps = query.getResultList();

            jComboBox21.removeAllItems();
            for (Groupe g : grps) {
                jComboBox21.addItem(g.getNom());
            }
            refreshTable7();
        }
    }//GEN-LAST:event_jComboBox20ItemStateChanged

    private void jComboBox20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox20ActionPerformed

    private void jComboBox21ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox21ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            refreshTable7();
        }
    }//GEN-LAST:event_jComboBox21ItemStateChanged

    private void jComboBox21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox21ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox21ActionPerformed

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable6MouseClicked

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(6);
        
        setbackground(jPanel17);
        resetbackground(jPanel8);
        resetbackground(jPanel10);
        resetbackground(jPanel11);
        resetbackground(jPanel12);
        resetbackground(jPanel9);
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
        refresdashboard();
        setbackground(jPanel11);
        resetbackground(jPanel8);
        resetbackground(jPanel10);
        resetbackground(jPanel9);
        resetbackground(jPanel12);
        resetbackground(jPanel17);
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
        refresdashboard();
        
        setbackground(jPanel12);
        resetbackground(jPanel8);
        resetbackground(jPanel10);
        resetbackground(jPanel11);
        resetbackground(jPanel9);
        resetbackground(jPanel17);
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
        refresdashboard();
        setbackground(jPanel10);
        resetbackground(jPanel8);
        resetbackground(jPanel9);
        resetbackground(jPanel11);
        resetbackground(jPanel12);
        resetbackground(jPanel17);

    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MousePressed

        jTabbedPane1.setSelectedIndex(1);
        refresdashboard();
     setbackground(jPanel9);
        resetbackground(jPanel8);
        resetbackground(jPanel10);
        resetbackground(jPanel11);
        resetbackground(jPanel12);
        resetbackground(jPanel17);
    }//GEN-LAST:event_jPanel9MousePressed

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        jTabbedPane1.setSelectedIndex(0);
        refresdashboard();
setbackground(jPanel8);
        resetbackground(jPanel9);
        resetbackground(jPanel10);
        resetbackground(jPanel11);
        resetbackground(jPanel12);
        resetbackground(jPanel17);
    }//GEN-LAST:event_jPanel8MouseClicked

    
    public void remplirtable10(int id)
    {
         try {

            DefaultTableModel model = (DefaultTableModel) jTable10.getModel();
            int rowCount = model.getRowCount();
//Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
                Object[] row;
                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("select DISTINCT module.nom ,(((notes.note_1 +notes.note_2)/2 +notes.note_f)/2 )- ( (select  COUNT( DISTINCT absence.id) from absence INNER join seance on seance.id=absence.seance where etudiant=etudiant.id ) * 0.25)  as notes_f ,CASE WHEN (((notes.note_1 +notes.note_2)/2 +notes.note_f)/2 )- ( (select  COUNT( DISTINCT absence.id) from absence INNER join seance on seance.id=absence.seance where etudiant=etudiant.id ) * 0.25)<5 then 'Non validé' WHEN (((notes.note_1 +notes.note_2)/2 +notes.note_f)/2 )- ( (select  COUNT( DISTINCT absence.id) from absence INNER join seance on seance.id=absence.seance where etudiant=etudiant.id ) * 0.25)>=10 THEN 'validé' ELSE 'Rattrapage' END as resultat\n" +
"from module inner join module_enseignant_groupe on module_enseignant_groupe.module=module.id_module\n" +
"INNER join etudiant on etudiant.groupe=module_enseignant_groupe.groupe \n" +
"inner join notes on notes.meg=module_enseignant_groupe.id and etudiant.id =notes.etudiant\n" +
"where etudiant.id=?");
                preparedStatement.setInt(1, id);
                resultatpreparedStatement = preparedStatement.executeQuery();
                float total=0;
                
                
                while (resultatpreparedStatement.next()) {
                    row = new Object[3];    
                
                    row[0] = resultatpreparedStatement.getString("nom");
                    row[1] = resultatpreparedStatement.getFloat("notes_f");
                    row[2] = resultatpreparedStatement.getString("resultat");
                    
                    total=total+resultatpreparedStatement.getFloat("notes_f");
                    
                    
                    model.addRow(row);

                }
                      if(model.getRowCount()>0)
                    {
                         row = new Object[3];    
                        row[0]="Moyenne Total";
                        row[1]=total/model.getRowCount();
                        row[2]="";
                        
                        model.addRow(row);
                    }
                
                
                
                
                
                
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
     public void setbackground(JPanel p)
     {
         p.setBackground(new Color(0,150,255));
     }
     public void resetbackground(JPanel p)
     {
         p.setBackground(new Color(0,188,255));
     }
    public void remplirchamps(int id)
    {
           try {

                PreparedStatement preparedStatement = null;
                ResultSet resultatpreparedStatement = null;
                preparedStatement = connection_db.connecter().prepareStatement("SELECT DISTINCT concat(etudiant.nom,' ',etudiant.prenom) as nom,etudiant.matricule,etudiant.cin ,filiere.nom as filiere ,etudiant.AF ,CURDATE() as date,'Casablanca' ville from etudiant INNER JOIN groupe on etudiant.groupe=groupe.id_groupe INNER join filiere on groupe.filiere=filiere.code_filiere WHERE etudiant.id=?");
                preparedStatement.setInt(1, id);
                resultatpreparedStatement = preparedStatement.executeQuery();
                if (resultatpreparedStatement.next()) {
                   jLabel72.setText(resultatpreparedStatement.getString("date"));
                   jLabel73.setText( resultatpreparedStatement.getString("ville"));
                   jLabel10.setText(resultatpreparedStatement.getString("nom"));
                   jLabel11.setText(resultatpreparedStatement.getString("filiere"));
                   jLabel12.setText(resultatpreparedStatement.getString("af"));
                   jLabel13.setText(resultatpreparedStatement.getString("matricule"));
                   jLabel14.setText(resultatpreparedStatement.getString("cin"));
                }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       
        PrinterJob printerJob=PrinterJob.getPrinterJob();
        printerJob.setJobName("Printer Record");
        
        printerJob.setPrintable(new Printable(){
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex>0)
                    return Printable.NO_SUCH_PAGE;
                
                Graphics2D  graphics2D=(Graphics2D)graphics;
                graphics2D.translate(pageFormat.getImageableX()*2, pageFormat.getImageableY()*2);
                graphics2D.scale(0.5, 0.5);
                jPanel21.paint(graphics2D);
                
                return Printable.PAGE_EXISTS;
                }
            }
        );
                boolean returnResults=printerJob.printDialog();
        if (returnResults)
        {
            try{
                for (int i = 0; i < jTable9.getRowCount(); i++) {
                    if (jTable9.getValueAt(i, 3) != null) {
                          if ((boolean) jTable9.getValueAt(i, 3) == true) {
                              remplirchamps(Integer.parseInt(jTable9.getValueAt(i, 0).toString()));
                              remplirtable10(Integer.parseInt(jTable9.getValueAt(i, 0).toString()));
                                 printerJob.print();
                                 
                                 
                            }   
                    }
                }
                
                
                
                //jTabbedPane1.setSelectedIndex(7);
                
            
//            printerJob.print();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_jButton9ActionPerformed

    
    
    private void jComboBox22ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox22ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            Query query = em.createQuery("SELECT g FROM Groupe g WHERE g.af = :af and g.etat=1 and g.filiere=:filiere");
            query.getParameters().clear();
            query.setParameter("af", af);
            query.setParameter("filiere", getcodefiliereSelected(jComboBox22.getSelectedItem().toString()));
            List<Groupe> grps = query.getResultList();

            jComboBox23.removeAllItems();
            for (Groupe g : grps) {
                jComboBox23.addItem(g.getNom());
            }
            refreshTable8();
        }
    }//GEN-LAST:event_jComboBox22ItemStateChanged

    private void jComboBox22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox22ActionPerformed

    private void jComboBox23ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox23ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            refreshTable8();
        }
    }//GEN-LAST:event_jComboBox23ItemStateChanged

    private void jTable9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable9MouseClicked

    private void jTable10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable10MouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Gestion_Scolaire.Interface_Authantification t=new Gestion_Scolaire.Interface_Authantification();
        t.setVisible(true);

    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
 try {
            jTable1.setModel(new DefaultTableModel(new Object[][]{},
                    new String[]{"id", "CIN", "CNE", "Nom", "Prénom", "Téléphone", "Email", "Date de Naissance", "Lieu", "Adresse", "État", "Groupe"}));
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            Object[] row;
            PreparedStatement preparedStatement = null;
            ResultSet resultatpreparedStatement = null;
            preparedStatement = connection_db.connecter().prepareStatement("SELECT * from Etudiant where af=? and ( cin like ? or matricule like ? or nom like ? or prenom like ? )");
            preparedStatement.setString(1, this.af.getAf());
            preparedStatement.setString(2, jTextField15.getText()+"%");
            preparedStatement.setString(3, jTextField15.getText()+"%");
            preparedStatement.setString(4, jTextField15.getText()+"%");
            preparedStatement.setString(5, jTextField15.getText()+"%");
            
            resultatpreparedStatement = preparedStatement.executeQuery();

            while (resultatpreparedStatement.next()) {
                Etudiant next = new Etudiant(resultatpreparedStatement.getInt("id"),
                        resultatpreparedStatement.getString("Matricule"),
                        resultatpreparedStatement.getString("cin"),
                        resultatpreparedStatement.getDate("DateN"),
                        resultatpreparedStatement.getString("lieuN"),
                        resultatpreparedStatement.getString("adresse"),
                        resultatpreparedStatement.getString("telephone"),
                        resultatpreparedStatement.getString("nom"),
                        resultatpreparedStatement.getString("prenom"),
                        resultatpreparedStatement.getString("email"),
                        resultatpreparedStatement.getBoolean("etat"));

                Query query = em.createNamedQuery("Groupe.findByIdGroupe");
                query.getParameters().clear();
                query.setParameter("idGroupe", resultatpreparedStatement.getInt("groupe"));
                List<Groupe> grps = query.getResultList();
                if (grps.size() > 0) {
                    next.setGroupe(grps.get(0));
                }

                row = new Object[12];
                row[0] = next.getId();
                row[1] = next.getCin();
                row[2] = next.getMatricule();
                row[3] = next.getNom();
                row[4] = next.getPrenom();
                row[5] = next.getTelephone();
                row[6] = next.getEmail();
                row[7] = next.getDateN();
                row[8] = next.getLieuN();
                row[9] = next.getAdresse();
                row[10] = next.getEtat() ? "Actif" : "Non Actif";
                if (grps.size() > 0) {
                    row[11] = next.getGroupe().getNom();
                }
                model.addRow(row);
            }
            
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
jTable1.getColumnModel().getColumn(0).setWidth(0);
TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable1.getColumnCount(); i++) {
        col= jTable1.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        } catch (Exception e) {
            e.printStackTrace();
        }      

    }//GEN-LAST:event_jButton13ActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard_chef_scolarite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard_chef_scolarite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard_chef_scolarite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard_chef_scolarite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox19;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox20;
    private javax.swing.JComboBox<String> jComboBox21;
    private javax.swing.JComboBox<String> jComboBox22;
    private javax.swing.JComboBox<String> jComboBox23;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
