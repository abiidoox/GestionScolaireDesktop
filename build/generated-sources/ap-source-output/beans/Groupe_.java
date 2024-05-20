package beans;

import beans.Anneeformation;
import beans.Etudiant;
import beans.Filiere;
import beans.ModuleEnseignantGroupe;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-04T21:23:27", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Groupe.class)
public class Groupe_ { 

    public static volatile SingularAttribute<Groupe, Filiere> filiere;
    public static volatile SingularAttribute<Groupe, Anneeformation> af;
    public static volatile SingularAttribute<Groupe, Integer> annee;
    public static volatile SingularAttribute<Groupe, Integer> idGroupe;
    public static volatile ListAttribute<Groupe, Etudiant> etudiantList;
    public static volatile ListAttribute<Groupe, ModuleEnseignantGroupe> moduleEnseignantGroupeList;
    public static volatile SingularAttribute<Groupe, String> nom;
    public static volatile SingularAttribute<Groupe, Boolean> etat;

}