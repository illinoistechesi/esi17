package esi17.vkannan3;
import battleship.core.*;
import battleship.games.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintStream;

/*
 * Genetic Ship
 * @author Vinesh Kannan
 * INSTRUCTIONS
 * $ javac esi17/vkannan3/GeneticShip.java
 * $ java esi17.vkannan3.GeneticShip
 */
public class GeneticShip extends Ship {
    
    public static void main(String[] args) {
        int seed = GeneticLab.getRandom().nextInt(GeneticLab.TRIALS);
        if (args.length >= 1) {
            seed = Integer.parseInt(args[0]);
        }
        System.out.println("Battle Seed: " + seed);
        //int[] chromosome = {1, 3, 0, 6, 3, 0, 0, 1, 1};
        //int[] chromosome = {3, 3, 1, 3, 0, 0, 3, 0, 0};
        int[] chromosome = {2, 3, 1, 4, 4, 0, 2, 1, 0};
        CustomBattle battle = GeneticLab.runTrial(chromosome, seed);
        Arena arena = battle.getArena();
        GeneticShip geneticShip = GeneticLab.getGeneticShip(arena);
        System.out.println(geneticShip);
        System.out.println(geneticShip.getPhenotype());
        int kills = GeneticLab.getKillsByShip(arena, geneticShip);
        int survived = geneticShip.getTurnsSurvived();
        int turns = arena.getTurn();
        System.out.println("Sunk " + kills + " ships.");
        System.out.println("Survived " + survived + "/" + turns + " turns.");
        GeneticLab.showBattleResults(battle);
    }
    
    private static int[] CHROMOSOME = {2, 3, 1, 4, 4, 0, 2, 1, 0};
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
    
    public GeneticShip() {
        GeneticShip.out = System.out;
        this.initializeName("Genetic Ship");
        this.initializeOwner("Vinesh");
        this.initializeHull(this.getGene(HULL_GENE));
        this.initializeFirepower(this.getGene(FIREPOWER_GENE));
        this.initializeSpeed(this.getGene(SPEED_GENE));
        this.initializeRange(this.getGene(RANGE_GENE));
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        System.setOut(GeneticShip.out);
        this.turnsSurvived++;
        GeneticShip self = this;
        List<Ship> nearby = this.getNearbyEnemies(arena);
        if (nearby.size() > 0) {
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
                    int focusOn = 0;
                    for (int f = 0; f < this.getFirepower(); f++) {
                        Ship target = nearby.get(focusOn);
                        Coord coord = target.getCoord();
                        this.fire(arena, coord.getX(), coord.getY());
                        if (target.isSunk()) {
                            focusOn++;
                            if (focusOn >= nearby.size()) {
                                break;
                            }
                        }
                    }
                    break;
                case 1:  // Spread
                    int s = 0;
                    while (this.getRemainingShots() > 0) {
                        Ship target = nearby.get(s);
                        Coord loc = target.getCoord();
                        this.fire(arena, loc.getX(), loc.getY());
                        s++;
                        s = s % nearby.size();
                    }
                    break;
                default:
                    break;
            }
        } else {
            List<Ship> allShips = this.getAllEnemies(arena);
            if (allShips.size() > 0) {
                Collections.sort(allShips, new Comparator<Ship>() {
                    public int compare(Ship s1, Ship s2) {
                        int d1 = GeneticShip.getDistanceBetween(self, s1);
                        int d2 = GeneticShip.getDistanceBetween(self, s2);
                        return d1 - d2;
                    }
                });
                int moveCounter = 0;
                while (moveCounter < this.getSpeed()) {
                    Coord location = this.getCoord();
                    Coord closest = allShips.get(0).getCoord();
                    int xDiff = closest.getX() - location.getX();
                    int yDiff = closest.getY() - location.getY();
                    Direction xDir = null;
                    Direction yDir = null;
                    if (xDiff > 0) {
                        xDir = Direction.EAST;
                    } else if (xDiff < 0) {
                        xDir = Direction.WEST;
                    }
                    if (yDiff > 0) {
                        yDir = Direction.SOUTH;
                    } else if (yDiff < 0) {
                        yDir = Direction.NORTH;
                    }
                    if (xDir != null) {
                        this.move(arena, xDir);
                        moveCounter++;
                    }
                    if (yDir != null) {
                        this.move(arena, yDir);
                        moveCounter++;
                    }
                    if (xDir == null && yDir == null) {
                        break;
                    }
                }
            }
        }
    }
    
    public List<Ship> getNearbyEnemies(Arena arena) {
        List<Ship> res = new ArrayList<Ship>();
        for (Ship ship : this.getNearbyShips(arena)) {
            if (!this.isSameTeamAs(ship)) {
                res.add(ship);
            }
        }
        return res;
    }
    
    public List<Ship> getAllEnemies(Arena arena) {
        List<Ship> res = new ArrayList<Ship>();
        for (Ship ship : arena.getAllShips()) {
            if (!this.isSameTeamAs(ship)) {
                res.add(ship);
            }
        }
        return res;
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
    
    public String getPhenotype() {
        String res = "Genetic Ship";
        res += "\n- " + "Genes: " + Arrays.toString(this.getChromosome());
        res += "\n- " + "Hull: " + this.getGene(HULL_GENE);
        res += "\n- " + "Firepower: " + this.getGene(FIREPOWER_GENE);
        res += "\n- " + "Speed: " + this.getGene(SPEED_GENE);
        res += "\n- " + "Firepower: " + this.getGene(RANGE_GENE);
        String priorityA = "???";
        String priorityB = "???";
        switch (this.getGene(PRIORITY_A_GENE)) {
            case 0: // Distance
                priorityA = "distance";
                priorityB = "distance";
                break;
            case 1: // Health
                priorityA = "health";
                priorityB = "health";
                break;
            case 2: // Firepower
                priorityA = "firepower";
                priorityB = "firepower";
                break;
            case 3: // Speed
                priorityA = "speed";
                priorityB = "speed";
                break;
            case 4: // Range
                priorityA = "range";
                priorityB = "range";
                break;
            default:
                break;
        }
        String sortA = this.getGene(PRIORITY_A_SIGN_GENE) == 0 ? "decreasing" : "increasing";
        String sortB = this.getGene(PRIORITY_B_SIGN_GENE) == 0 ? "decreasing" : "increasing";
        res += "\n- " + "Sort nearby enemies by " + sortA + " " + priorityA + ",";
        res += "\n- " + "Then sort by " + sortB + " " + priorityB + ",";
        if (this.getGene(FIRE_PATTERN_GENE) == 0) {
            res += "\n- " + "Then focus shots on the first ship.";   
        } else {
            res += "\n- " + "Then distribute shots among the ships.";
        }
        return res;
    }
    
}