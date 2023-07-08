package org.example.domain;

import java.util.Date;

public class PubWifi {

    private String dist;
    private String mgrNo;
    private String region;
    private String mainNm;
    private String address;
    private String addressDetail;
    private String installFloor;
    private String installMby;
    private String installTy;
    private String serviceSe;
    private String networkTy;
    private String installYear;
    private String isOutdoor;
    private String connectEnv;
    private String longitude;
    private String latitude;
    private String workDate;

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getMgrNo() {
        return mgrNo = mgrNo;
    }

    public void setMgrNo(String mgrNo) {
        this.mgrNo = mgrNo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMainNm() {
        return mainNm;
    }

    public void setMainNm(String mainNm) {
        this.mainNm = mainNm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getInstallFloor() {
        return installFloor;
    }

    public void setInstallFloor(String installFloor) {
        this.installFloor = installFloor;
    }

    public String getInstallTy() {return installTy;}

    public void setInstallTy(String installTy) {
        this.installTy = installTy;
    }


    public String getInstallMby() {
        return installMby;
    }

    public void setInstallMby(String installMby) {
        this.installMby = installMby;
    }


    public String getServiceSe() {
        return serviceSe;
    }

    public void setServiceSe(String serviceSe) {
        this.serviceSe = serviceSe;
    }

    public String getNetworkTy(){
        return networkTy;
    }

    public void setNetworkTy(String networkTy) {
        this.networkTy = networkTy;
    }

    public String getInstallYear() {
        return installYear;
    }

    public void setInstallYear(String installYear) {
        this.installYear = installYear;
    }

    public String getIsOutdoor(){
        return isOutdoor;
    }

    public void setIsOutdoor(String isOutdoor) {
        this.isOutdoor = isOutdoor;
    }

    public String getConnectEnv() {
        return connectEnv;
    }

    public void setConnectEnv(String connectEnv) {
        this.connectEnv = connectEnv;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }





    //북마크
    private int id;
    private String name;
    private String ssid;
    private String password;
    private int groupId;
    private Date createDate;
    private Date modifiedDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public double getLatitudeAsDouble() {
        return Double.parseDouble(latitude);
    }

    public double getLongitudeAsDouble() {
        return Double.parseDouble(longitude);
    }

    public void setLatitudeFromDouble(double latitudeValue) {
        this.latitude = String.valueOf(latitudeValue);
    }

    public void setLongitudeFromDouble(double longitudeValue) {
        this.longitude = String.valueOf(longitudeValue);
    }

}
