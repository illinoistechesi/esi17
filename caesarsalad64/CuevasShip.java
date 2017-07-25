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
        
    //My ship's location:
    Coord coord = this.getCoord();
    int myX = coord.getX();
    int myY = coord.getY();
    System.out.println("My ship is at (" + myX + ", " + myY + ").");        

    //Logic for finding teammates & firing at enemies:
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
            //Get shots to take down a ship instead of random shots
            System.out.println("This ship is an enemy.");
            Coord location = this.getShipCoord(arena, unknown);
            int x = location.getX();
            int y = location.getY();
        
            for(i = 0; i < 3; i++)
            {
                this.fire(arena, x, y);
            }
            
            //System.out.println(getNearbyShips(arena));
            System.out.println("Fired at: (" + x + ", " + y + ")"); 
            }
        }
        
    //My ship's movement:
    this.move(arena, Direction.WEST);
        
    }
}