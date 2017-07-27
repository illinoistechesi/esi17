package esi17.vkannan3;
import battleship.core.*;
import battleship.games.*;
import battleship.ships.*;
import java.util.*;

public class GeneticTrial {
    
    private static final String INSTRUCTOR_TEAM = "Instructors";
    private static final String STUDENT_TEAM = "Students";
    private static Random RANDOM = new Random();
    
    public static void main(String[] args) {
        int simulationSeed = (int) Math.floor(Math.random() * 100);
        if (args.length >= 1) {
            simulationSeed = Integer.parseInt(args[0]);
        }
        setSeed(simulationSeed);
        System.out.println("Genetic Simulation Seed: " + simulationSeed);
        // Use Simulation Seed = 4
        // $ java esi17.vkannan3.GeneticTrial 4
        
        int[] chromosome = getRandomChromosome();
        for (int i = 0; i < 10; i++) {
            int seed = i;
            CustomBattle battle = runTrial(chromosome, seed);
            Arena arena = battle.getArena();
            double fitness = getFitness(arena);
            System.out.println(Arrays.toString(chromosome) + " -> " + fitness);
        }
        
    }
    
    public static double getFitness(Arena arena) {
        double fitness = 0;
        GeneticShip ship = getGeneticShip(arena);
        if (hasValidProperties(ship)) {
            int turns = ship.getTurnsSurvived();
            int kills = getKillsByShip(arena, ship);
            int killsKillsSum = getKillsOfKills(arena, ship);
            fitness = turns;
            fitness = kills;
            fitness = killsKillsSum;
        } else {
            fitness = 0;
        }
        return fitness;
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
    
    public static void showBattleResults(CustomBattle battle, String instructorTeam, String studentTeam) {
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