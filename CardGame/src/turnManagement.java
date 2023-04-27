public class turnManagement {
    static int turnCount;
    static int faith = 5;
    public turnManagement(){
        turnCount = 1;
    }
    public void nextTurn() {
        if (main.workstatus.getFood() >= main.workstatus.getHunger()){
            main.workstatus.addGold(main.workstatus.getGPT());
            turnCount++;
            faith = main.workstatus.getFaith();
            System.out.println("Turn " + turnCount + " ~~~~~ You have " + main.workstatus.getGold() + " gold!");
            EventManagement.processEvent();

        }
        else System.out.println("Not enough food, change your villagers' jobs!");
    }
}
