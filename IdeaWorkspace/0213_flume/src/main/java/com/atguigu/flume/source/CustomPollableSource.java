package com.atguigu.flume.source;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.FlumeException;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.AbstractPollableSource;

import java.util.ArrayList;
import java.util.List;

//自带轮询
public class CustomPollableSource extends AbstractPollableSource {
    private String prefix=null;
    private Integer batchSiez=null;
    private List<Event> list=new ArrayList<Event>();

    //实现读写的逻辑，循环调用x
    protected Status doProcess() throws EventDeliveryException {
        try {
            //1.读取数据封装成Event
            for (int i = 0; i <batchSiez ; i++) {
                String message=prefix+'-'+i;
                Event event = EventBuilder.withBody(message.getBytes());
                list.add(event);
            }
            //2.将event交给channelProceor处理，
                 //一个channel对应一个channelProceor（自己会实例化好）
            ChannelProcessor channelProcessor = getChannelProcessor();
                //有两个方法，看每次读一个,调用processEvent()，方法里面处理事物
                        //channelProcessor.processEvent(even);
                // 还是读一个batchSiez，调用processEventBatch(list)，方法里面处理事物
                channelProcessor.processEventBatch(list);
                //每次清空一下，否则会重复
                list.clear();
                //返回是READY，就立即调用下一次的doProcess
                //返回是BACKOFF，当数据处理出现问题，返回
                return  Status.READY;
        } catch (Exception e) {
                e.printStackTrace();
                //数据去读一半有问题也需要清除数据
                list.clear();
                return  Status.BACKOFF;
        }


    }
    //可以读取配置文件的参数
    protected void doConfigure(Context context) throws FlumeException {
        prefix=context.getString("prefix");
        batchSiez=context.getInteger("batchSize",1);
    }
    //建立连接，初始化操作（类加载调用一次）
    protected void doStart() throws FlumeException {
        System.out.println("==========================");
        System.out.println("Custom source start!");
        System.out.println("==========================");
    }
    //关闭资源（方法结束调用一次）
    protected void doStop() throws FlumeException {
        System.out.println("==========================");
        System.out.println("Custom source stop!");
        System.out.println("==========================");
    }
}
