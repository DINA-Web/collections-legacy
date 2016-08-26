/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.logic;
 
import java.security.DigestException;
import java.util.ArrayList;
import java.util.List; 
import java.util.Map; 
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;  
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when; 
import org.mockito.runners.MockitoJUnitRunner;
import static org.testng.Assert.*;  
import se.nrm.dina.data.exceptions.DinaException;
import se.nrm.dina.data.jpa.DinaDao; 
import se.nrm.dina.data.util.JpaReflectionHelper;
import se.nrm.dina.datamodel.BaseEntity;
import se.nrm.dina.datamodel.EntityBean; 
import se.nrm.dina.datamodel.impl.Testentity; 
import se.nrm.dina.logic.util.NamedQueries;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)
public class DinaDataLogicNGTest {
  
    @Mock
    DinaDao dao;
    
    @Mock
    ObjectMapper mapper;
     
    EntityBean testBean;

    private DinaDataLogic instance;  
   
    public DinaDataLogicNGTest() {
        
    }

    @Before
    public void setUpClass() throws Exception {
        testBean = new Testentity();
        instance = new DinaDataLogic(dao);
        instance = new DinaDataLogic(dao, mapper, testBean); 
    }

    @After
    public void tearDownClass() throws Exception {
    }

    @Test
    public void testDinaDataLogicConstructor() throws Exception {
        System.out.println("testDinaDataLogicConstructor");
 
        instance = new DinaDataLogic<>();
        assertNotNull(instance);
    }

