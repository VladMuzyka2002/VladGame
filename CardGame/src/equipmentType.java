import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class equipmentType {

    static List<equipmentType> knownRecipes = new ArrayList<equipmentType>(34);
    static List<equipmentType> Simples = new ArrayList<equipmentType>(11);
    static List<equipmentType> Durables = new ArrayList<equipmentType>(10);
    static List<equipmentType> Advanceds = new ArrayList<equipmentType>(5);
    static List<equipmentType> Grandioses = new ArrayList<equipmentType>(3);

    String name;
    String rarity;
    int idlocal;
    int id;
    int str;
    int dex;
    int stam;
    int intt;
    int spir;
    int maxstr = 0;
    int maxdex = 0;
    int maxstam = 0;
    int maxintt = 0;
    int maxspir = 0;
    List<Materials> mats;

    public equipmentType(String name, String rarity, int idlocal, int id, int str, int dex, int stam, int intt, int spir, List<Materials> mats){
        this.name = name;
        this.rarity = rarity;
        this.id = id;
        this.idlocal = idlocal;
        this.str = str;
        this.dex = dex;
        this.stam = stam;
        this.intt = intt;
        this.spir = spir;
        this.mats = new ArrayList<>((mats));
    }

    public static void generateEquipmentTypes(){
        knownRecipes.add(new equipmentType("Club", "Basic", 0, 0, 2, 0, 0, 0, 0, Arrays.asList(new Materials[]{Materials.materials.get(0)})));
        knownRecipes.add(new equipmentType("Gloves", "Basic", 1, 1, 0, 2, 0, 0, 0, Arrays.asList(new Materials[]{Materials.materials.get(1)})));
        knownRecipes.add(new equipmentType("Shoes", "Basic", 2, 2, 0, 0, 2, 0, 0, Arrays.asList(new Materials[]{Materials.materials.get(1)})));
        knownRecipes.add(new equipmentType("Headcap", "Basic", 3, 3, 0, 0, 0, 2, 0, Arrays.asList(new Materials[]{Materials.materials.get(1)})));
        knownRecipes.add(new equipmentType("Cane", "Basic", 4, 4, 0, 0, 0, 0, 2, Arrays.asList(new Materials[]{Materials.materials.get(0)})));
        Simples.add(new equipmentType("Sword", "Simple", 0, 5, 5, 0, 0, 0, 0, Arrays.asList(new Materials[]{Materials.materials.get(0), Materials.materials.get(2)})));
        Simples.add(new equipmentType("Hoe", "Simple", 1, 6, 0, 5, 0, 0, 0, Arrays.asList(new Materials[]{Materials.materials.get(0), Materials.materials.get(2)})));
        Simples.add(new equipmentType("Shield", "Simple", 2, 7, 0, 0, 5, 0, 0, Arrays.asList(new Materials[]{Materials.materials.get(1), Materials.materials.get(2)})));
        Simples.add(new equipmentType("Book", "Simple", 3, 8, 0, 0, 0, 5, 0, Arrays.asList(new Materials[]{Materials.materials.get(1), Materials.materials.get(3)})));
        Simples.add(new equipmentType("Robes", "Simple", 4, 9, 0, 0, 0, 0, 5, Arrays.asList(new Materials[]{Materials.materials.get(1), Materials.materials.get(4)})));
        Simples.add(new equipmentType("Tunic", "Simple", 5, 10, 0, 2, 3, 0, 0, Arrays.asList(new Materials[]{Materials.materials.get(1), Materials.materials.get(4)})));
        Simples.add(new equipmentType("Scepter", "Simple", 6, 11, 3, 0, 0, 0, 2, Arrays.asList(new Materials[]{Materials.materials.get(0), Materials.materials.get(2)})));
        Simples.add(new equipmentType("Scriptures", "Simple", 7, 12, 0, 0, 0, 2, 3, Arrays.asList(new Materials[]{Materials.materials.get(1), Materials.materials.get(3)})));
        Simples.add(new equipmentType("Shovel", "Simple", 8, 13, 2, 3, 0, 0, 0, Arrays.asList(new Materials[]{Materials.materials.get(0), Materials.materials.get(2)})));
        Simples.add(new equipmentType("Headdress", "Simple", 9, 14, 0, 0, 2, 3, 0, Arrays.asList(new Materials[]{Materials.materials.get(1), Materials.materials.get(4)})));
        Simples.add(new equipmentType("Copper Necklace", "Simple", 10, 15, 1, 1, 1, 1, 1, Arrays.asList(new Materials[]{Materials.materials.get(2), Materials.materials.get(4)})));
        Durables.add(new equipmentType("Military Knife", "Durable", 0, 16, 7, 0, 5, 0, 0, Arrays.asList(new Materials[]{Materials.materials.get(0), Materials.materials.get(1), Materials.materials.get(5)})));
        Durables.add(new equipmentType("Bucket", "Durable", 1, 17, 0, 7, 0, 0, 5, Arrays.asList(new Materials[]{Materials.materials.get(2), Materials.materials.get(5)})));
        Durables.add(new equipmentType("Thick Coat", "Durable", 2, 18, 0, 0, 7, 5, 0, Arrays.asList(new Materials[]{Materials.materials.get(1), Materials.materials.get(4), Materials.materials.get(6)})));
        Durables.add(new equipmentType("Glasses", "Durable", 3, 19, 0, 5, 0, 7, 0, Arrays.asList(new Materials[]{Materials.materials.get(2), Materials.materials.get(7)})));
        Durables.add(new equipmentType("Holy Tome", "Durable", 4, 20, 5, 0, 0, 0, 7, Arrays.asList(new Materials[]{Materials.materials.get(1), Materials.materials.get(4), Materials.materials.get(6)})));
        Durables.add(new equipmentType("Telescope", "Durable", 5, 21, 0, 4, 4, 4, 0, Arrays.asList(new Materials[]{Materials.materials.get(0), Materials.materials.get(1), Materials.materials.get(7)})));
        Durables.add(new equipmentType("Heavy Blanket", "Durable", 6, 22, 0, 0, 4, 4, 4, Arrays.asList(new Materials[]{Materials.materials.get(1), Materials.materials.get(3), Materials.materials.get(6)})));
        Durables.add(new equipmentType("Multi-Purpose Knife", "Durable", 7, 23, 4, 4, 4, 0, 0, Arrays.asList(new Materials[]{Materials.materials.get(0), Materials.materials.get(1), Materials.materials.get(5)})));
        Durables.add(new equipmentType("Beast Trophy", "Durable", 8, 24, 4, 4, 4, 0, 0, Arrays.asList(new Materials[]{Materials.materials.get(1), Materials.materials.get(4), Materials.materials.get(6)})));
        Durables.add(new equipmentType("Glass Necklace", "Durable", 9, 25, 3, 3, 3, 3, 3, Arrays.asList(new Materials[]{Materials.materials.get(5), Materials.materials.get(7)})));
        Advanceds.add(new equipmentType("Shotgun", "Advanced", 0, 26, 10, 5, 7, 0, 0, Arrays.asList(new Materials[]{Materials.materials.get(0), Materials.materials.get(2), Materials.materials.get(5), Materials.materials.get(10)})));
        Advanceds.get(0).maxstr = 15;
        Advanceds.add(new equipmentType("Cart", "Advanced", 1, 27, 0, 10, 7, 0, 5, Arrays.asList(new Materials[]{Materials.materials.get(5), Materials.materials.get(7), Materials.materials.get(9)})));
        Advanceds.get(1).maxdex = 15;
        Advanceds.add(new equipmentType("Flashlight", "Advanced", 2, 28, 0, 0, 10, 5, 7, Arrays.asList(new Materials[]{Materials.materials.get(1), Materials.materials.get(2), Materials.materials.get(7), Materials.materials.get(9)})));
        Advanceds.get(2).maxstam = 15;
        Advanceds.add(new equipmentType("Mechanical Cross", "Advanced", 3, 29, 0, 0, 0, 10, 10, Arrays.asList(new Materials[]{Materials.materials.get(0), Materials.materials.get(3), Materials.materials.get(6), Materials.materials.get(8)})));
        Advanceds.get(3).maxintt = 15;
        Advanceds.get(3).maxspir = 15;
        Advanceds.add(new equipmentType("Gear Necklace", "Advanced", 4, 30, 5, 5, 5, 5, 5, Arrays.asList(new Materials[]{Materials.materials.get(8), Materials.materials.get(10)})));
        Grandioses.add(new equipmentType("Tractor", "Grandiose", 0, 31, 15, 30, 10, 5, 5, Arrays.asList(new Materials[]{Materials.materials.get(2), Materials.materials.get(4), Materials.materials.get(5), Materials.materials.get(7), Materials.materials.get(8), Materials.materials.get(9), Materials.materials.get(11)})));
        Grandioses.get(0).maxstr = 25;
        Grandioses.add(new equipmentType("Computer", "Grandiose", 1, 32, 4, 12, 4, 30, 15, Arrays.asList(new Materials[]{Materials.materials.get(2), Materials.materials.get(3), Materials.materials.get(5), Materials.materials.get(7), Materials.materials.get(8), Materials.materials.get(10), Materials.materials.get(12)})));
        Grandioses.get(1).maxspir = 25;
        Grandioses.add(new equipmentType("Lazer Gun", "Grandiose", 2, 33, 30, 0, 15, 17, 3, Arrays.asList(new Materials[]{Materials.materials.get(3), Materials.materials.get(5), Materials.materials.get(7), Materials.materials.get(8), Materials.materials.get(10), Materials.materials.get(13)})));
        Grandioses.get(2).maxstam = 25;
    }
}