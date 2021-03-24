import java.util.Scanner;

public class chatbot {

    static String chatbotAnswer = "";
    static String userInput = "";
    static boolean metAlready = false;

    public static void main(String []args) {
        System.out.println("Type anything in to get a response. Type in 'stop' without the quotes to exit.");
        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals("stop")) {

            userInput = scanner.nextLine().toLowerCase();
            chatbotAnswer = "";

            greetingCheck(userInput);
            likeCheck(userInput);
            hateCheck(userInput);

            confusedCheck(userInput);

            System.out.println(chatbotAnswer);
        }
        System.out.println("It was a pleasure to talk to you, see you later!");
    }

    public static void greetingCheck(String input) {
        String[] checks = {"hello", "hi", "hey", "greetings", "good morning", "what's up"};

        String[] answers = {"Hey, nice to meet you!", "Hello, can't wait to talk to you!"};
        if (metAlready) {
            chatbotAnswer += "I believe we have already met each other, let's talk about something else.";
        } else {
            for (int i = 0; i < checks.length; i++) {
                if (userInput.contains(checks[i])) {
                    chatbotAnswer += answers[(int) (Math.random()*answers.length - 1)];
                    metAlready = true;
                    break;
                }
            }
        }
    }

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
            chatbotAnswer += "I like " + answer + " too!";
        }
    }

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
            chatbotAnswer += "I am not a big fan of " + answer + " as well to be honest.";
        }
    }

    public static void confusedCheck(String input) {
        String[] answers = {"One day on Venus is longer than one year on Earth", "The first person convicted of speeding was going eight mph.", "The Nobel Peace Prize is named for Alfred Nobel, the inventor of dynamite."};
        if (chatbotAnswer.equals("")) {
            chatbotAnswer += "Sorry, I don't understand what you're saying. Here's a fun fact instead: ";
            chatbotAnswer += answers[(int) (Math.random()*answers.length - 1)];
        }
    }
}