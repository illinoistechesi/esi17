package esi17.vkannan3;
import esi17.vkannan3.GeneticShip;
import battleship.core.*;
import battleship.games.*;
import battleship.ships.*;
import battlehub.FinalMain;
import java.util.*;

/*
 * Genetic Lab
 * @author Vinesh Kannan
 * INSTRUCTIONS
 * $ javac esi17/vkannan3/GeneticLab.java
 * $ java esi17.vkannan3.GeneticLab
 */
public class GeneticLab {
    
    public static final String INSTRUCTOR_TEAM = "Instructors";
    public static final String STUDENT_TEAM = "Students";
    private static Random RANDOM = new Random();
    public static final int TRIALS = 5;
    public static final int POPULATION_SIZE = 10;
    public static final int GENERATIONS = 2;
    public static final double GENE_MUTATION_RATE = 0.10;
    public static final double PROPERTY_MUTATION_RATE = 0.05;
    
    private static Map<int[], Double> winRates = new HashMap<int[], Double>();
    
    public static void main(String[] args) {
        int simulationSeed = (int) Math.floor(Math.random() * 100);
        if (args.length >= 1) {
            simulationSeed = Integer.parseInt(args[0]);
        }
        setSeed(simulationSeed);
        System.out.println("Genetic Simulation Seed: " + simulationSeed);
        // Use Simulation Seed = 4
        // $ java esi17.vkannan3.GeneticLab 4
        
        Helper.disableWrites();
        
        System.out.println("\r\rInitial Population");
        Map<int[], Double> population = new HashMap<int[], Double>();
        for (int p = 0; p < POPULATION_SIZE; p++) {
            int[] chromosome = getRandomChromosome();
            List<Arena> trials = getTrials(chromosome);
            double fitness = getMeanFitness(trials);
            double winRate = getWinRate(trials);
            //System.out.println(p + ": " + Arrays.toString(chromosome) + " -> " + fitness);   
            population.put(chromosome, fitness);
            winRates.put(chromosome, winRate);
        }
        
        for (int n = 0; n < GENERATIONS; n++) {
            System.out.println("\r\rGeneration " + n);
            population = getNextPopulation(population);
        }
        
        System.out.println("Generation " + GENERATIONS);
        List<int[]> solutions = new ArrayList<int[]>();
        for (Map.Entry<int[], Double> candidate : population.entrySet()) {
            int[] chromosome = candidate.getKey();
            solutions.add(chromosome);
        }
        final Map<int[], Double> finalPopulation = population;
        Collections.sort(solutions, new Comparator<int[]>() {
            public int compare(int[] s1, int[] s2) {
                double f1 = finalPopulation.get(s1);
                double f2 = finalPopulation.get(s2);
                double diff = f1 - f2;
                int comparison = 0;
                if (diff > 0) {
                    comparison = -1;
                } else if (diff < 0) {
                    comparison = 1;
                }
                return comparison;
            } 
        });
        int counter = 0;
        for (int[] chromosome : solutions) {
            counter++;
            double fitness = finalPopulation.get(chromosome);
            double winRate = winRates.get(chromosome);
            System.out.println(counter + ": " + Arrays.toString(chromosome) + " -> " + fitness + "\t" + winRate);
        }
    }
    
    public static Map<int[], Double> getNextPopulation(Map<int[], Double> population) {
        Map<int[], Double> nextPopulation = new HashMap<int[], Double>();
        int[] parent1 = chooseParent(population, 1);
        int[] parent2 = chooseParent(population, 2);
        int[] parent3 = chooseParent(population, 3);
        int[] parent4 = chooseParent(population, 4);
        double fitness1 = population.get(parent1);
        double fitness2 = population.get(parent2);
        double fitness3 = population.get(parent3);
        double fitness4 = population.get(parent4);
        //System.out.println(Arrays.toString(parent1) + " <-> " + Arrays.toString(parent2));
        int halfSize = POPULATION_SIZE / 2;
        for (int c = 0; c < halfSize; c++) {
            int[] child1 = crossover(parent1, fitness1, parent4, fitness4);
            child1 = mutate(child1);
            List<Arena> trials1 = getTrials(child1);
            double childFitness1 = getMeanFitness(trials1);
            double winRate1 = getWinRate(trials1);
            winRates.put(child1, winRate1);
            //System.out.println("Child " + c + ": " + Arrays.toString(child) + " -> " + childFitness);
            nextPopulation.put(child1, childFitness1);
            //
            int[] child2 = crossover(parent2, fitness2, parent3, fitness3);
            child2 = mutate(child2);
            List<Arena> trials2 = getTrials(child2);
            double childFitness2 = getMeanFitness(trials2);
            double winRate2 = getWinRate(trials2);
            winRates.put(child2, winRate2);
            nextPopulation.put(child2, childFitness2);
        }
        for (Map.Entry<int[], Double> candidate : population.entrySet()) {
            int[] chromosome = candidate.getKey();
            double fitness = candidate.getValue();
            if (fitness > 0) {
                //nextPopulation.put(chromosome, fitness);   
            }
        }
        return nextPopulation;
    }
    
