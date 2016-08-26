/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service;
   
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType; 
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.data.exceptions.DinaException;
import se.nrm.dina.logic.DinaDataLogic;
import se.nrm.dina.data.service.test.vo.TestEntity;
import se.nrm.dina.data.service.test.vo.TestEntityCollection; 

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)
public class DinaServiceNGTest {

    
    @Mock
    DinaDataLogic logic;
    
    private MockHttpResponse response;

    private DinaService instance;
    private Dispatcher dispatcher;
    private final String entity = "TestEntity"; 
    private TestEntityCollection list;
    private TestEntity testEntity;

    public DinaServiceNGTest() {
    }

    @Before
    public void setUp() throws Exception {
        instance = new DinaService(logic);

        dispatcher = MockDispatcherFactory.createDispatcher();
        dispatcher.getRegistry().addSingletonResource(instance);

        list = new TestEntityCollection();
    }

    @After
    public void tearDown() throws Exception {
        dispatcher = null;
        instance = null;
        testEntity = null;
        list = null;
    }

    @Test
    public void testDinaServiceConstractor() throws Exception {
        instance = new DinaService();
        assertNotNull(instance);
    }

    /**
     * Test of getAllByEntityName method, of class DinaService.
     *
     * @throws java.lang.Exception
     */
//    @Test
//    public void testGetAllByEntityName() throws Exception {
//        System.out.println("getAllByEntityName");
//
//        String orderby = "test";
//        List<String> orders = Arrays.asList(StringUtils.split(orderby, ","));
// 
//        when(logic.findAll(entity, 0, 0, 0, 0, "asc", orders, null)).thenReturn(list.getTestEntities());
//
//        MockHttpRequest request = MockHttpRequest.get("/v0/TestEntity?offset=0&limit=0&minid=0&maxid=0&orderby=test");
//        response = new MockHttpResponse();
//
//        dispatcher.invoke(request, response);
//        verify(logic).findAll(entity, 0, 0, 0, 0, "asc", orders, null);
//        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
//    }

