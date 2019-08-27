import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ChangingBehaviorExercises extends App {
  val system = ActorSystem("changingBehaviorExercises")

  object Counter {
    case object Increment
    case object Decrement
    case object Print
  }
  class Counter extends Actor {
    import Counter._

    override def receive: Receive = counterReceive(0)

    def counterReceive(currentCount: Int): Receive = {
      case Increment => context.become(counterReceive(currentCount + 1))
      case Decrement => context.become(counterReceive(currentCount - 1))
      case Print => println(s"[counter] my current counter is $currentCount")
    }
  }

  val counter = system.actorOf(Props[Counter], "counter")

  case class Vote(candidate: String)
  case object VoteStatusRequest
  case class VoteStatusReply(candidate: Option[String])
  class Citizen extends Actor {
    var candidate: Option[String] = None
    override def receive: Receive = {
      case Vote(c) => candidate = Some(c)
      case VoteStatusRequest => sender() ! candidate
    }
  }

  case class AggregateVotes(citizens: Set[ActorRef])
  class VoteAggregator extends Actor {
    var stillWaiting: Set[ActorRef] = Set()
    var currentStats: Map[String, Int] = Map()
    override def receive: Receive = {
      case AggregateVotes(citizens) =>
        stillWaiting = citizens
        citizens.foreach(citizenRef => citizenRef ! VoteStatusRequest)
      case VoteStatusReply(None) =>
    }
  }
}
