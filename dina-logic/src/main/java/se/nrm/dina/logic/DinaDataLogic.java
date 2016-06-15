/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.logic;
  
import se.nrm.dina.data.util.JpaReflectionHelper;
import java.io.IOException;
import java.io.Serializable; 
import java.lang.reflect.Field;
import java.sql.Timestamp; 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.List;
import java.util.Map; 
import javax.ejb.EJB;
import javax.ejb.Stateless;  
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.nrm.dina.data.exceptions.DinaConstraintViolationException;
import se.nrm.dina.data.exceptions.DinaException;
import se.nrm.dina.data.jpa.DinaDao; 
import se.nrm.dina.logic.util.NamedQueries;
import se.nrm.dina.datamodel.EntityBean;     
import se.nrm.dina.logic.util.HelpClass;

/**
 *
 * @author idali
 * @param <T>
 */
@Stateless
public class DinaDataLogic<T extends EntityBean> implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
      
    private java.util.Date date; 
    private EntityBean createdByUserBean;

    @EJB
    private DinaDao dao;

    public DinaDataLogic() { 
    }
    
    public DinaDataLogic(DinaDao dao) {
        this.dao = dao;
    }


   
    /**
     * Finds all the instances of an entity
     * 
     * @param entityName 
     * @param limit
     * @param offset 
     * @param orderby
     * @param sort 
     * 
     * @return List
     */
    public List<T> findAll(String entityName, int limit, int offset, String sort, List<String> orderby ) {
          
        try {
            entityName = JpaReflectionHelper.getInstance().validateEntityName(entityName);
            Class clazz = JpaReflectionHelper.getInstance().convertClassNameToClass(entityName);
            
            String strQuery = NamedQueries.getInstance().createQueryFindAll(entityName, clazz, sort, orderby);
 
            return dao.findAll(clazz, strQuery, limit, null, false, offset); 
        } catch (DinaException e) {
            throw e;
        }
    }
     

    /**
     * Finds all the instances of an entity by query
     * @param entityName
     * @param limit 
     * @param offset
     * @param sort
     * @param orderby
     * @param condition 
     * @param isExact 
     * 
     * @return List
     */
    public List<T> findAllBySearchCriteria(String entityName, int limit, int offset, String sort, 
                                           List<String> orderby, Map<String, String> condition, boolean isExact) {

        logger.info("findAllBySearchCriteria : {}", condition);

        entityName = JpaReflectionHelper.getInstance().validateEntityName(entityName);
    
        try {
            Class clazz = JpaReflectionHelper.getInstance().convertClassNameToClass(entityName); 

            String strQuery = NamedQueries.getInstance()
                    .createQueryFindAllWithSearchCriteria(entityName, clazz,  HelpClass.getInstance().getSort(sort),
                                                            orderby, isExact, condition);
   
            logger.info("strQury : {}", strQuery);
            return dao.findAll(clazz, strQuery, limit, condition, isExact, offset);
        } catch (DinaException e) {
            throw e;
        }
    }


    /**
     * Finds an entity by its database id
     *
     * @param id
     * @param entityName  
     * @return T
     */
    public T findById(String id, String entityName) {
        logger.info("findById : {} -- {}", id, entityName);
   
        try {
            Class clazz = JpaReflectionHelper.getInstance().convertClassNameToClass(entityName); 
            if(HelpClass.getInstance().isNumric(id)) { 
                return (T) dao.findById(Integer.parseInt(id), clazz, JpaReflectionHelper.getInstance().isVersioned(clazz));
            } else {
                return (T) dao.findByStringId(id, clazz); 
            }       
        } catch (DinaException e) {
            throw e;
        }

    }

    public List<T> findEntitiesByids(String entityName, String ids) {
        logger.info("findEntitiesByids : {} -- {}", entityName, ids);

//        String idList = StringUtils.substringBetween(ids, "(", ")");

        if (StringUtils.isEmpty(ids)) {
            return null;
        }
        
        if(ids.contains("(")) {
            ids = StringUtils.substringBetween(ids, "(", ")");
        } 
        
        try { 
            Class clazz = JpaReflectionHelper.getInstance().convertClassNameToClass(entityName); 
            boolean isVersioned = JpaReflectionHelper.getInstance().isVersioned(clazz);
            List<T> beans = new ArrayList();
            
            Arrays.asList(ids.split(",")).stream()
                    .forEach(strId -> {
                        int id =  HelpClass.getInstance().strToInt(strId); 
                        T bean = (T) dao.findById(id, clazz, isVersioned);
                        if(bean != null) {
                            beans.add(bean);
                        } 
                    }); 
            return beans;
        } catch (DinaException e) {
            throw e;
        }

    }

    /**
     * Finds the total number of an entity in database
     *
     * @param entityName
     * @return int
     */
    public int findEntityCount(String entityName) {

        try {
            return dao.getCountByQuery(NamedQueries.getInstance()
                    .createFindTotalCountNamedQuery(
                            JpaReflectionHelper.getInstance().convertClassNameToClass(entityName)
                                    .getSimpleName()));
        } catch (DinaException e) {
            throw e;
        }
    }

    /**
     * Creates an entity in database
     *
     * @param entityName
     * @param json  
     * @param agentId  
     * @return EntityBean
     */
    public EntityBean createEntity(String entityName, String json, int agentId) { 
        logger.info("createEntity : {} ", entityName);
 
        LocalDateTime ld = LocalDateTime.now();
        date = Timestamp.valueOf(ld);

        try {
            EntityBean bean = mappObject(entityName, json); 
            Class clazz = JpaReflectionHelper.getInstance().getCreatedByClazz();
            createdByUserBean = dao.findById(agentId, clazz, JpaReflectionHelper.getInstance().isVersioned(clazz));
 
            Field[] fields = bean.getClass().getDeclaredFields();
            Arrays.stream(fields)
                    .forEach(f -> {
                        setValueToBean(bean, f);
                    });

            setTimeStampCreated(bean);
            setCreatedByUser(bean, createdByUserBean);
            return dao.create(bean);
        } catch (DinaConstraintViolationException ex) {
            throw ex;
        } catch (DinaException ex) {
            throw ex;
        } 
    }


    /**
     * Updates an entity in database
     *
     * @param entityName
     * @param json
     * @return EntityBean
     */
    public EntityBean updateEntity(String entityName, String json) {
        logger.info("updateEntity : {} -- {}", entityName, json);

        try {
            EntityBean bean = mappObject(entityName, json);
            return dao.merge(bean);
        } catch (DinaException ex) {
            throw new DinaException(ex.getMessage());
        }
    }

    private EntityBean mappObject(String entityName, String json) {

        ObjectMapper mapper = new ObjectMapper();

        EntityBean bean = null;
        try {
            bean = (EntityBean) mapper.readValue(json, JpaReflectionHelper.getInstance().convertClassNameToClass(entityName));
        } catch (IOException ex) {
            throw new DinaException(ex.getMessage());
        }
        return bean;
    }

    /**
     * Deletes an entity in database
     *
     * @param entityName
     * @param id
     */
    public void deleteEntity(String entityName, int id) {

        logger.info("deleteEntity : {} -- {}", entityName, id);

        try {
            EntityBean bean = dao.findByReference(id, JpaReflectionHelper.getInstance().convertClassNameToClass(entityName));

            if (bean != null) {
                dao.delete(bean);
            }
        } catch (DinaException e) {
            logger.error(e.getMessage());
            throw new DinaException(e.getMessage());
        }
    }

    private void setChildToBean(EntityBean parent, Field f) { 
        try {
            f.setAccessible(true);
            EntityBean child = (EntityBean) f.get(parent); 
            if (child != null) {
                Field field = JpaReflectionHelper.getInstance().getIDField(child);

                field.setAccessible(true);
                if (field.get(child) != null && (Integer) field.get(child) > 0) { 
                    Class clazz = child.getClass(); 
                    EntityBean entity = dao.findById((Integer) field.get(child), clazz, JpaReflectionHelper.getInstance().isVersioned(clazz)); 
                    if (entity == null) {
                        setTimeStampCreated(child);
                        setCreatedByUser(child, createdByUserBean);
                        f.set(parent, child);
                        Field[] fields = child.getClass().getDeclaredFields();
                        Arrays.stream(fields)
                                .forEach(fd -> {
                                    setValueToBean(child, fd);
                                });
                        setParentToChild(fields, child, parent);
                    } else {
                        f.set(parent, entity);
                    } 
                } else {
                    setTimeStampCreated(child);
                    setCreatedByUser(child, createdByUserBean);
                    f.set(parent, child);
                    Field[] fields = child.getClass().getDeclaredFields();
                    Arrays.stream(fields)
                            .forEach(fd -> {
                                setValueToBean(child, fd);
                            });
                    setParentToChild(fields, child, parent);
                }  
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new DinaException("Save data failed.");
        }
    }

    private void setChildrenToBean(EntityBean parent, Field field) { 
        try {
            field.setAccessible(true);
            List<EntityBean> children = (List) field.get(parent);
            Field[] fields;
            if (children != null && !children.isEmpty()) {
                for (EntityBean child : children) {
                    setTimeStampCreated(child);
                    setCreatedByUser(child, createdByUserBean);
                    fields = child.getClass().getDeclaredFields(); 
                    setParentToChild(fields, child, parent);
                }
                field.set(parent, children);

            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new DinaException("Save data failed. " + ex.getMessage());
        }
    }

    private void setParentToChild(Field[] fields, EntityBean child, EntityBean parent) {
        Arrays.asList(fields).stream()
                .forEach(f -> {
                    if (JpaReflectionHelper.getInstance().isEntity(child.getClass(), f.getName())) {
                        try {
                            setChildToBean(child, f);
                            f.setAccessible(true);
                            if (f.getName().toLowerCase().contains(parent.getClass().getSimpleName().toLowerCase())) {
                                f.set(child, parent);
                            }
                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            throw new DinaException("Save data failed. " + ex.getMessage());
                        }
                    }
                });
    }

    private void setValueToBean(EntityBean parent, Field f) {
        try {
            if (JpaReflectionHelper.getInstance().isEntity(parent.getClass(), f.getName())) {
                setChildToBean(parent, f);
            } else if (JpaReflectionHelper.getInstance().isCollection(parent.getClass(), f.getName())) {
                setChildrenToBean(parent, f);
            }
        } catch (DinaException e) {
            throw e;
        }
    }

    private void setCreatedByUser(EntityBean bean, EntityBean userBean) {
        Field field = JpaReflectionHelper.getInstance().getCreatedByField(bean.getClass());

        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(bean, userBean);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                throw new DinaException(ex.getMessage()); 
            }
        } 
    }
     
    
    private void setTimeStampCreated(EntityBean bean) {
        Field field = JpaReflectionHelper.getInstance().getTimestampCreated(bean.getClass());
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(bean, date);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                throw new DinaException(ex.getMessage()); 
            }
        }
    }  
}
