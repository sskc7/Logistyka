package com.mycompany.logistyka;

import com.mycompany.logistyka.Driver;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-02T14:08:15")
@StaticMetamodel(Delegation.class)
public class Delegation_ { 

    public static volatile SingularAttribute<Delegation, Driver> driverId;
    public static volatile SingularAttribute<Delegation, Date> endDate;
    public static volatile SingularAttribute<Delegation, String> description;
    public static volatile SingularAttribute<Delegation, Integer> delegationId;
    public static volatile SingularAttribute<Delegation, Date> startDate;

}