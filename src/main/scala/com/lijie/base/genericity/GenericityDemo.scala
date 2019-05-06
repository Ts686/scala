package com.lijie.base.genericity

import scala.reflect.ClassTag

class Earth {
  def sound() {
    println("hello !")
  }
}

class Animal extends Earth {
  override def sound() = {
    println("animal sound")
  }
}

class Bird extends Animal {
  override def sound() = {
    println("bird sounds")
  }
}


object GenericityDemo extends App {
  def biophony[A: ClassTag, T <: Animal](things: Seq[T]) = things.map(_.sound())

  val s = Seq(new Animal, new Bird)
  biophony(s)

}
