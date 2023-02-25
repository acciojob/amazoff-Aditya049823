package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    HashMap<String,Order>orderHashMap;
    HashMap<String,DeliveryPartner>deliveryPartnerHashMap;
    HashMap<String, List<String>>orderPartnerMap;
    HashSet<String> ordersNotAssigned;

    public OrderRepository() {
        this.orderHashMap=new HashMap<>();
        this.deliveryPartnerHashMap=new HashMap<>();
        this.orderPartnerMap=new HashMap<>();
        this.ordersNotAssigned=new HashSet<>();
    }


    public void addOrder(Order order) {
        orderHashMap.put(order.getId(),order);
        ordersNotAssigned.add(order.getId());
    }

    public void addPartner(String partnerId) {
        deliveryPartnerHashMap.put(partnerId,new DeliveryPartner(partnerId));
    }

    public void orderPartnerPair(String orderId, String partnerId) {
        if(orderHashMap.containsKey(orderId) && deliveryPartnerHashMap.containsKey(partnerId))
        {
            orderHashMap.put(orderId,orderHashMap.get(orderId));
            deliveryPartnerHashMap.put(partnerId,deliveryPartnerHashMap.get(partnerId));
            List<String>curr=new ArrayList<>();
            if(orderPartnerMap.containsKey(partnerId))
            {
                curr=orderPartnerMap.get(partnerId);
            }
            curr.add(orderId);
            orderPartnerMap.put(partnerId,curr);
        }
    }

    public Order findOrderbyId(String orderId) {
        for(String s:orderHashMap.keySet())
        {
            if(s.equals(orderId))
            {
                return orderHashMap.get(orderId);
            }
        }
        return null;
    }

    public DeliveryPartner findPartnerById(String partnerId) {
        for(String str:deliveryPartnerHashMap.keySet())
        {
            if(str.equals(partnerId))
            {
                return deliveryPartnerHashMap.get(partnerId);
            }
        }
        return null;
    }

    public List<String> findAllOrders() {
        List<String>orderId=new ArrayList<>();
        for(String s:orderHashMap.keySet())
        {
            orderId.add(s);
        }
        return orderId;
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return orderPartnerMap.get(partnerId).size();
    }

    public List<String> orderByPartnerId(String partnerId) {
        List<String>order=new ArrayList<>();
        if(orderPartnerMap.containsKey(partnerId))
        {
            order=orderPartnerMap.get(partnerId);
        }
        return order;
    }

    public Integer findUnassigned() {
        return ordersNotAssigned.size();
    }

    public Integer leftOrders(String time, String partnerId) {
        int tym=Integer.parseInt(time.substring(0,2))*60+Integer.parseInt(time.substring(3,5));
        int cnt=0;
        for(String str:orderPartnerMap.get(partnerId))
        {
            if(orderHashMap.get(str).getDeliveryTime()>tym)
            {
                cnt++;
            }
        }
        return cnt;
    }

    public String getDeliveryTime(String partnerId) {
        int time=0;
        if(orderPartnerMap.containsKey(partnerId))
        {
            for(String s:orderPartnerMap.get(partnerId))
            {
                if(orderHashMap.get(s).getDeliveryTime()>time)
                {
                    time=orderHashMap.get(s).getDeliveryTime();
                }
            }
        }
        int hours=time/60;
        int min=time%60;
        String strhours = Integer.toString(hours);
        if(strhours.length()==1){
            strhours = "0"+strhours;
        }

        String minutes = Integer.toString(min);
        if(minutes.length()==1){
            minutes = "0" + minutes;
        }
        return strhours + ":" + minutes;
    }

    public void delete(String partnerId) {
        if(!orderPartnerMap.isEmpty())
        {
            ordersNotAssigned.addAll((Collection<? extends String>) orderPartnerMap.get(partnerId));
        }
        orderPartnerMap.remove(partnerId);
        deliveryPartnerHashMap.remove(partnerId);
    }

    public void deleteById(String orderId) {
        List<String>list=new ArrayList<>();
        if(orderPartnerMap.containsKey(orderId))
        {
            list=orderPartnerMap.get(orderId);
            for(String str:list)
            {
                if(orderHashMap.containsKey(str))
                {
                    orderHashMap.remove(str);
                }
            }
            orderPartnerMap.remove(orderId);
        }
        if(deliveryPartnerHashMap.containsKey(orderId))
        {
            deliveryPartnerHashMap.remove(orderId);
        }
    }
}
