package com.atguigu.mapreduce.combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReduce extends Reducer<Text, IntWritable,Text,IntWritable> {

    IntWritable intWritable= new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int sum= 0;
        for(IntWritable count:values){
           //求和
           sum+=count.get();
        }
        intWritable.set(sum);
        context.write(key,intWritable);
    }
}

