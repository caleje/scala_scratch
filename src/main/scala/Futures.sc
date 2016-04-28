import java.util.concurrent.TimeUnit

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration.Duration

object Futures {
  import scala.concurrent.Future
  import ExecutionContext.Implicits.global

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
}