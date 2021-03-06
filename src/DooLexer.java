
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

 TOKENIZER OF DOOLANG

*/


public class DooLexer {
    

   ////////////////////  GLOBAL VARIABLES AND CONFIGS.  //////////////////////////


    static int[] constants = new int[20];
    static int tnum = 0;
    static int l =-1;

    static ArrayList<String> identifiers = new ArrayList<>();

    // for alpha conditions
    static int row = 0;
    static int column = -1;
    static char[][] variables = new char[20][20];
    static int[] lastpos = new int[10];
    static int lp_n = 0; // holds last pos of column number in isAlpha condition

    // for oprator ,seperator , puntuators conditions ;
    static char[] operators = new char[20];
    static int o_i = -1; // operator count

    static char[] seperator = new char[20];
    static int s_i = -1; // seperator count

    static char[] punctuators = new char[20];
    static int p_i = -1; // punctuators count

    //////////////////// GLOBAL VARIABLES AND CONFIGS.///////////////////////

    // PRESEVED KEYS
    // static String[] keywords = {"frd","bkd","rt","lt"};
    static ArrayList<String> keywords = new ArrayList<>();
    static List<String> preserved_keywords = new ArrayList<>() {
        {
            add("frd");
            add("bkd");
            add("rt");
            add("lt");
            add("doo");

        }
    };

    static List<Character> OPERATORS = new ArrayList<>() {
        {
            add('+');
            add('-');
            add('/');
            add('=');
            add('*');

        }
    };

    static List<Character> SEPERATORS = new ArrayList<Character>() {
        {
            add('{');
            add('}');
            add('(');
            add(')');
            add('[');
            add(']');
            add(',');

        }
    };

    static List<Character> PUNCTUATORS = new ArrayList<Character>() {
        {
            add(':');
            add(';');
            add('.');
        }
    };

    DooLexer() {
        // Empty construtor;
    }

    /////////////////////////////////////// Lexer ///////////////////

    void init_lexer(String source) {

        String expr[] = source.split("");
        print("<< Tokens :" + Arrays.toString(expr));
        int n = expr.length;
        ArrayList<Integer> ascii = new ArrayList<>();

        for (String s : expr) {
            ascii.add((int) s.charAt(0));
        }

        // print(ascii.toString());

        /////////////////////////////////////// Lexical Analysis/////////////////

        for (int k = 0; k < n; ++k) {

            /////////////////// Checking if ascii is digit////////////

            // char curr_char = (expr[k]).charAt(0);
            // System.out.println(k);

            if (Character.isDigit(expr[k].charAt(0))) {
                // print(expr[i]+" is a digit");
                while (Character.isDigit(expr[k].charAt(0))) {
                    tnum = 10 * tnum + ascii.get(k) - '0';
                    k++;
                }
                l++;
                constants[l] = tnum;
                tnum = 0;
            }

            /////////////////// Checking if ascii is digit////////////

            /////////// Checking if element is seperator //////////////////////////////

            if (SEPERATORS.contains(expr[k].charAt(0))) {
                s_i++;
                seperator[s_i] = expr[k].charAt(0);

            }

            /////////// Checking if ascii is SEPERATOR //////////////////////////////

            /////////// Checking if element is punctuators //////////////////////////////

            if (PUNCTUATORS.contains(expr[k].charAt(0))) {
                p_i++;
                punctuators[p_i] = expr[k].charAt(0);

            }

            /////////// Checking if ascii is SEPERATOR //////////////////////////////

            //////////////////////////// Checking if ascii is alphabet////////////

            if (Character.isLetter(expr[k].charAt(0))) {

                while (Character.isLetter(expr[k].charAt(0)) || Character.isDigit(expr[k].charAt(0))
                        || expr[k].charAt(0) == '_') {
                    column++; // column number increases in every iteration
                    variables[row][column] = expr[k].charAt(0);
                    k++;
                }
                row++;
                lastpos[lp_n] = column; // save last position of column number
                lp_n++;
                column = -1;
            }

            //////////////////////////// Checking if ascii is alphabet//////////

            /////////// Checking if ascii is Operator //////////////////////////////
            if (OPERATORS.contains(expr[k].charAt(0))) {
                o_i++;
                operators[o_i] = expr[k].charAt(0);
            }
        }
        /////////// Checking if ascii is Operator //////////////////////////////

        ///////////////////////////////////////// converting 2d array to 1-D for
        ///////////////////////////////////////// identifiers;

        int lp = 0;
        n = lastpos.length;

        StringBuffer t_id = new StringBuffer();

        for (int i = 0; i < 20; ++i) {

            if (lp < lastpos.length) {
                for (int j = 0; j < (lastpos[lp] + 1); j++) {

                    t_id.append(variables[i][j]);

                }
                lp++;
                if (!preserved_keywords.contains(String.valueOf(t_id))) {
                    identifiers.add(String.valueOf(t_id));
                    t_id.delete(0, t_id.length());
                }else{
                    keywords.add(String.valueOf(t_id));
                    t_id.delete(0, t_id.length());
                    }

                }

        }



     /////////////////////////////////////////converting 2d array to 1-D for identifiers



      
    }






        void _printTokens(){

        System.out.println("<< Classification :\n\n");        
        System.out.println("<< Constants :"+Arrays.toString(constants));

        System.out.println("<< Operators :"+Arrays.toString(operators));

        System.out.println("<< Seperator :"+Arrays.toString(seperator));

        System.out.println("<< Punctuators :"+Arrays.toString(punctuators));

        System.out.println("<< Identifiers :"+identifiers);

        System.out.println("<< Keywords :" + keywords);

       
    
    }




    static void print(String inp){
        System.out.println(inp);

    }

}
