import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;

public class chatbot {

    static String chatbotAnswer = "";
    static String userInput = "";
    static boolean questionAsked = false;
    static boolean feelingAsked = false;

    public static void main(String[] args) {
        System.out.println("Type anything in to get a response. Type in 'stop' without the quotes to exit.");
        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals("stop")) {
            userInput = scanner.nextLine().toLowerCase();
            if (userInput.equals("stop")) {
                System.out.println("It was a pleasure to talk to you, see you later!");
                break;
            }
            chatbotAnswer = "";

            reactToAnswer();
            greetingCheck(userInput);
            feelingCheck();
            likeCheck(userInput);
            hateCheck(userInput);

            confusedCheck(userInput);

            askQuestion();
            System.out.println("Bot: " + chatbotAnswer);
        }
    }

    private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
    // private static ArrayList<String> posAdjectives = new ArrayList<String>();
    // private static ArrayList<String> negAdjectives = new ArrayList<String>();

    private static final String SPACE = " ";

    /**
     * Taken from PLTW 2.4.6 Consumer Reviews Lab
     */
    static {
        try {
            Scanner input = new Scanner(new File("cleanSentiment.csv"));
            while (input.hasNextLine()) {
                String[] temp = input.nextLine().split(",");
                sentiment.put(temp[0], Double.parseDouble(temp[1]));
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error reading or parsing cleanSentiment.csv");
        }
    }

    /**
     * Calculates the sentiment value of a single word
     */
    public static double sentimentVal(String word) {
        try {
            return sentiment.get(word.toLowerCase());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Returns an array consisting of the words in the user input, used in
     * totalSentiment
     */
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

    /**
     * Calculates the sentiment value of each word the user input array and totals
     * it
     */
    public static double totalSentiment(String word) {
        double finalScore = 0;
        ArrayList<String> wordsToCheck = getWords(word);
        for (String i : wordsToCheck) {
            finalScore += sentimentVal(i);
        }
        return finalScore;
    }

    /**
     * Bot responses to possible user greetings
     */
    public static void greetingCheck(String input) {
        String[] checks = { "hello", "hi", "hey", "greetings", "good morning", "what's up", "sup" };

        String[] answers = { "Hey, nice to meet you!", "Hello, can't wait to talk to you!" };
        for (int i = 0; i < checks.length; i++) {
            if (userInput.contains(checks[i])) {
                chatbotAnswer += answers[(int) (Math.random() * answers.length - 1)] + " ";
                break;
            }
        }
    }

    /**
     * Bot reactions to user after asking "How are you?"
     */
    public static void feelingCheck() {
        System.out.println("How are you doing today?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        // String[] checks = { "good", "great", "ok", "bad" };

        // for (int i = 0; i < checks.length; i++) {
        if (!feelingAsked) {
            if (totalSentiment(input) > 0) {
                chatbotAnswer += "That's good!" + " ";
                // break;
            } else if (totalSentiment(input) < 0) {
                chatbotAnswer += "Aww that's sad." + " ";
                // break;
            } else {
                System.out.println("OK.");
                // break;
            }
            feelingAsked = true;
        }

        // }
    }

    /**
     * If the user's input contains "like", bot says they hate the same thing too
     */
    public static void likeCheck(String input) {
        String answer = "";
        if (userInput.contains("like")) {
            for (int i = input.indexOf("like") + 5; i < input.length(); i++) {
                if (!input.substring(i, i + 1).equals(" ")) {
                    answer += input.substring(i, i + 1);
                } else {
                    break;
                }
            }
            chatbotAnswer += "I like " + answer + " too! ";
        }
    }

    /**
     * If the user's input contains "hate", bot says they hate the same thing too
     */
    public static void hateCheck(String input) {
        String answer = "";
        if (userInput.contains("hate")) {
            for (int i = input.indexOf("hate") + 5; i < input.length(); i++) {
                if (!input.substring(i, i + 1).equals(" ")) {
                    answer += input.substring(i, i + 1);
                } else {
                    break;
                }
            }
            chatbotAnswer += "I am not a big fan of " + answer + " as well to be honest. ";
        }
    }

    /**
     * If the user does not say anything, bot gives a random fact
     */
    public static void confusedCheck(String input) {
        String[] answers = { "One day on Venus is longer than one year on Earth",
                "The first person convicted of speeding was going eight mph.",
                "The Nobel Peace Prize is named for Alfred Nobel, the inventor of dynamite.",
                "It's illegal to own just one guinea pig in Switzerland",
                "The average person spends two weeks of their life waiting at traffic lights." };
        if (chatbotAnswer.equals("")) {
            chatbotAnswer += "Sorry, I don't understand what you're saying. Here's a fun fact instead: ";
            chatbotAnswer += answers[(int) (Math.random() * answers.length - 1)] + " ";
        }
    }

    /**
     * Bot asks a random question to the user
     */
    public static void askQuestion() {
        String[] questions = { "How are you doing today?", "What's the weather like where you live?",
                "What is your favorite hobby?", "Is cereal a soup?",
                "If a tree falls in a forest and no one is around to hear it, does it make a sound?",
                "What's the most useless thing you own?", "Do you have a pet?" };
        if (userInput.length() < 10 && questionAsked == false) {
            questionAsked = true;
            chatbotAnswer += "I have a question for you: ";
            chatbotAnswer += questions[(int) (Math.random() * questions.length - 1)] + " ";
        }
    }

    /**
     * Bot gives a random reaction to after a question is asked
     */
    public static void reactToAnswer() {
        String[] reactions = { "That's cool!", "Interesting!", "Tell me more.", "Thanks for sharing!" };
        if (questionAsked) {
            chatbotAnswer += reactions[(int) (Math.random() * reactions.length - 1)] + " ";
            questionAsked = false;
        }
    }
}
