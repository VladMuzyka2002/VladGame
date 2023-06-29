import java.util.Random;

public class Villager {
    static int villagerQuantity;
    int villagerID;

    int strength = 1;
    int dexterity = 1;
    int stamina = 1;
    int intellect = 1;
    int spirit = 1;
    Equipment equipment;
    final int PROFESSION_COEFFICIENT = 2;

    String job = "None";

    String name;
    String quality;
    int hunger;
    int profession;

    Random random = new Random();

    public Villager(int statTotal, int profession, int rarity){
        equipment = main.emptyEquipment;
        villagerID = villagerQuantity;
        this.profession = profession;
        hunger = generateHunger();
        quality = getQualityString(rarity);
        distributeStats(statTotal);
    }

    public int generateHunger(){
        return random.nextInt(3) + 1;
    }

    public String getQualityString(int rarity){
        switch (rarity) {
            case (0): return ("Novice");
            case (1): return ("Proficient");
            case (2): return ("Remarkable");
            case (3): return ("Master");
            case (4): return ("Legendary");
        }
        return "Error :(";
    }

    private void distributeStats(int stats) {
        for (int i = stats; i > 0; i--){
            switch (random.nextInt(5)){
                case (0):
                    this.strength++;
                    break;
                case (1):
                    this.dexterity++;
                    break;
                case (2):
                    this.stamina++;
                    break;
                case (3):
                    this.intellect++;
                    break;
                case (4):
                    this.spirit++;break;
            }
        }
        chooseMultiplier();
    }

    private void chooseMultiplier(){
        switch(profession){
            case (0):
                this.strength *= PROFESSION_COEFFICIENT;
                this.name = quality + " Warrior";
                break;
            case (1):
                this.dexterity *= PROFESSION_COEFFICIENT;
                this.name = quality + " Farmer";
                break;
            case (2):
                this.stamina *= PROFESSION_COEFFICIENT;
                this.name = quality + " Miner";
                break;
            case (3):
                this.intellect *= PROFESSION_COEFFICIENT;
                if (random.nextInt(2) == 0) this.name = quality + " Engineer";
                else this.name = quality + " Craftsman";
                break;
            case (4):
                this.spirit *= PROFESSION_COEFFICIENT;
                this.name = quality + " Prophet";
                break;
        }
    }

    public int getID(){
        return villagerID;
    }

    static public void equipVillager(Villager villager, Equipment item){
        Equipment oldItem = villager.equipment;
        Equipment newItem = item;

        if (item.Owner.name != "None"){
            main.emptyEquipment.Owner = new Villager(0, 0, 0);
            main.emptyEquipment.Owner.name = "None";
            equipVillager(main.party.villagersList.get(item.Owner.villagerID), main.emptyEquipment);
            villager.equipment = newItem;
            item.Owner = villager;

            swapItemType(villager, main.emptyEquipment, newItem, villager.job);
        }
        else {
            villager.equipment = newItem;
            item.Owner = villager;

            swapItemType(villager, oldItem, newItem, villager.job);
        }


    }

    private static void swapItemType(Villager villager, Equipment oldItem, Equipment newItem, String job) {
        switch (job){
            case "Warrior":
                main.workstatus.setPower(main.workstatus.getPower() - oldItem.str + newItem.str);
                break;
            case "Farmer":
                main.workstatus.setFood(main.workstatus.getFood() - oldItem.dex + newItem.dex);
                break;
            case "Miner":
                main.workstatus.setGPT(main.workstatus.getGPT() - oldItem.stam + newItem.stam);
                break;
            case "Prophet":
                main.workstatus.setFaith(main.workstatus.getFaith() - oldItem.spir + newItem.spir);
                break;
            case "Craftsman":
                main.workstatus.setCraftsmanship(main.workstatus.getCraftsmanship() - oldItem.intt + newItem.intt);
                break;
            case "Engineer":
                main.workstatus.setIngenuity(main.workstatus.getIngenuity() - oldItem.intt + newItem.intt);
                break;
        }
    }
}