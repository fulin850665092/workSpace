package com.atguigu.shuffer.comparator;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {

    private String order_id; // 订单id号
    private Double price; // 价格

    public OrderBean() {
        super();
    }

    public OrderBean(String order_id, Double price) {
        super();
        this.order_id = order_id;
        this.price = price;
    }


    public void write(DataOutput out) throws IOException {
        out.writeUTF(order_id);
        out.writeDouble(price);
    }


    public void readFields(DataInput in) throws IOException {
        order_id = in.readUTF();
        price = in.readDouble();
    }

    @Override
    public String toString() {
        return order_id + "\t" + price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // 二次排序

    public int compareTo(OrderBean o) {

//        int result;
//        if (order_id > o.getOrder_id()) {
//            result = 1;
//        } else if (order_id < o.getOrder_id()) {
//            result = -1;
//        } else {
//            // 价格倒序排序
//            result = price > o.getPrice() ? -1 : 1;
//        }
//        return result;
        return this.order_id.compareTo(o.getOrder_id())==0?-this.price.compareTo(o.getPrice()):this.order_id.compareTo(o.getOrder_id());
    }
}
