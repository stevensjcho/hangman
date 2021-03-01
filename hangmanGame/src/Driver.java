import java.io.IOException;

public class Driver {

    public static void main(String[] args) throws IOException {
        Hangman myHangman = new Hangman("Easy");
        myHangman.game();
    }

}
