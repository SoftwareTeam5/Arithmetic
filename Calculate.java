import java.util.ArrayList;  
import java.util.Stack;  
  
public class Calculate {
	
    public ArrayList<String> getStringList(String str){  
        ArrayList<String> result = new ArrayList<String>();  
        String num = "";  
        for (int i = 0; i < str.length(); i++) {  
            if(Character.isDigit(str.charAt(i))){  
                num = num + str.charAt(i);  
            }else{  
                if(num != "") {  
                    result.add(num);  
                }  
                result.add(str.charAt(i) + "");  
                num = "";  
            }
        }
        if(num != "") {
            result.add(num);
        }  
        return result;  
    }  
    /** 
     * 将中缀表达式转化为后缀表达式 
     */  
    public ArrayList<String> getPostOrder(ArrayList<String> inOrderList) {
        ArrayList<String> result = new ArrayList<String>();  
        Stack<String> stack = new Stack<String>();  
        for (int i = 0; i < inOrderList.size(); i++) {  
            if(Character.isDigit(inOrderList.get(i).charAt(0))){  
                result.add(inOrderList.get(i));  
            }else{  
                switch (inOrderList.get(i).charAt(0)) {  
                case '(':  
                    stack.push(inOrderList.get(i));  
                    break;  
                case ')':  
                    while (!stack.peek().equals("(")) {  
                        result.add(stack.pop());  
                    }  
                    stack.pop();  
                    break;  
                default:  
                    while (!stack.isEmpty() && compare(stack.peek(), inOrderList.get(i))){  
                        result.add(stack.pop());  
                    }  
                    stack.push(inOrderList.get(i));  
                    break;  
                }  
            }  
        }  
        while(!stack.isEmpty()){  
            result.add(stack.pop());  
        }  
        return result;  
    }  
      
    /** 
     * 计算后缀表达式 
     */  
    public Fraction calculate(ArrayList<String> postOrder){  
        Stack<Fraction> stack = new Stack();
        for (int i = 0; i < postOrder.size(); i++) {  
            if(Character.isDigit(postOrder.get(i).charAt(0))){
            	int num = Integer.parseInt(postOrder.get(i));
            	Fraction fraction = new Fraction(num);
                stack.push(fraction);
            }else{  
            	Fraction back = (Fraction)stack.pop();  
            	Fraction front = (Fraction)stack.pop();  
            	Fraction res = new Fraction();  
                switch (postOrder.get(i).charAt(0)) {  
                case '+':  
                    res = Fraction.add(front, back);
                    break;  
                case '-':  
                    res = Fraction.sub(front, back);
                    break;  
                case '*':  
                    res = Fraction.mul(front, back);  
                    break;  
                case '/':  
                    res = Fraction.div(front, back);  
                    break;  
                }  
                stack.push(res);  
            }  
        }  
        return (Fraction)stack.pop();  
    }  
      
    /** 
     * 比较运算符等级 
     */  
    public static boolean compare(String peek, String cur){  
        if("*".equals(peek) && ("/".equals(cur) || "*".equals(cur) ||"+".equals(cur) ||"-".equals(cur))){  
            return true;  
        }else if("/".equals(peek) && ("/".equals(cur) || "*".equals(cur) ||"+".equals(cur) ||"-".equals(cur))){  
            return true;  
        }else if("+".equals(peek) && ("+".equals(cur) || "-".equals(cur))){  
            return true;  
        }else if("-".equals(peek) && ("+".equals(cur) || "-".equals(cur))){  
            return true;  
        }  
        return false;  
    }  
   
    
    public static String getAnswer(String s) {
        Calculate calculate = new Calculate();  
        ArrayList result = calculate.getStringList(s);
        result = calculate.getPostOrder(result);   //中缀变后缀  
        Fraction answer = calculate.calculate(result);   //计算  
        return answer.toString();
    }
    
//    public static void main(String args[]) {
//    	System.out.println(getAnswer("(5-4)*(3+8)"));
//    }
    
}