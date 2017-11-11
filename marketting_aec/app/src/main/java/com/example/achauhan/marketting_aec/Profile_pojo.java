package com.example.achauhan.marketting_aec;

/**
 * Created by achauhan on 17/10/17.
 */

public class Profile_pojo {


    String date, mname, maddress, mjoining_date, mvehicle_number, mphone_number, malternate_phone_number;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMaddress() {
        return maddress;
    }

    public void setMaddress(String maddress) {
        this.maddress = maddress;
    }

    public String getMjoining_date() {
        return mjoining_date;
    }

    public void setMjoining_date(String mjoining_date) {
        this.mjoining_date = mjoining_date;
    }

    public String getMvehicle_number() {
        return mvehicle_number;
    }

    public void setMvehicle_number(String mvehicle_number) {
        this.mvehicle_number = mvehicle_number;
    }

    public String getMphone_number() {
        return mphone_number;
    }

    public void setMphone_number(String mphone_number) {
        this.mphone_number = mphone_number;
    }

    public String getMalternate_phone_number() {
        return malternate_phone_number;
    }

    public void setMalternate_phone_number(String malternate_phone_number) {
        this.malternate_phone_number = malternate_phone_number;
    }

    public Profile_pojo(String date, String mname, String maddress, String mjoining_date, String mvehicle_number, String mphone_number, String malternate_phone_number) {
        this.date = date;

        this.mname = mname;
        this.maddress = maddress;
        this.mjoining_date = mjoining_date;
        this.mvehicle_number = mvehicle_number;
        this.mphone_number = mphone_number;
        this.malternate_phone_number = malternate_phone_number;
    }
}