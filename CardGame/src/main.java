import javax.swing.plaf.BorderUIResource;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    static Boolean isGameRunning = true;
    static Scanner myScanner = new Scanner(System.in);
    static Party party = new Party();
    public static workStatus workstatus = new workStatus();
    public static turnManagement turns = new turnManagement();
    static Villager emptyVillager;
    static Equipment emptyEquipment;
    static AtomicBoolean isTraderPresent = new AtomicBoolean(true);

    static public ArrayList<String> jobs;
    static public ArrayList<String> stats;


    public static void main(String[] args){
        jobs = new ArrayList<String>();
        stats = new ArrayList<String>();
        Materials.generateMaterials();
        EventManagement.Startup();
        turnManagement.trader = new Trader();
        addJobs();
        addStats();
        equipmentType.generateEquipmentTypes();
        emptyVillager = new Villager(0, 0, 0);
        emptyEquipment = new Equipment("None", "Basic", 0,0, 0, 0, 0, 0, 0);
        emptyVillager.name = "None";

        System.out.println("Welcome.");
        System.out.println("Enter 'help' command to show all commands.");
        System.out.println("Enter 'tutorial' command for tutorial.");
        System.out.println("Talk to the trader while he's still here to get going.");
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
                System.out.println("You now have " + workstatus.getGold() + " gold");
                break;
            case "shop":
                if (isTraderPresent.get()) {
                    turnManagement.trader.shop();
                }
                else System.out.println("Trader isn't here.");
                break;
            case "buy20":
                party.buy20();
                break;
            case "stats":
                viewStats(brokenCommand);
                break;
            case "party":
                party.showParty();
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
            case "sortby":
                sortBy(brokenCommand);
                break;
            case "future":
                farsight();
                break;
            case "maxfuture":
                MaxFarsight();
                break;
            case "craft":
                craftItem(brokenCommand);
                break;
            case "development":
                development();
                break;
            case "dummy":
                dummy();
                break;
            case "dummy2":
                dummy2(brokenCommand);
                break;
            case "equipment":
            case "items":
                Equipment.printEquipment();
                break;
            case "recipes":
                Equipment.printRecipes();
                break;
            case "materials":
                Materials.printMaterials();
                break;
            case "unequip":
                remove(brokenCommand);
                break;
            case "equip":
                equip(brokenCommand);
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }

    private static void remove(String[] command) {
        try{
            Villager.equipVillager(party.getVillager(Integer.parseInt(command[1])), emptyEquipment);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid Input");
        }
        catch (ArrayIndexOutOfBoundsException nfe) {
            System.out.println("Invalid villager and/or item.");
        }
        catch (IndexOutOfBoundsException nfe){
            System.out.println("Invalid villager and/or item.");
        }
    }

    private static void equip(String[] command) {
        try{
            Villager.equipVillager(party.getVillager(Integer.parseInt(command[1])), Equipment.Equipment.get(Integer.parseInt(command[2]) - 1));
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid Input");
        }
        catch (ArrayIndexOutOfBoundsException nfe) {
            System.out.println("Invalid villager and/or item.");
        }
        catch (IndexOutOfBoundsException nfe){
            System.out.println("Invalid villager and/or item.");
        }

    }

    private static void craftItem(String[] command){
        try{
            Equipment.createEquipment(Integer.parseInt(command[1]) - 1);
            System.out.println("Creating " + Equipment.inDevelopment.name);
        }
        catch (NumberFormatException nfe) {
            System.out.println("Please enter an id of a valid recipe.");
        }
        catch (ArrayIndexOutOfBoundsException nfe) {
            System.out.println("Please enter an id of a valid recipe.");
        }
        catch (IndexOutOfBoundsException nfe){
            System.out.println("Please enter an id of a valid recipe.");
        }
        catch (NullPointerException nfe){

        }
    }

    private static void development() {
        System.out.println("Craftsmanship per Turn: " + workstatus.getCraftsmanship());
        System.out.println("Ingenuity per Turn: " + workstatus.getIngenuity());
        System.out.println("Next research progress: " + turnManagement.accumulatedIngenuity + " / 400");
        System.out.println();
        if (Equipment.inDevelopment == null) System.out.println("Nothing is being developed right now.");
        else {
            System.out.println(Equipment.inDevelopment.name + " is being developed right now.");
            System.out.println("Crafting Progress: " + turnManagement.accumulatedCraftsmanship + " / " + turnManagement.rarityWeight(Equipment.inDevelopment.rarity));
            showRecipeStatsNoMats(Equipment.inDevelopment);
        }

    }

    private static void showRecipeStats(Equipment item) {
        equipmentType recipe = equipmentType.knownRecipes.get(item.recipeID);
        System.out.println("Recipe ID: " + item.recipeID + 1);
        System.out.print("Rarity: ");
        qualityColorPrintEquipment(item.rarity, item.rarity);
        System.out.println("Item Stats: ");

        if (recipe.maxstr != 0){System.out.println("Strength: " + recipe.str + " - " + recipe.maxstr);}
        else{System.out.println("Strength: " + recipe.str);}

        if (recipe.maxdex != 0){System.out.println("Dexterity: " + recipe.dex + " - " + recipe.maxdex);}
        else{System.out.println("Dexterity: " + recipe.dex);}

        if (recipe.maxstam != 0){System.out.println("Stamina " + recipe.stam + " - " + recipe.maxstam);}
        else{System.out.println("Stamina: " + recipe.stam);}

        if (recipe.maxintt != 0){System.out.println("Intellect: " + recipe.intt + " - " + recipe.maxintt);}
        else{System.out.println("Intellect: " + recipe.intt);}

        if (recipe.maxspir != 0){System.out.println("Spirit: " + recipe.spir + " - " + recipe.maxspir);}
        else{System.out.println("Spirit: " + recipe.spir);}

        System.out.println("\nMaterials required:");
        recipe.mats.forEach(materials -> {qualityColorPrintEquipment(materials.rarity, materials.name);});
    }

    private static void showRecipeStatsNoMats(Equipment item) {
        equipmentType recipe = equipmentType.knownRecipes.get(item.recipeID);
        System.out.println("Recipe ID: " + Integer.toString(item.recipeID + 1));
        System.out.print("Rarity: ");
        qualityColorPrintEquipment(item.rarity, item.rarity);
        System.out.println("Item Stats: ");

        if (recipe.maxstr != 0){System.out.println("Strength: " + recipe.str + " - " + recipe.maxstr);}
        else{System.out.println("Strength: " + recipe.str);}

        if (recipe.maxdex != 0){System.out.println("Dexterity: " + recipe.dex + " - " + recipe.maxdex);}
        else{System.out.println("Dexterity: " + recipe.dex);}

        if (recipe.maxstam != 0){System.out.println("Stamina " + recipe.stam + " - " + recipe.maxstam);}
        else{System.out.println("Stamina: " + recipe.stam);}

        if (recipe.maxintt != 0){System.out.println("Intellect: " + recipe.intt + " - " + recipe.maxintt);}
        else{System.out.println("Intellect: " + recipe.intt);}

        if (recipe.maxspir != 0){System.out.println("Spirit: " + recipe.spir + " - " + recipe.maxspir);}
        else{System.out.println("Spirit: " + recipe.spir);}
    }

    //this is a function where I test functionality of java
    //changes frequently, but not useful to the program
    private static void dummy() {
        Equipment.discoverEquipment();

        List<Equipment> z = Equipment.Equipment;
        System.out.println(Materials.materials.get(0).quantity);
        System.out.println(equipmentType.knownRecipes);
    }

    private static void dummy2(String[] command){
        Equipment.createEquipment(Integer.parseInt(command[1]) - 1);
    }

    public static void add(String[] command){
        try {
            int gold = Integer.parseInt(command[1]);
            workstatus.addGold(gold);
            System.out.println(gold +" Gold added!");
            System.out.println("You now have " + workstatus.getGold() + " gold!");
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
            workstatus.subtractGold(gold);
            System.out.println(gold +" Gold added!");
            System.out.println("You now have " + workstatus.getGold() + " gold!");
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
            if (id > 0 && id <= party.partySize() + 1) {
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
            party.getVillager(villagerId).name = newName;
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid villager id");
        }
        catch (ArrayIndexOutOfBoundsException nfe) {
            System.out.println("Please enter an id of an existing villager.");
        }
    }

    public static void statPrint(int id){
        statPrintNew(id);
        Villager currentVillager = party.getVillager(id);
        System.out.print("Equipped item: ");
        qualityColorPrintEquipmentNoNl(currentVillager.equipment.rarity, currentVillager.equipment.name);
        System.out.println();
        if (currentVillager.equipment.name != "None"){
            System.out.println("~~~~~~~~~~~~~~~~~~");
            System.out.println("Item Stats:");
            System.out.println("Strength: " + currentVillager.equipment.str);
            System.out.println("Dexterity: " + currentVillager.equipment.dex);
            System.out.println("Stamina: " + currentVillager.equipment.stam);
            System.out.println("Intellect " + currentVillager.equipment.intt);
            System.out.println("Spirit: " + currentVillager.equipment.spir);
        }
    }

    public static void statPrintNew(int id){
        Villager currentVillager = party.getVillager(id);
        System.out.println(currentVillager.name + "'s Stats");
        System.out.println("ID: " + Integer.toString(currentVillager.villagerID + 1));
        System.out.print("Quality: ");
        qualityColorPrint(currentVillager.quality, currentVillager.quality);
        System.out.println("Job: " + currentVillager.job);
        System.out.println("Strength: " + currentVillager.strength);
        System.out.println("Dexterity: " + currentVillager.dexterity);
        System.out.println("Stamina: " + currentVillager.stamina);
        System.out.println("Intellect: " + currentVillager.intellect);
        System.out.println("Spirit: " + currentVillager.spirit);
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

    public static void qualityColorPrintEquipment(String quality, String text){
        switch (quality){
            case("Basic"): {
                System.out.println(text);
                break;
            }
            case("Simple"): {
                System.out.println(ANSI_BLUE + text + ANSI_RESET);
                break;
            }
            case("Durable"): {
                System.out.println(ANSI_RED + text + ANSI_RESET);
                break;
            }
            case("Advanced"): {
                System.out.println(ANSI_GREEN + text + ANSI_RESET);
                break;
            }
            case("Grandiose"): {
                System.out.println(ANSI_YELLOW + text + ANSI_RESET);
                break;
            }
        }
    }

    public static void qualityColorPrintEquipmentNoNl(String quality, String text){
        switch (quality){
            case("Basic"): {
                System.out.print(text);
                break;
            }
            case("Simple"): {
                System.out.print(ANSI_BLUE + text + ANSI_RESET);
                break;
            }
            case("Durable"): {
                System.out.print(ANSI_RED + text + ANSI_RESET);
                break;
            }
            case("Advanced"): {
                System.out.print(ANSI_GREEN + text + ANSI_RESET);
                break;
            }
            case("Grandiose"): {
                System.out.print(ANSI_YELLOW + text + ANSI_RESET);
                break;
            }
        }
    }

    public static void qualityColorPrintNoNl(String quality, String text){
        switch (quality){
            case("Novice"): {
                System.out.print(text);
                break;
            }
            case("Proficient"): {
                System.out.print(ANSI_BLUE + text + ANSI_RESET);
                break;
            }
            case("Remarkable"): {
                System.out.print(ANSI_RED + text + ANSI_RESET);
                break;
            }
            case("Master"): {
                System.out.print(ANSI_GREEN + text + ANSI_RESET);
                break;
            }
            case("Legendary"): {
                System.out.print(ANSI_YELLOW + text + ANSI_RESET);
                break;
            }
        }
    }

    public static void help(){
        System.out.println("Debugging Commands, don't use while doing normal play-through");
        System.out.println("add x: Adds x amount of gold to your account *DEBUGGING*");
        System.out.println("subtract x: Removes x amount of gold from your account *DEBUGGING*");
        System.out.println("buy20: Buys 20 villagers *DEBUGGING*");
        System.out.println("dummy: Completes a random research *DEBUGGING*");
        System.out.println("dummy2 x: Creates item with x id, only works when code is adjusted *DEBUGGING*");
        System.out.println("maxfuture: Sees 5 turns into the future *DEBUGGING*");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("General Commands");
        System.out.println("help: Lists available commands");
        System.out.println("tutorial: Goes through the tutorial");
        System.out.println("statshelp: Shows the meaning of stats");
        System.out.println("gold: Shows the amount of gold your account has");
        System.out.println("shop: Accesses the shop if the trader is present");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Villager Commands");
        System.out.println("stats x: Shows the stats of a villager with the ID of x");
        System.out.println("party: Shows your villager party and their ID's");
        System.out.println("sortby x: Sorts all villagers by their values in stat x");
        System.out.println("namechange x y: Changes the name of a villager with ID of x to the name y");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Job Commands");
        System.out.println("employ x y: Employs villager x with job y");
        System.out.println("employed: Shows the employed villagers");
        System.out.println("unemploy all: Unemploys all villagers");
        System.out.println("unemploy x: Unemploys villager with id x");
        System.out.println("production: Shows the active work production of your villagers, and their hunger");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Crafting Commands");
        System.out.println("development: Shows crafting and research stats, and shows which item is currently being crafted");
        System.out.println("craft x: Crafts item with recipe id x if needed materials are present");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Item Commands");
        System.out.println("equipment: Shows items that you have crafted");
        System.out.println("items: Shows items that you have crafted");
        System.out.println("recipes: Shows available recipes");
        System.out.println("materials: Shows your materials");
        System.out.println("equip x y: Equips villager id x with item id y");
        System.out.println("unequip x: Unequips villager id x off his gear");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Turn Commands");
        System.out.println("future: Reveals upcoming events depending on the amount of Faith you have");
        System.out.println("next: Enters next year, and gives you your gold income.");
        System.out.println("over: Signals that you give up, ends the game");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Shop Commands *Accessible only in shop*");
        System.out.println("villager: Buys a villager");
        System.out.println("x: Buys material with id x");
        System.out.println("gold: Shows the amount of gold your account has");
        System.out.println("exit: Exits shop");
    }

    public static void enrollVillager(String[] command){
        try{
            String lowerCaseJob = command[2].toLowerCase(Locale.ROOT);
            if (jobs.contains(lowerCaseJob)) {
                workstatus.enrollVillager(party.getVillager(Integer.parseInt(command[1])), command[2]);
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
        jobs.add("prophet");
        jobs.add("engineer");
    }

    private static void showProduction() {
        String ANSI_STATE;
        if (workstatus.getHunger() > workstatus.getFood()) ANSI_STATE = ANSI_RED;
        else ANSI_STATE = ANSI_GREEN;
        System.out.println("Power: " + workstatus.getPower());
        System.out.println("Food: " + workstatus.getFood());
        System.out.println("Income: " + workstatus.getGPT());
        System.out.println("Craftsmanship: " + workstatus.getCraftsmanship());
        System.out.println("Faith: " + workstatus.getFaith());
        System.out.println("Ingenuity: " + workstatus.getIngenuity());
        System.out.println(ANSI_STATE + "Hunger rate: " + workstatus.getHunger() + ANSI_RESET);
    }

    private static void statsHelp() {
        System.out.println("Strength: Increases combat power of your village. Good for Warriors");
        System.out.println("Dexterity: Increases farming efficiency of your village. Good for Farmers");
        System.out.println("Stamina: Increases mining efficiency of your village. Good for Miners");
        System.out.println("Intellect: Increases craftsmanship and ingenuity of your village. Good for Craftsmen and Engineers.");
        System.out.println("Spirit: Increases spirit of your village. Good for Prophets\n");
        System.out.println("Power: Total strength generated by employed warriors, used for fighting attackers");
        System.out.println("Farming rating: Total dexterity generated by employed farmers, used for feeding villagers");
        System.out.println("Income: Total stamina generated by employed miners, generates gold");
        System.out.println("Craftsmanship: Total intellect generated by employed craftsmen, accelerates crafting of items");
        System.out.println("Faith: Total spirit generated by employed prophets");
        System.out.println("Ingenuity: Total intellect generated by employed engineers, accelerates research of recipes");
    }

    private static void tutorial() {
        System.out.println("The point of the game is to make your village grow and defeat powerful enemies.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("You start with 400 gold. With it, you can buy villagers to work on your village.");
        System.out.println("To buy villagers, enter command 'shop', and then enter command 'villager'.");
        System.out.println("Villager costs starts at 100 gold, each villager costs 20% more than last.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Each villager has a hunger stat, and needs food to operate.");
        System.out.println("To move on to the next day, you need to make sure that your food production is no less");
        System.out.println("than the combined hunger of all villagers.");
        System.out.println("To get food, you need to assign villagers to be farmers.");
        System.out.println("To do so, use the 'employ' command.");
        System.out.println("For command formatting, make sure to refer to the 'help' command.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("With more time, you will need more people to work on your village.");
        System.out.println("To get more villagers, you will need to buy more with gold.");
        System.out.println("Therefore, you might want to assign villagers as miners to increase gold income.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("With time, you will get raided by foes.");
        System.out.println("You will need to assign villagers to be warriors to protect your village.");
        System.out.println("A lack of power from your warriors will result you being robbed.");
        System.out.println("Successful defence grants you a random material.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Items give your villagers additional stats.");
        System.out.println("One villager can equip only one item at a time.");
        System.out.println("One item can be equipped by only one villager at a time.");
        System.out.println("To get items, you must craft them using craftsmen.");
        System.out.println("If you have a recipe and appropriate materials, you can craft an item.");
        System.out.println("Craftsmen give craftsmanship, are more efficient with higher intellect.");
        System.out.println("To craft, use command 'craft'.");
        System.out.println("Every turn, if you are crafting an item you accumulate craftsmanship");
        System.out.println("Once you reach a proper threshold, your item is crafted.");
        System.out.println("You can access your progress using 'development' command.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("To gain more recipes, you must research them with ingenuity.");
        System.out.println("Ingenuity is earned by engineers every turn.");
        System.out.println("Engineers are more efficient with higher intellect.");
        System.out.println("Once you reach 400 ingenuity, you have a chance to unlock a new recipe.");
        System.out.println("At start, you are guaranteed to earn a new recipe, but with each unlock the odds lower.");
        System.out.println("You can access your progress using 'development' command.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("You can also see the future turns with prophets.");
        System.out.println("By assigning enough points into spirit, your prophets can yell you future events.");
        System.out.println("This is great for predicting attack waves.");
        System.out.println("At the start of every turn, you see a turn into the future for every 20 faith");
        System.out.println("Prophets generate faith for every spirit point prophets have.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Your villager's output from their job scales directly with their stats.");
        System.out.println("Each stat directly correlates to a job.");
        System.out.println("Jobs and skills are related as follows:");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        statsHelp();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Every few turns, you will be visited by a traveling wanderer.");
        System.out.println("When such happens, you can use 'shop' command to access his shop.");
        System.out.println("There, you can buy more villagers and materials.");
        System.out.println("Refer to 'help' for shop commands.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("There will be more functionality implemented. Good luck and have fun!");
    }

    public static void sortBy(String[] command){
        try{
            String stat = command[1].toLowerCase(Locale.ROOT);
            if (stats.contains(stat)) {
                if (command[2].equals("villager")) workstatus.sortByStat(stat);
                else if (command[2].equals("equipment") || command[2].equals("item")) Equipment.sortByStat(stat);
            }
            else System.out.println("Invalid input");
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input");
        }
        catch (ArrayIndexOutOfBoundsException nfe) {
            System.out.println("Please enter a valid stat and target type.");
        }
        catch (IndexOutOfBoundsException nfe){
            System.out.println("Please enter a valid stat.");
        }
    }


    public static void addStats(){
        stats.add("strength");
        stats.add("dexterity");
        stats.add("stamina");
        stats.add("intellect");
        stats.add("spirit");
        stats.add("hunger");
    }

    public static String addPaddingToStrings(String string, int padding){
        while (string.length() < padding) string += ' ';
        return string;
    }

    public static void farsight() {
        boolean canSee = true;
        int id = 0;
        int turnNum;
        while (canSee){
            turnNum = id + 1;
            if (turnManagement.faith >= turnNum * 20) {
                System.out.print("In " + turnNum + " day");
                if (turnNum > 1) System.out.print('s');
                System.out.print(": ");
                System.out.println(Event.futureEvents.get(id).eventType);
                id++;
                if (id == 5) canSee = false;
            }
            else canSee = false;
        }
    }


    public static void MaxFarsight(){
        System.out.println("In 1 day: " + Event.futureEvents.get(0).eventType);
        System.out.println("In 2 days: " + Event.futureEvents.get(1).eventType);
        System.out.println("In 3 days: " + Event.futureEvents.get(2).eventType);
        System.out.println("In 4 days: " + Event.futureEvents.get(3).eventType);
        System.out.println("In 5 days: " + Event.futureEvents.get(4).eventType);
    }
}