    /**
     * Test of getAllByEntityName method, of class DinaService.
     *
     * @throws java.lang.Exception
     */
//    @Test
//    public void testGetAllByEntityName2() throws Exception {
//        System.out.println("getAllByEntityName");
//
//        String orderby = "";
//        List<String> orders = Arrays.asList(StringUtils.split(orderby, ","));
//
//        when(logic.findAll(entity, 0, 0, 0, 0, "asc", orders, null)).thenReturn(list.getTestEntities());
//
//        MockHttpRequest request = MockHttpRequest.get("/v0/TestEntity?offset=0&limit=0&minid=0&maxid=0");
//        response = new MockHttpResponse();
//
//        dispatcher.invoke(request, response);
//        verify(logic).findAll(entity, 0, 0, 0, 0, "asc", orders, null);
//        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
//    }

//    /**
//     * Test of getAllByEntityName method, of class DinaService.
//     *
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testGetAllByEntityNameFailure() throws Exception {
//        System.out.println("getAllByEntityName");
//
//        String orderby = "";
//        List<String> orders = Arrays.asList(StringUtils.split(orderby, ","));
//
//        when(logic.findAll(entity, 0, 0, 0, 0, "asc", orders, null)).thenThrow(new DinaException("error", 400));
//
//        MockHttpRequest request = MockHttpRequest.get("/v0/TestEntity?offset=0&limit=0&minid=0&maxid=0");
//        response = new MockHttpResponse();
//
//        dispatcher.invoke(request, response);
//        verify(logic).findAll(entity, 0, 0, 0, 0, "asc", orders, null);
//        assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());
//    }

//    /**
//     * Test of getData method, of class DinaService.
//     *
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testGetDataFailure() throws Exception {
//
//        System.out.println("getData");
//
//        MultivaluedMap<String, String> map = new MultivaluedHashMap();
//        when(logic.findAllBySearchCriteria(entity, map)).thenThrow(new DinaException("error", 400));
//
//        MockHttpRequest request = MockHttpRequest.get("/v0/TestEntity/search");
//        response = new MockHttpResponse();
//
//        dispatcher.invoke(request, response);
//        verify(logic).findAllBySearchCriteria(entity, map);
//        assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());
//    }

//    /**
//     * Test of getData method, of class DinaService.
//     *
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testGetData() throws Exception {
//
//        System.out.println("getData");
//
//        MultivaluedMap<String, String> map = new MultivaluedHashMap();
//        when(logic.findAllBySearchCriteria(entity, map)).thenReturn(list.getTestEntities());
//
//        MockHttpRequest request = MockHttpRequest.get("/v0/TestEntity/search");
//        response = new MockHttpResponse();
//
//        dispatcher.invoke(request, response);
//        verify(logic).findAllBySearchCriteria(entity, map);
//        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
//    }

//    /**
//     * Test of getEntityById method, of class DinaService.
//     *
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testGetEntityById() throws Exception {
//        System.out.println("getEntityById");
//
//        String id = "20";
//        TestEntity bean = new TestEntity();
//
//        when(logic.findById(id, entity)).thenReturn(bean);
//
//        MockHttpRequest request = MockHttpRequest.get("/v0/TestEntity/20");
//        response = new MockHttpResponse();
//
//        dispatcher.invoke(request, response);
//        verify(logic).findById(id, entity);
//
//        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
//    }

//    /**
//     * Test of getEntityById method, of class DinaService.
//     *
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testGetEntityByIdFailure() throws Exception {
//        System.out.println("getEntityById");
//
//        String id = "20";
//        when(logic.findById(id, entity)).thenThrow(new DinaException("error", 400));
//
//        MockHttpRequest request = MockHttpRequest.get("/v0/TestEntity/20");
//        response = new MockHttpResponse();
//
//        dispatcher.invoke(request, response);
//        verify(logic).findById(id, entity);
//
//        assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());
//    }

    /**
     * Test of getEntityCount method, of class DinaService.
     *
     * @throws java.lang.Exception
     */
//    @Test
    public void testGetEntityCount() throws Exception {
        System.out.println("getEntityCount");

        MockHttpRequest request = MockHttpRequest.get("/v0/TestEntity/count");
        response = new MockHttpResponse();

        when(logic.findEntityCount(entity)).thenReturn(10);

        dispatcher.invoke(request, response);
        verify(logic).findEntityCount(entity);

        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        assertEquals("10", response.getContentAsString());
    }

    /**
     * Test of getEntityCount method, of class DinaService.
     *
     * @throws java.lang.Exception
     */
//    @Test
    public void testGetEntityCountFailure() throws Exception {
        System.out.println("getEntityCount");

        MockHttpRequest request = MockHttpRequest.get("/v0/TestEntity/count");
        response = new MockHttpResponse();

        when(logic.findEntityCount(entity)).thenThrow(new DinaException("error", 400));

        dispatcher.invoke(request, response);
        verify(logic).findEntityCount(entity);

        assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());
    }

    /**
     * Test of createNewEntity method, of class DinaService.
     *
     * @throws java.lang.Exception
     */
//    @Test
    public void testCreateNewEntity() throws Exception {
        System.out.println("createNewEntity");

        MockHttpRequest request = MockHttpRequest.post("/v0/TestEntity");
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON_TYPE);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(testEntity);

        when(logic.createEntity(entity, jsonInString, 0)).thenReturn(testEntity);

        request.content(jsonInString.getBytes());

        response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        verify(logic).createEntity(entity, jsonInString, 0);
        Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    }

    /**
     * Test of createNewEntity method, of class DinaService.
     *
     * @throws java.lang.Exception
     */
//    @Test
    public void testCreateNewEntityFailure() throws Exception {
        System.out.println("createNewEntity");

        MockHttpRequest request = MockHttpRequest.post("/v0/TestEntity");
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON_TYPE);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(testEntity);

        when(logic.createEntity(entity, jsonInString, 0)).thenThrow(new DinaException("error", 400));

        request.content(jsonInString.getBytes());

        response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        verify(logic).createEntity(entity, jsonInString, 0);
        Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());
    }

    /**
     * Test of updateEntity method, of class DinaService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateEntity() throws Exception {
        System.out.println("updateEntity");

        MockHttpRequest request = MockHttpRequest.put("/v0/TestEntity");
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON_TYPE);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(testEntity);

        when(logic.updateEntity(entity, jsonInString)).thenReturn(testEntity);

        request.content(jsonInString.getBytes());

        response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        verify(logic).updateEntity(entity, jsonInString);
        Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    }

    /**
     * Test of updateEntity method, of class DinaService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateEntityFailure() throws Exception {
        System.out.println("updateEntity");

        MockHttpRequest request = MockHttpRequest.put("/v0/TestEntity");
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON_TYPE);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(testEntity);

        when(logic.updateEntity(entity, jsonInString)).thenThrow(new DinaException("error", 400));

        request.content(jsonInString.getBytes());

        response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        verify(logic).updateEntity(entity, jsonInString);
        Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());
    }

    /**
     * Test of deleteEntityById method, of class DinaService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteEntityById() throws Exception {
        System.out.println("deleteEntityById");

        MockHttpRequest request = MockHttpRequest.delete("/v0/TestEntity/20");
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON_TYPE);

        response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        verify(logic).deleteEntity(entity, 20);
        Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    }

    /**
     * Test of deleteEntityById method, of class DinaService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteEntityByIdFailure() throws Exception {
        System.out.println("deleteEntityById");

        MockHttpRequest request = MockHttpRequest.delete("/v0/TestEntity/20");
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON_TYPE);

        doThrow(new DinaException("error", 400)).when(logic).deleteEntity(entity, 20);
        response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        verify(logic).deleteEntity(entity, 20);
        Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());
    }
 
}
