package com.software.calculator;

public class MatchAnswer {

	public static StringBuffer match(String rightAnswer, String userAnswer) {
		StringBuffer output = new StringBuffer("��ȷ�𰸣�" + rightAnswer + " ��Ĵ𰸣�" + userAnswer + " ");
		if (rightAnswer.equals(userAnswer)) {
			output.append("��ȷ��\n");
		} else {
			output.append("����\n");
		}
		return output;
	}
	
}
