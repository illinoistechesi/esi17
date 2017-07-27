package esi17.vkannan3;
import battleship.core.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.PrintStream;

/*
 * Vinesh
 * @author Your Name
 */
public class GeneticShip extends Ship {
    
    private static int[] CHROMOSOME;
    private static final int[] GENE_MAX = {9, 9, 9, 9, 4, 1, 4, 1, 1};
    private static final int HULL_GENE = 0;
    private static final int FIREPOWER_GENE = 1;
    private static final int SPEED_GENE = 2;
    private static final int RANGE_GENE = 3;
    private static final int PRIORITY_A_GENE = 4;
    private static final int PRIORITY_A_SIGN_GENE = 5;
    private static final int PRIORITY_B_GENE = 6;
    private static final int PRIORITY_B_SIGN_GENE = 7;
    private static final int FIRE_PATTERN_GENE = 8;
    
    private int turnsSurvived = 0;
    
    private static PrintStream out;
    
    /*public GeneticShip() {
        this.initializeName("Genetic Ship");
        this.initializeOwner("Vinesh");
        this.initializeHull(1);
        this.initializeFirepower(1);
        this.initializeSpeed(1);
        this.initializeRange(1);
    }*/
    
    public GeneticShip() {
        GeneticShip.out = System.out;
        this.initializeName("Genetic Ship");
        this.initializeOwner("Vinesh");
        this.initializeHull(this.getGene(HULL_GENE));
        this.initializeFirepower(this.getGene(FIREPOWER_GENE));
        this.initializeSpeed(this.getGene(SPEED_GENE));
        this.initializeRange(this.getGene(RANGE_GENE));
    }
    
    /*public GeneticShip(int[] chromosome) {
        this.setChromosome(chromosome);
        this.initializeName("Genetic Ship");
        this.initializeOwner("Vinesh");
        this.initializeHull(this.getGene(HULL_GENE));
        this.initializeFirepower(this.getGene(FIREPOWER_GENE));
        this.initializeSpeed(this.getGene(SPEED_GENE));
        this.initializeRange(this.getGene(RANGE_GENE));
    }*/
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        System.setOut(GeneticShip.out);
        this.turnsSurvived++;
        List<Ship> nearby = this.getNearbyShips(arena);
        GeneticShip self = this;
        Collections.sort(nearby, new Comparator<Ship>() {
            public int compare(Ship s1, Ship s2) {
                int comparison = self.getComparison(s1, s2, PRIORITY_A_GENE, PRIORITY_A_SIGN_GENE);
                if (comparison == 0) {
                    comparison = self.getComparison(s1, s2, PRIORITY_B_GENE, PRIORITY_B_SIGN_GENE);
                }
                return comparison;
            } 
        });
        switch (this.getGene(FIRE_PATTERN_GENE)) {
            case 0: // Eliminate
                Ship first = nearby.get(0);
                Coord coord = first.getCoord();
                for (int f = 0; f < this.getFirepower(); f++) {
                    this.fire(arena, coord.getX(), coord.getY());
                }
                break;
            case 1:  // Spread
                for (int s = 0; s < this.getFirepower(); s++) {
                    if (s < nearby.size()) {
                        Ship target = nearby.get(s);
                        Coord loc = target.getCoord();
                        this.fire(arena, loc.getX(), loc.getY());   
                    } else {
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }
    
    public int getComparison(Ship s1, Ship s2, int propertyIndex, int signIndex) {
        int a1 = 0;
        int a2 = 0;
        switch (this.getGene(propertyIndex)) {
            case 0: // Distance
                a1 = GeneticShip.getDistanceBetween(this, s1);
                a2 = GeneticShip.getDistanceBetween(this, s2);
                break;
            case 1: // Health
                a1 = s1.getHealth();
                a2 = s2.getHealth();
                break;
            case 2: // Firepower
                a1 = s1.getFirepower();
                a2 = s2.getFirepower();
                break;
            case 3: // Speed
                a1 = s1.getSpeed();
                a2 = s2.getSpeed();
                break;
            case 4: // Range
                a1 = s1.getRange();
                a2 = s2.getRange();
                break;
            default:
                break;
        }
        int sign = 1;
        switch (this.getGene(signIndex)) {
            case 0: // Negative
                sign = -1;
                break;
            case 1: // Positive
                sign = 1;
                break;
            default:
                break;
        }
        int comparison = sign * (a1 - a2);
        return comparison;
    }
    
    public static int getDistanceBetween(Ship a, Ship b) {
        Coord start = a.getCoord();
        Coord end = b.getCoord();
        int xDiff = Math.abs(end.getX() - start.getX());
        int yDiff = Math.abs(end.getY() - start.getY());
        return xDiff + yDiff - 1;
    }
    
    public int getTurnsSurvived() {
        return this.turnsSurvived;
    }
    
    public static int getChromosomeSize() {
        return GeneticShip.GENE_MAX.length;
    }
    
    public static int getGeneMax(int index) {
        return GeneticShip.GENE_MAX[index];
    }
    
    public static void setChromosome(int[] chromosome) {
        GeneticShip.CHROMOSOME = chromosome;
    }
    
    private static int[] getChromosome() {
        return GeneticShip.CHROMOSOME;
    }
    
    private int getGene(int index) {
        return GeneticShip.getChromosome()[index];
    }
    
}