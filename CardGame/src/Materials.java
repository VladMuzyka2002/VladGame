import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Materials {
    public static List<Materials> materials = new ArrayList<Materials>(14);
    public int quantity;
    int id;
    String name;
    String rarity;

    public static void generateMaterials(){
        materials.add(new Materials(0, "Stick", "Basic"));
        materials.add(new Materials(1, "Leather", "Basic"));
        materials.add(new Materials(2, "Copper", "Simple"));
        materials.add(new Materials(3, "Paper", "Simple"));
        materials.add(new Materials(4, "Silk", "Simple"));
        materials.add(new Materials(5, "Steel", "Durable"));
        materials.add(new Materials(6, "Fur", "Durable"));
        materials.add(new Materials(7, "Glass", "Durable"));
        materials.add(new Materials(8, "Gears", "Advanced"));
        materials.add(new Materials(9, "Oil", "Advanced"));
        materials.add(new Materials(10, "Gunpowder", "Advanced"));
        materials.add(new Materials(11, "Titanium", "Grandiose"));
        materials.add(new Materials(12, "Microchip", "Grandiose"));
        materials.add(new Materials(13, "Uranium", "Grandiose"));
    }

    public Materials(int id, String name, String rarity){
        this.id = id;
        this.name = name;
        this.rarity = rarity;
        this.quantity = 0;
    }

    static public void printMaterials(){
        materials.forEach(materials -> {
            main.qualityColorPrintEquipmentNoNl(materials.rarity, materials.name);
            System.out.println(": " + materials.quantity);
        });
    }

    public static Materials addMaterial() {
        int materialID = generateRandomMaterialID();
        materials.get(materialID).quantity++;
        return materials.get(materialID);
    }

    public static int generateRandomMaterialID(){
        Random random = new Random();
        int materialID = 0;
        int rarity = random.nextInt(100);
        if (rarity < 50) { materialID = random.nextInt(2);}
        else if (rarity < 75) { materialID = random.nextInt(3) + 2;}
        else if (rarity < 90) { materialID = random.nextInt(3) + 5;}
        else if (rarity < 99) { materialID = random.nextInt(3) + 8;}
        else materialID = random.nextInt(3) + 11;
        return materialID;
    }
}