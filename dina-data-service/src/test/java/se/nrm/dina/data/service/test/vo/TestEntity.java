/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service.test.vo;
 
import javax.xml.bind.annotation.XmlRootElement;
import se.nrm.dina.datamodel.BaseEntity;

/**
 *
 * @author idali
 */
@XmlRootElement(name="testEntity")
public class TestEntity extends BaseEntity {
    
    private String testField;

    public String getTestField() {
        return testField;
    }

    public void setTestField(String testField) {
        this.testField = testField;
    }
    
    

    @Override
    public String getIdentityString() {
        return "0";
    }

    @Override
    public int getEntityId() {
        return 0;
    }  

    @Override
    public String getUUID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
