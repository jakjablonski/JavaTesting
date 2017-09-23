package Game.TicTacToe.Mongo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TTTObjectTest {

	
TTTObject object;
	
	@Test
    public void IdTest() {
		
		object = new TTTObject(1, 1, 1, "x");
        assertEquals(1, object.getTurn());
        
    }
	@Test
    public void testX() {
		
		object = new TTTObject(1, 1, 1, "x");
        assertEquals(1, object.getX());
        
    }
	@Test
    public void testY() {
		
		object = new TTTObject(1, 1, 1, "x");
        assertEquals(1, object.getY());
        
    }
	@Test
    public void testPlayer() {
		
		object = new TTTObject(1, 1, 1, "x");
        assertEquals("x", object.getPlayer());
        
    }
}
