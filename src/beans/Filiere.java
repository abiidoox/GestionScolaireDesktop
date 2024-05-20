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
@Table(name = "filiere")
@NamedQueries({
    @NamedQuery(name = "Filiere.findAll", query = "SELECT f FROM Filiere f"),
    @NamedQuery(name = "Filiere.findByCodeFiliere", query = "SELECT f FROM Filiere f WHERE f.codeFiliere = :codeFiliere"),
    @NamedQuery(name = "Filiere.findByNom", query = "SELECT f FROM Filiere f WHERE f.nom = :nom"),
     @NamedQuery(name = "Filiere.findbyaf", query = "SELECT f FROM Filiere f WHERE f.af = :af" ),
     @NamedQuery(name = "Filiere.findByNomAndAf", query = "SELECT f FROM Filiere f WHERE f.nom = :nom and f.af = :af" )
})
public class Filiere implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filiere")
    private List<Module> moduleList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filiere")
    private List<Groupe> groupeList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "code_filiere")
    private String codeFiliere;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @JoinColumn(name = "af", referencedColumnName = "AF")
    @ManyToOne(optional = false)
    private Anneeformation af;

    public Filiere() {
    }

    public Filiere(String codeFiliere) {
        this.codeFiliere = codeFiliere;
    }

    public Filiere(String codeFiliere, String nom) {
        this.codeFiliere = codeFiliere;
        this.nom = nom;
    }

    public String getCodeFiliere() {
        return codeFiliere;
    }

    public void setCodeFiliere(String codeFiliere) {
        this.codeFiliere = codeFiliere;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        hash += (codeFiliere != null ? codeFiliere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filiere)) {
            return false;
        }
        Filiere other = (Filiere) object;
        if ((this.codeFiliere == null && other.codeFiliere != null) || (this.codeFiliere != null && !this.codeFiliere.equals(other.codeFiliere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Filiere[ codeFiliere=" + codeFiliere + " ]";
    }
        public Boolean create() {
        boolean insered=false;
         
         
		try {
        Query query ;
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        query=em.createNamedQuery("Filiere.findByCodeFiliere").setParameter("codeFiliere", this.getCodeFiliere());
        
        
        if(query.getResultList().size()<=0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("INSERT INTO Filiere(code_filiere,nom,af) VALUES"
                    + "( ? , ? , ?)")
                    
                .setParameter(1, this.codeFiliere)
                .setParameter(2, this.nom)
                .setParameter(3, this.af.getAf());
               
		
            
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
      query=em.createNamedQuery("Filiere.findByCodeFiliere").setParameter("codeFiliere", this.getCodeFiliere());
        if(query.getResultList().size()>0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("UPDATE Filiere set nom=?,af=? where code_filiere=?")
                .setParameter(3, this.codeFiliere)
                .setParameter(1, this.nom)
                .setParameter(2, this.af.getAf());
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
                query=em.createNamedQuery("Filiere.findByCodeFiliere").setParameter("codeFiliere", this.getCodeFiliere());

        if(query.getResultList().size()>0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("DELETE FROM Filiere where code_filiere=?")
                .setParameter(1, this.codeFiliere);
                
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

    public List<Groupe> getGroupeList() {
        return groupeList;
    }

    public void setGroupeList(List<Groupe> groupeList) {
        this.groupeList = groupeList;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }
}
