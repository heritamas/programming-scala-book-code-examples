// src/main/scala/progscala2/typelessdomore/futures.sc
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

def sleep(millis: Long) = {
  Thread.sleep(millis)
}

// Busy work ;)
def doWork(index: Int) = {
  sleep((math.random * 1000).toLong)
  index
}

(1 to 5) foreach { index =>
  val future = Future {
    doWork(index)
  }
  future onComplete {
    case Success(answer) => println(s"Success! returned: $answer")
    case Failure(th) => println(s"FAILURE! returned: $th")
  }
}

sleep(1000)  // Wait long enough for the "work" to finish.
println("Finito!")
