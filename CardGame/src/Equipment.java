import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Equipment {
    static List<Equipment> Equipment = new ArrayList<Equipment>(50);
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

    Villager Owner = main.emptyVillager;

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
        int rarity = random.nextInt(100);
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


        Equipment.add(new Equipment(itemName, itemRarity, itemID, itemStr, itemDex, itemStam, itemInt, itemSpir, id));
        //inDevelopment = new Equipment(itemName, itemRarity, itemID, itemStr, itemDex, itemStam, itemInt, itemSpir, id);
    }


    public static void sortByStat(String stat){
        List<Equipment> copyOfEquipment = new ArrayList<>(Equipment);
        List<Integer> sortedEquipment = new ArrayList<Integer>(Equipment.size());
//for (int i = 0; i++ < Equipment.size();){
        for (int i = 0; i++ < Equipment.size();){
            int b = 0;
            int max = 0;
            int maxidReal = 0;
            int maxidTemp = 0;
            chooseStat(stat, b, i, copyOfEquipment, sortedEquipment, max, maxidReal,maxidTemp);
        }
        printSortedEquipment(sortedEquipment, stat);

    }

    private static void chooseStat(String stat, int b, int i, List<Equipment> copyOfEquipment, List<Integer> sortedEquipment, int max, int maxidReal, int maxidTemp){
        switch(stat) {
            case "strength":
                strengthCase(b, i, copyOfEquipment, sortedEquipment, max, maxidReal,maxidTemp);
                break;

            case "dexterity":
                dexterityCase(b, i, copyOfEquipment, sortedEquipment, max, maxidReal,maxidTemp);
                break;

            case "stamina":
                staminaCase(b, i, copyOfEquipment, sortedEquipment, max, maxidReal,maxidTemp);
                break;

            case "intellect":
                intellectCase(b, i, copyOfEquipment, sortedEquipment, max, maxidReal,maxidTemp);
                break;

            case "spirit":
                spiritCase(b, i, copyOfEquipment, sortedEquipment, max, maxidReal,maxidTemp);
                break;

        }
    }

    private static void strengthCase(int b, int i, List<Equipment> copyOfEquipment, List<Integer> sortedEquipment, int max, int maxidReal, int maxidTemp){
        for (b = Equipment.size() - i; b >= 0; b--) {
            if (copyOfEquipment.get(b).str >= max) {
                max = copyOfEquipment.get(b).str;
                maxidReal = copyOfEquipment.get(b).id;
                maxidTemp = b;

            }
        }

        sortedEquipment.add(Equipment.get(maxidReal).id);
        copyOfEquipment.remove(maxidTemp);
    }

    private static void dexterityCase(int b, int i, List<Equipment> copyOfEquipment, List<Integer> sortedEquipment, int max, int maxidReal, int maxidTemp){
        for (b = Equipment.size() - i; b >= 0; b-- ) {
            if (copyOfEquipment.get(b).dex > max) {
                max = copyOfEquipment.get(b).dex;
                maxidReal = copyOfEquipment.get(b).id;
                maxidTemp = b;
            }
        }
        sortedEquipment.add(Equipment.get(maxidReal).id);
        copyOfEquipment.remove(maxidTemp);
    }

    private static void staminaCase(int b, int i, List<Equipment> copyOfEquipment, List<Integer> sortedEquipment, int max, int maxidReal, int maxidTemp){
        for (b = Equipment.size() - i; b >= 0; b--) {
            if (copyOfEquipment.get(b).stam > max) {
                max = copyOfEquipment.get(b).stam;
                maxidReal = copyOfEquipment.get(b).id;
                maxidTemp = b;
            }
        }
        sortedEquipment.add(Equipment.get(maxidReal).id);
        copyOfEquipment.remove(maxidTemp);
    }

    private static void intellectCase(int b, int i, List<Equipment> copyOfEquipment, List<Integer> sortedEquipment, int max, int maxidReal, int maxidTemp){
        for (b = Equipment.size() - i; b >= 0; b-- ) {
            if (copyOfEquipment.get(b).intt > max) {
                max = copyOfEquipment.get(b).intt;
                maxidReal = copyOfEquipment.get(b).id;
                maxidTemp = b;
            }
        }
        sortedEquipment.add(Equipment.get(maxidReal).id);
        copyOfEquipment.remove(maxidTemp);
    }

    private static void spiritCase(int b, int i, List<Equipment> copyOfEquipment, List<Integer> sortedEquipment, int max, int maxidReal, int maxidTemp){
        for (b = Equipment.size() - i; b >= 0; b-- ) {
            if (copyOfEquipment.get(b).spir > max) {
                max = copyOfEquipment.get(b).spir;
                maxidReal = copyOfEquipment.get(b).id;
                maxidTemp = b;
            }
        }
        sortedEquipment.add(Equipment.get(maxidReal).id);
        copyOfEquipment.remove(maxidTemp);
    }

    private static void printSortedEquipment(List<Integer> sortedEquipment, String stat) {
        for (int i = 0; i < Equipment.size(); i++) {
            Equipment equipment = Equipment.get(sortedEquipment.get(i));
            System.out.print(main.addPaddingToStrings(Integer.toString(equipment.id + 1) + "#:", 4));
            main.qualityColorPrintEquipmentNoNl(equipment.rarity, main.addPaddingToStrings(equipment.name, 20));
            System.out.print("   |   ");
            printEquipmentStat(stat, equipment);
            System.out.print("   |   Owner: " + equipment.Owner.name);
            if (!equipment.Owner.name.equals("None")){
                System.out.println(" #" + Integer.toString(Equipment.get(i).Owner.villagerID + 1));
            }
            System.out.println();
        }
    }

    private static void printEquipmentStat(String stat, Equipment equipment){
        switch (stat){
            case "strength":
                System.out.print("Strength:  " + main.addPaddingToStrings(Integer.toString(equipment.str), 2));
                break;
            case "dexterity":
                System.out.print("Dexterity: " + main.addPaddingToStrings(Integer.toString(equipment.dex), 2));
                break;
            case "stamina":
                System.out.print("Stamina:   " + main.addPaddingToStrings(Integer.toString(equipment.stam), 2));
                break;
            case "intellect":
                System.out.print("Intellect: " + main.addPaddingToStrings(Integer.toString(equipment.intt), 2));
                break;
            case "spirit":
                System.out.print("Spirit: " + main.addPaddingToStrings(Integer.toString(equipment.spir), 2));
                break;
        }
    }

    public static void printEquipment(){
        for (int i = 0; i < Equipment.size(); i++){
            System.out.print(main.addPaddingToStrings(Integer.toString(Equipment.get(i).id + 1) + "#: ", 5));
            main.qualityColorPrintEquipmentNoNl(Equipment.get(i).rarity, main.addPaddingToStrings(Equipment.get(i).name, 20));
            System.out.print(" | ");
            System.out.print("Strength: " + main.addPaddingToStrings(Integer.toString(Equipment.get(i).str), 2) + " | ");
            System.out.print("Dexterity: " + main.addPaddingToStrings(Integer.toString(Equipment.get(i).dex), 2) + " | ");
            System.out.print("Stamina: " + main.addPaddingToStrings(Integer.toString(Equipment.get(i).stam), 2) + " | ");
            System.out.print("Intellect: " + main.addPaddingToStrings(Integer.toString(Equipment.get(i).intt), 2) + " | ");
            System.out.print("Spirit: " + main.addPaddingToStrings(Integer.toString(Equipment.get(i).spir), 2) + " | ");
            System.out.print("Owner: " + Equipment.get(i).Owner.name);
            if (!Equipment.get(i).Owner.name.equals("None")){
                System.out.print(" #" + Integer.toString(Equipment.get(i).Owner.villagerID + 1));
            }
            System.out.println();
        }
    }

    public static void printRecipes(){
        for (int i = 0; i < equipmentType.knownRecipes.size(); i++) {
            equipmentType recipe = equipmentType.knownRecipes.get(i);
            System.out.print(main.addPaddingToStrings(Integer.toString(i + 1) + "#: ", 5));
            main.qualityColorPrintEquipmentNoNl(recipe.rarity, main.addPaddingToStrings(recipe.name, 20));
            System.out.print(" | ");
            //System.out.print("ID: " + main.addPaddingToStrings(Integer.toString(recipe.id + 1), 2));
            //System.out.print(" | ");

            if (recipe.maxstr != 0){System.out.print("Strength: " + main.addPaddingToStrings(Integer.toString(recipe.str) + " - " + Integer.toString(recipe.maxstr), 7));}
            else{System.out.print("Strength: " + main.addPaddingToStrings(Integer.toString(recipe.str), 7));}
            System.out.print(" | ");

            if (recipe.maxdex != 0){System.out.print("Dexterity: " + main.addPaddingToStrings(Integer.toString(recipe.dex) + " - " + Integer.toString(recipe.maxdex), 7));}
            else{System.out.print("Dexterity: " + main.addPaddingToStrings(Integer.toString(recipe.dex), 7));}
            System.out.print(" | ");

            if (recipe.maxstam != 0){System.out.print("Stamina: " + main.addPaddingToStrings(Integer.toString(recipe.stam) + " - " + Integer.toString(recipe.maxstam), 7));}
            else{System.out.print("Stamina: " + main.addPaddingToStrings(Integer.toString(recipe.stam), 7));}
            System.out.print(" | ");

            if (recipe.maxintt != 0){System.out.print("Intellect: " + main.addPaddingToStrings(Integer.toString(recipe.intt) + " - " + Integer.toString(recipe.maxintt), 7));}
            else{System.out.print("Intellect: " + main.addPaddingToStrings(Integer.toString(recipe.intt), 7));}
            System.out.print(" | ");

            if (recipe.maxspir != 0){System.out.print("Spirit: " + main.addPaddingToStrings(Integer.toString(recipe.spir) + " - " + Integer.toString(recipe.maxspir), 7));}
            else{System.out.print("Spirit: " + main.addPaddingToStrings(Integer.toString(recipe.spir), 7));}

            System.out.print("\nMaterials required: ");
            AtomicBoolean firstmat = new AtomicBoolean(true);
            recipe.mats.forEach(materials -> {
                if (!firstmat.get()){
                    System.out.print(", ");
                }
                firstmat.set(false);
                main.qualityColorPrintEquipmentNoNl(materials.rarity, materials.name);});
            System.out.println("\n");
        }
    }

}
