/** Define Data */
object MyData {

  case class User(id: Int, name: String)

  case class ErrorResponse(message: String)

  case class CreateUserRequest(name: String)

  case class UpdateUserRequest(name: String)

}
