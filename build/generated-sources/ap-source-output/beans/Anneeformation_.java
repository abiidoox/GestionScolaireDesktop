package beans;

import beans.Etudiant;
import beans.Filiere;
import beans.Groupe;
import beans.Module;
import beans.ModuleEnseignantGroupe;
import beans.Seance;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-04T21:23:27", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Anneeformation.class)
public class Anneeformation_ { 

    public static volatile ListAttribute<Anneeformation, Module> moduleList;
    public static volatile SingularAttribute<Anneeformation, String> af;
    public static volatile ListAttribute<Anneeformation, Groupe> groupeList;
    public static volatile ListAttribute<Anneeformation, Filiere> filiereList;
    public static volatile ListAttribute<Anneeformation, Etudiant> etudiantList;
    public static volatile ListAttribute<Anneeformation, ModuleEnseignantGroupe> moduleEnseignantGroupeList;
    public static volatile ListAttribute<Anneeformation, Seance> seanceList;

}