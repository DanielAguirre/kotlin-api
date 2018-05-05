package server

import DB.DbProvider
import com.google.gson.GsonBuilder
import io.ktor.server.netty.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.*
import io.ktor.request.receiveText
import io.ktor.response.*
import io.ktor.server.engine.*
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import user.userApi
import java.util.*


fun main(args: Array<String>) {

    suspend {
        var x = async { DbProvider.getDatabase("mongodb://localhost:27018") }
        x.await();
    }


    println("paso?")
    embeddedServer(Netty,
            port = 8080,
            watchPaths = listOf("DB/db"),
            module = Application::install)
            .start(wait = true)
}

fun Application.install() {

    install(DefaultHeaders)

    install(CallLogging)


    routing {

        userApi()

        get("/me") {
            val name:String? = call.request.queryParameters["name"]
            val age:String? = call.request.queryParameters["age"]
            val gson = GsonBuilder().setPrettyPrinting().create()

            val edad = try {
                age!!.toInt()
            } catch (e: NumberFormatException) {
                0
            }

            if( name != null && age!= null) {
                val me = Person(name, edad)
                val jsonResponse = gson.toJson(me)
                call.respondText(jsonResponse, ContentType.Application.Json, HttpStatusCode.OK)
            } else {
                val error = Error(HttpStatusCode.BadRequest.value, "Datos invalidos")
                call.respondText(gson.toJson(error), ContentType.Text.Html)
            }

            call.respondText("Nombre $name Age: $age", ContentType.Text.Html)
        }
        post("/postme") {
            val messagePost = call.receiveText()

            call.respondText ("Ok", ContentType.Text.Html, HttpStatusCode.OK)
        }
    }

}
