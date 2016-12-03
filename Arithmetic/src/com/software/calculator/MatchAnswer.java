package com.software.calculator;

public class MatchAnswer {

	public static StringBuffer match(String rightAnswer, String userAnswer) {
		StringBuffer output = new StringBuffer("正确答案：" + rightAnswer + " 你的答案：" + userAnswer + " ");
		if (rightAnswer.equals(userAnswer)) {
			output.append("正确！\n");
		} else {
			output.append("错误！\n");
		}
		return output;
	}
	
}
