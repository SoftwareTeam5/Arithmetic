package com.software.calculator;


/**
 * 对运算式的正确答案以及用户给定的答案进行检验，并返回检验结果
 * @author 陈燊
 *
 */
public class MatchAnswer {

	public static int RightNums = 0;//正确答案的数量
	public static int wrongNums = 0;//错误答案的数量
	
	/**
	 * 对运算式的正确答案以及用户给定的答案进行检验，并返回检验结果
	 * @param rightAnswer 运算式的正确答案
	 * @param userAnswer  用户给定的答案
	 * @return 检验结果
	 */
	public static StringBuffer match(String rightAnswer, String userAnswer) {
		StringBuffer output = new StringBuffer("正确答案：" + rightAnswer + " 你的答案：" + userAnswer + " ");
		if (rightAnswer.equals(userAnswer)) {
			RightNums ++;
			output.append("正确！\n");
		} else {
			wrongNums ++;
			output.append("错误！\n");
		}
		return output;
	}

	public static int getRightNums() {
		return RightNums;
	}

	public static void setRightNums(int rightNums) {
		RightNums = rightNums;
	}

	public static int getWrongNums() {
		return wrongNums;
	}

	public static void setWrongNums(int wrongNums) {
		MatchAnswer.wrongNums = wrongNums;
	}
	
}
