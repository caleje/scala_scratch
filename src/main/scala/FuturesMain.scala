
import java.util.concurrent.TimeUnit

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object FuturesMain extends App {
  def doSomeWork(): Future[Int] = {
    println("I'm doing work")
    Future {
      42
    }
  }

  def addOne(i: Int): Int = {
    i + 1
  }

  val f = doSomeWork map addOne

  f onSuccess {
    case i => println(s"Got success $i")
  }

  f onFailure {
    case e => println(s"Got failure $e")
  }

  Await.ready(f, Duration(1, TimeUnit.SECONDS))
  // scala.io.StdIn.readLine("Hit enter")
  // ExecutionContext.global.wait(1000)
}
