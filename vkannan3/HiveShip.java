package esi17.vkannan3;
import battleship.core.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/*
 * Hive Ship
 * @author Vinesh
 */
public class HiveShip extends Ship {
    
    public HiveShip() {
        this.initializeName("Hive Ship");
        this.initializeOwner("The Evil Fleet");
        this.initializeHull(2);
        this.initializeFirepower(2);
        this.initializeSpeed(2);
        this.initializeRange(4);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        Ship target = this.getNextTarget(arena);
        while (target != null && this.getRemainingShots() > 0) {
            this.approachTarget(arena, target);
            Coord coord = target.getCoord();
            while (!target.isSunk() && this.getRemainingShots() > 0){
                this.fire(arena, coord.getX(), coord.getY());
                System.out.println("Loop in line 36.");
            }
            target = this.getNextTarget(arena);
        }
    }
    
    public Ship getNextTarget(Arena arena) {
        Ship target = null;
        List<Ship> options = new ArrayList<Ship>();
        for (Ship ship : this.getAllEnemyShips(arena)) {
            int dist = HiveShip.getArenaDistanceBetween(this, ship);
            int reach = this.getRemainingMoves() + this.getRange();
            boolean isAlive = !ship.isSunk();
            boolean canReach = reach >= dist;
            if (isAlive && canReach) {
                options.add(ship);
            }
        }
        Ship self = this;
        Collections.sort(options, new Comparator<Ship>() {
            public int compare(Ship s1, Ship s2) {
                int comparison = 0;
                int d1 = HiveShip.getArenaDistanceBetween(self, s1);
                int d2 = HiveShip.getArenaDistanceBetween(self, s2);
                int diff = d1 - d2;
                if (diff == 0) {
                    int h1 = s1.getHealth();
                    int h2 = s2.getHealth();
                    comparison = h1 - h2;
                } else {
                    comparison = diff;
                }
                return comparison;
            } 
        });
        if (options.size() > 0) {
            target = options.get(0);
        }
        return target;
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
    
    public void approachTarget(Arena arena, Ship target) {
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
                yDir = yDiff > 0 ? Direction.SOUTH : Direction.NORTH;
            }
            if (xDir != null) {
                boolean xFree = this.canMoveInDirection(arena, start, xDir);
                if (xFree) {
                    this.move(arena, xDir);
                    start = this.getCoord();
                } else {
                    xDir = null;
                }
            }
            if (yDir != null) {
                boolean yFree = this.canMoveInDirection(arena, start, yDir);
                if (yFree) {
                    System.out.println(this.getCoord());
                    System.out.println(yDir + " " + this);
                    System.out.println(arena.getShipAt(this.getCoord().getX(), this.getCoord().getY()+1));
                    this.move(arena, yDir);
                    System.out.println(this.getCoord());
                    start = this.getCoord();
                } else {
                    yDir = null;
                }
            }
            notCloseEnough = !arena.isInRange(this, target);
            canMove = this.getRemainingMoves() > 0;
            boolean noValidMove = xDir == null && yDir == null;
            if (noValidMove) {
                break;
            }
            System.out.println("Loop in line 124: " + xDir + " " + yDir + " " + this.getRemainingMoves());
        }
    }
    
    public boolean canMoveInDirection(Arena arena, Coord start, Direction dir) {
        boolean isFree = false;
        switch (dir) {
            case NORTH:
                isFree = canMoveToSpace(arena, start.getX(), start.getY() - 1);
                break;
            case EAST:
                isFree = canMoveToSpace(arena, start.getX() + 1, start.getY());
                break;
            case SOUTH:
                isFree = canMoveToSpace(arena, start.getX(), start.getY() + 1);
                break;
            case WEST:
                isFree = canMoveToSpace(arena, start.getX() - 1, start.getY());
                break;
            default:
                break;
        }
        return isFree;
    }
    
    public boolean canMoveToSpace(Arena arena, int x, int y) {
        return arena.getShipAt(x, y) == null;
    }
    
    public static int getArenaDistanceBetween(Ship a, Ship b) {
        Coord start = a.getCoord();
        Coord end = b.getCoord();
        int xDiff = Math.abs(end.getX() - start.getX());
        int yDiff = Math.abs(end.getY() - start.getY());
        return xDiff + yDiff - 1;
    }
    
}