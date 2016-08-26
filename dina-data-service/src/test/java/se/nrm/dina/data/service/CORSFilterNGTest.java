/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;   
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;  
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)
public class CORSFilterNGTest {

    @Mock
    ContainerRequestContext crc;
    
    @Mock
    ContainerResponseContext cres;
    
    public CORSFilterNGTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
 

    /**
     * Test of filter method, of class CORSFilter.
     * @throws java.lang.Exception
     */
    @Test
    public void testFilter() throws Exception {
        
        System.out.println("filter");
         
        MultivaluedMap<String, Object> map = new MultivaluedHashMap<>();
        when(cres.getHeaders()).thenReturn(map);
         
        CORSFilter instance = new CORSFilter(); 
        instance.filter(crc, cres); 
         
        verify(cres, times(4)).getHeaders();  
    } 
}