    public static int[] mutate(int[] child) {
        int[] mutated = new int[child.length];
        for (int g = 0; g < child.length; g++) {
            if (g >= 4) {
                double rand = getRandom().nextDouble();
                if (rand > GENE_MUTATION_RATE) {
                    int geneMax = GeneticShip.getGeneMax(g);
                    mutated[g] = getRandom().nextInt(geneMax + 1);
                } else {
                    mutated[g] = child[g];
                }
            } else {
                mutated[g] = child[g];
            }
        }
        double rand = getRandom().nextDouble();
        if (rand > PROPERTY_MUTATION_RATE) {
            int baseIndex = getRandom().nextInt(4);
            int swapIndex = getRandom().nextInt(4);
            while (baseIndex == swapIndex) {
                baseIndex = getRandom().nextInt(4);
                swapIndex = getRandom().nextInt(4);
            }
            mutated[baseIndex] = child[swapIndex];
            mutated[swapIndex] = child[baseIndex];
        }
        return mutated;
    }
    
    public static int[] crossover(int[] p1, double f1, int[] p2, double f2) {
        int[] child = new int[p1.length];
        for (int i = 0; i < p1.length; i++) {
            double random = getRandom().nextDouble();
            if (random > 0.5) {
                child[i] = p2[i];
            } else {
                child[i] = p1[i];
            }
        }
        double propsRand = getRandom().nextDouble();
        int[] propsParent = new int[p1.length];
        //if (f2 > f1) {
        if (propsRand > 0.5) {
            propsParent = p2;
        } else {
            propsParent = p1;
        }
        for (int p = 0; p < 4; p++) {
            child[p] = propsParent[p];
        }
        return child;
    }
    
    public static int[] chooseParent(Map<int[], Double> population, int num) {
        int passed = 0;
        double sum = 0;
        for (Map.Entry<int[], Double> candidate : population.entrySet()) {
            int[] chromosome = candidate.getKey();
            Double fitness = candidate.getValue();
            sum += fitness;
        }
        double partial = getRandom().nextDouble() * sum;
        while (passed < num) {
            for (Map.Entry<int[], Double> candidate : population.entrySet()) {
                int[] chromosome = candidate.getKey();
                Double fitness = candidate.getValue();
                partial += fitness;
                if (partial >= sum) {
                    passed++;
                    if (passed >= num) {
                        return chromosome;   
                    }
                }
            }   
        }
        return null;
    }
    
    public static double getMeanFitness(List<Arena> trials) {
        double sum = 0;
        double total = trials.size();
        for (Arena arena : trials) {
            double fitness = getFitness(arena);
            sum += fitness;
        }
        double mean = sum / total;
        return mean;
    }
    
    public static double getWinRate(List<Arena> trials) {
        double wins = 0;
        double total = trials.size();
        for (Arena arena : trials) {
            double ratio = getFinalScoreRatio(arena);
            if (ratio > 1) {
                wins++;
            }
        }
        double rate = wins / total;
        return rate;
    }
    
    public static List<Arena> getTrials(int[] chromosome) {
        List<Arena> trials = new ArrayList<Arena>();
        for (int i = 0; i < TRIALS; i++) {
            int seed = i;
            CustomBattle battle = runTrial(chromosome, seed);
            Arena arena = battle.getArena();
            trials.add(arena);
        }
        return trials;
    }
    
    public static double getFitness(Arena arena) {
        double fitness = 0;
        GeneticShip ship = getGeneticShip(arena);
        if (hasValidProperties(ship)) {
            /*int turns = ship.getTurnsSurvived();
            int kills = getKillsByShip(arena, ship);
            int killsKillsSum = getKillsOfKills(arena, ship);
            fitness = turns;
            fitness = kills;
            fitness = killsKillsSum;*/
            //fitness = getFinalScoreRatio(arena);
            double ratio = getFinalScoreRatio(arena);
            double kills = (double) getKillsByShip(arena, ship);
            double turns = (double) ship.getTurnsSurvived();
            fitness = kills;//ratio * kills;
        } else {
            fitness = 0;
        }
        return fitness;
    }
    
    private static int zika = 0;
    
