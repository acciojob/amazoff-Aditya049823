package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        this.id=id;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        String s[]=deliveryTime.split(":");
        int hr=Integer.parseInt(s[0]);
        int min=Integer.parseInt(s[1]);
        int hrInMinutes=hr*60;
        this.deliveryTime=hrInMinutes+min;

    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
