object Memoize extends App {


  def memo[A,B,C](fn: A => B): A => B = {
    var byParams:Map[A, B]  = Map()

    a: A => {
      if(byParams.contains(a)) {
        byParams(a)
      } else {
        val r = fn(a)
        byParams = byParams + (a -> r)
        r
      }
    }
  }

  val r = scala.util.Random

  val sum1 = (a: Int) => a + 1

  val sumWithMemo = memo(sum1)

  println(r.nextInt)
}


