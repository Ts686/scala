package com.lijie.spark.sql

import java.util.Date

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

import scala.collection.immutable

object SqlDemo extends App {
  val warehouseLocation = "D:/Documents/Downloads/spark-warehouse"
  val spark: SparkSession = getSparkSession("sql demo")

  //      val frame: DataFrame = spark.sql("CREATE TABLE IF NOT EXISTS person (id STRING, name STRING,age STRING)ROW FORMAT DELIMITED  FIELDS TERMINATED BY ',' STORED AS textfile")
  //  val frame: DataFrame = spark.sql("CREATE TABLE IF NOT EXISTS wms_clnd_finish (id STRING, name STRING,age STRING)ROW FORMAT DELIMITED  FIELDS TERMINATED BY ',' STORED AS textfile")
  //  spark.sql("LOAD DATA INPATH 'hdfs://bigdata01:9000/wordcount/person.txt' INTO TABLE person")
  val personDF: DataFrame = spark.sql("select * from person")
  //  val strings: Array[String] = personDF.rdd.collect.map(line => line.toString())
  //  strings.foreach(println)
  //  personDF.show
  //  println(personDF) //打印dataFrame只是输出schema
  //  val personRdd: RDD[Row] = personDF.rdd
  private val rdd: RDD[Row] = personDF.rdd


  //  Test:::可以在dataFrame上应用map函数，让其内部row元素做处理


  //  val ds: Dataset[String] = personDF.map(line => {
  //    line.mkString("**")
  //  })
  //  ds.rdd.foreach(println)

  //  Test:::
  //  val collect: Array[Row] = personRdd.collect()
  //  personRdd.foreach(println)
  //  println("-" * 60)
  //  println(personRdd, collect)
  //  collect.foreach(println)
  //    val strings: Seq[String] = personDF.schema.map(_.name)

  //  //dataFrame to Dataset 就像 rdd to dataFrame  一样都要隐式转换
  //  The items in DaraFrames are of type Row,
  // which allows you to access each column by ordinal.

//  import spark.implicits._

  val stringsDS: Dataset[String] = personDF.map({
    case Row(id: String, value: String, age: String) => { //类型可以省略
      s"Key: $id, Value: $value, Age: $age"
    }
  })
  //  stringsDS.show()

  //  spark.sql("truncate table person").show

  println("-" * 60)
  //  val records: immutable.IndexedSeq[Record] = (1 to 50).map(i => Record(i, s"val_$i"))
  val records: immutable.IndexedSeq[(Int, String, String)] = (1 to 50).map(i => (i, s"val_$i", new Date().toString))

  //操作关系型数据库
  println("-" * 60)

  val hd_usr_wms_city_2014 = Map("url" -> "jdbc:mysql://172.17.209.6:3306/test",
    "user" -> "root",
    "password" -> "root",
    "dbtable" -> "student2",
    "driver" -> "com.mysql.jdbc.Driver")
  val hd_usr_wms_city_2019 = Map("url" -> "jdbc:mysql://172.17.209.6:3306/test",
    "user" -> "root",
    "password" -> "root",
    "dbtable" -> "student3",
    "driver" -> "com.mysql.jdbc.Driver")
  val load: DataFrame = spark.read.options(hd_usr_wms_city_2014).format("jdbc").load
  load.write.options(hd_usr_wms_city_2019).format("jdbc")

  //  records.toDF("id", "name", "age").write.format("jdbc").options(hd_usr_wms_city_2014).save //.write.insertInto("wms_clnd_finish")
  //    .write.mode("append").saveAsTable("wms_clnd_finish")
  //  val recordsDF: DataFrame = spark.createDataFrame(records)
  //  println(recordsDF)
  //  recordsDF.createOrReplaceTempView("records")


  /**
    * 获取sparkSession
    *
    * @param appName
    * @return
    */
  def getSparkSession(appName: String) = {
    SparkSession.builder()
      .master("local[*]")
      .appName(appName)
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()
  }

}

case class Record(key: Int, value: String)


