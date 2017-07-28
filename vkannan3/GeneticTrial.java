package esi17.vkannan3;
import battleship.core.*;
import battleship.games.*;
import battleship.ships.*;
import java.util.*;

/*
 * $ javac esi17/vkannan3/GeneticTrial.java
 * $ java esi17.vkannan3.GeneticTrial 14
 * $ javac esi17/vkannan3/GeneticShip.java
 * $ java esi17.vkannan3.GeneticShip
 */
public class GeneticTrial {
    
    private static final String INSTRUCTOR_TEAM = "Instructors";
    private static final String STUDENT_TEAM = "Students";
    private static Random RANDOM = new Random();
    private static final int TRIALS = 5;
    private static final int POPULATION_SIZE = 10;
    private static final int GENERATIONS = 4;
    private static final double GENE_MUTATION_RATE = 0.10;
    private static final double PROPERTY_MUTATION_RATE = 0.05;
    
    public static void main(String[] args) {
        int simulationSeed = (int) Math.floor(Math.random() * 100);
        if (args.length >= 1) {
            simulationSeed = Integer.parseInt(args[0]);
        }
        setSeed(simulationSeed);
        System.out.println("Genetic Simulation Seed: " + simulationSeed);
        // Use Simulation Seed = 4
        // $ java esi17.vkannan3.GeneticTrial 4
        
        Helper.disableWrites();
        
        System.out.println("\r\rInitial Population");
        Map<int[], Double> population = new HashMap<int[], Double>();
        for (int p = 0; p < POPULATION_SIZE; p++) {
            int[] chromosome = getRandomChromosome();
            double fitness = getMeanFitness(chromosome);
            //System.out.println(p + ": " + Arrays.toString(chromosome) + " -> " + fitness);   
            population.put(chromosome, fitness);
        }
        
        for (int n = 0; n < GENERATIONS; n++) {
            System.out.println("\r\rGeneration " + n);
            population = getNextPopulation(population);
        }
        
        System.out.println("Generation " + GENERATIONS);
        for (Map.Entry<int[], Double> candidate : population.entrySet()) {
            int[] chromosome = candidate.getKey();
            double fitness = candidate.getValue();
            System.out.println(Arrays.toString(chromosome) + " -> " + fitness);   
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
            double childFitness1 = getMeanFitness(child1);
            //System.out.println("Child " + c + ": " + Arrays.toString(child) + " -> " + childFitness);
            nextPopulation.put(child1, childFitness1);
            //
            int[] child2 = crossover(parent2, fitness2, parent3, fitness3);
            child2 = mutate(child2);
            double childFitness2 = getMeanFitness(child2);
            nextPopulation.put(child2, childFitness2);
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
    
    public static double getMeanFitness(int[] chromosome) {
        double sum = 0;
        double total = 0;
        for (int i = 0; i < TRIALS; i++) {
            int seed = i;
            CustomBattle battle = runTrial(chromosome, seed);
            Arena arena = battle.getArena();
            double fitness = getFitness(arena);
            sum += fitness;
            total++;
            //System.out.println(Arrays.toString(chromosome) + " -> " + fitness);
        }
        double mean = sum / total;
        return mean;
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
        //System.out.println(result + " -> " + ratio);
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
        //
        return chromosome;
    }
    
    /*  OLD PROPERTY GENE RANDOMIZATION
        int propsLeft = 4;
        int index = 0;
        int total = 10;
        int sum = 0;
        //String res = "";
        while (propsLeft > 1) {
            int val = getRandom().nextInt(total - propsLeft);
            if (index != 2) {
                val += 1; // Prevent properties other than speed from being zero
            }
            chromosome[index] = val;
            //res += val + " + ";
            sum += val;
            total -= val;
            propsLeft--;
            index++;
        }
        chromosome[index] = total; // Always use up the full 10 points
        //res += total + " + ";
        sum += total;
        //System.out.println("\n" + res + " = " + sum + "\n");
    */
    
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
        CustomBattle battle = initBattle(seed, INSTRUCTOR_TEAM, STUDENT_TEAM);
        battle.setVerbose(false);
        battle.setCanPrint(false);
        battle.setMaxTurns(100);
        battle.setArenaFile("files/genetic-arena.txt");
        battle.setTurnFile("files/genetic-turns.txt");
        battle.setLogFile("files/genetic-log.txt");
        battle.run();
        //showBattleResults(battle, INSTRUCTOR_TEAM, STUDENT_TEAM);
        return battle;
    }
    
    public static CustomBattle initBattle(int seed, String instructorTeam, String studentTeam) {
        
        //System.out.println("Seed: " + seed);
        
        char[][] layout = {
          //  0    1    2    3    4    5    6    7    8    9   10   11   12   13   14
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, // 0
            {' ', 'X', 'Y', ' ', ' ', 'A', 'B', ' ', 'C', 'D', ' ', ' ', 'E', 'F', ' '}, // 1
            {' ', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'G', ' '}, // 2
            {' ', ' ', ' ', ' ', '#', ' ', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ', ' '}, // 3
            {' ', ' ', ' ', '#', ' ', ' ', '#', '@', '#', ' ', ' ', '#', ' ', ' ', ' '}, // 4
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, // 5
            {' ', 'V', ' ', ' ', '#', ' ', ' ', '%', ' ', ' ', '#', ' ', ' ', 'H', ' '}, // 6
            {' ', 'U', ' ', ' ', '@', ' ', '%', ' ', '%', ' ', '@', ' ', ' ', 'I', ' '}, // 7
            {' ', 'T', ' ', ' ', '#', ' ', ' ', '&', ' ', ' ', '#', ' ', ' ', 'J', ' '}, // 8
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, // 9
            {' ', ' ', ' ', '#', ' ', ' ', '#', '@', '#', ' ', ' ', '#', ' ', ' ', ' '}, // 10
            {' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' '}, // 11
            {' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'K', ' '}, // 12
            {' ', 'R', 'Q', ' ', ' ', ' ', 'P', 'O', 'N', ' ', ' ', ' ', 'M', 'L', ' '}, // 13
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, // 14
        };
        
        Map<Character, Class<? extends Ship>> shipMap = new HashMap<Character, Class<? extends Ship>>();
        Map<Character, String> teamMap = new HashMap<Character, String>();
        
        shipMap.put('&', esi17.vkannan3.GeneticShip.class);
        shipMap.put('%', battleship.ships.DummyShip.class);
        shipMap.put('@', battleship.ships.QueenShip.class);
        shipMap.put('#', battleship.ships.HiveShip.class);
        
        shipMap.put('A', esi17.Nickthegreat.MoistNoodle.class);
        shipMap.put('B', esi17.ssoto7713.SotoShip.class);
        shipMap.put('C', esi17.slee1713.MakeAmericaGreatAgain.class);
        shipMap.put('D', esi17.vsandrade99.TheBlackPearl.class);
        shipMap.put('E', esi17.kaminoshimobe.KSsinker.class);
        shipMap.put('F', esi17.AusWorley1.Mars.class);
        shipMap.put('G', esi17.jtcrane.RoboShip.class);
        shipMap.put('H', esi17.UnknownUser02.RoldanShip.class);
        shipMap.put('I', esi17.cmercado995.MercadoShip.class);
        shipMap.put('J', esi17.joshuatgonzalez.TheShip.class);
        shipMap.put('K', esi17.ageorgescu17.Maverick.class);
        shipMap.put('L', esi17.abilling.Ironsides.class);
        shipMap.put('M', esi17.npatel6219.NehaShip.class);
        shipMap.put('N', esi17.aproctor1.AlecShip.class);
        shipMap.put('O', esi17.chrisjtoohey.TooheyShip.class);
        shipMap.put('P', esi17.ririgoyen99.Immigrant.class);
        shipMap.put('Q', esi17.cguy1.Pirateship.class);
        shipMap.put('R', esi17.caesarsalad64.CuevasShip.class);
        shipMap.put('S', esi17.jbrimm.ColossusofClout.class);
        shipMap.put('T', esi17.estefaniaLopez4645.FriendShip.class);
        shipMap.put('U', esi17.jjmun.PieceofShip.class);
        shipMap.put('V', esi17.aquafreeze.AquafreezeShip.class);
        shipMap.put('W', esi17.Dolphin20.Dolphin20Ship.class);
        shipMap.put('X', esi17.Kahsel.KahselShip.class);
        shipMap.put('Y', esi17.mruiz9.guppy.class);
        
        for (Character key : shipMap.keySet()) {
            if (key.equals('@') || key.equals('#') || key.equals('%') || key.equals('&')) {
                teamMap.put(key, instructorTeam);
            } else{
                teamMap.put(key, studentTeam);
            }
        }
        
        List<Spawn> c = new ArrayList<Spawn>();
        
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[y].length; x++) {
                Character marker = layout[y][x];
                if (shipMap.containsKey(marker) && teamMap.containsKey(marker)) {
                    Class<? extends Ship> shipClass = shipMap.get(marker);
                    String team = teamMap.get(marker);
                    Spawn spawn = new Spawn(shipClass, team, x, y);
                    c.add(spawn);
                }
            }
        }
        
        CustomBattle battle = new CustomBattle(c, seed, layout.length, layout[0].length);
        
        return battle;
        
    }
    
    public static void showBattleResults(CustomBattle battle) {
        String instructorTeam = INSTRUCTOR_TEAM;
        String studentTeam = STUDENT_TEAM;
        Arena arena = battle.getArena();
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
        int sfs = scoreMap.get(studentTeam);
        int ifs = scoreMap.get(instructorTeam);
        String winner = "Draw";
        if (sfs > ifs) {
            winner = studentTeam;
        } else if (sfs < ifs) {
            winner = instructorTeam;
        }
        System.out.println("Final Battle Results");
        System.out.println(String.format("Winner: %s", winner));
        System.out.println(String.format("- %s sunk %d ships.", studentTeam, sfs));
        System.out.println(String.format("- %s sunk %d ships.", instructorTeam, ifs));
    }
    
    public static Random getRandom() {
        return GeneticTrial.RANDOM;
    }
    
    public static void setSeed(int seed) {
        getRandom().setSeed(seed);
    }
    
}