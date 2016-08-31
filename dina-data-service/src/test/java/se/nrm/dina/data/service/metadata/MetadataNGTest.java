/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service.metadata;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest; 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import static org.testng.Assert.*; 
import se.nrm.dina.data.service.vo.MetadataBean;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)
public class MetadataNGTest {
    
    @Mock
    HttpServletRequest req;
    
    private String entity;
    
    private Metadata instance;
    
    public MetadataNGTest() {
        
    }

    @Before
    public void setUp() throws Exception { 
        instance = new Metadata();
        entity = "Testentity";
    }

    @After
    public void tearDown() throws Exception { 
        instance = null;
    }


    /**
     * Test of buildMetadata method, of class Metadata.
     */
    @Test
    public void testBuildMetadata_HttpServletRequest_String() {
        
        System.out.println("buildMetadata");
    
        when(req.getRequestURI()).thenReturn("/collections/v0/Testentity");
        when(req.getContextPath()).thenReturn("/collections");
         
        MetadataBean result = instance.buildMetadata(req, entity);
        assertNotNull(result); 
        assertEquals("https://beta-api.dina-web.net/collections/v0/Testentity", result.getCallEndpoint()); 
        assertEquals("0", result.getApiVersion());
    }

    /**
     * Test of buildMetadata method, of class Metadata.
     */
    @Test
    public void testBuildMetadata_9args() {
        
        System.out.println("buildMetadata");
        
        when(req.getRequestURI()).thenReturn("/collections/v0/Testentity");
 
        int offset = 20;
        int limit = 20;
        String sortOrder = "";
        List<String> order = null;
        String orderby = "";
        boolean isExact = false;
        Map<String, String> condition = null;
        
        instance = new Metadata();
        
        MetadataBean result = instance.buildMetadata(req, entity, offset, limit, sortOrder, order, orderby, isExact, condition);
        assertNotNull(result); 
    }
    
}
