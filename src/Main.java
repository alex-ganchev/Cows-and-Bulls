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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int digits = 4;
        ArrayList<Integer> randomNumberList = generateRandomNumber(digits);
        printArrayList(randomNumberList);
        System.out.println();
        int bulls = 0;
        do {
            System.out.print("Въведете число: ");
            String input = scanner.nextLine();
            if (validateInput(input, digits)) {
                int inputNumber = Integer.parseInt(input);
                ArrayList<Integer> inputNumberList = convertIntToArrayList(inputNumber);
                int cows = countCows(inputNumberList, randomNumberList);
                bulls = countBulls(inputNumberList, randomNumberList);
                printOutput(cows, bulls);
            }
        } while (bulls != digits);
    }
}