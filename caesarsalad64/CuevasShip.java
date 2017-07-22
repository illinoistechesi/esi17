package esi17.caesarsalad64;
import battleship.core.*;
import java.util.List;

/*
 * Cuevas Ship
 * @author Your Name
 */
 
//  https://github.com/illinoistechesi/battleship 
 
public class CuevasShip extends Ship 
{
    public CuevasShip() 
    { 
        this.initializeName("Cuevas Ship");
        this.initializeOwner("Your Name");
        this.initializeHull(4);      //Health
        this.initializeFirepower(3); //Power
        this.initializeSpeed(1);     //Speed
        this.initializeRange(2);     //Range of visibility
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    
    @Override
    public void doTurn(Arena arena) {
    // Fill in your strategy here
        
    this.move(arena, Direction.WEST);
    
    List<Ship> nearby = this.getNearbyShips(arena);
    for (int i = 0; i < nearby.size(); i++) 
    {
        Ship unknown = nearby.get(i);
        boolean isOnMyTeam = this.isSameTeamAs(unknown);
        if (isOnMyTeam) 
        {
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
            
            System.out.println("Fired at: " + x + ", " + y); 
            }
        }
    }
}