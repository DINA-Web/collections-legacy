/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.datamodel.util;
 
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import org.junit.After;
import org.junit.Before; 
import org.junit.Test;

/**
 *
 * @author idali
 */
public class UtilNGTest {
    
    private Util testInstance;
    
    public UtilNGTest() {
    }

    @Before 
    public void setUpClass() throws Exception {
    }

    @After 
    public void tearDownClass() throws Exception {
    }
 
    /**
     * Test of getInstance method, of class Util.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
         
        Util result = Util.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of getURLLink method, of class Util.
     */
    @Test
    public void testGetURLLink() {
        System.out.println("getURLLink");
        
        String entityName = "Accession";
        
        testInstance = new Util();
         
        String expResult = "https://beta-api.dina-web.net/collections/v0/Accession/";
         
        String result = testInstance.getURLLink(entityName);
        assertEquals(result, expResult); 
    }
    
}
