package esi17.joshuatgonzalez;
import battleship.core.*;
import java.util.List;

/*
 * TheShip
 * @Joshua Gonzalez
 */
public class TheShip extends Ship {
    
    public TheShip() {
        this.initializeName("TheShip");
        this.initializeOwner("Joshua Gonzalez");
        this.initializeHull(4);
        this.initializeFirepower(1);
        this.initializeSpeed(4);
        this.initializeRange(1);
        
    }
    
    /*
     * Determines what actions the ship  will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        this.move(arena, Direction.WEST);
        List<Ship> nearby = this.getNearbyShips(arena);
       Ship target = nearby.get(0);
       String myTeam = this.getTeam();
       String otherTeam = target.getTeam();
       if(!otherTeam.equals(myTeam)){   
            Coord location = getShipCoord(arena,target);
            int x = location.getX();
            int y = location.getY();
            System.out.println(x+" "+y);
            this.fire(arena, x, y);
       }
    }
    
}