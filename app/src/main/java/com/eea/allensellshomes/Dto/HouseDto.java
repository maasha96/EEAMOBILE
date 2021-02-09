package com.eea.allensellshomes.Dto;

public class HouseDto {

    private long houseId;
    private String status;
    private String reservedBy;

    public HouseDto(long houseId, String status, String reservedBy) {
        this.houseId = houseId;
        this.status = status;
        this.reservedBy = reservedBy;
    }
}
