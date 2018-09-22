// ((a+cd)(ah+cd')) giving exception
package truthTable;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author hp
 */
public class Table {

     private String booleanEquation;
     ArrayList<Character> alphabet = new ArrayList<>();
     ArrayList<String> tableValue = new ArrayList<>();

    public Table(String booleanEquation) {
        this.booleanEquation = booleanEquation;
        //System.out.println(".." + booleanEquation);
    }

    public static void main(String a[]) {
        //System.out.println(orGate("(0+0+0)"));
        //System.out.println(bracket(orGate(andGate(notGate("((1+11)(01'+1'0'))")))));
        //System.out.println(variableCounter("((acdb)+(cabd+abcd))"));
//        ArrayList<String> value=combinations(5);
//        for(String s: value)
//        {
//            System.out.println(s);
//        }

//        ArrayList<Character> c=variables("((acxdb)+(cabd+abcdxx))");
//        //Collections.sort(c);
//        for(char aa: c){
//             
//             System.out.println(aa);
//        }
        //ArrayList<String> av = truthTableValue("((ab+cd)'(ab+cd')')");
//        ArrayList<String> av = truthTableValue("((a+cd)(ah+cd'))");
//        for (String s : av) {
//            System.out.println(s);
//        }

    }

     String notGate(String equation) {
        int back = 0, forth = 0, length = equation.length();
        String p1, p2 = "", p3, result = "";
        for (int i = 0; i < length; i++) {

            if (String.valueOf(equation.charAt(i)).equals("'")) {
                if (String.valueOf(equation.charAt(i - 1)).equals("1")) {
                    back = i - 1;
                    forth = i;
                    p2 = "0";
                } else if (String.valueOf(equation.charAt(i - 1)).equals("0")) {
                    back = i - 1;
                    forth = i;
                    p2 = "1";
                } else {
                    continue;
                }
                result = p2;
                if (back != 0) {
                    p1 = equation.substring(0, back);
                    result = p1 + result;
                }
                if (forth != length) {
                    p3 = equation.substring(forth + 1, length);
                    result = result + p3;
                }
                equation = result;
                length = equation.length();
            }
            //equation=result;
        }
        //System.out.println("Not ...."+equation);
        return equation;
    }

     String orGate(String equation) {
        int back = 0, forth = 0, length = equation.length(), c = 0;
        String p1, p2 = "", p3, p4, result = "";
        for (int i = 0; i < length; i++) {

            if (String.valueOf(equation.charAt(i)).equals("+")&&((String.valueOf(equation.charAt(i-1)).equals("1"))||(String.valueOf(equation.charAt(i-1)).equals("0")))&&((String.valueOf(equation.charAt(i+1)).equals("1"))||(String.valueOf(equation.charAt(i+1)).equals("0")))) {
                //the condition check if the "+" is between two  0/1
                if((String.valueOf(equation.charAt(i-2)).equals("(")||String.valueOf(equation.charAt(i-2)).equals("+"))&&(String.valueOf(equation.charAt(i+2)).equals(")")||String.valueOf(equation.charAt(i+2)).equals("+"))){
                c = 1;

                back = i - 1;
                forth = i + 1;
                p2 = equation.substring(back, i);
                p3 = equation.substring(i + 1, forth + 1);
                if (p2.equals("1") || p3.equals("1")) {
                    result = "1";
                } else {
                    result = "0";
                }

                if (back != 0) {
                    p1 = equation.substring(0, back);
                    result = p1 + result;
                }
                if (forth != length) {
                    p4 = equation.substring(forth + 1, length);
                    result = result + p4;
                }
                equation = result;
                length = equation.length();
                
                
                //System.out.println("or...step"+equation);
            }
            }
            

        }
        if (c == 1) {
            equation = orGate(equation);
        }
        return equation;
    }

     String andGate(String equation) {
        int back = 0, forth = 0, length = equation.length(), check = 0, c = 0;
        String p1, p2 = "", p3, p4, result = "";
        for (int i = 0; i < length; i++) {
            if (String.valueOf(equation.charAt(i)).equals("1") || String.valueOf(equation.charAt(i)).equals("0")) {
                if (i != 0) {
                    if (String.valueOf(equation.charAt(i - 1)).equals("1") || String.valueOf(equation.charAt(i - 1)).equals("0")) {

                        check++;
                    }
                }
            }

            if (check == 1) {
                c = 1;
                check = 0;

                back = i - 1;
                forth = i;
                p2 = equation.substring(back, i);
                p3 = equation.substring(i, forth + 1);
                if (p2.equals("1") && p3.equals("1")) {
                    result = "1";
                } else {
                    result = "0";
                }

                if (back != 0) {
                    p1 = equation.substring(0, back);
                    result = p1 + result;
                }
                if (forth != length) {
                    p4 = equation.substring(forth + 1, length);
                    result = result + p4;
                }
                equation = result;
                length = equation.length();

            }

        }
        if (c == 1) {
            equation = andGate(equation);
        }
        return equation;
    }

