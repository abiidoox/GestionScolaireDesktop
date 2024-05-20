/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 *
 * @author abiid
 */
@Entity
@Table(name = "utilisateur")
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
    @NamedQuery(name = "Utilisateur.findByMatricule", query = "SELECT u FROM Utilisateur u WHERE u.matricule = :matricule"),
    @NamedQuery(name = "Utilisateur.findByNom", query = "SELECT u FROM Utilisateur u WHERE u.nom = :nom"),
    @NamedQuery(name = "Utilisateur.findByPrenom", query = "SELECT u FROM Utilisateur u WHERE u.prenom = :prenom"),
    @NamedQuery(name = "Utilisateur.findByTelephone", query = "SELECT u FROM Utilisateur u WHERE u.telephone = :telephone"),
    @NamedQuery(name = "Utilisateur.findByEmail", query = "SELECT u FROM Utilisateur u WHERE u.email = :email"),
    @NamedQuery(name = "Utilisateur.findByLogin", query = "SELECT u FROM Utilisateur u WHERE u.login = :login"),
    @NamedQuery(name = "Utilisateur.findByPassword", query = "SELECT u FROM Utilisateur u WHERE u.password = :password"),
    @NamedQuery(name = "Utilisateur.findByRole", query = "SELECT u FROM Utilisateur u WHERE u.role = :role"),
    @NamedQuery(name = "Utilisateur.findByAdresse", query = "SELECT u FROM Utilisateur u WHERE u.adresse = :adresse"),
    @NamedQuery(name = "Utilisateur.findByEtat", query = "SELECT u FROM Utilisateur u WHERE u.etat = :etat"),
            @NamedQuery(name = "Utilisateur.findByLoginandPass", query = "SELECT u FROM Utilisateur u WHERE u.login = :login AND u.password=:password")
})

public class Utilisateur implements Serializable {

    @OneToMany(mappedBy = "enseignant")
    private List<ModuleEnseignantGroupe> moduleEnseignantGroupeList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Matricule")
    private String matricule;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "telephone")
    private String telephone;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "etat")
    private boolean etat;

    public Utilisateur() {
    }

    public Utilisateur(String matricule) {
        this.matricule = matricule;
    }

    public Utilisateur(String matricule, String nom, String prenom, String telephone, String email, String login, String password, String role, String adresse, boolean etat) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.adresse = adresse;
        this.etat = etat;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricule != null ? matricule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.matricule == null && other.matricule != null) || (this.matricule != null && !this.matricule.equals(other.matricule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Utilisateur[ matricule=" + matricule + " ]";
    }
    
    
    
        public Boolean create() {
        boolean insered=false;
         
         
		try {
        Query query ;
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        query=em.createNamedQuery("Utilisateur.findByMatricule").setParameter("matricule", this.getMatricule());
        
        
        if(query.getResultList().size()<=0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("INSERT INTO Utilisateur(Matricule,nom,prenom,telephone,email,login,password,role,adresse,etat) VALUES"
                    + "( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )")
                    
                .setParameter(1, this.matricule)
                .setParameter(2, this.nom)
                .setParameter(3, this.prenom)
                .setParameter(4, this.telephone)
                .setParameter(5, this.email)
                .setParameter(6, this.login)
                .setParameter(7, this.password)
                .setParameter(8, this.role)
                .setParameter(9, this.adresse)
                .setParameter(10, this.etat);
		
            
                query.executeUpdate();
                tx.commit();
                insered=true;
                
        }
        else
            insered= false;
        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
                      
		}
                finally{
                 return insered;
                }
	}
    
    
    public Boolean update() {
        boolean updated=false;
		try {
        Query query ;
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        query=em.createNamedQuery("Utilisateur.findByMatricule").setParameter("matricule", this.getMatricule());
        if(query.getResultList().size()>0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("UPDATE Utilisateur set nom=?,prenom=?,telephone=?,email=?,login=?,password=?,role=?,adresse=?,etat=? where Matricule=?")
                .setParameter(10, this.matricule)
                .setParameter(1, this.nom)
                .setParameter(2, this.prenom)
                .setParameter(3, this.telephone)
                .setParameter(4, this.email)
                .setParameter(5, this.login)
                .setParameter(6, this.password)
                .setParameter(7, this.role)
                .setParameter(8, this.adresse)
                .setParameter(9, this.etat);
                query.executeUpdate();
                tx.commit();
                updated=true;
        }
        else
            updated= false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
                finally{
                 return updated;
                }
	}
public Boolean delete() {
        boolean deleted=false;
		try {
        Query query ;
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        query=em.createNamedQuery("Utilisateur.findByMatricule").setParameter("matricule", this.getMatricule());
        if(query.getResultList().size()>0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("DELETE FROM Utilisateur where Matricule=?")
                .setParameter(1, this.matricule);
                
                query.executeUpdate();
                tx.commit();
                deleted=true;
        }
        else
            deleted= false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
                finally{
                 return deleted;
                }
	}

    public List<ModuleEnseignantGroupe> getModuleEnseignantGroupeList() {
        return moduleEnseignantGroupeList;
    }

    public void setModuleEnseignantGroupeList(List<ModuleEnseignantGroupe> moduleEnseignantGroupeList) {
        this.moduleEnseignantGroupeList = moduleEnseignantGroupeList;
    }
    
}
