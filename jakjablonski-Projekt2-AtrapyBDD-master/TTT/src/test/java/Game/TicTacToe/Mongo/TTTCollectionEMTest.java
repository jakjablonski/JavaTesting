package Game.TicTacToe.Mongo;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.easymock.EasyMockSupport;


import org.easymock.EasyMock;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class TTTCollectionEMTest extends EasyMockSupport {

	TTTCollection collection;
	TTTObject object;
    MongoCollection mongoCollection;

    
    
    @Test
    public void testCollectionDBName() {
    	mongoCollection = EasyMock.createNiceMock(MongoCollection.class);
    	collection = new TTTCollection(); 
        assertEquals("TTT", collection.getMongoCollection().getDBCollection().getDB().getName());
    }
    
    @Test
    public void testCollectionName() {
    	mongoCollection = EasyMock.createNiceMock(MongoCollection.class);
    	collection = new TTTCollection(); 
        assertEquals("ruchy", collection.getMongoCollection().getName());
    }
    @Test
    public void ChcekGetMongoCollection()
    {
        collection = createMock(TTTCollection.class);
        mongoCollection = createMock(MongoCollection.class);
    
    EasyMock.expect(collection.getMongoCollection()).andReturn(mongoCollection);
    EasyMock.expectLastCall().once();
    EasyMock.replay(collection);
    assertEquals(mongoCollection, collection.getMongoCollection());
    EasyMock.verify(collection);
    }
    
    @Test
    public void testDeleteCollection(){
    	collection = createMock(TTTCollection.class);
        mongoCollection = createMock(MongoCollection.class);
    
    EasyMock.expect(collection.getMongoCollection()).andReturn(mongoCollection);
    EasyMock.expectLastCall().once();
    EasyMock.expect(collection.deletedb()).andReturn(true);
    replayAll();
    assertTrue(collection.deletedb());
    
    }
    
    @Test
    public void testSaveCollection(){
    	collection = createMock(TTTCollection.class);
        mongoCollection = createMock(MongoCollection.class);
        object = new TTTObject(1,2, 2, "x");
    
    EasyMock.expect(collection.getMongoCollection()).andReturn(mongoCollection);
    EasyMock.expectLastCall().once();
    EasyMock.expect(collection.save(object)).andReturn(true);
    replayAll();
    assertTrue(collection.save(object));
    
    }

}
