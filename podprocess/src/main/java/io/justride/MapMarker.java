package io.justride;

import java.io.Serializable;

public class MapMarker implements Serializable {
    private Double latitude;
    private Double longitude;
    private Double violationSpeed;
    private Double averageSpeed;
    private String address;
    private String policyNumber;
    private String customerName;
    private String gendre;
    private Integer age;
    private String carPlate;

    public MapMarker(Double latitude, Double longitude, Double violationSpeed, Double averageSpeed, String address, String policyNumber, String customerName, String gendre, Integer age, String carPlate) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.violationSpeed = violationSpeed;
        this.averageSpeed = averageSpeed;
        this.address = address;
        this.policyNumber = policyNumber;
        this.customerName = customerName;
        this.gendre = gendre;
        this.age = age;
        this.carPlate = carPlate;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getViolationSpeed() {
        return violationSpeed;
    }

    public Double getAverageSpeed() {
        return averageSpeed;
    }

    public String getAddress() {
        return address;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getGendre() {
        return gendre;
    }

    public Integer getAge() {
        return age;
    }

    public String getCarPlate() {
        return carPlate;
    }

    @Override
    public String toString() {
        return "MapMarker{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", violationSpeed=" + violationSpeed +
                ", averageSpeed=" + averageSpeed +
                ", address='" + address + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", gendre='" + gendre + '\'' +
                ", age='" + age + '\'' +
                ", carPlate='" + carPlate + '\'' +
                '}';
    }
}
