package com.eea.allensellshomes.Dto;

import java.util.Date;

public class ReservedHousesDto {

    private int id;

    private String houseType;

    private String category;

    private String title;

    private String covrimg;

    private int lotSize;

    private int NumOfRooms;

    private int NumOfbBaths;

    private int NumOfGarages;

    private String Description;

    private double price;

    private String status;

    private String location;

    private String reservedBy;

    private Date reservedDate;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCovrimg() {
        return covrimg;
    }

    public void setCovrimg(String covrimg) {
        this.covrimg = covrimg;
    }

    private String imagePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    public int getNumOfRooms() {
        return NumOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        NumOfRooms = numOfRooms;
    }

    public int getNumOfbBaths() {
        return NumOfbBaths;
    }

    public void setNumOfbBaths(int numOfbBaths) {
        NumOfbBaths = numOfbBaths;
    }

    public int getNumOfGarages() {
        return NumOfGarages;
    }

    public void setNumOfGarages(int numOfGarages) {
        NumOfGarages = numOfGarages;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }

    public Date getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(Date reservedDate) {
        this.reservedDate = reservedDate;
    }
}
