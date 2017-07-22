package esi17.cguy1;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class Pirateship extends Ship {
    
    public Pirateship ()  {
        this.initializeName("Pirateship");
        this.initializeOwner("cguy1");
        this.initializeHull(1);
        this.initializeFirepower(4);
        this.initializeSpeed(0);
        this.initializeRange(5);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        this.move(arena, Direction.EAST);
        //List<Ship> nearby = this.getNearbyShips(arena);
       
        //if (nearby.size() > 0) {
           
        //Ship first = nearby.get(0);
        //Coord coord = this.getShipCoord(arena, first);
        //int x = coord.getX();
        //int y = coord.getY();
        //this.fire(arena, x, y);
        
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
    