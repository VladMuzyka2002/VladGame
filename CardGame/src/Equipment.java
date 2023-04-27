import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Equipment {
    static List<Equipment> Equipment = new ArrayList<Equipment>(20);
    static Equipment inDevelopment = null;
    String name;
    String rarity;
    int id;
    int str;
    int dex;
    int stam;
    int intt;
    int spir;
    int recipeID;
    String Owner = "None";

    public Equipment(String name, String rarity, int id, int str, int dex, int stam, int intt, int spir, int recipeID){
        this.name = name;
        this.rarity = rarity;
        this.id = id;
        this.str = str;
        this.dex = dex;
        this.stam = stam;
        this.intt = intt;
        this.spir = spir;
        this.recipeID = recipeID;
    }

    public static void discoverEquipment(){
        Random random = new Random();
        int rarity = random.nextInt(101);
        try {
            if (rarity < 50) {
                int equipmentID = random.nextInt(11);
                equipmentType.knownRecipes.add(equipmentType.Simples.get(equipmentID));
                equipmentType.Simples.remove(equipmentID);

            } else if (rarity < 80) {
                int equipmentID = random.nextInt(10);
                equipmentType.knownRecipes.add(equipmentType.Durables.get(equipmentID));
                equipmentType.Durables.remove(equipmentID);
            } else if (rarity < 98) {
                int equipmentID = random.nextInt(5);
                equipmentType.knownRecipes.add(equipmentType.Advanceds.get(equipmentID));
                equipmentType.Advanceds.remove(equipmentID);
            } else {
                int equipmentID = random.nextInt(3);
                equipmentType.knownRecipes.add(equipmentType.Grandioses.get(equipmentID));
                equipmentType.Grandioses.remove(equipmentID);
            }
        }
        catch (IndexOutOfBoundsException nfe) {
                System.out.println("Our engineers have discovered nothing new.");
        }
    }

    public static void createEquipment(int id){
        try{
            AtomicBoolean canCraft = new AtomicBoolean(true);
            equipmentType.knownRecipes.get(id).mats.forEach(materials -> {if (materials.quantity == 0) canCraft.set(false);});

            if (canCraft.get()){
                equipmentType.knownRecipes.get(id).mats.forEach(materials -> {materials.quantity--;});
                makeItem(id);
            }else System.out.println("You do not have enough materials to start crafting this item");
        } catch (IndexOutOfBoundsException nfe){
            System.out.println("Such item does not exist.");
        }
    }

    private static void makeItem(int id) {
        Random random = new Random();
        String itemName = equipmentType.knownRecipes.get(id).name;
        String itemRarity = equipmentType.knownRecipes.get(id).rarity;
        int itemID = Equipment.size();
        int itemStr;
        int itemDex;
        int itemStam;
        int itemInt;
        int itemSpir;

        if (equipmentType.knownRecipes.get(id).maxstr != 0){ itemStr = random.nextInt(equipmentType.knownRecipes.get(id).maxstr + 1 - equipmentType.knownRecipes.get(id).str) + equipmentType.knownRecipes.get(id).str;}
        else{itemStr = equipmentType.knownRecipes.get(id).str;}

        if (equipmentType.knownRecipes.get(id).maxdex != 0){ itemDex = random.nextInt(equipmentType.knownRecipes.get(id).maxdex + 1 - equipmentType.knownRecipes.get(id).dex) + equipmentType.knownRecipes.get(id).dex;}
        else{itemDex = equipmentType.knownRecipes.get(id).dex;}

        if (equipmentType.knownRecipes.get(id).maxstam != 0){ itemStam = random.nextInt(equipmentType.knownRecipes.get(id).maxstam + 1 - equipmentType.knownRecipes.get(id).stam) + equipmentType.knownRecipes.get(id).stam;}
        else{itemStam = equipmentType.knownRecipes.get(id).stam;}

        if (equipmentType.knownRecipes.get(id).maxintt != 0){ itemInt = random.nextInt(equipmentType.knownRecipes.get(id).maxintt + 1 - equipmentType.knownRecipes.get(id).intt) + equipmentType.knownRecipes.get(id).intt;}
        else{itemInt = equipmentType.knownRecipes.get(id).intt;}

        if (equipmentType.knownRecipes.get(id).maxspir != 0){ itemSpir = random.nextInt(equipmentType.knownRecipes.get(id).maxspir + 1 - equipmentType.knownRecipes.get(id).spir) + equipmentType.knownRecipes.get(id).spir;}
        else{itemSpir = equipmentType.knownRecipes.get(id).spir;}

        inDevelopment = new Equipment(itemName, itemRarity, itemID, itemStr, itemDex, itemStam, itemInt, itemSpir, id);
    }
}
