package controller;

import java.util.Date;

public class AssetsTrjn {
    private int jourNo;
    private String FromAcc;
    private int AssetsID;
    private int PersonID;
    private String purpose;
    private String Other;
    private String sAssetsID;
    private String sPersonID;
    Date RegDate = new Date(System.currentTimeMillis());




    public AssetsTrjn(int jourNO, String FromAcc, String sAssetsID, Date RegDate, String sPersonID, String purpose, String Other) {
        this.jourNo = jourNO;
        this.FromAcc = FromAcc;
        this.sAssetsID = sAssetsID;
        this.sPersonID = sPersonID;
        this.purpose = purpose;
        this.RegDate = RegDate;
        this.Other = Other;
    }
    public AssetsTrjn(int AssetsID, int PersonID, String purpose, String Other) {
        this.AssetsID = AssetsID;
        this.PersonID = PersonID;
        this.purpose = purpose;
        this.Other = Other;
    }

    public AssetsTrjn(String FromAcc, int AssetsID, int PersonID, String purpose, String Other) {
        this.FromAcc = FromAcc;
        this.AssetsID = AssetsID;
        this.PersonID = PersonID;
        this.purpose = purpose;
        this.Other = Other;
    }
    public AssetsTrjn( int PersonID, String purpose, String Other) {

        this.PersonID = PersonID;
        this.purpose = purpose;
        this.Other = Other;
    }
    public AssetsTrjn(int jourNo, int AssetsID, int PersonID, String purpose, String Other) {
        this.jourNo = jourNo;
        this.AssetsID = AssetsID;
        this.PersonID = PersonID;
        this.purpose = purpose;
        this.Other = Other;
    }

    public AssetsTrjn(int jourNo, String FromAcc, int AssetsID, int PersonID, String purpose, String Other) {
        this.jourNo = jourNo;
        this.FromAcc = FromAcc;
        this.AssetsID = AssetsID;
        this.PersonID = PersonID;
        this.purpose = purpose;
        this.Other = Other;
    }

    public AssetsTrjn(int jourNo, String FromAcc, int AssetsID, Date RegDate, int PersonID, String purpose, String Other) {
        this.jourNo = jourNo;
        this.FromAcc = FromAcc;
        this.AssetsID = AssetsID;
        this.RegDate = RegDate;
        this.PersonID = PersonID;
        this.purpose = purpose;
        this.Other = Other;
    }

    public AssetsTrjn(int jourNo) {
        this.jourNo = jourNo;
    }
    public Date getRegDate() {
        return RegDate;
    }

    public void setRegDate(Date RegDate) {
        this.RegDate = RegDate;
    }
    public int getJourNo() {
        return jourNo;
    }

    public void setJourNo(int jourNo) {
        this.jourNo = jourNo;
    }

    public String getFromAcc() {
        return FromAcc;
    }

    public void setFromAcc(String FromAcc) {
        this.FromAcc = FromAcc;
    }

    public int getAssetsID() {
        return AssetsID;
    }

    public void setAssetsID(int AssetsID) {
        this.AssetsID = AssetsID;
    }

    public String getsAssetsID() {
        return sAssetsID;
    }
    public String getsPersonID() {
        return sPersonID;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int personID) {
        this.PersonID = PersonID;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String Other) {
        this.Other = Other;
    }


}
