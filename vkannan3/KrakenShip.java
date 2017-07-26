package esi17.vkannan3;
import battleship.core.*;
import java.util.List;

/*
 * Vinesh
 * @author Your Name
 */
public class KrakenShip extends Ship {
    
    public KrakenShip() {
        this.initializeName("Kraken");
        this.initializeOwner("Vinesh");
        this.initializeHull(1);
        this.initializeFirepower(1);
        this.initializeSpeed(1);
        this.initializeRange(4);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {

    }
    
    
    public List<Ship> getAllEnemyShips(arena) {
        List<Ship> res = new ArrayList<Ship>();
        for (Ship ship : arena.getAllShips()) {
            if (!ship.isSameTeamAs(this)) {
                res.add(ship);
            }
        }
        return res;
    }
    
    public boolean isCoordInArena(Arena arena, Coord coord) {
        boolean inX = coord.getX() >= 0 && coord.getX() < arena.getXSize();
        boolean inY = coord.getY() >= 0 && coord.getY() < arena.getYSize();
        return inX && inY;
    }
    
    public List<Coord> getHitBox(Arena arena, Ship source, Ship target) {
        List<Coord> res = new ArrayList<Coord>();
        Coord tar = target.getCoord();
        int radius = source.getRange();
        int leftX = tar.getX() - radius;
        int rightX = tar.getX() + radius;
        int topY = tar.getY() - radius;
        int bottomY = tar.getY() + radius;
        for (int x = leftX; x <= rightX; x++) {
            res.add(new Coord(x, topY));
            res.add(new Coord(x, bottomY));
        }
        for (int y = topY; y <= bottomY; y++) {
            res.add(new Coord(leftX, y));
            res.add(new Coord(rightX, y));
        }
        return res;
    }
    
    public void getPathsBetween(Coord start, Coord end) {
        int dx = 1;
        int dy = 1;
        int diffX = end.getX() - start.getX();
        int diffY = end.getY() - start.getY();
    }
}