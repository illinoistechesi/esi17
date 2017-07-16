package esi17.cs;
import battleship.core.*;
import java.util.*;

class EvilFleet {
    
    static String EVIL_FILE = "files/evil.txt";
    static List<EvilFleetShip> FLEET = new ArrayList<EvilFleetShip>();
    
    protected EvilFleet() {
        
    }
    
    protected static void writeEvilLine(String content) {
        Helper.writeFileLine(EVIL_FILE, content);
    }
    
    protected static List<Ship> sortShipsByDistanceFrom(EvilFleetShip self, List<Ship> ships, Arena arena) {
        Collections.sort(ships, new Comparator<Ship>() {
            @Override
            public int compare(Ship s1, Ship s2) {
                double d1 = getDistance(self, arena, self, s1);
                double d2 = getDistance(self, arena, self, s2);
                double diff = d2 - d1;
                return (int) diff;
            }
        });
        return ships;
    }
    
    protected static double getDistance(EvilFleetShip self, Arena arena, Ship s1, Ship s2) {
        Coord c1 = self.getShipCoordEvilly(arena, s1);
        Coord c2 = self.getShipCoordEvilly(arena, s2);
        if (c1 != null && c2 != null) {
            int x1 = c1.getX();
            int y1 = c1.getY();
            int x2 = c2.getX();
            int y2 = c2.getY();
            double xDiff = (double) (x2 - x1);
            double yDiff = (double) (y2 - y1);
            return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }
    
    static List<EvilFleetShip> getFleet() {
        return FLEET;
    }
    
    static void joinFleet(EvilFleetShip ship) {
        FLEET.add(ship);
    }
    
}