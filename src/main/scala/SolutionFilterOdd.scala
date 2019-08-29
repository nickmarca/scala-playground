object SolutionFilterOdd extends App {
  def f(arr: List[Int], output: List[Int] = List(), isOdd: Boolean = true): List[Int] = {
    arr match {
      case Nil => output
      case head :: rest => f(rest, if(!isOdd) output ++ List(head) else output, !isOdd)
    }
  }

  println(f(List(1,2,3,4,5)))
}
