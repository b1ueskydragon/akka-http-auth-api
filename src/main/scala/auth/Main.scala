package auth

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.{HttpChallenge, OAuth2BearerToken}
import akka.http.scaladsl.server.directives.{AuthenticationDirective, AuthenticationResult}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.concurrent.Future

/** Account (User)
  *
  * @param name user name
  */
case class Account(name: String)

/** Akka http authorization
  *
  * Set Bearer Token manually, use it for Authorize.
  *
  * $ sbt run
  * $ curl -H "Authorization: Bearer ${TOKEN}" --dump-header - http://localhost:${PORT}/
  */
object Main extends App {
  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  lazy val TOKEN_STRING = "inano-123"
  lazy val USER_NAME = "me\n"
  lazy val PORT = 8999

  private def authenticate: AuthenticationDirective[Account] =
    authenticateOrRejectWithChallenge[OAuth2BearerToken, Account] {
      case Some(OAuth2BearerToken(token)) if token == TOKEN_STRING =>
        Future.successful(AuthenticationResult.success(Account(USER_NAME)))
      case _ => Future.successful(AuthenticationResult.failWithChallenge(
        HttpChallenge("bearer", None, Map("error" -> "invalid_token"))))
    }


  val route: Route = pathEndOrSingleSlash(authenticate(account => complete(account.name)))

  Http().bindAndHandle(route, "", PORT)

}
