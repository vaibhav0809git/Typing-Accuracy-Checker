import java.util.*;

public class TypingSpeed {

    public static void main(String[] args) {
        printIntro();

        Scanner st = new Scanner(System.in);
        String initiate = st.nextLine().trim();

        if (initiate.equalsIgnoreCase("start")) {
            startCountdown();
            performAction();
        } else {
            System.out.println("Invalid input! Please type \"start\" to begin.");
        }

        st.close();
    }

    private static void printIntro() {
        System.out.println("  ***** TYPING SPEED CALCULATOR *****\n");
        System.out.println("  -> Try typing with high speed and accuracy.");
        System.out.println("  -> Type \"start\" and the typing test will begin after the countdown.\n");
        System.out.println("  ***********************************\n");
    }

    private static void startCountdown() {
        int input = 3;
        System.out.println("Get ready...");
        for (int i = input; i >= 1; i--) {
            System.out.println(i + "...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Thread was interrupted!");
            }
        }
        System.out.println("Start typing now!\n");
    }

    private static void performAction() {
        String question = "Tony Stark, also known as Iron Man, is a fictional character from the Marvel Cinematic Universe (MCU) and Marvel Comics. In the MCU, he is portrayed by Robert Downey Jr. and first appeared in the film "Iron Man" in 2008.";
        System.out.println("Type the following:\n");
        System.out.println(question + "\n");

        long start = System.currentTimeMillis();
        Scanner ans = new Scanner(System.in);
        String userAnswer = ans.nextLine().trim();
        long end = System.currentTimeMillis();

        if (userAnswer.isEmpty()) {
            System.out.println("No input detected! Try again.");
            ans.close();
            return;
        }

        ans.close();

        String[] questionArray = question.trim().split("\\s+");
        String[] userArray = userAnswer.trim().split("\\s+");

        int correctCount = 0;
        ArrayList<String> wrongWords = new ArrayList<>();

        for (int i = 0; i < Math.min(questionArray.length, userArray.length); i++) {
            if (questionArray[i].equals(userArray[i])) {
                correctCount++;
            } else {
                wrongWords.add(questionArray[i]);
            }
        }

        for (int i = questionArray.length; i < userArray.length; i++) {
            wrongWords.add(userArray[i]);
        }

        int wrongCount = wrongWords.size();
        long time = (end - start) / 1000;

        double typeSpeed = time == 0 ? 0 : Math.round((correctCount / (double) time) * 60);

        printSummary(time, correctCount, wrongCount, typeSpeed, wrongWords);
    }

    private static void printSummary(long time, int correctCount, int wrongCount, double typeSpeed, ArrayList<String> wrongWords) {
        System.out.println("\n===== TYPING SUMMARY =====");
        System.out.println("Total Time: " + time + " sec");
        System.out.println("Correct Words: " + correctCount);
        System.out.println("Incorrect Words: " + wrongCount);
        System.out.println("Typing Speed: " + typeSpeed + " WPM");

        if (!wrongWords.isEmpty()) {
            System.out.println("Wrong Words: " + String.join(", ", wrongWords));
        } else {
            System.out.println("No wrong words! Excellent job!");
        }

        System.out.println("===========================\n");
    }
}