    /**
     * Test of findAll method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAll_String() throws Exception {
        System.out.println("findAll");
        
        String entityName = "Testentity";
        
        List<Testentity> list = new ArrayList<>();
        Testentity testEntity = new Testentity(1);
        list.add(testEntity);
        testEntity = new Testentity(2);
        list.add(testEntity);
        
        Class clazz = Testentity.class;
        String strQuery = "SELECT e From Testentity e  ORDER BY e.id null";
        int limit = 0;
        Map<String, String> conditions = null;
        boolean isExact = false;
        int offset = 0;
       
        when(dao.findAll(clazz, strQuery, limit, conditions, isExact, offset)).thenReturn(list);
        
        
        List<Testentity> results = (List<Testentity>)instance.findAll(entityName, limit, offset, null, null);
        verify(dao).findAll(clazz, strQuery, limit, conditions, isExact, offset);
        assertEquals(2, results.size());
    }
    
    @Test(expected = DinaException.class)
    public void testFindAll_StringFailure() throws Exception {
        System.out.println("findAll");
        
        String entityName = "Test";
         
        
        Class clazz = Testentity.class;
        String strQuery = "SELECT e From Testentity e  ORDER BY e.id null";
        int limit = 0;
        Map<String, String> conditions = null;
        boolean isExact = false;
        int offset = 0;
         
        List<Testentity> results = (List<Testentity>)instance.findAll(entityName, limit, offset, null, null);
        verify(dao, times(0)).findAll(clazz, strQuery, limit, conditions, isExact, offset);  
        assertNull(results);
    }
    
    /**
     * Test of findAllBySearchCriteria method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAllBySearchCriteria() throws Exception {
        System.out.println("findAllBySearchCriteria");
         
        Class clazz = Testentity.class;
        String entityName = clazz.getSimpleName();
        int limit = 0;
        int offset = 0;
        String sort = "asc";
        List<String> orderBy = null;
        Map<String, String> conditions = null;
        boolean isExact = false;
             
        String strQuery = "SELECT e From Testentity e  ORDER BY e.id ASC";
        
        List<Testentity> list = new ArrayList<>();
        Testentity testEntity = new Testentity(1);
        list.add(testEntity);
        testEntity = new Testentity(2);
        list.add(testEntity);
 
        when(dao.findAll(clazz, strQuery, limit, conditions, isExact, offset)).thenReturn(list);
        
        List result = instance.findAllBySearchCriteria(entityName, limit, offset, sort, orderBy, conditions, isExact);
        verify(dao).findAll(clazz, strQuery, limit, conditions, isExact, offset);
        assertEquals(result, list);
    }
 
    @Test(expected = DinaException.class)
    public void testFindAllBySearchCriteriaException() throws Exception {
        System.out.println("findAllBySearchCriteria");
         
        Class clazz = BaseEntity.class;
        String entityName = clazz.getSimpleName();
        int limit = 0;
        int offset = 0;
        String sort = "asc";
        List<String> orderBy = null;
        Map<String, String> conditions = null;
        boolean isExact = false;
              
        String strQuery = "SELECT e From Testentity e  ORDER BY e.id ASC";
 
        instance.findAllBySearchCriteria(entityName, limit, offset, sort, orderBy, conditions, isExact);
        verify(dao, times(0)).findAll(clazz, strQuery, limit, conditions, isExact, offset); 
    }
  
    /**
     * Test of findById method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindByIdIng() throws Exception {
        System.out.println("findById");
      
        String entityName = "Testentity";
        Class clazz = JpaReflectionHelper.getInstance().convertClassNameToClass(entityName);
        String id = "20";
          
        Testentity theEntity = new Testentity(20);
        when(dao.findById(20, clazz, true)).thenReturn(theEntity);
        EntityBean result = instance.findById(id, entityName);
        
        assertNotNull(result);
        assertEquals(result.getEntityId(), 20);
        verify(dao).findById(20, clazz, true); 
    }
    
    @Test
    public void testFindByIdString() throws Exception {
        System.out.println("findById");
      
        String entityName = "Testentity";
        Class clazz = JpaReflectionHelper.getInstance().convertClassNameToClass(entityName);
        String id = "myId";
           
        Testentity theEntity = new Testentity(20);
        when(dao.findByStringId(id, clazz)).thenReturn(theEntity);
        EntityBean result = instance.findById(id, entityName);
        
        assertNotNull(result); 
        verify(dao).findByStringId(id, clazz); 
    }
        
    @Test(expected = DinaException.class)
    public void testFindByIdStringException() throws Exception {
        System.out.println("findById");
      
        String entityName = "BaseEntity";
        Class clazz = BaseEntity.class;
        String id = "20";
            
        EntityBean result = instance.findById(id, entityName);
        
        assertNull(result); 
        verify(dao, times(0)).findByStringId(id, clazz); 
        verify(dao, times(0)).findById(20, clazz, true); 
    }
 
    /**
     * Test of findEntitiesByids method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindEntitiesByids() throws Exception {
        System.out.println("findEntitiesByids");
        
        String entityName = "Testentity";
        Class clazz = Testentity.class;
        String ids = "(1,5,7)"; 
        
        Testentity theEntity1 = new Testentity(1);
        Testentity theEntity2 = new Testentity(2);
        Testentity theEntity3 = new Testentity(3);
        
        when(dao.findById(1, clazz, true)).thenReturn(theEntity1);
        when(dao.findById(5, clazz, true)).thenReturn(theEntity2);
        when(dao.findById(7, clazz, true)).thenReturn(theEntity3);
        
        List result = instance.findEntitiesByids(entityName, ids);
        assertEquals(result.size(), 3); 
    }
    
    /**
     * Test of findEntitiesByids method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindEntitiesByidsOneNotFound() throws Exception {
        System.out.println("findEntitiesByids");
        
        String entityName = "Testentity";
        Class clazz = Testentity.class;
        String ids = "(1,5,7)"; 
        
        Testentity theEntity1 = new Testentity(1);
        Testentity theEntity2 = new Testentity(2); 
        
        when(dao.findById(1, clazz, true)).thenReturn(theEntity1);
        when(dao.findById(5, clazz, true)).thenReturn(null);
        when(dao.findById(7, clazz, true)).thenReturn(theEntity2);
        
        List result = instance.findEntitiesByids(entityName, ids);
        assertEquals(result.size(), 2); 
    }
 
    /**
     * Test of findEntitiesByids method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindEntitiesByidsEmptyIds() throws Exception {
        System.out.println("findEntitiesByids");
        
        String entityName = "Testentity"; 
        String ids = "";  
        
        List result = instance.findEntitiesByids(entityName, ids);
        assertNull(result);  
    }
    
     
    /**
     * Test of findEntitiesByids method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindEntitiesByids1() throws Exception {
        System.out.println("findEntitiesByids");
        
        String entityName = "Testentity";
        Class clazz = Testentity.class;
        String ids = "1,5,7"; 
        
        Testentity theEntity1 = new Testentity(1);
        Testentity theEntity2 = new Testentity(2); 
        
        when(dao.findById(1, clazz, true)).thenReturn(theEntity1);
        when(dao.findById(5, clazz, true)).thenReturn(null);
        when(dao.findById(7, clazz, true)).thenReturn(theEntity2);
        
        List result = instance.findEntitiesByids(entityName, ids);
        assertEquals(result.size(), 2); 
    }
    
         
    /**
     * Test of findEntitiesByids method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test(expected = DinaException.class)
    public void testFindEntitiesByidsException() throws Exception {
        System.out.println("findEntitiesByids");
        
        String entityName = "BaseEntity"; 
        String ids = "1,5,7";  
        List result = instance.findEntitiesByids(entityName, ids);
        assertNull(result); 
    }
     
    /**
     * Test of findEntityCount method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindEntityCount() throws Exception {
        System.out.println("findEntityCount");
        
        String entityName = "Testentity";
        
        int expResult = 10;
        String strQuery = NamedQueries.getInstance().createFindTotalCountNamedQuery(JpaReflectionHelper.getInstance().convertClassNameToClass(entityName).getSimpleName());
        
        when(dao.getCountByQuery(strQuery)).thenReturn(10);
         
        int result = instance.findEntityCount(entityName);
        verify(dao).getCountByQuery(strQuery);
        
        assertEquals(result, expResult); 
    }
    
    /**
     * Test of findEntityCount method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test(expected = DinaException.class)
    public void testFindEntityCountFailure() throws Exception {
        System.out.println("findEntityCount");
        
        String entityName = "BaseEntity";
          
        int result = instance.findEntityCount(entityName); 
        assertNull(result); 
    }
 

    /**
     * Test of createEntity method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateEntity() throws Exception {
        System.out.println("createEntity");
        
        String entityName = "Testentity"; 
        
        ObjectMapper mapper1 = new ObjectMapper();
        String json = mapper1.writeValueAsString(testBean);
     
        when((EntityBean)mapper.readValue(json, Testentity.class)).thenReturn(testBean); 
        when(dao.create(testBean)).thenReturn(testBean);
        
        EntityBean result = instance.createEntity(entityName, json, 0);
        verify(dao).create(testBean);
        assertEquals(result, testBean);
        assertTrue(result instanceof Testentity);
    }
    
    
    
    
    
    
    
    /**
     * Test of deleteEntity method, of class DinaDataLogic.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteEntity() throws Exception {
        System.out.println("deleteEntity");

        String entityName = "Testentity";
        int id = 20;

        Testentity theEntity = new Testentity(20);
        when(dao.findByReference(id, JpaReflectionHelper.getInstance().convertClassNameToClass(entityName))).thenReturn(theEntity);

        instance.deleteEntity(entityName, id);
        verify(dao).delete(theEntity);
    }
    
    /**
     * Test of deleteEntity method, of class DinaDataLogic.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteEntityNotFound() throws Exception {
        System.out.println("deleteEntity");

        String entityName = "Testentity";
        int id = 20;
 
        Testentity theEntity = new Testentity(20);
        when(dao.findByReference(id, JpaReflectionHelper.getInstance().convertClassNameToClass(entityName))).thenReturn(null);

        instance.deleteEntity(entityName, id);
        verify(dao, times(0)).delete(theEntity);
    }
    
    /**
     * Test of deleteEntity method, of class DinaDataLogic.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = DinaException.class)
    public void testDeleteEntityException() throws Exception {
        System.out.println("deleteEntity");

        String entityName = "BaseEntity";
        int id = 20;
  
        instance.deleteEntity(entityName, id); 
    }
    

    /**
     * Test of createEntity method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
//    @Test
//    public void testCreateEntityFailure() throws Exception {
//        System.out.println("createEntity");
//        String entityName = "Accession";
//         
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(accession1);
//        
//        when(dao.create(accession1)).thenThrow(new DinaException("error"));
//        
//        try {
//            instance.createEntity(entityName, json, 0);
//            fail();
//        } catch(DinaException e) {
//            verify(dao).create(accession1);
//            assertEquals("error", e.getMessage());
//        } 
//    }
//    
//    
//    /**
//     * Test of updateEntity method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testUpdateEntity() throws Exception {
//        System.out.println("updateEntity");
//        
//        String entityName = "Accession"; 
//        ObjectMapper mapper = new ObjectMapper();
//
//        EntityBean bean = null;
//        try {
//            bean = (EntityBean) mapper.readValue(jsonInString, JpaReflectionHelper.getInstance().convertClassNameToClass(entityName));
//        } catch (IOException ex) {
//            throw new DinaException(ex.getMessage());
//        }
//   
//        when(dao.merge(bean)).thenReturn(bean);
//        
//        EntityBean result = instance.updateEntity(entityName, jsonInString);
//         
//        verify(dao).merge(bean);
//        assertEquals(result, bean);
//        assertTrue(result instanceof Accession);
//    }
    
//    /**
//     * Test of updateEntity method, of class DinaDataLogic.
//     *
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testUpdateEntityFailure() throws Exception {
//        System.out.println("updateEntity");
//
//        String entityName = "Accession"; 
//        
//        ObjectMapper mapper = new ObjectMapper();
//
//        EntityBean bean = null;
//        try {
//            bean = (EntityBean) mapper.readValue(jsonInString, JpaReflectionHelper.getInstance().convertClassNameToClass(entityName));
//        } catch (IOException ex) {
//            throw new DinaException(ex.getMessage());
//        }
//
//        when(dao.merge(bean)).thenThrow(new DinaException("error"));
//        try {
//            instance.updateEntity(entityName, jsonInString);
//            fail();
//        } catch(DinaException e) {
//            verify(dao).merge(bean);
//            assertEquals("error", e.getMessage());
//        }  
//    }
    
//    /**
//     * Test of updateEntity method, of class DinaDataLogic.
//     *
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testUpdateEntityFailure1() throws Exception {
//        System.out.println("updateEntity");
//
//        String entityName = "Accessionddd";
//
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(accession1);
// 
//        try {
//            instance.updateEntity(entityName, json);
//            fail();
//        } catch(DinaException e) {
//            verify(dao, times(0)).merge(accession1);
//            assertEquals("The entity name is wrong", e.getMessage());
//        }  
//    }



    

//    /**
//     * Test of deleteEntity method, of class DinaDataLogic.
//     *
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testDeleteEntity1() throws Exception {
//        System.out.println("deleteEntity");
//
//        String entityName = "Accession";
//        int id = 20;
//         
//        when(dao.findByReference(id, JpaReflectionHelper.getInstance().convertClassNameToClass(entityName))).thenReturn(null);
//    
//        instance.deleteEntity(entityName, id); 
//        verify(dao, times(0)).delete(accession1);
//        verify(dao).findByReference(id, JpaReflectionHelper.getInstance().convertClassNameToClass(entityName));
//    }
    

    /**
     * Test of deleteEntity method, of class DinaDataLogic.
     *
     * @throws java.lang.Exception
     */
//    @Test
//    public void testDeleteEntityFailure() throws Exception {
//        System.out.println("deleteEntity");
//
//        String entityName = "Accession";
//        int id = 20;
//         
//        when(dao.findByReference(id, JpaReflectionHelper.getInstance().convertClassNameToClass(entityName))).thenReturn(accession1);
//    
//        doThrow(mock(DinaException.class)).when(dao).delete(accession1);
//        
//        try {
//            instance.deleteEntity(entityName, id);
//        } catch(DinaException e) {
//            verify(dao).delete(accession1);
//            verify(dao).findByReference(id, JpaReflectionHelper.getInstance().convertClassNameToClass(entityName));
//        }  
//    }
    
//    private void preparaTestData() {
//        accessions = new ArrayList();
//        Accession accession = new Accession(1);
//        accessions.add(accession);
//        accession = new Accession(2);
//        accessions.add(accession);
//        accession = new Accession(3);
//        accessions.add(accession);
//        accession = new Accession(4);
//        accessions.add(accession);
//        accession = new Accession(5);
//        accessions.add(accession);
//        accession = new Accession(6);
//        accessions.add(accession);
//        accession = new Accession(7);
//        accessions.add(accession);
//        accession = new Accession(8);
//        accessions.add(accession);
//        accession = new Accession(9);
//        accessions.add(accession);
//        accession = new Accession(10);
//        accessions.add(accession);
//        
//        accessions1 = new ArrayList();
//        accessions1.add(new Accession(3));
//        accessions1.add(new Accession(4));
//        accessions1.add(new Accession(5));
//
//        accession1 = new Accession(20);
//        accession1.setAccessionNumber("acc00020");
        
//        
//        StringBuilder sb = new StringBuilder();
//        sb.append("{ \"timestampCreated\": 1297691649000,");
//        sb.append("\"accessionID\": 20,");
//        sb.append("\"accessionNumber\": \"2004-NHRS-001\",");
//        sb.append("\"dateAccessioned\": \"2004-07-07\",");
//        sb.append("\"dateReceived\": \"2004-07-07\",");
//        sb.append("\"status\": \"inprocess\",");
//        sb.append("\"modifiedByAgentID\": 1,");
//        sb.append("\"createdByAgentID\": 1,");
//        sb.append("\"version\": 10,");
//        sb.append("\"accessionagentList\": [1, 2, 7]}");
//        jsonInString = sb.toString();
//    }

//    /**
//     * Test of findAll method, of class DinaDataLogic.
//     */
//    @org.testng.annotations.Test
//    public void testFindAll() throws Exception {
//        System.out.println("findAll");
//        String entityName = "";
//        int limit = 0;
//        int offset = 0;
//        String sort = "";
//        List<String> orderby = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        DinaDataLogic instance = (DinaDataLogic)container.getContext().lookup("java:global/classes/DinaDataLogic");
//        List expResult = null;
//        List result = instance.findAll(entityName, limit, offset, sort, orderby);
//        assertEquals(result, expResult);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findAllBySearchCriteria method, of class DinaDataLogic.
//     */
//    @org.testng.annotations.Test
//    public void testFindAllBySearchCriteria() throws Exception {
//        System.out.println("findAllBySearchCriteria");
//        String entityName = "";
//        int limit = 0;
//        int offset = 0;
//        String sort = "";
//        List<String> orderby = null;
//        Map<String, String> condition = null;
//        boolean isExact = false;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        DinaDataLogic instance = (DinaDataLogic)container.getContext().lookup("java:global/classes/DinaDataLogic");
//        List expResult = null;
//        List result = instance.findAllBySearchCriteria(entityName, limit, offset, sort, orderby, condition, isExact);
//        assertEquals(result, expResult);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findById method, of class DinaDataLogic.
//     */
//    @org.testng.annotations.Test
//    public void testFindById() throws Exception {
//        System.out.println("findById");
//        String id = "";
//        String entityName = "";
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        DinaDataLogic instance = (DinaDataLogic)container.getContext().lookup("java:global/classes/DinaDataLogic");
//        Object expResult = null;
//        Object result = instance.findById(id, entityName);
//        assertEquals(result, expResult);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }


//    /**
//     * Test of createEntity method, of class DinaDataLogic.
//     */
//    @org.testng.annotations.Test
//    public void testCreateEntity() throws Exception {
//        System.out.println("createEntity");
//        String entityName = "";
//        String json = "";
//        int agentId = 0;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        DinaDataLogic instance = (DinaDataLogic)container.getContext().lookup("java:global/classes/DinaDataLogic");
//        EntityBean expResult = null;
//        EntityBean result = instance.createEntity(entityName, json, agentId);
//        assertEquals(result, expResult);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateEntity method, of class DinaDataLogic.
//     */
//    @org.testng.annotations.Test
//    public void testUpdateEntity() throws Exception {
//        System.out.println("updateEntity");
//        String entityName = "";
//        String json = "";
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        DinaDataLogic instance = (DinaDataLogic)container.getContext().lookup("java:global/classes/DinaDataLogic");
//        EntityBean expResult = null;
//        EntityBean result = instance.updateEntity(entityName, json);
//        assertEquals(result, expResult);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteEntity method, of class DinaDataLogic.
//     */
//    @org.testng.annotations.Test
//    public void testDeleteEntity() throws Exception {
//        System.out.println("deleteEntity");
//        String entityName = "";
//        int id = 0;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        DinaDataLogic instance = (DinaDataLogic)container.getContext().lookup("java:global/classes/DinaDataLogic");
//        instance.deleteEntity(entityName, id);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
