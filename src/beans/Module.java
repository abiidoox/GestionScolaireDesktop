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
@Table(name = "module")
@NamedQueries({
    @NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module m"),
    @NamedQuery(name = "Module.findByIdModule", query = "SELECT m FROM Module m WHERE m.idModule = :idModule"),
    @NamedQuery(name = "Module.findByNom", query = "SELECT m FROM Module m WHERE m.nom = :nom"),
    @NamedQuery(name = "Module.findByMh", query = "SELECT m FROM Module m WHERE m.mh = :mh"),
    @NamedQuery(name = "Module.findByEtat", query = "SELECT m FROM Module m WHERE m.etat = :etat")
    
})
public class Module implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private List<ModuleEnseignantGroupe> moduleEnseignantGroupeList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_module")
    private Integer idModule;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "mh")
    private long mh;
    @Basic(optional = false)
    @Column(name = "etat")
    private boolean etat;
    @JoinColumn(name = "af", referencedColumnName = "AF")
    @ManyToOne(optional = false)
    private Anneeformation af;
    @JoinColumn(name = "filiere", referencedColumnName = "code_filiere")
    @ManyToOne(optional = false)
    private Filiere filiere;

    public Module() {
    }

    public Module(Integer idModule) {
        this.idModule = idModule;
    }

    public Module(Integer idModule, String nom, long mh, boolean etat) {
        this.idModule = idModule;
        this.nom = nom;
        this.mh = mh;
        this.etat = etat;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getMh() {
        return mh;
    }

    public void setMh(long mh) {
        this.mh = mh;
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
        hash += (idModule != null ? idModule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Module)) {
            return false;
        }
        Module other = (Module) object;
        if ((this.idModule == null && other.idModule != null) || (this.idModule != null && !this.idModule.equals(other.idModule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Module[ idModule=" + idModule + " ]";
    }
       public Boolean create() {
        boolean insered=false;
         
         
		try {
        Query query ;
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("INSERT INTO Module(nom,mh,af,etat,filiere) VALUES"
                    + "( ? , ? , ?,?,?)");
                query.setParameter(1, this.nom)
                .setParameter(2, this.mh)
                .setParameter(3, this.af.getAf())
                 .setParameter(4, this.etat)
                    .setParameter(5, this.filiere.getCodeFiliere());
                        

                query.executeUpdate();
                tx.commit();
                insered=true;
        
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
      query=em.createNamedQuery("Module.findByIdModule").setParameter("idModule", this.getIdModule());
        if(query.getResultList().size()>0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("UPDATE Module set nom=?,af=?,mh=?,etat=?,filiere=? where id_module=?")
                .setParameter(6, this.idModule)
                .setParameter(1, this.nom)
                .setParameter(2, this.af.getAf())
                .setParameter(3, this.getMh())
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
                query=em.createNamedQuery("Module.findByIdModule").setParameter("idModule", this.getIdModule());

        if(query.getResultList().size()>0)
        {   query.getParameters().clear();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("DELETE FROM Module where id_Module=?")
                .setParameter(1, this.idModule);
                
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
