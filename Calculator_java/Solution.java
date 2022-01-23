import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Solution{
    
    String input;

    public Solution(String input) {
        this.input = input;
    }
 
    public String Calculate() throws ScriptException {

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        ArrayList<String> values = new ArrayList<String>();
        String signs = "+-*/";
        String superScript2 = "²";
        String superScript3 = "³";
        String squareroot = "√";
        String updatedInput = "";

        //checking for syntax errors
        //dividing the eqaution into categories to make it to be simplified and solved by a script engine
        int index = 0;
        int index2 = -1;
        for (int i = 0; i < input.length() -1 ; i ++) {
            char a = input.charAt(i);
            if (signs.contains(String.valueOf(input.charAt(i))) && signs.contains(String.valueOf(input.charAt(i+1)))) {
                return "SYNTAX ERROR";
            }
            if (signs.contains(String.valueOf(input.charAt(i)))) {
                if (i == 0 || input.charAt(i-1) != '^' ) {
                    values.add(input.substring(index2+1, i));
                    index2 = i;
                    values.add(String.valueOf(input.charAt(i)));
                    index = i;
                }
                
            }
        }
        if (values.size() ==0) {
            values.add(input);
        }
        else {
            values.add(input.substring(index+1, input.length()));
        }

        //replacing the groups of numbers/equation separated in a list to how the java would solve it. so 2^2 would be solved by java by 
        //Math.pow(2,2)
        for (int z = 0; z < values.size(); z++) {
            if (values.get(z).contains("ANS")) {
                String val = values.get(z);
                int ind = 0;
                for (int i = 0; i < val.length(); i ++ ) {
                    if (val.charAt(i) =='A') ind = i;
                }
                values.set(z, val.substring(0, ind) + Calculator.textbox2.getText() + val.substring(ind + 3));
                
            }
            if (values.get(z).contains("π")) {
                String val = values.get(z);
                values.set(z, "Math.PI" + val.substring(1,val.length()));
                
            }
            if (values.get(z).contains(superScript2)) {
                String val = values.get(z);
                values.set(z, "Math.pow(" + val.substring(0, val.length()-1) + ",2)");
                
            }
            if (values.get(z).contains(superScript3)) {
                String val = values.get(z);
                values.set(z, "Math.pow(" + val.substring(0, val.length()-1) + ",3)");
               
            }
            if (values.get(z).contains(squareroot)) {
                String val = values.get(z);
                values.set(z, "Math.sqrt(" + val.substring(1, val.length()) + ")");
                
            }
            if (values.get(z).contains("^")) {
                String val = values.get(z);
                int t = 0;
                for (int h = 0; h < val.length(); h ++) if (val.charAt(h) == '^') t = h;
                values.set(z, "Math.pow(" + val.substring(0, t) + "," + val.substring(t+1, val.length()) +")");
                
            }       
        }

        //putting all the updated varaibles into a string which would be used in a script engine to let java solve the equation
        for (String n: values) updatedInput +=n;

        String result = "";

        //using cript engine to turn the string into an input to be solved by java. Formatting of the answer is also dont in thi try catch block
        try {
            result = String.valueOf(engine.eval(updatedInput));
            
            if(Double.valueOf(String.valueOf(engine.eval(updatedInput))) == Math.round(Double.valueOf(String.valueOf(engine.eval(updatedInput)))))
            result = String.valueOf(Math.round(Double.valueOf(String.valueOf(engine.eval(updatedInput)))));
            else if (result.length() > 8) {
               NumberFormat numFormat = new DecimalFormat("0.###########E0");
               result = String.valueOf(numFormat.format(Double.valueOf(result)));
            }
            else result = result;
        }
        catch (ScriptException e) {
            return "ERROR";        
        }
        //returns the result
        return result;


    }


}
