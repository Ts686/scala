package com.lijie.spark.es

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.elasticsearch.spark._

object ElasticSpark extends App {
  val conf = new SparkConf().setAppName("es-spark").setMaster("local")
  conf.set("es.node", "bigdata01,bigdata02,bigdata03")
  conf.set("es.port", "9200")
  conf.set("es.index.auto.create", "true")
  val sc: SparkContext = new SparkContext(conf)
  val query =
    s"""
      {
      "query": {
      "bool": {
      "must": [
      {
      "match_all": { }
      }
      ],
      "must_not": [ ],
      "should": [ ]
      }
      },
      "from": 0,
      "size": 10,
      "sort": [ ],
      "aggs": { }
      }
     """.stripMargin

  val value: RDD[(String, collection.Map[String, AnyRef])] = sc.esRDD("store", query)
  println(value.collect)
}
