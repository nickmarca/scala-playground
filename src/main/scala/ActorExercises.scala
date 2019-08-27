import ActorExercises.BankAccount.Deposit
import ActorExercises.Person.LiveTheLife
import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.io.StdIn

object ActorExercises extends App {
  object Counter {

    case class Increase(value: Integer)

    case class Decrease(value: Integer)

    case class Log()

  }

  class ActorExercise1 extends Actor {
    import Counter._
    var state = 0

    override def receive: Receive = {
      case Increase(value) => state += value
      case Decrease(value) => state -= value
      case Log => println(s"Current state is: $state")
    }
  }

  val system = ActorSystem("exercise1")
  val actorExercise1 = system.actorOf(Props[ActorExercise1], "actorExercise1")

  import Counter._
  (1 to 10).foreach(_ => actorExercise1 ! Increase(1))
  //actorExercise1 ! Log



  object BankAccount {
    case class Deposit(amount: Int)
    case class Withdraw(amount: Int)
    case object Statement
    case class TransactionSuccess(message: String)
    case class TransactionFailure(reason: String)
  }

  class BankAccount extends Actor {
    import BankAccount._

    var funds = 0

    override def receive: Receive = {
      case Deposit(amount) =>
        if(amount < 0) sender() ! TransactionFailure("invalid deposit amount")
        else {
          funds += amount
          sender() ! TransactionSuccess(s"successfully deposited $amount")
        }
      case Withdraw(amount) =>
        if(amount < 0) sender() ! TransactionFailure("invalid withdraw amount")
        else if (amount > funds) sender() ! TransactionFailure("insufficient funds")
        else {
          funds -= amount
          sender() ! TransactionSuccess(s"successfully withdrew $amount")
        }
      case Statement => sender() ! s"Your balance is $funds"
    }
  }

  object Person {
    case class LiveTheLife(account: ActorRef)
  }
  class Person extends Actor {
    import Person._
    import BankAccount._

    override def receive: Receive = {
      case LiveTheLife(account) =>
        account ! Deposit(10000)
        account ! Withdraw(90000)
        account ! Withdraw(500)
      case message => println(message.toString)
    }
  }

  val account = system.actorOf(Props[BankAccount], "bankAccount")
  val person = system.actorOf(Props[Person], "billionaire")

  person ! LiveTheLife(account)
}
