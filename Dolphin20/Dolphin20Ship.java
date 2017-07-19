package esi17.Dolphin20;
import battleship.core.*;
import java.util.List;

/*
 * Dolphin20 Ship
 * @author Your Name
 */
public class Dolphin20Ship extends Ship {
    
    public Dolphin20Ship() {
        this.initializeName("Dolphin20 Ship");
        this.initializeOwner("Jovany Soto");
        this.initializeHull(2);
        this.initializeFirepower(3);
        this.initializeSpeed(3);
        this.initializeRange(2);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        // Fill in your strategy here
    
        
        this.move(arena, Direction.EAST);
        this.move(arena, Direction.EAST);
        this.move(arena, Direction.EAST);
        List<Ship> nearby = this.getNearyShips(arena);
        
        if (nearby.size() > 0) {
           Ship first = nearby.get(0);
        
           Coord coord =this.getShipCoord(arena, first);
           int x = coord.getX();
           int y = coord.getY();
           
          this.fire(arena, x, y);
          //this.move(arena, Direc
        } // Forgot this curly brace
        
        
        
        
        
        
        
    }
    
}