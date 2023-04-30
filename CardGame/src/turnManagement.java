public class turnManagement {
    static int turnCount;
    static int faith = 5;
    static int accumulatedIngenuity = 0;
    static int accumulatedCraftsmanship = 0;
    static Trader trader;
    public turnManagement(){
        turnCount = 1;
    }
    public void nextTurn() {
        if (main.workstatus.getFood() >= main.workstatus.getHunger()){
            main.workstatus.addGold(main.workstatus.getGPT());
            turnCount++;
            accumulatedIngenuity += main.workstatus.getIngenuity();
            main.isTraderPresent.set(false);
            System.out.println("Turn " + turnCount + " ~~~~~ You have " + main.workstatus.getGold() + " gold!");

            if (Equipment.inDevelopment != null) {
                accumulatedCraftsmanship += main.workstatus.getCraftsmanship();

                if (accumulatedCraftsmanship >= rarityWeight(Equipment.inDevelopment.rarity)){
                    Equipment.Equipment.add(Equipment.inDevelopment);
                    accumulatedCraftsmanship = 0;
                    Equipment.inDevelopment = null;
                    System.out.println("Your craftsmen have crafted " + Equipment.Equipment.get(Equipment.Equipment.size() - 1).name);
                }
            }
            if (accumulatedIngenuity >= 400){
                Equipment.discoverEquipment();
                accumulatedIngenuity -= 400;
                System.out.println("Your engineers have researched " + equipmentType.knownRecipes.get(equipmentType.knownRecipes.size() - 1).name);
            }
            faith = main.workstatus.getFaith();

            main.farsight();

            EventManagement.processEvent();

        }
        else System.out.println("Not enough food, change your villagers' jobs!");
    }

    static public int rarityWeight(String rarity) {
        switch(rarity){
            case "Basic": return 100;
            case "Simple": return 200;
            case "Durable": return 500;
            case "Advanced": return 1000;
            case "Grandiose": return 2000;
        }
        return 0;
    }
}
