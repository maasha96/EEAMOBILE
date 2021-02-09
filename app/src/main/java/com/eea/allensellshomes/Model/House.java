package com.eea.allensellshomes.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class House implements Parcelable {

    private int id;

    private String houseType;

    private String category;

    private String title;

    private int lotSize;

    private String covrimg;

    private int NumOfRooms;

    private int NumOfbBaths;

    private int NumOfGarages;

    private String Description;

    private double price;

    private String status;

    private String location;

    private String reservedBy;

    private Date reservedDate;

  List <String> imagePath;
    private byte[] fileData;

    public String getCovrimg() {
        return covrimg;
    }

    public void setCovrimg(String covrimg) {
        this.covrimg = covrimg;
    }

    private String imgName;
    private String imgType;

    private User user;

    private List<Appointments> appointments = new ArrayList<>();

    private List<Favorites> favorites = new ArrayList<>();

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

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

    public List<String> getImagePath() {
        return imagePath;
    }

    public void setImagePath(List<String> imagePath) {
        this.imagePath = imagePath;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Appointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointments> appointments) {
        this.appointments = appointments;
    }

    public List<Favorites> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorites> favorites) {
        this.favorites = favorites;
    }
}
