/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.util;
   
import java.lang.reflect.Field; 
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;   
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import se.nrm.dina.data.exceptions.DinaException;
import se.nrm.dina.datamodel.EntityBean;
import se.nrm.dina.datamodel.impl.Testentity;
import se.nrm.dina.datamodel.util.DataModelHelper;
  

/**
 *
 * @author idali
 */  
public class JpaReflectionHelperNGTest {
     
    
    private static JpaReflectionHelper instance;
    
    public JpaReflectionHelperNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception { 
        instance = JpaReflectionHelper.getInstance();
    }

    @AfterClass
    public static void tearDownClass() throws Exception { 
    }
 
    /**
     * Test of getInstance method, of class JpaReflectionHelper.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        
        instance = null;
        instance = JpaReflectionHelper.getInstance();

        assertNotNull(instance);
    }

    @Test
    public void testGetInstance1() {
        System.out.println("getInstance");

        instance = new JpaReflectionHelper();
        instance = JpaReflectionHelper.getInstance();

        assertNotNull(instance);
    }

    /**
     * Test of convertClassNameToClass method, of class JpaReflectionHelper.
     */
    @Test
    public void testConvertClassNameToClass() {
        System.out.println("convertClassNameToClass");
        
        String classname = "Testentity"; 
        Class expResult = Testentity.class;
        Class result = instance.convertClassNameToClass(classname);
        assertEquals(result, expResult);
        assertEquals(result.getSimpleName(), classname);
    }
 
    /**
     * Test of reformClassName method, of class JpaReflectionHelper.
     */
    @Test
    public void testReformClassName() {
        
        System.out.println("reformClassName");
          
        String entityName = "testEntity"; 
        String expResult = "Testentity";
        String result = instance.reformClassName(entityName);
        assertEquals(result, expResult); 
    }

    /**
     * Test of createNewInstance method, of class JpaReflectionHelper.
     */
    @Test
    public void testCreateNewInstance() {
        System.out.println("createNewInstance");
        
        Class clazz = Testentity.class; 
      
        Testentity result = instance.createNewInstance(clazz);
        assertNotNull(result);
        assertSame(clazz, result.getClass());
    }

    /**
     * Test of validateEntityName method, of class JpaReflectionHelper.
     */
    @Test
    public void testValidateEntityName() {
        System.out.println("validateEntityName");
        
        String entityName = "testEntity"; 
     
        String result = instance.validateEntityName(entityName);
        assertEquals(result, Testentity.class.getSimpleName()); 
    }

    @Test(expected = Throwable.class)
    public void testValidateEntityNameFailure() {
        System.out.println("validateEntityName");

        String entityName = "testEntity"; 
        when(instance.validateEntityName(entityName)).thenThrow(new DinaException()); 
        String result = instance.validateEntityName(entityName);
        assertNull(result);
    }

    
    /**
     * Test of isIntField method, of class JpaReflectionHelper.
     */
    @Test
    public void testIsIntField() {
        
        System.out.println("isIntField");
        
        Class clazz = Testentity.class;
        String fieldName = "version"; 
        boolean result = instance.isIntField(clazz, fieldName);
        assertTrue(result);
    }
    
    @Test
    public void testIsIntField2() {
        
        System.out.println("isIntField");
        
        Class clazz = Testentity.class;
        String fieldName = "bgDecimal"; 
        boolean result = instance.isIntField(clazz, fieldName);
        assertFalse(result);
    }
    
    @Test(expected = DinaException.class)
    public void testIsIntFieldFailure() {
        
        System.out.println("isIntField");
        
        Class clazz = Testentity.class;
        String fieldName = "testField"; 
        boolean result = instance.isIntField(clazz, fieldName);
        assertNull(result);
    }

    /**
     * Test of isIntField method, of class JpaReflectionHelper.
     */
    @Test
    public void testIsShortFieldTrue() {

        System.out.println("isShortField");

        Class clazz = Testentity.class;
        String fieldName = "s"; 
        boolean result = instance.isShortField(clazz, fieldName);
        assertTrue(result);
    }
    
