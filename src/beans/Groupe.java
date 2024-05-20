/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;
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

/**
 *
 * @author abiid
 */
@Entity
@Table(name = "groupe")
@NamedQueries({
    @NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g"),
    @NamedQuery(name = "Groupe.findByIdGroupe", query = "SELECT g FROM Groupe g WHERE g.idGroupe = :idGroupe"),
    @NamedQuery(name = "Groupe.findByNom", query = "SELECT g FROM Groupe g WHERE g.nom = :nom"),
    @NamedQuery(name = "Groupe.findByAnnee", query = "SELECT g FROM Groupe g WHERE g.annee = :annee"),
    @NamedQuery(name = "Groupe.findByEtat", query = "SELECT g FROM Groupe g WHERE g.etat = :etat"),

@NamedQuery(name = "Groupe.findByNomandaf", query = "SELECT g FROM Groupe g WHERE g.nom = :nom and g.af=:af")})

public class Groupe implements Serializable {

    @OneToMany(mappedBy = "groupe")
    private List<Etudiant> etudiantList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupe")
    private List<ModuleEnseignantGroupe> moduleEnseignantGroupeList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_groupe")
    private Integer idGroupe;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "annee")
    private int annee;
    @Basic(optional = false)
    @Column(name = "etat")
    private boolean etat;
    @JoinColumn(name = "af", referencedColumnName = "AF")
    @ManyToOne(optional = false)
    private Anneeformation af;
    @JoinColumn(name = "filiere", referencedColumnName = "code_filiere")
    @ManyToOne(optional = false)
    private Filiere filiere;

    public Groupe() {
    }

    public Groupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

    public Groupe(Integer idGroupe, String nom, int annee, boolean etat) {
        this.idGroupe = idGroupe;
        this.nom = nom;
        this.annee = annee;
        this.etat = etat;
    }

    public Integer getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Anneeformation getAf() {
        return af;
    }

    public void setAf(Anneeformation af) {
        this.af = af;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroupe != null ? idGroupe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if ((this.idGroupe == null && other.idGroupe != null) || (this.idGroupe != null && !this.idGroupe.equals(other.idGroupe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Groupe[ idGroupe=" + idGroupe + " ]";
    }
    
    public Boolean create() {
        boolean insered=false;
         
         
		try {
        Query query ;
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        
        EntityTransaction tx = em.getTransaction();
        
        query =em.createNamedQuery("Groupe.findByNomandaf").setParameter("nom", this.getNom()).setParameter("af", this.af);
       if(query.getResultList().size()==0)
       {        tx.begin();
            query         = em.createNativeQuery("INSERT INTO Groupe(nom,annee,af,etat,filiere) VALUES"
                    + "( ? , ? , ?,?,?)");
                query.setParameter(1, this.nom)
                .setParameter(2, this.annee)
                .setParameter(3, this.af.getAf())
                 .setParameter(4, this.etat)
                    .setParameter(5, this.filiere.getCodeFiliere());
                        

                query.executeUpdate();
                tx.commit();
                insered=true;
       }else 
           insered=false;
           
        
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
      query=em.createNamedQuery("Groupe.findByIdGroupe").setParameter("idGroupe", this.getIdGroupe());
        if(query.getResultList().size()>0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("UPDATE Groupe set nom=?,af=?,annee=?,etat=?,filiere=? where id_groupe=?")
                .setParameter(6, this.idGroupe)
                .setParameter(1, this.nom)
                .setParameter(2, this.af.getAf())
                .setParameter(3, this.getAnnee())
                    .setParameter(4, this.getEtat())
                    .setParameter(5, this.getFiliere().getCodeFiliere())
                    ;
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
                query=em.createNamedQuery("Groupe.findByIdGroupe").setParameter("idGroupe", this.getIdGroupe());

        if(query.getResultList().size()>0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("DELETE FROM Groupe where id_groupe=?")
                .setParameter(1, this.idGroupe);
                
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
public void deleteaffectation()
{
		try {
        Query query ;
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
                query=em.createNamedQuery("Groupe.findByIdGroupe").setParameter("idGroupe", this.getIdGroupe());

        if(query.getResultList().size()>0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("DELETE FROM module_enseignant_groupe where groupe=?")
                .setParameter(1, this.idGroupe);
                
                query.executeUpdate();
                tx.commit();             
        }   
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
               
    
}

    public List<ModuleEnseignantGroupe> getModuleEnseignantGroupeList() {
        return moduleEnseignantGroupeList;
    }

    public void setModuleEnseignantGroupeList(List<ModuleEnseignantGroupe> moduleEnseignantGroupeList) {
        this.moduleEnseignantGroupeList = moduleEnseignantGroupeList;
    }

    public List<Etudiant> getEtudiantList() {
        return etudiantList;
    }

    public void setEtudiantList(List<Etudiant> etudiantList) {
        this.etudiantList = etudiantList;
    }
    
}
