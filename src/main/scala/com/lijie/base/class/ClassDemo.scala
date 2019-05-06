package com.lijie.base.`class`


/**
  * 类名后面+private 表示构造方法私有
  * 前面的private[aa] 表示包的私有权限，只能在包aa及其子包中访问
  */
//private[aa] class Person private {
class Person(idd: String) {
  var id: String = ""
  val name = ""
  private[this] var address: String = _

  def method(): Unit = {
    println(address)
  }
}

object Person extends App {
  val person = new Person("")
  person.id = "1"
  println(person.id)
  person.method()
}