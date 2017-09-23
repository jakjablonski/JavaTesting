package Game.TicTacToe;

import Game.TicTacToe.Mongo.TTTCollection;
import Game.TicTacToe.Mongo.TTTObject;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.*;

import java.net.UnknownHostException;

public class TTTTest {

	@Rule
    public ExpectedException exception = ExpectedException.none();
	private TTT ttt;
    private TTTCollection collection;
	
	 @Before
	    public final void Setup(){
	        collection = mock(TTTCollection.class);
	        doReturn(true).when(collection).deletedb();
	        doReturn(true).when(collection).save(any(TTTObject.class));
	        ttt = new TTT(collection);
	    }

	@Test(expected = IllegalArgumentException.class)
	public void xPozaPolemTest() {
		ttt.wstaw(5, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void yPozaPolemTest() {
		ttt.wstaw(1, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void zajeteMiejsceTest() {
		ttt.wstaw(1, 1);
		ttt.wstaw(1, 1);

	}

	@Test
	public void RemisTest() {
		ttt.wstaw(1, 1);
		ttt.wstaw(1, 2);
		ttt.wstaw(1, 3);
		ttt.wstaw(2, 3);
		ttt.wstaw(2, 2);
		ttt.wstaw(2, 1);
		ttt.wstaw(3, 1);
		ttt.wstaw(3, 2);
		assertEquals("draw", ttt.wstaw(3, 3));
		//assertThat(ttt.wstaw(3, 3), containsString("draw"));
	}
	@Test
	public void WinPionTestX() {
		ttt.wstaw(1, 1); // x
		ttt.wstaw(1, 2); // o
		ttt.wstaw(2, 1);// x
		ttt.wstaw(3, 2);// o
		assertEquals("win x", ttt.wstaw(3, 1));
		//assertThat(ttt.wstaw(3, 1), containsString("win x"));// x
	}
	@Test
	public void WinPionTestO() {
		ttt.wstaw(1, 1); // x
		ttt.wstaw(1, 3); // o
		ttt.wstaw(3, 2);// x
		ttt.wstaw(3, 3);// o
		ttt.wstaw(1, 2);// x
		assertEquals("win o", ttt.wstaw(2, 3));
		//assertThat(ttt.wstaw(2, 3), containsString("win o"));// x
	}
	
	@Test
	public void WinPoziomTestX() {
		ttt.wstaw(1, 1); // x
		ttt.wstaw(2, 1); // o
		ttt.wstaw(1, 2);// x
		ttt.wstaw(3, 2);// o
		assertEquals("win x", ttt.wstaw(1, 3));
		//assertThat(ttt.wstaw(1, 3), containsString("win x"));// x
	}

	@Test
	public void WinPoziomTestO() {
		ttt.wstaw(3, 3);//x
		ttt.wstaw(2, 1); // o
		ttt.wstaw(1, 3); // x
		ttt.wstaw(2, 2);// o
		ttt.wstaw(3, 2);// x
		assertEquals("win o", ttt.wstaw(2, 3));
		//assertThat(ttt.wstaw(2, 3), containsString("win o"));// o
	}

	@Test
	public void WinUkosX() {
		ttt.wstaw(1, 1); // x
		ttt.wstaw(2, 1); // o
		ttt.wstaw(2, 2);// x
		ttt.wstaw(3, 2);// o
		assertEquals("win x", ttt.wstaw(3, 3));
		//assertThat(ttt.wstaw(3, 3), containsString("win x"));// x
	}
	
	@Test
	public void WinUkosO() {
		ttt.wstaw(1, 2); // x
		ttt.wstaw(1, 1); // o
		ttt.wstaw(2, 3);// x
		ttt.wstaw(2, 2);// o
		ttt.wstaw(3, 1);// x
		assertEquals("win o", ttt.wstaw(3, 3));
		//assertThat(ttt.wstaw(3, 3), containsString("win o"));// o
	}

	@Test
	public void WinUkos2O() {
		ttt.wstaw(1, 2); // x
		ttt.wstaw(1, 3); // o
		ttt.wstaw(1, 1);// x
		ttt.wstaw(2, 2);// o
		ttt.wstaw(2, 1);//x	
		assertEquals("win o", ttt.wstaw(3, 1));
		//assertThat(ttt.wstaw(3, 1), containsString("win o"));// o
	}
	
	@Test
	public void WinUkos2X() {
		ttt.wstaw(1, 3); // x
		ttt.wstaw(1, 1); // o
		ttt.wstaw(2, 2);// x
		ttt.wstaw(1, 2);// o
		assertEquals("win x", ttt.wstaw(3, 1));
		//assertThat(ttt.wstaw(3, 1), containsString("win x"));// x
	}

	@Test
	public void secondCheckPlayerChange() {
		ttt.playerChange();
		ttt.playerChange();
		ttt.playerChange();
		String znakTest = ttt.znak;
		assertEquals("o", znakTest);
	}
	@Test
	public void czyPierwszyX() {
		ttt.wstaw(3, 1);
		assertEquals("x", ttt.plansza[3-1][0]);
		
	}
	
	@Test
	public void StanGry() {
		ttt.wstaw(1, 3); // x
		ttt.wstaw(1, 1); // o
		assertEquals("nowa tura ,brak wygranej", ttt.wstaw(3, 1));
		//assertThat(ttt.wstaw(3, 1), containsString("nowa tura ,brak wygranej"));// x
	}
	
	@Test
    public void testCollection() {
        assertNotNull(ttt.getTTTCollection());
    }
	
	@Test
    public void testDeleteAtStart() {
        verify(collection, times(1)).deletedb();
    }
}
