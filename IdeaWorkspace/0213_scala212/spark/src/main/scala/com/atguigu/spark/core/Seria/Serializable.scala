package com.atguigu.spark.core.Seria

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Serializable {

  def main(args: Array[String]): Unit = {

    //1.初始化配置信息及SparkContext
    val sparkConf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)

    //2.创建一个RDD
    val rdd: RDD[String] = sc.parallelize(Array("hadoop", "spark", "hive", "atguigu"))

    //3.创建一个Search对象
    val search = new Search("hadoop")

    //4.运用第一个过滤函数并打印结果
    val match1: RDD[String] = search.getMatch1(rdd)
    match1.collect().foreach(println)

    //4.运用第一个过滤函数并打印结果
    val match2: RDD[String] = search.getMatche2(rdd)
    match2.collect().foreach(println)

  }
  class Search(query:String) extends Serializable {

    //过滤出包含字符串的数据
    def isMatch(s: String): Boolean = {
      s.contains(query)
    }

    //过滤出包含字符串的RDD
    def getMatch1(rdd: RDD[String]): RDD[String] = {
      rdd.filter(isMatch)
    }

    //过滤出包含字符串的RDD
    def getMatche2(rdd: RDD[String]): RDD[String] = {
//      val s = query       //这样类可以不用序列化，s是String类型，在Driver，直接传递Executor
//      rdd.filter(x => x.contains(s))
      rdd.filter(x => x.contains(query))  //这样必须序列化类
    }
  }
}
