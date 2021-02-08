package controller;

import model.Game;
import service.Checks;
import service.UserInput;
import view.Printer;

public class Main {
    public static void main(String[] args) {
        String hashSecretKey = Game.getSha256(Game.createSecretKey());
        boolean condition = true;
        while (condition) {
            if (Checks.checkInputParameters(args)) {
                int computerIndexChoice = Game.generateComputerMove(args);
                String computerMove = args[computerIndexChoice];
                Printer.print("HMAC:\n" + Game.creatHMAC(hashSecretKey, computerMove) + "\nAvailable moves:");
                for (int i = 0; i < args.length; i++) {
                    Printer.print(i + 1 + " - " + args[i]);
                }
                Printer.print("0 - exit");
                int userMove = UserInput.inputUserMove("Enter your move: ", args.length);
                if (userMove == 0) {
                    break;
                }
                Printer.print("Your move: " + args[userMove - 1] + "\nComputer move: " + computerMove);
                Game.writeWhoWin(args, userMove, computerIndexChoice);
                Printer.print("HMAC key: " + hashSecretKey + "\n");
            } else {
                condition = false;
            }
        }
    }
}



