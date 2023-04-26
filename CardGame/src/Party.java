import java.util.ArrayList;
import java.util.List;

public class Party {

    public List<Villager> villagersList = new ArrayList<Villager>(20);

    void buyVillager(){
        if (main.workstatus.getGold() < 100){
            System.out.println("You do not have enough gold.");
        }
        else if (Villager.villagerQuantity == 20) System.out.println("You are at maximum capacity for villagers");
        else{
            main.workstatus.subtractGold(100);
            generateVillager genVil = new generateVillager();
            genVil.generateVillager();
            main.statPrint(Villager.villagerQuantity);
        }
    }

    void addVillager(Villager villager){
        Villager.villagerQuantity++;
        this.villagersList.add(villager);
        main.workstatus.raiseHunger(villager.hunger);
    }

    Villager getVillager(int id) {return main.party.villagersList.get(id - 1);}

    int partySize(){
        return main.party.villagersList.size();
    }

    void showParty(){
        if (Villager.villagerQuantity == 0) System.out.println("You don't have any villagers. Buy some!");
        else {
            for (int i = 0; i++ < Villager.villagerQuantity;) {
                System.out.print(getVillager(i).villagerID + 1 + "#: ");
                main.qualityColorPrint(getVillager(i).quality, getVillager(i).name);
            }
        }
    }

    //for debugging
    void buy20(){
        if (Villager.villagerQuantity == 0) {
            main.workstatus.addGold(1700);
            for (int i = 0; i++ < 20; buyVillager()) ;
        }
        else System.out.println("There already exist villagers.");
    }
}

