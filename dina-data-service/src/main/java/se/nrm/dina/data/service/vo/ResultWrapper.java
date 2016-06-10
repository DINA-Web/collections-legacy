/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service.vo;

import java.io.Serializable;

/**
 *
 * @author idali
 */
public class ResultWrapper implements Serializable {
    
    private final int id;
    private final String type;
    
    private final Object attributes;
    
    public ResultWrapper(int id, String type, Object attributes) {
        this.id = id;
        this.type = type;
        this.attributes = attributes;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Object getAttributes() {
        return attributes;
    } 
}
