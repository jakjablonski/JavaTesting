package Game.TicTacToe.Mongo;

import org.jongo.marshall.jackson.oid.Id;

public class TTTObject {
	
	@Id
	private int turn;
    public int getTurn() {
        return turn;
    }
	
	
	private int x;
    public int getX() {
        return x;
    }
    
    private int y;
    public int getY() {
        return y;
    }

    private String player;
    public String getPlayer() {
        return player;
    }
    
    public TTTObject(int turn, int x, int y, String player) {
        this.turn = turn;
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public TTTObject() { }
}
