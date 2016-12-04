package com.software.calculatorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.software.calculator.MatchAnswer;

/**
 * 答案匹配的测试用例
 * @author 陈燊
 *
 */
public class MatchAnswerTest {

	String rightAnswer;
	String userAnswer1;
	String userAnswer2;
	StringBuffer output1;
	StringBuffer output2;
	
	@Before
	public void setUp() throws Exception {
		rightAnswer = "10";
		userAnswer1 = "10";
		userAnswer2 = "2/5";
		output1 = new StringBuffer("正确答案：" + rightAnswer + " 你的答案：" + userAnswer1 + " 正确！\n");
		output2 = new StringBuffer("正确答案：" + rightAnswer + " 你的答案：" + userAnswer2 + " 错误！\n");
	}

	@Test
	public void testMatch() {
		Assert.assertEquals(MatchAnswer.match(rightAnswer, userAnswer1).toString(), output1.toString());
		Assert.assertEquals(MatchAnswer.match(rightAnswer, userAnswer2).toString(), output2.toString());
	}

}
