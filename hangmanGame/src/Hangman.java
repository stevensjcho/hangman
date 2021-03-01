import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Hangman {
    private Difficulty currentDifficulty;
    private int numberOfChances;
    char[] array;
    private boolean guessed = false;
    private String answer;
    private final static int EASY_CHANCES = 10;
    private final static int MEDIUM_CHANCES = 10;
    private final static int DIFFICULT_CHANCES = 10;
    private HashMap<Character, List<Integer>> map;



    public Hangman(String difficulty) throws IOException {
        if (difficulty.equals("easy")) {
            this.currentDifficulty = Difficulty.EASY;
            this.numberOfChances = EASY_CHANCES;
            this.answer = chooseWord("./hangmanGame/src/EasyStrings");
        } else if(difficulty.equals("medium")) {
            this.currentDifficulty = Difficulty.MEDIUM;
            this.numberOfChances = MEDIUM_CHANCES;
            this.answer = chooseWord("./hangmanGame/src/MediumStrings");
        } else if(difficulty.equals("difficult")) {
            this.currentDifficulty = Difficulty.DIFFICULT;
            this.numberOfChances = DIFFICULT_CHANCES;
            this.answer = chooseWord("./hangmanGame/src/DifficultStrings");
        }

        initializeHashMap(this.answer);

    }

    private String chooseWord(String txtPath) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(txtPath));
        String words;
        String word;
        List<String> wordsList = new ArrayList<>();

        while((words = in.readLine()) != null) {
            wordsList.add(words);
        }

        String[] wordsArray = wordsList.toArray(new String[0]);
        int arrayLength = wordsArray.length;

        Random randomizer = new Random();
        word = wordsArray[randomizer.nextInt(arrayLength)];

        return word;
    }

    public char[] makeInitialLine() {
        int stringLength = this.answer.length();

        this.array = new char[stringLength];
        for(int i = 0; i < stringLength; i++) {
            array[i] = '?';
            System.out.print(array[i]);
        }
        System.out.println();
        return this.array;
    }

    private void initializeHashMap(String str) {
        map = new HashMap<>();
        char currentChar;
        for(int i = 0; i < str.length(); i++) {
            currentChar = str.charAt(i);
            if(!map.containsKey(currentChar)) {
                map.put(currentChar, new ArrayList<>((Arrays.asList(i))));
            } else {
                map.get(currentChar).add(i);
            }
        }
    }

    private boolean guessWord(char letter) {
        if (map.containsKey(letter)) {
            return true;
        } else {
            return false;
        }
    }

    public void game() {
        HashMap<Character, Integer> lettersSeenSoFar = new HashMap<>();
        makeInitialLine();
        Scanner scan = new Scanner(System.in);
        char letter;
        int counter = 0;
        List<Integer> myList;

        while(numberOfChances > 0) {
            System.out.print("Guess a letter: ");
            letter = scan.next().charAt(0);
            if(!lettersSeenSoFar.containsKey(letter)) {
                lettersSeenSoFar.put(letter, counter);
                counter++;
                if(!guessWord(letter)){
                    numberOfChances--;
                } else {
                    myList = map.get(letter);
                    if(myList.size() == 1) {
                        array[myList.get(0)] = letter;
                    } else {
                        for(int i = 0; i < myList.size(); i++) {
                            array[myList.get(i)] = letter;
                        }
                    }
                }
            } else {
                System.out.println("You already chose this letter!");
            }
            String compareString = "";
            for (int i = 0; i < array.length; i++) {
                compareString = compareString + array[i];
            }
            printImage(this.numberOfChances);
            if(answer.equals(compareString)) {
                guessed = true;
                break;
            }
            System.out.println(compareString + ", Number of Chances Left: " + numberOfChances);
        }
        if (guessed) {
            System.out.println("You Won!");
        } else {
            System.out.println("You Lost!");
            System.out.println("Word was: " + answer);
        }
    }

    private void printImage(int chancesLeft) {
        if(chancesLeft == 9) {
            System.out.println("   \n\n");
            System.out.println("-------");
            System.out.println("|     |");
        } else if (chancesLeft == 8) {
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("-------");
            System.out.println("|     |");
        } else if (chancesLeft == 7) {
            System.out.println("   _________");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("-------");
            System.out.println("|     |");
        } else if (chancesLeft == 6) {
            System.out.println("   _________");
            System.out.println("   |       |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("-------");
            System.out.println("|     |");
        }else if (chancesLeft == 5) {
            System.out.println("   _________");
            System.out.println("   |       |");
            System.out.println("   |       0");
            System.out.println("   |");
            System.out.println("-------");
            System.out.println("|     |");
        } else if (chancesLeft == 4) {
            System.out.println("   _________");
            System.out.println("   |       |");
            System.out.println("   |       0");
            System.out.println("   |       |");
            System.out.println("-------");
            System.out.println("|     |");
        }else if (chancesLeft == 3) {
            System.out.println("   _________");
            System.out.println("   |       |");
            System.out.println("   |      \\0");
            System.out.println("   |       |");
            System.out.println("-------");
            System.out.println("|     |");
        }else if (chancesLeft == 2) {
            System.out.println("   _________");
            System.out.println("   |       |");
            System.out.println("   |      \\0/");
            System.out.println("   |       |");
            System.out.println("-------");
            System.out.println("|     |");
        } else if (chancesLeft == 1) {
            System.out.println("   _________");
            System.out.println("   |       |");
            System.out.println("   |      \\0/");
            System.out.println("   |       |");
            System.out.println("-------   /");
            System.out.println("|     |");
        } else if (chancesLeft == 0) {
            System.out.println("   _________");
            System.out.println("   |       |");
            System.out.println("   |      \\0/");
            System.out.println("   |       |");
            System.out.println("-------   / \\");
            System.out.println("|     |");
        }

    }

    public Difficulty getCurrentDifficulty() {
        return currentDifficulty;
    }
}
