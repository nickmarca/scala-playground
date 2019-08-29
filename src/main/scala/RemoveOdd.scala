object RemoveOdd extends App {
  val seq = Seq(1,2,3,4)

  val removeOdd: Seq[Int] => Seq[Int] = nums => {
    if (nums.isEmpty) {
      Seq()
    }
    else {
      if (nums.head % 2 == 0) {
        Seq(nums.head) ++ removeOdd(nums.tail)
      } else {
        removeOdd(nums.tail)
      }
    }
  }

}


import scala.io.StdIn

object Solution extends App {
  val s = StdIn.readInt()

  var i: Int = 0
  var list: List[Int] = List()
  for(i <- 1 to s) {

  }
}
