import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int[] generateRandomNumber(int digits){
        int[] number = new int[digits];
        int i = 0;
        Random rnd = new Random();
        HashSet<Integer> uniqeNumbers = new HashSet<>();
        while (uniqeNumbers.size() < digits) {
            uniqeNumbers.add(rnd.nextInt(10));
        }
        for (int element: uniqeNumbers) {
            number[i++] = element;
        }
        return number;
    }
    public static int[] checkCowsAndBulls(int inputNumber, int[] randomNumber){
        int countCows = 0;
        int countBulls = 0;
        int[] arrayInputNumber = new int[randomNumber.length];
        int[] cowsBulls = new int[2];
        int k = randomNumber.length-1;
        while (inputNumber > 0){
            int temp = inputNumber % 10;
            //System.out.println(temp);
            arrayInputNumber[k] = temp;
            inputNumber = inputNumber / 10;
            k--;
        }
        for (int i = 0; i < randomNumber.length; i++) {
            for (int j = 0; j < arrayInputNumber.length; j++) {
                if (arrayInputNumber[j] == randomNumber[i] && j != i) {
                    countCows++;
                }
            }
            if (arrayInputNumber[i] == randomNumber[i]) {
                countBulls++;
            }
        }
        cowsBulls[0] = countCows;
        cowsBulls[1] = countBulls;
        return cowsBulls;
    }

    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        int[] randomNumber = generateRandomNumber(4);
        int[] cowsBulls = new int[2];
        printArray(randomNumber);
        System.out.println();
        while (cowsBulls[1] != 4) {
        System.out.print("Въведете число: ");
        int inputNumber = scanner.nextInt();
        cowsBulls = checkCowsAndBulls(inputNumber, randomNumber);
        System.out.println(inputNumber + " - " + cowsBulls[0] + (cowsBulls[0] == 1 ? " крава" : " крави") + " и " + cowsBulls[1] + (cowsBulls[1] == 1 ? " бик" : " бика"));
        }
    }
}