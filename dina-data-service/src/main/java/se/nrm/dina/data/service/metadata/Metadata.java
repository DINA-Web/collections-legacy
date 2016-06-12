/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service.metadata;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.nrm.dina.data.service.vo.MetadataBean;
import se.nrm.dina.logic.util.HelpClass;

/**
 *
 * @author idali
 */
public class Metadata {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private String callEndpoint;
    private String version;
    private String previous;
    private String next;
    private StringBuilder sb;
    
    private final String HOST_NAME = "beta-api.dina-web.net";
    private final String PROTOCAL = "https://"; 
 
        
    
    public Metadata() {
        
    } 
    
    public MetadataBean buildMetadata(HttpServletRequest req, String entity) {
  
        buildBaseUri(req, entity);
        
        MetadataBean meta = new MetadataBean();
        meta.setCallEndpoint(callEndpoint);
        meta.setApiVersion(version);
        meta.setCallDate(HelpClass.getInstance().dateTimeToString(LocalDateTime.now())); 
        meta.setSupportedLanguages(HelpClass.getInstance().getSupportedLanguages());
  
        meta.setMaintenanceContact(HelpClass.getInstance().getMaintanceContact());
        return meta;
    }
 
    public MetadataBean buildMetadata(HttpServletRequest req, String entity,  int offset, int limit,
                                      int minid, int maxid, String sortOrder, List<String> order, 
                                      String orderby, boolean isExact, Map<String, String> condition) {
        buildBaseUri(req, entity);
        buildPaging(offset, limit); 
        buildParameters(minid, maxid, sortOrder, orderby, isExact, condition);
         
        MetadataBean meta = new MetadataBean();
        meta.setCallEndpoint(callEndpoint);
        meta.setApiVersion(version);
        meta.setCallDate(HelpClass.getInstance().dateTimeToString(LocalDateTime.now())); 
        meta.setSupportedLanguages(HelpClass.getInstance().getSupportedLanguages());
        meta.setLimit(limit);
        meta.setOffset(offset);
         
        if(offset > 0) {
            meta.setPrevious(previous); 
        } 
        meta.setNext(next);
        
        meta.setOrderBy(order);
        meta.setSortOrder(HelpClass.getInstance().getSort(sortOrder));
        meta.setMaintenanceContact(HelpClass.getInstance().getMaintanceContact());
        return meta;
    }
    
        
    private void buildParameters(int minid, int maxid, String sortOrder, String orderby,
                                boolean isExact, Map<String, String> condition) {
        sb = new StringBuilder();
        sb.append("&minid=");
        sb.append(minid);
        
        if(maxid > 0) {
            sb.append("&maxid=");
            sb.append(maxid);
        }
        
        sb.append("&sort=");
        sb.append(sortOrder);
        
        if(orderby != null && !orderby.isEmpty()) {
            sb.append("&orderBy=");
            sb.append(orderby);
        }
        
        if(condition != null) {
            condition.entrySet()
                    .stream()
                    .forEach(entry -> {
                        sb.append("&");
                        sb.append(entry.getKey());
                        sb.append("=");
                        sb.append(entry.getValue());
                    }); 
        }
        
        sb.append("&exact=");
        sb.append(isExact);
        
        callEndpoint += sb.toString();
        previous += sb.toString();
        next += sb.toString();
    }
     
    private void buildBaseUri(HttpServletRequest req, String entity) {
        String uri = req.getRequestURI();   
        version = StringUtils.substringBetween(uri, req.getContextPath() + "/v", "/" + entity);
  
        sb = new StringBuilder(); 
        sb.append(PROTOCAL);
        sb.append(HOST_NAME);
        sb.append(uri); 
        callEndpoint = sb.toString(); 
    } 
    
    private String buildPaging(String uri, int offset, int limit) {
        sb = new StringBuilder();
        sb.append(uri);
        sb.append("?offset=");
        sb.append(offset);
        sb.append("&limit=");
        sb.append(limit);
        return sb.toString();
    }

    private void buildPaging(int offset, int limit) { 
         
        if(offset > 0) {
            if (offset >= limit) {
                int previousOffset = offset - limit;
                previous = buildPaging(callEndpoint, previousOffset, limit); 
            }   
        }
         
        int nextOffset = offset + limit;
        next = buildPaging(callEndpoint, nextOffset, limit); 
        callEndpoint = buildPaging(callEndpoint, offset, limit); 
    }   
}
