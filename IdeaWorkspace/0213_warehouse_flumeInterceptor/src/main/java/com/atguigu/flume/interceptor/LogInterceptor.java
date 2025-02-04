package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

public class LogInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }
    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        String line= new String(body, Charset.forName("utf-8"));
        if(JSONUtils.CheckJson(line)){
           return event;
       }else {
           return null;
       }
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        Iterator<Event> iterator = events.iterator();
        if (iterator.hasNext()){
            Event next = iterator.next();
            if (intercept(next)==null){
                iterator.remove();
            }
        }
        return events;
    }
    public  static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new LogInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
    @Override
    public void close() {

    }
}
