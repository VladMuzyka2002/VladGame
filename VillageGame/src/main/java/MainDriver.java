//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Scanner;

public class MainDriver {
    static Boolean isGameRunning;
    static Scanner myScanner;

    public MainDriver() {
    }

    public static void main(String[] args) {
        applicationPreparation();
        gameLoop();
    }

    private static void applicationPreparation() {
        isGameRunning = true;
    }

    private static void gameLoop() {
        while(isGameRunning) {
            System.out.println("Please enter a command");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
            ProcessInput.parseCommand(myScanner.nextLine());
        }

    }

    static {
        myScanner = new Scanner(System.in);
    }
}