     String bracket(String equation) {
        int back = 0, forth = 0, length = equation.length(), check = 0, c = 0;
        String p1, p2 = "", p3 = "", result = "";
        for (int i = 0; i < length; i++) {
            if (String.valueOf(equation.charAt(i)).equals("1") || String.valueOf(equation.charAt(i)).equals("0")) {
                if (i != 0 && i != length) {
                    if (String.valueOf(equation.charAt(i - 1)).equals("(") && String.valueOf(equation.charAt(i + 1)).equals(")")) {

                        check++;
                    }
                }
            }

            if (check == 1) {
                c = 1;
                check = 0;

                back = i;
                forth = i + 1;
                p2 = equation.substring(back, forth);
                result = p2;

                if (back - 1 != 0) {
                    p1 = equation.substring(0, back - 1);
                    result = p1 + result;
                }
                if (forth + 1 != length) {
                    p3 = equation.substring(forth + 1, length);
                    result = result + p3;
                }
                equation = result;
                length = equation.length();

            }

        }
        if (c == 1) {
            equation = bracket(equation);
        }
        return equation;
    }

     ArrayList<String> combinations(int numberOfVariable) {
        //System.out.println("gggg");
        //ArrayList<String> tableValue = new ArrayList<>();
        //System.out.println("nnnnnn");
        for (int i = numberOfVariable - 1; i >= 0; i--) {
            //System.out.println("looppppppppppp..........");
            String binaryValue = "0";
            int loopSize = (int) Math.pow(2, numberOfVariable);
            for (int j = 0; j < loopSize; j++) {
                //System.out.println("...........looppppppppppp");
                if (i == numberOfVariable - 1) {
                    tableValue.add(binaryValue);
                    //System.out.println("pow"+(j+1)%(int)Math.pow(2, i));

                } else {
                    String d = tableValue.get(j) + binaryValue;
                    //System.out.println("value d:...."+d);
                    tableValue.set(j, d);

                }
                if ((j + 1) % (int) Math.pow(2, i) == 0) {
                    // System.out.println("ttttttttt");
                    if (binaryValue.equals("0")) {
                        binaryValue = "1";
                    } else {
                        binaryValue = "0";
                    }
                }
            }
        }

        return tableValue;

    }

     ArrayList<Character> variables(String booleanEquation) {
        int length = booleanEquation.length(), check = 0;
        
        //char[] alphabet=new char[length];
        for (int i = 0; i < length; i++) {
            int j = 0;
            char temp = booleanEquation.charAt(i);
            if (String.valueOf(temp).compareTo("+") != 0 && String.valueOf(temp).compareTo("(") != 0 && String.valueOf(temp).compareTo(")") != 0 && String.valueOf(temp).compareTo("'") != 0) {
                for (char a : alphabet) {
                    if (a == temp) {
                        check = 1;
                    }
                    j++;
                }
                if (check == 0) {
                    alphabet.add(temp);
                }
                check = 0;
            }
        }
        int count = 0;
        for (char a : alphabet) {
            count++;
            //System.out.println(count+" "+a);
        }
        Collections.sort(alphabet);
        return alphabet;
    }

     ArrayList<String> truthTableValue() {
        ArrayList<String> values = new ArrayList<String>();//store the truth table output

        ArrayList<Character> c = variables(booleanEquation);// store the variable

        int variableNumber = c.size();//store number of variable

        ArrayList<String> combinationValues = combinations(variableNumber);//store input combinations
        for (String combination : combinationValues) {
            //System.out.println("combination.........  " + combination);
            String v = booleanEquation;
            char[] vChars = v.toCharArray();
            int i = 0;
            for (char value : vChars) {
                //System.out.println("previous val" + value);
                //System.out.println("eqnt...........value.........  " + value);
                int j = 0;
                for (char b : c) {
                    //System.out.println("variable..... b.........  " + b);

                    if (value == b) {
                        //System.out.println("match ....variable..... b.........  "+b);
                        vChars[i] = combination.charAt(j);
                        //System.out.println("replace ....value..... b.........  "+vChars[i]);

                    }
                    j++;
                }
                //System.out.println("vchar " + vChars[i]);
//                v=String.valueOf(vChars);
                //System.out.println(v);
                i++;
            }

            String v1 = String.valueOf(vChars);
            //System.out.println("......" + v1);
//            for(char bb:vChars)
//             {
//                 System.out.println("vChar................."+bb);
//             }
            do {
                v1 = notGate(v1);
                //System.out.println("not ..v1" + v1);
                v1 = andGate(v1);
                //System.out.println("and ...v1" + v1);
                v1 = orGate(v1);
                //System.out.println("or....v1" + v1);
                v1 = bracket(v1);
                //System.out.println("v1" + v1);

            } while (v1.length() > 1);
            //System.out.println(v1);
            values.add(v1);
        }

        return values;

    }

}
