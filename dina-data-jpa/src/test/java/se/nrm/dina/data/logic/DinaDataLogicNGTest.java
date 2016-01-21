/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.logic;

import java.util.ArrayList; 
import java.util.Arrays;
import java.util.List;
import java.util.Map; 
import java.util.stream.Collectors; 
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap; 
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock; 
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when; 
import org.mockito.runners.MockitoJUnitRunner;
import static org.testng.Assert.*;  
import se.nrm.dina.data.jpa.DinaDao;
import se.nrm.dina.data.util.NamedQueries;
import se.nrm.dina.data.util.Util;
import se.nrm.dina.datamodel.Accession;
import se.nrm.dina.datamodel.EntityBean;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)
public class DinaDataLogicNGTest {
  
    @Mock
    DinaDao dao;

    private DinaDataLogic instance;
    private List<Accession> accessions;
    private List<Accession> accessions1;
    private Accession accession1;
   
    public DinaDataLogicNGTest() {
        
    }

    @Before
    public void setUpClass() throws Exception {
        instance = new DinaDataLogic(dao);
        preparaTestData();
    }

    @After 
    public void tearDownClass() throws Exception {
    }

    /**
     * Test of findAll method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAll_String() throws Exception {
        System.out.println("findAll");
        
        String entityName = "Accession";
        
        when(dao.findAll(Accession.class)).thenReturn(accessions);
        
        List result = instance.findAll(entityName);
        verify(dao).findAll(Accession.class);
        assertEquals(10, result.size());
    }

    /**
     * Test of findAll method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAll_7args() throws Exception {
        System.out.println("findAll");
        
        String entityName = "Accession";
        Class clazz = Accession.class;
        
        int offset = 2;
        int limit = 3;
        int minid = 5;
        int maxid = 9;
        List<String> sort = null;
        Map<String, String> conditions = null;
        
        String strQuery = NamedQueries.getInstance()
                    .createQueryFindAllWithSearchCriteria(entityName, 
                            clazz, offset, minid, maxid, sort, conditions);

        when(dao.findAll(clazz, strQuery, limit, conditions)).thenReturn(accessions1);
        List result = instance.findAll(entityName, offset, limit, minid, maxid, sort, conditions);
        verify(dao).findAll(clazz, strQuery, limit, conditions);
        
        assertEquals(result, accessions1); 
    }

    /**
     * Test of findAllBySearchCriteria method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAllBySearchCriteria() throws Exception {
        System.out.println("findAllBySearchCriteria");
         
        MultivaluedMap<String, String> map = new MultivaluedHashMap();
        
        String offset = map.getFirst("offset");
        String limit = map.getFirst("limit");
        String minid = map.getFirst("minid");
        String maxid = map.getFirst("maxid");
        String orderBy = map.getFirst("orderby");
        
        List<String> orderby = new ArrayList<>();
        if (orderBy != null) {
            orderby = Arrays.asList(orderBy.split(","));
        }
          
        Map<String, String> condition = map.entrySet()
                .stream() 
                .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue().get(0)));
        String entityName = "Accession";
        Class clazz = Accession.class;
        String strQuery = NamedQueries.getInstance()
                    .createQueryFindAllWithSearchCriteria(
                            entityName,
                            clazz,
                            Integer.parseInt(offset == null ? "0" : offset),
                            Integer.parseInt(minid == null ? "0" : minid),
                            Integer.parseInt(maxid == null ? "0" : maxid),
                            orderby, condition);
 
        when(dao.findAll(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition)).thenReturn(accessions1);
        
        List result = instance.findAllBySearchCriteria(entityName, map);
        verify(dao).findAll(clazz, strQuery, Integer.parseInt(limit == null ? "50" : limit), condition);
        assertEquals(result, accessions1);
    }

    /**
     * Test of findById method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
      
        String entityName = "Accession"; 
        
        when(dao.findById(20, Accession.class)).thenReturn(accession1);
        EntityBean result = instance.findById("20", entityName);
        
        verify(dao).findById(20, Accession.class);
        assertEquals(result, accession1);
        assertTrue(result instanceof Accession);
    }

    /**
     * Test of findEntityCount method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindEntityCount() throws Exception {
        System.out.println("findEntityCount");
        
        String entityName = "Accession";
        
        int expResult = 10;
        String strQuery = NamedQueries.getInstance().createFindTotalCountNamedQuery(Util.getInstance().convertClassNameToClass(entityName).getSimpleName());
        
        when(dao.getCountByQuery(strQuery)).thenReturn(10);
         
        int result = instance.findEntityCount(entityName);
        verify(dao).getCountByQuery(strQuery);
        
        assertEquals(result, expResult); 
    }

    /**
     * Test of createEntity method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateEntity() throws Exception {
        System.out.println("createEntity");
        String entityName = "Accession";
         
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(accession1);
        
        when(dao.create(accession1)).thenReturn(accession1);
        
        EntityBean result = instance.createEntity(entityName, jsonInString);
        verify(dao).create(accession1);
        assertEquals(result, accession1);
        assertTrue(result instanceof Accession);
    }
    
    /**
     * Test of updateEntity method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateEntity() throws Exception {
        System.out.println("updateEntity");
        
        String entityName = "Accession";
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(accession1);

        when(dao.merge(accession1)).thenReturn(accession1);
        
        EntityBean result = instance.updateEntity(entityName, jsonInString);
         
        verify(dao).merge(accession1);
        assertEquals(result, accession1);
        assertTrue(result instanceof Accession);
    }

    /**
     * Test of deleteEntity method, of class DinaDataLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteEntity() throws Exception {
        System.out.println("deleteEntity");
        
        String entityName = "Accession";
        int id = 20;
         
        when(dao.findByReference(id, Util.getInstance().convertClassNameToClass(entityName))).thenReturn(accession1);
    
        instance.deleteEntity(entityName, id); 
        verify(dao).delete(accession1);
    }
    
    private void preparaTestData() {
        accessions = new ArrayList();
        Accession accession = new Accession(1);
        accessions.add(accession);
        accession = new Accession(2);
        accessions.add(accession);
        accession = new Accession(3);
        accessions.add(accession);
        accession = new Accession(4);
        accessions.add(accession);
        accession = new Accession(5);
        accessions.add(accession);
        accession = new Accession(6);
        accessions.add(accession);
        accession = new Accession(7);
        accessions.add(accession);
        accession = new Accession(8);
        accessions.add(accession);
        accession = new Accession(9);
        accessions.add(accession);
        accession = new Accession(10);
        accessions.add(accession);
        
        accessions1 = new ArrayList();
        accessions1.add(new Accession(3));
        accessions1.add(new Accession(4));
        accessions1.add(new Accession(5));

        accession1 = new Accession(20);
        accession1.setAccessionNumber("acc00020");
    }
    
}