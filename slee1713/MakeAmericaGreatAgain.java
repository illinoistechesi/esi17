package esi17.slee1713;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class MakeAmericaGreatAgain extends Ship {
    
    public MakeAmericaGreatAgain() {
        this.initializeName("Make America Great Again");
        this.initializeOwner("Your Name");
        this.initializeHull(3);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
        this.initializeRange(3);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        //this.move(arena, Direction.EAST);
        int i = arena.getRandom().nextInt(10);
        if (i > 3) {
            this.move(arena, Direction.NORTH);
        }
        else if (i > 2) {
            this.move(arena, Direction.WEST);
        }
        else if (i < 8) {
            this.move(arena, Direction.SOUTH);
        }
        else if (i > 5) {
            this.move(arena, Direction.EAST);
        }
        List<Ship> nearby = this.getNearbyShips(arena);
        if (nearby.size() > 0) {
            Ship first = nearby.get(0);
            Coord coord  = this.getShipCoord(arena, first);
            int x = coord.getX();
            int y = coord.getY(); 
            this.fire(arena, x, y);
        // Fill in your strategy here
         }
    }
    
    
}