    public static double getFinalScoreRatio(Arena arena) {
        String instructorTeam = INSTRUCTOR_TEAM;
        String studentTeam = STUDENT_TEAM;
        Map<String, Integer> scoreMap = new HashMap<String, Integer>();
        scoreMap.put(instructorTeam, 0);
        scoreMap.put(studentTeam, 0);
        for (Ship ship : arena.getAllSpawnedShips()) {
            if (ship.isSunk()) {
                if (ship.getTeam().equals(instructorTeam)) {
                    int ss = scoreMap.get(studentTeam);
                    ss++;
                    scoreMap.put(studentTeam, ss);
                } else {
                    int is = scoreMap.get(instructorTeam);
                    is++;
                    scoreMap.put(instructorTeam, is);
                }
            }
        }
        double sfs = (double) scoreMap.get(studentTeam);
        double ifs = (double) scoreMap.get(instructorTeam);
        String result = "Match is a Draw.";
        if (sfs > ifs) {
            result = "Students win.";
        } else if (sfs < ifs) {
            result = "Instructors win.";
        }
        double ratio = ifs / sfs;
        if (zika < 10) {
            //System.out.println(result + " -> " + ratio);
            zika++;
        }
        return ratio;
    }
    
    public static int getKillsOfKills(Arena arena, Ship attacker) {
        int killSum = 0;
        for(Ship victim : getVictimsOf(arena, attacker)) {
            killSum += getKillsByShip(arena, victim);
        }
        return killSum;
    }
    
    public static List<Ship> getVictimsOf(Arena arena, Ship attacker) {
        List<Ship> res = new ArrayList<Ship>();
        for (Ship ship : arena.getAllSpawnedShips()) {
            if (ship.isSunk()) {
                if (ship.getSunkBy().equals(attacker)) {
                    res.add(ship);
                }   
            }
        }
        return res;
    }
    
    public static int getKillsByShip(Arena arena, Ship attacker) {
        return getVictimsOf(arena, attacker).size();
    }
    
    public static boolean hasValidProperties(Ship ship) {
        boolean valid = true;
        if (ship.getHull() < 1) {
            valid = false;
        }
        if (ship.getFirepower() < 1) {
            valid = false;
        }
        if (ship.getRange() < 1) {
            valid = false;
        }
        int[] props = {
            ship.getHull(),
            ship.getFirepower(),
            ship.getSpeed(),
            ship.getRange()
        };
        //System.out.println(Arrays.toString(props) + (valid ? " is " : " is not ") + "valid.");
        return valid;
    }
    
    public static int[] getRandomChromosome() {
        int[] chromosome = new int[GeneticShip.getChromosomeSize()];
        for (int g = 0; g < chromosome.length; g++) {
            int geneMax = GeneticShip.getGeneMax(g);
            chromosome[g] = getRandom().nextInt(geneMax + 1);
        }
        // Set property genes
        int total = 10;
        int[] splitPoints = new int[4];
        for (int r = 0; r < splitPoints.length; r++) {
            splitPoints[r] = getRandom().nextInt(total);
        }
        splitPoints[3] = 10;
        Arrays.sort(splitPoints);
        int lastSplit = 0;
        for (int s = 0; s < splitPoints.length; s++) {
            int thisSplit = splitPoints[s];
            int val = thisSplit - lastSplit;
            lastSplit = thisSplit;
            chromosome[s] = val;
        }
        int sum = 0;
        int[] props = new int[4];
        for (int q = 0; q < 4; q++) {
            int gene = chromosome[q];
            props[q] = gene;
            sum += gene;
        }
        //System.out.println("\n" + Arrays.toString(props) + " = " + sum + "\n");
        return chromosome;
    }
    
    public static GeneticShip getGeneticShip(Arena arena) {
        for (Ship ship : arena.getAllSpawnedShips()) {
            if (ship instanceof GeneticShip) {
                return (GeneticShip) ship;
            }
        }
        return null;
    }
    
    public static CustomBattle runTrial(int[] chromosome, int seed) {
        
        GeneticShip.setChromosome(chromosome);
        
        CustomBattle battle = FinalMain.initBattle(seed, INSTRUCTOR_TEAM, STUDENT_TEAM);
        
        battle.setVerbose(false);
        battle.setCanPrint(false);
        battle.setMaxTurns(100);
        battle.setArenaFile("files/genetic-arena.txt");
        battle.setTurnFile("files/genetic-turns.txt");
        battle.setLogFile("files/genetic-log.txt");
        battle.run();
        
        //FinalMain.showBattleResults(battle, INSTRUCTOR_TEAM, STUDENT_TEAM);
        
        return battle;
    }
    
    public static Random getRandom() {
        return GeneticLab.RANDOM;
    }
    
    public static void setSeed(int seed) {
        getRandom().setSeed(seed);
    }
    
}