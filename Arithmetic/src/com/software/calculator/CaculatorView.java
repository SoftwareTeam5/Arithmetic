package com.software.calculator;

import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * 程序的UI界面，对用户的输入输出进行判定运算，并给出相应的提示
 * 程序的主框架，调用了其他类封装好的静态方法
 * @author 陈燊
 *
 */
public class CaculatorView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	/** 窗口的宽度 */
	public static final int WIDTH = 810;
	/** 窗口的高度 */
	public static final int HEIGTH = 500;//窗口的高度
	
	/**
	 * 运算式的显示文本框区域
	 */
	private JTextArea equationInput = new JTextArea(10, 15);
	private JScrollPane scp1 = new JScrollPane(equationInput);
	
	/**
	 * 用户答案的输入文本框区域
	 */
	private JTextArea answerInput = new JTextArea(10, 15);
	private JScrollPane scp2 = new JScrollPane(answerInput);
	
	/**
	 * 答案检验的结果输出文本框区域
	 */
	private JTextArea matchAnswer = new JTextArea(10, 25);
	private JScrollPane scp3 = new JScrollPane(matchAnswer);
	
	private JTextField numberText = new JTextField();//题量的输入框
	private JLabel jLabel = new JLabel("题量：");//题量的文本框
	
	private JButton ensureEquation = new JButton("确认");//确认按钮
	private JButton ensureAnswer = new JButton("确认答案");//确认答案按钮
	private JButton reset = new JButton("重置");//重置按钮

	private StringBuffer equationStrs = new StringBuffer();//表达式的字符串缓冲区
	private StringBuffer resultStrs = new StringBuffer();//用户给定答案的字符串缓冲区
	private StringBuffer matchOutputStrs = new StringBuffer();//检测结果的字符串缓冲区

	private String[] userResult = new String[100];//用户给定的答案
	private String[] rightResult = new String[100];//运算式的正确答案
	private String[] expression = new String[100];//随机生成的表达式
	private int ensureNumber;//运算式的题量
		
	/**
	 * CaculatorView的构造方法
	 */
	public CaculatorView() {
		
		//设置标题和大小
		this.setTitle("运算器");
		this.setSize(WIDTH,HEIGTH);
		
		//设置中间容器
		JPanel jPanel = new JPanel();
		this.setContentPane(jPanel);
		jPanel.setLayout(null);
	
		//设置表达式和检验结果文本框为不可编辑状态
		equationInput.setEditable(false);
		matchAnswer.setEditable(false);
		
		//设置各个UI部件的大小以及位置
		jLabel.setBounds(20, 10, 50, 25);
		numberText.setBounds(60, 10, 60, 25);
		ensureEquation.setBounds(125, 10, 60, 25);
		ensureAnswer.setBounds(250, 10, 90, 25);
		reset.setBounds(480, 10, 60, 25);
		scp1.setBounds(20,50,200,400);
		scp2.setBounds(250,50,200,400);
		scp3.setBounds(480,50,300,400);
		
		//为三个按钮添加事件监听
		ensureEquation.addActionListener(this);
		ensureAnswer.addActionListener(this);
		reset.addActionListener(this);
		
		//把所有UI部件添加到jPanel面板里
		jPanel.add(ensureEquation);
		jPanel.add(ensureAnswer);
		jPanel.add(reset);
		jPanel.add(scp1);
		jPanel.add(scp2);
		jPanel.add(scp3);
		jPanel.add(jLabel);
		jPanel.add(numberText);
		
		//设置可见性
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//将窗口显示在屏幕中间
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		//生成表达式的数据初始化方法
		Equation.createEquation();
		
	}
	
	public static void main(String[] args) throws IOException{
		new CaculatorView();
	}
	
	/**
	 * 所有数据的重置
	 * @param flag
	 * flag为true，表示点击了重置按钮，则numberText清空
	 * flag为false，表示点击了确定按钮，则numberText不清空
	 */
	void resetDatas(boolean flag) {
		if (flag) {
			numberText.setText("");
		}
		equationInput.setText("");
		answerInput.setText("");
		matchAnswer.setText("");
		ensureNumber = 0;
		equationStrs.delete(0, equationStrs.length());
		resultStrs.delete(0, resultStrs.length());
		matchOutputStrs.delete(0, matchOutputStrs.length());
		userResult = new String[100];
		rightResult = new String[100];
		expression = new String[100];
		MatchAnswer.setRightNums(0);
		MatchAnswer.setWrongNums(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String button = e.getActionCommand();
		if (button.equals("确认")) {
			resetDatas(false);
			String numberStr = numberText.getText();
			
			if (numberStr.length() != 0) {
				
				/**
				 * 对输入题量进行判断，上限为100题，如果超出或则小于等于0题，都会给出相应提示
				 * 当输入正确时，把输入转化为int型保存在ensureNumber里
				 */
				if ((Integer.parseInt(numberStr) > 100) || (Integer.parseInt(numberStr) <= 0)) {
					JOptionPane.showMessageDialog(null, "题量最多为100题！请重新输入！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					ensureNumber = Integer.parseInt(numberStr);
				}
			} else {
				JOptionPane.showMessageDialog(null, "请先输入题量！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
			
			for (int i = 0; i < ensureNumber; i ++) {
				
				/**
				 * 调用Equation类的getEquation()生成表达式方法
				 * 并把生成的表达式保存在expression数组里面
				 */
				expression[i] = Equation.getEquation();
				equationStrs.append("题" +  (i + 1) + ": " + expression[i] + "\n");
		  
				/**
				 * 调用Calculate类的getAnswer()方法
				 * 计算出运算式的答案并保存在rightResult数组里面
				 */
		        rightResult[i] = Calculate.getAnswer(expression[i]);
		        resultStrs.append("题" +  (i + 1) + ": " + rightResult[i] + "\n");
			}
			equationInput.setText(equationStrs.toString());			
		} else if (button.equals("确认答案")) {
			String numberStr = numberText.getText();
			
			/**
			 * 对输入进行判断，当题量或则表达式未生成时，给出相应错误提示
			 */
			if (numberStr.length() == 0) {
				JOptionPane.showMessageDialog(null, "请先输入题量！", "提示", JOptionPane.INFORMATION_MESSAGE);
			} else if(ensureNumber == 0) {
				JOptionPane.showMessageDialog(null, "请先生成表达式！", "提示", JOptionPane.INFORMATION_MESSAGE);
			} else {
				userResult = answerInput.getText().split("\n");
				
				/**
				 * 对用户给定的答案数量进行判断
				 * 当答案数小于或大于运算式的数量时，给出相应错误提示
				 */
				if (userResult.length < ensureNumber) {
					JOptionPane.showMessageDialog(null, "答案数量过少，请给出正确数量的答案！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else if (userResult.length > ensureNumber) {
					JOptionPane.showMessageDialog(null, "答案数量过多，请给出正确数量的答案！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					
					/**
					 * 调用MatchAnswer类的match()方法，对用户给定的答案进行检验
					 * 并把检验结果保存到matchOutputStrs字符缓冲区里
					 * 给出正确和错误的答案数
					 */
					for (int i = 0; i < ensureNumber; i ++) {
						matchOutputStrs.append(MatchAnswer.match(rightResult[i], userResult[i]));
					}
					matchOutputStrs.append("\n共有 " + ensureNumber + " 题运算式\n");
					matchOutputStrs.append("正确答案： " + MatchAnswer.getRightNums() + "题\n");
					matchOutputStrs.append("错误答案： " + MatchAnswer.getWrongNums() + "题\n");
					if (MatchAnswer.getRightNums() == ensureNumber) {
						matchOutputStrs.append("厉害了我的哥！你答对了所有题目！\n");
					} else if (MatchAnswer.getRightNums() == 0) {
						matchOutputStrs.append("一题都没答对。。。小学校长想你了！");
					} else {
						matchOutputStrs.append("谢谢参与，欢迎再次挑战！");
					}
					matchAnswer.setText(matchOutputStrs.toString());
				}
			}
		} else if (button.equals("重置")) {
			/**
			 * 清空所有文本框，以及所有数据
			 */
			resetDatas(true);
		}	
		
	}

}
