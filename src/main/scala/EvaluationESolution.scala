object EvaluationESolution extends App {

  def factorial(n: Int): Int = n match {
    case 0 => 1
    case _ => n * factorial(n - 1)
  }

  def e(x: Double, n: Int = 1, acc: Double = 1.0): Double = {
      if(n == 1) e(x, n + 1, acc + x)
      else if(n < 10) e(x, n + 1, acc + (math.pow(x, n) / factorial(n)))
      else acc
  }

  println(e(5.0000))
}
