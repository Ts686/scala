package com.lijie.base.reduce


/**
  * 函数的三种表达方式
  */
object ReduceDemo extends App {
  val list: List[Int] = List(1, 2, 3, 4, 5)

  def sub(a: Int, b: Int): Int = {
    a + b
  }

  val sub2 = (a: Int, b: Int) => {
    a + b
  }
  val sub3: (Int, Int) => Int = {
    (a, b) => a + b
  }
  val i: Int = list.reduce(sub2)
  println(i)
}
