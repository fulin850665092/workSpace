package com.atguigu.groupingcomparator;


import com.atguigu.bean.OrderBean;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class WritableComparat extends WritableComparator {

    protected WritableComparat() {
        super(OrderBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        OrderBean aBean= (OrderBean) a;
        OrderBean bBean= (OrderBean) b;

        int result;

        if (aBean.getOrder_id() > bBean.getOrder_id()) {
            result = 1;
        } else if (aBean.getOrder_id() < bBean.getOrder_id()) {
            result = -1;
        } else {
            result = 0;
        }
        return  result;
    }
}
