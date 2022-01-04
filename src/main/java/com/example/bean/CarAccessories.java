package com.example.bean;

/**
 * 汽车配件实体类
 *
 * @Author Zhang Chenyang
 * @Date 2022/01/04 20:43
 * @Version 1.0
 */
public class CarAccessories {
    private Integer accessoriesId;
    private String brand, accessoriesName;
    private Boolean availability;
    private Float price;

    public CarAccessories(Integer accessoriesId, String brand, String accessoriesName, Boolean availability, Float price) {
        this.accessoriesId = accessoriesId;
        this.brand = brand;
        this.accessoriesName = accessoriesName;
        this.availability = availability;
        this.price = price;
    }

    public CarAccessories() {
    }

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
