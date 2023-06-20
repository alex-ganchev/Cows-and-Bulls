
import java.util.*;


public class Main {
    public static ArrayList<Integer> generateRandomNumber(int digits) {
        Random rnd = new Random();
        HashSet<Integer> uniqeNumbers = new HashSet<>();
        while (uniqeNumbers.size() < digits) {
            uniqeNumbers.add(rnd.nextInt(10));
        }

        ArrayList<Integer> randomNumberList = new ArrayList<>(uniqeNumbers);
        Collections.shuffle(randomNumberList);
        while (randomNumberList.get(0) == 0) {
            Collections.shuffle(randomNumberList);
        }

        return randomNumberList;
    }

    public static boolean validateInput(String inputNumber, int digits) {
        try {
            Integer.parseInt(inputNumber);
        } catch (NumberFormatException e) {
            System.out.println("Въведохте текст. Моля въведете число с " + digits + (digits == 1 ? " цифрa." : " цифри."));

            return false;
        }
        if (inputNumber.length() != digits) {
            System.out.print("Въведохте число с " + inputNumber.length() + (inputNumber.length() == 1 ? " цифрa." : " цифри. "));
            System.out.println("Моля въведете число с " + digits + (digits == 1 ? " цифрa." : " цифри."));

            return false;
        }
        if (inputNumber.charAt(0) == '0') {
            System.out.println("Моля въведете число без водеща 0 и с " + digits + (digits == 1 ? " цифрa." : " цифри."));

            return false;
        }
        for (int i = 0; i < inputNumber.length() - 1; i++) {
            for (int j = i + 1; j < inputNumber.length(); j++) {
                if (inputNumber.charAt(i) == inputNumber.charAt(j)) {
                    System.out.println("Моля въведете число с неповтарящи се цифри.");

                    return false;
                }
            }
        }

        return true;
    }

    public static ArrayList<Integer> convertIntToArrayList(int inputNumber) {
        ArrayList<Integer> inputNumberList = new ArrayList<>();
        do {
            inputNumberList.add(inputNumber % 10);
            inputNumber /= 10;
        } while (inputNumber > 0);

        Collections.reverse(inputNumberList);

        return inputNumberList;
    }

    public static int countCows(ArrayList<Integer> inputNumberList, ArrayList<Integer> randomNumberList) {
        int countCows = 0;
        for (int i = 0; i < inputNumberList.size(); i++) {
            for (int j = 0; j < randomNumberList.size(); j++) {
                if (inputNumberList.get(i).equals(randomNumberList.get(j)) && i != j) {
                    countCows++;
                }
            }
        }

        return countCows;
    }

    public static int countBulls(ArrayList<Integer> inputNumberList, ArrayList<Integer> randomNumberList) {
        int countBulls = 0;
        for (int i = 0; i < inputNumberList.size(); i++) {
            if (inputNumberList.get(i).equals(randomNumberList.get(i))) {
                countBulls++;
            }
        }

        return countBulls;
    }

    public static void printOutput(int cows, int bulls) {
        if (cows != 0) {
            System.out.print(cows + (cows == 1 ? " крава" : " крави"));
        }
        if (cows != 0 && bulls != 0) {
            System.out.print(" и ");
        }
        if (cows == 0 && bulls == 0) {
            System.out.print("Няма съвпадения.");
        }
        if (bulls != 0) {
            System.out.print(bulls + (bulls == 1 ? " бик" : " бика"));
        }
        System.out.println();
    }

    public static void printArrayList(ArrayList<Integer> list) {
        for (int element : list) {
            System.out.print(element);
        }
    }

