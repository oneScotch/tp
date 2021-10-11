package data.game;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class QuizGame extends Game {

    public static String MCQName = "multiple-choice question";
    public static String MRQName = "multiple-response question";
    public static String TFQName = " true-false question";
    public static String FillInBlankName = "fill-in-the-blanks question";
    public static final Random RANDOM = new Random();
    public static final int NumberOfQuestions = 4;
    public static String[] MCQ = {"Who is the person that writes code and communicates instructions to a computer? "
            + "\n[A: Code][B: Programmer][C: Command][D: Algorithm]",
        "What is the action of finding and fixing errors or mistakes in programs? "
            + "\n[A: Looping][B: Sequencing][C: Debugging][D: Decomposing]",
        "What is an error or mistake called in a program? "
            + "\n[A: Bug][B: Program][C: Code][D: Loop]",
        "Who invented Java language? \n[A: Jackie Chan][B: Linus Torvalds][C: Fabrice Bellard][D: James Gosling]",
        "Of the following, if statements, which one returns true?"
            + "\n[A: X = 1; X > 0;][B: X = \"hi\"; X == \"hello\";][C: X = 0; X == 1;][D: X = \"k\"; X == 1]"};
    public static String[] MCQAnswers = {"B", "C", "A", "D", "A"};

    public static String[] MRQ = {"Which will the following statement return true?"
            + "\n[A: 1 == 1][B: \"abc\" == \"bcd\"][C: \"Hello\" == \"hello\"][D: \"Hello\" == \"hello\"]",
        "Choose are programming languages.\n[A: Python][B: Chinese][C: Java][D: C-+]",
        "Choose all the correct codes. \n[A: 1 = x;[B: x++;][C: return x;][D: return y]",
        "Choose correct statements which satisfy Java coding style."
            + "\n[A: x=1][B: int a[] = new int[20];][C: int[] a = new int[20];][D: x = 2]",
        "Choose all Java Language Keywords. \n[A: continue][B: final][C: hello][D: private]"};
    public static String[] MRQAnswers = {"AD", "AC", "BC", "CD", "ABD"};

    public static String[] TFQ = {"Is \"R\" a programming language? \n[True][False]",
        "Can \"for\" and \"while\" both be used to form a loop? \n[True][False]",
        "\"do while\" may not necessarily be executed. \n[True][False]",
        "\"hello\" is a Java Language Keywords. \n[True][False]"};
    public static String[] TFQAnswers = {"True", "True", "False", "False"};

    public static String[] FIB = {"What is loop that can loop endlessly?",
        "Which integer number represents false in Java?",
        "______ is the process of finding errors and fixing them within a program.",
        "______ defines the common variables and methods of a set of objects.",
        "If A=10, then after B=++A, the value of B is _______."};
    public static String[] FIBAnswers = {"infinite loop", "0", "debugging", "class", "11"};

    public static int NumOfCorrect = 0;
    public static int NumOfUserAnswer = 0;

    @Override
    public void execute() {
        while(NumOfCorrect < 4) {
            System.out.println(NumOfUserAnswer);
            switch (NumOfUserAnswer % 4) {
            case 0:
                getQuestion(MCQ, MCQAnswers);
                break;
            case 1:
                getQuestion(MRQ, MRQAnswers);
                break;
            case 2:
                getQuestion(TFQ, TFQAnswers);
                break;
            case 3:
                getQuestion(FIB, FIBAnswers);
                break;
            default:
                System.out.println("Error");
            }
            if (NumOfUserAnswer == 4 && NumOfCorrect == 4) {
                System.out.println("Good! You achieve full mark.\n");
                break;
            } else if (NumOfUserAnswer == 4 && NumOfCorrect < 4) {
                System.out.println("Sorry! You did pass this challenge. Please try angin.\n");
                NumOfCorrect = 0;
                NumOfUserAnswer = 0;
            }
        }
        System.out.println("end");
    }

    public void getQuestion(String[] questions, String[] answers) {
        int index = chooseQuestion(questions);
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        if (answer.equals(answers[index])) {
            System.out.println("Congratulations! The answer is correct!\n");
            NumOfCorrect++;
        } else {
            System.out.println("Oops! The answer is not correct.\n");
        }
        NumOfUserAnswer++;
    }

    public int chooseQuestion(String[] questions) {
        int index = RANDOM.nextInt(questions.length);
        System.out.println(questions[index]);
        return index;
    }

    @Override
    public String getName() {
        return "QuizGame";
    }
}

