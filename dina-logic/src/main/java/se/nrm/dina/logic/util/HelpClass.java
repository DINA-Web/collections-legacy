/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.logic.util;
   
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;



/**
 *
 * @author idali
 */
public class HelpClass {
    
    private static HelpClass instance = null;
     
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' HH.mm.ss");
    private final String MAINTENANCE_CONTACT = "admin@dina-system.org";
    private final List<String> supportedLanguages;
    
    HelpClass() {
        supportedLanguages = new ArrayList<>();
        supportedLanguages.add("GB_en"); 
        supportedLanguages.add("SE_sv");
    }  
    
    
    public static synchronized HelpClass getInstance() {
        if (instance == null) {
            instance = new HelpClass();
        }
        return instance;
    }

    public String dateTimeToString(LocalDateTime date) {
        return date == null ? null : date.format(FORMATTER);
    }

    
    
    public java.sql.Date getTodaysDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
    
    public String getMaintanceContact() {
        return MAINTENANCE_CONTACT;
    }
 
    
    public List<String> getSupportedLanguages() {
       return supportedLanguages;
    } 
    
        /**
     * Checks if the String is numeric
     * @param s
     * @return 
     */    
    public boolean isNumric(String s) { 
        return StringUtils.isNumeric(s);
    } 
    
    public int strToInt(String s) {
        
        if(s == null) {
            return 0;
        }
        try {
            return Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return 0;
        } 
    } 
    
    public boolean strToBoolean(String s) {
        
        if(s == null || s.isEmpty()) {
            return false;
        } 
        try {
            return Boolean.valueOf(s.toLowerCase());
        } catch(Exception e) {
            return false;
        }
    }

    public String getSort(String s) {
        if(s == null) {
            return "ASC";
        }

        if (s.equalsIgnoreCase("asc") || s.equalsIgnoreCase("desc")) {
            return s.toUpperCase(); 
        } else {
            return "ASC";
        }
    }
}
