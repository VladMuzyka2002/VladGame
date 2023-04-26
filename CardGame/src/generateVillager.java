import java.util.Random;

public class generateVillager {
    Random random = new Random();
    public void generateVillager() {
        chooseRarity(random.nextInt(101), random.nextInt(3));
    }

    private void chooseRarity(int rarity, int profession){
        if (rarity < 50) main.party.addVillager(new Villager(random.nextInt(6) + 6, profession, 0));
        else if (rarity < 75) main.party.addVillager(new Villager(random.nextInt(6) + 11, profession, 1));
        else if (rarity < 90) main.party.addVillager(new Villager(random.nextInt(6) + 16, profession, 2));
        else if (rarity < 99) main.party.addVillager(new Villager(random.nextInt(6) + 21, profession, 3));
        else main.party.addVillager(new Villager(random.nextInt(10) + 30, profession, 4));

    }
}
