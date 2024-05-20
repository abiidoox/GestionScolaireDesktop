package beans;

import beans.Anneeformation;
import beans.ModuleEnseignantGroupe;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-04T21:23:27", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Seance.class)
public class Seance_ { 

    public static volatile SingularAttribute<Seance, ModuleEnseignantGroupe> idMeg;
    public static volatile SingularAttribute<Seance, String> jour;
    public static volatile SingularAttribute<Seance, Anneeformation> af;
    public static volatile SingularAttribute<Seance, Integer> id;
    public static volatile SingularAttribute<Seance, Date> hd;
    public static volatile SingularAttribute<Seance, Date> hf;

}