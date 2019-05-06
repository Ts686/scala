package com.lijie.base.`class`

import scala.io.{BufferedSource, Source}

//无参构造
//class People {
//带参构造
class People(id: String, name: String) extends Animal with Human {
  //  def this(address: String) {
  //    this
  //  }

  println("构造...")

  override def run(): Unit = {
    println("People run...")
  }

  def play(): Unit = {

  }
}

object People extends App {
  var p = new People("", "")
  var pp = People
  var ppp = People
  println(p)
  println(pp == ppp)
  //  try {
  //    val str = Source.fromFile("d://", "").mkString
  //  } catch {
  //    case e: Exception => e.printStackTrace()
  //  } finally {
  //
  //  }

}
