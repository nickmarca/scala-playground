object SolutionFilterArray extends App {

  def f(delim: Int, arr: List[Int], output: List[Int] = List()): List[Int] = {
    arr match {
      case Nil => output
      case head :: rest => f(delim, rest, if(head < delim) output ++ List(head) else output)
    }
  }

  println(f(2, List(1,2)))
}
