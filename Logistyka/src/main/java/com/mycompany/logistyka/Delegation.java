/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.logistyka;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "delegation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Delegation.findAll", query = "SELECT d FROM Delegation d"),
    @NamedQuery(name = "Delegation.findByDelegationId", query = "SELECT d FROM Delegation d WHERE d.delegationId = :delegationId"),
    @NamedQuery(name = "Delegation.findByStartDate", query = "SELECT d FROM Delegation d WHERE d.startDate = :startDate"),
    @NamedQuery(name = "Delegation.findByEndDate", query = "SELECT d FROM Delegation d WHERE d.endDate = :endDate"),
    @NamedQuery(name = "Delegation.findByDescription", query = "SELECT d FROM Delegation d WHERE d.description = :description")})
public class Delegation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "delegation_id")
    private Integer delegationId;
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "driver_id", referencedColumnName = "driver_id")
    @ManyToOne(optional = false)
    private Driver driverId;

    public Delegation() {
    }

    public Delegation(Integer delegationId) {
        this.delegationId = delegationId;
    }

    public Integer getDelegationId() {
        return delegationId;
    }

    public void setDelegationId(Integer delegationId) {
        this.delegationId = delegationId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Driver getDriverId() {
        return driverId;
    }

    public void setDriverId(Driver driverId) {
        this.driverId = driverId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (delegationId != null ? delegationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Delegation)) {
            return false;
        }
        Delegation other = (Delegation) object;
        if ((this.delegationId == null && other.delegationId != null) || (this.delegationId != null && !this.delegationId.equals(other.delegationId))) {
            return false;
        }
        return true;
    }
     public boolean equalsWithoudId(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Delegation)) {
            return false;
        }
        Delegation other = (Delegation) object;
        if (!this.startDate.equals(other.startDate)) {
            return false;
        }
        else if(!this.endDate.equals(other.endDate)){
            return false;
        }
        else if(!this.description.equals(other.description)){
            return false;
        }
         
        else if(!this.driverId.getDriverId().equals(other.driverId.getDriverId())){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Delegation{" + "delegationId=" + delegationId + ", startDate=" + startDate + ", endDate=" + endDate + ", description=" + description + ", driverId=" + driverId + '}';
    }

  
    
}
