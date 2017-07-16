package esi17.rfeliz;
import battleship.core.*;
import java.util.List;

/*
 * Fast Ship
 * @author Rosa Feliz
 */
public class FastShip extends Ship {
    
    public FastShip() {
        this.initializeName("Fast Ship");
        this.initializeOwner("Rosa Feliz");
        this.initializeHull(1);
        this.initializeFirepower(1);
        this.initializeSpeed(7);
        this.initializeRange(1);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        arena.move(this, Direction.WEST);
    }
    
}