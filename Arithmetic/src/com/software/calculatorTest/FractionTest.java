package com.software.calculatorTest;
import org.junit.*;

import com.software.calculator.Fraction;

public class FractionTest {
	
	Fraction fraction1;
	Fraction fraction2;
	
	@Before
	public void testBefore() {
		fraction1 = new Fraction(1, 6);
		fraction2 = new Fraction(1, 8);
	}
	
	/** 
	 * 测试分数加法
	 */
	@Test
	public void testFractionAdd() {
		// 1/6 + 1/8 = 7/24
		Assert.assertEquals(Fraction.add(fraction1, fraction2).toString(), "7/24");
		
		// 3/7 + 2/5 = 29/35
		Assert.assertEquals(Fraction.add(new Fraction(3, 7), new Fraction(2, 5)).toString(), "29/35");
		
		// -2/11 + 3/11 = 1/11
		Assert.assertEquals(Fraction.add(new Fraction(-2, 11), new Fraction(3, 11)).toString(), "1/11");
	}
	
	/** 
	 * 测试分数减法
	 */
	@Test
	public void testFractionSub() {
		// 1/6 - 1/8 = 1/24
		Assert.assertEquals(Fraction.sub(fraction1, fraction2).toString(), "1/24");
	}
	
	/** 
	 * 测试分数乘法
	 */
	@Test
	public void testFractionMul() {
		// 1/6 * 1/8 = 1/48
		Assert.assertEquals(Fraction.mul(fraction1, fraction2).toString(), "1/48");
	}
	
	/** 
	 * 测试分数除法
	 */
	@Test
	public void testFractiondiv() {
		// 1/6 / 1/8 = 4/3
		Assert.assertEquals(Fraction.div(fraction1, fraction2).toString(), "4/3");
	}
}
