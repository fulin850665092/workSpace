package com.atguigu.mapreduce.wordcounttest;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordtestMap  extends Mapper<LongWritable , Text,Text, IntWritable> {

    Text k=  new Text();
    IntWritable v= new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line= value.toString();

        String[] split = line.split(" ");

        for (String str:split
             ) {
            k.set(str);
            context.write(k,v);
        }
    }
}
