
package com.mycompany.logistyka;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private DelegationController delegationController;
  private DriverController driverController;
  private ScheduleModel eventModel;
  private Delegation previousDelegation;
  private ScheduleEvent event = new DefaultScheduleEvent();
  private Driver driver;
  private List<Driver> drivers;

  public ScheduleView()
      throws SQLException, ClassNotFoundException, ParseException {
    eventModel = new DefaultScheduleModel();
    delegationController = new DelegationController();
    driverController = new DriverController();
    drivers = driverController.allDrivers();

    List<Delegation> listDelegations = delegationController.getAllDelegations();
    for (int i = 0; i < listDelegations.size(); i++) {
      Delegation user = listDelegations.get(i);
      if (user.getStartDate() == null || user.getEndDate() == null)
        continue;
      DefaultScheduleEvent scheduleEvent = new DefaultScheduleEvent(
          user.getDriverId().getSurname(), user.getStartDate(),
          user.getEndDate());
      scheduleEvent.setDescription(user.getDescription());
      switch (user.getDriverId().getDriverId()) {
        case (1) : {
          scheduleEvent.setStyleClass("kolorRed");
          break;
        }
        case (2) : {
          scheduleEvent.setStyleClass("kolorBlue");
          break;
        }
        case (3) : {
          scheduleEvent.setStyleClass("kolorYellow");
          break;
        }
        case (4) : {
          scheduleEvent.setStyleClass("kolorBrown");
          break;
        }
        case (5) : {
          scheduleEvent.setStyleClass("kolorGrey");
          break;
        }
      }
      eventModel.addEvent(scheduleEvent);
    }
  }

  public Driver getDriver() {
    return driver;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
  }

  public List<Driver> getDrivers() {
    return drivers;
  }

  public void setDrivers(List<Driver> drivers) {
    this.drivers = drivers;
  }

  public Delegation getPreviousDelegation() {
    return previousDelegation;
  }

  public void setPreviousDelegation() {
    this.previousDelegation = new Delegation();
    this.previousDelegation.setDescription(event.getDescription());
    Driver driver = driverController.findDriverBySurname(event.getTitle());
    this.previousDelegation.setStartDate(event.getStartDate());
    this.previousDelegation.setEndDate(event.getEndDate());
    this.previousDelegation.setDriverId(driver);
  }

  public ScheduleModel getEventModel() {
    return eventModel;
  }

  public ScheduleEvent getEvent() {

    return event;
  }

  public void setEvent(ScheduleEvent event) {

    this.event = event;
  }

  public void addEvent(ActionEvent actionEvent) {

    if (event.getId() == null) {
      eventModel.addEvent(event);
      Delegation delegation = new Delegation();
      delegation.setStartDate(event.getStartDate());
      delegation.setEndDate(event.getEndDate());
      delegation.setDescription(event.getDescription());
      Driver driver = driverController.findDriverBySurname(event.getTitle());
      delegation.setDriverId(driver);
      delegationController.setDelegation(delegation);
      delegationController.insertDelegationdb();
    } else {
      eventModel.updateEvent(event);
      Driver driver = driverController.findDriverBySurname(event.getTitle());
      Delegation delegation = delegationController
          .findDelegation(previousDelegation);
      if (delegation == null) {
        return;
      }
      delegation.setDescription(event.getDescription());
      delegation.setDriverId(driver);
      delegation.setStartDate(event.getStartDate());
      delegation.setEndDate(event.getEndDate());
      delegationController.setDelegation(delegation);
      delegationController.editDelegationdb();
    }

    event = new DefaultScheduleEvent();

  }
  public void removeEvent(ActionEvent actionEvent) {
    eventModel.deleteEvent(event);
    Delegation delegation = new Delegation();
    delegation.setStartDate(event.getStartDate());
    delegation.setEndDate(event.getEndDate());
    delegation.setDescription(event.getDescription());
    Driver driver = driverController.findDriverBySurname(event.getTitle());
    delegation.setDriverId(driver);
    System.out.println("del prev " + delegation);
    delegationController.setDelegation(delegation);
    Delegation del = delegationController.findDelegation(delegation);
    if (del == null) {
      return;
    }
    delegationController.setDelegation(del);
    delegationController.removeDelegationdb();

    event = new DefaultScheduleEvent();
  }

  public void onEventSelect(SelectEvent selectEvent) {

    event = (ScheduleEvent) selectEvent.getObject();
    this.setPreviousDelegation();

  }

  public void onDateSelect(SelectEvent selectEvent) {

    Date startDate = (Date) selectEvent.getObject();

    Date endDate = (Date) selectEvent.getObject();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(startDate);
    calendar.add(Calendar.HOUR, 8);
    startDate = calendar.getTime();
    calendar.setTime(endDate);
    calendar.add(Calendar.HOUR, 16);
    endDate = calendar.getTime();
    event = new DefaultScheduleEvent("", startDate, endDate);
  }

}
