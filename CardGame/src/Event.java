import java.util.*;

public class Event {

    static List<Event> futureEvents = new ArrayList<Event>(6);
    public String eventType;
    int brawlPower;

    public static void createEvent(){
        Random random = new Random();
        int eventRandom = random.nextInt(100);
        Event event = new Event();
        futureEvents.add(event);

        if (eventRandom < 2 && turnManagement.turnCount > 200){
            event.eventType = "Raid";
            event.brawlPower = random.nextInt(31) + 20;
        }
        else if (eventRandom < 10 && turnManagement.turnCount > 20 && turnManagement.turnCount < 50) {
            event.eventType = "Attack";
            event.brawlPower = random.nextInt(6) + 5;
        }
        else if (eventRandom < 20 && turnManagement.turnCount > 50 && turnManagement.turnCount < 100) {
            event.eventType = "Attack";
            event.brawlPower = random.nextInt(11) + 10;
        }
        else if (eventRandom < 20 && turnManagement.turnCount > 50) {
            event.eventType = "Attack";
            event.brawlPower = random.nextInt(16) + 15;
        }
        else if (eventRandom >= 20 && eventRandom < 30){
            event.eventType = "Travelling Merchant";
        }
        else event.eventType = "Peaceful day";
    }


    public Event getEvent(){return futureEvents.get(0);}
}
