package com.software.calculator;
import org.junit.*;

public class Fraction {
	
	int a; // 分子
	int b; // 分母
	
	/**
	 * @param a 整数 a
	 * @param b 整数 b
	 * @return 整数 a 和 b 的最大公约数
	 */
	public static int gcd(int a, int b) {
		if (b == 0) return a;
		else return gcd(b, a % b);
	}
	
	/**
	 * @param a 整数 a
	 * @param b 整数 b
	 * @return 整数 a 和 b 的最小公倍数
	 */	
	public static int lcm(int a, int b) {
		return a / gcd(a, b) * b;
	}
	
	/**
	 * 构造方法 的描述
	 * 默认构造的分数为 0/1
	 */
	public Fraction() {
		a = 0;
		b = 1;
	}
	
	/**
	 * 构造方法 的描述
	 * @param x
	 * 构造整数x对应的分数形式，即x = x/1
	 */
	public Fraction(int x) {
		a = x;
		b = 1;
	}
	
	/**
	 * 构造方法 的描述
	 * @param x
	 * @param y
	 * x为分子，y为分母，构造分数 x/y
	 */
	public Fraction(int x, int y) {
		a = x;
		b = y;
	}
	
	/**
	 * 将分数进行约分
	 */
	public void simplify() {
		int m = gcd(a, b);
		if (m != 0) {
			a /= m;
			b /= m;			
		}
	}
	
	/**
	 * @return 
	 * 将分数转换为字符串输出
	 * 1) 分子为0，说明答案为0，因此输出 “0”
	 * 2) 分母为1，说明答案为整数，输出分子即可
	 * 3) 分数的一般形式，输出 “a/b”
	 */
	public String toString() {
		String str = "";
		simplify();
		if (a == 0) {
			str = "0";
		} else if (b == 1) {
			str = String.valueOf(a);
		} else {
			str = String.valueOf(a) + "/" + String.valueOf(b);
		}
		return str;
	}
	
	/**
	 * 分数相加函数
	 * @param f1 第一个分数
	 * @param f2 第二个分数
	 * @return 两个分数相加的结果
	 */
	public static Fraction add(Fraction f1, Fraction f2) {
		int m = gcd(f1.b, f2.b);
		Fraction fraction = new Fraction(f1.b / m * f2.a + f2.b / m * f1.a, f1.b / m * f2.b);
		return fraction;
	}
	
	/**
	 * 分数相减函数
	 * @param f1 第一个分数
	 * @param f2 第二个分数
	 * @return 两个分数相减的结果
	 */
	public static Fraction sub(Fraction f1, Fraction f2) {
		int m = gcd(f1.b, f2.b);
		Fraction fraction = new Fraction(f2.b / m * f1.a - f1.b / m * f2.a, f1.b / m * f2.b);
		return fraction;
	}
	
	/**
	 * 分数相乘函数
	 * @param f1 第一个分数
	 * @param f2 第二个分数
	 * @return 两个分数相乘的结果
	 */
	public static Fraction mul(Fraction f1, Fraction f2) {
		int m1 = gcd(Math.abs(f1.a), Math.abs(f2.b));
		int m2 = gcd(Math.abs(f1.b), Math.abs(f2.a));
		Fraction fraction = new Fraction((f1.a / m1) * (f2.a / m2), (f1.b / m2) * (f2.b / m1));
		return fraction;
	}
	
	/**
	 * 分数相除函数
	 * @param f1 第一个分数
	 * @param f2 第二个分数
	 * @return 两个分数相除的结果
	 */
	public static Fraction div(Fraction f1, Fraction f2) {
		Fraction temp = new Fraction(f2.b, f2.a);
		Fraction fraction = mul(f1, temp);
		return fraction;
	}
	
}
