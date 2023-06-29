import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Trader {
    public static int villagerPrice = 100;
    ArrayList<Materials> storeMats;
    ArrayList<AtomicBoolean> available;
    Random random = new Random();
    AtomicBoolean isExit = new AtomicBoolean(false);

    public Trader(){
        storeMats = new ArrayList<Materials>(5);
        available = new ArrayList<AtomicBoolean>(5);
        generateMats();
        for (int i = 0; i < 5;i++)
            available.add(new AtomicBoolean(true));
    }

    private void generateMats() {
        int randomQuantity = random.nextInt(3) + 3;
        for (int i = 0; i < randomQuantity; i++){
            storeMats.add(Materials.materials.get(Materials.generateRandomMaterialID()));

        }
    }

    private static void buy() {
        if (Villager.villagerQuantity == 20) System.out.println("You are at maximum capacity for villagers");
        else if (main.workstatus.getGold() < villagerPrice){
            System.out.println("You do not have enough gold to buy a villager.");
        }
        else{
            main.workstatus.subtractGold(villagerPrice);
            generateVillager genVil = new generateVillager();
            genVil.generateVillager();
            main.statPrintNew(Villager.villagerQuantity);
            villagerPrice *= 1.2;
            System.out.println("Villagers now cost " + villagerPrice);
        }
    }

    private static int getMaterialPrice(Materials material){
        if (material.rarity == "Basic") return 50;
        if (material.rarity == "Simple") return 100;
        if (material.rarity == "Durable") return 250;
        if (material.rarity == "Advanced") return 500;
        if (material.rarity == "Grandiose") return 1000;
        return 0;
    }

    public void shop(){
        System.out.println("Welcome to my shop!");
        System.out.println("You have " + main.workstatus.getGold() + " gold.");
        System.out.println("villager: Buy a villager for " + villagerPrice + " gold.");
        for (int i = 0; i < storeMats.size(); i++){
            System.out.print(Integer.toString( i + 1) + ": Purchase ");
            main.qualityColorPrintEquipmentNoNl(storeMats.get(i).rarity,  storeMats.get(i).name);
            System.out.println(" for " + getMaterialPrice(storeMats.get(i)) + " gold.");
        }
        isExit.set(false);
        while (!isExit.get()) {
            System.out.println("Would you  like to purchase something?");
            System.out.println("Please enter a command");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String userCommand = main.myScanner.nextLine();
            processPurchase(userCommand);
        }
    }

    private void processPurchase(String userCommand) {
        switch (userCommand){
            case "villager":
                buy();
                break;
            case "exit":
                isExit.set(true);
                break;
            case "gold":
                System.out.println("You have " + main.workstatus.getGold() + " gold.");
                break;
            case "1":
                if (available.get(0).get()){
                    if (main.workstatus.getGold() >= getMaterialPrice(storeMats.get(0))){
                        main.workstatus.subtractGold(getMaterialPrice(storeMats.get(0)));
                        Materials.materials.get(storeMats.get(0).id).quantity++;
                        available.get(0).set(false);
                        System.out.println(storeMats.get(0).name + " purchased!");
                    }
                    else System.out.println("Not enough gold.");
                }
                else System.out.println("Item already purchased");
                break;
            case "2":
                if (available.get(1).get()){
                    if (main.workstatus.getGold() >= getMaterialPrice(storeMats.get(1))){
                        main.workstatus.subtractGold(getMaterialPrice(storeMats.get(1)));
                        Materials.materials.get(storeMats.get(1).id).quantity++;
                        available.get(1).set(false);
                        System.out.println(storeMats.get(1).name + " purchased!");
                    }
                    else System.out.println("Not enough gold.");
                }
                else System.out.println("Item already purchased");
                break;
            case "3":
                if (available.get(2).get()){
                    if (main.workstatus.getGold() >= getMaterialPrice(storeMats.get(2))){
                        main.workstatus.subtractGold(getMaterialPrice(storeMats.get(2)));
                        Materials.materials.get(storeMats.get(2).id).quantity++;
                        available.get(2).set(false);
                        System.out.println(storeMats.get(2).name + " purchased!");
                    }
                    else System.out.println("Not enough gold.");
                }
                else System.out.println("Item already purchased");
                break;
            case "4":
                try{
                    if (available.get(3).get()){
                        if (main.workstatus.getGold() >= getMaterialPrice(storeMats.get(3))){
                            main.workstatus.subtractGold(getMaterialPrice(storeMats.get(3)));
                            Materials.materials.get(storeMats.get(3).id).quantity++;
                            available.get(3).set(false);
                            System.out.println(storeMats.get(3).name + " purchased!");
                        }
                        else System.out.println("Not enough gold.");
                    }
                    else System.out.println("Item already purchased.");
                }
                catch (NumberFormatException nfe) {
                    System.out.println("Invalid item.");
                }
                catch (ArrayIndexOutOfBoundsException nfe) {
                    System.out.println("Invalid item.");
                }
                catch (IndexOutOfBoundsException nfe){
                    System.out.println("Invalid item.");
                }
                break;
            case "5":
                try{
                    if (available.get(4).get()){
                        if (main.workstatus.getGold() >= getMaterialPrice(storeMats.get(4))){
                            main.workstatus.subtractGold(getMaterialPrice(storeMats.get(4)));
                            Materials.materials.get(storeMats.get(4).id).quantity++;
                            available.get(4).set(false);
                            System.out.println(storeMats.get(4).name + " purchased!");
                        }
                        else System.out.println("Not enough gold.");
                    }
                    else System.out.println("Item already purchased.");
                }
                catch (NumberFormatException nfe) {
                    System.out.println("Invalid item.");
                }
                catch (ArrayIndexOutOfBoundsException nfe) {
                    System.out.println("Invalid item.");
                }
                catch (IndexOutOfBoundsException nfe){
                    System.out.println("Invalid item.");
                }
                break;
            default:
                System.out.println("Invalid input.");
        }
    }
}