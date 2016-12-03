package com.software.calculator;

import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * �����UI���棬���û���������������ж����㣬��������Ӧ����ʾ
 * ���������ܣ��������������װ�õľ�̬����
 * @author ��
 *
 */
public class CaculatorView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	/** ���ڵĿ�� */
	public static final int WIDTH = 810;
	/** ���ڵĸ߶� */
	public static final int HEIGTH = 500;//���ڵĸ߶�
	
	/**
	 * ����ʽ����ʾ�ı�������
	 */
	private JTextArea equationInput = new JTextArea(10, 15);
	private JScrollPane scp1 = new JScrollPane(equationInput);
	
	/**
	 * �û��𰸵������ı�������
	 */
	private JTextArea answerInput = new JTextArea(10, 15);
	private JScrollPane scp2 = new JScrollPane(answerInput);
	
	/**
	 * �𰸼���Ľ������ı�������
	 */
	private JTextArea matchAnswer = new JTextArea(10, 25);
	private JScrollPane scp3 = new JScrollPane(matchAnswer);
	
	private JTextField numberText = new JTextField();//�����������
	private JLabel jLabel = new JLabel("������");//�������ı���
	
	private JButton ensureEquation = new JButton("ȷ��");//ȷ�ϰ�ť
	private JButton ensureAnswer = new JButton("ȷ�ϴ�");//ȷ�ϴ𰸰�ť
	private JButton reset = new JButton("����");//���ð�ť

	private StringBuffer equationStrs = new StringBuffer();//���ʽ���ַ���������
	private StringBuffer resultStrs = new StringBuffer();//�û������𰸵��ַ���������
	private StringBuffer matchOutputStrs = new StringBuffer();//��������ַ���������

	private String[] userResult = new String[100];//�û������Ĵ�
	private String[] rightResult = new String[100];//����ʽ����ȷ��
	private String[] expression = new String[100];//������ɵı��ʽ
	private int ensureNumber;//����ʽ������
		
	/**
	 * CaculatorView�Ĺ��췽��
	 */
	public CaculatorView() {
		
		//���ñ���ʹ�С
		this.setTitle("������");
		this.setSize(WIDTH,HEIGTH);
		
		//�����м�����
		JPanel jPanel = new JPanel();
		this.setContentPane(jPanel);
		jPanel.setLayout(null);
	
		//���ñ��ʽ�ͼ������ı���Ϊ���ɱ༭״̬
		equationInput.setEditable(false);
		matchAnswer.setEditable(false);
		
		//���ø���UI�����Ĵ�С�Լ�λ��
		jLabel.setBounds(20, 10, 50, 25);
		numberText.setBounds(60, 10, 60, 25);
		ensureEquation.setBounds(125, 10, 60, 25);
		ensureAnswer.setBounds(250, 10, 90, 25);
		reset.setBounds(480, 10, 60, 25);
		scp1.setBounds(20,50,200,400);
		scp2.setBounds(250,50,200,400);
		scp3.setBounds(480,50,300,400);
		
		//Ϊ������ť����¼�����
		ensureEquation.addActionListener(this);
		ensureAnswer.addActionListener(this);
		reset.addActionListener(this);
		
		//������UI������ӵ�jPanel�����
		jPanel.add(ensureEquation);
		jPanel.add(ensureAnswer);
		jPanel.add(reset);
		jPanel.add(scp1);
		jPanel.add(scp2);
		jPanel.add(scp3);
		jPanel.add(jLabel);
		jPanel.add(numberText);
		
		//���ÿɼ���
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//��������ʾ����Ļ�м�
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		//���ɱ��ʽ�����ݳ�ʼ������
		Equation.createEquation();
		
	}
	
	public static void main(String[] args) throws IOException{
		new CaculatorView();
	}
	
	/**
	 * �������ݵ�����
	 * @param flag
	 * flagΪtrue����ʾ��������ð�ť����numberText���
	 * flagΪfalse����ʾ�����ȷ����ť����numberText�����
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
		if (button.equals("ȷ��")) {
			resetDatas(false);
			String numberStr = numberText.getText();
			
			if (numberStr.length() != 0) {
				
				/**
				 * ���������������жϣ�����Ϊ100�⣬�����������С�ڵ���0�⣬���������Ӧ��ʾ
				 * ��������ȷʱ��������ת��Ϊint�ͱ�����ensureNumber��
				 */
				if ((Integer.parseInt(numberStr) > 100) || (Integer.parseInt(numberStr) <= 0)) {
					JOptionPane.showMessageDialog(null, "�������Ϊ100�⣡���������룡", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else {
					ensureNumber = Integer.parseInt(numberStr);
				}
			} else {
				JOptionPane.showMessageDialog(null, "��������������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
			
			for (int i = 0; i < ensureNumber; i ++) {
				
				/**
				 * ����Equation���getEquation()���ɱ��ʽ����
				 * �������ɵı��ʽ������expression��������
				 */
				expression[i] = Equation.getEquation();
				equationStrs.append("��" +  (i + 1) + ": " + expression[i] + "\n");
		  
				/**
				 * ����Calculate���getAnswer()����
				 * ���������ʽ�Ĵ𰸲�������rightResult��������
				 */
		        rightResult[i] = Calculate.getAnswer(expression[i]);
		        resultStrs.append("��" +  (i + 1) + ": " + rightResult[i] + "\n");
			}
			equationInput.setText(equationStrs.toString());			
		} else if (button.equals("ȷ�ϴ�")) {
			String numberStr = numberText.getText();
			
			/**
			 * ����������жϣ�������������ʽδ����ʱ��������Ӧ������ʾ
			 */
			if (numberStr.length() == 0) {
				JOptionPane.showMessageDialog(null, "��������������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			} else if(ensureNumber == 0) {
				JOptionPane.showMessageDialog(null, "�������ɱ��ʽ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			} else {
				userResult = answerInput.getText().split("\n");
				
				/**
				 * ���û������Ĵ����������ж�
				 * ������С�ڻ��������ʽ������ʱ��������Ӧ������ʾ
				 */
				if (userResult.length < ensureNumber) {
					JOptionPane.showMessageDialog(null, "���������٣��������ȷ�����Ĵ𰸣�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else if (userResult.length > ensureNumber) {
					JOptionPane.showMessageDialog(null, "���������࣬�������ȷ�����Ĵ𰸣�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else {
					
					/**
					 * ����MatchAnswer���match()���������û������Ĵ𰸽��м���
					 * ���Ѽ��������浽matchOutputStrs�ַ���������
					 * ������ȷ�ʹ���Ĵ���
					 */
					for (int i = 0; i < ensureNumber; i ++) {
						matchOutputStrs.append(MatchAnswer.match(rightResult[i], userResult[i]));
					}
					matchOutputStrs.append("\n���� " + ensureNumber + " ������ʽ\n");
					matchOutputStrs.append("��ȷ�𰸣� " + MatchAnswer.getRightNums() + "��\n");
					matchOutputStrs.append("����𰸣� " + MatchAnswer.getWrongNums() + "��\n");
					if (MatchAnswer.getRightNums() == ensureNumber) {
						matchOutputStrs.append("�������ҵĸ磡������������Ŀ��\n");
					} else if (MatchAnswer.getRightNums() == 0) {
						matchOutputStrs.append("һ�ⶼû��ԡ�����СѧУ�������ˣ�");
					} else {
						matchOutputStrs.append("лл���룬��ӭ�ٴ���ս��");
					}
					matchAnswer.setText(matchOutputStrs.toString());
				}
			}
		} else if (button.equals("����")) {
			/**
			 * ��������ı����Լ���������
			 */
			resetDatas(true);
		}	
		
	}

}
