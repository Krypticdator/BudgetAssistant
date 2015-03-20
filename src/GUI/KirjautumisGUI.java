package GUI;
import java.awt.EventQueue;

import controller.Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;

//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.factories.FormFactory;
//import com.jgoodies.forms.layout.RowSpec;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class KirjautumisGUI {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private Controller cntrl;
	private JLabel lblError;

	
	public KirjautumisGUI(Controller cntr){
		cntrl = cntr;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try{
					initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(102, 51, 153));
		panel.setBackground(new Color(153, 255, 153));
		panel.setBounds(0, 0, 634, 462);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Tunnus");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnOk = new JButton("Kirjaudu");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String account = textField.getText();
				@SuppressWarnings("deprecation")
				String password = passwordField.getText();
				//frame.dispose();
				boolean succesfull = cntrl.login(account, password);
				if(!succesfull){
					lblError.setText("V‰‰r‰ tunnus tai salasana");
				}
				
			}
		});
		btnOk.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblNewLabel_1 = new JLabel("Salasana");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		passwordField = new JPasswordField();
		
		JButton btnLuoUusiTili = new JButton("Luo uusi tili");
		btnLuoUusiTili.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cntrl.openCreateAccountWindow();
			}
		});
		btnLuoUusiTili.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnLuoUusiTili.setForeground(new Color(0, 0, 0));
		
		lblError = new JLabel("Errortext");
		lblError.setText(" ");
		lblError.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(248, Short.MAX_VALUE)
					.addComponent(btnLuoUusiTili, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addGap(240))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(277, Short.MAX_VALUE)
					.addComponent(btnOk)
					.addGap(268))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(238)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblError, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(passwordField, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(56)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(57)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(39)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addGap(4)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblError)
					.addGap(10)
					.addComponent(btnLuoUusiTili)
					.addContainerGap(203, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	public void destroyFrame(){
		frame.dispose();
	}
}
