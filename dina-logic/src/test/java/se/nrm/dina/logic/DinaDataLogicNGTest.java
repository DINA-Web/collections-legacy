/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.logic;
 
import java.util.ArrayList;
import java.util.List; 
import java.util.Map; 
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
import se.nrm.dina.datamodel.impl.Testentity;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)
public class DinaDataLogicNGTest {
  
    @Mock
    DinaDao dao;

    private DinaDataLogic instance; 
    private String jsonInString;
   
    public DinaDataLogicNGTest() {
        
    }

    @Before
    public void setUpClass() throws Exception {
        instance = new DinaDataLogic(dao);
//        preparaTestData();
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
//    @Test
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
             
        String strQuery = "";
        
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
 

//    /**
//     * Test of findAll method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindAll_7args() throws Exception {
//        System.out.println("findAll");
//        
//        String entityName = "Accession";
//        Class clazz = Accession.class;
//        
//        int offset = 2;
//        int limit = 3;
//        int minid = 5;
//        int maxid = 9;
//        String sort = null;
//        List<String> orders = null;
//        Map<String, String> conditions = null;
//        
//        String strQuery = NamedQueries.getInstance()
//                    .createQueryFindAllWithSearchCriteria(entityName, 
//                            clazz, offset, minid, maxid, sort, orders, false, conditions);
//
//        when(dao.findAll(clazz, strQuery, limit, conditions)).thenReturn(accessions1);
//        
//        
//        EntityWrapper result = (EntityWrapper)instance.findAll(entityName, offset, limit, minid, maxid, sort, orders, conditions);
//        verify(dao).findAll(clazz, strQuery, limit, conditions);
//        
//        assertEquals(result.getResult(), accessions1); 
//    }
//    

//    /**
//     * Test of findAll method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testFindAll_7argsFailure() throws Exception {
//        System.out.println("findAll");
//        
//        String entityName = "Accession";
//        Class clazz = Accession.class;
//        
//        int offset = 2;
//        int limit = 3;
//        int minid = 5;
//        int maxid = 9;
//        String sort = null;
//        List<String> orders = null;
//        Map<String, String> conditions = null;
//        
//        String strQuery = NamedQueries.getInstance()
//                    .createQueryFindAllWithSearchCriteria(entityName, 
//                            clazz, offset, minid, maxid, sort, orders, false, conditions);
//
//        when(dao.findAll(clazz, strQuery, limit, conditions)).thenThrow(new DinaException("error"));
//        
//        try {
//            instance.findAll(entityName, offset, limit, minid, maxid, sort, orders, conditions);
//            fail("Expected a DinaException to be thrown");  
//        } catch(DinaException e) {
//            verify(dao).findAll(clazz, strQuery, limit, conditions);
//            assertEquals(e.getMessage(), "error"); 
//        } 
//    }
    


//    
//
//    /**
//     * Test of findAllBySearchCriteria method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindAllBySearchCriteriaWithExactSearch() throws Exception {
//        System.out.println("findAllBySearchCriteria");
//         
//        MultivaluedMap<String, String> map = new MultivaluedHashMap();
//        map.add("exact", "true");
//  
//        String offset = map.getFirst("offset");
//        String limit = map.getFirst("limit");
//        String minid = map.getFirst("minid");
//        String maxid = map.getFirst("maxid");
//        String orderBy = map.getFirst("orderby"); 
//        
//        List<String> orderby = new ArrayList<>();
//        if (orderBy != null) {
//            orderby = Arrays.asList(orderBy.split(","));
//        }
//          
////        Map<String, String> condition = map.entrySet()
////                .stream() 
////                .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue().get(0)));
//        
//        Map<String, String> condition = new HashMap();
//        
//        String entityName = "Accession";
//        Class clazz = Accession.class;
//        String strQuery = NamedQueries.getInstance()
//                    .createQueryFindAllWithSearchCriteria(
//                            entityName,
//                            clazz,
//                            Integer.parseInt(offset == null ? "0" : offset),
//                            Integer.parseInt(minid == null ? "0" : minid),
//                            Integer.parseInt(maxid == null ? "0" : maxid),
//                            null,
//                            orderby, true, condition);
// 
//        when(dao.findAll(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition)).thenReturn(accessions1);
//        
//        List result = instance.findAllBySearchCriteria(entityName, map);
//        verify(dao).findAll(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition);
//        assertEquals(result, accessions1);
//    }    
    
    
    
    
//    /**
//     * Test of findAllBySearchCriteria method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindAllBySearchCriteria1() throws Exception {
//        System.out.println("findAllBySearchCriteria1");
//         
//        MultivaluedMap<String, String> map = new MultivaluedHashMap();
//        map.add("orderby", "accessionNumber");
//  
//        String offset = map.getFirst("offset");
//        String limit = map.getFirst("limit");
//        String minid = map.getFirst("minid");
//        String maxid = map.getFirst("maxid");
//        String orderBy = map.getFirst("orderby");
//          
//        List<String> orderby = new ArrayList<>();
//        if (orderBy != null) {
//            orderby = Arrays.asList(orderBy.split(","));
//        }
//          
//        Map<String, String> condition = map.entrySet()
//                .stream() 
//                .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue().get(0)));
//        
//        condition.remove("orderby");
//        
//        String entityName = "Accession";
//        Class clazz = Accession.class;
//        String strQuery = NamedQueries.getInstance()
//                    .createQueryFindAllWithSearchCriteria(
//                            entityName,
//                            clazz,
//                            Integer.parseInt(offset == null ? "0" : offset),
//                            Integer.parseInt(minid == null ? "0" : minid),
//                            Integer.parseInt(maxid == null ? "0" : maxid),
//                            null,
//                            orderby, false, condition);
// 
//        when(dao.findAllWithFuzzSearch(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition)).thenReturn(accessions1);
//        
//        List result = instance.findAllBySearchCriteria(entityName, map);
//        verify(dao).findAllWithFuzzSearch(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition);
//        assertEquals(result, accessions1);
//    }
    
//       /**
//     * Test of findAllBySearchCriteria method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindAllBySearchCriteria1WithExactSearch() throws Exception {
//        System.out.println("findAllBySearchCriteria1");
//         
//        MultivaluedMap<String, String> map = new MultivaluedHashMap();
//        map.add("orderby", "accessionNumber");
//        map.add("exact", "true");
//  
//        String offset = map.getFirst("offset");
//        String limit = map.getFirst("limit");
//        String minid = map.getFirst("minid");
//        String maxid = map.getFirst("maxid");
//        String orderBy = map.getFirst("orderby"); 
//          
//        List<String> orderby = new ArrayList<>();
//        if (orderBy != null) {
//            orderby = Arrays.asList(orderBy.split(","));
//        }
//          
//        Map<String, String> condition = map.entrySet()
//                .stream() 
//                .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue().get(0)));
//        
//        condition.remove("orderby");
//        condition.remove("exact");
//        
//        String entityName = "Accession";
//        Class clazz = Accession.class;
//        String strQuery = NamedQueries.getInstance()
//                    .createQueryFindAllWithSearchCriteria(
//                            entityName,
//                            clazz,
//                            Integer.parseInt(offset == null ? "0" : offset),
//                            Integer.parseInt(minid == null ? "0" : minid),
//                            Integer.parseInt(maxid == null ? "0" : maxid),
//                            null,
//                            orderby, true, condition);
// 
//        when(dao.findAll(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition)).thenReturn(accessions1);
//        
//        List result = instance.findAllBySearchCriteria(entityName, map);
//        verify(dao).findAll(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition);
//        assertEquals(result, accessions1);
//    }    
//    
    
//    /**
//     * Test of findAllBySearchCriteria method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindAllBySearchCriteria2() throws Exception {
//        System.out.println("findAllBySearchCriteria2");
//         
//        MultivaluedMap<String, String> map = new MultivaluedHashMap();
//        map.add("orderby", "accessionNumber");
//        map.add("offset", "20");
//        map.add("limit", "5");
//        map.add("minid", "3"); 
//        map.add("maxid", "60");
//  
//        
//  
//        String offset = map.getFirst("offset");
//        String limit = map.getFirst("limit");
//        String minid = map.getFirst("minid");
//        String maxid = map.getFirst("maxid");
//        String orderBy = map.getFirst("orderby");
//          
//        List<String> orderby = new ArrayList<>();
//        if (orderBy != null) {
//            orderby = Arrays.asList(orderBy.split(","));
//        }
//          
//        Map<String, String> condition = map.entrySet()
//                .stream() 
//                .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue().get(0)));
//        
//        condition.remove("orderby");
//        condition.remove("offset");
//        condition.remove("limit");
//        condition.remove("minid");
//        condition.remove("maxid");
//        
//        String entityName = "Accession";
//        Class clazz = Accession.class;
//        String strQuery = NamedQueries.getInstance()
//                    .createQueryFindAllWithSearchCriteria(
//                            entityName,
//                            clazz,
//                            Integer.parseInt(offset == null ? "0" : offset),
//                            Integer.parseInt(minid == null ? "0" : minid),
//                            Integer.parseInt(maxid == null ? "0" : maxid),
//                            null,
//                            orderby, false, condition);
// 
//        when(dao.findAllWithFuzzSearch(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition)).thenReturn(accessions1);
//        
//        List result = instance.findAllBySearchCriteria(entityName, map);
//        verify(dao).findAllWithFuzzSearch(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition);
//        assertEquals(result, accessions1);
//    }
//
//    /**
//     * Test of findAllBySearchCriteria method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindAllBySearchCriteria2WithExactSearch() throws Exception {
//        System.out.println("findAllBySearchCriteria2");
//         
//        MultivaluedMap<String, String> map = new MultivaluedHashMap();
//        map.add("orderby", "accessionNumber");
//        map.add("offset", "20");
//        map.add("limit", "5");
//        map.add("minid", "3"); 
//        map.add("maxid", "60"); 
//        map.add("exact", "true"); 
//        
//  
//        String offset = map.getFirst("offset");
//        String limit = map.getFirst("limit");
//        String minid = map.getFirst("minid");
//        String maxid = map.getFirst("maxid");
//        String orderBy = map.getFirst("orderby");
//          
//        List<String> orderby = new ArrayList<>();
//        if (orderBy != null) {
//            orderby = Arrays.asList(orderBy.split(","));
//        }
//          
//        Map<String, String> condition = map.entrySet()
//                .stream() 
//                .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue().get(0)));
//        
//        condition.remove("orderby");
//        condition.remove("offset");
//        condition.remove("limit");
//        condition.remove("minid");
//        condition.remove("maxid");
//        condition.remove("exact");
//        
//        String entityName = "Accession";
//        Class clazz = Accession.class;
//        String strQuery = NamedQueries.getInstance()
//                    .createQueryFindAllWithSearchCriteria(
//                            entityName,
//                            clazz,
//                            Integer.parseInt(offset == null ? "0" : offset),
//                            Integer.parseInt(minid == null ? "0" : minid),
//                            Integer.parseInt(maxid == null ? "0" : maxid),
//                            null,
//                            orderby, true, condition);
// 
//        when(dao.findAll(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition)).thenReturn(accessions1);
//        
//        List result = instance.findAllBySearchCriteria(entityName, map);
//        verify(dao).findAll(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition);
//        assertEquals(result, accessions1);
//    }    
//    
//    
//    
    

//    /**
//     * Test of findAllBySearchCriteria method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindAllBySearchCriteriaFailure() throws Exception {
//        System.out.println("findAllBySearchCriteria");
//         
//        MultivaluedMap<String, String> map = new MultivaluedHashMap();
//  
//        String offset = map.getFirst("offset");
//        String limit = map.getFirst("limit");
//        String minid = map.getFirst("minid");
//        String maxid = map.getFirst("maxid");
//        String orderBy = map.getFirst("orderby");
//        
//        List<String> orderby = new ArrayList<>();
//        if (orderBy != null) {
//            orderby = Arrays.asList(orderBy.split(","));
//        }
//          
//        Map<String, String> condition = map.entrySet()
//                .stream() 
//                .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue().get(0)));
//        String entityName = "Accession";
//        Class clazz = Accession.class;
//        String strQuery = NamedQueries.getInstance()
//                    .createQueryFindAllWithSearchCriteria(
//                            entityName,
//                            clazz,
//                            Integer.parseInt(offset == null ? "0" : offset),
//                            Integer.parseInt(minid == null ? "0" : minid),
//                            Integer.parseInt(maxid == null ? "0" : maxid),
//                            null,
//                            orderby, false, condition);
// 
//        when(dao.findAllWithFuzzSearch(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition)).thenThrow(new DinaException());
//        
//        try {
//            instance.findAllBySearchCriteria(entityName, map);
//            fail();
//        } catch(DinaException e) {
//            verify(dao).findAllWithFuzzSearch(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition); 
//        } 
//    }

//    /**
//     * Test of findAllBySearchCriteria method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindAllBySearchCriteriaFailureWithExactSearch() throws Exception {
//        System.out.println("findAllBySearchCriteria");
//         
//        MultivaluedMap<String, String> map = new MultivaluedHashMap();
//        map.add("exact", "true");
//  
//        String offset = map.getFirst("offset");
//        String limit = map.getFirst("limit");
//        String minid = map.getFirst("minid");
//        String maxid = map.getFirst("maxid");
//        String orderBy = map.getFirst("orderby"); 
//        
//        List<String> orderby = new ArrayList<>();
//        if (orderBy != null) {
//            orderby = Arrays.asList(orderBy.split(","));
//        }
//          
//        Map<String, String> condition = map.entrySet()
//                .stream() 
//                .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue().get(0)));
//        condition.remove("exact");
//        
//        
//        String entityName = "Accession";
//        Class clazz = Accession.class;
//        String strQuery = NamedQueries.getInstance()
//                    .createQueryFindAllWithSearchCriteria(
//                            entityName,
//                            clazz,
//                            Integer.parseInt(offset == null ? "0" : offset),
//                            Integer.parseInt(minid == null ? "0" : minid),
//                            Integer.parseInt(maxid == null ? "0" : maxid),
//                            null,
//                            orderby, true, condition);
// 
//        when(dao.findAll(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition)).thenThrow(new DinaException());
//        
//        try {
//            instance.findAllBySearchCriteria(entityName, map);
//            fail();
//        } catch(DinaException e) {
//            verify(dao).findAll(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition); 
//        } 
//    }
    
    /**
//     * Test of findById method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
//   // @Test
//    public void testFindById() throws Exception {
//        System.out.println("findById");
//      
//        String entityName = "Accession"; 
//        EntityWrapper wrapper = new EntityWrapper(null, accession1);
//        
//        when(dao.findById(20, Accession.class)).thenReturn(wrapper);
//        EntityBean result = instance.findById("20", entityName);
//        
//        verify(dao).findById(20, Accession.class);
//        assertEquals(result, wrapper);
//        assertTrue(result instanceof Accession);
//    }

//    /**
//     * Test of findById method, of class DinaDataLogic.
//     *
//     * @throws java.lang.Exception
//     */
//   // @Test
//    public void testFindByIdString() throws Exception {
//        System.out.println("findById");
//
//        String entityName = "Accession"; 
//        
//        when(dao.findByStringId("test", Accession.class)).thenReturn(accession1);
//        EntityBean result = instance.findById("test", entityName);
//        
//        verify(dao).findByStringId("test", Accession.class);
//        assertEquals(result, accession1);
//        assertTrue(result instanceof Accession);
//    }
//    
//    /**
//     * Test of findById method, of class DinaDataLogic.
//     *
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testFindByIdFailure() throws Exception {
//        System.out.println("findById");
//
//        String entityName = "Accession";
//
//        when(dao.findById(20, Accession.class)).thenThrow(new DinaException("error"));
//        
//        try {
//            instance.findById("20", entityName);
//            fail();
//        } catch(DinaException e) {
//            verify(dao).findById(20, Accession.class);
//            assertEquals("error", e.getMessage());
//        } 
//    }
//
//    /**
//     * Test of findEntityCount method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindEntityCount() throws Exception {
//        System.out.println("findEntityCount");
//        
//        String entityName = "Accession";
//        
//        int expResult = 10;
//        String strQuery = NamedQueries.getInstance().createFindTotalCountNamedQuery(JpaReflectionHelper.getInstance().convertClassNameToClass(entityName).getSimpleName());
//        
//        when(dao.getCountByQuery(strQuery)).thenReturn(10);
//         
//        int result = instance.findEntityCount(entityName);
//        verify(dao).getCountByQuery(strQuery);
//        
//        assertEquals(result, expResult); 
//    }
//    
//    /**
//     * Test of findEntityCount method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindEntityCountFailure() throws Exception {
//        System.out.println("findEntityCount");
//        
//        String entityName = "Accession";
//         
//        String strQuery = NamedQueries.getInstance().createFindTotalCountNamedQuery(JpaReflectionHelper.getInstance().convertClassNameToClass(entityName).getSimpleName());
//        
//        when(dao.getCountByQuery(strQuery)).thenThrow(new DinaException("The entity name is wrong"));
//         
//        try {
//            instance.findEntityCount(entityName);
//            fail();
//        } catch(DinaException e) {
//            verify(dao).getCountByQuery(strQuery);
//            assertEquals("The entity name is wrong", e.getMessage()); 
//        }   
//    }   

//    /**
//     * Test of createEntity method, of class DinaDataLogic.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testCreateEntity() throws Exception {
//        System.out.println("createEntity");
//        String entityName = "Accession";
//         
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(accession1);
//        
//        when(dao.create(accession1)).thenReturn(accession1);
//        
//        EntityBean result = instance.createEntity(entityName, json, 0);
//        verify(dao).create(accession1);
//        assertEquals(result, accession1);
//        assertTrue(result instanceof Accession);
//    }
    

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
//    public void testDeleteEntity() throws Exception {
//        System.out.println("deleteEntity");
//
//        String entityName = "Accession";
//        int id = 20;
//         
//        when(dao.findByReference(id, JpaReflectionHelper.getInstance().convertClassNameToClass(entityName))).thenReturn(accession1);
//    
//        instance.deleteEntity(entityName, id); 
//        verify(dao).delete(accession1);
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
//
//    /**
//     * Test of findEntitiesByids method, of class DinaDataLogic.
//     */
//    @org.testng.annotations.Test
//    public void testFindEntitiesByids() throws Exception {
//        System.out.println("findEntitiesByids");
//        String entityName = "";
//        String ids = "";
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        DinaDataLogic instance = (DinaDataLogic)container.getContext().lookup("java:global/classes/DinaDataLogic");
//        List expResult = null;
//        List result = instance.findEntitiesByids(entityName, ids);
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