    @Test
    public void testIsShortFieldFalse() {

        System.out.println("isShortField");

        Class clazz = Testentity.class;
        String fieldName = "version"; 
        boolean result = instance.isShortField(clazz, fieldName);
        assertFalse(result);
    }
    
    @Test(expected = DinaException.class)
    public void testIsShortFieldFailure() {

        System.out.println("isShortField");

        Class clazz = Testentity.class;
        String fieldName = "testField"; 
        boolean result = instance.isShortField(clazz, fieldName);
        assertNull(result);
    }
    
    
    /**
     * Test of isEntity method, of class JpaReflectionHelper.
     */
    @Test
    public void testIsEntity() {
        System.out.println("isEntity");
        
        Class clazz = Testentity.class;
        String fieldName = DataModelHelper.getInstance().getCREATED_BY_FIELD(); 
        boolean result = instance.isEntity(clazz, fieldName);
        assertTrue(result);
    }
    
    @Test
    public void testIsEntityFalse() {
        System.out.println("isEntity");
        
        Class clazz = Testentity.class;
        String fieldName = "version"; 
        boolean result = instance.isEntity(clazz, fieldName);
        assertFalse(result);
    }
    
    @Test(expected = DinaException.class)
    public void testIsEntityFailure() {
        System.out.println("isEntity");
        
        String fieldName = "testField";
        Class clazz = Testentity.class;  
 
        boolean result = instance.isEntity(clazz, fieldName);
        assertNull(result);
    }
 
    /**
     * Test of isCollection method, of class JpaReflectionHelper.
     */
    @Test
    public void testIsCollection() {
        System.out.println("isCollection");
        
        Class clazz = Testentity.class;
        String fieldName = "testList"; 
        boolean result = instance.isCollection(clazz, fieldName);
        assertTrue(result);
    }
    
    @Test
    public void testIsCollectionFalse() {
        System.out.println("isCollection");
        
        Class clazz = Testentity.class;
        String fieldName = "version"; 
        boolean result = instance.isCollection(clazz, fieldName);
        assertFalse(result);
    }
    
    @Test(expected = DinaException.class)
    public void testIsCollectionFailure() {
        System.out.println("isCollection");
        
        Class clazz = Testentity.class;
        String fieldName = "testField"; 
        boolean result = instance.isCollection(clazz, fieldName);
        assertNull(result);
    }
    
    
    /**
     * Test of isDate method, of class JpaReflectionHelper.
     */
    @Test
    public void testIsDate() {
        System.out.println("isDate");
        
        Class clazz = Testentity.class;
        String fieldName = DataModelHelper.getInstance().getTIME_CREATED_FIELD();
        instance = new JpaReflectionHelper();
      
        boolean result = instance.isDate(clazz, fieldName);
        assertTrue(result);
    }

    /**
     * Test of isDate method, of class JpaReflectionHelper.
     */
    @Test
    public void testIsDateFalse() {
        System.out.println("isDate");

        Class clazz = Testentity.class;
        String fieldName = DataModelHelper.getInstance().getCREATED_BY_FIELD();
        instance = new JpaReflectionHelper();
      
        boolean result = instance.isDate(clazz, fieldName);
        assertFalse(result);
    }
    
