package simulations

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpProtocol: HttpProtocolBuilder  = http
    .baseUrl("http://jsonplaceholder.typicode.com/") // Here is the root for all relative URLs
    .acceptHeader("application/json")

  val getAllPosts : ScenarioBuilder = scenario("Get All Posts") // A scenario is a chain of requests and pauses
    .exec(http("get_all_posts_request")
      .get("/posts"))

  setUp(getAllPosts.inject(rampUsers(300).during(20.seconds))).protocols(httpProtocol)
}
