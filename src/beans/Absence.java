/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;
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

/**
 *
 * @author abiid
 */
@Entity
@Table(name = "absence")
@NamedQueries({
    @NamedQuery(name = "Absence.findAll", query = "SELECT a FROM Absence a"),
    @NamedQuery(name = "Absence.findById", query = "SELECT a FROM Absence a WHERE a.id = :id")})
public class Absence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "etudiant", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Etudiant etudiant;
    @JoinColumn(name = "seance", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Seance seance;

    public Absence() {
    }

    public Absence(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
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
        if (!(object instanceof Absence)) {
            return false;
        }
        Absence other = (Absence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Absence[ id=" + id + " ]";
    }
    
    
    
    
       public Boolean create() {
        boolean insered=false;

		try {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        EntityTransaction tx = em.getTransaction();
        tx.begin();
          Query  query         = em.createNativeQuery("INSERT INTO Absence(seance,etudiant) VALUES"
                    + "( ? , ? )")
                    
                .setParameter(1, this.seance.getId())
                .setParameter(2, this.etudiant.getId());
                query.executeUpdate();
                tx.commit();
                insered=true;
                
        }
      
		 catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
                      
		}
                finally{
                 return insered;
                }
}
    
}
