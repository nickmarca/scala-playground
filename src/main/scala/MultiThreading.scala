object MultiThreading extends App {
  val aThread = new Thread(() => println("Im running in parallel"))
  aThread.start()
  aThread.join()


  val threadHello = new Thread(() => (1 to 1000).foreach(_ => println("hello")))
  val threadGoodbye = new Thread(() => (1 to 1000).foreach(_ => println("goodbye")))

  threadHello.start()
  threadGoodbye.start()
}
