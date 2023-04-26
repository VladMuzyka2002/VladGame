public class EventManagement {
    public static void Startup() {
        for (int i = 0; i++ < 5; AddEvent());
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
                break;
            case ("Attack"):
            case ("Raid"):
                System.out.print("You are being ");
                if (event == "Attack") System.out.print("attacked! ");
                else System.out.print("raided!!! ");
                System.out.println("Defend yourself!!!");
                break;
        }
    }
}