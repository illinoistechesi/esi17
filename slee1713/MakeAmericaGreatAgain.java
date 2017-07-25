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
        this.initializeHull(2);
        this.initializeFirepower(2);
        this.initializeSpeed(1);
        this.initializeRange(5);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
     
    @Override
    public void doTurn(Arena arena) { 
        List<Ship> nearby = this.getNearbyShips(arena);
    for (int i = 0; i < nearby.size(); i++) {
        Ship ship = nearby.get(i);
        String myTeam = this.getTeam();
        String theirTeam = ship.getTeam();
        if (theirTeam.equals(myTeam)) {
        } else {
            Coord coord = ship.getCoord();
            int x = coord.getX();
            int y = coord.getY();
            this.fire(arena, x, y);
            this.fire(arena, x, y);
            
        // this.move(arena, Direction.EAST);
         this.fire(arena, 9, 1);
        }
    }
         Coord me = this.getCoord();
    int a = me.getX();
    int b = me.getY();
    
    
    if (a > 9) {
        this.move(arena, Direction.WEST);
        
    }
    else if (a < 9) {
        this.move(arena, Direction.EAST);
    }
       // this.move(arena, Direction.EAST);
        //int i = arena.getRandom().nextInt(10);
    /*(int i = 0; i < nearby.size(); i++); { 
        if (i > 3;) {
            this.move(arena, Direction.NORTH);
        }
        else if (i > 2); {
            this.move(arena, Direction.WEST);
        }
        else if (i < 8); {
            this.move(arena, Direction.SOUTH);
        }
        else if (i > 5); {
            this.move(arena, Direction.EAST);
        }
        *
       //else this.fire(arena, 9, 2);
      
       //this.move(arena, Direction.WEST);
       
        /*List<Ship> nearby = this.getNearbyShips(arena);
        if (nearby.size() > 0) {
            Ship first = nearby.get(0);
            Coord coord  = this.getShipCoord(arena, first);
            int x = coord.getX();
            int y = coord.getY(); 
            this.fire(arena, x, y); 
        // Fill in your strategy here
         }*/
    }
    
    
}