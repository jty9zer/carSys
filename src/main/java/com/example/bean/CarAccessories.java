package com.example.bean;

public class CarAccessories {
    Integer accessoriesId;
    String brand, accessoriesName;
    Boolean availability;
    Float price;

    public CarAccessories(Integer accessoriesId, String brand, String accessoriesName, Boolean availability, Float price) {
        this.accessoriesId = accessoriesId;
        this.brand = brand;
        this.accessoriesName = accessoriesName;
        this.availability = availability;
        this.price = price;
    }

    public CarAccessories(){}

    public Integer getAccessoriesId() {
        return accessoriesId;
    }

    public void setAccessoriesId(Integer accessoriesId) {
        this.accessoriesId = accessoriesId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAccessoriesName() {
        return accessoriesName;
    }

    public void setAccessoriesName(String accessoriesName) {
        this.accessoriesName = accessoriesName;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CarAccessories{" +
                "accessoriesId=" + accessoriesId +
                ", brand='" + brand + '\'' +
                ", accessoriesName='" + accessoriesName + '\'' +
                ", availability=" + availability +
                ", price=" + price +
                '}';
    }
}
