import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int[] generateRandomNumber(){
        int[] number = new int[4];
        Random rnd = new Random();
        while (true) {
            for (int i = 0; i < number.length; i++) {
                number[i] = rnd.nextInt(10);
            }
            if (number[0] != 0 && number[0] != number[1] && number[0] != number[2] && number[0] != number[3] &&
                    number[1] != number[2] && number[1] != number[3] && number[2] != number[3]){
                break;
            }
        }
        return number;
    }
    public static int[] checkCowsAndBulls(int num, int[] number){
        int countCows = 0;
        int countBulls = 0;
        int[] inputNumber = new int[number.length];
        int[] cowsBulls = new int[2];
        int k = number.length-1;
        while (num > 0){
            int temp = num % 10;
            //System.out.println(temp);
            inputNumber[k] = temp;
            num = num / 10;
            k--;
        }
        for (int i = 0; i < number.length; i++) {
            for (int j = 0; j < inputNumber.length; j++) {
                if (inputNumber[j] == number[i] && j != i) {
                    countCows++;
                }
            }
            if (inputNumber[i] == number[i]) {
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
        int[] randomNumber = generateRandomNumber();
        int[] cowsBulls = new int[2];
        while (cowsBulls[1] != 4) {
            System.out.print("Въведете число: ");
            int n = scanner.nextInt();
            cowsBulls = checkCowsAndBulls(n, randomNumber);
            System.out.println(n + " - " + cowsBulls[0] + (cowsBulls[0] == 1 ? " крава" : " крави") + " и " + cowsBulls[1] + (cowsBulls[1] == 1 ? " бик" : " бика"));
        }
    }
}