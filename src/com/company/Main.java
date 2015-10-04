package com.company;


/**
 * –абота с регул€рными выражени€ми
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static final String TEXT = "oleg.daskevych@gmail.com";

    private static String sentenceReg = "(([A-Z]{1}[a-z]*)(\\s([0-9]*\\s)*[a-zA-Z]*)*\\.\\s)+";

    private static String emailReg = "([\\w+[\\-_]?]+[\\.]?[\\w[\\-_]?]*)@([\\w]+)(\\.[\\w]{2,})+";
                            //

    private static ArrayList<String[]> validatedEmails = new ArrayList<>();

    public static void checkSentence() {
//        Scanner scanner;
//        try {
//            scanner = new Scanner(new File("C:\\Users\\Oleg\\EclipseProjects\\RegExpressionsExec\\src\\com\\company\\regular_exp"));
//           // while (scanner.hasNextLine());{
//                sentenceReg = scanner.nextLine();
//            //}
//        }catch (IOException e){
//            e.getCause().getLocalizedMessage();
//        }
        Pattern pattern = Pattern.compile(sentenceReg);
        Matcher matcher = pattern.matcher(TEXT);
        System.out.println(matcher.matches());
        while (matcher.find()){
            System.out.println();
        }
    }

    private static boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile(emailReg);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static String[] operateEmail(String email) {
        Pattern pattern = Pattern.compile(emailReg);
        Matcher matcher = pattern.matcher(email);
        String[] pair = new String[2];
        matcher.find();
        pair[0] = matcher.group(1);
        pair[1] = matcher.group(2) + matcher.group(3);
        return pair;
    }

    public static int addEmails(String[] emails) {
        int result = 0;
        for (String email : emails) {
            if (checkEmail(email)) {
                validatedEmails.add(operateEmail(email));
                result++;
            }
        }
        return result;
    }

    private static void output() {
        for (String[] email : validatedEmails) {
            System.out.printf("nickname: %s; domain: %s;\n", email[0], email[1]);
        }
    }

    public static String[] input() {
        Scanner scanner = new Scanner(System.in);
        String enteredStr = scanner.nextLine();
        scanner.close();
        String[] sentences = enteredStr.split("(, )|( )+|(\\.$)");
        return sentences;
    }

    private static void startEmail() {
        System.out.println("Enter Emails between comma:");
        String[] res = input();
        if (res.length != 0) {
            System.out.printf("%d emails were added.\n", addEmails(res));
            output();
        } else System.out.println("Error: no elements in array.");
    }

    private static void searchSubstring(String regExp, String text) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    private static void test1() {
        // ограничители строк
        System.out.println("ќграничители строк");
        searchSubstring("(\\A\\w*\\b\\z)", "first\n");
        searchSubstring("(\\A\\w*\\b\\Z)", "second\n"); // учитывает ограничители строк

        //квантификаторы
        System.out.println("\n вантификаторы");
     /*   System.out.println("~~~~~~~~~~~~~\n жадный один или ноль");
        searchSubstring("(ab?)", "aabcabbb\n");
        
        System.out.println("~~~~~~~~~~~~~ \n ленивый один или ноль");
        searchSubstring("(ab??)", "aabcabbb");
      */
        System.out.println("~~~~~~~~~~~~~\n жадный ноли или более раз");
        searchSubstring("(ab*)", "aabcabbb\n");
        
        System.out.println("~~~~~~~~~~~~~\n ленивый ноли или более раз");
        searchSubstring("(ab*?)", "aabcabbb");
        /*
        System.out.println("~~~~~~~~~~~~~\n жадный один или более раз");
        searchSubstring("(ab+)", "aabcabbb");
        
        System.out.println("~~~~~~~~~~~~~\n ленивый один или более раз");
        searchSubstring("(ab+?)", "aabcabbb");
        
        System.out.println("~~~~~~~~~~~~~\n жадный >=N раз");
        searchSubstring("(ab{2,})", "aabcabbb");
        
        System.out.println("~~~~~~~~~~~~~\n ленивый >=N раз");
        searchSubstring("(ab{2,}?)", "aabcabbb");
        
        System.out.println("~~~~~~~~~~~~~\n сверхжадный");
        searchSubstring("(a++)", "aabcabbbaf");
        
        System.out.println("~~~~~~~~~~~~~\n ”преждающий просмотр вперед позитивный");
        searchSubstring("a+(?=b)", "aabcabbbaaaaf");
        
        System.out.println("~~~~~~~~~~~~~\n ”преждающий просмотр вперед негативный");
        searchSubstring("a+(?!b)", "aabcabbbaaaaf");
        
        System.out.println("~~~~~~~~~~~~~\n ”преждающий просмотр назад позитивный");
        searchSubstring("(?<=b)A+", "aabcabbbaaaaf");
        
        System.out.println("~~~~~~~~~~~~~\n ”преждающий просмотр назад негативный");
        searchSubstring("(?i)(?<!b)a+", "aabcabbbaaaaf"); // кроме того CASE_INSENSITIVE
*/    }

    private static String[] separateWords(String text) {
        Pattern p1 = Pattern.compile("([^A-Za-z]+)");
        String[] words = p1.split(text);
        return words;
    }

    private static String replaceLetter(int k, char letter, String text) throws Exception {
        if (k < 0) throw new Exception("k must be not negative number.");
        Pattern p1 = Pattern.compile("(\\w)+");
        Matcher matcher = p1.matcher(text);
        String resultText = text;
        while (matcher.find()) {
            String word = matcher.group();
            if (word.length() <= k) continue;
            char[] wordChars = word.toCharArray();
            wordChars[k] = letter;
            word = String.valueOf(wordChars);
            resultText = resultText.replaceFirst(matcher.group(), word);
        }
        return resultText;
    }

    private static void task1() {
        try {
            String result = replaceLetter(-4, 'F', "abcds lfdl--fd   hjjdsss! ");
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
/*

    private static String getRussianAlphabetAt(String text){

    }
*/

    public static void main(String[] args) {
    	task1();
//    	test1();
//    	startEmail();
//    	separateWords("Hi my dear  friend2Oleg!");

    }
}
