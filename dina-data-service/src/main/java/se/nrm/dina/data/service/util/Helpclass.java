/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service.util;
  
import java.util.ArrayList;
import java.util.List;   
import se.nrm.dina.data.service.vo.EntityCount;
import se.nrm.dina.data.service.vo.EntityWrapper;
import se.nrm.dina.data.service.vo.MetadataBean;
import se.nrm.dina.data.service.vo.ResultWrapper;
import se.nrm.dina.data.vo.ErrorBean;
import se.nrm.dina.datamodel.EntityBean;



/**
 *
 * @author idali
 */
public class Helpclass {

    private static Helpclass instance = null;

    public static synchronized Helpclass getInstance() {
        if (instance == null) {
            instance = new Helpclass();
        }
        return instance;
    }
 
            
    public EntityWrapper buildEntityWrapper(ErrorBean error, MetadataBean meta, int statusCode, int resultCount) {
        meta.setResultCount(resultCount);
        meta.setStatusCode(statusCode);
        return new EntityWrapper(meta, error);
    }
        
    public EntityWrapper buildEntityWrapper(List<EntityBean> entityBeans, MetadataBean meta, int statusCode) {
        if(entityBeans != null) {
            meta.setResultCount(entityBeans.size());
        }  
        meta.setStatusCode(statusCode);
        
        if(entityBeans != null) {
            List<ResultWrapper> list = new ArrayList<>();
            entityBeans.stream()
                    .forEach(e -> {
                        ResultWrapper wrapper = new ResultWrapper(e.getEntityId(), e.getClass().getSimpleName(), e);
                        list.add(wrapper);
                    });
             
            
            return new EntityWrapper(meta, list);
        } else {
            return new EntityWrapper(meta, entityBeans);
        } 
    }  
    
    public EntityWrapper buildEntityWrapper(EntityBean entityBean, MetadataBean meta, int statusCode) { 
        meta.setStatusCode(statusCode);
        if(entityBean != null) {
            meta.setResultCount(1);
        } 
        if(entityBean != null) {
            ResultWrapper wrapper = new ResultWrapper(entityBean.getEntityId(), entityBean.getClass().getSimpleName(), entityBean);
            return new EntityWrapper(meta, wrapper);
        } else {
            return new EntityWrapper(meta, entityBean);
        }
        
    }  
    
    public EntityWrapper buildEntityWrapper(EntityCount entityBean, MetadataBean meta, int statusCode) { 
        meta.setStatusCode(statusCode);
        meta.setResultCount(1);
        return new EntityWrapper(meta, entityBean);
    }  
}
