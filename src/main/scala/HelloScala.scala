import scala.io.Source

object HelloScala {
  def main(args: Array[String]): Unit = {
    print("hello")
    val list: Range.Inclusive = 1 to 5
    val arr: Array[Any] = Array(1, 2, "a")
    //    val lines: Iterator[String] = Source.fromFile("").getLines()
    val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo", "China" -> "Beijing")
    val str = capitals.mkString("---")
    println(str)
    //    val str: String = capitals.getOrElse("haha", "没有")
    val maybeString = capitals.get("France").get
    //    val maybeString2 = capitals.get("France2").get

    for (x <- 1 until 3) {
      println(x)
    }
    println(maybeString)
    //    println(maybeString2)


    //偏函数的应用
    val ints: List[Int] = List(1, 3, 5, "seven").collect({
      case i: Int => i + 1
    })
    println(ints)


  }
}
