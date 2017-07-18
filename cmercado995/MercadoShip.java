package esi17.cmercado995;
import battleship.core.*;
import java.util.List;

/*
 * Mercado Ship
 * @author Christian Mercado
 */
public class MercadoShip extends Ship {
    
    public MercadoShip() {
        this.initializeName("Mercado Ship");
        this.initializeOwner("Christian Mercado");
        this.initializeHull(3);
        this.initializeFirepower(3);
        this.initializeSpeed(0);
        this.initializeRange(4);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena){
        // Fill in your strategy here
        
        /************************
        Nick's code for movement
        *************************/
        /*
        // gets the current location of the ship
            Coord coord = this.getCoord();
            int x = coord.getX();
            int y = coord.getY();
            
        if (x > 8) {
                this.move(arena, Direction.WEST);
            }
            else if (x < 2) {
                this.move(arena, Direction.EAST);
            }
            else if (y > 8) {
                this.move(arena, Direction.NORTH);
            }
            else if (y < 2) {
                this.move(arena, Direction.SOUTH);
            }
            else {
                // make a list of all the location, and store it in a variable
                Direction[] possibleMovement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
                
                // get a random number and store it in a variable
                Direction randomNumber = arena.getRandom().nextInt(4)
                
                // get a random movement by using the random number to access one possibleMovement 
                this.move(arena, possibleMovement[randomNumber]);
            }
            */
        this.move(arena, Direction.EAST);
                
        // Loop over all nearby ships
        List<Ship> nearby = this.getNearbyShips(arena);
        for (Ship ship : nearby) {
                System.out.println("One nearby ship has " + ship.getHealth() + " HP left.");
            }
        
        // Get the first ship in the list, if there are that many
        if(nearby.size() > 0){
             Ship first = nearby.get(0);
             Coord enemyCoord = this.getShipCoord(arena, first);
             int enemyX = enemyCoord.getX();
             int enemyY = enemyCoord.getY();
             this.fire(arena, enemyX, enemyY);
        }else{
            System.out.println("No enemies nearby");
        }
    }
}