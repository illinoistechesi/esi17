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
        this.initializeHull(2);
        this.initializeFirepower(2);
        this.initializeSpeed(2);
        this.initializeRange(4);
    }
    
    //public static List<Ship> targets = new ArrayList<Ship>();
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        //System.out.println("\n");
        //System.out.println("I am at " + this.getCoord());
        Ship target = this.getNextTarget(arena);
        if (target != null) {
            this.moveTowards(arena, target);
            for (int f = 0; f < this.getFirepower(); f++) {
                Coord coord = target.getCoord();
                this.fire(arena, coord.getX(), coord.getY());
            }
        }
        //System.out.println(String.format("%s target ship!", target.isSunk() ? "Sunk": "Did not sink"));
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
            Direction xDir = null;
            Direction yDir = null;
            if (xMag > this.getRange()) {
                xDir = xDiff > 0 ? Direction.EAST : Direction.WEST;
            }
            if (yMag > this.getRange()) {
                yDir = yDiff >0 ? Direction.SOUTH : Direction.NORTH;
            }
            if (xDir != null) {
                boolean xFree = this.checkDirection(arena, start, xDir);
                if (xFree) {
                    this.move(arena, xDir);
                } else {
                    //System.out.println("Can't move " + xDir);
                    xDir = null;
                }
            }
            if (yDir != null) {
                boolean yFree = this.checkDirection(arena, start, yDir);
                if (yFree) {
                    this.move(arena, yDir);
                } else {
                    //System.out.println("Can't move " + yDir);
                    yDir = null;
                }
            }
            boolean madeValidMove = xDir != null || yDir != null;
            notCloseEnough = !arena.isInRange(this, target);
            canMove = this.getRemainingMoves() > 0 && madeValidMove;
        }
    }
    
    public boolean checkDirection(Arena arena, Coord start, Direction dir) {
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
        return isFree;
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
        if (target != null) {
            //System.out.println("Locked on to a target at " + target.getCoord());
            int d = this.getArenaDistanceBetween(this, target);
            int s = this.getSpeed();
            int r = this.getRange();
            int t = s + r;
            //System.out.println(String.format("Target is %d away: %d + %d = %d", d, s, r, t));
            int diff = d - t;
            //System.out.println("Diff: " + diff);
        } else {
            //System.out.println("No targets to lock onto.");
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