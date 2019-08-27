object Exercises extends App {

  def sum = (n: Int) => n + 1

  def nTimes[A](fn: A => A, times: Int): A => A = {
    a: A => {

      def fnTimes(acc: Int = 0, sum: A): A = {
        if (acc == times) {
          sum
        } else {
          val s = fn(sum)
         fnTimes(acc + 1, s)
        }
      }

      fnTimes(sum = a)
    }
  }

  val plusOne = (a: Int) => a + 2

  val hf = nTimes(plusOne, 2)

  //println(hf(1))

}
