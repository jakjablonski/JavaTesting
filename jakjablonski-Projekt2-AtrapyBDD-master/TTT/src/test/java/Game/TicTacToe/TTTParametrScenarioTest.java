package Game.TicTacToe;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jongo.MongoCollection;

import Game.TicTacToe.Mongo.TTTCollection;
import Game.TicTacToe.Mongo.TTTObject;

public class TTTParametrScenarioTest {
	MongoCollection mongoCollection;
	TTTCollection collection;
	public TTTObject object;
    private TTT ttt;
    
    
    
    @BeforeScenario
	public void givenMamPustaNowaPlansze(){
    	collection = spy(new TTTCollection());
    	mongoCollection = mock(MongoCollection.class);
    	doReturn(mongoCollection).when(collection).getMongoCollection();
    	
    	//ttt = new TTT(collection);
	}
    
    @When("tworze obiekt (<turn>,<x>,<y>,<player>)")
    public void whenWstawieWPolexIy(@Named("turn") int turn ,@Named("x") int x, @Named("y") int y,@Named("player") String player){

    	object = new TTTObject(turn,x,y,player);
    	
    }
	
	@Then("gra zwraca $wynik")
	public void GraTrwaDalej(String wynik){
		
		assertEquals(wynik,String.valueOf(collection.save(object)));

	}
	

}