    public static void menuChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------- МЕНЮ ----------");
        System.out.println("| 1. Игра с един играч   |");
        System.out.println("| 2. Игра с двама играча |");
        System.out.println("| 3. Класация            |");
        System.out.println("| 4. Изход               |");
        System.out.println("--------------------------");
        System.out.print("Вашият избор : ");
        String choice = scanner.next();
        switch (choice) {
            case "1": {
                System.out.println("Вие избрахте \"Игра с един играч\".");
                int digits = difficultyChoice();
                String playerName = inputPlayerName();
                playSinglePlayer(playerName, digits);
                break;
            }
            case "2": {
                System.out.println("Вие избрахте \"Игра с двама играча\".");
                int digits = difficultyChoice();
                String firstPlayerName = inputPlayerName();
                String secondPlayerName = inputPlayerName();
                playMultiPlayer(firstPlayerName, secondPlayerName, digits);
                break;
            }
//          case "3" :break;
            case "4": {
                System.out.println("Вие избрахте \"Изход\".");
                break;
            }
            default: {
                System.out.println("Моля изберете валидна опция от менюто.");
                menuChoice();
                break;
            }
        }

    }

    public static void secondMenuChoice(String firstPlayerName, String secondPlayerName, int digits) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------ МЕНЮ ------");
        System.out.println("| 1. Нова игра   |");
        System.out.println("| 2. Главно меню |");
        System.out.println("------------------");
        System.out.print("Вашият избор : ");
        String choice = scanner.next();
        switch (choice) {
            case "1": {
                System.out.println("Вие избрахте \"Нова игра\".");
                if (secondPlayerName == null) {
                    playSinglePlayer(firstPlayerName, digits);
                }else {
                   playMultiPlayer(firstPlayerName, secondPlayerName, digits);
                }
                break;
            }
            case "2": {
                System.out.println("Вие избрахте \"Главно меню\".");
                menuChoice();
                break;
            }
            default: {
                System.out.println("Моля изберете валидна опция от менюто.");
                secondMenuChoice(firstPlayerName, secondPlayerName, digits);
                break;
            }
        }

    }

    public static int difficultyChoice() {
        Scanner scanner = new Scanner(System.in);
        int digits = 0;
        String input;
        do {
            System.out.print("Въведете трудност от 1 до 9 (брой разряди на числото) : ");
            try {
                input = scanner.nextLine();
                digits = Integer.parseInt(input);

                if (digits < 1 || digits > 9) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Моля въведете валидна стойност!");
            }
        }
        while (digits < 1 || digits > 9);

        return digits;
    }

    public static String inputPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Въведете име на играча : ");

        return scanner.nextLine();
    }

    public static void playSinglePlayer(String playerName, int digits) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> randomNumberList = generateRandomNumber(digits);
        printArrayList(randomNumberList);
        System.out.println();
        int bulls = 0;
        int countTurn = 0;
        do {
            System.out.print(playerName + " въведи число: ");
            String input = scanner.nextLine();
            if (validateInput(input, digits)) {
                int inputNumber = Integer.parseInt(input);
                ArrayList<Integer> inputNumberList = convertIntToArrayList(inputNumber);
                int cows = countCows(inputNumberList, randomNumberList);
                bulls = countBulls(inputNumberList, randomNumberList);
                printOutput(cows, bulls);
                countTurn++;
            }
        } while (bulls != digits);
        System.out.println("Поздравления " + playerName + "! Позна числото в " + countTurn + (countTurn == 1 ? " ход." : " хода."));
        secondMenuChoice(playerName, null, digits);
    }

    public static void playMultiPlayer(String firstPlayerName, String secondPlayerName, int digits) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> firstRandomNumberList = generateRandomNumber(digits);
        ArrayList<Integer> secondRandomNumberList = generateRandomNumber(digits);
        printArrayList(firstRandomNumberList);
        printArrayList(secondRandomNumberList);
        System.out.println();
        int bulls = 0;
        int countTurn = 0;
        String playerNameOnTurn = firstPlayerName;
        ArrayList<Integer> randomNumberListOnTurn = firstRandomNumberList;
        do {
            System.out.print(playerNameOnTurn + " въведи число: ");
            String input = scanner.nextLine();
            if (validateInput(input, digits)) {
                int inputNumber = Integer.parseInt(input);
                ArrayList<Integer> inputNumberList = convertIntToArrayList(inputNumber);
                int cows = countCows(inputNumberList, randomNumberListOnTurn);
                bulls = countBulls(inputNumberList, randomNumberListOnTurn);
                printOutput(cows, bulls);
                countTurn++;
                System.out.println(countTurn);
                if (playerNameOnTurn.equals(firstPlayerName) && bulls != digits) {
                    playerNameOnTurn = secondPlayerName;
                    randomNumberListOnTurn = secondRandomNumberList;
                } else if (playerNameOnTurn.equals(secondPlayerName) && bulls != digits) {
                    playerNameOnTurn = firstPlayerName;
                    randomNumberListOnTurn = firstRandomNumberList;
                }
            }

        } while (bulls != digits);
        countTurn = (int)Math.ceil(countTurn/2.0);
        System.out.println("Поздравления " + playerNameOnTurn + "! Позна числото в " + countTurn + (countTurn == 1 ? " ход." : " хода."));
        secondMenuChoice(firstPlayerName, secondPlayerName, digits);
    }

    public static void main(String[] args) {
        menuChoice();
    }
}