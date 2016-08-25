/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.jpa.impl;
 
import java.util.Map;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.nrm.dina.data.exceptions.DinaException;
import se.nrm.dina.data.util.HelpClass;
import se.nrm.dina.data.util.JpaReflectionHelper;
import se.nrm.dina.data.util.ValueType;

/**
 * QueryBuilder helps to build query with search conditions
 * 
 * @author idali
 */
class QueryBuilder {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    private final String BETWEEN = "between";
    private final String GREAT_THAN = "gt";
    private final String LESS_THAN = "lt";
    private final String MIN = "min";
    private final String MAX = "max";
    
    private static QueryBuilder instance = null;

    public static synchronized QueryBuilder getInstance() {
        if (instance == null) {
            instance = new QueryBuilder();
        }
        return instance;
    }

    /**
     * Build a namedQuery with parameters
     *
     * @param query
     * @param clazz
     * @param parameters
     * @param isFuzzSearch
     * @return Query
     */
    public Query createQuery(Query query, Class clazz, Map<String, String> parameters, boolean isExact) {
        logger.info("createQuery : {}", isExact);
         
        if (parameters != null) {
            parameters.entrySet()
                    .stream()
                    .forEach((entry) -> {
                        String fieldName = entry.getKey();
                        String value = entry.getValue();
                        try {
                            ValueType valueType = JpaReflectionHelper.getInstance().getValueType(clazz, fieldName); 
                            switch (valueType) {
                                case INT:
                                    query.setParameter(fieldName, Integer.parseInt(entry.getValue()));
                                    break;
                                case ENTITY:
                                    query.setParameter(fieldName, Integer.parseInt(entry.getValue()));
                                    break;
                                case BIGDECIMAL:
                                    setBigDecimal(fieldName, value, query);
                                    break;
                                case DATE:
                                    setDate(fieldName, value, query);
                                    break;
                                case SHORT:
                                    setShotValue(fieldName, value, query);
                                    break;
                                default:
                                    setDefaultValue(fieldName, value, query, isExact);
                                    break;
                            }
                        } catch (DinaException e) {
                            throw e;
                        }
                    });
        }
        return query;
    }
     
    private void setBigDecimal(String fieldName, String value, Query query) {
    
        if (value.toLowerCase().startsWith(BETWEEN)) {
            query.setParameter(fieldName + MIN, HelpClass.getInstance().convertStringToBigDecimal(StringUtils.substringBetween(value, "(", ",")));
            query.setParameter(fieldName + MAX, HelpClass.getInstance().convertStringToBigDecimal(StringUtils.substringBetween(value, ",", ")")));
        } else if (value.toLowerCase().startsWith(GREAT_THAN)) {
            query.setParameter(fieldName + "v1", HelpClass.getInstance().convertStringToBigDecimal(StringUtils.substringBetween(value, "(", ")")));
        } else if (value.toLowerCase().startsWith(LESS_THAN)) {
            query.setParameter(fieldName + "v2", HelpClass.getInstance().convertStringToBigDecimal(StringUtils.substringBetween(value, "(", ")")));
        } else {
            query.setParameter(fieldName, HelpClass.getInstance().convertStringToBigDecimal(value));
        }
    }

    private void setDate(String fieldName, String value, Query query) {

        if (value.toLowerCase().startsWith(BETWEEN)) {
            query.setParameter(fieldName + MIN, HelpClass.getInstance().convertStringToDate(StringUtils.substringBetween(value, "(", ",")));
            query.setParameter(fieldName + MAX, HelpClass.getInstance().convertStringToDate(StringUtils.substringBetween(value, ",", ")")));
        } else if (value.toLowerCase().startsWith(GREAT_THAN)) {
            query.setParameter(fieldName + "v1", HelpClass.getInstance().convertStringToDate(StringUtils.substringBetween(value, "(", ")")));
        } else if (value.toLowerCase().startsWith(LESS_THAN)) {
            query.setParameter(fieldName + "v2", HelpClass.getInstance().convertStringToDate(StringUtils.substringBetween(value, "(", ")")));
        } else {
            query.setParameter(fieldName, HelpClass.getInstance().convertStringToDate(value));
        }
    }

    
    private void setShotValue(String fieldName, String value, Query query) {
      
        if(value != null && !value.isEmpty()) {
            short s = java.lang.Short.parseShort(value); 
            query.setParameter(fieldName, s);
        } 
    } 
    
    private void setDefaultValue(String fieldName, String value, Query query, boolean isExact) {
        logger.info("setDefaultValue : {} -- {}", value, isExact);
        if (isExact) {
            query.setParameter(fieldName, value);
        } else {
            query.setParameter(fieldName, "%" + value + "%"); 
        }
    } 
 
}                      