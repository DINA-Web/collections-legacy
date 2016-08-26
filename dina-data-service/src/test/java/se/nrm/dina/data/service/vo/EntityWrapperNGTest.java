/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service.vo;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author idali
 */
public class EntityWrapperNGTest {
    
    public EntityWrapperNGTest() {
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
     * Test of getMeta method, of class EntityWrapper.
     */
//    @Test
    public void testGetMeta() {
        System.out.println("getMeta");
        EntityWrapper instance = null;
        MetadataBean expResult = null;
        MetadataBean result = instance.getMeta();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class EntityWrapper.
     */
//    @Test
    public void testGetData() {
        System.out.println("getData");
        EntityWrapper instance = null;
        Object expResult = null;
        Object result = instance.getData();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
