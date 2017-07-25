package esi17.jbrimm;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class ColossusofClout extends Ship {
    
    public ColossusofClout() {
        this.initializeName("ColossusofClout");
        this.initializeOwner("jbrimm");
        this.initializeHull(2);       //HEALTH
        this.initializeFirepower(3); //POWER
        this.initializeSpeed(1);     //SPEED
        this.initializeRange(4);    //RANGE
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
    //Fill in strategy here
    
    
      this.move(arena, Direction.WEST); 

    List<Ship> nearby = this.getNearbyShips(arena);
    
    for (int i = 0; i < nearby.size(); i++) 
    {
    Ship unknown = nearby.get(i);
    boolean isOnMyTeam = this.isSameTeamAs(unknown);
    
    if (isOnMyTeam) {
        System.out.println("This ship is on my team!");
        //Don't Shoot
    } 
    else 
    {
        System.out.println("This ship is an enemy.");
        
        Coord location = this.getShipCoord(arena, unknown);
        int x = location.getX();
        int y = location.getY(); 
        
        
        this.fire(arena, x, y);

        System.out.println("Fired at: (" + x + "," + y + ")");
            }
        }
    }
}
