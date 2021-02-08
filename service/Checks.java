package service;

public class Checks {
    public static boolean checkInputParameters(String[] arr) {
        if (arr.length < 3) {
            System.out.println("Please, re-enter with 3 or more parameters.\nExample: rock paper scissors or 1 2 3 4 5 etc.");
            return false;
        } else if (arr.length % 2 == 0) {
            System.out.println("Please, re-enter with odd parameters number.\nExample: rock paper scissors or 1 2 3 4 5 etc.");
            return false;
        } else if (!checkParametersUnique(arr)) {
            System.out.println("Please, re-enter with unique values.\nExample: rock paper scissors or 1 2 3 4 5 etc.");
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkParametersUnique(String[] array) {
        for (String i : array) {
            int counter = 0;
            for (String j : array) {
                if (i.equals(j)) {
                    counter++;
                }
                if (counter >= 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
