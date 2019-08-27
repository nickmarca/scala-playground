import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ActorCapabilities extends App {
  class SimpleActor extends Actor {
    override def receive: Receive = {
      case "Hi" => sender() ! "Hello, there"
      case message: String => println(s"[simple actor $self] I have received $message")
      case number: Int => println(s"[simple actor $self] I have received A NUMBER $number")
      case SpecialMessage(contents) => println(s"[simple actor $self] I have received something SPECIAL: $contents")
      case SendMessageToYourself(contents) =>
        self ! contents
      case SayHiTo(ref) => ref ! "Hi"
      case WirelessPhoneMessage(content, ref) => ref forward (content + "s")
    }
  }

  val system = ActorSystem("actorCapabilitiesDemo")
  val simpleActor = system.actorOf(Props[SimpleActor], "simpleActor")

  //simpleActor ! "hello actor"

  case class SpecialMessage(contents: String)
  //simpleActor ! SpecialMessage("some special content")

  case class SendMessageToYourself(content: String)
  //simpleActor ! SendMessageToYourself("I am an actor and I am proud of it")

  val alice = system.actorOf(Props[SimpleActor], "alice")
  val bob = system.actorOf(Props[SimpleActor], "bob")

  case class SayHiTo(ref: ActorRef)
  //alice ! SayHiTo(bob)

  case class WirelessPhoneMessage(content: String, ref: ActorRef)
  alice ! WirelessPhoneMessage("Hi", bob)
}
