/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.exceptions;

import java.util.List;
import javax.ejb.ApplicationException;  
import se.nrm.dina.datamodel.ErrorBean;

/**
 *
 * @author idali
 */
@ApplicationException
public class DinaConstraintViolationException extends DinaException {
    
    public DinaConstraintViolationException(ErrorBean errorBean, int errorCode) {
        super(errorBean, errorCode);
    }
    
    public DinaConstraintViolationException(List<ErrorBean> errorBeans, int errorCode) {
        super(errorBeans, errorCode);
    }
    
    public DinaConstraintViolationException(Throwable t) { 
    }
}
