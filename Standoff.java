import java.util.Scanner;

class Standoff {
    static String player1, player2;
    Scanner sc = new Scanner(System.in);

    void Welcome() {
        //printing rules and inputting names
        System.out.println("\nQuiz Standoff!\n");
        System.out.println("- Each player will create a quiz of 3 questions twice and the other player will have to answer them.");
        System.out.println("- For each correct answer the score increases by 5 and for each wrong answer the score reduces by 2.");
        System.out.println("- The player with highest score at the end wins");
        System.out.println("\nGood luck to both players!");        
        System.out.print("\nName of player 1: ");
        player1 = sc.next();
        System.out.print("Name of player 2: ");
        player2 = sc.next();
        System.out.println("\n*\t*\t*");
    }    

    String[][] Questions (String name) {
        System.out.println("\n" + name + "'s turn to create questions. \n");
        sc.nextLine();
        String[][] quiz = new String[5][3];
        //storing questions along with options
        for (int i = 0; i < 3; i++) {
            System.out.print("Question number " + (i+1) + ": ");
            quiz[0][i] = sc.nextLine();
            System.out.print("Correct answer: ");    
            quiz[1][i] = sc.nextLine();
            for (int j = 0; j < 3; j++) {
                System.out.print("Incorret option: ");
                quiz[i+2][j] = sc.nextLine();
            }
            System.out.println();
        }
        System.out.println("\n*\t*\t*\n");
        return quiz; 
    }

    int Quiz (String[][] quiz, String name) {
        int score = 0;
        String[] options = new String[4];
        System.out.println(name + "'s turn to answer.\n");
        
        for (int i = 0; i < 3; i++) {
            System.out.println("Question " + (i+1) + ") " + quiz[0][i] + " ?\n");
            //randomizing options
            for (int j = 0; j < 4; j++) {
                int answerIndex = (int) Math.floor(Math.random() * 4);       
                for (int k = 0, c = 0; k < 4; k++) {
                    if (answerIndex == k) {
                        options[k] = quiz[1][i];
                        continue;
                    }
                    options[k] = quiz[i+2][c++];           
                }
            }
            //printing options
            for (int l = 0; l < 4; l++) {
                System.out.println("(" + (l+1) +") " + options[l]);
            }
            System.out.print("\nSelect option: ");
            int ans = sc.next().charAt(0) - 49;
            System.out.println();
            
            if (ans < 0 || ans > 3 ) {
                System.out.println(name + " entered invalid option... 3 points deducted! \n");
                score -=3;
                continue;
            }
            if (options[ans].equals(quiz[1][i])) score += 5;
            else score -= 2;
        }
        System.out.println("*\t*\t*");
        return score;
    }

    public static void main(String[] args) {
        Standoff ob = new Standoff();
        ob.Welcome();
        int finalScoreP1 = 0, finalScoreP2 = 0;
        //creating rounds for each player
        for (int i = 1; i <= 4; i++) {
            if (i%2 != 0) {
                String[][] questions = ob.Questions(Standoff.player1);
                finalScoreP2 += ob.Quiz(questions, Standoff.player2);
            } else {
                String[][] questions = ob.Questions(Standoff.player2);
                finalScoreP1 += ob.Quiz(questions, Standoff.player1);
            }
        }
        //declaring scores and the winner
        System.out.println("\n" + Standoff.player1 + ": " + finalScoreP1);
        System.out.println(Standoff.player2 + ": " + finalScoreP2 + "\n");

        if (finalScoreP1 > finalScoreP2) System.out.println(Standoff.player1 + " wins by " + (finalScoreP1 - finalScoreP2) + " points!");
        else if (finalScoreP1 < finalScoreP2) System.out.println(Standoff.player2 + " wins by " + (finalScoreP2 - finalScoreP1) + " points!");
        else System.out.println("A tie between " + player1 + " and " + player2);
    }
}

/*
 * @Author Abismar
 * @Github https://github.com/sleepingzzs/QuizStandoff
 * @Description Text based game to create quizes and duel with your friends! Similar to Kahoot
 */