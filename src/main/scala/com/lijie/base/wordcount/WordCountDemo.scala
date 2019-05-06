package com.lijie.base.wordcount

import java.util.Date

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.io.Source

object WordCountDemo extends App {
  val conf: SparkConf = new SparkConf().setAppName("WordCount Demo").setMaster("local")
  val sc = new SparkContext(conf)
  val intRdd: RDD[Int] = sc.parallelize(Array(1, 2, 3, 4))
  val value: RDD[Int] = intRdd.filter(_ < 4)
  val value1: RDD[(String, Int)] = sc.textFile("hdfs://bigdata01:9000/wordcount/input/words.txt").
    flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
  val first: (String, Int) = value1.first
  println(first)
  //    saveAsTextFile("hdfs://bigdata01:9000/wordcount/output3")

  var rdd1 = sc.parallelize(List(("hello", 1), ("world", 1), ("java", 9)))
  var rdd2 = sc.parallelize(List(("mysql", 1), ("world", 10), ("java", 20), ("oracle", 1)))
  val rdd3: RDD[(String, Int)] = rdd1.union(rdd2)
  val groupRdd: RDD[(String, Iterable[Int])] = rdd3.groupByKey
  val collect: Array[(String, Iterable[Int])] = groupRdd.collect
  println(collect.toArray)
  //  val value: RDD[(String, Int)] = groupRdd.map(x => {
  //    (x._1, x._2.sum)
  //  })
  //  println(groupRdd.partitions.length)

}
