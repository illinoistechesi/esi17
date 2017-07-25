package esi17.npatel6219;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class NehaShip extends Ship {
    
    public NehaShip() {
        this.initializeName("Neha Ship");
        this.initializeOwner("NehaP");
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
       this.move(arena, Direction.EAST);
       Ship target = null;
       List<Ship> nearby = this.getNearbyShips(arena);
       for (int i = 0; i < nearby.size(); i++) {
         Ship ship = nearby.get(i);
          String myTeam = this.getTeam();
          String theirTeam = ship.getTeam();
          if (theirTeam.equals(myTeam)) {
              // Don't shoot!
          } else {
             if (ship.getHealth() < minHealth) {
                 target=ship;
                 minHealth = ship.getHealth();
             }
          }
       }
       if (target != null) {
          Coord coord = target.getCoord();
          int x = coord.getX();
          int y = coord.getY();
          int myX = this.getCoord().getX();
          int myY = this.getCoord().getY();
    
          if (myX>x) {
              this.move(arena,Direction.WEST);
          } else {
          //if (myX <x) {
              this.move(arena,Direction.EAST);
          } //else
          if (myY > y) {
              this.move(arena,Direction.NORTH);
          } else {
              this.move(arena,Direction.SOUTH);
          }
            this.fire(arena, x, y);
            this.fire(arena, x, y);
       }
       
    
    }
}