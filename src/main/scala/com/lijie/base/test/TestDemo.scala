package com.lijie.base.test

import org.apache.spark.sql.SparkSession

object TestDemo extends App {

  val myMap: Map[String, String] = Map("key1" -> "value")
  val value1: Option[String] = myMap.get("key1")
  val value2: Option[String] = myMap.get("key2")

  println(value1.get) // Some("value1")
  println(value2) // None

  val json =
    """
      |
    """.stripMargin

  val warehouseLocation = "D:/Documents/Downloads/spark-warehouse"
  val spark: SparkSession = getSparkSession("aa")

  import spark.implicits._

  //  val tuples: Seq[(Int, String, String, Record.type)] = Seq((1, "a", "hello", Record))
  //  val value: Any = Seq((1, "a", "hello")).toDF("aa", "bb", "cc")
  //  println(value)

  def getSparkSession(appName: String) = {
    SparkSession.builder()
      .master("local[*]")
      .appName(appName)
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()
  }
}
