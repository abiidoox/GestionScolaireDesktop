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
@Table(name = "module_enseignant_groupe")
@NamedQueries({
    @NamedQuery(name = "ModuleEnseignantGroupe.findAll", query = "SELECT m FROM ModuleEnseignantGroupe m"),
    @NamedQuery(name = "ModuleEnseignantGroupe.findById", query = "SELECT m FROM ModuleEnseignantGroupe m WHERE m.id = :id"),    
    @NamedQuery(name = "ModuleEnseignantGroupe.findByGroupe", query = "SELECT m FROM ModuleEnseignantGroupe m WHERE m.groupe = :groupe")})

public class ModuleEnseignantGroupe implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meg")
    private List<Notes> notesList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMeg")
    private List<Seance> seanceList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "af", referencedColumnName = "AF")
    @ManyToOne(optional = false)
    private Anneeformation af;
    @JoinColumn(name = "enseignant", referencedColumnName = "Matricule")
    @ManyToOne
    private Utilisateur enseignant;
    @JoinColumn(name = "groupe", referencedColumnName = "id_groupe")
    @ManyToOne(optional = false)
    private Groupe groupe;
    @JoinColumn(name = "module", referencedColumnName = "id_module")
    @ManyToOne(optional = false)
    private Module module;

    public ModuleEnseignantGroupe() {
    }

    public ModuleEnseignantGroupe(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Anneeformation getAf() {
        return af;
    }

    public void setAf(Anneeformation af) {
        this.af = af;
    }

    public Utilisateur getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Utilisateur enseignant) {
        this.enseignant = enseignant;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
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
        if (!(object instanceof ModuleEnseignantGroupe)) {
            return false;
        }
        ModuleEnseignantGroupe other = (ModuleEnseignantGroupe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ModuleEnseignantGroupe[ id=" + id + " ]";
    }
    
     public Boolean create() {
        boolean insered=false;
         
       
         
		try {
        Query query ;
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        
        
         
       
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        query=em.createNativeQuery("select mge.id_groupe from groupe mge order by mge.id_groupe desc");
        int id;
        if(this.getGroupe().getIdGroupe()==null)
            id=Integer.parseInt(query.getResultList().get(0).toString());
        else
            id=this.getGroupe().getIdGroupe();
            query         = em.createNativeQuery("INSERT INTO module_enseignant_groupe(module,groupe,enseignant,af) VALUES"
                    + "( ? , ? , ?,?)").setParameter(1, this.getModule().getIdModule())
                .setParameter(2, id)
                .setParameter(3, null)
                .setParameter(4, this.af.getAf());
               
		
            
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
      
      
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("UPDATE module_enseignant_groupe  set enseignant=? where id=?")
                .setParameter(1, this.enseignant.getMatricule())
                .setParameter(2, this.getId());
                query.executeUpdate();
                tx.commit();
                updated=true;
      
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
                finally{
                 return updated;
                }
	}    

    public List<Seance> getSeanceList() {
        return seanceList;
    }

    public void setSeanceList(List<Seance> seanceList) {
        this.seanceList = seanceList;
    }

    public List<Notes> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<Notes> notesList) {
        this.notesList = notesList;
    }
    
}
