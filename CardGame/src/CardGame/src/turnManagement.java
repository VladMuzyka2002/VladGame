public class turnManagement {
    static int turnCount;
    public turnManagement(){
        turnCount = 1;
    }
    public void nextTurn() {
        if (main.workstatus.getFood() >= main.workstatus.getHunger()){
            main.workstatus.addGold(main.workstatus.getGPT());
            turnCount++;
            System.out.println("Turn " + turnCount + " ~~~~~ You have " + main.workstatus.getGold() + " gold!");
        }
        else System.out.println("Not enough food, change your villagers' jobs!");
    }
}
