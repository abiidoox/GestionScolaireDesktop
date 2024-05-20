/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin;
import beans.*;
import beans.Module;
import java.awt.Color;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class Dashboard extends javax.swing.JFrame {
    private static Utilisateur u1;
    private static Anneeformation af;



    /**
     * Creates new form Dashboard
     */
    public Dashboard(Utilisateur u,Anneeformation af) {
         this.u1=u;
         this.af=af;
      
                     initComponents();  
                     refresdashboard();
                    refreshTable1();
                    refreshTable2();
                    refreshTable3();
                    refreshTable4();
                    
                     
        
            
          /*  for (Filiere filieres : Filieres) {
                jComboBox4.setModel(filieres.getNom());*/
        //}
           
     
    }
    void refresdashboard(){
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        Query query = em.createQuery("SELECT f FROM Filiere f WHERE f.af = :af");
        query.setParameter("af", af);
        List<Filiere> filieres = query.getResultList();
                    int count=filieres.size();
                    jLabel14.setText(String.valueOf(count)) ;               
          query = em.createQuery("SELECT m FROM Module m WHERE m.af = :af and m.etat=1");
          query.getParameters().clear();
        query.setParameter("af", af);
            List<Module> module = query.getResultList();
            count=module.size();
                    jLabel15.setText(String.valueOf(count)) ;
            query = em.createNamedQuery("Utilisateur.findAll");
          query.getParameters().clear();
        List<Utilisateur> uti = query.getResultList();
                     count=uti.size();
                    jLabel13.setText(String.valueOf(count)) ;
                     query = em.createQuery("SELECT g FROM Groupe g WHERE g.af = :af and g.etat=1");
          query.getParameters().clear();
        query.setParameter("af", af);
        List<Groupe> grps = query.getResultList();
                     count=grps.size();
                    jLabel16.setText(String.valueOf(count)) ;
          
        
        jComboBox4.removeAllItems();
        jComboBox6.removeAllItems();
        for (Filiere f : filieres) {
            jComboBox4.addItem(f.getNom());
            jComboBox6.addItem(f.getNom());
            
        }
    }
public void refreshTable1() {
        try{
            jTable1.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"Matricule", "Nom", "Prénom", "Téléphone", "Email", "Pseudo", "Mot de passe", "Adresse", "Role", "État"}));
		DefaultTableModel model = (DefaultTableModel)  jTable1.getModel();
            
        
		Object[] row;
                        PreparedStatement preparedStatement = null;
                        ResultSet resultatpreparedStatement = null;
                       

			preparedStatement = connection_db.connecter().prepareStatement("SELECT * from Utilisateur");

			resultatpreparedStatement = preparedStatement.executeQuery();

			while (resultatpreparedStatement.next()) {
                            Utilisateur next = new Utilisateur(resultatpreparedStatement.getString("Matricule"),
						resultatpreparedStatement.getString("nom"), 
                                                resultatpreparedStatement.getString("prenom"),                                     
						resultatpreparedStatement.getString("telephone"), 
						resultatpreparedStatement.getString("email"),
                                                resultatpreparedStatement.getString("login"),
                                                resultatpreparedStatement.getString("password"),
                                                resultatpreparedStatement.getString("role"),
                                                resultatpreparedStatement.getString("adresse"),
                                                resultatpreparedStatement.getBoolean("etat"));
                                
                         row = new Object[10];
			row[0] = next.getMatricule();
			row[1] = next.getNom();
			row[2] = next.getPrenom();
			row[3] = next.getTelephone();
			row[4] = next.getEmail();
                        
			// row[4] = employer.getDate_embauche();
			row[5] = next.getLogin();
                        row[6] = next.getPassword();
                        row[7] = next.getAdresse();
                        row[8] = next.getRole();
                        row[9] =  next.getEtat()?"Actif":"Non Actif";
				model.addRow(row);
			}

                     
TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable1.getColumnCount(); i++) {
        col= jTable1.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
			
    }


public void refreshTable2() {
        try{
            jTable2.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"code","Libelle"}));
		DefaultTableModel model = (DefaultTableModel)  jTable2.getModel();
            
        
		Object[] row;
                        PreparedStatement preparedStatement = null;
                        ResultSet resultatpreparedStatement = null;
                       

			preparedStatement = connection_db.connecter().prepareStatement("SELECT * from Filiere where af=?");
                        preparedStatement.setString(1, this.af.getAf());
			resultatpreparedStatement = preparedStatement.executeQuery();

			while (resultatpreparedStatement.next()) {
                            Filiere next = new Filiere(resultatpreparedStatement.getString("code_filiere"),
						resultatpreparedStatement.getString("nom"));
                                
                         row = new Object[3];
			row[0] = next.getCodeFiliere();
			row[1] = next.getNom();
                	model.addRow(row);
			}
                     
TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable2.getColumnCount(); i++) {
        col= jTable2.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        
        

        }
        catch(Exception e){
            e.printStackTrace();
        }
        
			
    }
        void refreshTable3(){
                 try{
            jTable3.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"code Module", "Libelle", "Masse Horaire", "Filiére", "État"}));
		DefaultTableModel model = (DefaultTableModel)  jTable3.getModel();
            
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        
		Object[] row;
                        PreparedStatement preparedStatement = null;
                        ResultSet resultatpreparedStatement = null;
                       

			preparedStatement = connection_db.connecter().prepareStatement("SELECT * from module where af=?");
                        preparedStatement.setString(1, this.af.getAf());
			resultatpreparedStatement = preparedStatement.executeQuery();

			while (resultatpreparedStatement.next()) {
                            Module next = new Module(resultatpreparedStatement.getInt("id_module"),resultatpreparedStatement.getString("nom"),resultatpreparedStatement.getLong("mh"),resultatpreparedStatement.getBoolean("etat"));
                            Query query = em.createNamedQuery("Filiere.findByCodeFiliere");
                            query.setParameter("codeFiliere", resultatpreparedStatement.getString("filiere"));
                            next.setFiliere((Filiere)query.getResultList().get(0));
                            
                         row = new Object[5];
			row[0] = next.getIdModule();
			row[1] = next.getNom();
                        row[2] = next.getMh();
                        row[4] = (next.getEtat())?"Actif":"Non actif";
                        row[3] = next.getFiliere().getNom();
                	model.addRow(row);
			}
jTable3.getColumnModel().getColumn(0).setMinWidth(0);
jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
jTable3.getColumnModel().getColumn(0).setWidth(0);



TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable3.getColumnCount(); i++) {
        col= jTable3.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        }

	void refreshTable4()
        {
              try{
            jTable4.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"code  Groupe", "Libelle", "Année", "Filiére", "État"}));
		DefaultTableModel model = (DefaultTableModel)  jTable4.getModel();
            
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        
		Object[] row;
                        PreparedStatement preparedStatement = null;
                        ResultSet resultatpreparedStatement = null;
                       

			preparedStatement = connection_db.connecter().prepareStatement("SELECT * from Groupe where af=?");
                        preparedStatement.setString(1, this.af.getAf());
			resultatpreparedStatement = preparedStatement.executeQuery();

			while (resultatpreparedStatement.next()) {
                            Groupe next = new Groupe(resultatpreparedStatement.getInt("id_groupe"),resultatpreparedStatement.getString("nom"),resultatpreparedStatement.getInt("annee"),resultatpreparedStatement.getBoolean("etat"));
                            Query query = em.createNamedQuery("Filiere.findByCodeFiliere");
                            query.setParameter("codeFiliere", resultatpreparedStatement.getString("filiere"));
                            next.setFiliere((Filiere)query.getResultList().get(0));
                            
                         row = new Object[5];
			row[0] = next.getIdGroupe();
			row[1] = next.getNom();
                        row[2] = next.getAnnee();
                        row[4] = (next.getEtat())?"Actif":"Non actif";
                        row[3] = next.getFiliere().getNom();
                	model.addRow(row);
			}
