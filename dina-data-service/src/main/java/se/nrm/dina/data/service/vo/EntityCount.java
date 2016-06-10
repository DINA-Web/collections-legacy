/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.service.vo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author idali
 */
@XmlRootElement
public class EntityCount implements Serializable {

    @XmlElement
    private final int count;

    public EntityCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
