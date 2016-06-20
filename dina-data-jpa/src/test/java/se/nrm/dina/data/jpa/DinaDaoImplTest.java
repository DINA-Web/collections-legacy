/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.jpa;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException; 
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory; 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import se.nrm.dina.data.exceptions.DinaException; 
import se.nrm.dina.datamodel.EntityBean;
import se.nrm.dina.datamodel.Accession;
import se.nrm.dina.datamodel.Geoname;
import se.nrm.dina.datamodel.Recordsetitem;
import se.nrm.dina.datamodel.Sppermission;
import se.nrm.dina.datamodel.Workbenchdataitem;
import se.nrm.dina.datamodel.Workbenchrow;
import se.nrm.dina.datamodel.Workbenchrowimage;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)
public class DinaDaoImplTest {
    
    @Mock
    EntityManager entityManager;
    
    @Mock
    Query query;
    
    @Mock
    TypedQuery<String> tq;
    
    @Mock
    TypedQuery<Object[]> tqObj;
    
    @Mock
    TypedQuery<Integer> intTQ;
    
    private DinaDao dao;
    private List<Accession> accessions;
    private List<Accession> accessions1;
    private Accession accession1;
    
    private String strQuery;
    
    public DinaDaoImplTest() {
        
    }
    
    @Before
    public void setUp() {
        dao = new DinaDaoImpl(entityManager, query);
        preparaTestData();
    }
    
    @After
    public void tearDown() {
        dao = null;
        accession1 = null;
        accessions = null;
        accessions1 = null;        
    }
    
    @Test
    public void testDinaDaoImplConstractor() throws Exception {
        dao = new DinaDaoImpl();
        assertNotNull(dao);
    }

