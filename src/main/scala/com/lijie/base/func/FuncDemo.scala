package com.lijie.base.func

object FuncDemo extends App {
  def method(x: Int): Int = {
    x * 3
  }

  val func = (x: Int) => x * 3
  val func2 = (x: Any) => x + "1"
  val func3 = method _


  def m(x: Int) = (y: Int) => x * y

  def m2(x: Int)(y: Int) = x * y

}
