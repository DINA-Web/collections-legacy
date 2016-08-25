/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.logic.util;

import java.sql.Date;
import java.time.LocalDateTime; 
import java.util.List;
import org.junit.After;
import org.junit.Before;
import static org.testng.Assert.*; 
import org.testng.annotations.Test;

/**
 *
 * @author idali
 */
public class HelpClassNGTest {
    
    private HelpClass instance;
    
    public HelpClassNGTest() {
    }

    @Before 
    public static void setUpClass() throws Exception {
    }

    @After 
    public static void tearDownClass() throws Exception {
    }

 

    /**
     * Test of getInstance method, of class HelpClass.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
 
        HelpClass result = HelpClass.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of getTodaysDate method, of class HelpClass.
     */
    @Test
    public void testGetTodaysDate() {
        System.out.println("getTodaysDate");
         
        instance = HelpClass.getInstance();
        Date result = instance.getTodaysDate();
        
        java.util.Date today = new java.util.Date();
        Date expResult = new java.sql.Date(today.getTime()); 
        assertEquals(expResult.getYear(), result.getYear()); 
        assertEquals(expResult.getMonth(), result.getMonth()); 
        assertEquals(expResult.getDay(), result.getDay()); 
        assertEquals(expResult.getDate(), result.getDate()); 
    }

    /**
     * Test of dateTimeToString method, of class HelpClass.
     */
    @Test
    public void testDateTimeToString() {
        System.out.println("dateTimeToString");
        LocalDateTime date = LocalDateTime.now(); 
         
        instance = HelpClass.getInstance();
        String result = instance.dateTimeToString(date);
        assertNotNull(result);
    }
    
    @Test
    public void testDateTimeToStringNull() {
        System.out.println("dateTimeToString");
        LocalDateTime date = null; 
         
        instance = HelpClass.getInstance();
        String result = instance.dateTimeToString(date);
        assertNull(result);
    }

    /**
     * Test of getMaintanceContact method, of class HelpClass.
     */
    @Test
    public void testGetMaintanceContact() {
        System.out.println("getMaintanceContact");
        
        instance = new HelpClass();
        String expResult = "admin@dina-system.org";
        String result = instance.getMaintanceContact();
        assertEquals(result, expResult); 
    }

    /**
     * Test of getSupportedLanguages method, of class HelpClass.
     */
    @Test
    public void testGetSupportedLanguages() {
        System.out.println("getSupportedLanguages");
        instance = new HelpClass(); 
        List result = instance.getSupportedLanguages();
        assertNotNull(result);
        assertEquals(result.size(), 2);
    }

    /**
     * Test of isNumric method, of class HelpClass.
     */
    @Test
    public void testIsNumric() {
        System.out.println("isNumric");
   
        String s = "20";
        instance = new HelpClass(); 
        boolean result = instance.isNumric(s);
        assertTrue(result);
    }
    
    @Test
    public void testIsNumricFalse() {
        System.out.println("isNumric");
   
        String s = "s";
        instance = new HelpClass(); 
        boolean result = instance.isNumric(s);
        assertFalse(result);
    }

    /**
     * Test of strToInt method, of class HelpClass.
     */
    @Test
    public void testStrToInt() {
        System.out.println("strToInt");
        String s = "20";
        instance = new HelpClass();
   
        int result = instance.strToInt(s);
        assertEquals(result, 20); 
    }
    
    @Test
    public void testStrToIntNull() {
        System.out.println("strToInt"); 
        
        String s = null;
        instance = new HelpClass();
   
        int result = instance.strToInt(s);
        assertEquals(result, 0); 
    }
    
    @Test
    public void testStrToIntFailure() {
        System.out.println("strToInt"); 
        
        String s = "s";
        instance = new HelpClass();
   
        int result = instance.strToInt(s);
        assertEquals(result, 0); 
    }

    /**
     * Test of strToBoolean method, of class HelpClass.
     */
    @Test
    public void testStrToBoolean() {
        System.out.println("strToBoolean");
        String s = "true";
        instance = new HelpClass();
 
        boolean result = instance.strToBoolean(s);
        assertTrue(result); 
    }
    
    @Test
    public void testStrToBooleanNull() {
        System.out.println("strToBoolean");
        String s = null;
        instance = new HelpClass();
 
        boolean result = instance.strToBoolean(s);
        assertFalse(result); 
    }
    
    @Test
    public void testStrToBooleanEmpty() {
        System.out.println("strToBoolean");
        String s = "";
        instance = new HelpClass();
 
        boolean result = instance.strToBoolean(s);
        assertFalse(result); 
    }
    
    @Test
    public void testStrToBooleanFailure() {
        System.out.println("strToBoolean");
        String s = "sss";
        instance = new HelpClass();
 
        boolean result = instance.strToBoolean(s);
        assertFalse(result); 
    }
    
        
    @Test
    public void testStrToBooleanFailure1() {
        System.out.println("strToBoolean");
        String s = "1";
        instance = new HelpClass();
 
        boolean result = instance.strToBoolean(s);
        assertFalse(result); 
    }

    /**
     * Test of getSort method, of class HelpClass.
     */
    @Test
    public void testGetSort() {
        System.out.println("getSort");
        String s = "asc";
        instance = new HelpClass();
        String expResult = "ASC";
        String result = instance.getSort(s);
        assertEquals(result, expResult); 
    }
    
    @Test
    public void testGetSort1() {
        System.out.println("getSort");
        String s = "desc";
        instance = new HelpClass();
        String expResult = "DESC";
        String result = instance.getSort(s);
        assertEquals(result, expResult); 
    }
    
    @Test
    public void testGetSortNull() {
        System.out.println("getSort");
        String s = null;
        instance = new HelpClass();
        String expResult = "ASC";
        String result = instance.getSort(s);
        assertEquals(result, expResult); 
    }
    
    @Test
    public void testGetSort2() {
        System.out.println("getSort");
        String s = "dddd";
        instance = new HelpClass();
        String expResult = "ASC";
        String result = instance.getSort(s);
        assertEquals(result, expResult); 
    }
}
