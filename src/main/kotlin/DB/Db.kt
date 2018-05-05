package DB

import com.mongodb.async.client.MongoDatabase
import org.litote.kmongo.coroutine.* //NEEDED! import KMongo extensions
import org.litote.kmongo.async.KMongo


object DbProvider {
    private var db:MongoDatabase? = null;

    fun getDatabase(host:String) :MongoDatabase? {
        if (db != null) {
            return db
        }

        val mongo = KMongo.createClient(host)
        db = mongo.getDatabase("user");
        println("works")
        return db;
    }


}
