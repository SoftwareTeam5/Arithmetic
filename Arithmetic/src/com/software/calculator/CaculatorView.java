package com.software.calculator;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class CaculatorView extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 810;
	static final int HEIGTH = 500;
	
	private JTextArea equationInput = new JTextArea(10, 15);
	private JScrollPane scp1 = new JScrollPane(equationInput);
	
	private JTextArea answerInput = new JTextArea(10, 15);
	private JScrollPane scp2 = new JScrollPane(answerInput);
	
	private JTextArea matchAnswer = new JTextArea(10, 25);
	private JScrollPane scp3 = new JScrollPane(matchAnswer);
	
	private JTextField numberText = new JTextField();
	
	private JLabel jLabel = new JLabel("题量：");
	
	//设置按钮
	private JButton ensureEquation = new JButton("确认");
	private JButton ensureAnswer = new JButton("确认答案");
	private JButton reset = new JButton("重置");

	private StringBuffer equationStrs = new StringBuffer();
	private StringBuffer resultStrs = new StringBuffer();
	private StringBuffer matchOutput = new StringBuffer();

	private String userResultStrs;
	
	public String[] userResult = new String[100];
	public String[] result = new String[100];
	public String[] expression = new String[100];
	public static int ensureNumber;
		
	public CaculatorView() {
		
		this.setTitle("运算器");
		this.setSize(WIDTH,HEIGTH);
		
		//设置中间容器
		JPanel jPanel = new JPanel();
		this.setContentPane(jPanel);
		jPanel.setLayout(null);
	
		equationInput.setEditable(false);
		matchAnswer.setEditable(false);
		
		jLabel.setBounds(20, 10, 50, 25);
		numberText.setBounds(60, 10, 60, 25);
		ensureEquation.setBounds(125, 10, 60, 25);
		ensureAnswer.setBounds(250, 10, 90, 25);
		reset.setBounds(480, 10, 60, 25);
		scp1.setBounds(20,50,200,400);
		scp2.setBounds(250,50,200,400);
		scp3.setBounds(480,50,300,400);
		
		ensureEquation.addActionListener(this);
		ensureAnswer.addActionListener(this);
		reset.addActionListener(this);
		
		jPanel.add(ensureEquation);
		jPanel.add(ensureAnswer);
		jPanel.add(reset);
		jPanel.add(scp1);
		jPanel.add(scp2);
		jPanel.add(scp3);
		jPanel.add(jLabel);
		jPanel.add(numberText);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//将窗口显示在屏幕中间
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		Equation.createEquation();
		
	}
	
	public static void main(String[] args) throws IOException{

		new CaculatorView();
		
	}
	
	void resetDatas(boolean flag) {
		if (flag) {
			numberText.setText("");
		}
		equationInput.setText("");
		answerInput.setText("");
		matchAnswer.setText("");
		ensureNumber = 0;
		equationStrs = new StringBuffer();
		resultStrs = new StringBuffer();
		matchOutput = new StringBuffer();
		userResultStrs = "";
		userResult = new String[100];
		result = new String[100];
		expression = new String[100];
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String button = e.getActionCommand();
		if (button.equals("确认")) {
			resetDatas(false);
			String numberStr = numberText.getText();
			if (numberStr.length() != 0) {
				if ((Integer.parseInt(numberStr) > 100) || (Integer.parseInt(numberStr) <= 0)) {
					JOptionPane.showMessageDialog(null, "题量最多为100题！请重新输入！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					ensureNumber = Integer.parseInt(numberStr);
				}
			} else {
				JOptionPane.showMessageDialog(null, "请先输入题量！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
			
			for (int i = 0; i < ensureNumber; i ++) {
				expression[i] = Equation.getEquation();
				equationStrs.append("题" +  (i + 1) + ": " + expression[i] + "\n");
		       
		        result[i] = Calculate.getAnswer(expression[i]);
		        resultStrs.append("题" +  (i + 1) + ": " + result[i] + "\n");
			}
			equationInput.setText(equationStrs.toString());			
		} else if (button.equals("确认答案")) {
			String numberStr = numberText.getText();
			if (numberStr.length() == 0) {
				JOptionPane.showMessageDialog(null, "请先输入题量！", "提示", JOptionPane.INFORMATION_MESSAGE);
			} else if(ensureNumber == 0) {
				JOptionPane.showMessageDialog(null, "请先生成表达式！", "提示", JOptionPane.INFORMATION_MESSAGE);
			} else {
				userResultStrs = answerInput.getText();
				userResult = userResultStrs.split("\n");
				
				if (userResult.length < ensureNumber) {
					JOptionPane.showMessageDialog(null, "答案数量过少，请给出正确数量的答案！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else if (userResult.length > ensureNumber) {
					JOptionPane.showMessageDialog(null, "答案数量过多，请给出正确数量的答案！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					for (int i = 0; i < ensureNumber; i ++) {
						matchOutput.append(MatchAnswer.match(result[i], userResult[i]));
					}
					matchAnswer.setText(matchOutput.toString());
				}
			}
		} else if (button.equals("重置")) {
			resetDatas(true);
		}	
		
	}

}
