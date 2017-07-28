package esi17.cmercado995;
import battleship.core.*;
import battleship.ships.QueenShip;

import java.util.ArrayList;
import java.util.List;
/******************************************************
 * This ship includes a lot of academic dishonesty, 
 * Also, does not shoot itself or friends.  
 * @author Christian Mercado
 * @version idk
 * @date 7/24/2017
*******************************************************/
public class MercadoShip extends Ship {
  
	public MercadoShip() {
        this.initializeName("Mercado Ship");
        this.initializeOwner("Christian Mercado");
        this.initializeHull(3);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
        this.initializeRange(3);
    }
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */

    @Override
    public void doTurn(Arena arena){
        // List of Nearby Ships
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
            
            // If not sunk, not on my team, and its name is Queen ship, shoot.
            while(ship.getHealth() > 0 && !ship.isSunk() && !otherTeam.equals(myTeam) && ship.getName().equals("Queen Ship")){
                Coord enemyCoord = this.getShipCoord(arena, ship);
                int enemyX = enemyCoord.getX();
                int enemyY = enemyCoord.getY();
                this.fire(arena, enemyX, enemyY);
                if(this.getRemainingShots() == 0) break;
            }
            
            // If not sunk, not on my team, and any name, shoot.
            while(ship.getHealth() > 0 && !ship.isSunk() && !otherTeam.equals(myTeam)){
                Coord enemyCoord = this.getShipCoord(arena, ship);
                int enemyX = enemyCoord.getX();
                int enemyY = enemyCoord.getY();
                this.fire(arena, enemyX, enemyY);
                if(this.getRemainingShots() == 0) break;
            }
            
            // If in an enemy's range, move out of the range
            // I apologize if there are too many duplicate variables, I don't understand java yet
            boolean inEnemyRange = arena.isInRange(ship, this);
            if(inEnemyRange){
            	Coord enemyCoord = ship.getCoord();
            	Coord myCoord = this.getCoord();
            	int EnemyRange = ship.getRange();
            	if(myCoord.getX() > enemyCoord.getX() + EnemyRange){
            		this.move(arena, Direction.SOUTH);
            	}else if(myCoord.getX() < enemyCoord.getX() + EnemyRange){
            		this.move(arena, Direction.NORTH);
            	}else if(myCoord.getY() > enemyCoord.getY() + EnemyRange){
            		this.move(arena, Direction.EAST);
            	}else if(myCoord.getY() < enemyCoord.getY() + EnemyRange){
            		this.move(arena, Direction.WEST);
            	}else if(this.getRemainingMoves() == 0) break;
            	}else {
            		System.out.println("What now?");
            	}
        }
 }
    public boolean isInRange(Ship self, Ship target) {
		Coord st = self.getCoord();
		Coord ct = target.getCoord();
		int range = self.getRange();
		int[] sXRange = {st.getX() - range, st.getX() + range};
		int[] sYRange = {st.getY() - range, st.getY() + range};
		boolean inXRange = ct.getX() >= sXRange[0] && ct.getX() <= sXRange[1];
		boolean inYRange = ct.getY() >= sYRange[0] && ct.getY() <= sYRange[1];
		return inXRange && inYRange;
	}
}