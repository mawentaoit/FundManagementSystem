package controller;

import java.util.Date;

public class Assets {
    private int AssetsID;
    private String Name;
    private  String TypeID;
    private String Model;
    private String Price;
    Date BuyDate = new Date(System.currentTimeMillis());
    private String Status;
    private String Other;

    public Assets(int AssetsID, String Name, String TypeID, String Model, String Price, Date BuyDate, String Status, String Other) {
        this.AssetsID = AssetsID;
        this.Name = Name;
        this.TypeID = TypeID;
        this.Model = Model;
        this.Price = Price;
        this.BuyDate = BuyDate;
        this.Status = Status;
        this.Other = Other;
    }
    public Assets(String Status,int AssetsID) {


        this.Status = Status;
        this.AssetsID = AssetsID;
    }
    public Assets(String Name, String TypeID, String Model, String Price, Date BuyDate, String Status, String Other){
        this.Name = Name;
        this.TypeID = TypeID;
        this.Model = Model;
        this.Price = Price;
        this.BuyDate = BuyDate;
        this.Status = Status;
        this.Other = Other;
    }

    public Assets(int AssetsID, String Name,String TypeID, String Model, String Price,  String Status, String Other){
        this.AssetsID = AssetsID;
        this.Name = Name;
        this.TypeID = TypeID;
        this.Model = Model;
        this.Price = Price;

        this.Status = Status;
        this.Other = Other;
    }
    public Assets(String Name,String TypeID, String Model, String Price,  String Status, String Other){
        this.Name = Name;
        this.TypeID = TypeID;
        this.Model = Model;
        this.Price = Price;

        this.Status = Status;
        this.Other = Other;
    }
    public int getAssetsID() {
        return AssetsID;
    }

    public Date getBuyDate() {
        return BuyDate;
    }

    public String getModel() {
        return Model;
    }

    public String getname() {
        return Name;
    }

    public String getOther() {
        return Other;
    }

    public String getPrice() {
        return Price;
    }

    public String getStatus() {
        return Status;
    }

    public String  getTypeID() {
        return TypeID;
    }

    public void setAssetsID(int AssetsID) {
        this.AssetsID = AssetsID;
    }

    public void setBuyDate(Date BuyDate) {
        this.BuyDate = BuyDate;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public void setname(String Name) {
        this.Name = Name;
    }

    public void setOther(String Other) {
        this.Other = Other;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setTypeID(String  TypeID) {
        this.TypeID = TypeID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.AssetsID;
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Assets other = (Assets) obj;
        if (this.AssetsID != other.AssetsID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "资产编号：" +AssetsID + ", 资产名称：" + Name + ", 所属类型：" +  TypeID+",型号：" +  Model+ ", 价格：" +Price + ",购买日期：" +BuyDate
                +",状态：" +  Status+",备注：" +Other  ;
    }

}
