package com.software.calculator;


/**
 * ������ʽ����ȷ���Լ��û������Ĵ𰸽��м��飬�����ؼ�����
 * @author ��
 *
 */
public class MatchAnswer {

	public static int RightNums = 0;//��ȷ�𰸵�����
	public static int wrongNums = 0;//����𰸵�����
	
	/**
	 * ������ʽ����ȷ���Լ��û������Ĵ𰸽��м��飬�����ؼ�����
	 * @param rightAnswer ����ʽ����ȷ��
	 * @param userAnswer  �û������Ĵ�
	 * @return ������
	 */
	public static StringBuffer match(String rightAnswer, String userAnswer) {
		StringBuffer output = new StringBuffer("��ȷ�𰸣�" + rightAnswer + " ��Ĵ𰸣�" + userAnswer + " ");
		if (rightAnswer.equals(userAnswer)) {
			RightNums ++;
			output.append("��ȷ��\n");
		} else {
			wrongNums ++;
			output.append("����\n");
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
