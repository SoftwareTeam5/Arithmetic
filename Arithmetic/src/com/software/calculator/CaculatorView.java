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
	
	private JLabel jLabel = new JLabel("������");
	
	//���ð�ť
	private JButton ensureEquation = new JButton("ȷ��");
	private JButton ensureAnswer = new JButton("ȷ�ϴ�");
	private JButton reset = new JButton("����");

	private StringBuffer equationStrs = new StringBuffer();
	private StringBuffer resultStrs = new StringBuffer();
	private StringBuffer matchOutput = new StringBuffer();

	private String userResultStrs;
	
	public String[] userResult = new String[100];
	public String[] result = new String[100];
	public String[] expression = new String[100];
	public static int ensureNumber;
		
	public CaculatorView() {
		
		this.setTitle("������");
		this.setSize(WIDTH,HEIGTH);
		
		//�����м�����
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
		
		//��������ʾ����Ļ�м�
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
		if (button.equals("ȷ��")) {
			resetDatas(false);
			String numberStr = numberText.getText();
			if (numberStr.length() != 0) {
				if ((Integer.parseInt(numberStr) > 100) || (Integer.parseInt(numberStr) <= 0)) {
					JOptionPane.showMessageDialog(null, "�������Ϊ100�⣡���������룡", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else {
					ensureNumber = Integer.parseInt(numberStr);
				}
			} else {
				JOptionPane.showMessageDialog(null, "��������������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
			
			for (int i = 0; i < ensureNumber; i ++) {
				expression[i] = Equation.getEquation();
				equationStrs.append("��" +  (i + 1) + ": " + expression[i] + "\n");
		       
		        result[i] = Calculate.getAnswer(expression[i]);
		        resultStrs.append("��" +  (i + 1) + ": " + result[i] + "\n");
			}
			equationInput.setText(equationStrs.toString());			
		} else if (button.equals("ȷ�ϴ�")) {
			String numberStr = numberText.getText();
			if (numberStr.length() == 0) {
				JOptionPane.showMessageDialog(null, "��������������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			} else if(ensureNumber == 0) {
				JOptionPane.showMessageDialog(null, "�������ɱ��ʽ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			} else {
				userResultStrs = answerInput.getText();
				userResult = userResultStrs.split("\n");
				
				if (userResult.length < ensureNumber) {
					JOptionPane.showMessageDialog(null, "���������٣��������ȷ�����Ĵ𰸣�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else if (userResult.length > ensureNumber) {
					JOptionPane.showMessageDialog(null, "���������࣬�������ȷ�����Ĵ𰸣�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else {
					for (int i = 0; i < ensureNumber; i ++) {
						matchOutput.append(MatchAnswer.match(result[i], userResult[i]));
					}
					matchAnswer.setText(matchOutput.toString());
				}
			}
		} else if (button.equals("����")) {
			resetDatas(true);
		}	
		
	}

}