jTable4.getColumnModel().getColumn(0).setMinWidth(0);
jTable4.getColumnModel().getColumn(0).setMaxWidth(0);
jTable4.getColumnModel().getColumn(0).setWidth(0);
TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable4.getColumnCount(); i++) {
        col= jTable4.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
        jLabel44 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
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
        jLabel27 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPasswordField1 = new javax.swing.JPasswordField();
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
        jButton13 = new javax.swing.JButton();
        jTextField12 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jSeparator7 = new javax.swing.JSeparator();
        jTextField16 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jComboBox6 = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jTextField18 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();

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
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel43)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28))
        );

        jPanel7.setBackground(new java.awt.Color(0, 188, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Administrateur");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(u1.getPrenom().charAt(0)+"."+u1.getNom());

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(136, 136, 136))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(96, 96, 96))))
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
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Gestion des Utilisateurs");

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_withe.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(34, 34, 34))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(0, 188, 255));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Gestion des filiéres");

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-repository-24.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(48, 48, 48))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(0, 188, 255));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Gestion des modules");

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-open-book-24.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(34, 34, 34))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(0, 188, 255));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Gestion des Groupes");

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/group.png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(34, 34, 34))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                .addGap(60, 60, 60)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(299, 299, 299))
        );

        kGradientPanel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 281, -1));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1250, 750));

        jPanel2.setBackground(new java.awt.Color(229, 229, 229));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 750));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel4.setkEndColor(new java.awt.Color(51, 0, 204));
        kGradientPanel4.setkGradientFocus(400);
        kGradientPanel4.setkStartColor(new java.awt.Color(0, 204, 204));
        kGradientPanel4.setPreferredSize(new java.awt.Dimension(350, 200));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-open-book-32.png"))); // NOI18N
        jLabel11.setText("Nombre des Modules");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("0");

        javax.swing.GroupLayout kGradientPanel4Layout = new javax.swing.GroupLayout(kGradientPanel4);
        kGradientPanel4.setLayout(kGradientPanel4Layout);
        kGradientPanel4Layout.setHorizontalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(209, 209, 209))
            .addGroup(kGradientPanel4Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel11)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        kGradientPanel4Layout.setVerticalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel4Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        jPanel2.add(kGradientPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 410, -1));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Acceuil ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, -1, -1));

        kGradientPanel3.setkEndColor(new java.awt.Color(51, 0, 204));
        kGradientPanel3.setkGradientFocus(400);
        kGradientPanel3.setkStartColor(new java.awt.Color(0, 204, 204));
        kGradientPanel3.setPreferredSize(new java.awt.Dimension(350, 200));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-repository-32.png"))); // NOI18N
        jLabel9.setText("Nombre des filiéres");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("0");

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jLabel14)
                .addContainerGap(198, Short.MAX_VALUE))
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        jPanel2.add(kGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 410, -1));

        kGradientPanel2.setkEndColor(new java.awt.Color(51, 0, 204));
        kGradientPanel2.setkGradientFocus(400);
        kGradientPanel2.setkStartColor(new java.awt.Color(0, 204, 204));
        kGradientPanel2.setPreferredSize(new java.awt.Dimension(350, 200));
        kGradientPanel2.setBorder(new EmptyBorder(10,10,10,10));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-user-32.png"))); // NOI18N
        jLabel10.setText("Nombre des utilisateurs");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("0");

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap(204, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(192, 192, 192))
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        jPanel2.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 410, -1));

        kGradientPanel5.setkEndColor(new java.awt.Color(51, 0, 204));
        kGradientPanel5.setkGradientFocus(400);
        kGradientPanel5.setkStartColor(new java.awt.Color(0, 204, 204));
        kGradientPanel5.setPreferredSize(new java.awt.Dimension(350, 200));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-user-groups-32.png"))); // NOI18N
        jLabel12.setText("Nombre des Groupes");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("0");

        javax.swing.GroupLayout kGradientPanel5Layout = new javax.swing.GroupLayout(kGradientPanel5);
        kGradientPanel5.setLayout(kGradientPanel5Layout);
        kGradientPanel5Layout.setHorizontalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel5Layout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(209, 209, 209))
            .addGroup(kGradientPanel5Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel5Layout.setVerticalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel5Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel2.add(kGradientPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 390, 410, 200));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 95, 1244, 10));

        jTabbedPane1.addTab("tab1", jPanel2);

        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel19.setBackground(new java.awt.Color(230, 230, 230));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setText("Information d'utilisateur :");

        jLabel20.setBackground(new java.awt.Color(230, 230, 230));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Matricule :");

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
        jLabel25.setText("Mot de passe :");

        jTextField7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel26.setBackground(new java.awt.Color(230, 230, 230));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Pseudo :");

        jTextField8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTextField9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel27.setBackground(new java.awt.Color(230, 230, 230));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Role :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Chef scolarite", "Enseignant" }));
        jComboBox1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jPasswordField1.setText("Mot de passe");
        jPasswordField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusLost(evt);
            }
        });

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
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Matricule", "Nom", "Prénom", "Téléphone", "Email", "Pseudo", "Mot de passe", "Adresse", "Role", "État"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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

        jTextField12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jTextField5))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(26, 26, 26)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(jTextField9)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(26, 26, 26)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(jTextField4)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField10)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField7)
                    .addComponent(jPasswordField1)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124)
                        .addComponent(jTextField12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox1, jComboBox2, jPasswordField1, jTextField3, jTextField4, jTextField5, jTextField7, jTextField8, jTextField9});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jComboBox2, jPasswordField1, jTextField10, jTextField3, jTextField4, jTextField5, jTextField7, jTextField8, jTextField9});

        jTabbedPane1.addTab("tab2", jPanel3);

        jPanel5.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel30.setBackground(new java.awt.Color(230, 230, 230));
        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setText("Information sur les filiéres :");

        jLabel31.setBackground(new java.awt.Color(230, 230, 230));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("Code du filiére :");

        jTextField6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel32.setBackground(new java.awt.Color(230, 230, 230));
        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("Libelle");

        jTextField11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton6.setBackground(new java.awt.Color(153, 153, 153));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add (1).png"))); // NOI18N
        jButton6.setText("Ajouter");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(153, 153, 153));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changes.png"))); // NOI18N
        jButton4.setText("Modifier");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "code", "Libelle"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        jTextField16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton14.setBackground(new java.awt.Color(153, 153, 153));
        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jButton14.setText("Rechercher");
        jButton14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(jTextField11))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)
                        .addComponent(jTextField16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
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
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(297, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(249, 249, 249)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(637, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab2", jPanel5);

        jPanel6.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel33.setBackground(new java.awt.Color(230, 230, 230));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setText("Information des modules :");

        jLabel35.setBackground(new java.awt.Color(230, 230, 230));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setText("Libelle :");

        jTextField13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel36.setBackground(new java.awt.Color(230, 230, 230));
        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setText("Masse Horaire :");

        jTextField14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel37.setBackground(new java.awt.Color(230, 230, 230));
        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setText("Filiére :");

        jLabel38.setBackground(new java.awt.Color(230, 230, 230));
        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setText("État :");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Actif", "Non actif" }));
        jComboBox3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jComboBox4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton7.setBackground(new java.awt.Color(153, 153, 153));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add (1).png"))); // NOI18N
        jButton7.setText("Ajouter");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(153, 153, 153));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changes.png"))); // NOI18N
        jButton8.setText("Modifier");
        jButton8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(153, 153, 153));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        jButton9.setText("Supprimer");
        jButton9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTable3.setAutoCreateRowSorter(true);
        jTable3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "code Module", "Libelle", "Masse Horaire", "Filiére", "État"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

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

        jLabel17.setText("");
        jLabel17.setVisible(false);

        jTextField17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton15.setBackground(new java.awt.Color(153, 153, 153));
        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jButton15.setText("Rechercher");
        jButton15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 97, Short.MAX_VALUE))
            .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(26, 26, 26)
                                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(182, 182, 182)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 126, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128)
                        .addComponent(jTextField17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)))
                .addContainerGap())
            .addComponent(jSeparator10)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(68, 68, 68)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(243, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel6);

        jPanel13.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel34.setBackground(new java.awt.Color(230, 230, 230));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setText("Information des Groupes :");

        jLabel39.setBackground(new java.awt.Color(230, 230, 230));
        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setText("Libelle :");

        jTextField15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel40.setBackground(new java.awt.Color(230, 230, 230));
        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setText("Année :");

        jLabel41.setBackground(new java.awt.Color(230, 230, 230));
        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setText("Filiére :");

        jLabel42.setBackground(new java.awt.Color(230, 230, 230));
        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setText("État :");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Actif", "Non actif" }));
        jComboBox5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jComboBox6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton10.setBackground(new java.awt.Color(153, 153, 153));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add (1).png"))); // NOI18N
        jButton10.setText("Ajouter");
        jButton10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(153, 153, 153));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changes.png"))); // NOI18N
        jButton11.setText("Modifier");
        jButton11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(153, 153, 153));
        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        jButton12.setText("Supprimer");
        jButton12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jTable4.setAutoCreateRowSorter(true);
        jTable4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "code  Groupe", "Libelle", "Année", "Filiére", "État"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

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

        jLabel18.setText("");
        jLabel18.setVisible(false);

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        jComboBox7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTextField18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton16.setBackground(new java.awt.Color(153, 153, 153));
        jButton16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jButton16.setText("Rechercher");
        jButton16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator13)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel13Layout.createSequentialGroup()
                                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel13Layout.createSequentialGroup()
                                                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(26, 26, 26)
                                                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGap(182, 182, 182)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 85, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129)
                        .addComponent(jTextField18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(68, 68, 68)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(242, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel13);

        kGradientPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, -37, 970, 920));

        getContentPane().add(kGradientPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void clearComponent()
    {
        jTextField3.setText("");
        jTextField3.setEditable(true);
        jTextField9.setText("");
        jTextField8.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField7.setText("");
        jPasswordField1.setText("");
        jComboBox1.setSelectedIndex(0);
        jTextField10.setText("");
        jComboBox2.setSelectedIndex(0);
        
    }
    public void clearComponent2()
    {
        jTextField6.setText("");
        jTextField6.setEditable(true);
        jTextField11.setText("");
        
        
    }
     public void clearComponent3()
    {
        
        jLabel17.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jComboBox3.setSelectedIndex(0);
        
        jComboBox4.setSelectedIndex(0);
        
    }
       public void clearComponent4()
    {
        jTextField15.setText("");
        jLabel18.setText("");
       jComboBox7.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        jComboBox6.setSelectedIndex(0);
        
    }
    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
       jTabbedPane1.setSelectedIndex(0);
       refresdashboard();
       setbackground(jPanel8);
        resetbackground(jPanel9);
        resetbackground(jPanel10);
        resetbackground(jPanel11);
        resetbackground(jPanel12);
       
    }//GEN-LAST:event_jPanel8MouseClicked

    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int index=jTable1.getSelectedRow();
        TableModel tm=jTable1.getModel();
        jTextField3.setText(tm.getValueAt(index, 0).toString());
        jTextField3.setEditable(false);
        jTextField9.setText(tm.getValueAt(index, 1).toString());
        jTextField8.setText(tm.getValueAt(index, 2).toString());
        jTextField4.setText(tm.getValueAt(index, 3).toString());
        jTextField5.setText(tm.getValueAt(index, 4).toString());
        jTextField7.setText(tm.getValueAt(index, 5).toString());
        jPasswordField1.setText(new String(tm.getValueAt(index, 6).toString()));
        jComboBox1.setSelectedItem(tm.getValueAt(index, 8).toString());
        jTextField10.setText(tm.getValueAt(index, 7).toString());
        jComboBox2.setSelectedItem(tm.getValueAt(index, 9).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        boolean delete=false;
        Utilisateur u2=new Utilisateur();
        u2.setMatricule(jTextField3.getText());
        u2.setNom(jTextField9.getText());
        u2.setPrenom(jTextField8.getText());
        u2.setEmail(jTextField5.getText());
        u2.setTelephone(jTextField4.getText());
        u2.setLogin(jTextField7.getText());
        u2.setPassword(new String(jPasswordField1.getPassword()));
        u2.setAdresse(jTextField10.getText());
        u2.setRole(jComboBox1.getSelectedItem().toString().toLowerCase());
        u2.setEtat((jComboBox2.getSelectedItem().toString()=="Actif") ? true : false);

        delete= u2.delete();

        if(delete)
        {   JOptionPane.showMessageDialog(null , "L'utilisateur à été supprimer");
            clearComponent();

        }
        refreshTable1();
           
                    refreshTable2();
                    refreshTable3();
                    refreshTable4();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        boolean update=false;
        if(!jTextField5.getText().contains("@"))
        {  JOptionPane.showMessageDialog ( null , " Email Non valide!" );}
        else
        {     Utilisateur u2=new Utilisateur();
            u2.setMatricule(jTextField3.getText());
            u2.setNom(jTextField9.getText());
            u2.setPrenom(jTextField8.getText());
            u2.setEmail(jTextField5.getText());
            u2.setTelephone(jTextField4.getText());
            u2.setLogin(jTextField7.getText());
            u2.setPassword(new String(jPasswordField1.getPassword()));
            u2.setAdresse(jTextField10.getText());
            u2.setRole(jComboBox1.getSelectedItem().toString().toLowerCase());
            u2.setEtat((jComboBox2.getSelectedItem().toString()=="Actif") ? true : false);

            update= u2.update();

            if(update)
            {   JOptionPane.showMessageDialog(null , "Les données sont bien Modifier");
                clearComponent();

            }
        }

        refreshTable1();
           
                    refreshTable2();
                    refreshTable3();
                    refreshTable4();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        boolean insert=false;
        if(!jTextField5.getText().contains("@"))
        {  JOptionPane.showMessageDialog ( null , " Email Non valide!" );}
        else
        {  Utilisateur u2=new Utilisateur();
            u2.setMatricule(jTextField3.getText());
            u2.setNom(jTextField9.getText());
            u2.setPrenom(jTextField8.getText());
            u2.setEmail(jTextField5.getText());
            u2.setTelephone(jTextField4.getText());
            u2.setLogin(jTextField7.getText());
            u2.setPassword(new String(jPasswordField1.getPassword()));
            u2.setAdresse(jTextField10.getText());
            u2.setRole(jComboBox1.getSelectedItem().toString().toLowerCase());
            u2.setEtat((jComboBox2.getSelectedItem().toString()=="Actif") ? true : false);

            insert= u2.create();

            if(insert)
            {   JOptionPane.showMessageDialog(null , "Utilisateur bien Inséré");
                   refreshTable1();
                    refreshTable2();
                    refreshTable3();
                    refreshTable4();
            }

            else
            JOptionPane.showMessageDialog(null , "Utilisateur Existe Déja" );
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPasswordField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusLost
        // TODO add your handling code here:
        String pass=new String(jPasswordField1.getPassword());
        if(pass.equals(""))
        {
            jPasswordField1.setText("Mot de passe");
            jPasswordField1.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jPasswordField1FocusLost

    private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained
        // TODO add your handling code here:
        String pass=new String(jPasswordField1.getPassword());
        if(pass.equals("Mot de passe"))
        {
            jPasswordField1.setText("");
            jPasswordField1.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_jPasswordField1FocusGained

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))) {
            evt.consume();// ignorer l'événement
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
        refresdashboard();
        setbackground(jPanel10);
        resetbackground(jPanel9);
        resetbackground(jPanel8);
        resetbackground(jPanel11);
        resetbackground(jPanel12);
        
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
        refresdashboard();
        
        setbackground(jPanel12);
        resetbackground(jPanel9);
        resetbackground(jPanel10);
        resetbackground(jPanel11);
        resetbackground(jPanel8);
        
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
        refresdashboard();
        setbackground(jPanel11);
        resetbackground(jPanel9);
        resetbackground(jPanel10);
        resetbackground(jPanel8);
        resetbackground(jPanel12);
      
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
          boolean update=false;
        
             Filiere u2=new Filiere();
            u2.setCodeFiliere(jTextField6.getText());
            u2.setNom(jTextField11.getText());
            u2.setAf(af);
            
            update= u2.update();

            if(update)
            {   JOptionPane.showMessageDialog(null , "Les données sont bien Modifier");
                clearComponent2();
            }
        

           refreshTable1();
                    refreshTable2();
                    refreshTable3();
                    refreshTable4();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
            boolean delete=false;
        Filiere u2=new Filiere();
        u2.setCodeFiliere(jTextField6.getText());
        u2.setNom(jTextField11.getText());
        u2.setAf(af);
      

        delete= u2.delete();

        if(delete)
        {   JOptionPane.showMessageDialog(null , "La filiére à été supprimer");
            clearComponent2();

        }
           refreshTable1();
                    refreshTable2();
                    refreshTable3();
                    refreshTable4();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
          int index=jTable2.getSelectedRow();
        TableModel tm=jTable2.getModel();
        jTextField6.setText(tm.getValueAt(index, 0).toString());
        jTextField6.setEditable(false);
        jTextField11.setText(tm.getValueAt(index, 1).toString());
      
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
         boolean insert=false;
        if(jTextField6.getText()=="")
        {  JOptionPane.showMessageDialog ( null , " code Non valide!" );}
        else
        {  Filiere u2=new Filiere();
            u2.setCodeFiliere(jTextField6.getText());
            u2.setNom(jTextField11.getText());
            u2.setAf(af);
            insert= u2.create();

            if(insert)
            {   JOptionPane.showMessageDialog(null , "La Filiére bien Inséré");
                   refreshTable1();
                    refreshTable2();
                    refreshTable3();
                    refreshTable4();
            }

            else
            JOptionPane.showMessageDialog(null , "Cette Filiére Existe Déja" );
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
         int index=jTable3.getSelectedRow();
        TableModel tm=jTable3.getModel();
        jLabel17.setText(tm.getValueAt(index, 0).toString());
        jTextField13.setText(tm.getValueAt(index, 1).toString());
        jTextField14.setText(tm.getValueAt(index, 2).toString());
        jComboBox4.setSelectedItem(tm.getValueAt(index, 3).toString());
        jComboBox3.setSelectedItem(tm.getValueAt(index, 4).toString());
        
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
   boolean delete=false;
        Module u2=new Module();
        u2.setIdModule(Integer.parseInt( jLabel17.getText()));
        
        
      

        delete= u2.delete();

        if(delete)
        {   JOptionPane.showMessageDialog(null , "Le module à été supprimer");
            clearComponent3();

        }
           refreshTable1();
                    refreshTable2();
                    refreshTable3();
                    refreshTable4();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        
         boolean update=false;
        
        
             Module u2=new Module();
            u2.setIdModule(Integer.parseInt( jLabel17.getText()));
            u2.setNom(jTextField13.getText());
            u2.setMh(Long.parseLong( jTextField14.getText()));
            u2.setEtat((jComboBox2.getSelectedItem().toString()=="Actif") ? true : false);
            u2.setFiliere(getcodefiliereSelected(jComboBox4.getSelectedItem().toString()));
            u2.setAf(af);
        

            update= u2.update();

            if(update)
            {   JOptionPane.showMessageDialog(null , "Les données sont bien Modifier");
                clearComponent3();

            }
        

           refreshTable1();
                    refreshTable2();
                    refreshTable3();
                    refreshTable4();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        boolean insert=false;
        if(jTextField13.getText()=="")
        {  JOptionPane.showMessageDialog ( null , " Nom Non valide!" );}
        else
        {  Module u2=new Module();
            u2.setIdModule(null);
            u2.setNom(jTextField13.getText());
            u2.setAf(af);
            u2.setEtat((jComboBox3.getSelectedItem().toString()=="Actif") ? true : false);
            u2.setMh(Long.parseLong(jTextField14.getText().toString()));

            u2.setFiliere(getcodefiliereSelected(jComboBox4.getSelectedItem().toString()));

            insert= u2.create();

            if(insert)
            {   JOptionPane.showMessageDialog(null , "Le module est bien Inséré");
                   refreshTable1();
                    refreshTable2();
                    refreshTable3();
                    refreshTable4();
            }

            else
            JOptionPane.showMessageDialog(null , "Ce Module Existe Déja" );

        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
         int index=jTable4.getSelectedRow();
        TableModel tm=jTable4.getModel();
        jLabel18.setText(tm.getValueAt(index, 0).toString());
        jTextField15.setText(tm.getValueAt(index, 1).toString());
        jComboBox7.setSelectedItem(tm.getValueAt(index, 2).toString());
        jComboBox6.setSelectedItem(tm.getValueAt(index, 3).toString());
        jComboBox5.setSelectedItem(tm.getValueAt(index, 4).toString());
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
         boolean delete=false;
        Groupe u2=new Groupe();
        u2.setIdGroupe(Integer.parseInt( jLabel18.getText()));
        
        
      

        delete= u2.delete();

        if(delete)
        {   JOptionPane.showMessageDialog(null , "Le Groupe à été supprimer");
            clearComponent4();

        }
           refreshTable1();
                    refreshTable2();
                    refreshTable3();
                    refreshTable4();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
          boolean update=false;
        
        
             Groupe u2=new Groupe();
            u2.setIdGroupe(Integer.parseInt( jLabel18.getText()));
                u2.setNom(jTextField15.getText());
            u2.setAnnee(Integer.parseInt( jComboBox7.getSelectedItem().toString() ));
            u2.setEtat((jComboBox2.getSelectedItem().toString()=="Actif") ? true : false);
            u2.setFiliere(getcodefiliereSelected(jComboBox6.getSelectedItem().toString()));
            u2.setAf(af);
        

            update= u2.update();

            if(update)
                
            {   
                u2.deleteaffectation();
                insererAffectation(u2);
                
                JOptionPane.showMessageDialog(null , "Les données sont bien Modifier");
                clearComponent4();

            }
        

           refreshTable1();
                    refreshTable2();
                    refreshTable3();
                    refreshTable4();
    }//GEN-LAST:event_jButton11ActionPerformed

    
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
         boolean insert=false;
        if(jTextField15.getText()=="")
        {  JOptionPane.showMessageDialog ( null , " Nom Non valide!" );}
        else
        {  Groupe u2=new Groupe();
            u2.setIdGroupe(null);
            u2.setNom(jTextField15.getText());
            u2.setAf(af);
            u2.setEtat((jComboBox5.getSelectedItem().toString()=="Actif") ? true : false);
            u2.setAnnee(Integer.parseInt(jComboBox7.getSelectedItem().toString()));

            u2.setFiliere(getcodefiliereSelected(jComboBox6.getSelectedItem().toString()));

            insert= u2.create();

            if(insert)
            {   
                insererAffectation(u2);
                JOptionPane.showMessageDialog(null , "Le Groupe est bien Inséré");
                   refreshTable1();
                    refreshTable2();
                    refreshTable3();
                    refreshTable4();
                    
            }

            else
            JOptionPane.showMessageDialog(null , "Ce Groupe Existe Déja veuillez vérifier les données" );
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jPanel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MousePressed

        jTabbedPane1.setSelectedIndex(1);
        refresdashboard();
        setbackground(jPanel9);
        resetbackground(jPanel8);
        resetbackground(jPanel10);
        resetbackground(jPanel11);
        resetbackground(jPanel12);

    }//GEN-LAST:event_jPanel9MousePressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        try{
            
jTable1.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"Matricule", "Nom", "Prénom", "Téléphone", "Email", "Pseudo", "Mot de passe", "Adresse", "Role", "État"}));
		DefaultTableModel model = (DefaultTableModel)  jTable1.getModel();
            
        
		Object[] row;
                        PreparedStatement preparedStatement = null;
                        ResultSet resultatpreparedStatement = null;
                       

			preparedStatement = connection_db.connecter().prepareStatement("SELECT * from Utilisateur where matricule like ? or nom like ? or prenom like ? or role like ?");
                        preparedStatement.setString(1, jTextField12.getText()+"%");
                        preparedStatement.setString(2, jTextField12.getText()+"%");
                        preparedStatement.setString(3, jTextField12.getText()+"%");
                        preparedStatement.setString(4, jTextField12.getText()+"%");
			resultatpreparedStatement = preparedStatement.executeQuery();

			while (resultatpreparedStatement.next()) {
                            Utilisateur next = new Utilisateur(resultatpreparedStatement.getString("Matricule"),
						resultatpreparedStatement.getString("nom"), 
                                                resultatpreparedStatement.getString("prenom"),                                     
						resultatpreparedStatement.getString("telephone"), 
						resultatpreparedStatement.getString("email"),
                                                resultatpreparedStatement.getString("login"),
                                                resultatpreparedStatement.getString("password"),
                                                resultatpreparedStatement.getString("role"),
                                                resultatpreparedStatement.getString("adresse"),
                                                resultatpreparedStatement.getBoolean("etat"));
                                
                         row = new Object[10];
			row[0] = next.getMatricule();
			row[1] = next.getNom();
			row[2] = next.getPrenom();
			row[3] = next.getTelephone();
			row[4] = next.getEmail();
                        
			// row[4] = employer.getDate_embauche();
			row[5] = next.getLogin();
                        row[6] = next.getPassword();
                        row[7] = next.getAdresse();
                        row[8] = next.getRole();
                        row[9] =  next.getEtat()?"Actif":"Non Actif";
				model.addRow(row);
			}

                     
TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable1.getColumnCount(); i++) {
        col= jTable1.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        try{
            jTable2.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"code","Libelle"}));
		DefaultTableModel model = (DefaultTableModel)  jTable2.getModel();
            
        
		Object[] row;
                        PreparedStatement preparedStatement = null;
                        ResultSet resultatpreparedStatement = null;
                       

			preparedStatement = connection_db.connecter().prepareStatement("SELECT * from Filiere where af=? and( code_filiere like ? or nom like ?)");
                        preparedStatement.setString(1, this.af.getAf());
                        preparedStatement.setString(2, jTextField16.getText()+"%");
                        preparedStatement.setString(3, jTextField16.getText()+"%");
			resultatpreparedStatement = preparedStatement.executeQuery();

			while (resultatpreparedStatement.next()) {
                            Filiere next = new Filiere(resultatpreparedStatement.getString("code_filiere"),
						resultatpreparedStatement.getString("nom"));
                                
                         row = new Object[3];
			row[0] = next.getCodeFiliere();
			row[1] = next.getNom();
                	model.addRow(row);
			}
                     
TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable2.getColumnCount(); i++) {
        col= jTable2.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        
        

        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
          try{
            jTable3.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"code Module", "Libelle", "Masse Horaire", "Filiére", "État"}));
		DefaultTableModel model = (DefaultTableModel)  jTable3.getModel();
            
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        
		Object[] row;
                        PreparedStatement preparedStatement = null;
                        ResultSet resultatpreparedStatement = null;
                       

			preparedStatement = connection_db.connecter().prepareStatement("SELECT * from module where af=? and nom like ? ");
                        preparedStatement.setString(1, this.af.getAf());
                        preparedStatement.setString(2, jTextField17.getText() +"%");
                        
			resultatpreparedStatement = preparedStatement.executeQuery();

			while (resultatpreparedStatement.next()) {
                            Module next = new Module(resultatpreparedStatement.getInt("id_module"),resultatpreparedStatement.getString("nom"),resultatpreparedStatement.getLong("mh"),resultatpreparedStatement.getBoolean("etat"));
                            Query query = em.createNamedQuery("Filiere.findByCodeFiliere");
                            query.setParameter("codeFiliere", resultatpreparedStatement.getString("filiere"));
                            next.setFiliere((Filiere)query.getResultList().get(0));
                            
                         row = new Object[5];
			row[0] = next.getIdModule();
			row[1] = next.getNom();
                        row[2] = next.getMh();
                        row[4] = (next.getEtat())?"Actif":"Non actif";
                        row[3] = next.getFiliere().getNom();
                	model.addRow(row);
			}
