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
        return orderRepository.leftOrders(time,partnerId);
    }

    public String getDeliveryTime(String partnerId) {
        return orderRepository.getDeliveryTime(partnerId);
    }

    public void delete(String partnerId) {
        orderRepository.delete(partnerId);
    }

    public void deleteById(String orderId) {
        orderRepository.deleteById(orderId);
    }

}
