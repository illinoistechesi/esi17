package esi17.cguy1;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class Enemy extends Ship {
    
    public Enemy ()  {
        this.initializeName("Enemy");
        this.initializeOwner("cguy1");
        this.initializeHull(2);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
        this.initializeRange(4);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        this.move(arena, Direction.WEST);
      
        List<Ship> nearby = this.getNearbyShips(arena);
        for (int i = 0; i < nearby.size(); i++) {
            Ship unknown = nearby.get(i);
            boolean isOnMyTeam = this.isSameTeamAs(unknown);
            
            if (isOnMyTeam) {
                //System.out.println("This ship is on my team!");
            }      
            else {
                Coord coord = this.getShipCoord(arena, unknown);
                int x = coord.getX();
                int y = coord.getY();
                this.fire(arena, x, y);
            }
        }
        
    }
        
}
    