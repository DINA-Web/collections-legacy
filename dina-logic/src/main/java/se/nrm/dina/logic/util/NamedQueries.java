/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.logic.util;
 
import se.nrm.dina.data.util.JpaReflectionHelper;
import java.util.List; 
import java.util.Map; 
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;   
import se.nrm.dina.data.exceptions.DinaException; 

/**
 *
 * @author idali
 */
public class NamedQueries {
     
    private final Logger logger = LoggerFactory.getLogger(this.getClass());   
    
    private static NamedQueries instance = null; 
    private final String BETWEEN = "between";
    private final String GREAT_THAN = "gt";
    private final String LESS_THAN = "lt";

    public static synchronized NamedQueries getInstance() {
        if (instance == null) {
            instance = new NamedQueries();
        }
        return instance;
    }


    /**
     * Creates a query string
     * @param entityName
     * @param clazz 
     * @param sort
     * @param orderBy 
     * @return String
     */
    public String createQueryFindAll(String entityName, Class clazz, String sort, List<String> orderBy ) {
  
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT e From ");
        sb.append(entityName);
        sb.append(" e ");
          
        try {
            if (orderBy != null && !orderBy.isEmpty()) {
                buildOrderByString(clazz, orderBy, sort, sb);
            } else {
                buildSorting(clazz, sort, sb);
            }
            String string = sb.toString().trim();
            if (StringUtils.endsWith(string, ",")) {
                string = StringUtils.substringBeforeLast(string, ",");
            } 
            return string;
        } catch (DinaException e) {
            throw e;
        }
    }

    /**
     * Creates a query string
     * @param entityName
     * @param clazz  
     * @param sort
     * @param orderBy
     * @param isExact
     * @param criteria
     * @return String
     */
    public String createQueryFindAllWithSearchCriteria(String entityName,
                                                        Class clazz,  
                                                        String sort,
                                                        List<String> orderBy,
                                                        boolean isExact,
                                                        Map<String, String> criteria) {
  
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT e From ");
        sb.append(entityName);
        sb.append(" e ");

        try {
            if (criteria != null && !criteria.isEmpty()) { 
                String buildCriteria = " WHERE "; 
                sb.append(buildCriteria);
                sb.append(buildSearchCriteria(clazz, criteria, isExact));
            }
            
            if (orderBy != null && !orderBy.isEmpty()) {
                buildOrderByString(clazz, orderBy, sort, sb);
            } else {
                buildSorting(clazz, sort, sb);
            }
            
            String string = sb.toString().trim();
            if(StringUtils.endsWith(string, ",")) {
                string = StringUtils.substringBeforeLast(string, ",");
            }
             
            return string;
        } catch(DinaException e) {
            throw e;
        }
    }
 
    private String buildSearchCriteria(Class clazz, Map<String, String> criteria, boolean isExact) {

        logger.info("buildSearchCriteria : {}", criteria);
        StringBuilder sb = new StringBuilder();
        try { 
            criteria.entrySet()
                    .stream()
                    .forEach(entry -> {
                        sb.append(" e.");
                        sb.append(entry.getKey());
                        if (JpaReflectionHelper.getInstance().isEntity(clazz, entry.getKey())) {
                            sb.append(".");
                            sb.append(JpaReflectionHelper.getInstance().getIDFieldName(JpaReflectionHelper.getInstance().getEntity(clazz, entry.getKey())));
                            sb.append(" = :");
                            sb.append(entry.getKey());
                        } else if (JpaReflectionHelper.getInstance().isBigDecimal(clazz, entry.getKey()) || JpaReflectionHelper.getInstance().isDate(clazz, entry.getKey())) {
                            String value = entry.getValue();
                            if (value.toLowerCase().startsWith(BETWEEN)) {
                                sb.append(" BETWEEN :");
                                sb.append(entry.getKey());
                                sb.append("min AND :");
                                sb.append(entry.getKey());
                                sb.append("max");
                            } else if (value.toLowerCase().startsWith(GREAT_THAN)) {
                                sb.append(" >= :");
                                sb.append(entry.getKey());
                                sb.append("v1");
                            } else if (value.toLowerCase().startsWith(LESS_THAN)) {
                                sb.append(" <= :");
                                sb.append(entry.getKey());
                                sb.append("v2");
                            } else {
                                sb.append(" = :");
                                sb.append(entry.getKey());
                            }
                        } else if (JpaReflectionHelper.getInstance().isShortField(clazz, entry.getKey())) {
                            sb.append(" = :");
                            sb.append(entry.getKey());
                        } else {
                            sb.append(isExact ? " = :" : " like :");
                            sb.append(entry.getKey());
                        }
                        sb.append(" AND ");
                    }); 
              
            return StringUtils.substringBeforeLast(sb.toString(), " AND");
        } catch (DinaException e) {
            throw e;
        }
    }

    private void buildSorting(Class clazz, String sort, StringBuilder sb) {
        logger.info("buildSorting : {} -- {}", clazz, sort);

        String idField = JpaReflectionHelper.getInstance().getIDFieldName(clazz); 
        sb.append(" ORDER BY ");
        sb.append("e.");
        sb.append(idField);
        sb.append(" ");
        sb.append(sort); 
    }
     
     
    private void buildOrderByString(Class clazz, List<String> list, String sort, StringBuilder sb) {

        logger.info("buildOrderByString : {} -- {}", clazz, list);
          
        sb.append(" ORDER BY");
        list.stream()
                .filter(l -> JpaReflectionHelper.getInstance().validateFields(clazz, l))
                .forEach(l -> {
                    sb.append(" e.");
                    sb.append(l);
                    sb.append(" ");
                    sb.append(sort);
                    sb.append(",");
                });  
    }
    
    /**
     * Creates a NamedQuery String for find total count of an entity
     * @param entityName
     * @return String
     */
    public String createFindTotalCountNamedQuery(String entityName) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(e) FROM "); 
        sb.append(entityName);
        sb.append(" e"); 
        
        return sb.toString();
    } 
}
