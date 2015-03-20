package GUI;
import controller.Controller;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TulotGUI {
	private Controller cntr;
	private JFrame frame;
	private JTextField field_salary;
	private JTextField field_other;
	private JTextField field_opintotuki;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public TulotGUI(Controller cntrl) {
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
		this.cntr = cntrl;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 255, 153));
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblTulot = new JLabel("Tulot");
		lblTulot.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		field_salary = new JTextField();
		field_salary.setColumns(10);
		
		JLabel lblPalkka = new JLabel("Palkka");
		lblPalkka.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblMuutTulot = new JLabel("Muut tulot");
		lblMuutTulot.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		field_other = new JTextField();
		field_other.setColumns(10);
		
		JLabel lblOpintotuki = new JLabel("Opintotuki");
		lblOpintotuki.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		field_opintotuki = new JTextField();
		field_opintotuki.setColumns(10);
		
		JButton btnTallenna = new JButton("Tallenna");
		btnTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double salary = Double.parseDouble(field_salary.getText());
				double opt = Double.parseDouble(field_opintotuki.getText());
				double other = Double.parseDouble(field_other.getText());
				System.out.println(salary);
				cntr.saveSumToDatabase("palkka", salary);
				cntr.saveSumToDatabase("opintotuki", opt);
				cntr.saveSumToDatabase("other", other);
				frame.dispose();
			}
		});
		btnTallenna.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JButton btnPoistu = new JButton("P\u00E4\u00E4valikko");
		btnPoistu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnPoistu.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTulot)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPalkka)
								.addComponent(lblMuutTulot)
								.addComponent(lblOpintotuki)
								.addComponent(btnPoistu))
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(field_opintotuki, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(field_other, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(field_salary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTallenna))))
					.addContainerGap(421, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTulot)
							.addGap(25)
							.addComponent(lblPalkka))
						.addComponent(field_salary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOpintotuki)
						.addComponent(field_opintotuki, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(field_other, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMuutTulot))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTallenna)
						.addComponent(btnPoistu, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(205, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
