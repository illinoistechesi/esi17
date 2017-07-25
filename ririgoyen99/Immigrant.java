package esi17.ririgoyen99;
import battleship.core.*;
import java.util.List;

/*
 * Immigrant
 * @ririgoyen99
 */
public class Immigrant extends Ship {
    
    public Immigrant() {
        this.initializeName("Immigrant");
        this.initializeOwner("ririgoyen99");
        this.initializeHull(6);
        this.initializeFirepower(2);
        this.initializeSpeed(1);
        this.initializeRange(1);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
   public void doTurn(Arena arena) {
        this.move(arena, Direction.EAST);
        this.move(arena, Direction.NORTH);
        this.move(arena, Direction.EAST);
        this.move(arena, Direction.EAST);
        this.move(arena, Direction.EAST);
        this.move(arena, Direction.EAST);
        this.move(arena, Direction.EAST);
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
