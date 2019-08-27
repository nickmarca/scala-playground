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
