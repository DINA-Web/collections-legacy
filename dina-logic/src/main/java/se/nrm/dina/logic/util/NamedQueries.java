/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.logic.util;
 
import se.nrm.dina.data.util.Util;
import java.util.List; 
import java.util.Map; 
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  
import se.nrm.dina.datamodel.EntityBean;

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
     * @param offset
     * @param minid
     * @param maxid
     * @param sort
     * @param orderBy
     * @param isExact
     * @param criteria
     * @return String
     */
    public String createQueryFindAllWithSearchCriteria(String entityName,
                                                        Class clazz,
                                                        int offset,
                                                        int minid,
                                                        int maxid,
                                                        String sort,
                                                        List<String> orderBy,
                                                        boolean isExact,
                                                        Map<String, String> criteria) {
  
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT e From ");
        sb.append(entityName);
        sb.append(" e ");

        if (maxid > 0) {
            minid = minid > offset ? minid : offset;
            sb.append(buildConditions(clazz, minid, maxid, criteria, isExact));
        } else if (offset > 0) {
            offset = offset > minid ? offset : minid;
            sb.append(buildConditions(clazz, offset, criteria, isExact));
        } else if (criteria != null && !criteria.isEmpty()) {
            sb.append(buildConditions(clazz, criteria, isExact));
        }

        if (orderBy != null && !orderBy.isEmpty()) {
            sb.append(buildOrderByString(clazz, orderBy, sort));
        } else {
            sb.append(buildSorting(clazz, sort));
        }
        return sb.toString(); 
    }

 
    private String buildConditions(Class clazz, int minid, int maxid, Map<String, String> criteria, boolean isExact) {

        EntityBean bean = Util.getInstance().createNewInstance(clazz);
        String idFieldName = Util.getInstance().getIDFieldName(bean);

        StringBuilder sb = new StringBuilder();
        sb.append("WHERE e.");
        sb.append(idFieldName);
        sb.append(" BETWEEN ");
        sb.append(minid);
        sb.append(" AND ");
        sb.append(maxid);

        if (criteria == null || criteria.isEmpty()) {
            return sb.toString().trim();
        } else { 
            sb.append(" AND ");
            sb.append(buildSearchCriteria(clazz, criteria, isExact));
            return sb.toString();
        } 
    }

    private String buildConditions(Class clazz, int offset, Map<String, String> criteria, boolean isExact) {

        EntityBean bean = Util.getInstance().createNewInstance(clazz);
        String idFieldName = Util.getInstance().getIDFieldName(bean);

        StringBuilder sb = new StringBuilder();
        sb.append("WHERE e.");
        sb.append(idFieldName);
        sb.append(" >= ");
        sb.append(offset);

        if (criteria == null || criteria.isEmpty()) {
            return sb.toString().trim();
        } else { 
            sb.append(" AND ");
            sb.append(buildSearchCriteria(clazz, criteria, isExact));
            return sb.toString();
        } 
    }
    
    private String buildConditions(Class clazz, Map<String, String> criteria, boolean isExact) {
        StringBuilder sb = new StringBuilder();
        sb.append("WHERE");
        sb.append(buildSearchCriteria(clazz, criteria, isExact));

        return sb.toString();
    }

    private String buildSearchCriteria(Class clazz, Map<String, String> criteria, boolean isExact) {

        logger.info("buildSearchCriteria : {}", criteria);
        StringBuilder sb = new StringBuilder();
        criteria.entrySet()
                .stream()
                .forEach(entry -> {
                    sb.append(" e.");
                    sb.append(entry.getKey()); 
                    if (Util.getInstance().isEntity(clazz, entry.getKey())) {
                        sb.append(".");
                        sb.append(Util.getInstance().getIDFieldName(Util.getInstance().getEntity(clazz, entry.getKey())));
                        sb.append(" = :");
                        sb.append(entry.getKey());
                    } else if(Util.getInstance().isBigDecimal(clazz, entry.getKey()) || Util.getInstance().isDate(clazz, entry.getKey())) {
                        String value = entry.getValue(); 
                        if(value.toLowerCase().startsWith(BETWEEN)) {
                            sb.append(" BETWEEN :");
                            sb.append(entry.getKey());
                            sb.append("min AND :");
                            sb.append(entry.getKey());
                            sb.append("max");
                        } else if(value.toLowerCase().startsWith(GREAT_THAN)) {
                            sb.append(" >= :");
                            sb.append(entry.getKey());
                            sb.append("v1");
                        } else if(value.toLowerCase().startsWith(LESS_THAN)) {
                            sb.append(" <= :");
                            sb.append(entry.getKey());
                            sb.append("v2");
                        } else {
                            sb.append(" = :"); 
                            sb.append(entry.getKey());
                        } 
                    } else {
                        sb.append(isExact ? " = :" : " like :"); 
                        sb.append(entry.getKey());
                    } 
                    sb.append(" AND ");
                });
        return StringUtils.substringBeforeLast(sb.toString(), " AND");
    }
    
    private String buildSorting(Class clazz, String sort) {
        logger.info("buildSorting : {} -- {}", clazz, sort);
        
        String idField = Util.getInstance().getIDFieldName(clazz);
        StringBuilder sb = new StringBuilder();
        sb.append(" ORDER BY ");
        sb.append("e.");
        sb.append(idField);
        sb.append(" ");
        sb.append(sort);
        return sb.toString();
    }

    private String buildOrderByString(Class clazz, List<String> list, String sort) {

        logger.info("buildOrderByString : {} -- {}", clazz, list);
         
        StringBuilder sb = new StringBuilder();
        sb.append(" ORDER BY ");
        list.stream()
                .filter(l -> Util.getInstance().validateFields(clazz, l))
                .forEach(l -> {
                    sb.append("e.");
                    sb.append(l);
                    sb.append(" ");
                    sb.append(sort);
                    sb.append(", ");
                });
         
        return StringUtils.substringBeforeLast(sb.toString(), ","); 
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
