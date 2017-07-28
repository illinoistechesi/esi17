package esi17.smallcpu;
import battleship.core.*;
import java.util.List;
import java.util.Scanner;

/*
 * Custom Ship
 * @author Your Name
 */
public class SmallcpuShip extends Ship {
    
    public SmallcpuShip() {
        this.initializeName("SS Winner");
        this.initializeOwner("Jor-ell");
        this.initializeHull(5);
        this.initializeFirepower(2);
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
        
        List<Ship> nearby = this.getNearbyShips(arena);
        int count = nearby.size();
        System.out.println("There are " + count + " ships near me.");
      
      
      
       for (int i = 0; i < nearby.size(); i++) {
           Ship other = nearby.get(i);
            boolean isOnMyTeam = this.isSameTeamAs(other);
            if (isOnMyTeam) {
                System.out.println("This ship is on my team!");
            } else {
                System.out.println("This ship is an enemy.");
            }
        }
          
      
      
      
        //Locate and Destroy
        if(count==0){
            this.move(arena, Direction.EAST);
            
        }
        /*else if (isOnMyTeam) {
            System.out.pringln("not fired");
        }*/
        else{
               List<Ship> list = this.getNearbyShips(arena);
            Ship enemy = list.get(0);
           Coord coord = getShipCoord(arena, enemy);
            int x = coord.getX();
            int y = coord.getY();
            System.out.println("the ship is at (" + x + ", " + y + ").");
            this.fire(arena,  x, y);
        }
      
        //this.fire(arena, x,y);
        
       
    }
    
}