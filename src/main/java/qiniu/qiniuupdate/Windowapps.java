package qiniu.qiniuupdate;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Windowapps {

	public JFrame frame;
	public JTextField path_textField;
	public JTextField textField_accesskey;
	public JTextField textField_secretkey;
	public JTextField textField_bucket;
	public JButton button_start;
	public JButton button_stop;

	Windowapps mwind;
	public JTextArea textPane;
	private JScrollPane scrollPane_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final Windowapps window = new Windowapps();
					window.frame.setVisible(true);
					
					final App app = new App();
					
					window.frame.addWindowListener(new WindowListener() {
						
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub
							app.oncreate(window);
						}
						
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							System.out.println("关中");
							app.ondestory();
						}
						
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							System.out.println("关闭了");
							//
						}
						
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public Windowapps() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(UIManager.getColor("TextPane.selectionBackground"));
		frame.setBounds(100, 100, 749, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		path_textField = new JTextField();
		path_textField.setBounds(210, 27, 350, 35);
		frame.getContentPane().add(path_textField);
		path_textField.setColumns(10);
		
		JLabel label = new JLabel("监控目录：");
		label.setBounds(15, 22, 134, 45);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("accessKey：");
		lblNewLabel.setBounds(15, 80, 134, 48);
		frame.getContentPane().add(lblNewLabel);
		
		textField_accesskey = new JTextField();
		textField_accesskey.setBounds(210, 87, 350, 35);
		frame.getContentPane().add(textField_accesskey);
		textField_accesskey.setColumns(10);
		
		JLabel lblLkxfwadlkmbzusbybsztceggtlyibipr = new JLabel("secretKey：");
		lblLkxfwadlkmbzusbybsztceggtlyibipr.setBounds(15, 143, 134, 45);
		frame.getContentPane().add(lblLkxfwadlkmbzusbybsztceggtlyibipr);
		
		textField_secretkey = new JTextField();
		textField_secretkey.setColumns(10);
		textField_secretkey.setBounds(210, 148, 350, 35);
		frame.getContentPane().add(textField_secretkey);
		
		JLabel lblBucket = new JLabel("bucket：");
		lblBucket.setBounds(15, 214, 134, 45);
		frame.getContentPane().add(lblBucket);
		
		textField_bucket = new JTextField();
		textField_bucket.setColumns(10);
		textField_bucket.setBounds(210, 219, 350, 35);
		frame.getContentPane().add(textField_bucket);
		
		button_start = new JButton("开始同步");
		
		button_start.setBounds(420, 274, 140, 43);
		frame.getContentPane().add(button_start);
		
		button_stop = new JButton("停止同步");
		button_stop.setBounds(420, 332, 140, 40);
		frame.getContentPane().add(button_stop);
		
		textPane = new JTextArea();
		textPane.setBounds(35, 275, 269, 137);
		frame.getContentPane().add(textPane);
		
		 scrollPane_1 = new JScrollPane();
	        scrollPane_1.setBounds(35, 275, 269, 137);
	        frame.add(scrollPane_1);
	        scrollPane_1.setViewportView(textPane);
	}
}
