package beans;

import beans.Absence;
import beans.Anneeformation;
import beans.Groupe;
import beans.Notes;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-04T21:23:27", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Etudiant.class)
public class Etudiant_ { 

    public static volatile SingularAttribute<Etudiant, String> lieuN;
    public static volatile SingularAttribute<Etudiant, String> matricule;
    public static volatile SingularAttribute<Etudiant, Anneeformation> af;
    public static volatile SingularAttribute<Etudiant, String> cin;
    public static volatile SingularAttribute<Etudiant, String> telephone;
    public static volatile SingularAttribute<Etudiant, String> nom;
    public static volatile SingularAttribute<Etudiant, Boolean> etat;
    public static volatile SingularAttribute<Etudiant, Date> dateN;
    public static volatile ListAttribute<Etudiant, Notes> notesList;
    public static volatile ListAttribute<Etudiant, Absence> absenceList;
    public static volatile SingularAttribute<Etudiant, String> adresse;
    public static volatile SingularAttribute<Etudiant, Groupe> groupe;
    public static volatile SingularAttribute<Etudiant, Integer> id;
    public static volatile SingularAttribute<Etudiant, String> prenom;
    public static volatile SingularAttribute<Etudiant, String> email;

}