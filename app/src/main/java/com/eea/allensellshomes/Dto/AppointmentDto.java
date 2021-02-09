package com.eea.allensellshomes.Dto;

import java.util.Date;

public class AppointmentDto {

    private String message;

    private Date appointmentDateTime;

    private Date createdDate;

    private long vehicleid;

    private String useremail;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(Date appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(long vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public AppointmentDto(String message, Date appointmentDateTime, Date createdDate, long vehicleid, String useremail) {
        this.message = message;
        this.appointmentDateTime = appointmentDateTime;
        this.createdDate = createdDate;
        this.vehicleid = vehicleid;
        this.useremail = useremail;
    }

    public AppointmentDto(String message, Date appointmentDateTime, long vehicleid, String useremail) {
        this.message = message;
        this.appointmentDateTime = appointmentDateTime;
        this.vehicleid = vehicleid;
        this.useremail = useremail;
    }
}
