package com.eea.allensellshomes.Model;

import java.util.Date;

public class Favorites {

    private long id;

    private House house;

    private String userid;

    private Date createdAt;

    private Date updatedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Favorites(long id, House house, String userid, Date createdAt, Date updatedAt) {
        this.id = id;
        this.house = house;
        this.userid = userid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}


