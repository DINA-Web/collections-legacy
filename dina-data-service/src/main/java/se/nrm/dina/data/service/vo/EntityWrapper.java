/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service.vo;
 
import java.io.Serializable; 
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement; 
import org.codehaus.jackson.map.annotate.JsonSerialize; 

/**
 *
 * @author idali
 */
@XmlRootElement 
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class EntityWrapper implements Serializable  {
    
        
    @XmlElement
    private final MetadataBean meta;
    
    @XmlElement
    private final Object data;
      
    
    public EntityWrapper(MetadataBean meta, Object data) {
        this.data = data;
        this.meta = meta;
    } 

    public MetadataBean getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }
}
