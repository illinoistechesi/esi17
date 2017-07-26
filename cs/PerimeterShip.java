package esi17.cs;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class PerimeterShip extends Ship {
    
    public PerimeterShip() {
        this.initializeName("Custom Ship");
        this.initializeOwner("Your Name");
        this.initializeHull(1);
        this.initializeFirepower(1);
        this.initializeSpeed(1);
        this.initializeRange(1);
    }
    
    /**
     * Check if it's on an edge
     * Will need to define x and y edges
     * Once it's on an edge, it will move accordingly
     * Do we need a while loop?
     * Then it needs to know when it hits a corner
     * Then move accordingly
     * Just go south until it hits the border
     * What if a ship is blocking us?
     * - Shoot it
     * - Go to a different edge
     */
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        // Fill in your strategy here
        
        //Gives coor of our ship
        Coord shipCoord = this.getCoord();
        int shipX = shipCoord.getX();
        int shipY = shipCoord.getY();
        
        System.out.println("\nx coor:" + shipX);
        System.out.println("y coor:"+ shipY);
        
        int arenaX = arena.getXSize();
        int arenaY = arena.getYSize();
        
        System.out.println("\narena x size:" + arenaX);
        System.out.println("arena y size:"+ arenaY);
        
    }
    
}