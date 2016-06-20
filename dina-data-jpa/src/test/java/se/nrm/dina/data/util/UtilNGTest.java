/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.util;
 
import java.util.HashMap;
import java.util.Map; 
import org.junit.Test;
import org.mockito.Mock;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass; 
import org.testng.annotations.BeforeClass;  
import se.nrm.dina.data.exceptions.DinaException;
import se.nrm.dina.data.exceptions.ErrorMsg;
import se.nrm.dina.datamodel.Accession;
import se.nrm.dina.datamodel.Collectionobject;
import se.nrm.dina.datamodel.Division;
import se.nrm.dina.datamodel.EntityBean;

/**
 *
 * @author idali
 */ 
public class UtilNGTest {
    
    private Util testInstance;
    
    public UtilNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
 
    /**
     * Test of getInstance method, of class Util.
     */
    @Test
    public void testGetInstance() {
        System.out.println("testGetInstance");
         
        Util result = Util.getInstance();
        assertNotNull(result);   
    }

    /**
     * Test of convertClassNameToClass method, of class Util.
     */
    @Test
    public void testConvertClassNameToClass() {
        
        System.out.println("testConvertClassNameToClass");
        
        String classname = "Accession";
        testInstance = new Util();
        Class expResult = Accession.class;
        Class result = testInstance.convertClassNameToClass(classname);
        assertEquals(result, expResult); 
        assertEquals(result.getSimpleName(), classname);
    }

    /**
     * Test of convertClassNameToClass method, of class Util.
     */
    @Test  
    public void testConvertClassNameToClassException() {

        System.out.println("testConvertClassNameToClassException");

        testInstance = new Util();
        String classname = "Accessions"; 
        try {
            testInstance.convertClassNameToClass(classname); 
        } catch(DinaException e) {
            assertEquals(e.getMessage(), ErrorMsg.getInstance().getEntityNameErrorMsg());
        } 
    }
    
    /**
     * Test of reformClassName method, of class Util.
     */
    @Test
    public void testReformClassName() {
        System.out.println("testReformClassName");
        
        String s = "collectionObject";
        
        testInstance = new Util();
        
        String expResult = "Collectionobject";
        String result = testInstance.reformClassName(s);
        assertEquals(result, expResult); 
    }

    /**
     * Test of reformClassName method, of class Util.
     */
    @Test
    public void testReformClassNameEmptyName() {
        System.out.println("testReformClassNameEmptyName");

        String s = "";

        testInstance = new Util();
 
        String result = testInstance.reformClassName(s);
        assertEquals(0, result.length());
    }

    /**
     * Test of createNewInstance method, of class Util.
     */
    @Test
    public void testCreateNewInstance() {
        System.out.println("testCreateNewInstance");
         
        testInstance = new Util(); 
        Accession result = testInstance.createNewInstance(Accession.class);
        assertNotNull(result);
        assertEquals(result.getClass().getSimpleName(), "Accession");
    }

    /**
     * Test of createNewInstance method, of class Util.
     */
    @Test(expected = DinaException.class)
    public void testFailedCreateNewInstance() {
        System.out.println("testCreateNewInstance");
 
        testInstance = new Util();
        testInstance.createNewInstance(Mock.class); 
    }

    /**
     * Test of validateEntityName method, of class Util.
     */
    @Test
    public void testValidateEntityName() {
        System.out.println("testValidateEntityName");
        
        String entityName = "accession";
        
        testInstance = new Util();
        String expResult = "Accession";
        String result = testInstance.validateEntityName(entityName);
        assertEquals(result, expResult);
    }

    /**
     * Test of validateEntityName method, of class Util.
     */
    @Test(expected = DinaException.class)
    public void testInvalidateEntityName() {
        System.out.println("testInvalidateEntityName");

        String entityName = "accessions";

        testInstance = new Util(); 
        testInstance.validateEntityName(entityName);
    }


    /**
     * Test of isFieldsValid method, of class Util.
     */
    @Test
    public void testIsFieldsValid() {
        System.out.println("testIsFieldsValid");
        
        Class clazz = Accession.class;
        
        Map<String, String> map = new HashMap();
        map.put("accessionCondition", null);
        map.put("accessionNumber", null);
        map.put("dateAccessioned", null);
        map.put("dateReceived", null);
         
        testInstance = new Util();
        boolean expResult = true;
        boolean result = testInstance.isFieldsValid(clazz, map);
        assertEquals(result, expResult);
    }
    
        /**
     * Test of isFieldsValid method, of class Util.
     */
    @Test
    public void testIsFieldsValidFailed() {
        System.out.println("testIsFieldsValid");
        
        Class clazz = Accession.class;
        
        Map<String, String> map = new HashMap();
        map.put("accessionCondition", null);
        map.put("accessionNumbers", null);
        map.put("dateAccessioned", null);
        map.put("dateReceived", null);
         
        testInstance = new Util(); 
        try {
            testInstance.isFieldsValid(clazz, map);
            fail("Expected a DinaException to be thrown");
        } catch(DinaException e) {
            assertEquals(e.getMessage(), "accessionNumbers");
        } 
    }

    /**
     * Test of isFieldsValid method, of class Util.
     */
    @Test 
    public void testIsFieldsValidFailedExpected() {
        System.out.println("testIsFieldsValid");

        Class clazz = Accession.class;

        Map<String, String> map = new HashMap();
        map.put("accessionConditions", null);
        map.put("accessionNumbers", null);
        map.put("dateAccessioned", null);
        map.put("dateReceived", null);
         
        testInstance = new Util();   
         
        try {
            testInstance.isFieldsValid(clazz, map);
            fail();
        } catch(DinaException e) {
            assertTrue(true);
        }
    }

