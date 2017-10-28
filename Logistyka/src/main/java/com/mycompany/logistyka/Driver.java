/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.logistyka;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "driver")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Driver.findAll", query = "SELECT d FROM Driver d"),
    @NamedQuery(name = "Driver.findByDriverId", query = "SELECT d FROM Driver d WHERE d.driverId = :driverId"),
    @NamedQuery(name = "Driver.findByName", query = "SELECT d FROM Driver d WHERE d.name = :name"),
    @NamedQuery(name = "Driver.findBySurname", query = "SELECT d FROM Driver d WHERE d.surname = :surname")})
public class Driver implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "driver_id")
  private Integer driverId;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 25)
  @Column(name = "name")
  private String name;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 25)
  @Column(name = "surname")
  private String surname;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "driverId")
  private Collection<Delegation> delegationCollection;

  public Driver() {
  }

  public Driver(Integer driverId) {
    this.driverId = driverId;
  }

  public Driver(Integer driverId, String name, String surname) {
    this.driverId = driverId;
    this.name = name;
    this.surname = surname;
  }

  public Integer getDriverId() {
    return driverId;
  }

  public void setDriverId(Integer driverId) {
    this.driverId = driverId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @XmlTransient
  public Collection<Delegation> getDelegationCollection() {
    return delegationCollection;
  }

  public void setDelegationCollection(
      Collection<Delegation> delegationCollection) {
    this.delegationCollection = delegationCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (driverId != null ? driverId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not
    // set
    if (!(object instanceof Driver)) {
      return false;
    }
    Driver other = (Driver) object;
    if ((this.driverId == null && other.driverId != null)
        || (this.driverId != null && !this.driverId.equals(other.driverId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.mycompany.logistyka.Driver[ driverId=" + driverId + " ]";
  }

}
