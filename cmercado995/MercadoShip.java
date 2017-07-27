package esi17.cmercado995;
import battleship.core.*;
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
                
            
            // Gives me my Coordinates as integers
            Coord myCoord = this.getCoord();
            int MyX = myCoord.getX();
            int MyY = myCoord.getY();
            
            // Literally copy-pasted to get around "getNearbyShips" being protected lol
            /*** Academic Dishonesty ***/
            /****** Declaration of a function ***************/
            /*
            for(Ships ship : getAllEnemies){
                public boolean inEnemyRange(Ship self, Ship target) {
                    Coord st = self.getCoord();
                    Coord ct = target.getCoord();
                    int range = self.getRange();
                    int[] tXRange = {ct.getX() - target.getRange, ct.getX() + target.getRange};
                    int[] tYRange = {ct.getY() - target.getRange, ct.getY() + target.getRange};
                    boolean inXRange = st.getX() >= tXRange[0] && st.getX() <= tXRange[1];
                    boolean inYRange = st.getY() >= tYRange[0] && st.getY() <= tYRange[1];
                    return inXRange && inYRange;
                }
            }
            // What to do if I can be targeted
            this.inEnemyRange;
            */
        }
    }
}