object ReverseListSolution extends App {
  def f(arr: List[Int], output: List[Int] = List()): List[Int] = {
    arr match {
      case Nil => output
      case head :: rest => f(rest,  List(head) ++ output)
    }
  }

  println(f(List(1,2,3)))
}
