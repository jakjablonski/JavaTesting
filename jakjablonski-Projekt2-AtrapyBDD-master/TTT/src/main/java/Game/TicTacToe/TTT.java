package Game.TicTacToe;
import Game.TicTacToe.Mongo.TTTObject;

import java.net.UnknownHostException;

import Game.TicTacToe.Mongo.TTTCollection;

public class TTT {

	public String[][] plansza = { { "0", "0", "0" }, { "0", "0", "0" }, { "0", "0", "0" } };
	public String znak = "x";
	int turn = 0;
	
	private TTTCollection TTTCollection;
	protected TTTCollection getTTTCollection() {
        return TTTCollection;
    }
	 public TTT(){
	        this(new TTTCollection());
	    }

	public TTT(TTTCollection collection) {
		TTTCollection = collection;
        if (!TTTCollection.deletedb()) {
            throw new RuntimeException("failed ");
        }
	}

	public String wstaw(int x, int y) {
		turn = turn++;
		sprawdzKolumneX(x);
		sprawdzKolumneY(y);
		wstawznak(new TTTObject(turn,x,y,znak));
		//wstawznak(x, y, znak);
		if (RemisCheck() == true) {
			return "draw";
		}
		if (WygranaPion(x, y) == true) {
			return "win " + znak;
		}
		if (WygranaPoziom(x, y) == true) {
			return "win " + znak;
		}
		if (WygranaPrzek1() == true) {
			return "win " + znak;
		}
		if (WygranaPrzek2() == true) {
			return "win " + znak;
		}else {
			playerChange();
			//pokazPlansze();
			//System.out.print("\n\n");
			return "nowa tura ,brak wygranej";
		}

	}

	public void wstawznak(TTTObject object) {
		sprawdzCzyZajete(object);
		plansza[object.getX() - 1][object.getY() - 1] = znak;
		TTTCollection.save(object);

	}
	
	public void sprawdzCzyZajete(TTTObject object) {

		if (plansza[object.getX()- 1][object.getY() - 1] != "0") {
			throw new IllegalArgumentException("pole zajete");
		}

	}

	public void playerChange() {
		if (znak == "x") {
			znak = "o";
		} else {
			znak = "x";
		}

	}

	

	public void sprawdzKolumneX(int x) {
		if (x < 0 || x > 3) {
			throw new IllegalArgumentException("po za plansza");
		}
	}

	public void sprawdzKolumneY(int y) {
		if (y < 0 || y > 3) {
			throw new IllegalArgumentException("po za plansza");
		}

	}

	

	public boolean RemisCheck() {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (plansza[x][y] == "0") {
					return false;
				}
			}
		}
		return true;
	}
	
	
	public boolean WygranaPion(int x, int y) {
		String pion = "";
		for (int i = 0; i < 3; i++) {
			pion += plansza[i][y - 1];
			if (pion.equals("xxx") || pion.equals("ooo")) {
				return true;
			}
		}
		return false;

	}
	
	public boolean WygranaPoziom(int x, int y) {
		String poziom = "";
		for (int i = 0; i < 3; i++) {
			poziom += plansza[x-1][i];
			if (poziom.equals("xxx") || poziom.equals("ooo")) {
				return true;
			}
		}
		return false;

	}


	public boolean WygranaPrzek1() {
		String przek = "";
		for (int i = 0; i < 3; i++) {
			przek += plansza[i][i];
			if (przek.equals("xxx") || przek.equals("ooo")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean WygranaPrzek2() {
		String przek = "";
		for (int i = 0; i < 3; i++) {
			przek += plansza[i][2-i];
			if (przek.equals("xxx") || przek.equals("ooo")) {
				return true;
			}
		}
		return false;

	}
	
	public void pokazPlansze() {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				System.out.print(plansza[x][y]);
			}
			System.out.print("\n");
		}
	}
}
