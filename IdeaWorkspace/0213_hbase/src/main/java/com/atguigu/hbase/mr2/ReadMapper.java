package com.atguigu.hbase.mr2;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public  class ReadMapper extends Mapper<LongWritable, Text,ImmutableBytesWritable,Put> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line= value.toString();

        String[] split = line.split("\t");
        if(split.length<3){
            return;
        }
        Put put = new Put(Bytes.toBytes(split[0]));
        //添加lie
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes(split[1]));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("sex"),Bytes.toBytes(split[2]));

        context.write(new ImmutableBytesWritable(Bytes.toBytes(split[0])),put);
    }
}
