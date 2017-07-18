package esi17.mruiz9;
import battleship.core.*;
import java.util.List;

/*
 * mruiz9
 * @author Your Name
 */
public class guppy extends Ship {
    
    public guppy() {
        this.initializeName("mruiz9");
        this.initializeOwner("Guppy");
        this.initializeHull(5);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
        this.initializeRange(1);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        // Fill in your strategy here
        this.move(arena, Direction.WEST);
    }
    
}