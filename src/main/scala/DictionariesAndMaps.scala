import scala.io.StdIn

object DictionariesAndMaps {

    def readValues(n: Int, it: Int = 1, values: Map[String, String] = Map()): Map[String, String] = {

      if(it > n) {
        values
      } else {
        val input = StdIn.readLine().toString
        val value = input.split(" ")
        readValues(n, it + 1, values + (value(0) -> value(1)))
      }
    }


    def main(args: Array[String]) {
      val n = StdIn.readLine().toInt

      val values = readValues(n)

      var ln = ""

      do {
          ln = StdIn.readLine().toString
          if (values.isDefinedAt(ln)) {
            println(s"${ln}=${values(ln)}")
          } else {
            println("Not found")
          }
      } while(ln != "")
    }
}
