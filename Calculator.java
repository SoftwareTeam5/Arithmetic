package lalala;

import java.util.StringTokenizer;

public class Calculator {
	int numerator;  // 分子
	int denominator; // 分母
	static Calculator r1;
	static Calculator r2;
	Calculator(){
	}

	Calculator(int a,int b){
		if(a == 0){
			numerator = 0;
			denominator = 1;
		}
		else{
			setNumeratorAndDenominator(a,b);
		}
	}
	
	void setNumeratorAndDenominator(int a, int b){  // 设置分子和分母
		int c = f(Math.abs(a),Math.abs(b));         // 计算最大公约数
		numerator = a / c;
		denominator = b / c;
		if(numerator<0 && denominator<0){
			numerator = - numerator;
			denominator = - denominator;
		}
	}
	
	int getNumerator(){
		return numerator;
	}

	int getDenominator(){
		return denominator;
	}
	
	int f(int a,int b){  // 求a和b的最大公约数
		if(a < b){
			int c = a;
			a = b;
			b = c;
		}
		int r = a % b;
		while(r != 0){
			a = b;
			b = r;;
			r = a % b;
		}
		return b;
	}
	
	private Calculator add(Calculator r){  // 加法运算
		int a = r.getNumerator();
		int b = r.getDenominator();
		int newNumerator = numerator * b + denominator * a;
		int newDenominator = denominator * b;
		Calculator result = new Calculator(newNumerator,newDenominator);
		return result;
	}
	
	private Calculator sub(Calculator r){  // 减法运算
		int a = r.getNumerator();
		int b = r.getDenominator();
		int newNumerator = numerator * b - denominator * a;
		int newDenominator = denominator * b;
		Calculator result = new Calculator(newNumerator,newDenominator);
		return result;
	} 
	
	private Calculator muti(Calculator r){ // 乘法运算
		int a = r.getNumerator();
		int b = r.getDenominator();
		int newNumerator = numerator * a;
		int newDenominator = denominator * b;
		Calculator result = new Calculator(newNumerator,newDenominator);
		return result;
	}
	
	private Calculator div(Calculator r){  // 除法运算
		int a = r.getNumerator();
		int b = r.getDenominator();
		int newNumerator = numerator * b;
		int newDenominator = denominator * a;
		Calculator result = new Calculator(newNumerator,newDenominator);
		return result;
	}
	public static void before(String data1,String data2)
	{
		StringTokenizer fenxi = new StringTokenizer(data1,"/");
	    int data1_1 = Integer.parseInt(fenxi.nextToken());
	    int data1_2 = Integer.parseInt(fenxi.nextToken());
		fenxi = new StringTokenizer(data2,"/");
	    int data2_1 = Integer.parseInt(fenxi.nextToken());
	    int data2_2 = Integer.parseInt(fenxi.nextToken());
	    	    
		r1 = new Calculator(data1_1,data1_2);
		r2 = new Calculator(data2_1,data2_2);
		
	}
	public String addResult(String data1,String data2)
	{
		before(data1,data2);
		Calculator result;
		result = r1.add(r2);
		String ans=result.getNumerator()+"/"+result.getDenominator();
		return ans;
	}
	public String subResult(String data1,String data2)
	{
		before(data1,data2);
		Calculator result;
		result = r1.sub(r2);
		String ans=result.getNumerator()+"/"+result.getDenominator();
		return ans;
	}
	public String mulResult(String data1,String data2)
	{
		before(data1,data2);
		Calculator result;
		result = r1.muti(r2);
		String ans=result.getNumerator()+"/"+result.getDenominator();
		return ans;
	}
	public String divResult(String data1,String data2)
	{
		before(data1,data2);
		Calculator result;
		result = r1.div(r2);
		String ans=result.getNumerator()+"/"+result.getDenominator();
		return ans;
	}
	
}