    /**
     * Test of findAll method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        
        when(entityManager.createNamedQuery(Accession.class.getSimpleName() + ".findAll")).thenReturn(query);
        when(query.getResultList()).thenReturn(accessions);
        int expResult = 10;
        
        List result = dao.findAll(Accession.class);
        verify(entityManager).createNamedQuery(Accession.class.getSimpleName() + ".findAll");
        verify(query).getResultList();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of findAll method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
//    @Test
    public void testFindAllWithConditionsException() throws Exception {
        System.out.println("findAll");
        
        Class clazz = Accession.class;
        int limit = 20;
        Map<String, String> conditions = new HashMap();
        
        DinaException e = new DinaException("error");        
        when(entityManager.createQuery(strQuery)).thenReturn(query);        
        when(query.setMaxResults(limit)).thenReturn(query);
        when(query.getResultList()).thenThrow(e);
        
        try {
            dao.findAll(clazz, strQuery, limit, conditions);            
            fail("Expected a DinaException to be thrown");
        } catch (DinaException ex) {            
            assertEquals(ex.getMessage(), "error");
        }
    }

    /**
     * Test of findAll method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
//    @Test
    public void testFindAllWithConditions1() throws Exception {
        System.out.println("findAll");
        
        Class clazz = Accession.class;
        int limit = 20;
        Map<String, String> conditions = new HashMap();
        conditions.put("accessionNumber", "12345");
        conditions.put("accessionID", "5");
        conditions.put("divisionID", "5");
        
        when(entityManager.createQuery(strQuery)).thenReturn(query);
        when(query.setMaxResults(limit)).thenReturn(query);
        when(query.getResultList()).thenReturn(accessions1);
        
        dao.findAll(clazz, strQuery, limit, conditions);
        
    }

    /**
     * Test of findAll with conditions method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
//    @Test
    public void testFindAllWithConditions2() throws Exception {
        
        System.out.println("testFindAllWithConditions");
        
        Class clazz = Accession.class;        
        int limit = 3;
        Map<String, String> conditions = null;
        
        int expResultSize = 3;
        
        when(entityManager.createQuery(strQuery)).thenReturn(query);        
        when(query.setMaxResults(limit)).thenReturn(query);
        when(query.getResultList()).thenReturn(accessions1);
        
        List result = dao.findAll(clazz, strQuery, limit, conditions);
        
        verify(entityManager).createQuery(strQuery);
        verify(query).setMaxResults(limit);
        verify(query).getResultList();
        assertEquals(expResultSize, result.size());        
    }

    /**
     * Test of findById method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        
        when(entityManager.find(Accession.class, 20, LockModeType.OPTIMISTIC)).thenReturn(accession1);
        
        EntityBean result = dao.findById(20, Accession.class);
        verify(entityManager).find(Accession.class, 20, LockModeType.OPTIMISTIC);
        verify(entityManager).flush();
        assertSame(accession1, result);
    }
    
    @Test
    public void testFindByIdFailure() throws Exception {
        System.out.println("findById");
        
        when(entityManager.find(Accession.class, 20, LockModeType.OPTIMISTIC)).thenThrow(new OptimisticLockException());
        
        EntityBean result = dao.findById(20, Accession.class);
        verify(entityManager).find(Accession.class, 20, LockModeType.OPTIMISTIC);
        verify(entityManager).refresh(null);
        assertNull(result);
    }
    
    @Test
    public void testFindByIdFailure2() throws Exception {
        System.out.println("findById");
 
        doThrow(mock(DinaException.class)).when(entityManager).find(Accession.class, 20, LockModeType.OPTIMISTIC);
        
        EntityBean result = dao.findById(20, Accession.class); 
        assertNull(result);
    }

    /**
     * Test of findById method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindByIdForNoVisionEntities() throws Exception {
        System.out.println("testFindByIdForNoVisionEntities");
        
        Recordsetitem rt = new Recordsetitem();
        when(entityManager.find(Recordsetitem.class, 20, LockModeType.PESSIMISTIC_WRITE)).thenReturn(rt);
        
        EntityBean result = dao.findById(20, Recordsetitem.class);
        verify(entityManager).find(Recordsetitem.class, 20, LockModeType.PESSIMISTIC_WRITE);
        assertSame(rt, result);
    }

    /**
     * Test of findById method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindByIdForNoVisionEntities1() throws Exception {
        System.out.println("testFindByIdForNoVisionEntities");
        
        Sppermission sp = new Sppermission();
        when(entityManager.find(Sppermission.class, 20, LockModeType.PESSIMISTIC_WRITE)).thenReturn(sp);
        
        EntityBean result = dao.findById(20, Sppermission.class);
        verify(entityManager).find(Sppermission.class, 20, LockModeType.PESSIMISTIC_WRITE);
        assertSame(sp, result);
    }
    
    /**
     * Test of findById method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindByIdForNoVisionEntities2() throws Exception {
        System.out.println("testFindByIdForNoVisionEntities");
        
        Workbenchrow wb = new Workbenchrow();
        when(entityManager.find(Workbenchrow.class, 20, LockModeType.PESSIMISTIC_WRITE)).thenReturn(wb);
        
        EntityBean result = dao.findById(20, Workbenchrow.class);
        verify(entityManager).find(Workbenchrow.class, 20, LockModeType.PESSIMISTIC_WRITE);
        assertSame(wb, result);
    }

    /**
     * Test of findById method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindByIdForNoVisionEntities3() throws Exception {
        System.out.println("testFindByIdForNoVisionEntities");
        
        Workbenchdataitem wb = new Workbenchdataitem();
        when(entityManager.find(Workbenchdataitem.class, 20, LockModeType.PESSIMISTIC_WRITE)).thenReturn(wb);
        
        EntityBean result = dao.findById(20, Workbenchdataitem.class);
        verify(entityManager).find(Workbenchdataitem.class, 20, LockModeType.PESSIMISTIC_WRITE);
        assertSame(wb, result);
    }
    
    /**
     * Test of findById method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindByIdForNoVisionEntities4() throws Exception {
        System.out.println("testFindByIdForNoVisionEntities");
        
        Workbenchrowimage wb = new Workbenchrowimage();
        when(entityManager.find(Workbenchrowimage.class, 20, LockModeType.PESSIMISTIC_WRITE)).thenReturn(wb);
        
        EntityBean result = dao.findById(20, Workbenchrowimage.class);
        verify(entityManager).find(Workbenchrowimage.class, 20, LockModeType.PESSIMISTIC_WRITE);
        assertSame(wb, result);
    }
    
    /**
     * Test of findById method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindByIdForNoVisionEntities5() throws Exception {
        System.out.println("testFindByIdForNoVisionEntities");
        
        Geoname g = new Geoname();
        when(entityManager.find(Geoname.class, 20, LockModeType.PESSIMISTIC_WRITE)).thenReturn(g);
        
        EntityBean result = dao.findById(20, Geoname.class);
        verify(entityManager).find(Geoname.class, 20, LockModeType.PESSIMISTIC_WRITE);
        assertSame(g, result);
    }
 

    @Test
    public void testFindByStringId() throws Exception {
        System.out.println("testFindByStringId");
 
        when(entityManager.find(Accession.class, "20", LockModeType.NONE)).thenReturn(accession1);

        EntityBean result = dao.findByStringId("20", Accession.class);
        verify(entityManager).find(Accession.class, "20", LockModeType.NONE);
        verify(entityManager).flush();
        assertSame(accession1, result);
    }

    @Test
    public void testFindByStringIdFailure() throws Exception {
        System.out.println("testFindByStringId");

        when(entityManager.find(Accession.class, "20", LockModeType.NONE)).thenThrow(new OptimisticLockException());

        EntityBean result = dao.findByStringId("20", Accession.class);
        verify(entityManager).find(Accession.class, "20", LockModeType.NONE);
        verify(entityManager).refresh(null);
        verify(entityManager, times(0)).flush();
        assertNull(result);
    }

    @Test
    public void testFindByStringIdFailure2() throws Exception {
        System.out.println("testFindByStringId");

        doThrow(mock(DinaException.class)).when(entityManager).find(Accession.class, "20", LockModeType.NONE);
        EntityBean result = dao.findByStringId("20", Accession.class);
        assertNull(result); 
        verify(entityManager).find(Accession.class, "20", LockModeType.NONE); 
    }

    /**
     * Test of findByReference method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindByReference() throws Exception {
        System.out.println("findByReference");
        
        Accession expResult = accession1;
        
        when(entityManager.getReference(Accession.class, 20)).thenReturn(accession1);
        
        EntityBean acc = dao.findByReference(20, Accession.class);
        verify(entityManager).getReference(Accession.class, 20);
        assertEquals(expResult, acc);
    }

    /**
     * Test of create method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        
        EntityBean bean = new Accession(50);
        
        int expResult = 50;
        Accession result = (Accession) dao.create(bean);
        verify(entityManager).persist(bean);
        verify(entityManager).flush();        
        assertEquals(expResult, (int) result.getAccessionID());
    }

//    @Test
    public void testCreateFailure() throws Exception {
        System.out.println("create");

        EntityBean bean = new Accession(50);
        doThrow(mock(ConstraintViolationException.class)).when(entityManager).persist(bean);
         
        try {
            dao.create(bean);
            fail("Expected a DinaException to be thrown");
        } catch(DinaException e) {
            verify(entityManager).persist(bean); 
            verify(entityManager, times(0)).flush(); 
        }  
    }
 
//    @Test
    public void testCreateFailure2() throws Exception {
        System.out.println("create");

        EntityBean bean = new Accession(50);
         
        doThrow(mock(DinaException.class)).when(entityManager).persist(bean);
        dao.create(bean);
        verify(entityManager).persist(bean); 
        verify(entityManager, times(0)).flush();
    }
    
    /**
     * Test of merge method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testMerge() throws Exception {
        System.out.println("merge");
        
        accession1.setAccessionNumber("acc00060");
        String expResult = "acc00060";
        
        when(entityManager.merge(accession1)).thenReturn(accession1);
        Accession result = (Accession) dao.merge(accession1);
        
        verify(entityManager).merge(accession1);
        verify(entityManager).flush();
        assertEquals(expResult, result.getAccessionNumber());
    }

    @Test
    public void testMergeFailure1() throws Exception {
        System.out.println("merge");

        accession1.setAccessionNumber("acc00060");
    
        doThrow(mock(OptimisticLockException.class)).when(entityManager).merge(accession1);
        dao.merge(accession1);

        verify(entityManager).merge(accession1);  
        verify(entityManager, times(0)).flush();
    }

//    @Test
    public void testMergeFailure2() throws Exception {
        System.out.println("merge");
 
        accession1.setAccessionNumber("acc00060");
       
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
 
        Set<ConstraintViolation<Accession>> constraintViolations = validator.validate(accession1);
        ConstraintViolationException ce = new ConstraintViolationException(constraintViolations);
         
        when(entityManager.merge(accession1)).thenThrow(ce);
        try {
            dao.merge(accession1);
            fail("Expected a DinaException to be thrown");  
        } catch(DinaException e) {
            verify(entityManager).merge(accession1);
            verify(entityManager, times(0)).flush(); 
        } 
    }

    @Test
    public void testMergeFailure3() throws Exception {
        System.out.println("merge");

        accession1.setAccessionNumber("acc00060");
      
        doThrow(mock(DinaException.class)).when(entityManager).merge(accession1);
        dao.merge(accession1);
        verify(entityManager).merge(accession1);
        verify(entityManager, times(0)).flush(); 
    }

    /**
     * Test of updateByJPQL method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateByJPQL() throws Exception {
        System.out.println("updateByJPQL");
        
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE Accession a SET a.assectionNumber = 'acc1235'");
        sb.append(" WHERE a.accessionId = 20");
        
        when(entityManager.createQuery(sb.toString())).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);
        
        boolean result = dao.updateByJPQL(sb.toString());
        verify(entityManager).createQuery(sb.toString());
        verify(query).executeUpdate();
        assertTrue(result);
    }

        /**
     * Test of updateByJPQL method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateByJPQLFailure() throws Exception {
        System.out.println("updateByJPQL");
        
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE Accession a SET a.assectionNumber = 'acc1235'");
        sb.append(" WHERE a.accessionId = 20");
        
        when(entityManager.createQuery(sb.toString())).thenReturn(query);
        when(query.executeUpdate()).thenReturn(0);
        
        boolean result = dao.updateByJPQL(sb.toString());
        verify(entityManager).createQuery(sb.toString());
        verify(query).executeUpdate();
        assertFalse(result);
    }
    
    /**
     * Test of delete method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        
        dao.delete(accession1);
        
        verify(entityManager).remove(accession1);
        verify(entityManager).flush();
    }
    
        
    /**
     * Test of delete method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteFailure1() throws Exception {
        System.out.println("delete");
        
        doThrow(mock(ConstraintViolationException.class)).when(entityManager).remove(accession1);
        dao.delete(accession1);
        
        verify(entityManager).remove(accession1);
        verify(entityManager, times(0)).flush();
    }
    
        
    /**
     * Test of delete method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteFailure2() throws Exception {
        System.out.println("delete");
        
        doThrow(mock(DinaException.class)).when(entityManager).remove(accession1);
        dao.delete(accession1);
        
        verify(entityManager).remove(accession1);
        verify(entityManager, times(0)).flush();
    }

    /**
     * Test of getEntityByJPQL method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetEntityByJPQL() throws Exception {
        System.out.println("getEntityByJPQL");
        String jpql = "SELECT a FROM Accession WHERE a.accessionId = 2";
        
        when(entityManager.createQuery(jpql)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(accession1);
        
        Object expResult = accession1;
        EntityBean result = dao.getEntityByJPQL(jpql);
        verify(entityManager).createQuery(jpql);
        verify(query).getSingleResult();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEntityByJPQL method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetEntityByJPQLFailure1() throws Exception {
        System.out.println("getEntityByJPQL");
        String jpql = "SELECT a FROM Accession WHERE a.accessionId = 2";

        when(entityManager.createQuery(jpql)).thenReturn(query);
        doThrow(mock(NoResultException.class)).when(query).getSingleResult();

        EntityBean result = dao.getEntityByJPQL(jpql);
        verify(entityManager).createQuery(jpql);
        verify(query).getSingleResult();
        assertNull(result);
    }
    
    
    /**
     * Test of getEntityByJPQL method, of class DinaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetEntityByJPQLFailure2() throws Exception {
        System.out.println("getEntityByJPQL");
        String jpql = "SELECT a FROM Accession WHERE a.accessionId = 2";

        when(entityManager.createQuery(jpql)).thenReturn(query);
        doThrow(mock(NonUniqueResultException.class)).when(query).getSingleResult();

        EntityBean result = dao.getEntityByJPQL(jpql);
        verify(entityManager).createQuery(jpql);
        verify(query).getSingleResult();
        assertNull(result);
    }
    
    @Test
    public void testGetCountByQuery() {
        System.out.println("testGetCountByQuery");
         
        when(entityManager.createQuery(strQuery)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(20);
        
        int result = dao.getCountByQuery(strQuery);
        verify(entityManager).createQuery(strQuery);
        verify(query).getSingleResult();
        assertEquals(result, 20);
    }

    @Test
    public void testGetCountByQueryFailure() {
        System.out.println("testGetCountByQuery");

        when(entityManager.createQuery(strQuery)).thenReturn(query);
        doThrow(mock(DinaException.class)).when(query).getSingleResult();

        int result = dao.getCountByQuery(strQuery);
        verify(entityManager).createQuery(strQuery);
        verify(query).getSingleResult();
        assertEquals(result, 0);
    }
 

    private void preparaTestData() {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT a From Accession a ");        
        sb.append("WHERE a.accessionID");        
        sb.append(" BETWEEN ");
        sb.append(3);
        sb.append(" AND ");
        sb.append(8);
        strQuery = sb.toString();
        
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
