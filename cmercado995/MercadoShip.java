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
                
        // Loop over all nearby ships
        List<Ship> nearby = this.getNearbyShips(arena);
        for (Ship ship : nearby) {
                System.out.println("One nearby ship has " + ship.getHealth() + " HP left.");
                int shipsLeft = (arena.countLiveShips() - 1);
                if((arena.countLiveShips() - 1) != 1){
                    System.out.println("There are " + shipsLeft + " ships left.");
                }else{
                    System.out.println("There is " + shipsLeft + "ship left.");
                }
        }
        
         //Only shoots at ship if its health is greater 
        //than 0 otherwise moves to the second one in the range
        // Get the first ship in the list, if there are that many
        int n = 0;
        
        Ship target = nearby.get(n);
        if (target.getHealth() > 0 && !target.isSunk()){
            Coord enemyCoord = this.getShipCoord(arena, target);
            int enemyX = enemyCoord.getX();
            int enemyY = enemyCoord.getY();
            this.fire(arena, enemyX, enemyY);
        }else{
            n++;
            target = nearby.get(n);
            Coord enemyCoord = this.getShipCoord(arena, target);
            int enemyX = enemyCoord.getX();
            int enemyY = enemyCoord.getY();
            this.fire(arena, enemyX, enemyY);
        }
    }
}