//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class ProcessInput {

    public static void parseCommand(String unsplit_command) {
        String[] command = unsplit_command.split(" ", 0);
        switch(command[0]) {
            case "exit":
                MainDriver.isGameRunning = false;
        }
    }
}