    @Test(expected = DinaException.class)
    public void testIsDateFailure() {
        System.out.println("isDate");

        Class clazz = Testentity.class;
        String fieldName = "testField";
        instance = new JpaReflectionHelper();
      
        boolean result = instance.isDate(clazz, fieldName);
        assertNull(result);
    }
    
    
    
    
    /**
     * Test of isBigDecimal method, of class JpaReflectionHelper.
     */
    @Test
    public void testIsBigDecimal() {
        System.out.println("isBigDecimal");
        
        Class clazz = Testentity.class;
        String fieldName = "bgDecimal";
        instance = new JpaReflectionHelper(); 
        
        boolean result = instance.isBigDecimal(clazz, fieldName);
        assertTrue(result);
    }
    
        
    /**
     * Test of isBigDecimal method, of class JpaReflectionHelper.
     */
    @Test
    public void testIsBigDecimalFalse() {
        System.out.println("isBigDecimal");
        
        Class clazz = Testentity.class;
        String fieldName = "version";
        instance = new JpaReflectionHelper(); 
        
        boolean result = instance.isBigDecimal(clazz, fieldName);
        assertFalse(result);
    }
    
        
    /**
     * Test of isBigDecimal method, of class JpaReflectionHelper.
     */
    @Test(expected = DinaException.class)
    public void testIsBigDecimalFailure() {
        System.out.println("isBigDecimal");
        
        Class clazz = Testentity.class;
        String fieldName = "testField";
        instance = new JpaReflectionHelper(); 
        
        boolean result = instance.isBigDecimal(clazz, fieldName);
        assertNull(result);
    }
 
    /**
     * Test of getEntity method, of class JpaReflectionHelper.
     */
    @Test
    public void testGetEntity() {
        System.out.println("getEntity");
        Class clazz = Testentity.class;
        String fieldName = DataModelHelper.getInstance().getCREATED_BY_FIELD();
        instance = new JpaReflectionHelper();
 
        EntityBean result = instance.getEntity(clazz, fieldName);
        assertNotNull(result); 
    }

    @Test(expected = DinaException.class)
    public void testGetEntityFailure() {
        System.out.println("getEntity");
        Class clazz = Testentity.class;
        String fieldName = "testField";
        instance = new JpaReflectionHelper();
 
        EntityBean result = instance.getEntity(clazz, fieldName);
        assertNull(result);
    }
    
    
    /**
     * Test of getValueType method, of class JpaReflectionHelper.
     */
    @Test
    public void testGetValueTypeInt() {
        
        System.out.println("getValueType");
        
        Class clazz = Testentity.class;
        String fieldName = "version"; 
        
        ValueType expResult = ValueType.INT;
        ValueType result = instance.getValueType(clazz, fieldName);
        assertEquals(result, expResult); 
    }
    
    @Test
    public void testGetValueTypeEntity() {
        
        System.out.println("getValueType");
        
        Class clazz = Testentity.class;
        String fieldName = DataModelHelper.getInstance().getCREATED_BY_FIELD();
        
        ValueType expResult = ValueType.ENTITY;
        ValueType result = instance.getValueType(clazz, fieldName);
        assertEquals(result, expResult); 
    }
    
    @Test
    public void testGetValueTypeBigDecimal() {
        
        System.out.println("getValueType");
        
        Class clazz = Testentity.class;
        String fieldName = "bgDecimal";
        
        ValueType expResult = ValueType.BIGDECIMAL;
        ValueType result = instance.getValueType(clazz, fieldName);
        assertEquals(result, expResult); 
    }
    
    @Test
    public void testGetValueTypeDate() {
        
        System.out.println("getValueType");
        
        Class clazz = Testentity.class;
        String fieldName = DataModelHelper.getInstance().getTIME_CREATED_FIELD();
        
        ValueType expResult = ValueType.DATE;
        ValueType result = instance.getValueType(clazz, fieldName);
        assertEquals(result, expResult); 
    }
    
    @Test
    public void testGetValueTypeList() {
        
        System.out.println("getValueType");
        
        Class clazz = Testentity.class;
        String fieldName = "testList";
        
        ValueType expResult = ValueType.LIST;
        ValueType result = instance.getValueType(clazz, fieldName);
        assertEquals(result, expResult); 
    }
    
    @Test
    public void testGetValueTypeShort() {
        
        System.out.println("getValueType");
        
        Class clazz = Testentity.class;
        String fieldName = "s";
        
        ValueType expResult = ValueType.SHORT;
        ValueType result = instance.getValueType(clazz, fieldName);
        assertEquals(result, expResult); 
    }
    
