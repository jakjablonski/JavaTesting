package Game.TicTacToe.Mongo;

import org.jongo.MongoCollection;
import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.UnknownHostException;

import org.junit.Test;

import com.mongodb.MongoException;


public class TTTCollectionTest {
	
	TTTCollection collection;
	TTTObject object;
    MongoCollection mongoCollection;	
    
    @Before
    public void Setup(){
        collection = spy(new TTTCollection());
        mongoCollection = mock(MongoCollection.class);
        object = new TTTObject(1,2, 2, "x");
    }
    
    @Test
    public void testCollectionDBName() {
        assertEquals("TTT", collection.getMongoCollection().getDBCollection().getDB().getName());
    }
    
    @Test
    public void testCollectionName() {
        assertEquals("ruchy", collection.getMongoCollection().getName());
    }
    
    @Test
    public void testDeleteCollection(){
    	doReturn(mongoCollection).when(collection).getMongoCollection();
    	assertTrue(collection.deletedb());
    }
    
    @Test
    public void testSave() {
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertTrue(collection.save(object));
    } 
    
    @Test
    public void exceptionSave() {
        doThrow(new MongoException("Fail save")).when(mongoCollection).save(any(TTTObject.class));
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertFalse(collection.save(object));
    }
    
    @Test
    public void exceptionDelete() {
        doThrow(new MongoException("Fail drop")).when(mongoCollection).drop();
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertFalse(collection.deletedb());
    }
    
    @Test
    public void MongoSave() {
        doReturn(mongoCollection).when(collection).getMongoCollection();
        collection.save(object);
        verify(mongoCollection).save(object);
    }
    
    @Test
    public void MongoDelete() {
        doReturn(mongoCollection).when(collection).getMongoCollection();
        collection.deletedb();
        verify(mongoCollection).drop();
    }

}
