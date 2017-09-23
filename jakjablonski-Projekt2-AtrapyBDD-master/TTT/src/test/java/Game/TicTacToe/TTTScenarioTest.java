package Game.TicTacToe;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import Game.TicTacToe.Mongo.TTTCollection;
import Game.TicTacToe.Mongo.TTTObject;



public class TTTScenarioTest{
	
	TTTCollection collection;
	TTTObject object;
    private TTT ttt;
    
    
    
    @BeforeScenario
	public void givenMamPustaNowaPlansze(){
    	collection = mock(TTTCollection.class);
        doReturn(true).when(collection).deletedb();
        doReturn(true).when(collection).save(any(TTTObject.class));
    	ttt = new TTT(collection);
	}
    @When("wstawie x w pole (1,1)")
	public void whenWstawieXWPole11(){
    	ttt.wstaw(1, 1);
	}
	@Then("nastepny gracz ma znak o")
	public void thenNastepnyGraczMaZnakO(){
		ttt.wstaw(3, 1);
		assertEquals("o", ttt.plansza[3-1][0]);
	}
	
	@Then("nastepny gracz wstawia o w pole (1,2) i gra trwa dalej")
	public void thenNastepnyGraczWstawiaO(){
		assertEquals("nowa tura ,brak wygranej", ttt.wstaw(1,2));
	}
	
//	 @Given("Mam pusta nowa plansze 3")
//		public void givenMamPustaNowaPlansze3(){
//	    	collection = mock(TTTCollection.class);
//	        doReturn(true).when(collection).deletedb();
//	        doReturn(true).when(collection).save(any(TTTObject.class));
//	    	ttt = new TTT(collection);
//		}
	
	
	@When("uzupelnie 8 na 9 pol")
	public void whenUzupelnie8na9pol(){
		ttt.wstaw(1, 1);
		ttt.wstaw(1, 2);
		ttt.wstaw(1, 3);
		ttt.wstaw(2, 3);
		ttt.wstaw(2, 2);
		ttt.wstaw(2, 1);
		ttt.wstaw(3, 1);
		ttt.wstaw(3, 2);
	}
	
	@Then("wstawienie ostatniego powoduje remis")
	public void WstawienieOstatniegoPowodujeRemis(){
		assertEquals("draw", ttt.wstaw(3, 3));
	}
	
	@When("uzupelnie plansze by wygrac w pionie")
	public void whenUzupelniePion(){
		ttt.wstaw(1, 1);
		ttt.wstaw(1, 3);
		ttt.wstaw(3, 2);
		ttt.wstaw(3, 3);
		ttt.wstaw(1, 2);
	}
	
	@Then("wstawienie powoduje wygrana w pionie")
	public void WstawieniePowodujeWygranaPion(){
		assertEquals("win o", ttt.wstaw(2, 3));
	}
	
	@When("uzupelnie plansze by wygrac w poziomie")
	public void whenUzupelniePoziom(){
		ttt.wstaw(1, 1);
		ttt.wstaw(2, 1);
		ttt.wstaw(1, 2);
		ttt.wstaw(3, 2);
	}
	
	@Then("wstawienie powoduje wygrana w poziomie")
	public void WstawieniePowodujeWygranaPoziom(){
		assertEquals("win x", ttt.wstaw(1, 3));
	}
	
	@When("uzupelnie plansze by wygrac na ukos")
	public void whenUzupelnieUkos(){
		ttt.wstaw(1, 1);
		ttt.wstaw(2, 1);
		ttt.wstaw(2, 2);
		ttt.wstaw(3, 2);
	}
	
	@Then("wstawienie powoduje wygrana na ukos")
	public void WstawieniePowodujeWygranaUkos(){
		assertEquals("win x", ttt.wstaw(3, 3));
	}
	
	@When("pierwszy gracz rozpoczyna gre")
	public void whenPierwszyGraczRozpoczynaGre(){
		ttt.wstaw(1, 1);
	}
	
	@Then("jego symbol to x")
	public void JegoSymbolToX(){
		assertEquals("x", ttt.plansza[0][0]);
	}
	
	
}