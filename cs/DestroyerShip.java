package esi17.cs;
import battleship.core.*;
import java.util.*;

/*
 * Destroyer Ship
 * @author The Evil Fleet
 */
public class DestroyerShip extends EvilFleetShip {
    
    public DestroyerShip() {
        this.initializeName("Destroyer Ship");
        this.initializeOwner("The Evil Fleet");
        this.initializeHull(1);
        this.initializeFirepower(4);
        this.initializeSpeed(0);
        this.initializeRange(5);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        List<Ship> ships = this.getNearbyShips(arena);
        Collections.sort(ships, new Comparator<Ship>() {
            @Override
            public int compare(Ship s1, Ship s2) {
                return s1.getHealth() - s2.getHealth();
            }
        });
        int shotsUsed = 0;
        int targetIndex = 0;
        try {
            Ship target = ships.get(targetIndex);
            while (shotsUsed < this.getFirepower()) {
                if (target.isSunk()) {
                    targetIndex++;
                    target = ships.get(targetIndex);
                }
                Coord coord = this.getShipCoord(arena, target);
                this.fire(arena, coord.getX(), coord.getY());
                shotsUsed++;
            }
        } catch (Exception e) {
            // No more ships to shoot at
        }
    }
    
}