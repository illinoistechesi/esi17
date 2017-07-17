package esi17.DoomDawn;
import battleship.core.*;
import java.util.List;

/*
 * Dummy Ship
 * @author The Evil Fleet
 */
public class DummyShip extends Ship {
    
    public DummyShip() {
        this.initializeName("Dummy Ship");
        this.initializeOwner("The Evil Fleet");
        this.initializeHull(5);
        this.initializeFirepower(1);
        this.initializeSpeed(3);
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

    }   
}