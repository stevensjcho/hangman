import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String difficulty = "";
        boolean validDifficulty = false;
        System.out.print("Please choose a difficulty(Easy, Medium, Difficult): ");
        while(!validDifficulty) {
            difficulty = scan.next().toLowerCase();
            if (difficulty.equals("easy") || difficulty.equals("medium") || difficulty.equals("difficult")) {
                validDifficulty = true;
            } else {
                System.out.print("Please type in a valid difficulty!: ");
            }

        }
        Hangman myHangman = new Hangman(difficulty);
        myHangman.game();

    }

}
