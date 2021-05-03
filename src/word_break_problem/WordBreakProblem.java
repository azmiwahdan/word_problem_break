package word_break_problem;
//Azmi Wahdan
//BirZeit University

import java.util.ArrayList;

import java.util.Stack;

public class WordBreakProblem {
    private ArrayList<String> result = new ArrayList<>();
    private Stack<String> stack = new Stack<>();

    // get dynamic table
    private int[][] getDynamicTable(ArrayList<String> dic, String str) {
        int l = str.length();
        int[][] dt = new int[l][l];

        for (int i = 0; i < l; i++) {
            for (int j = i; j < l; j++) {
                if (dic.contains(str.substring(i, j + 1))) {
                    dt[i][j] = 1;

                } else {
                    dt[i][j] = 0;
                }
            }
        }

        return dt;
    }

    // print dynamic table of word break problem

    public String printDynamicTable(ArrayList<String> dic, String str) {
        String res = "";
        str = str.trim();
        if (str.length() == 0 && dic.isEmpty()) {
            return "Error:Enter required data!!,try again";

        } else if (str.length() == 0) {
            return "please enter your word!!,try again";
        } else if (dic.isEmpty()) {
            return "please enter your dictionary!!,try again ";

        } else {
            int[][] dt = getDynamicTable(dic, str);

            for (int i = 0; i < dt.length; i++) {
                for (int j = 0; j < dt[i].length; j++) {
                    res += dt[i][j] + "  ";
                }
                res += "\n";
            }
        }

        return "Dynamic table:\n" + res;
    }

    // get output of all possibilities of word ,in list
    private ArrayList<String> getAllpossibilities(int length, String str, int[][] dp, boolean isOptimal) {

        if (length == -1) {
            String words = "";
            int i = stack.size() - 1;

            while (i >= 0) {
                words += stack.get(i) + "   ";
                i--;
            }

            result.add(words);
        } else {

            for (int j = 0; j <= length; j++) {
                if (dp[j][length] == 1) {
                    stack.push(str.substring(j, length + 1));// optimal
                    getAllpossibilities(j - 1, str, dp, isOptimal);
                    stack.pop();
                    if (isOptimal) {
                        break;
                    }
                }

            }

        }

        return result;
    }

    public String printResults(String str, ArrayList<String> dic, boolean isOptimal) {
        str = str.trim();
        String statment = "";

        if (str.length() == 0 && dic.isEmpty()) {
            return "Error:Enter required data!!,try again";

        } else if (str.length() == 0) {
            return "please enter your word!!,try again";
        } else if (dic.isEmpty()) {
            return "please enter your dictionary!!,try again ";

        } else {
            int[][] dt = getDynamicTable(dic, str);
            ArrayList<String> results = new ArrayList<>();

            results = getAllpossibilities(str.length() - 1, str, dt, isOptimal);
            if (results.isEmpty())
                return "word can't be segmented from dictionary";
            for (int i = 0; i < results.size(); i++) {
                statment += (i + 1) + " : " + results.get(i) + "\n";
            }

            results.clear();
        }

        return statment;
    }

}
