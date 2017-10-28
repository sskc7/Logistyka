package com.mycompany.logistyka;

import com.mycompany.logistyka.Delegation;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-02T14:08:15")
@StaticMetamodel(Driver.class)
public class Driver_ { 

    public static volatile SingularAttribute<Driver, Integer> driverId;
    public static volatile CollectionAttribute<Driver, Delegation> delegationCollection;
    public static volatile SingularAttribute<Driver, String> surname;
    public static volatile SingularAttribute<Driver, String> name;

}