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
        this.setTeam("Fairly Strong Ducks");
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
                if(shipsLeft != 1){
                    System.out.println("There are " + shipsLeft + " ships left.");
                }else{
                    System.out.println("There is " + shipsLeft + "ship left.");
                }
        }
            
        //if target is alive and not in my team, shoot
        for(Ship ship : nearby){
            String myTeam = this.getTeam();
            String otherTeam = ship.getTeam();
            
            while(ship.getHealth() > 0 && !ship.isSunk() && !otherTeam.equals(myTeam)){
                Coord enemyCoord = this.getShipCoord(arena, ship);
                int enemyX = enemyCoord.getX();
                int enemyY = enemyCoord.getY();
                this.fire(arena, enemyX, enemyY);
            }
        }
    }
}