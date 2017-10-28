/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.logistyka;

import java.io.Serializable;
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
public class DelegationController implements Serializable{
         /**
   * 
   */
  private static final long serialVersionUID = 1L;
        EntityManagerFactory emf;
         EntityManager em;
         Delegation delegation;

    public DelegationController() {
        emf = Persistence.createEntityManagerFactory("logistykaMySql");
        em = emf.createEntityManager();
        this.delegation=new Delegation();   
  
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

    public Delegation getDelegation() {
        return delegation;
    }

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
    }
    
        public List<Delegation> getAllDelegations() {
           List<Delegation> listDelegation=new ArrayList();
           Query query = em.createNamedQuery("Delegation.findAll");
           listDelegation = query.getResultList();
           return listDelegation;
        }
        public void insertDelegationdb() {
             try {
                 EntityTransaction entr = em.getTransaction();
                 entr.begin();
                 em.persist(delegation);
                 entr.commit();
                 }
                 catch(Exception e){
                 }
        }   
        
      public void removeDelegationdb() {

        try {
            EntityTransaction entr = em.getTransaction();
            entr.begin();
            em.remove(delegation);
            entr.commit();
        }
        catch(Exception e){
        }

    }   
          public void editDelegationdb() {

               try {
                 EntityTransaction entr = em.getTransaction();
                 entr.begin();
                 em.persist(delegation);
                 entr.commit();
               }
               catch(Exception e){
               }
          }  

    public Delegation findDelegation(Delegation delegation) {
        List<Delegation> listDelegation=new ArrayList();
        Query query = em.createNamedQuery("Delegation.findAll");
        listDelegation = query.getResultList();
        int licznik=0;
        for (Delegation delegations : listDelegation) {
            licznik++;       
            if(delegations.equalsWithoudId(delegation)){
                return delegations;
            }
        }
        return null;
    }
     
}
