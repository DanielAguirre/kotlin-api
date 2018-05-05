package Models

import DB.DbProvider
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.coroutine.getCollection
import org.litote.kmongo.coroutine.save

data class Person(@BsonId val key: org.bson.types.ObjectId?, val name:String, val age:Number)

suspend fun createUSer(person:Person){
//    val DB = DbProvider.getDatabase("")
    print(person)

    DbProvider
            .getDatabase("")
            ?.let { it.getCollection<Person>().save(person) }

}
