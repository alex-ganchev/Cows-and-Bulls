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

    public static int[] checkCowsAndBulls(int inputNumber, ArrayList<Integer> randomNumber) {
        int size = randomNumber.size();
        int[] arrayInputNumber = new int[size];
        int k = size - 1;

        while (inputNumber > 0) {
            int temp = inputNumber % 10;
            arrayInputNumber[k] = temp;
            inputNumber = inputNumber / 10;
            k--;
        }

        int countCows = 0;
        int countBulls = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < arrayInputNumber.length; j++) {
                if (arrayInputNumber[j] == randomNumber.get(i) && j != i) {
                    countCows++;
                }
            }
            if (arrayInputNumber[i] == randomNumber.get(i)) {
                countBulls++;
            }
        }

        int[] cowsBulls = new int[2];
        cowsBulls[0] = countCows;
        cowsBulls[1] = countBulls;

        return cowsBulls;
    }

    public static boolean validateWin(int[] cowsBulls, int digits) {
        if (cowsBulls[1] == digits) {
            System.out.println("Честито! Познахте числото.");
            return true;
        } else {
            if (cowsBulls[0] != 0) {
                System.out.print(cowsBulls[0] + (cowsBulls[0] == 1 ? " крава" : " крави"));
            }
            if (cowsBulls[0] != 0 && cowsBulls[1] != 0) {
                System.out.print(" и ");
            }
            if (cowsBulls[0] == 0 && cowsBulls[1] == 0) {
                System.out.print("Няма съвпадения.");
            }
            if (cowsBulls[1] != 0) {
                System.out.print(cowsBulls[1] + (cowsBulls[1] == 1 ? " бик" : " бика"));
            }
            System.out.println();
        }

        return false;
    }

    public static void printArrayList(ArrayList<Integer> list) {
        for (int element : list) {
            System.out.print(element);
        }
    }

    public static boolean validateInput(String inputNumber, int digits) {
        try {
            Integer.parseInt(inputNumber);
        } catch (Exception e) {
        }
        if (inputNumber.length() != digits) {
            return false;
        } else {
            for (int i = 0; i < inputNumber.length() - 1; i++) {
                for (int j = i + 1; j < inputNumber.length(); j++) {
                    if (inputNumber.charAt(i) == inputNumber.charAt(j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int digits = 4;
        ArrayList<Integer> randomNumber = generateRandomNumber(digits);
        int[] cowsBulls = {0, 0};
        String inputNumber;
        printArrayList(randomNumber);
        System.out.println();
        do {
            System.out.print("Въведете число: ");
            inputNumber = scanner.next();
            if (validateInput(inputNumber, digits)) {
                int i = Integer.parseInt(inputNumber);
                cowsBulls = checkCowsAndBulls(i, randomNumber);
                validateWin(cowsBulls, digits);
            } else {
                System.out.println("Въведете валидно число!");
            }
        } while (cowsBulls[1] != digits);
    }
}