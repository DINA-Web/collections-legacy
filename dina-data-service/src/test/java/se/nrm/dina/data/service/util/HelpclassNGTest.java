/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service.util;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest; 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;   
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import se.nrm.dina.data.service.vo.EntityCount;
import se.nrm.dina.data.service.vo.EntityWrapper;
import se.nrm.dina.data.service.vo.MetadataBean;
import se.nrm.dina.data.vo.ErrorBean;
import se.nrm.dina.datamodel.EntityBean;
import se.nrm.dina.datamodel.impl.Testentity;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)
public class HelpclassNGTest {
    
    @Mock
    HttpServletRequest req;
    
    private static Helpclass instance;
    
    public HelpclassNGTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    /**
     * Test of getInstance method, of class Helpclass.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        
        instance = null;
        Helpclass result = Helpclass.getInstance();
        assertNotNull(result); 
    }

    /**
     * Test of buildEntityWrapper method, of class Helpclass.
     */
    @Test
    public void testBuildEntityWrapper_4args() {
        System.out.println("buildEntityWrapper");
        
        ErrorBean error = new ErrorBean();
        MetadataBean meta = new MetadataBean();
        
        int statusCode = 200;
        int resultCount = 20;
        
        instance = new Helpclass(); 
        EntityWrapper result = instance.buildEntityWrapper(error, meta, statusCode, resultCount); 
        
        assertNotNull(result);
        assertEquals(result.getData(), error);
        assertEquals(result.getMeta(), meta);
        assertEquals(result.getMeta().getResultCount(), resultCount);
        assertEquals(result.getMeta().getStatusCode(), 200);
    }

    /**
     * Test of buildEntityWrapper method, of class Helpclass.
     */
    @Test
    public void testBuildEntityWrapper_3args_1() {
        System.out.println("buildEntityWrapper");
        
        List<EntityBean> entityBeans = new ArrayList<>();
        
        EntityBean theEntity = new Testentity(1);
        entityBeans.add(theEntity);
        
        theEntity = new Testentity(2);
        entityBeans.add(theEntity);
        
        MetadataBean meta = new MetadataBean();
        int statusCode = 200;
   
        instance = new Helpclass(); 
        EntityWrapper result = instance.buildEntityWrapper(entityBeans, meta, statusCode);
        assertNotNull(result); 
         
        assertEquals(result.getMeta(), meta); 
        assertEquals(result.getMeta().getStatusCode(), 200);
        assertEquals(result.getMeta().getResultCount(), 2);
    }
    
    /**
     * Test of buildEntityWrapper method, of class Helpclass.
     */
    @Test
    public void testBuildEntityWrapperNoResult() {
        System.out.println("buildEntityWrapper");
        
        List<EntityBean> entityBeans = null;
         
        MetadataBean meta = new MetadataBean();
        int statusCode = 200;
   
        instance = new Helpclass(); 
        EntityWrapper result = instance.buildEntityWrapper(entityBeans, meta, statusCode);
        assertNotNull(result); 
         
        assertEquals(result.getMeta(), meta); 
        assertEquals(result.getMeta().getStatusCode(), 200);
        assertEquals(result.getMeta().getResultCount(), 0);
    }
     
    /**
     * Test of buildEntityWrapper method, of class Helpclass.
     */
    @Test
    public void testBuildEntityWrapper_3args_2() {
        System.out.println("buildEntityWrapper");
        
        EntityBean entityBean = new Testentity(20);
        MetadataBean meta = new MetadataBean();
        int statusCode = 200;
        
        instance = new Helpclass(); 
        EntityWrapper result = instance.buildEntityWrapper(entityBean, meta, statusCode);
        assertNotNull(result); 
        assertEquals(result.getMeta(), meta);
        assertEquals(result.getMeta().getResultCount(), 1);
        assertEquals(result.getMeta().getStatusCode(), statusCode);
    }
    
         
    /**
     * Test of buildEntityWrapper method, of class Helpclass.
     */
    @Test
    public void testBuildEntityWrapper_3argsNoResult() {
        System.out.println("buildEntityWrapper");
        
        EntityBean entityBean = null;
        MetadataBean meta = new MetadataBean();
        int statusCode = 200;
        
        instance = new Helpclass(); 
        EntityWrapper result = instance.buildEntityWrapper(entityBean, meta, statusCode);
        assertNotNull(result); 
        assertEquals(result.getMeta(), meta); 
        assertEquals(result.getMeta().getStatusCode(), statusCode);
    }

    /**
     * Test of buildEntityWrapper method, of class Helpclass.
     */
    @Test
    public void testBuildEntityWrapper_3args_3() {
        System.out.println("buildEntityWrapper");
        
        EntityCount entityCount = new EntityCount(8);
        MetadataBean meta = new MetadataBean();
        int statusCode = 200;
        
        instance = new Helpclass(); 
        EntityWrapper result = instance.buildEntityWrapper(entityCount, meta, statusCode);
        assertNotNull(result); 
        assertEquals(result.getMeta(), meta);
        assertEquals(result.getMeta().getResultCount(), 1);
        assertEquals(result.getMeta().getStatusCode(), statusCode);
    }

    /**
     * Test of buildBaseEndPoint method, of class Helpclass.
     */
    @Test
    public void testBuildBaseEndPoint() {
        System.out.println("buildBaseEndPoint");
 
        String entity = "Testentity";
        
        when(req.getRequestURI()).thenReturn("testUri");
         
        instance = new Helpclass();
        String expResult = "https://beta-api.dina-web.nettestUri";
        String result = instance.buildBaseEndPoint(req, entity);
        
        System.out.println("result " + result);
        assertEquals(result, expResult); 
    }
    
}
