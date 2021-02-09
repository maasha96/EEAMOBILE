package com.eea.allensellshomes.Dto;

public class HouseRequest {

    private Long houseId;

    private  double housePrice;

    public HouseRequest(Long houseId, double housePrice) {
        this.houseId = houseId;
        this.housePrice = housePrice;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public double getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(double housePrice) {
        this.housePrice = housePrice;
    }
}
