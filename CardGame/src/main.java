import java.util.*;

public class main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    static Boolean isGameRunning = true;
    static Scanner myScanner = new Scanner(System.in);
    static Player player = new Player();
    public static workStatus workstatus = new workStatus();
    public static turnManagement turns = new turnManagement();

    static public ArrayList<String> jobs;

    public static void main(String[] args){
        jobs = new ArrayList<String>();
        addJobs();
        System.out.println("Type 'help' for all commands");
        while(isGameRunning){
            gameLoop();
        }
    }

    public static void gameLoop(){
        System.out.println("Please enter a command");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        String userCommand = myScanner.nextLine();
        processCommand(userCommand);
    }

    public static void processCommand(String userCommand){
        String[] brokenCommand = userCommand.split(" ", 0);
        switch(brokenCommand[0]) {
            case "add":
                add(brokenCommand);
                break;
            case "subtract":
                subtract(brokenCommand);
                break;
            case "gold":
                System.out.println("You now have " + player.getGold() + " gold");
                break;
            case "buy":
                player.buyVillager();
                break;
            case "stats":
                viewStats(brokenCommand);
                break;
            case "party":
                player.showParty();
                break;
            case "namechange":
                nameChange(brokenCommand);
                break;
            case "employ":
                enrollVillager(brokenCommand);
                break;
            case "help":
                help();
                break;
            case "employed":
                workstatus.showWorkers();
                break;
            case "production":
                showProduction();
                break;
            case "statshelp":
                statsHelp();
                break;
            case "next":
                turns.nextTurn();
                break;
            case "over":
                System.out.println("Game over!");
                System.out.println("Days lasted: " + turnManagement.turnCount);
                isGameRunning = false;
                break;
            case "unemploy":
                workstatus.unenroll(brokenCommand);
                break;
            case "tutorial":
                tutorial();
                break;
            case "albania":
                System.out.println("So what about them uhh, recordings?");
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }

    public static void add(String[] command){
        try {
            int gold = Integer.parseInt(command[1]);
            player.addGold(gold);
            System.out.println(gold +" Gold added!");
            System.out.println("You now have " + player.getGold() + " gold!");
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid gold amount");
        }
        catch (ArrayIndexOutOfBoundsException nfe) {
            System.out.println("Please enter an amount of gold");
        }
    }

    public static void subtract(String[] command){
        try {
            int gold = Integer.parseInt(command[1]);
            player.subtractGold(gold);
            System.out.println(gold +" Gold added!");
            System.out.println("You now have " + player.getGold() + " gold!");
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid gold amount");
        }
        catch (ArrayIndexOutOfBoundsException nfe) {
            System.out.println("Please enter an amount of gold");
        }
    }


    public static void viewStats(String[] command){
        try{
            int id = Integer.parseInt(command[1]);
            if (id > 0 && id <= player.partySize() + 1) {
                statPrint(id);
            }
            else System.out.println("Invalid villager id.");
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid villager id");
        }
        catch (ArrayIndexOutOfBoundsException nfe) {
            System.out.println("Please enter an id of an existing villager.");
        }
        catch (IndexOutOfBoundsException nfe){
            System.out.println("Please enter an id of an existing villager.");
        }
    }
    public static void nameChange(String[] command){
        try{
            int villagerId = Integer.parseInt(command[1]);
            String newName = command[2];
            player.getVillager(villagerId).name = newName;
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid villager id");
        }
        catch (ArrayIndexOutOfBoundsException nfe) {
            System.out.println("Please enter an id of an existing villager.");
        }
    }
    public static void statPrint(int id){
        Villager currentVillager = player.getVillager(id);
        System.out.println(currentVillager.name + "'s Stats");
        System.out.print("Quality: ");
        qualityColorPrint(currentVillager.quality, currentVillager.quality);
        System.out.println("Job: " + currentVillager.job);
        System.out.println("Strength: " + currentVillager.strength);
        System.out.println("Dexterity: " + currentVillager.dexterity);
        System.out.println("Stamina: " + currentVillager.stamina);
        System.out.println("Intellect: " + currentVillager.intellect);
        System.out.println("Hunger Rate: " + currentVillager.hunger);
    }

    public static void qualityColorPrint(String quality, String text){
        switch (quality){
            case("Novice"): {
                System.out.println(text);
                break;
            }
            case("Proficient"): {
                System.out.println(ANSI_BLUE + text + ANSI_RESET);
                break;
            }
            case("Remarkable"): {
                System.out.println(ANSI_RED + text + ANSI_RESET);
                break;
            }
            case("Master"): {
                System.out.println(ANSI_GREEN + text + ANSI_RESET);
                break;
            }
            case("Legendary"): {
                System.out.println(ANSI_YELLOW + text + ANSI_RESET);
                break;
            }
        }
    }
    public static void help(){
        System.out.println("tutorial: Goes through the tutorial");
        System.out.println("add x: Adds x amount of gold to your account *DEBUGGING*");
        System.out.println("subtract x: Removes x amount of gold from your account *DEBUGGING*");
        System.out.println("gold: Shows the amount of gold your account has");
        System.out.println("buy: Buys a new villager for 100 gold");
        System.out.println("stats x: Shows the stats of a villager with the ID of x");
        System.out.println("party: Shows your villager party and their ID's");
        System.out.println("employ x y: Employs villager x with job y");
        System.out.println("employed: Shows the employed villagers");
        System.out.println("unemploy all: Unemploys all villagers");
        System.out.println("unemploy x: Unemploys villager with id x");
        System.out.println("namechange x y: Changes the name of a villager with ID of x to the name y");
        System.out.println("production: Shows the active work production of your villagers, and their hunger");
        System.out.println("statshelp: Shows the meaning of stats");
        System.out.println("next: Enters next year, and gives you your gold income.");
        System.out.println("over: Signals that you give up, ends the game");
    }

    public static void enrollVillager(String[] command){
        try{
            String lowerCaseJob = command[2].toLowerCase(Locale.ROOT);
            if (jobs.contains(lowerCaseJob)) {
                workstatus.enrollVillager(player.getVillager(Integer.parseInt(command[1])), command[2]);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input");
        }
        catch (ArrayIndexOutOfBoundsException nfe) {
            System.out.println("Please enter an id of an existing villager.");
        }
        catch (IndexOutOfBoundsException nfe){
            System.out.println("Please enter an id of an existing villager.");
        }
    }

    public static void addJobs(){
        jobs.add("warrior");
        jobs.add("farmer");
        jobs.add("miner");
        jobs.add("craftsman");
    }

    private static void showProduction() {
        String ANSI_STATE;
        if (player.getHunger() > workstatus.getFood()) ANSI_STATE = ANSI_RED;
        else ANSI_STATE = ANSI_GREEN;
        System.out.println("Power: " + workstatus.getPower());
        System.out.println("Food: " + workstatus.getFood());
        System.out.println("Income: " + workstatus.getGPT());
        System.out.println("Craftsmanship: " + workstatus.getCraftsmanship());
        System.out.println(ANSI_STATE + "Hunger rate: " + player.getHunger() + ANSI_RESET);
    }

    private static void statsHelp() {
        System.out.println("Strength: Increases combat power of your village. Good for Warriors");
        System.out.println("Dexterity: Increases farming efficiency of your village. Good for Farmers");
        System.out.println("Stamina: Increases mining efficiency of your village. Good for Miners");
        System.out.println("Intellect: Increases crafting efficiency of your village. Good for Craftsmen");
    }

    private static void tutorial() {
        System.out.println("The point of the game is to last for as many years as you can without collapse.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("You start with 300 gold. With it, you can buy villagers to work on your village.");
        System.out.println("Each villager costs 100 gold, you can buy them with the command 'buy'.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Each villager has a hunger stat, and needs food to operate.");
        System.out.println("To move on to the next year, you need to make sure that your food production");
        System.out.println("is no less than the combined hunger of all villagers.");
        System.out.println("To get food, you need to assign villagers to be farmers.");
        System.out.println("To do so, use the 'employ' command.");
        System.out.println("For command formatting, make sure to refer to the 'help' command.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("With more time, you will need more people to work on your village.");
        System.out.println("To get more villagers, you will need to buy more with gold.");
        System.out.println("Therefore, you might want to assign villagers as miners to increase gold income.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("DISCLAIMER!!!!! Warrior and Craftsmen jobs are not yet implemented");
        System.out.println("DISCLAIMER!!!!! Mechanics related to these jobs arent implemented either.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("With time, you will get raided by foes.");
        System.out.println("You will need to assign villagers to be warriors to protect your village.");
        System.out.println("A lack of power from your warriors will result in villagers being killed.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("If you have expendable workforce, you can employ your villagers as craftsmen.");
        System.out.println("They will be able to create equipment for your villagers to enhance their stats.");
        System.out.println("You can have one equipment equipped on each villager");
        System.out.println("Additionally, you can swap equipment on and off freely between villagers");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Your villager's output from their job scales directly with their stats.");
        System.out.println("Each stat directly correlates to a job.");
        System.out.println("Jobs and skills are related as follows:");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        statsHelp();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("There will be more functionality implemented. Good luck and have fun!");
    }
}
