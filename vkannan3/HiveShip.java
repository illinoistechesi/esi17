package esi17.vkannan3;
import battleship.core.*;
import java.util.List;
import java.util.ArrayList;

/*
 * Vinesh
 * @author Your Name
 */
public class HiveShip extends Ship {
    
    public HiveShip() {
        this.initializeName("Hive Ship");
        this.initializeOwner("Vinesh");
        this.initializeHull(3);
        this.initializeFirepower(2);
        this.initializeSpeed(3);
        this.initializeRange(2);
    }
    
    //public static List<Ship> targets = new ArrayList<Ship>();
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        Ship target = this.getNextTarget(arena);
        if (target != null) {
            this.moveTowards(arena, target);
            for (int f = 0; f < this.getFirepower(); f++) {
                Coord coord = target.getCoord();
                this.fire(arena, coord.getX(), coord.getY());
            }
        }
    }
    
    public void moveTowards(Arena arena, Ship target) {
        boolean notCloseEnough = !arena.isInRange(this, target);
        boolean canMove = this.getRemainingMoves() > 0;
        while (notCloseEnough && canMove) {
            Coord start = this.getCoord();
            Coord end = target.getCoord();
            int xDiff = end.getX() - start.getX();
            int yDiff = end.getY() - start.getY();
            int xMag = Math.abs(xDiff);
            int yMag = Math.abs(yDiff);
            Direction dir = null;
            if (xMag > this.getRange()) {
                dir = xDiff > 0 ? Direction.EAST : Direction.WEST;
            } else if (yMag > this.getRange()) {
                dir = yDiff >0 ? Direction.SOUTH : Direction.NORTH;
            }
            if (dir != null) {
                boolean isFree = false;
                switch (dir) {
                    case NORTH:
                        isFree = checkSpace(arena, start.getX(), start.getY() - 1);
                        break;
                    case EAST:
                        isFree = checkSpace(arena, start.getX() + 1, start.getY());
                        break;
                    case SOUTH:
                        isFree = checkSpace(arena, start.getX(), start.getY() - 1);
                        break;
                    case WEST:
                        isFree = checkSpace(arena, start.getX() - 1, start.getY());
                        break;
                    default:
                        break;
                }
                if (isFree) {
                    this.move(arena, dir);   
                }
            }
            notCloseEnough = !arena.isInRange(this, target);
            canMove = this.getRemainingMoves() > 0;
        }
    }
    
    public boolean checkSpace(Arena arena, int x, int y) {
        return arena.getShipAt(x, y) == null;
    }
    
    public Ship getNextTarget(Arena arena) {
        Ship target = null;
        for (Ship ship : this.getAllEnemyShips(arena)) {
            int dist = this.getArenaDistanceBetween(this, ship);
            int reach = this.getSpeed() + this.getRange();
            boolean isAlive = !ship.isSunk();
            boolean canReach = reach >= dist;
            if (isAlive && canReach) {
                target = ship;
                break;
            }
        }
        return target;
    }
    
    public int getArenaDistanceBetween(Ship a, Ship b) {
        Coord start = a.getCoord();
        Coord end = b.getCoord();
        int xDiff = Math.abs(end.getX() - start.getX());
        int yDiff = Math.abs(end.getY() - start.getY());
        return xDiff + yDiff - 1;
    }
    
    public List<Ship> getAllEnemyShips(Arena arena) {
        List<Ship> res = new ArrayList<Ship>();
        for (Ship ship : arena.getAllShips()) {
            if (!ship.isSameTeamAs(this)) {
                res.add(ship);
            }
        }
        return res;
    }
    
}