    /**
     * Test of isIntField method, of class Util.
     */
    @Test
    public void testIsIntField() {
        System.out.println("isIntField");
        
        Class clazz = Collectionobject.class;
        String fieldName = "collectionMemberID";
        testInstance = new Util();
         
        boolean result = testInstance.isIntField(clazz, fieldName);
        assertTrue(result);
    }

    /**
     * Test of isIntField method, of class Util.
     */
    @Test
    public void testIsIntFieldFalse() {
        System.out.println("isIntField");
        
        Class clazz = Accession.class;
        String fieldName = "accessionNumber";
        testInstance = new Util();
         
        boolean result = testInstance.isIntField(clazz, fieldName);
        assertFalse(result);
    }
    
    /**
     * Test of isIntField method, of class Util.
     */
    @Test  
    public void testIsIntegerFieldException() {
        System.out.println("isIntField");

        Class clazz = Accession.class;
        String fieldName = "accessionIDs";
        testInstance = new Util(); 
        
        try {
            testInstance.isIntField(clazz, fieldName);
            fail();
        } catch(DinaException e) {
            assertTrue(true);
        }
        
    }

    /**
     * Test of isIntField method, of class Util.
     */
    @Test
    public void testIsIntegerField() {
        System.out.println("isIntField");

        Class clazz = Accession.class;
        String fieldName = "accessionID";
        testInstance = new Util();

        boolean result = testInstance.isIntField(clazz, fieldName);
        assertTrue(result);
    }

    /**
     * Test of isEntity method, of class Util.
     */
    @Test
    public void testIsEntity() {
        System.out.println("isEntity");
    
        Class clazz = Accession.class;
        String fieldName = "divisionID";
        testInstance = new Util(); 
        boolean result = testInstance.isEntity(clazz, fieldName);
        assertTrue(result);
    }
    
    
    /**
     * Test of isEntity method, of class Util.
     */
    @Test
    public void testIsEntityFalse() {
        System.out.println("isEntity");

        Class clazz = Accession.class;
        String fieldName = "accessionNumber";
        testInstance = new Util();
        boolean result = testInstance.isEntity(clazz, fieldName);
        assertFalse(result);
    }
    
    /**
     * Test of isEntity method, of class Util.
     */
    @Test
    public void testIsEntityFalseException() {
        System.out.println("isEntity");

        Class clazz = Accession.class;
        String fieldName = "divisionid";
        testInstance = new Util();
        
        try {
            testInstance.isEntity(clazz, fieldName); 
        } catch(DinaException e) {
            assertTrue(true);
        }
        
    }

    /**
     * Test of isCollection method, of class Util.
     */
    @Test
    public void testIsCollection() {
        System.out.println("isCollection");
        
        Class clazz = Accession.class;
        String fieldName = "deaccessionList";
        testInstance = new Util(); 
        
        boolean result = testInstance.isCollection(clazz, fieldName);
        assertTrue(result);
    }
    
    /**
     * Test of isCollection method, of class Util.
     */
    @Test
    public void testIsCollectionFalse() {
        System.out.println("isCollection");

        Class clazz = Accession.class;
        String fieldName = "deaccessionlist";
        testInstance = new Util();

        try {
           testInstance.isCollection(clazz, fieldName); 
        } catch(DinaException e) {
            assertTrue(true);
        }  
    }

    /**
     * Test of getEntity method, of class Util.
     */
    @Test
    public void testGetEntity() {
        System.out.println("getEntity");
        
        Class clazz = Accession.class;
        String fieldName = "divisionID";
        
        testInstance = new Util(); 
        EntityBean result = testInstance.getEntity(clazz, fieldName);
        assertTrue(result instanceof Division); 
    }
    
    /**
     * Test of getEntity method, of class Util.
     */
    @Test
    public void testGetEntityFailure() {
        System.out.println("getEntity");
        
        Class clazz = Accession.class;
        String fieldName = "divisionid";
        
        testInstance = new Util(); 
        
        try {
            testInstance.getEntity(clazz, fieldName);
        } catch(DinaException e) {
            assertTrue(true); 
        }  
    }

    
    /**
     * Test of validateFields method, of class Util.
     */
    @Test
    public void testValidateFields() {
        System.out.println("validateFields");
        
        Class clazz = Accession.class;
        String fieldName = "accessionNumber";
        
        testInstance = new Util(); 
        boolean result = testInstance.validateFields(clazz, fieldName);
        assertTrue(result);
    }

    /**
     * Test of getIDFieldName method, of class Util.
     */
    @Test
    public void testGetIDFieldName_EntityBean() {
        System.out.println("getIDFieldName");
        
        EntityBean bean = new Accession();
        String expResult = "accessionID";
        
        testInstance = new Util(); 
        String result = testInstance.getIDFieldName(bean);
        assertEquals(result, expResult); 
    }
 
    /**
     * Test of isNumric method, of class Util.
     */
    @Test
    public void testIsNumric() {
        System.out.println("isNumric");
        
        String s = "20";
        testInstance = new Util(); 
        boolean result = testInstance.isNumric(s);
        assertTrue(result);
         
        s = "ttt";
        result = testInstance.isNumric(s);
        assertFalse(result);
    }

    /**
     * Test of maxLimit method, of class Util.
     */
    @Test
    public void testMaxLimit() {
        System.out.println("maxLimit");
        
        int limit = 5;
        testInstance = new Util();
         
        int result = testInstance.maxLimit(limit);
        assertEquals(result, limit); 
        
        limit = 300; 
        result = testInstance.maxLimit(limit);
        assertEquals(result, 200); 
        
        limit = 0;
        result = testInstance.maxLimit(limit);
        assertEquals(result, 50); 
        
    }
    
}