jTable3.getColumnModel().getColumn(0).setMinWidth(0);
jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
jTable3.getColumnModel().getColumn(0).setWidth(0);



TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable3.getColumnCount(); i++) {
        col= jTable3.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
  try{
            jTable4.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"code  Groupe", "Libelle", "Année", "Filiére", "État"}));
		DefaultTableModel model = (DefaultTableModel)  jTable4.getModel();
            
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        
		Object[] row;
                        PreparedStatement preparedStatement = null;
                        ResultSet resultatpreparedStatement = null;
                       

			preparedStatement = connection_db.connecter().prepareStatement("SELECT * from Groupe where af=? and nom like ? ");
                        preparedStatement.setString(1, this.af.getAf());
                        preparedStatement.setString(2, jTextField18.getText() +"%");
                        
			resultatpreparedStatement = preparedStatement.executeQuery();

			while (resultatpreparedStatement.next()) {
                            Groupe next = new Groupe(resultatpreparedStatement.getInt("id_groupe"),resultatpreparedStatement.getString("nom"),resultatpreparedStatement.getInt("annee"),resultatpreparedStatement.getBoolean("etat"));
                            Query query = em.createNamedQuery("Filiere.findByCodeFiliere");
                            query.setParameter("codeFiliere", resultatpreparedStatement.getString("filiere"));
                            next.setFiliere((Filiere)query.getResultList().get(0));
                            
                         row = new Object[5];
			row[0] = next.getIdGroupe();
			row[1] = next.getNom();
                        row[2] = next.getAnnee();
                        row[4] = (next.getEtat())?"Actif":"Non actif";
                        row[3] = next.getFiliere().getNom();
                	model.addRow(row);
			}
