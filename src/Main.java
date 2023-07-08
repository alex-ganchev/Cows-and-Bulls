
import java.io.PrintStream;
import java.util.*;
import java.io.File;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String GREEN = "\033[0;32m";
    public static final String BLUE = "\033[0;34m";

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
            System.out.println("Моля въведи число.");

            return false;
        }
        if (inputNumber.length() != digits) {
            System.out.println("Моля въведи число с " + digits + (digits == 1 ? " цифрa." : " цифри."));

            return false;
        }
        if (inputNumber.charAt(0) == '0') {
            System.out.println("Моля въведи число без водеща 0.");

            return false;
        }
        for (int i = 0; i < inputNumber.length() - 1; i++) {
            for (int j = i + 1; j < inputNumber.length(); j++) {
                if (inputNumber.charAt(i) == inputNumber.charAt(j)) {
                    System.out.println("Моля въведи число с " + digits + " неповтарящи се цифри.");

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

    public static void selectMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("#-----------------------------------------------------------#");
        System.out.println("|      /)  (\\            БИКОВЕ и КРАВИ                     |");
        System.out.println("| .-._((.~~.))_.-,  ~~~~~~~~~ МЕНЮ ~~~~~~~~  .-._.-~~-._.-, |");
        System.out.println("|  '-.   @@   ,-'   1. Игра с един играч     '-.   @@   ,-' |");
        System.out.println("|    / .o--o. \\     2. Игра с двама играчи     / .o--o. \\   |");
        System.out.println("|   ( ( .__. ) )    3. Класация               ( ( .__. ) )  |");
        System.out.println("|    ) '----' (     4. Изход                   ) '----' (   |");
        System.out.println("#-----------------------------------------------------------#");
        System.out.print("Вашият избор : ");
        String choice = scanner.next();
        switch (choice) {
            case "1": {
                System.out.println("#-------------------- ИГРА С ЕДИН ИГРАЧ --------------------#");
                String playerName = enterPlayerName();
                int digits = difficultyChoice();
                playSinglePlayer(playerName, digits);
                break;
            }
            case "2": {
                System.out.println("#------------------- ИГРА С ДВАМА ИГРАЧИ -------------------#");
                String player1Name = enterPlayerName();
                String player2Name = enterPlayerName();
                int digits = difficultyChoice();
                playMultiPlayer(player1Name, player2Name, digits);
                break;
            }
            case "3": {
                System.out.println("#------------------------ КЛАСАЦИЯ -------------------------#");
                printRanking();
                break;
            }
            case "4": {
                System.out.println("#-------------------------- ИЗХОД --------------------------#");
                System.out.println("\t\t\t\tПроект на Александър Ганчев");
                System.out.println("\t\t\t\thttps://digitalrazgrad.org/");
                System.out.println("#-----------------------------------------------------------#");
                break;
            }
            default: {
                System.out.println("Моля изберете валидна опция от менюто.");
                selectMenu();
                break;
            }
        }
    }

    public static void selectSecondMenu(String player1Name, String player2Name, int digits) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("#-----------------------------------------------------------#");
        System.out.println("|      /)  (\\            БИКОВЕ и КРАВИ                     |");
        System.out.println("| .-._((.~~.))_.-,  ~~~~~~~~~ МЕНЮ ~~~~~~~~  .-._.-~~-._.-, |");
        System.out.println("|  '-.   @@   ,-'       1. Нова игра         '-.   @@   ,-' |");
        System.out.println("|    / .o--o. \\         2. Главно меню         / .o--o. \\   |");
        System.out.println("|   ( ( .__. ) )                              ( ( .__. ) )  |");
        System.out.println("|    ) '----' (                                ) '----' (   |");
        System.out.println("#-----------------------------------------------------------#");
        System.out.print("Вашият избор : ");
        String choice = scanner.next();
        switch (choice) {
            case "1": {
                System.out.println("Вие избрахте \"Нова игра\".");
                if (player2Name == null) {
                    playSinglePlayer(player1Name, digits);
                } else {
                    playMultiPlayer(player1Name, player2Name, digits);
                }
                break;
            }
            case "2": {
                System.out.println("Вие избрахте \"Главно меню\".");
                selectMenu();
                break;
            }
            default: {
                System.out.println("Моля изберете валидна опция от менюто.");
                selectSecondMenu(player1Name, player2Name, digits);
                break;
            }
        }

    }

    public static int difficultyChoice() {
        Scanner scanner = new Scanner(System.in);
        int digits = 0;
        String input;
        do {
            System.out.print("Въведете трудност от 1 до 9 : ");
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

    public static String enterPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Въведете име на играча : ");
        return scanner.next();
    }

    public static void playSinglePlayer(String playerName, int digits) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> randomNumberList = generateRandomNumber(digits);
        printArrayList(randomNumberList);
        System.out.println();
        int bulls = 0;
        int countTurns = 0;
        do {

            System.out.print(playerName + " въведи число: ");
            String input = scanner.nextLine();
            if (validateInput(input, digits)) {
                int inputNumber = Integer.parseInt(input);
                ArrayList<Integer> inputNumberList = convertIntToArrayList(inputNumber);
                int cows = countCows(inputNumberList, randomNumberList);
                bulls = countBulls(inputNumberList, randomNumberList);
                System.out.println("-------------------------------------------------------------");
                System.out.print(inputNumber + " - ");
                printOutput(cows, bulls);
                System.out.println("-------------------------------------------------------------");
                countTurns++;
            }
        } while (bulls != digits);
        System.out.println("Поздравления " + playerName + "! \nПозна числото в " + countTurns + (countTurns == 1 ? " ход." : " хода."));
        if (playerName.equals("EasterEgg") && digits == 9) {
            createRanking();
            System.out.println("Класацията е нулирана! :)");
            selectMenu();
        } else {
            writeRanking(validateRanking(playerName, countTurns, digits));
            selectSecondMenu(playerName, null, digits);
        }
    }

    public static String randomlySelectedFirstPlayer(String firstPlayerName, String secondPlayerName) {
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(2);
        if (randomNumber == 0) {

            return firstPlayerName;
        } else {

            return secondPlayerName;
        }
    }

    public static void playMultiPlayer(String player1Name, String player2Name, int digits) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> firstRandomNumberList = generateRandomNumber(digits);
        ArrayList<Integer> secondRandomNumberList = generateRandomNumber(digits);
        ArrayList<Integer> randomNumberListOnTurn;
        printArrayList(firstRandomNumberList);
        printArrayList(secondRandomNumberList);
        System.out.println();
        int bulls = 0;
        int countTurns = 0;
        String playerOnTurn = randomlySelectedFirstPlayer(player1Name, player2Name);
        if (playerOnTurn.equals(player1Name)) {
            System.out.print(BLUE);
            randomNumberListOnTurn = firstRandomNumberList;
        } else {
            System.out.print(GREEN);
            randomNumberListOnTurn = secondRandomNumberList;
        }
        do {
            System.out.print(playerOnTurn + " въведи число: ");
            String input = scanner.nextLine();
            if (validateInput(input, digits)) {
                int inputNumber = Integer.parseInt(input);
                ArrayList<Integer> inputNumberList = convertIntToArrayList(inputNumber);
                int cows = countCows(inputNumberList, randomNumberListOnTurn);
                bulls = countBulls(inputNumberList, randomNumberListOnTurn);
                System.out.println("-------------------------------------------------------------");
                System.out.print(inputNumber + " - ");
                printOutput(cows, bulls);
                System.out.println("-------------------------------------------------------------");
                countTurns++;
                if (playerOnTurn.equals(player1Name) && bulls != digits) {
                    System.out.print(GREEN);
                    playerOnTurn = player2Name;
                    randomNumberListOnTurn = secondRandomNumberList;
                } else if (playerOnTurn.equals(player2Name) && bulls != digits) {
                    System.out.print(BLUE);
                    playerOnTurn = player1Name;
                    randomNumberListOnTurn = firstRandomNumberList;
                }
            }

        } while (bulls != digits);
        countTurns = (int) Math.ceil(countTurns / 2.0);
        System.out.print(ANSI_RESET);
        System.out.println("Поздравления " + playerOnTurn + "! \nПозна числото в " + countTurns + (countTurns == 1 ? " ход." : " хода."));
        writeRanking(validateRanking(playerOnTurn, countTurns, digits));
        selectSecondMenu(player1Name, player2Name, digits);
    }

    public static void printRanking() {
        File file = new File("ranking.csv");
        try {
            Scanner sc = new Scanner(file, "windows-1251");
            System.out.println("\t\t\t#--------------------------------#");
            System.out.println("\t\t\t| Трудност |  Ходове  |   Име    |");
            System.out.println("\t\t\t#--------------------------------#");
            while (sc.hasNext()) {
                String[] splitText = sc.nextLine().split(";");
                System.out.print("\t\t\t");
                for (int i = 0; i < splitText.length; i++) {
                    System.out.printf("%-11s", "| " + splitText[i] + " ");
                }
                System.out.println("|");
            }
            System.out.println("\t\t\t#--------------------------------#");
            sc.close();
        } catch (Exception e) {
            createRanking();
            System.out.println("Създадена е празна класация.");
            printRanking();
        }
        selectMenu();
    }

    public static void createRanking() {
        try {
            File file = new File("ranking.csv");
            PrintStream ps = new PrintStream(file, "windows-1251");
            String[][] ranking = {
                    {"1", "999", " "},
                    {"2", "999", " "},
                    {"3", "999", " "},
                    {"4", "999", " "},
                    {"5", "999", " "},
                    {"6", "999", " "},
                    {"7", "999", " "},
                    {"8", "999", " "},
                    {"9", "999", " "},
            };
            for (int k = 0; k < ranking.length; k++) {
                for (int j = 0; j < ranking[0].length; j++) {
                    ps.print(ranking[k][j]);
                    ps.print(";");
                }
                ps.println();
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String[][] validateRanking(String playerName, int countTurns, int digits) {
        File file = new File("ranking.csv");
        String[][] ranking = new String[9][3];
        int i = 0;
        try {
            Scanner sc = new Scanner(file, "windows-1251");
            while (sc.hasNext()) {
                String[] splitCsv = sc.nextLine().split(";");
                for (int j = 0; j < splitCsv.length; j++) {
                    ranking[i][j] = splitCsv[j];
                }
                if (Integer.parseInt(ranking[i][0]) == digits && countTurns < Integer.parseInt(ranking[i][1])) {
                    if (ranking[i][2].equals(" ")) {
                        System.out.println(playerName + " постави ново постижение!");
                    } else if (ranking[i][2].equals(playerName)) {
                        System.out.println(playerName + " подобри най-доброто си постижение!");
                    } else {
                        System.out.println(playerName + " подобри предишното най-добро постижение на " + ranking[i][2] + "!");
                    }
                    ranking[i][1] = countTurns + "";
                    ranking[i][2] = playerName;
                }
                i++;
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ranking;
    }

    public static void writeRanking(String[][] ranking) {
        File file = new File("ranking.csv");
        try {
            PrintStream ps = new PrintStream(file, "windows-1251");
            for (int k = 0; k < ranking.length; k++) {
                for (int j = 0; j < ranking[0].length; j++) {
                    ps.print(ranking[k][j]);
                    ps.print(";");
                }
                ps.println();
            }
            ps.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        selectMenu();
    }
}