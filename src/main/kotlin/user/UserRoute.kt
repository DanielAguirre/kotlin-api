package user

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

var routeController = UserController()

fun Route.userApi(){
    route("/api/user"){
       // get  { routeController.findUser(call) }
        post {
            println("entra?")
            routeController.createUSer(call)
        }
    }
}