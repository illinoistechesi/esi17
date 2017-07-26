package esi17.ageorgescu17;
import battleship.core.*;
import java.util.List;

/*
 * Maverick
 * @author Your Name
 */
public class Maverick extends Ship {
    
    public Maverick() {
        this.initializeName("Maverick");
        this.initializeOwner("Adriana");
        this.initializeHull(4);
        this.initializeFirepower(2);
        this.initializeSpeed(2);
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
        int minHealth = 100;
        this.move(arena, Direction.WEST);
        Ship target = null;
        
        //Checking for nearby ships
        List <Ship> nearby = this.getNearbyShips(arena);
        
        for (int i = 0; i < nearby.size(); i++){
            Ship ship = nearby.get(i);
            String myTeam = this.getTeam();
            String theirTeam = ship.getTeam();
            if (theirTeam.equals(myTeam)){
            } 
            else{
                
                if (ship.getHealth() < minHealth){
                     target = ship;
                     minHealth = ship.getHealth();
                }
                   
                    
                  
            }
    }
       //Receives enemy coordinates than moves and fires
        if (target != null) {
            Coord coord = target.getCoord();
            int x = coord.getX();
            int y = coord.getY();
            int myX = this.getCoord().getX();
            int myY = this.getCoord().getY();
        
            if (x < myX) {
                this.move(arena, Direction.WEST);
            } 
        
            else if (x > myX) {
                this.move(arena, Direction.EAST);
            } 
        
            else if (y < myY) {
                this.move(arena, Direction.NORTH);
            } 
       
            else if (y > myY) {
                this.move(arena, Direction.SOUTH);
            }
            
            //fire at ship
            this.fire(arena, x,y); 
            this.fire(arena, x,y); 
        }
        else {
            this.move(arena, Direction.WEST);
        }    
            
                          
           
    }
    
}