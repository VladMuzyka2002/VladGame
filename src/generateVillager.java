import java.util.Random;

public class generateVillager {
    Random random = new Random();
    public void generateVillager() {
        chooseRarity(random.nextInt(101), random.nextInt(5));
    }

    private void chooseRarity(int rarity, int profession){
        if (rarity < 50) main.party.addVillager(new Villager(random.nextInt(8) + 8, profession, 0));
        else if (rarity < 75) main.party.addVillager(new Villager(random.nextInt(8) + 15, profession, 1));
        else if (rarity < 90) main.party.addVillager(new Villager(random.nextInt(8) + 22, profession, 2));
        else if (rarity < 99) main.party.addVillager(new Villager(random.nextInt(8) + 29, profession, 3));
        else main.party.addVillager(new Villager(random.nextInt(8) + 36, profession, 4));

    }
}