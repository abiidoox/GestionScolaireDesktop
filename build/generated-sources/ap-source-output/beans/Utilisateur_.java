package beans;

import beans.ModuleEnseignantGroupe;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-04T21:23:27", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Utilisateur.class)
public class Utilisateur_ { 

    public static volatile SingularAttribute<Utilisateur, String> password;
    public static volatile SingularAttribute<Utilisateur, String> role;
    public static volatile SingularAttribute<Utilisateur, String> matricule;
    public static volatile SingularAttribute<Utilisateur, String> adresse;
    public static volatile SingularAttribute<Utilisateur, String> telephone;
    public static volatile SingularAttribute<Utilisateur, String> login;
    public static volatile ListAttribute<Utilisateur, ModuleEnseignantGroupe> moduleEnseignantGroupeList;
    public static volatile SingularAttribute<Utilisateur, String> nom;
    public static volatile SingularAttribute<Utilisateur, String> prenom;
    public static volatile SingularAttribute<Utilisateur, Boolean> etat;
    public static volatile SingularAttribute<Utilisateur, String> email;

}