package com.lijie.base.genericity

class Animals[A, B](var name: A, var age: B) {
  println(s"Name is $name, Age is $age")


}

object Animals {
  def asList[T](pSrc: Array[T]): List[T] = {
    if (pSrc == null || pSrc.isEmpty) {
      List[T]()
    } else {
      pSrc.toList
    }
  }
}

object GenericClient extends App {
  val cat = new Animals[String, Int]("小花", 2)
  val dog = new Animals[String, String]("阿黄", "5")

  val friends = Array("小白", "琪琪", "乐乐", 1)
  val friendList = Animals.asList(friends)
  println(friendList.isInstanceOf[List[Int]])
  println(friendList)
}
