package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {

    HashMap<String, Order> orderHashMap = new HashMap<>();
    HashMap<String, DeliveryPartner> deliveryPartnerHashMap = new HashMap<>();
    HashMap<String, String> orderPartnerMap = new HashMap<>();
    HashMap<String, List<String>> partnerOrderMap = new HashMap<>();


    public void addOrder(Order order) {
        orderHashMap.put(order.getId(), order);
    }

    public void addPartner(String partnerId) {
        deliveryPartnerHashMap.put(partnerId,new DeliveryPartner(partnerId));
    }

    public void orderPartnerPair(String orderId, String partnerId) {
        if(orderHashMap.containsKey(orderId) && deliveryPartnerHashMap.containsKey(partnerId))
        {
            orderPartnerMap.put(orderId,partnerId);
            List<String>orderList=new ArrayList<>();
            if(partnerOrderMap.containsKey(partnerId))
            {
                orderList=partnerOrderMap.get(partnerId);
            }
            orderList.add(orderId);
            partnerOrderMap.put(partnerId,orderList);
        }
    }

    public Order findOrderbyId(String orderId) {
        return orderHashMap.get(orderId);
    }

    public DeliveryPartner findPartnerById(String partnerId) {
        return deliveryPartnerHashMap.get(partnerId);
    }

    public List<String> findAllOrders() {
        List<String>allOrders=new ArrayList<>();
        for(String orders:orderHashMap.keySet())
        {
            allOrders.add(orders);
        }
        return allOrders;
    }

    public int getOrderCountByPartnerId(String partnerId) {
        return partnerOrderMap.get(partnerId).size();
    }

    public List<String> orderByPartnerId(String partnerId) {
        return partnerOrderMap.get(partnerId);
    }

    public int findUnassigned() {
        return orderHashMap.size()-orderPartnerMap.size();
    }

    public int leftOrders(int newtime, String partnerId) {
        int count=0;
        List<String>order=partnerOrderMap.get(partnerId);
        for(String orders:order)
        {
            int time=orderHashMap.get(orders).getDeliveryTime();
            if(time>newtime)
            {
                count++;
            }
        }
        return count;
    }

    public int getDeliveryTime(String partnerId) {
        int maxTime=0;
        List<String>orders=partnerOrderMap.get(partnerId);
        for(String order:orders)
        {
            int currTime=orderHashMap.get(order).getDeliveryTime();
            maxTime=Math.max(maxTime,currTime);
        }
        return maxTime;
    }

    public void delete(String partnerId) {
        deliveryPartnerHashMap.remove(partnerId);
        List<String>listofOrders=partnerOrderMap.get(partnerId);
        partnerOrderMap.remove(partnerId);
        for(String order:listofOrders)
        {
            orderPartnerMap.remove(order);
        }
    }

    public void deleteById(String orderId) {
        orderHashMap.remove(orderId);
        String partnerId=orderPartnerMap.get(orderId);
        orderPartnerMap.remove(orderId);
        partnerOrderMap.get(partnerId).remove(orderId);

        deliveryPartnerHashMap.get(partnerId).setNumberOfOrders(partnerOrderMap.get(partnerId).size());

    }
}