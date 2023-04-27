public class turnManagement {
    static int turnCount;
    static int faith = 5;
    static int accumulatedIngenuity = 5;
    static int accumulatedCraftsmanship = 5;
    public turnManagement(){
        turnCount = 1;
    }
    public void nextTurn() {
        if (main.workstatus.getFood() >= main.workstatus.getHunger()){
            main.workstatus.addGold(main.workstatus.getGPT());
            turnCount++;
            accumulatedIngenuity += main.workstatus.getIngenuity();
            if (Equipment.inDevelopment != null) accumulatedCraftsmanship += main.workstatus.getCraftsmanship();
            faith = main.workstatus.getFaith();

            System.out.println("Turn " + turnCount + " ~~~~~ You have " + main.workstatus.getGold() + " gold!");
            if (accumulatedIngenuity >= 100){
                Equipment.discoverEquipment();
                accumulatedIngenuity -= 100;
            }

            if (accumulatedCraftsmanship >= rarityWeight(Equipment.inDevelopment.rarity)){
                Equipment.Equipment.add(Equipment.inDevelopment);
                accumulatedCraftsmanship = 0;
                Equipment.inDevelopment = null;
            }
            EventManagement.processEvent();

        }
        else System.out.println("Not enough food, change your villagers' jobs!");
    }

    private int rarityWeight(String rarity) {
        switch(rarity){
            case "Basic": return 50;
            case "Simple": return 100;
            case "Durable": return 200;
            case "Advanced": return 500;
            case "Grandiose": return 1000;
        }
        return 0;
    }
}
