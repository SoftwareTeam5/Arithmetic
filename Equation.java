package com.weeway.compiler;

import java.util.Random;

/**
 * Created by wythe on 2016/12/3.
 */
public class Equation {
    private static char[] symbol = new char[4];
    private static String[] Equation = new String[1000];
    private static int amount = 100;
    private static boolean repeat = false;

    private static char create_symbol(){
        char symbol;
        Integer base;
        Integer index;
        Random random = new Random();
        base = random.nextInt(100)+1;
        index = base % 4;

        switch (index){
            case 0: symbol = '+'; break;
            case 1: symbol = '-'; break;
            case 2: symbol = '*'; break;
            default:symbol = '/'; break;
        }
        return symbol;
    }

    private static String int2String(Integer number){
        Integer temp = Math.abs(number);
        return String.valueOf(temp);
    }

    private static String convertEqu2Str(String operand1, String operand2, char symbol){
        String equation;
        equation = operand1+symbol+operand2;
        return equation;
    }

    public static void createEquation(){
        Random random = new Random();

        for(int i = 0; i < amount; ++i){
            int count = random.nextInt(11)%4+2;

            int openrand1 = random.nextInt(11)+1;
            int openrand2 = random.nextInt(11)+1;
            char symbol = create_symbol();
            String strOp1 = int2String(openrand1);
            String strOp2 = int2String(openrand2);
            Equation[i]= convertEqu2Str(strOp1,strOp2,symbol);

            if(count > 2){
                for(; count>2; count--){
                    symbol = create_symbol();
                    strOp1 = Equation[i];
                    int change = random.nextInt(11)%2;
                    if (change == 0){
                        strOp1 = '('+strOp1+')';
                    }
                    symbol = create_symbol();
                    openrand2 = random.nextInt(11);
                    strOp2 = int2String(openrand2);
                    change = random.nextInt(10);
                    if(change == 0){
                        String temp = strOp1;
                        strOp1 = strOp2;
                        strOp2 = temp;
                    }
                    Equation[i] = convertEqu2Str(strOp1,strOp2,symbol);
                }
            }
        }
    }

    public static String getEquation(){
        Random random = new Random();
        int index = random.nextInt(amount);
        return Equation[index];
    }
}