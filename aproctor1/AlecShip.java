package esi17.aproctor1;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class AlecShip extends Ship {
    
    public AlecShip() {
        this.initializeName("AlecShip");
        this.initializeOwner("Alec");
        this.initializeHull(2);
        this.initializeFirepower(2);
        this.initializeSpeed(1);
        this.initializeRange(5);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
        public void doTurn(Arena arena) {
        this.move(arena, Direction.WEST);
        
        List<Ship> nearby = this.getNearbyShips(arena);
        for (int i = 0; i < nearby.size(); i++) {
        Ship ship = nearby.get(i);
        String myTeam = this.getTeam();
        String theirTeam = ship.getTeam();
        if (theirTeam.equals(myTeam)) {
    }   else {
        Coord coord = ship.getCoord();
        int x = coord.getX();
        int y = coord.getY();
        this.fire(arena, x, y);
        }
    }
}
}

