package se.nrm.dina.datamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.0.v20160125-rNA", date="2016-01-26T15:11:50")
@StaticMetamodel(Borrowattachment.class)
public class Borrowattachment_ extends BaseEntity_ {

    public static volatile SingularAttribute<Borrowattachment, Integer> modifiedByAgentID;
    public static volatile SingularAttribute<Borrowattachment, Integer> createdByAgentID;
    public static volatile SingularAttribute<Borrowattachment, Integer> borrowID;
    public static volatile SingularAttribute<Borrowattachment, Integer> attachmentID;
    public static volatile SingularAttribute<Borrowattachment, Integer> borrowAttachmentID;
    public static volatile SingularAttribute<Borrowattachment, String> remarks;
    public static volatile SingularAttribute<Borrowattachment, Integer> ordinal;

}