package beans;

import beans.Etudiant;
import beans.Seance;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-04T21:23:27", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Absence.class)
public class Absence_ { 

    public static volatile SingularAttribute<Absence, Integer> id;
    public static volatile SingularAttribute<Absence, Seance> seance;
    public static volatile SingularAttribute<Absence, Etudiant> etudiant;

}