    @Test
    public void testGetValueTypeString() {
        
        System.out.println("getValueType");
        
        Class clazz = Testentity.class;
        String fieldName = "string";
        
        ValueType expResult = ValueType.STRING;
        ValueType result = instance.getValueType(clazz, fieldName);
        assertEquals(result, expResult); 
    }
    
    @Test(expected = DinaException.class)
    public void testGetValueTypeStringFailure() {
        
        System.out.println("getValueType");
        
        Class clazz = Testentity.class;
        String fieldName = "testField";
         
        ValueType result = instance.getValueType(clazz, fieldName); 
        assertNull(result);
    }
    
    /**
     * Test of validateFields method, of class JpaReflectionHelper.
     */
    @Test
    public void testValidateFields() {
        System.out.println("validateFields");
        Class clazz = Testentity.class;
        String fieldName = "version";  
        boolean result = instance.validateFields(clazz, fieldName);
        assertTrue(result);
    }
    
    @Test
    public void testValidateFieldsFalse() {
        System.out.println("validateFields");
        Class clazz = Testentity.class;
        String fieldName = "testField";  
        boolean result = instance.validateFields(clazz, fieldName);
        assertFalse(result);
    }
    
    /**
     * Test of isVersioned method, of class JpaReflectionHelper.
     */
    @Test
    public void testIsVersioned() {
        System.out.println("isVersioned");
        Class clazz = Testentity.class;  
        boolean result = instance.isVersioned(clazz);
        
        System.out.println("result : " + result);
        assertTrue(result);
    }
  
    
    /**
     * Test of getTimestampCreated method, of class JpaReflectionHelper.
     */
    @Test
    public void testGetTimestampCreated() {
        System.out.println("getTimestampCreated");
        
        Class clazz = Testentity.class; 
         
        String expected = DataModelHelper.getInstance().getTIME_CREATED_FIELD();
        Field result = instance.getTimestampCreated(clazz);
        assertEquals(result.getName(), expected); 
    }

 
    /**
     * Test of getTimestampCreated method, of class JpaReflectionHelper.
     */
    @Test
    public void testGetCreatedByClazz() {
        System.out.println("testGetCreatedByClazz");
         
        String expactResult = DataModelHelper.getInstance().getCREATED_BY_CLASS_NAME(); 
        Class result = instance.getCreatedByClazz();
        assertEquals(expactResult, result.getSimpleName());
    }
    
    
    /**
     * Test of getCreatedByField method, of class JpaReflectionHelper.
     */
    @Test
    public void testGetCreatedByField() {
        System.out.println("getCreatedByField");
        
        Class clazz = Testentity.class; 
    
        String expectedFieldName = "createdByAgentID";
        Field result = instance.getCreatedByField(clazz);
        assertEquals(result.getName(), expectedFieldName); 
    }
 
    
    
    /**
     * Test of getIDFieldName method, of class JpaReflectionHelper.
     */
    @Test(expected = DinaException.class)
    public void testGetIDFieldNameEntityBean() {
        System.out.println("getIDFieldName");
        
        EntityBean bean = new Testentity();
          
        String result = instance.getIDFieldName(bean);
        verify(instance, times(1)).getIDFieldName(bean.getClass());
 
        assertNull(result); 
    }
    
    
    
    /**
     * Test of getIDFieldName method, of class JpaReflectionHelper.
     */
    @Test(expected = DinaException.class)
    public void testGetIDFieldNameClass() {
        System.out.println("getIDFieldName");
        Class clazz = Testentity.class; 
        String result = instance.getIDFieldName(clazz); 
        assertNull(result);
    } 
    
    @Test(expected = DinaException.class)
    public void testGetIDField() {
        System.out.println("testGetIDField");
        EntityBean bean = new Testentity();
        Field result = instance.getIDField(bean);
        assertNull(result);
    } 
}
