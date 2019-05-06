package com.lijie.base.file

import scala.io.Source

object fileDemo extends App {
  val lines: Iterator[String] = Source.fromFile("D:/data/develop/bdp-dit/person.txt").getLines()
  val strings: Iterator[String] = lines.map(line => {
    line.split(",")(1)
  })

  val str: String = "java".mkString("**")
  val a = Array("apple", "banana", "cherry")
  println(str, a.mkString("[", ", ", "]"))

  val l = List("java", "hello")
  println(l.mkString(",,,"))

  val site = "Runoob" :: ("Google" :: ("Baidu" :: Nil))
  println(site.tail)




}

case class Person(name: String, age: Int)
