package se.nrm.dina.datamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import se.nrm.dina.datamodel.Agent;
import se.nrm.dina.datamodel.Attachment;

@Generated(value="EclipseLink-2.7.0.v20160208-rNA", date="2016-02-10T12:13:26")
@StaticMetamodel(Attachmentmetadata.class)
public class Attachmentmetadata_ extends BaseEntity_ {

    public static volatile SingularAttribute<Attachmentmetadata, Integer> attachmentMetadataID;
    public static volatile SingularAttribute<Attachmentmetadata, String> name;
    public static volatile SingularAttribute<Attachmentmetadata, Agent> modifiedByAgentID;
    public static volatile SingularAttribute<Attachmentmetadata, Agent> createdByAgentID;
    public static volatile SingularAttribute<Attachmentmetadata, Attachment> attachmentID;
    public static volatile SingularAttribute<Attachmentmetadata, String> value;

}