jTable4.getColumnModel().getColumn(0).setMinWidth(0);
jTable4.getColumnModel().getColumn(0).setMaxWidth(0);
jTable4.getColumnModel().getColumn(0).setWidth(0);
TableColumn col;
DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                  for (int i = 0; i < jTable4.getColumnCount(); i++) {
        col= jTable4.getColumnModel().getColumn(i);
        col.setCellRenderer(dtcr);              
                  }
        
        }
        catch(Exception e){
            e.printStackTrace();
        }        
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Gestion_Scolaire.Interface_Authantification t=new Gestion_Scolaire.Interface_Authantification();
        t.setVisible(true);
               
    }//GEN-LAST:event_jButton17ActionPerformed
    void insererAffectation(Groupe G)
    {
        ModuleEnseignantGroupe meg;
        for (Module mod : G.getFiliere().getModuleList()) {
            meg=new ModuleEnseignantGroupe();
            meg.setAf(af);
            meg.setEnseignant(null);
            meg.setGroupe(G);
            meg.setId(null);
            meg.setModule(mod);
            meg.create();
        }
    }
        Filiere getcodefiliereSelected(String nom)
        {
             EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
            EntityManager em=emf.createEntityManager(); 
            Query query=em.createNamedQuery("Filiere.findByNomAndAf").setParameter("nom", nom).setParameter("af", this.af);
           List<Filiere> fls=query.getResultList();
           
           return fls.get(0);
           
        }
        
        
     public void setbackground(JPanel p)
     {
         p.setBackground(new Color(0,150,255));
     }
     public void resetbackground(JPanel p)
     {
         p.setBackground(new Color(0,188,255));
     }
   
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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
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
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    // End of variables declaration//GEN-END:variables
}
