package com.example.achauhan.marketting_aec;

import java.io.Serializable;

/**
 * Created by achauhan on 26/10/17.
 */

public class Order_item  implements Serializable {





    private String dealer_name;
    private String dealer_address;
    private String date;
    private String Shipping_time;
    private int status;
    private String order_id;
    private String mid;
    private String did;
    private int cash;
    private int rate;
    private int credit;
    private int total;
    private int m25watt;
    private int m50watt;
    private int m75watt;
    private int m100watt;
    private int m150watt;
    private int m200watt;
    private int m250watt;



    public String getDealer_address() {
        return dealer_address;
    }

    public void setDealer_address(String dealer_address) {
        this.dealer_address = dealer_address;
    }



    public String getDealer_name() {
        return dealer_name;
    }

    public void setDealer_name(String dealer_name) {
        this.dealer_name = dealer_name;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getShipping_time() {
        return Shipping_time;
    }

    public void setShipping_time(String shipping_time) {
        Shipping_time = shipping_time;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getCredit() {
        return credit;
    }

    public int getM50watt() {
        return m50watt;
    }

    public void setM50watt(int m50watt) {
        this.m50watt = m50watt;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getM25watt() {
        return m25watt;
    }

    public void setM25watt(int m25watt) {
        this.m25watt = m25watt;
    }

    public int getM75watt() {
        return m75watt;
    }

    public void setM75watt(int m75watt) {
        this.m75watt = m75watt;
    }

    public int getM100watt() {
        return m100watt;
    }

    public void setM100watt(int m100watt) {
        this.m100watt = m100watt;
    }

    public int getM150watt() {
        return m150watt;
    }

    public void setM150watt(int m150watt) {
        this.m150watt = m150watt;
    }

    public int getM200watt() {
        return m200watt;
    }

    public void setM200watt(int m200watt) {
        this.m200watt = m200watt;
    }

    public int getM250watt() {
        return m250watt;
    }

    public void setM250watt(int m250watt) {
        this.m250watt = m250watt;
    }
}