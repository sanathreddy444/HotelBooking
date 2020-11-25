package com.example.hotelbooking;

import android.os.Parcel;
import android.os.Parcelable;

public class Product_Room implements Parcelable {
    String id;
    String name;
    String qty;
    String price;
    String description;

    String rent;
    String roomtype;

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getBookingstatus() {
        return bookingstatus;
    }

    public void setBookingstatus(String bookingstatus) {
        this.bookingstatus = bookingstatus;
    }

    String bookingstatus;

    public String getPvaliddays() {
        return pvaliddays;
    }

    public void setPvaliddays(String pvaliddays) {
        this.pvaliddays = pvaliddays;
    }

    public String getPdepartment() {
        return pdepartment;
    }

    public void setPdepartment(String pdepartment) {
        this.pdepartment = pdepartment;
    }

    String pvaliddays;
    String pdepartment;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.rent);
        dest.writeString(this.description);
        dest.writeString(this.roomtype);
        dest.writeString(this.bookingstatus);
    }

    public Product_Room() {
    }

    protected Product_Room(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.rent = in.readString();
        this.description = in.readString();
        this.roomtype=in.readString();
        this.bookingstatus=in.readString();
    }

    public static final Creator<Product_Room> CREATOR = new Creator<Product_Room>() {
        @Override
        public Product_Room createFromParcel(Parcel source) {
            return new Product_Room(source);
        }

        @Override
        public Product_Room[] newArray(int size) {
            return new Product_Room[size];
        }
    };
}
