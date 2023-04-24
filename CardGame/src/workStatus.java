import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class workStatus {
    private int goldPerTurn;
    private int food;
    private int power;
    private int craftsmanship;

    private List<Villager> activeVillagers = new ArrayList<Villager>(20);
    private static int workingVillagers = 0;

    public workStatus(){
        goldPerTurn = 5;
        food = 5;
        power = 5;
        craftsmanship = 5;
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
        }
        villager.job = job;
    }

    Villager getWorker(int ID){
        return activeVillagers.get(ID);
    }

    void showWorkers(){
        if (this.workingVillagers == 0) System.out.println("You don't have any employed villagers.");
        else {
            for (int i = 0; ++i <= Villager.villagerQuantity;) {
                if (main.player.getVillager(i).job != "None"){
                    System.out.print(main.player.getVillager(i ).villagerID + 1 + "#: ");
                    main.qualityColorPrint(main.player.getVillager(i).quality, main.player.getVillager(i).name);
                }
            }
        }
    }

    public void unenroll(String[] command) {
        try{
            if (command[1].equals("all")){ unenrollAll();}
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
        removeWorker(main.player.getVillager(ID));
        main.player.getVillager(ID).job = "None";
    }

    private void unenrollAll() {
        for (int i = 0; ++i <= Villager.villagerQuantity;){
            unenrollById(i);
        }
    }
}
