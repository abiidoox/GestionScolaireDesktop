package beans;

import beans.Anneeformation;
import beans.Groupe;
import beans.Module;
import beans.Notes;
import beans.Seance;
import beans.Utilisateur;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-04T21:23:27", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ModuleEnseignantGroupe.class)
public class ModuleEnseignantGroupe_ { 

    public static volatile ListAttribute<ModuleEnseignantGroupe, Notes> notesList;
    public static volatile SingularAttribute<ModuleEnseignantGroupe, Anneeformation> af;
    public static volatile SingularAttribute<ModuleEnseignantGroupe, Module> module;
    public static volatile SingularAttribute<ModuleEnseignantGroupe, Integer> id;
    public static volatile SingularAttribute<ModuleEnseignantGroupe, Utilisateur> enseignant;
    public static volatile SingularAttribute<ModuleEnseignantGroupe, Groupe> groupe;
    public static volatile ListAttribute<ModuleEnseignantGroupe, Seance> seanceList;

}