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
@Table(name = "notes")
@NamedQueries({
    @NamedQuery(name = "Notes.findAll", query = "SELECT n FROM Notes n"),
    @NamedQuery(name = "Notes.findByIdNote", query = "SELECT n FROM Notes n WHERE n.idNote = :idNote"),
    @NamedQuery(name = "Notes.findByNote1", query = "SELECT n FROM Notes n WHERE n.note1 = :note1"),
    @NamedQuery(name = "Notes.findByNote2", query = "SELECT n FROM Notes n WHERE n.note2 = :note2"),
    @NamedQuery(name = "Notes.findByNoteF", query = "SELECT n FROM Notes n WHERE n.noteF = :noteF")})
public class Notes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_note")
    private Integer idNote;
    @Basic(optional = false)
    @Column(name = "note_1")
    private long note1;
    @Basic(optional = false)
    @Column(name = "note_2")
    private long note2;
    @Basic(optional = false)
    @Column(name = "note_f")
    private long noteF;
    @JoinColumn(name = "etudiant", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Etudiant etudiant;
    @JoinColumn(name = "meg", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ModuleEnseignantGroupe meg;

    public Notes() {
    }

    public Notes(Integer idNote) {
        this.idNote = idNote;
    }

    public Notes(Integer idNote, long note1, long note2, long noteF) {
        this.idNote = idNote;
        this.note1 = note1;
        this.note2 = note2;
        this.noteF = noteF;
    }

    public Integer getIdNote() {
        return idNote;
    }

    public void setIdNote(Integer idNote) {
        this.idNote = idNote;
    }

    public long getNote1() {
        return note1;
    }

    public void setNote1(long note1) {
        this.note1 = note1;
    }

    public long getNote2() {
        return note2;
    }

    public void setNote2(long note2) {
        this.note2 = note2;
    }

    public long getNoteF() {
        return noteF;
    }

    public void setNoteF(long noteF) {
        this.noteF = noteF;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public ModuleEnseignantGroupe getMeg() {
        return meg;
    }

    public void setMeg(ModuleEnseignantGroupe meg) {
        this.meg = meg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNote != null ? idNote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notes)) {
            return false;
        }
        Notes other = (Notes) object;
        if ((this.idNote == null && other.idNote != null) || (this.idNote != null && !this.idNote.equals(other.idNote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Notes[ idNote=" + idNote + " ]";
    }
    
    
      public Boolean update() {
        boolean updated=false;
		try {
        Query query ;
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
      
      
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            query         = em.createNativeQuery("UPDATE Notes set note_1=?,note_2=?,note_f=? where etudiant=? and meg=?")
                .setParameter(1, this.note1)
                .setParameter(2, this.note2)
                .setParameter(3, this.noteF)
                    .setParameter(4, this.etudiant.getId())
                    .setParameter(5, this.meg.getId());
                query.executeUpdate();
                tx.commit();
                updated=true;
        }
		 catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
                finally{
                 return updated;
                }
	}

        public Boolean create() {
        boolean insered=false;
         
         
		try {
        Query query ;
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("Gestion_Scolaire1PU");  
         EntityManager em=emf.createEntityManager(); 
        
        EntityTransaction tx = em.getTransaction();
        
        
               tx.begin();
            query         = em.createNativeQuery("INSERT INTO Notes(note_1,note_2,note_f,etudiant,meg) VALUES"
                    + "( ? , ? , ?,?,?)");
                query.setParameter(1, this.note1)
                .setParameter(2, this.note2)
                .setParameter(3, this.noteF)
                 .setParameter(4, this.etudiant.getId())
                    .setParameter(5, this.meg.getId());
                        

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
      
}
