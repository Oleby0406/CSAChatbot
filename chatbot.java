import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;

public class chatbot {
    static String chatbotAnswer = "";
    static String userInput = "";

    public static void main(String[] args) {
        System.out.println("Hello! Type \"stop\" to stop chatting.");
        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals("stop")) {
            userInput = scanner.nextLine().toLowerCase();
            chatbotAnswer = "";
            greetingCheck(userInput);
            greetingCheck2(userInput);
            System.out.println("Bot: " + chatbotAnswer);
        }

        // while loop
        // every iteration get a scanner to get input, make it lowercase and put that
        // into userInput
        // clear the final answer, run all the checks we got to form the final answer
        // print final answer
        // break loop when a certain phrase is typed like "stop" or "break"
    }

    private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
    private static ArrayList<String> posAdjectives = new ArrayList<String>();
    private static ArrayList<String> negAdjectives = new ArrayList<String>();

    private static final String SPACE = " ";

    static {
        try {
            Scanner input = new Scanner(new File("cleanSentiment.csv"));
            while (input.hasNextLine()) {
                String[] temp = input.nextLine().split(",");
                sentiment.put(temp[0], Double.parseDouble(temp[1]));
                // System.out.println("added "+ temp[0] + ", " + temp[1]);
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error reading or parsing cleanSentiment.csv");
        }

        // read in the positive adjectives in postiveAdjectives.txt
        /*
         * try { Scanner input = new Scanner(new File("positiveAdjectives.txt")); while
         * (input.hasNextLine()) { String temp = input.nextLine().trim();
         * System.out.println(temp); posAdjectives.add(temp); } input.close(); } catch
         * (Exception e) {
         * System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
         * }
         * 
         * // read in the negative adjectives in negativeAdjectives.txt try { Scanner
         * input = new Scanner(new File("negativeAdjectives.txt")); while
         * (input.hasNextLine()) { negAdjectives.add(input.nextLine().trim()); }
         * input.close(); } catch (Exception e) {
         * System.out.println("Error reading or parsing negativeAdjectives.txt"); }
         */
    }

    public static double sentimentVal(String word) {
        try {
            return sentiment.get(word.toLowerCase());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Returns the ending punctuation of a string, or the empty string if there is
     * none
     */
    public static String getPunctuation(String word) {
        String punc = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            if (!Character.isLetterOrDigit(word.charAt(i))) {
                punc = punc + word.charAt(i);
            } else {
                return punc;
            }
        }
        return punc;
    }

    public static ArrayList<String> getWords(String input) {
        String temp = "";
        ArrayList<String> words = new ArrayList<String>();
            for (int i = 0; i < input.length(); i++) {
                if (!input.substring(i, i + 1).equals(SPACE)) {
                    temp += input.substring(i, i + 1);
                }
                words.add(temp);
            }
        return words;
    }

    public static double totalSentiment(String word) {
        double finalScore = 0;
        ArrayList<String> wordsToCheck = getWords(word);
        for (String i: wordsToCheck) {
            finalScore += sentimentVal(i);
        }
        return finalScore;
    }

    public static void greetingCheck(String input) {
        String[] checks = { "hello", "hi", "hey", "sup" }; // add more here, also keep all lowercase for simplicity

        // make a couple of answers in an array
        String[] answers = { "Hey, nice to meet you!", "Hello!", ":)" }; // add more, current are just ideas

        for (int i = 0; i < checks.length; i++) {
            if (userInput.contains(checks[i])) {
                chatbotAnswer += answers[(int) (Math.random() * checks.length - 1)];
                break;
            }
        }
        // loop through array and check for a greeting word here from the checks array
        // using userInput
        // concatenate the answer from this check to the final answer
    }

    public static void greetingCheck2(String input) {        
        System.out.println("How are you?");
        Scanner scanner = new Scanner(System.in);
        userInput = scanner.nextLine().toLowerCase();

        String[] checks = { "good", "great", "ok", "bad" }; // add more here, also keep all lowercase for simplicity

        // make a couple of answers in an array
        String[] answers = { "That's good!", "Aww that's terrible." }; // add more, current are just ideas

        for (int i = 0; i < checks.length; i++) {
            if (userInput.contains(checks[0]) || userInput.contains(checks[1]) || userInput.contains(checks[2])) {
                chatbotAnswer += answers[0];
                break;
            } else if (userInput.contains(checks[3])) {
                chatbotAnswer += answers[1];
                break;
            }
        }
        // loop through array and check for a greeting word here from the checks array
        // using userInput
        // concatenate the answer from this check to the final answer
    }

    // make a method that just spits out random fun animal facts or something just
    // to make it more fun
}