package service;

import java.util.Scanner;

public class UserInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputUserMove(String msg, int arraySize) {
        System.out.print(msg);
        int userMove = scanner.nextInt();
        userMove = checkUserInput(userMove, arraySize);
        return userMove;
    }

    private static int checkUserInput(int userMove, int arraySize) {
        boolean condition = true;
        while (condition) {
            if (userMove < 0 || userMove > arraySize) {
                System.out.println("\nIncorrect input. Please, repeat");
                userMove = scanner.nextInt();
            } else {
                condition = false;
            }
        }
        return userMove;
    }
}
