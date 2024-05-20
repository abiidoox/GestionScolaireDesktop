package beans;

import beans.Anneeformation;
import beans.Filiere;
import beans.ModuleEnseignantGroupe;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-04T21:23:27", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Module.class)
public class Module_ { 

    public static volatile SingularAttribute<Module, Filiere> filiere;
    public static volatile SingularAttribute<Module, Anneeformation> af;
    public static volatile SingularAttribute<Module, Long> mh;
    public static volatile SingularAttribute<Module, Integer> idModule;
    public static volatile ListAttribute<Module, ModuleEnseignantGroupe> moduleEnseignantGroupeList;
    public static volatile SingularAttribute<Module, String> nom;
    public static volatile SingularAttribute<Module, Boolean> etat;

}