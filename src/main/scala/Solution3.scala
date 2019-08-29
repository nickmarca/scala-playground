object Solution3 extends App {
  def helper(num: Int, value: Int, res: List[Int] = List()): List[Int] = {
    num match {
      case 0 => res
      case _ => helper(num - 1, value, res ++ List(value))
    }
  }

  def f(num: Int, arr: List[Int], res: List[Int] = List()) : List[Int] = {
    arr match {
      case Nil => res
      case head :: rest => f(num, rest, res ++ helper(num, head))
    }
  }

  println(f(3, List(1,2,3)))
}
