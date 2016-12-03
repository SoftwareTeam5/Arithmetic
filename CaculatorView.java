package view;

import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class CaculatorView extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 810;
	static final int HEIGTH = 500;
	
	private JTextArea equationInput = new JTextArea(10, 25);
	private JScrollPane scp1 = new JScrollPane(equationInput);
	
	private JTextArea answerInput = new JTextArea(10, 15);
	private JScrollPane scp2 = new JScrollPane(answerInput);
	
	private JTextArea matchAnser = new JTextArea(10, 15);
	private JScrollPane scp3 = new JScrollPane(matchAnser);
	
	private JTextField numberText = new JTextField();
	
	private JLabel jLabel = new JLabel("题量：");
	
	//设置按钮
	private JButton ensureEquation = new JButton("确认");
	private JButton ensureAnswer = new JButton("确认答案");
	private JButton reset = new JButton("重置");
	
	public static int ensureNumber;
		
	public CaculatorView() {
		
		this.setTitle("运算器");
		this.setSize(WIDTH,HEIGTH);
		
		//设置中间容器
		JPanel jPanel = new JPanel();
		this.setContentPane(jPanel);
		jPanel.setLayout(null);
	
		jLabel.setBounds(20, 10, 50, 25);
		numberText.setBounds(60, 10, 60, 25);
		ensureEquation.setBounds(125, 10, 60, 25);
		ensureAnswer.setBounds(350, 10, 90, 25);
		reset.setBounds(580, 10, 60, 25);
		scp1.setBounds(20,50,300,400);
		scp2.setBounds(350,50,200,400);
		scp3.setBounds(580,50,200,400);
		
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
		
	}
	
	public static void main(String[] args) throws IOException{

		new CaculatorView();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String button = e.getActionCommand();
		if (button.equals("确认")) {
			ensureNumber = Integer.parseInt(numberText.getText());
		} else if (button.equals("确认答案")) {

		} else if (button.equals("重置")) {

		}	
		
	}

}
