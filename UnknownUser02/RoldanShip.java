package esi17.UnknownUser02;
import battleship.core.*;
import java.util.List;

/*
 * Roldan Ship
 * @author Your Name
 */
public class RoldanShip extends Ship {
    
    public RoldanShip() {
        this.initializeName(" RoldanShip");
        this.initializeOwner("Tony");
        this.initializeHull(2);
        this.initializeFirepower(2);
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
        // FIll in your strategy here
   
        Coord me = this.getCoord();
        int a = me.getX();
        int b = me.getY();
        String myTeam = this.getTeam();
        List<Ship> nearby = this.getNearbyShips(arena);
        for(int i = 0;i < nearby.size();i++){
            Ship ship = nearby.get(i);
            String otherTeam = ship.getTeam();
            if (!otherTeam.equals(myTeam)){
                Coord enemyCoord = this.getShipCoord(arena, ship);
                int enemyX = enemyCoord.getX();
                int enemyY = enemyCoord.getY();
                
                this.move(arena, Direction.WEST);
                this.move(arena, Direction.WEST);
                this.fire(arena, enemyX, enemyY);
            
            }
        }
    }
}