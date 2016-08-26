/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service.vo;

import java.util.List;
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
public class MetadataBeanNGTest {
    
    public MetadataBeanNGTest() {
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
     * Test of getCallEndpoint method, of class MetadataBean.
     */
//    @Test
    public void testGetCallEndpoint() {
        System.out.println("getCallEndpoint");
        MetadataBean instance = new MetadataBean();
        String expResult = "";
        String result = instance.getCallEndpoint();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCallDate method, of class MetadataBean.
     */
//    @Test
    public void testGetCallDate() {
        System.out.println("getCallDate");
        MetadataBean instance = new MetadataBean();
        String expResult = "";
        String result = instance.getCallDate();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getApiVersion method, of class MetadataBean.
     */
//    @Test
    public void testGetApiVersion() {
        System.out.println("getApiVersion");
        MetadataBean instance = new MetadataBean();
        String expResult = "";
        String result = instance.getApiVersion();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCallEndpoint method, of class MetadataBean.
     */
//    @Test
    public void testSetCallEndpoint() {
        System.out.println("setCallEndpoint");
        String callEndpoint = "";
        MetadataBean instance = new MetadataBean();
        instance.setCallEndpoint(callEndpoint);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCallDate method, of class MetadataBean.
     */
//    @Test
    public void testSetCallDate() {
        System.out.println("setCallDate");
        String callDate = "";
        MetadataBean instance = new MetadataBean();
        instance.setCallDate(callDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setApiVersion method, of class MetadataBean.
     */
//    @Test
    public void testSetApiVersion() {
        System.out.println("setApiVersion");
        String apiVersion = "";
        MetadataBean instance = new MetadataBean();
        instance.setApiVersion(apiVersion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSupportedLanguages method, of class MetadataBean.
     */
//    @Test
    public void testGetSupportedLanguages() {
        System.out.println("getSupportedLanguages");
        MetadataBean instance = new MetadataBean();
        List expResult = null;
        List result = instance.getSupportedLanguages();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSupportedLanguages method, of class MetadataBean.
     */
//    @Test
    public void testSetSupportedLanguages() {
        System.out.println("setSupportedLanguages");
        List<String> supportedLanguages = null;
        MetadataBean instance = new MetadataBean();
        instance.setSupportedLanguages(supportedLanguages);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLimit method, of class MetadataBean.
     */
//    @Test
    public void testGetLimit() {
        System.out.println("getLimit");
        MetadataBean instance = new MetadataBean();
        int expResult = 0;
        int result = instance.getLimit();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLimit method, of class MetadataBean.
     */
//    @Test
    public void testSetLimit() {
        System.out.println("setLimit");
        int limit = 0;
        MetadataBean instance = new MetadataBean();
        instance.setLimit(limit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderBy method, of class MetadataBean.
     */
//    @Test
    public void testGetOrderBy() {
        System.out.println("getOrderBy");
        MetadataBean instance = new MetadataBean();
        List expResult = null;
        List result = instance.getOrderBy();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrderBy method, of class MetadataBean.
     */
//    @Test
    public void testSetOrderBy() {
        System.out.println("setOrderBy");
        List<String> orderBy = null;
        MetadataBean instance = new MetadataBean();
        instance.setOrderBy(orderBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSortOrder method, of class MetadataBean.
     */
//    @Test
    public void testGetSortOrder() {
        System.out.println("getSortOrder");
        MetadataBean instance = new MetadataBean();
        String expResult = "";
        String result = instance.getSortOrder();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSortOrder method, of class MetadataBean.
     */
//    @Test
    public void testSetSortOrder() {
        System.out.println("setSortOrder");
        String sortOrder = "";
        MetadataBean instance = new MetadataBean();
        instance.setSortOrder(sortOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaintenanceContact method, of class MetadataBean.
     */
//    @Test
    public void testGetMaintenanceContact() {
        System.out.println("getMaintenanceContact");
        MetadataBean instance = new MetadataBean();
        String expResult = "";
        String result = instance.getMaintenanceContact();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaintenanceContact method, of class MetadataBean.
     */
//    @Test
    public void testSetMaintenanceContact() {
        System.out.println("setMaintenanceContact");
        String maintenanceContact = "";
        MetadataBean instance = new MetadataBean();
        instance.setMaintenanceContact(maintenanceContact);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatusCode method, of class MetadataBean.
     */
//    @Test
    public void testGetStatusCode() {
        System.out.println("getStatusCode");
        MetadataBean instance = new MetadataBean();
        int expResult = 0;
        int result = instance.getStatusCode();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatusCode method, of class MetadataBean.
     */
//    @Test
    public void testSetStatusCode() {
        System.out.println("setStatusCode");
        int statusCode = 0;
        MetadataBean instance = new MetadataBean();
        instance.setStatusCode(statusCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffset method, of class MetadataBean.
     */
//    @Test
    public void testGetOffset() {
        System.out.println("getOffset");
        MetadataBean instance = new MetadataBean();
        int expResult = 0;
        int result = instance.getOffset();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffset method, of class MetadataBean.
     */
//    @Test
    public void testSetOffset() {
        System.out.println("setOffset");
        int offset = 0;
        MetadataBean instance = new MetadataBean();
        instance.setOffset(offset);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResultCount method, of class MetadataBean.
     */
//    @Test
    public void testGetResultCount() {
        System.out.println("getResultCount");
        MetadataBean instance = new MetadataBean();
        int expResult = 0;
        int result = instance.getResultCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResultCount method, of class MetadataBean.
     */
//    @Test
    public void testSetResultCount() {
        System.out.println("setResultCount");
        int resultCount = 0;
        MetadataBean instance = new MetadataBean();
        instance.setResultCount(resultCount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrevious method, of class MetadataBean.
     */
//    @Test
    public void testGetPrevious() {
        System.out.println("getPrevious");
        MetadataBean instance = new MetadataBean();
        String expResult = "";
        String result = instance.getPrevious();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrevious method, of class MetadataBean.
     */
//    @Test
    public void testSetPrevious() {
        System.out.println("setPrevious");
        String previous = "";
        MetadataBean instance = new MetadataBean();
        instance.setPrevious(previous);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNext method, of class MetadataBean.
     */
//    @Test
    public void testGetNext() {
        System.out.println("getNext");
        MetadataBean instance = new MetadataBean();
        String expResult = "";
        String result = instance.getNext();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNext method, of class MetadataBean.
     */
//    @Test
    public void testSetNext() {
        System.out.println("setNext");
        String next = "";
        MetadataBean instance = new MetadataBean();
        instance.setNext(next);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResultLanguages method, of class MetadataBean.
     */
//    @Test
    public void testGetResultLanguages() {
        System.out.println("getResultLanguages");
        MetadataBean instance = new MetadataBean();
        List expResult = null;
        List result = instance.getResultLanguages();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResultLanguages method, of class MetadataBean.
     */
//    @Test
    public void testSetResultLanguages() {
        System.out.println("setResultLanguages");
        List<String> resultLanguages = null;
        MetadataBean instance = new MetadataBean();
        instance.setResultLanguages(resultLanguages);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContentLicenses method, of class MetadataBean.
     */
//    @Test
    public void testGetContentLicenses() {
        System.out.println("getContentLicenses");
        MetadataBean instance = new MetadataBean();
        List expResult = null;
        List result = instance.getContentLicenses();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContentLicenses method, of class MetadataBean.
     */
//    @Test
    public void testSetContentLicenses() {
        System.out.println("setContentLicenses");
        List<String> contentLicenses = null;
        MetadataBean instance = new MetadataBean();
        instance.setContentLicenses(contentLicenses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessage method, of class MetadataBean.
     */
//    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        MetadataBean instance = new MetadataBean();
        String expResult = "";
        String result = instance.getMessage();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessage method, of class MetadataBean.
     */
//    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        String message = "";
        MetadataBean instance = new MetadataBean();
        instance.setMessage(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
