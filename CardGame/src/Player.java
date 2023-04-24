import java.util.ArrayList;
import java.util.List;

public class Player {
    private int gold;
    private int hunger;
    private static List<Villager> villagersList = new ArrayList<Villager>(20);

    public Player(){
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

    void buyVillager(){
        if (this.gold < 100){
            System.out.println("You do not have enough gold.");
        }
        else {
            this.gold-= 100;
            generateVillager genVil = new generateVillager();
            genVil.generateVillager();
            main.statPrint(Villager.villagerQuantity);
        }
    }

    void addVillager(Villager villager){
        Villager.villagerQuantity++;
        this.villagersList.add(villager);
        this.raiseHunger(villager.hunger);
    }

    int partySize(){
        return main.player.villagersList.size();
    }

    Villager getVillager(int id) {return main.player.villagersList.get(id - 1);}

    void showParty(){
        if (Villager.villagerQuantity == 0) System.out.println("You don't have any villagers. Buy some!");
        else {
            for (int i = 0; ++i <= Villager.villagerQuantity;) {
                System.out.print(getVillager(i).villagerID + 1 + "#: ");
                main.qualityColorPrint(getVillager(i).quality, getVillager(i).name);
            }
        }
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
}
