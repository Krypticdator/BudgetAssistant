package GUI;
import controller.Controller;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TilinluomisGUI {
	private Controller ctrl;
	private JFrame frame;
	private JTextField field_name;
	private JTextField field_password;
	private JTextField field_confirm_password;
	private JLabel infolabel;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public TilinluomisGUI(Controller ctr) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		ctrl = ctr;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void setErrorText(String text){
		infolabel.setText(text);
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 255, 153));
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNimi = new JLabel("Nimi");
		lblNimi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		field_name = new JTextField();
		field_name.setColumns(10);
		
		JLabel lblSalasana = new JLabel("Salasana");
		lblSalasana.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		field_password = new JTextField();
		field_password.setColumns(10);
		
		field_confirm_password = new JTextField();
		field_confirm_password.setBackground(new Color(255, 255, 255));
		field_confirm_password.setColumns(10);
		
		JLabel lblSalasanaUudelleen = new JLabel("Salasana uudelleen");
		lblSalasanaUudelleen.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JButton btnLuoTili = new JButton("Luo tili");
		btnLuoTili.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = field_name.getText();
				String password = field_password.getText();
				String confirmation = field_confirm_password.getText();
				
				if(password.equals(confirmation)){
					if(ctrl.saveNewUserToDB(name, password)){
						frame.dispose();
					}
					
					
				}
			}
		});
		btnLuoTili.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		infolabel = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(196)
							.addComponent(lblSalasanaUudelleen))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(227)
							.addComponent(lblSalasana))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(237)
							.addComponent(lblNimi, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(226)
							.addComponent(btnLuoTili))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(146)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(infolabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(field_confirm_password)
								.addComponent(field_password)
								.addComponent(field_name, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))))
					.addContainerGap(270, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblNimi)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(field_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(lblSalasana)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(field_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(lblSalasanaUudelleen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(field_confirm_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLuoTili)
					.addGap(16)
					.addComponent(infolabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(200, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
