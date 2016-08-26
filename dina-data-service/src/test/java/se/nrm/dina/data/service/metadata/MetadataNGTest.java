/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service.metadata;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.nrm.dina.data.service.vo.MetadataBean;

/**
 *
 * @author idali
 */
public class MetadataNGTest {
    
    public MetadataNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of buildMetadata method, of class Metadata.
     */
//    @Test
    public void testBuildMetadata_HttpServletRequest_String() {
        System.out.println("buildMetadata");
        HttpServletRequest req = null;
        String entity = "";
        Metadata instance = new Metadata();
        MetadataBean expResult = null;
        MetadataBean result = instance.buildMetadata(req, entity);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildMetadata method, of class Metadata.
     */
//    @Test
    public void testBuildMetadata_9args() {
        System.out.println("buildMetadata");
        HttpServletRequest req = null;
        String entity = "";
        int offset = 0;
        int limit = 0;
        String sortOrder = "";
        List<String> order = null;
        String orderby = "";
        boolean isExact = false;
        Map<String, String> condition = null;
        Metadata instance = new Metadata();
        MetadataBean expResult = null;
        MetadataBean result = instance.buildMetadata(req, entity, offset, limit, sortOrder, order, orderby, isExact, condition);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
