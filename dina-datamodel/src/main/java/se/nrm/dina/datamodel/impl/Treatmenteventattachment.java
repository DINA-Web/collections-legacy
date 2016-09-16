/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.datamodel.impl;


import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType; 
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import se.nrm.dina.datamodel.BaseEntity;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "treatmenteventattachment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Treatmenteventattachment.findAll", query = "SELECT t FROM Treatmenteventattachment t"),
    @NamedQuery(name = "Treatmenteventattachment.findByTreatmentEventAttachmentID", query = "SELECT t FROM Treatmenteventattachment t WHERE t.treatmentEventAttachmentID = :treatmentEventAttachmentID"),
    @NamedQuery(name = "Treatmenteventattachment.findByTimestampCreated", query = "SELECT t FROM Treatmenteventattachment t WHERE t.timestampCreated = :timestampCreated"),
    @NamedQuery(name = "Treatmenteventattachment.findByTimestampModified", query = "SELECT t FROM Treatmenteventattachment t WHERE t.timestampModified = :timestampModified"),
    @NamedQuery(name = "Treatmenteventattachment.findByOrdinal", query = "SELECT t FROM Treatmenteventattachment t WHERE t.ordinal = :ordinal")})
public class Treatmenteventattachment extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TreatmentEventAttachmentID")
    private Integer treatmentEventAttachmentID;
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ordinal")
    private int ordinal;
    
    @Lob
    @Size(max = 65535)
    @Column(name = "Remarks")
    private String remarks;
    
    @JoinColumn(name = "TreatmentEventID", referencedColumnName = "TreatmentEventID")
    @ManyToOne(optional = false)
    private Treatmentevent treatmentEventID;
    
    @JoinColumn(name = "ModifiedByAgentID", referencedColumnName = "AgentID")
    @ManyToOne
    private Agent modifiedByAgentID;
    
    @JoinColumn(name = "CreatedByAgentID", referencedColumnName = "AgentID")
    @ManyToOne
    private Agent createdByAgentID;
    
    @JoinColumn(name = "AttachmentID", referencedColumnName = "AttachmentID")
    @ManyToOne(optional = false)
    private Attachment attachmentID;

    public Treatmenteventattachment() {
    }

    public Treatmenteventattachment(Integer treatmentEventAttachmentID) {
        this.treatmentEventAttachmentID = treatmentEventAttachmentID;
    }

    public Treatmenteventattachment(Integer treatmentEventAttachmentID, Date timestampCreated, int ordinal) {
        this.treatmentEventAttachmentID = treatmentEventAttachmentID;
        this.timestampCreated = timestampCreated;
        this.ordinal = ordinal;
    }

    public Integer getTreatmentEventAttachmentID() {
        return treatmentEventAttachmentID;
    }
    
    @XmlID
    @XmlAttribute(name = "id")
    @Override 
    public String getIdentityString() {
        return String.valueOf(treatmentEventAttachmentID);
    }

    @Override
    public int getEntityId() {
        return treatmentEventAttachmentID;
    }

    public void setTreatmentEventAttachmentID(Integer treatmentEventAttachmentID) {
        this.treatmentEventAttachmentID = treatmentEventAttachmentID;
    }
    

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Treatmentevent getTreatmentEventID() {
        return treatmentEventID;
    }

    public void setTreatmentEventID(Treatmentevent treatmentEventID) {
        this.treatmentEventID = treatmentEventID;
    }

    public Agent getModifiedByAgentID() {
        return modifiedByAgentID;
    }

    public void setModifiedByAgentID(Agent modifiedByAgentID) {
        this.modifiedByAgentID = modifiedByAgentID;
    }

    public Agent getCreatedByAgentID() {
        return createdByAgentID;
    }

    public void setCreatedByAgentID(Agent createdByAgentID) {
        this.createdByAgentID = createdByAgentID;
    }

    public Attachment getAttachmentID() {
        return attachmentID;
    }

    public void setAttachmentID(Attachment attachmentID) {
        this.attachmentID = attachmentID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (treatmentEventAttachmentID != null ? treatmentEventAttachmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Treatmenteventattachment)) {
            return false;
        }
        Treatmenteventattachment other = (Treatmenteventattachment) object;
        return !((this.treatmentEventAttachmentID == null && other.treatmentEventAttachmentID != null) || (this.treatmentEventAttachmentID != null && !this.treatmentEventAttachmentID.equals(other.treatmentEventAttachmentID)));
    }

    @Override
    public String toString() {
        return "se.nrm.dina.datamodel.impl.Treatmenteventattachment[ treatmentEventAttachmentID=" + treatmentEventAttachmentID + " ]";
    }  
}
