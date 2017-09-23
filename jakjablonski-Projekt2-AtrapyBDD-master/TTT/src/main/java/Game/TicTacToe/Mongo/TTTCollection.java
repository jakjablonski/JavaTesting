package Game.TicTacToe.Mongo;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class TTTCollection {
	
	private MongoCollection mongoCollection;
    public MongoCollection getMongoCollection() {
        return mongoCollection;
    }
    private static final String dbName = "TTT";
    private static final String collectionName = "ruchy";
    
    
    public TTTCollection(){
        DB db = new MongoClient().getDB(dbName);
        mongoCollection = new Jongo(db).getCollection(collectionName);
    }
    
    public boolean deletedb() {
        try {
            getMongoCollection().drop();
            return true;
        } catch (Exception e) {
            return false;
        }
    } 
    
    public boolean save(TTTObject object) {
    	try {
    		getMongoCollection().save(object);
    		return true;
    	} catch (Exception e) {
    	return false;
    	}
}
    
}
