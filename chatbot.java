import java.util.Scanner;
public class chatbot{
    static String chatbotAnswer = "";
    static String userInput = "";
    static boolean metAlready = false;
    public static void main(String []args){
        System.out.println("Hello");
        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals("stop")) {
            userInput = scanner.nextLine().toLowerCase();
            chatbotAnswer = "";
            greetingCheck(userInput);
            likeCheck(userInput);
            hateCheck(userInput);
            System.out.println(chatbotAnswer);
        }

        //while loop
        //every iteration get a scanner to get input, make it lowercase and put that into userInput
        //clear the final answer, run all the checks we got to form the final answer
        //print final answer 
        //break loop when a certain phrase is typed like "stop" or "break"
    }

    public static void greetingCheck(String input) {
        String[] checks = {"hello", "hi", "hey", "greetings", "good morning", "what's up"}; //add more here, also keep all lowercase for simplicity

        //make a couple of answers in an array
        String[] answers = {"Hey, Nice to meet you!", "Hello!"}; //add more, current are just ideas
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
        
        //loop through array and check for a greeting word here from the checks array using userInput
        //concatenate the answer from this check to the final answer
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

    //add other checks that come to mind as separate methods similar to greetingCheck()
    //make a method that just spits out random fun animal facts or something just to make it more fun
}