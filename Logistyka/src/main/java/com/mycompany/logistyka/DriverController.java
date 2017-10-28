/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.logistyka;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class DriverController {
  EntityManagerFactory emf;
  EntityManager em;
  Driver driver;

  public DriverController() {
    emf = Persistence.createEntityManagerFactory("logistykaMySql");
    em = emf.createEntityManager();
    this.driver = new Driver();
  }
  public EntityManagerFactory getEmf() {
    return emf;
  }
  public void setEmf(EntityManagerFactory emf) {
    this.emf = emf;
  }
  public EntityManager getEm() {
    return em;
  }
  public void setEm(EntityManager em) {
    this.em = em;
  }
  public Driver getDriver() {
    return driver;
  }
  public void setDriver(Driver driver) {
    this.driver = driver;
  }
  public void insertDriverdb() {
    try {
      EntityTransaction entr = em.getTransaction();
      entr.begin();
      em.persist(driver);
      entr.commit();
    } catch (Exception e) {
    }
  }

  public List<Driver> allDrivers() {
    List<Driver> listDrivers = new ArrayList();
    Query query = em.createNamedQuery("Driver.findAll");
    listDrivers = query.getResultList();
    return listDrivers;
  }
  public Driver findDriverBySurname(String surname) {
    List<Driver> allDrivers = allDrivers();
    for (Driver driver : allDrivers) {
      if (driver.getSurname().equalsIgnoreCase(surname))
        return driver;
    }
    return null;
  }
}
