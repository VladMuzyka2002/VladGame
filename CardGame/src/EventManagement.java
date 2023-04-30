import java.util.Random;

public class EventManagement {
    public static void Startup() {
        for (int i = 0; i++ < 6; AddEvent());
        //Event.futureEvents.get(0).eventType = "Peaceful day";
    }

    private static void AddEvent() {Event.createEvent();}

    private static void RemoveEvent() {Event.futureEvents.remove(0);}

    public static void processEvent(){
        eventSwitch(Event.futureEvents.get(0).eventType);
        RemoveEvent();
        AddEvent();
    }

    private static void eventSwitch(String event){
        switch (event){
            case ("Peaceful day"):
                System.out.println("It's a quiet day. Nothing happened.");
                break;
            case ("Travelling Merchant"):
                System.out.println("A merchant passes by. Feel free to browse his wares!");
                main.isTraderPresent.set(true);
                turnManagement.trader = new Trader();
                break;
            case ("Attack"):
            case ("Raid"):
                System.out.print("You are being ");
                if (event == "Attack") System.out.print("attacked! ");
                else System.out.print("raided!!! ");
                System.out.println("Defend yourself!!!");
                System.out.println("Your power: " + main.workstatus.getPower());
                System.out.println("Enemy power: " + Event.futureEvents.get(0).brawlPower);
                Random random = new Random();
                if (Event.futureEvents.get(0).brawlPower > main.workstatus.getPower()){
                    int percentGoldRemoved = random.nextInt(41) + 40;
                    int goldRemoved = main.workstatus.getGold() * percentGoldRemoved / 100;
                    main.workstatus.subtractGold(goldRemoved);
                    System.out.println("You were defeated and lost " + goldRemoved+ " gold!");
                }
                else {
                    System.out.println("You have successfully repelled the attackers!");
                    int fivtyfivty = random.nextInt(2);
                    //if (fivtyfivty == 0){
                        Materials mat = Materials.addMaterial();
                        System.out.print("You have received ");
                        main.qualityColorPrintEquipment(mat.rarity, mat.name);
                    //}
                }
                break;
        }
    }
}