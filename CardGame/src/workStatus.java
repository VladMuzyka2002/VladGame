import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class workStatus {
    private int goldPerTurn;
    private int food;
    private int power;
    private int craftsmanship;
    private int faith;
    private int gold;
    private int hunger;
    private int ingenuity;

    private List<Villager> activeVillagers = new ArrayList<Villager>(20);
    private static int workingVillagers = 0;

    public workStatus(){
        goldPerTurn = 5;
        food = 5;
        power = 5;
        craftsmanship = 5;
        faith = 5;
        ingenuity = 5;
        gold = 300;
        hunger = 0;
    }

    int getGold(){
        return this.gold;
    }

    void setGold(int value) {
        this.gold = value;
        System.out.println("Successfully set gold!");
        System.out.println("You now have " + this.gold + " gold!");
    }

    void raiseHunger(int hunger){
        this.hunger += hunger;
    }

    void addGold(int value){
        this.gold+= value;
    }

    void subtractGold(int value){
        if (this.gold < value){
            System.out.println("You do not have enough gold.");
        }
        else this.gold-= value;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    int getFood(){
        return this.food;
    }

    void raiseFood(int food){
        this.food += food;
    }

    void lowerFood(int food){
        this.food -= food;
    }

    int getPower(){
        return this.power;
    }

    void setPower(int power){
        this.power = power;
    }

    int getGPT(){
        return this.goldPerTurn;
    }

    void setGPT(int gpt){
        this.goldPerTurn = gpt;
    }

    int getCraftsmanship(){
        return this.craftsmanship;
    }

    void setCraftsmanship(int craftsmanship){
        this.craftsmanship = craftsmanship;
    }

    int getFaith(){
        return this.faith;
    }

    void setFaith(int faith){
        this.faith = faith;
    }

    int getIngenuity(){
        return this.ingenuity;
    }

    void setIngenuity(int ingenuity){
        this.ingenuity = faith;
    }

    void enrollVillager(Villager villager, String job){
        removeWorker(villager);
        addWorker(villager, job);
        this.activeVillagers.add(villager);
    }

    void removeWorker(Villager villager){
        switch(villager.job.toLowerCase(Locale.ROOT)){
            case("warrior"):
                this.power -= villager.strength;
                break;
            case("farmer"):
                this.food -= villager.dexterity;
                break;
            case("miner"):
                this.goldPerTurn -= villager.stamina;
                break;
            case("craftsman"):
                this.craftsmanship -= villager.intellect;
                break;
            case("prophet"):
                this.faith -= villager.spirit;
                break;
            case("engineer"):
                this.ingenuity -= villager.intellect;
                break;
        }
    }

    void addWorker(Villager villager, String job){
        workingVillagers++;
        switch(job.toLowerCase(Locale.ROOT)){
            case("warrior"):
                this.power += villager.strength;
                break;
            case("farmer"):
                this.food += villager.dexterity;
                break;
            case("miner"):
                this.goldPerTurn += villager.stamina;
                break;
            case("craftsman"):
                this.craftsmanship += villager.intellect;
                break;
            case("prophet"):
                this.faith += villager.spirit;
                break;
            case("engineer"):
                this.ingenuity += villager.intellect;
                break;
        }
        villager.job = job.substring(0, 1).toUpperCase() + job.substring(1);;
    }

    Villager getWorker(int ID){
        return activeVillagers.get(ID);
    }

    void showWorkers(){
        if (this.workingVillagers == 0) System.out.println("You don't have any employed villagers.");
        else {
            for (int i = 0; ++i <= Villager.villagerQuantity;) {
                if (main.party.getVillager(i).job != "None"){
                    System.out.print(main.party.getVillager(i ).villagerID + 1 + "#: ");
                    main.qualityColorPrint(main.party.getVillager(i).quality, main.party.getVillager(i).name);
                }
            }
        }
    }

    public void unenroll(String[] command) {
        try{
            //enjoy reading this one
            if (command[1].equals("all")){for (int i = 0; ++i <= Villager.villagerQuantity;unenrollById(i));}
            else {unenrollById(Integer.parseInt(command[1]));}
        }
        catch (NumberFormatException nfe) {
            System.out.println("Invalid command. Use help command for command format");
        }
        catch (ArrayIndexOutOfBoundsException nfe) {
            System.out.println("Please enter a parameter. Use help command for command format");
        }
        catch(IndexOutOfBoundsException nfe){
            System.out.println("This villager does not exist");
        }
    }

    private void unenrollById(int ID) {
        removeWorker(main.party.getVillager(ID));
        main.party.getVillager(ID).job = "None";
    }

    public void sortByStat(String stat){
        List<Villager> copyOfVillagers = new ArrayList<>(main.party.villagersList);
        List<Integer> sortedVillagers = new ArrayList<Integer>(Villager.villagerQuantity);

        for (int i = 0; i++ < Villager.villagerQuantity;){
            int b = 0;
            int max = 0;
            int maxidReal = 0;
            int maxidTemp = 0;
            chooseStat(stat, b, i, copyOfVillagers, sortedVillagers, max, maxidReal,maxidTemp);
        }
        printSortedVillagers(sortedVillagers, stat);

    }

    private void chooseStat(String stat, int b, int i, List<Villager> copyOfVillagers, List<Integer> sortedVillagers, int max, int maxidReal, int maxidTemp){
        switch(stat) {
            case "strength":
                strengthCase(b, i, copyOfVillagers, sortedVillagers, max, maxidReal,maxidTemp);
                break;

            case "dexterity":
                dexterityCase(b, i, copyOfVillagers, sortedVillagers, max, maxidReal,maxidTemp);
                break;

            case "stamina":
                staminaCase(b, i, copyOfVillagers, sortedVillagers, max, maxidReal,maxidTemp);
                break;

            case "intellect":
                intellectCase(b, i, copyOfVillagers, sortedVillagers, max, maxidReal,maxidTemp);
                break;

            case "spirit":
                spiritCase(b, i, copyOfVillagers, sortedVillagers, max, maxidReal,maxidTemp);
                break;

            case "hunger":
                hungerCase(b, i, copyOfVillagers, sortedVillagers, max, maxidReal,maxidTemp);
                break;
        }
    }

    private void strengthCase(int b, int i, List<Villager> copyOfVillagers, List<Integer> sortedVillagers, int max, int maxidReal, int maxidTemp){
        for (b = -1; b++ < Villager.villagerQuantity - i; ) {
            if (copyOfVillagers.get(b).strength > max) {
                max = copyOfVillagers.get(b).strength;
                maxidReal = copyOfVillagers.get(b).villagerID;
                maxidTemp = b;
            }
        }
        sortedVillagers.add(main.party.villagersList.get(maxidReal).villagerID);
        copyOfVillagers.remove(maxidTemp);
    }

    private void dexterityCase(int b, int i, List<Villager> copyOfVillagers, List<Integer> sortedVillagers, int max, int maxidReal, int maxidTemp){
        for (b = -1; b++ < Villager.villagerQuantity - i; ) {
            if (copyOfVillagers.get(b).dexterity > max) {
                max = copyOfVillagers.get(b).dexterity;
                maxidReal = copyOfVillagers.get(b).villagerID;
                maxidTemp = b;
            }
        }
        sortedVillagers.add(main.party.villagersList.get(maxidReal).villagerID);
        copyOfVillagers.remove(maxidTemp);
    }

    private void staminaCase(int b, int i, List<Villager> copyOfVillagers, List<Integer> sortedVillagers, int max, int maxidReal, int maxidTemp){
        for (b = -1; b++ < Villager.villagerQuantity - i; ) {
            if (copyOfVillagers.get(b).stamina > max) {
                max = copyOfVillagers.get(b).stamina;
                maxidReal = copyOfVillagers.get(b).villagerID;
                maxidTemp = b;
            }
        }
        sortedVillagers.add(main.party.villagersList.get(maxidReal).villagerID);
        copyOfVillagers.remove(maxidTemp);
    }

    private void intellectCase(int b, int i, List<Villager> copyOfVillagers, List<Integer> sortedVillagers, int max, int maxidReal, int maxidTemp){
        for (b = -1; b++ < Villager.villagerQuantity - i; ) {
            if (copyOfVillagers.get(b).intellect > max) {
                max = copyOfVillagers.get(b).intellect;
                maxidReal = copyOfVillagers.get(b).villagerID;
                maxidTemp = b;
            }
        }
        sortedVillagers.add(main.party.villagersList.get(maxidReal).villagerID);
        copyOfVillagers.remove(maxidTemp);
    }

    private void spiritCase(int b, int i, List<Villager> copyOfVillagers, List<Integer> sortedVillagers, int max, int maxidReal, int maxidTemp){
        for (b = -1; b++ < Villager.villagerQuantity - i; ) {
            if (copyOfVillagers.get(b).spirit > max) {
                max = copyOfVillagers.get(b).spirit;
                maxidReal = copyOfVillagers.get(b).villagerID;
                maxidTemp = b;
            }
        }
        sortedVillagers.add(main.party.villagersList.get(maxidReal).villagerID);
        copyOfVillagers.remove(maxidTemp);
    }

    private void hungerCase(int b, int i, List<Villager> copyOfVillagers, List<Integer> sortedVillagers, int max, int maxidReal, int maxidTemp){
        for (b = -1; b++ < Villager.villagerQuantity - i; ) {
            if (copyOfVillagers.get(b).hunger > max) {
                max = copyOfVillagers.get(b).hunger;
                maxidReal = copyOfVillagers.get(b).villagerID;
                maxidTemp = b;
            }
        }
        sortedVillagers.add(main.party.villagersList.get(maxidReal).villagerID);
        copyOfVillagers.remove(maxidTemp);
    }

    private void printSortedVillagers(List<Integer> sortedVillagers, String stat) {
        for (int i = 0; i < Villager.villagerQuantity; i++) {
            Villager villager = main.party.getVillager(sortedVillagers.get(i) + 1);
            System.out.print(main.addPaddingToStrings(Integer.toString(villager.villagerID + 1) + "#:", 4));
            main.qualityColorPrintNoNl(villager.quality, main.addPaddingToStrings(villager.name, 20));
            System.out.print("   |   ");
            printVillagerStat(stat, villager);
            System.out.println("   |   Employment: " + villager.job);
        }
    }

    private void printVillagerStat(String stat, Villager villager){
        switch (stat){
            case "strength":
                System.out.print("Strength:  " + main.addPaddingToStrings(Integer.toString(villager.strength), 2));
                break;
            case "dexterity":
                System.out.print("Dexterity: " + main.addPaddingToStrings(Integer.toString(villager.dexterity), 2));
                break;
            case "stamina":
                System.out.print("Stamina:   " + main.addPaddingToStrings(Integer.toString(villager.stamina), 2));
                break;
            case "intellect":
                System.out.print("Intellect: " + main.addPaddingToStrings(Integer.toString(villager.intellect), 2));
                break;
            case "spirit":
                System.out.print("Spirit: " + main.addPaddingToStrings(Integer.toString(villager.spirit), 2));
                break;
            case "hunger":
                System.out.print("Hunger:    " + main.addPaddingToStrings(Integer.toString(villager.hunger), 2));
                break;
        }
    }
}
