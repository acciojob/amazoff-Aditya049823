package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    public void addPartner(String partnerId) {
        orderRepository.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        orderRepository.orderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId) {
        return orderRepository.findOrderbyId(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return orderRepository.findPartnerById(partnerId);
    }

    public List<String> getAllOrders() {
        return orderRepository.findAllOrders();
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return orderRepository.orderByPartnerId(partnerId);
    }

    public Integer unassignedCount() {
        return orderRepository.findUnassigned();
    }

    public Integer orderLeft(String time, String partnerId) {
        String timeleft[]=time.split(":");
        int newtime=Integer.parseInt(timeleft[0])*60+Integer.parseInt(timeleft[1]);
        return orderRepository.leftOrders(newtime,partnerId);
    }

    public String getDeliveryTime(String partnerId) {

        int time=orderRepository.getDeliveryTime(partnerId);
        String HH=String.valueOf(time/60);
        String MM=String.valueOf(time%60);
        if(HH.length()<2)
        {
            HH='0'+HH;
        }
        if(MM.length()<10)
        {
            MM='0'+MM;
        }
        return HH+':'+MM;
    }

    public void delete(String partnerId) {
        orderRepository.delete(partnerId);
    }

    public void deleteById(String orderId) {
        orderRepository.deleteById(orderId);
    }

}
