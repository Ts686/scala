package com.lijie.base.caseClass

import java.util.Date

import com.lijie.base.file.Person
import org.apache.spark.sql.SparkSession

import scala.util.Random

case class SubmitTask(id: String, name: String)

//{
//  name match {
//    case "hello" => {
//
//    }
//    case "world" => {
//
//    }
//  }
//}

case class HeartBeat(time: Long)

case object CheckTimeOutTask

object Demo extends App {
  val tup = (1, 3, 5)
  tup match {
    case (1, x, y) => println(s"1, $x , $y")
    case (_, z, 5) => println(z)
    case _ => println("else")
  }


  val arr: Array[Product with Serializable] = Array(CheckTimeOutTask, HeartBeat(12333), SubmitTask("0001", "task-0001"))
  var aa = arr(Random.nextInt(arr.length)) match {
    case SubmitTask(id, name) => {
      println(s"$id, $name") //前面需要加上s, $id直接取id的值
    }
    case HeartBeat(time) => {
      println(time)
    }
    case CheckTimeOutTask => {
      println("check")
    }
  }
  println(aa)

  println("_" * 60)

  //字符串匹配
  val arrStr = Array("hello", "world", "java")
  val name = arrStr(Random.nextInt(arrStr.length))

  name match {
    case "hello" => println(s"匹配到" + name)
    case "world" => println(s"匹配到" + name)
    case "java" => println(s"匹配到" + name)
  }

  println("_" * 60)

  val lst = List(3, List(3, -1))
  lst match {
    case 0 :: Nil => println("only 0")
    case x :: y :: Nil => println(s"x: $x y: $y")
    case 0 :: tail => println("0 ...")
    case _ => println("something else")
  }

  //定义方法模式匹配
  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }

  val alice = new Person("Alice", 25)
  val bob = new Person("Bob", 32)
  val charlie = new Person("Charlie", 32)

  for (person <- List(alice, bob, charlie)) {
    person match {
      case Person("Alice", 25) => println("Hi Alice!")
      case Person("Bob", 32) => println("Hi Bob!")
      case Person(name, age) =>
        println("Age: " + age + " year, name: " + name + "?")
    }
  }


//  def wmsCldn(spark: SparkSession, tablePropertys: List[(String, String, String)]) = {
//    var count = 1
//    tablePropertys.foreach {
//      case (tableName, pks, partition) => {
//        val startTime = System.currentTimeMillis()
//        val preff = s"[${count}/${tablePropertys.size}]"
//        println(s"${preff} 正在执行的表为：${tableName}")
//        //获取对应的列
//        val columns = spark.sql(s"select * from dc_ods.${tableName}_ods limit 1")
//          .schema.map(_.name).mkString(",")
//        //先删除表数据
//        spark.sql(s"truncate table dc_ods.${tableName}_ods").show()
//        val pk = pks.replace(":", ",")
//        val sql = s" INSERT overwrite TABLE dc_ods.${tableName}_ods partition(partition_date)  " +
//          s" select ${columns} from ( select *,from_unixtime(unix_timestamp(),'yyyy-MM-dd HH:mm:ss') AS ods_update_time," +
//          s" date_format(nvl(${partition},'2017-01-01'),'yyyyMM') AS partition_date," +
//          s" row_number() over(PARTITION BY ${pk}  ORDER BY nvl(${partition},to_date('2017-01-01', 'yyyy-MM-dd'))  DESC) rn" +
//          s" from bdp_tmp.${tableName}_tmp ) main where rn=1 "
//        println(s"${preff} 解析的sql为：${sql}")
//        spark.sql(sql).show()
//        val endTime = System.currentTimeMillis()
//        import spark.implicits._
//        Seq((tableName, endTime - startTime, new Date().toLocaleString))
//          .toDF("tableName", "totCount", "createTime")
//          .write.mode("append").saveAsTable("bdp_tmp.wms_clnd_finish")
//        println(s"${preff} ${tableName}完成，耗时：${endTime - startTime}")
//        count = count + 1
//      }
//    }
//  }

}
