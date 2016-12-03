
public class Fraction {
	
	int a, b;
	
	public static int gcd(int a, int b) {
		if (b == 0) return a;
		else return gcd(b, a % b);
	}//最大公约数
	
	public static int lcm(int a, int b) {
		return a / gcd(a, b) * b;
	}//最大公倍数
	
	public Fraction() {
		a = 0;
		b = 1;
	}
	
	public Fraction(int x) {
		a = x;
		b = 1;
	}
	
	public Fraction(int x, int y) {
		a = x;
		b = y;
	}
	//a是fenzi，b是分母
	public String toString() {
		String str = "";
		if (a == 0) {
			str = "0";
		} else if (b == 1) {
			str = String.valueOf(a);
		} else {
			str = String.valueOf(a) + "/" + String.valueOf(b);
		}
		return str;
	}//字符串
	
	public static Fraction add(Fraction f1, Fraction f2) {
		int m = gcd(f1.b, f2.b);
		Fraction fraction = new Fraction(f1.b / m * f2.a + f2.b / m * f1.a, f1.b / m * f2.b);
		return fraction;
	}//加
	
	public static Fraction sub(Fraction f1, Fraction f2) {
		int m = gcd(f1.b, f2.b);
		Fraction fraction = new Fraction(f2.b / m * f1.a - f1.b / m * f2.a, f1.b / m * f2.b);
		return fraction;
	}//减
	
	public static Fraction mul(Fraction f1, Fraction f2) {
		int m1 = gcd(Math.abs(f1.a), Math.abs(f2.b));
		int m2 = gcd(Math.abs(f1.b), Math.abs(f2.a));
		Fraction fraction = new Fraction((f1.a / m1) * (f2.a / m2), (f1.b / m2) * (f2.b / m1));
		return fraction;
	}//乘
	
	public static Fraction div(Fraction f1, Fraction f2) {
		Fraction temp = new Fraction(f2.b, f2.a);
		Fraction fraction = mul(f1, temp);
		return fraction;
	}//除
	
}
