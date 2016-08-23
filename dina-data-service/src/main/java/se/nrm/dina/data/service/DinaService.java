/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service;
    
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.List;   
import java.util.Map; 
import java.util.function.Predicate; 
import java.util.stream.Collectors;
import javax.ejb.EJB; 
import javax.ejb.Stateless; 
import javax.servlet.http.HttpServletRequest;  
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context; 
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.commons.lang.StringUtils;  
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.nrm.dina.data.exceptions.DinaConstraintViolationException;
import se.nrm.dina.data.exceptions.DinaException;  
import se.nrm.dina.data.service.metadata.Metadata;
import se.nrm.dina.data.service.util.Helpclass; 
import se.nrm.dina.data.service.vo.EntityCount; 
import se.nrm.dina.data.service.vo.MetadataBean;
import se.nrm.dina.data.vo.ErrorBean;
import se.nrm.dina.datamodel.EntityBean;
import se.nrm.dina.logic.DinaDataLogic;
 
/**
 *
 * @author idali
 */
@Path("/v0")
@Consumes({MediaType.APPLICATION_JSON+";charset=UTF-8"})
@Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
@Stateless
public class DinaService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @EJB
    private DinaDataLogic logic;
 

    public DinaService() {

    }
    
    public DinaService(DinaDataLogic logic) {
        this.logic = logic;
    }
    
    /**
     * Get all the records from database by the name of the entity
     * 
     * @param req       - HttpServletRequeset
     * @param entity    - Entity name
     * @param offset    - paging offset
     * @param limit     - The amount of records to return 
     * @param sort      - Sort order [ASC or DESC]
     * @param orderby   - Sort order by list of fields
     * @return Response
     */
    @GET
    @Path("{entity}") 
    public Response getAllByEntityName (@Context HttpServletRequest req,
                                        @PathParam("entity") String entity, 
                                        @DefaultValue("0") @QueryParam("offset") int offset, 
                                        @DefaultValue("50") @QueryParam("limit") int limit, 
                                        @DefaultValue("asc") @QueryParam("sort") String sort,
                                        @QueryParam("orderby") String orderby) {
        
        logger.info("getAllByEntityName : {} -- {}", entity, offset + " -- " + limit);  
         
        List<String> order = new ArrayList(); 
        if(orderby != null) {
            order = Arrays.asList(StringUtils.split(orderby, ","));
        }

        Metadata metadata = new Metadata();
        MetadataBean meta = metadata.buildMetadata(req, entity, offset, limit, sort, order, orderby, true, null);
 
        try {
            List<EntityBean> results = logic.findAll(entity, limit, offset, sort, order); 
            
            return Response.ok(Helpclass.getInstance().buildEntityWrapper(results, meta, 200)).build();
        } catch (DinaException e) {  
            ErrorBean error = new ErrorBean(entity, e.getMessage()); 
            return Response.status(e.getErrorCode()).entity(Helpclass.getInstance().buildEntityWrapper(error, meta, e.getErrorCode(), 0)).build();
        }
    }

    
    /**
     * Get all the records from database by the name of the entity and queries
     * @param req           - HttpServletRequest
     * @param entity        - the name of the entity
     * @param info          - QueryParameters
     * @return Response
     */
    @GET
    @Path("{entity}/search")
    public Response getData(@Context HttpServletRequest req, @PathParam("entity") String entity, @Context UriInfo info) {

        logger.info("getData : {} -- {}", entity, info);
        
        MultivaluedMap<String, String> map = info.getQueryParameters();
        int offset = se.nrm.dina.logic.util.HelpClass.getInstance().strToInt(map.getFirst("offset"));
        int limit = se.nrm.dina.logic.util.HelpClass.getInstance().strToInt(map.getFirst("limit")); 
        String orderBy = map.getFirst("orderby");
        String sort = map.getFirst("sort");
        boolean exact =  se.nrm.dina.logic.util.HelpClass.getInstance().strToBoolean(map.getFirst("exact"));
        logger.info("is exact search : {}", exact);
        
        List<String> order = new ArrayList<>();
        if (orderBy != null) {
            order = Arrays.asList(orderBy.split(","));
        }
        
        Map<String, String> condition = map.entrySet()
                .stream()
                .filter(filterCondition())
                .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue().get(0)));

        Metadata metadata = new Metadata(); 
        MetadataBean meta = metadata.buildMetadata(req, entity, offset, limit == 0 ? 50 : limit, sort == null ? "asc" : sort, order, orderBy, exact, condition); 
        try {
            List<EntityBean> results = logic.findAllBySearchCriteria(entity, limit, offset, sort, order, condition, exact);
            return Response.ok(Helpclass.getInstance().buildEntityWrapper(results, meta, 200)).build();  
        } catch (DinaException e) { 
            ErrorBean error = new ErrorBean(entity, e.getMessage()); 
            return Response.status(e.getErrorCode()).entity(Helpclass.getInstance().buildEntityWrapper(error, meta, e.getErrorCode(), 0)).build(); 
        }
    } 
    
        
    private Predicate<Map.Entry<String, List<String>>> filterCondition() {
        return s -> !s.getKey().equals("offset")
                && !s.getKey().equals("limit")
//                && !s.getKey().equals("minid")
//                && !s.getKey().equals("maxid")
                && !s.getKey().equals("orderby")
                && !s.getKey().equals("exact")
                && !s.getKey().equals("sort");
    }
        
    /**
     * Get single record frome database by the name of the entity and it's id
     * This method passes in a PathParam entity class name and entity id 
     *  
     * @param req
     * @param entity - class name of the entity
     * @param id - entity id
     * 
     * @return entity 
     */
    @GET
    @Path("{entity}/{id}/") 
    public Response getEntityById(@Context HttpServletRequest req,
                                  @PathParam("entity") String entity, 
                                  @PathParam("id") String id) {
        
        logger.info("getEntityById - entity: {}, id :  {}", entity, id); 
           
        Metadata metadata = new Metadata();
        MetadataBean meta = metadata.buildMetadata(req, entity);
        try {     
            EntityBean result = logic.findById(id, entity);
            return Response.ok(Helpclass.getInstance().buildEntityWrapper(result, meta, 200)).build();   
        } catch (DinaException e) {
            ErrorBean error = new ErrorBean(entity, e.getMessage()); 
            return Response.status(e.getErrorCode()).entity(Helpclass.getInstance().buildEntityWrapper(error, meta, e.getErrorCode(), 0)).build();  
        }
    }
    


    /**
     * Get a list of records frome database by the name of the entity and a list of corresponding ids. 
     *
     * @param req       - HttpServletRequest
     * @param entity    - The name of the entity
     * @param ids       - List of ids separated by ','
     * 
     * @return Response 
     */
    @GET
    @Path("{entity}/search/{ids}/") 
    public Response getEntityByIds(@Context HttpServletRequest req,
                                    @PathParam("entity") String entity, @PathParam("ids") String ids) {
        
        logger.info("getEntityByIds - entity: {}, id :  {}", entity, ids);
      
        Metadata metadata = new Metadata();
        MetadataBean meta = metadata.buildMetadata(req, entity);
        try {     
            List<EntityBean> results = logic.findEntitiesByids(entity, ids);
            return Response.ok(Helpclass.getInstance().buildEntityWrapper(results, meta, 200)).build();   
        } catch (DinaException e) {
            ErrorBean error = new ErrorBean(entity, e.getMessage()); 
            return Response.status(e.getErrorCode()).entity(Helpclass.getInstance().buildEntityWrapper(error, meta, e.getErrorCode(), 0)).build();  
        }
    }
 

    /**
     * Get total count of records in the database for a given entity by its name   
     * 
     * @param req       - HttpServletRequest
     * @param entity    - Class name of the entity 
     * 
     * @return Response 
     */
    @GET
    @Path("{entity}/count")  
    public Response getEntityCount(@Context HttpServletRequest req, @PathParam("entity") String entity ) {
        
        logger.info("getEntityById - entity: {} ", entity );

        Metadata metadata = new Metadata();
        MetadataBean meta = metadata.buildMetadata(req, entity);
        try {  
            int count = logic.findEntityCount(entity); 
            EntityCount entityCount = new EntityCount(count);
            return Response.ok(Helpclass.getInstance().buildEntityWrapper(entityCount, meta, 200)).build();    
        } catch(DinaException e) { 
            ErrorBean error = new ErrorBean(entity, e.getMessage()); 
            return Response.status(e.getErrorCode()).entity(Helpclass.getInstance().buildEntityWrapper(error, meta, e.getErrorCode(), 0)).build();  
        }  
    } 

    /**
     * Create an entity
     * 
     * @param req       - HttpServletRequest
     * @param entity    - The name of the entity
     * @param json      - Json String 
     * @return  Response
     *
     */
    @POST
    @Path("{entity}")
    @Consumes("application/json")
    public Response createNewEntity(@Context HttpServletRequest req,
                                    @PathParam("entity") String entity, String json) {
        logger.info("createNewEntity - entity: {}", json);
  
        Metadata metadata = new Metadata();
        MetadataBean meta = metadata.buildMetadata(req, entity);
        try {  
            int agentId = getAgentIdToken(req); 
            EntityBean result = logic.createEntity(entity, json, agentId); 
            
            String strUri = req.getRequestURI() + "/" + result.getIdentityString();
            
            return Response.created(new URI(strUri)).entity(Helpclass.getInstance().buildEntityWrapper(result, meta, 201)).build(); 
        } catch(DinaConstraintViolationException e) {   
            ErrorBean error = new ErrorBean(entity, e.getMessage()); 
            return Response.status(e.getErrorCode()).entity(Helpclass.getInstance().buildEntityWrapper(error, meta, e.getErrorCode(), 0)).build();   
        }  catch(DinaException e) {   
            ErrorBean error = new ErrorBean(entity, e.getMessage()); 
            return Response.status(e.getErrorCode()).entity(Helpclass.getInstance().buildEntityWrapper(error, meta, e.getErrorCode(), 0)).build();   
        } catch (URISyntaxException ex) {
            return Response.status(500).entity(Helpclass.getInstance().buildEntityWrapper(new ErrorBean(entity, ex.getMessage()), meta, 500, 0)).build();   
        }  
    }
    
    
         

    private int getAgentIdToken(HttpServletRequest req) {
        
        Principal userPrincipal = req.getUserPrincipal();  
        if (userPrincipal instanceof KeycloakPrincipal) { 
            KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) userPrincipal;
            Map<String, Object> map = kp.getKeycloakSecurityContext().getToken().getOtherClaims();
            
            try {
                String strId = (String)map.get("agentId"); 
                return Integer.parseInt(strId); 
            } catch(NumberFormatException e) {
                throw new DinaException("Wrong agent id", 400); 
            } 
        } else {
            throw new RuntimeException();
        } 
    }
    
    

    /**
     * Update an entity
     * 
     * @param entity    - The name of the entity
     * @param json      - Json String
     *
     * @return Response
     *
     */
    @PUT
    @Path("{entity}") 
    public Response updateEntity(@PathParam("entity") String entity, String json) {
    
        logger.info("update entity: {} -- {}", entity, json);
        
         try {  
            return Response.ok(logic.updateEntity(entity, json)).build(); 
         } catch(DinaException e) { 
            return Response.status(e.getErrorCode()) 
                    .entity(e.getMessage()).build();
        }  
    }
      
    /**
     * Delete an record from database by the name of the entity and it's id
     * 
     * @param entity    - Class name of the entity 
     * @param id        - The id of the entity to be deleted
     * 
     * @return Response 
     */
    @DELETE
    @Path("{entity}/{id}")  
    public Response deleteEntityById(@PathParam("entity") String entity, @PathParam("id") int id) {
        
        logger.info("deleteEntityById - entity -- id: {} -- {}", entity, id );

        try {   
            logic.deleteEntity(entity, id);  
            return Response.ok().build();  
        } catch(DinaException e) { 
            logger.error("not ok");
            return Response.status(e.getErrorCode()) 
                    .entity(e.getMessage()).build();
        }  
    }  
}
