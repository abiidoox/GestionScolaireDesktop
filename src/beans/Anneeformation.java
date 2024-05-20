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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author abiid
 */
@Entity
@Table(name = "anneeformation")
@NamedQueries({
    @NamedQuery(name = "Anneeformation.findAll", query = "SELECT a FROM Anneeformation a"),
    @NamedQuery(name = "Anneeformation.findByAf", query = "SELECT a FROM Anneeformation a WHERE a.af = :af")})
public class Anneeformation implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "af")
    private List<Etudiant> etudiantList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "af")
    private List<Seance> seanceList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "af")
    private List<ModuleEnseignantGroupe> moduleEnseignantGroupeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "af")
    private List<Module> moduleList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "af")
    private List<Groupe> groupeList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AF")
    private String af;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "af")
    private List<Filiere> filiereList;

    public Anneeformation() {
    }

    public Anneeformation(String af) {
        this.af = af;
    }

    public String getAf() {
        return af;
    }

    public void setAf(String af) {
        this.af = af;
    }

    public List<Filiere> getFiliereList() {
        return filiereList;
    }

    public void setFiliereList(List<Filiere> filiereList) {
        this.filiereList = filiereList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (af != null ? af.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anneeformation)) {
            return false;
        }
        Anneeformation other = (Anneeformation) object;
        if ((this.af == null && other.af != null) || (this.af != null && !this.af.equals(other.af))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Anneeformation[ af=" + af + " ]";
    }

    public List<Groupe> getGroupeList() {
        return groupeList;
    }

    public void setGroupeList(List<Groupe> groupeList) {
        this.groupeList = groupeList;
    }

    public List<ModuleEnseignantGroupe> getModuleEnseignantGroupeList() {
        return moduleEnseignantGroupeList;
    }

    public void setModuleEnseignantGroupeList(List<ModuleEnseignantGroupe> moduleEnseignantGroupeList) {
        this.moduleEnseignantGroupeList = moduleEnseignantGroupeList;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public List<Seance> getSeanceList() {
        return seanceList;
    }

    public void setSeanceList(List<Seance> seanceList) {
        this.seanceList = seanceList;
    }

    public List<Etudiant> getEtudiantList() {
        return etudiantList;
    }

    public void setEtudiantList(List<Etudiant> etudiantList) {
        this.etudiantList = etudiantList;
    }
    
}
