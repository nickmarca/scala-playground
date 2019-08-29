object SumOfOddsSolution extends App {

  def isOdd(n: Int) = {
    !(n % 2 == 0)
  }

  def f(arr: List[Int], output: Int = 0): Int = {
    arr match {
      case Nil => output
      case head :: rest => f(rest, if(isOdd(head)) output + head else output)
    }
  }

  println(f(List(1,2,3,4,5)))
}
