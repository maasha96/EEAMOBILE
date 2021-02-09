package com.eea.allensellshomes.Dto;

public class FavoritesDto {
    private long vehicleid;

    private String userid;

    public long getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(long vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public FavoritesDto(long vehicleid, String userid) {
        this.vehicleid = vehicleid;
        this.userid = userid;
    }
}
