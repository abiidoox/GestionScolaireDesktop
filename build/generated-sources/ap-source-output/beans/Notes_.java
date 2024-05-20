package beans;

import beans.Etudiant;
import beans.ModuleEnseignantGroupe;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-04T21:23:27", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Notes.class)
public class Notes_ { 

    public static volatile SingularAttribute<Notes, Long> note2;
    public static volatile SingularAttribute<Notes, Long> noteF;
    public static volatile SingularAttribute<Notes, Long> note1;
    public static volatile SingularAttribute<Notes, Integer> idNote;
    public static volatile SingularAttribute<Notes, Etudiant> etudiant;
    public static volatile SingularAttribute<Notes, ModuleEnseignantGroupe> meg;

}