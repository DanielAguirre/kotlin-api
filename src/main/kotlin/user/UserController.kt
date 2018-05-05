package user

import com.google.gson.GsonBuilder
import io.ktor.application.ApplicationCall
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import Models.Person
import Models.createUSer
import sun.misc.Signal.handle
import java.util.*

data class userMsessage(val message:String)

class UserController {
    val gson = GsonBuilder().setPrettyPrinting().create()
    suspend fun findUser(call: ApplicationCall){
        val jsonResponse = gson.toJson(userMsessage("Hi USer"))
        call.respondText(jsonResponse, ContentType.Application.Json, HttpStatusCode.OK)
    }

    suspend fun createUSer(call: ApplicationCall){

            println("entra 2")
            var person = Person(null,"Daniel",30)
            createUSer(person)
            val jsonResponse = gson.toJson(person)
            call.respondText(jsonResponse, ContentType.Application.Json, HttpStatusCode.OK)
    }

}
