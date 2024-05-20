package beans;

import beans.Anneeformation;
import beans.Groupe;
import beans.Module;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-04T21:23:27", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Filiere.class)
public class Filiere_ { 

    public static volatile ListAttribute<Filiere, Module> moduleList;
    public static volatile SingularAttribute<Filiere, String> codeFiliere;
    public static volatile SingularAttribute<Filiere, Anneeformation> af;
    public static volatile ListAttribute<Filiere, Groupe> groupeList;
    public static volatile SingularAttribute<Filiere, String> nom;

}