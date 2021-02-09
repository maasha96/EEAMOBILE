package com.eea.allensellshomes.Model;

import java.util.Date;

public class Appointments {
    private long id;

    private String message;

    private Date createdDate;

    private Date appointmentDateTime;

    private String status;

    private long houseid;

    private String useremail;

    public Appointments(long id, String message, Date createdDate, Date appointmentDateTime, String status, long houseid, String useremail) {
        this.id = id;
        this.message = message;
        this.createdDate = createdDate;
        this.appointmentDateTime = appointmentDateTime;
        this.status = status;
        this.houseid = houseid;
        this.useremail = useremail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(Date appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getHouseid() {
        return houseid;
    }

    public void setHouseid(long houseid) {
        this.houseid = houseid;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Appointments() {
    }
}
