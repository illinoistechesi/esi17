package esi17.AusWorley1;
import battleship.core.*;
import java.util.List;


//   Mars
//   @AusWorley1
 
public class Mars extends Ship {
    
    public Mars() {
        this.initializeName("Mars");
        this.initializeOwner("Austin Worley");
        this.initializeHull(4);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
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
        Coord coord = this.getCoord();
        int x = coord.getX();
     if (x < 5) {
        this.move(arena, Direction.EAST);
        List<Ship> nearby = this.getNearbyShips(arena);
        Ship a = nearby.get(0);
        System.out.println(a.getHealth());
     }
    }
}