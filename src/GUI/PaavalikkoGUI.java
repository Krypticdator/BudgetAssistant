package GUI;
import controller.Controller;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PaavalikkoGUI {

	private JFrame frame;
	private Controller cntrl;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	
	
	public PaavalikkoGUI(Controller cntrl){
		//System.out.println("where is my window?");
		this.cntrl = cntrl;
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
		this.cntrl.destroyLoginWindows();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 255, 153));
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnLisMenoja = new JButton("Lis\u00E4\u00E4 menoja");
		btnLisMenoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cntrl.openExpensesWindow();
			}
		});
		btnLisMenoja.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JButton btnLisTuloja = new JButton("Lis\u00E4\u00E4 tuloja");
		btnLisTuloja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cntrl.openIncomeWindow();
			}
		});
		btnLisTuloja.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JButton btnYhteenveto = new JButton("Raportti");
		btnYhteenveto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cntrl.openReportWindow();
			}
		});
		btnYhteenveto.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(260)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnYhteenveto, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnLisTuloja, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnLisMenoja)))
					.addContainerGap(263, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(76)
					.addComponent(btnLisMenoja)
					.addGap(28)
					.addComponent(btnLisTuloja, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnYhteenveto, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(247, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
	}

}
