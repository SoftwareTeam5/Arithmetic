package com.software.calculator;
import java.util.ArrayList;
import java.util.Random;

/**
 * 这个类生成四则运算表达式
 * Created on 2016/12/3.
 */
public class Equation {

    private static ArrayList<String> Record = new ArrayList<>();

    /**
     * 随机生成四则运算符(+、-、*、/)
     * @return symbol
     */
    private static char createOperator(){
        char operator;
        Integer base;
        Integer index;
        Random random = new Random();
        base = random.nextInt(100)+1;
        index = base % 4;

        switch (index){
            case 0: operator = '+'; break;
            case 1: operator = '-'; break;
            case 2: operator = '*'; break;
            default:operator = '/'; break;
        }
        return operator;
    }

    /**
     * int型转String
     * @param number
     * @return str_num
     */
    private static String int2String(Integer number){
        Integer temp = Math.abs(number);
        return String.valueOf(temp);
    }

    /**
     * 拼接表达式
     * @param operand1 操作数1
     * @param operand2 操作数2
     * @param symbol 运算符
     * @return equation
     */
    private static String convertEqu2Str(String operand1, String operand2, char symbol){
        String equation;
        equation = operand1+symbol+operand2;
        return equation;
    }

    /**
     * 生成四则运算表达式
     * @return equation
     */
    public static String createEquation(){
        Random random = new Random();
        String interim;
        String finalEquation;

        int operand1 = random.nextInt(11)+1;
        int operand2 = random.nextInt(11)+1;
        String strOp1 = int2String(operand1);
        String strOp2 = int2String(operand2);

        char operator = createOperator();
        interim = convertEqu2Str(strOp1,strOp2,operator);

        int numOfOperands = random.nextInt(11)%4+2;
        if(numOfOperands > 2){
            for(; numOfOperands > 2; numOfOperands--){
                strOp1 = interim;

                int bracket = random.nextInt(11)%2;
                if (bracket == 0){
                    strOp1 = '('+strOp1+')';
                }

                operator = createOperator();
                operand2 = random.nextInt(11)+1;
                strOp2 = int2String(operand2);

                int bracketPosition = random.nextInt(10)%2;
                if(bracketPosition == 0){
                    String temp = strOp1;
                    strOp1 = strOp2;
                    strOp2 = temp;
                }

                interim = convertEqu2Str(strOp1,strOp2,operator);
            }
        }

        finalEquation = interim;
        return finalEquation;
    }

    /**
     * 获得四则运算表达式
     * @return equation.
     */
    public static String getEquation(){
        String equation = createEquation();
        while (Record.contains(equation)){
            equation = createEquation();
        }
        Record.add(equation);
        return equation;
    }
}
