/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "seance")
@NamedQueries({
    @NamedQuery(name = "Seance.findAll", query = "SELECT s FROM Seance s"),
    @NamedQuery(name = "Seance.findById", query = "SELECT s FROM Seance s WHERE s.id = :id"),
    @NamedQuery(name = "Seance.findByJour", query = "SELECT s FROM Seance s WHERE s.jour = :jour"),
    @NamedQuery(name = "Seance.findByHd", query = "SELECT s FROM Seance s WHERE s.hd = :hd"),
    @NamedQuery(name = "Seance.findByHf", query = "SELECT s FROM Seance s WHERE s.hf = :hf")})
public class Seance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "jour")
    private String jour;
    @Basic(optional = false)
    @Column(name = "hd")
    @Temporal(TemporalType.TIME)
    private Date hd;
    @Basic(optional = false)
    @Column(name = "hf")
    @Temporal(TemporalType.TIME)
    private Date hf;
    @JoinColumn(name = "af", referencedColumnName = "AF")
    @ManyToOne(optional = false)
    private Anneeformation af;
    @JoinColumn(name = "id_meg", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ModuleEnseignantGroupe idMeg;

    public Seance() {
    }

    public Seance(Integer id) {
        this.id = id;
    }

    public Seance(Integer id, String jour, Date hd, Date hf) {
        this.id = id;
        this.jour = jour;
        this.hd = hd;
        this.hf = hf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public Date getHd() {
        return hd;
    }

    public void setHd(Date hd) {
        this.hd = hd;
    }

    public Date getHf() {
        return hf;
    }

    public void setHf(Date hf) {
        this.hf = hf;
    }

    public Anneeformation getAf() {
        return af;
    }

    public void setAf(Anneeformation af) {
        this.af = af;
    }

    public ModuleEnseignantGroupe getIdMeg() {
        return idMeg;
    }

    public void setIdMeg(ModuleEnseignantGroupe idMeg) {
        this.idMeg = idMeg;
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
        if (!(object instanceof Seance)) {
            return false;
        }
        Seance other = (Seance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Seance[ id=" + id + " ]";
    }
    
    public Boolean create() {
        boolean insered=false;
         
       
         
		try {
        Query query ;
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
            
        
        if(this.existeSeance()==null)
        {  
                query         = em.createNativeQuery("INSERT into seance(id_meg,jour,hd,hf,af) VALUES( ? , ? , ?,?,?)");
                query.setParameter(1, this.getIdMeg().getId());
                query.setParameter(2, this.getJour());
                query.setParameter(3, this.getHd());
                query.setParameter(4, this.getHf());
                query.setParameter(5, this.af.getAf());
                query.executeUpdate();
//                query.executeUpdate();
                tx.commit();
                insered=true;
        }
     
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
                      
		}
                finally{
                 return insered;
                }
                
	}
    
    
    public Seance existeSeance()
    {
         PreparedStatement preparedStatement = null;
                        ResultSet resultatpreparedStatement = null;
        Seance s=null;

        try{
      	preparedStatement = connection_db.connecter().prepareStatement("SELECT s.* from Seance s inner join Module_Enseignant_Groupe meg on meg.id=s.id_meg where s.jour=? and s.hd=? and s.hf=? and s.af=? and ( meg.groupe=? or meg.enseignant=? )  ");
            preparedStatement.setString(1, this.getJour());
             java.sql.Date d = new java.sql.Date (this.getHd().getTime());
             java.sql.Date d1 = new java.sql.Date (this.getHf().getTime());
             
             java.sql.Time t=new java.sql.Time(d.getTime());
             java.sql.Time t1=new java.sql.Time(d1.getTime());
             
               preparedStatement.setTime(2,  t);
               preparedStatement.setTime(3, t1);
                preparedStatement.setString(4, this.getAf().getAf());
                preparedStatement.setInt(5, this.getIdMeg().getGroupe().getIdGroupe());
                preparedStatement.setString(6, this.getIdMeg().getEnseignant().getMatricule());
               resultatpreparedStatement = preparedStatement.executeQuery();
          if(resultatpreparedStatement.next())
            {
                         Query query ;
                    EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
                    EntityManager em=emf.createEntityManager(); 
         
                s=new Seance(resultatpreparedStatement.getInt("id"),resultatpreparedStatement.getString("jour"),resultatpreparedStatement.getDate("hd"),resultatpreparedStatement.getDate("hf"));
                s.setAf(this.af);
                query=em.createNamedQuery("ModuleEnseignantGroupe.findById").setParameter("id", resultatpreparedStatement.getInt("id_meg"));
                ModuleEnseignantGroupe meg=(ModuleEnseignantGroupe)query.getResultList().get(0);
                s.setIdMeg(meg);
            }
    }
        
    catch(Exception ex)
    {
        ex.printStackTrace();
    }

     return s;
}
}