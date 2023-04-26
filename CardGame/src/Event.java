import java.util.*;

public class Event {

    static List<Event> futureEvents = new ArrayList<Event>(5);
    public String eventType;
    private int brawlPower;

    public static void createEvent(){
        Random random = new Random();
        int eventRandom = random.nextInt(101);
        Event event = new Event();
        futureEvents.add(event);

        if (eventRandom < 2 && turnManagement.turnCount > 50){
            event.eventType = "Raid";
            event.brawlPower = random.nextInt(31) + 20;
        }
        else if (eventRandom < 10 && turnManagement.turnCount > 10 && turnManagement.turnCount < 30) {
            event.eventType = "Attack";
            event.brawlPower = random.nextInt(6) + 5;
        }
        else if (eventRandom < 10 && turnManagement.turnCount > 30 && turnManagement.turnCount < 50) {
            event.eventType = "Attack";
            event.brawlPower = random.nextInt(11) + 10;
        }
        else if (eventRandom < 20 && turnManagement.turnCount > 50) {
            event.eventType = "Attack";
            event.brawlPower = random.nextInt(11) + 15;
        }
        else if (eventRandom > 20 && eventRandom <= 25){
            event.eventType = "Travelling Merchant";
        }
        else event.eventType = "Peaceful day";
    }

    public void Event(String eventType){
        this.eventType = eventType;
    }

}
