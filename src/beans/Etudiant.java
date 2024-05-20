/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author abiid
 */
@Entity
@Table(name = "etudiant")
@NamedQueries({
    @NamedQuery(name = "Etudiant.findAll", query = "SELECT e FROM Etudiant e"),
    @NamedQuery(name = "Etudiant.findById", query = "SELECT e FROM Etudiant e WHERE e.id = :id"),
    @NamedQuery(name = "Etudiant.findByMatricule", query = "SELECT e FROM Etudiant e WHERE e.matricule = :matricule"),
    @NamedQuery(name = "Etudiant.findByCin", query = "SELECT e FROM Etudiant e WHERE e.cin = :cin"),
    @NamedQuery(name = "Etudiant.findByDateN", query = "SELECT e FROM Etudiant e WHERE e.dateN = :dateN"),
    @NamedQuery(name = "Etudiant.findByLieuN", query = "SELECT e FROM Etudiant e WHERE e.lieuN = :lieuN"),
    @NamedQuery(name = "Etudiant.findByAdresse", query = "SELECT e FROM Etudiant e WHERE e.adresse = :adresse"),
    @NamedQuery(name = "Etudiant.findByTelephone", query = "SELECT e FROM Etudiant e WHERE e.telephone = :telephone"),
    @NamedQuery(name = "Etudiant.findByNom", query = "SELECT e FROM Etudiant e WHERE e.nom = :nom"),
    @NamedQuery(name = "Etudiant.findByPrenom", query = "SELECT e FROM Etudiant e WHERE e.prenom = :prenom"),
    @NamedQuery(name = "Etudiant.findByEmail", query = "SELECT e FROM Etudiant e WHERE e.email = :email"),
    @NamedQuery(name = "Etudiant.findByEtat", query = "SELECT e FROM Etudiant e WHERE e.etat = :etat")})
public class Etudiant implements Serializable {

    @JoinColumn(name = "groupe", referencedColumnName = "id_groupe")
    @ManyToOne(optional = true)
    private Groupe groupe;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "matricule")
    private String matricule;
    @Basic(optional = false)
    @Column(name = "cin")
    private String cin;
    @Basic(optional = false)
    @Column(name = "dateN")
    @Temporal(TemporalType.DATE)
    private Date dateN;
    @Basic(optional = false)
    @Column(name = "lieuN")
    private String lieuN;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "telephone")
    private String telephone;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "etat")
    private boolean etat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etudiant")
    private List<Notes> notesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etudiant")
    private List<Absence> absenceList;
    @JoinColumn(name = "AF", referencedColumnName = "AF")
    @ManyToOne(optional = false)
    private Anneeformation af;

    public Etudiant() {
    }

    public Etudiant(Integer id) {
        this.id = id;
    }

    public Etudiant(Integer id, String matricule, String cin, Date dateN, String lieuN, String adresse, String telephone, String nom, String prenom, String email, boolean etat) {
        this.id = id;
        this.matricule = matricule;
        this.cin = cin;
        this.dateN = dateN;
        this.lieuN = lieuN;
        this.adresse = adresse;
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.etat = etat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getDateN() {
        return dateN;
    }

    public void setDateN(Date dateN) {
        this.dateN = dateN;
    }

    public String getLieuN() {
        return lieuN;
    }

    public void setLieuN(String lieuN) {
        this.lieuN = lieuN;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public List<Notes> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<Notes> notesList) {
        this.notesList = notesList;
    }

    public List<Absence> getAbsenceList() {
        return absenceList;
    }

    public void setAbsenceList(List<Absence> absenceList) {
        this.absenceList = absenceList;
    }

    public Anneeformation getAf() {
        return af;
    }

    public void setAf(Anneeformation af) {
        this.af = af;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etudiant)) {
            return false;
        }
        Etudiant other = (Etudiant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Etudiant[ id=" + id + " ]";
    }
      public Boolean create() {
        boolean insered=false;
         
         
		try {
        Query query ;
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        query=em.createNamedQuery("Etudiant.findByMatricule").setParameter("matricule", this.getMatricule());
        
        
        if(query.getResultList().size()<=0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        java.sql.Date d = new java.sql.Date (this.getDateN().getTime());
            query         = em.createNativeQuery("INSERT INTO Etudiant(Matricule,CIN,nom,prenom,telephone,email,lieuN,dateN,adresse,etat,af,groupe) VALUES"
                    + "( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?,?)")
                    
                .setParameter(1, this.matricule)
                    .setParameter(2, this.cin)
                .setParameter(3, this.nom)
                .setParameter(4, this.prenom)
                .setParameter(5, this.telephone)
                .setParameter(6, this.email)
                .setParameter(7, this.lieuN)
                .setParameter(8, d)
                .setParameter(9, this.adresse)
                .setParameter(10, this.etat)
                .setParameter(11, this.af.getAf())
		.setParameter(12, this.groupe.getIdGroupe());
            
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
       query=em.createNamedQuery("Etudiant.findById").setParameter("id", this.getId());
        if(query.getResultList().size()>0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
         
            query         = em.createNativeQuery("UPDATE Etudiant set cin=?,nom=?,prenom=?,telephone=?,email=?,lieuN=?,adresse=?,etat=?,groupe=? where id=?")
                .setParameter(10, this.id)
                .setParameter(1, this.cin)
                .setParameter(2, this.nom)
                .setParameter(3, this.prenom)
                .setParameter(4, this.telephone)
                .setParameter(5, this.email)
                .setParameter(6, this.lieuN)
                .setParameter(7, this.adresse)
                .setParameter(8, this.etat)
                .setParameter(9, this.groupe.getIdGroupe());
            
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
        query=em.createNamedQuery("Etudiant.findById").setParameter("id", this.getId());
        if(query.getResultList().size()>0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("DELETE FROM Etudiant where id=?")
                .setParameter(1, this.id);
                
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

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
}
