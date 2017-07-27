package esi17.estefaniaLopez4645;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class FriendShip extends Ship {
    
    public FriendShip() {
        this.initializeName("FriendShip");
        this.initializeOwner("Your Name");
        this.initializeHull(1);
        this.initializeFirepower(3);
        this.initializeSpeed(3);
        this.initializeRange(3);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        
        this.move(arena, Direction.EAST);
        //this.move(arena, Direction.SOUTH);
        
 
        List<Ship> alive= this.getNearbyShips(arena);
        for (int i = 0; i < alive.size(); i++) {
            Ship ship = alive.get(i);
            // Call the getTeam() method on any ship to get its team name
            String myTeam = this.getTeam();
            String theirTeam =ship.getTeam();
            // To compare Strings, we have to use the special .equals() method
            // It will return true if the strings are equal and false if they are not
            if (theirTeam.equals(myTeam)) {
            // Don't shoot!
            } 
            else {
        
                Coord coord = ship.getCoord();
                int x = coord.getX();
                int y = coord.getY();
                if(this.getRemainingShots()>0 && arena.isInRange(this, ship ) ){
                    int totalHealth=ship.getHealth();
                   for(int j=0; j<totalHealth; j++){ 
                         this.fire(arena, x, y);
                   }
                }

            }

        }
    }
}
