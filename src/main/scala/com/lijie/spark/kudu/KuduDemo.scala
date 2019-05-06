package com.lijie.spark.kudu

import java.util

import org.apache.kudu.client.KuduClient
import org.apache.spark.sql.SparkSession

object KuduDemo extends App {
  val warehouseLocation = "D:/Documents/Downloads/spark-warehouse"
  var kudu_master_hosts = "10.0.43.5:7051"
  //  val spark: SparkSession = getSparkSession("KuduDemo")
  //  val df = spark.read.
  //    options(Map("kudu.master" -> kudu_master_hosts, "kudu.table" -> "user_info")).kudu
  //  df.show

  var kuduBuilder = new KuduClient.KuduClientBuilder(kudu_master_hosts)
  val kuduClient: KuduClient = kuduBuilder.build
  val tables: util.List[String] = kuduClient.getTablesList.getTablesList
  val ts = tables.toArray.toList

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
