import java.util.Scanner;
public class chatbot{
    static String chatbotAnswer = "";
    static String userInput = "";
    public static void main(String []args){
        System.out.println("Hello");
        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals("stop")) {
            userInput = scanner.nextLine().toLowerCase();
            chatbotAnswer = "";
            greetingCheck(userInput);
            System.out.println(chatbotAnswer);
        }

        //while loop
        //every iteration get a scanner to get input, make it lowercase and put that into userInput
        //clear the final answer, run all the checks we got to form the final answer
        //print final answer 
        //break loop when a certain phrase is typed like "stop" or "break"
    }

    public static void greetingCheck(String input) {
        String[] checks = {"hello", "hi", "hey"}; //add more here, also keep all lowercase for simplicity

        //make a couple of answers in an array
        String[] answers = {"Hey, Nice to meet you!", "Hello!"}; //add more, current are just ideas

        for (int i = 0; i < checks.length; i++) {
            if (userInput.contains(checks[i])) {
                chatbotAnswer += answers[(int) (Math.random()*checks.length - 1)];
                break;
            }
        }
        //loop through array and check for a greeting word here from the checks array using userInput
        //concatenate the answer from this check to the final answer
    }

    //add other checks that come to mind as separate methods similar to greetingCheck()
    //make a method that just spits out random fun animal facts or something just to make it more fun
}