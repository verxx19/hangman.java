import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        final Random random = new Random();
        final String[] words = {"skillmea", "akademia", "pocitac", "hardware"};
        final String wordToGuess = selectRandomWord(random, words);
        String hiddenWord = generateHiddenWord(wordToGuess);
        int guessesLeft = 6;
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman!");
        System.out.println("Guess the word: " + hiddenWord);

        while (guessesLeft > 0 && hiddenWord.contains("_")) {
            System.out.println("Enter a letter: ");
            final char guess = scanLetter(scanner);
            if (wordToGuess.contains(String.valueOf(guess))) {
                hiddenWord = revealLetters(wordToGuess, hiddenWord, guess);
                System.out.println("Correct guess! Updated word: " + hiddenWord);
            } else {
                guessesLeft--;
                System.out.println("Incorrect guess! You have " + (guessesLeft) + " guesses left");
            }
        }
        if (hiddenWord.contains("_")) {
            System.out.println("Sorry, you have run out of guesses, the word was: " + wordToGuess);
        } else {
            System.out.println("Congratulations! You nailed it");
        }
    }

    public static String revealLetters(String word, String hiddenWord, char letter) {
        char[] hiddenWordChars = hiddenWord.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                hiddenWordChars[i] = letter;
            }
        }
        return String.valueOf(hiddenWordChars);
    }

    public static char scanLetter(Scanner scanner) {
        char guess;
        while (true) {
            try {
                String line = scanner.nextLine();
                if (line.length() != 1) {
                    throw new Exception("Line lenght is not 1. Please enter a single character");
                }
                guess = line.charAt (0);
                if (!Character.isLetter(guess)) {
                    throw new Exception("Character is not a letter. Please enter a single character");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
        return guess;
    }

    public static String generateHiddenWord(String word) {
        return "_".repeat(word.length());
    }

    public static String selectRandomWord(Random random, String[] words) {
        return words[random.nextInt(words.length)];
    }
}