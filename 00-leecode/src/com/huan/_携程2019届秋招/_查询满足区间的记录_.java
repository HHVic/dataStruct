package com.huan._携程2019届秋招;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author:HuanK
 * @create:2021-03-04 17:37
 */
public class _查询满足区间的记录_ {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Order> orders = new ArrayList<>();
        List<String> res = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date time = format.parse(sc.next());
        for(int i = 0;i < N;++i){
            Order order = new Order();
            order.orderId = sc.next();
            order.inTime = format.parse(sc.next());
            order.outTime = format.parse(sc.next());
            orders.add(order);
        }
        Collections.sort(orders, (order1,order2) -> {
            if(order1.inTime.compareTo(order2.inTime) == 0){
                return order1.orderId.compareTo(order2.orderId);
            }
            return order1.inTime.compareTo(order2.inTime);
        });
        res = helper(orders,time);
        res.forEach(str -> System.out.println(str));
    }

    private static List<String> helper(List<Order> orders, Date time) throws ParseException {
        List<String> res = new ArrayList<>();
        for (Order order : orders){
            if(time.compareTo(order.inTime) > 0 && time.compareTo(order.outTime) <= 0) res.add(order.orderId);
        }
        return res;
    }

    private static class Order{
        String orderId;
        Date inTime;
        Date outTime;
    }
}
