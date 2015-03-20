package GUI;
import controller.Controller;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RaporttiGUI {

	private JFrame frame;
	private Controller cntrl;
	JLabel lblSummaVar, lblTuloVar, lblMenoVar;

	
	public RaporttiGUI(Controller cntrl) {
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
		this.cntrl = cntrl;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 255, 153));
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblYhteenveto = new JLabel("Raportti");
		lblYhteenveto.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblTulotKuukaudessa = new JLabel("Tulot kuukaudessa");
		lblTulotKuukaudessa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblMenotKuukaudessa = new JLabel("Menot kuukaudessa");
		lblMenotKuukaudessa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblTulot = new JLabel("TULOT");
		lblTulot.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblMenot = new JLabel("MENOT");
		lblMenot.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblBudjetti = new JLabel("Budjetti");
		lblBudjetti.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblEuroa = new JLabel("EUROA");
		lblEuroa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		double income = cntrl.collectIncome();
		double expenses = cntrl.collectExpenses();
		double budjetti = income - expenses;
		
		String erotus = String.valueOf(budjetti);
		
		lblSummaVar = new JLabel(erotus);
		
		String tulot = String.valueOf(income);
		lblTuloVar = new JLabel(tulot);
		
		String menot = String.valueOf(expenses);
		lblMenoVar = new JLabel(menot);
		
		JButton btnPoistu = new JButton("P\u00E4\u00E4valikko");
		btnPoistu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnPoistu.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JButton btnPoistaKaikkiTapahtumat = new JButton("Poista kaikki tapahtumat");
		btnPoistaKaikkiTapahtumat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cntrl.destroyEvents();
				double income = cntrl.collectIncome();
				double expenses = cntrl.collectExpenses();
				double budjetti = income - expenses;
				String tulot = String.valueOf(income);
				String menot = String.valueOf(expenses);
				String erotus = String.valueOf(budjetti);
				
				lblSummaVar.setText(erotus);
				lblTuloVar.setText(tulot);
				lblMenoVar.setText(menot);
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPoistaKaikkiTapahtumat)
						.addComponent(lblYhteenveto)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTulotKuukaudessa)
								.addComponent(lblTulot)
								.addComponent(lblTuloVar)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblBudjetti)
									.addGap(18)
									.addComponent(lblSummaVar)
									.addGap(28)
									.addComponent(lblEuroa)))
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPoistu)
								.addComponent(lblMenoVar)
								.addComponent(lblMenot, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMenotKuukaudessa, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(271, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(lblYhteenveto)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMenotKuukaudessa, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTulotKuukaudessa))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTulot)
						.addComponent(lblMenot, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTuloVar)
						.addComponent(lblMenoVar))
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBudjetti)
						.addComponent(lblEuroa)
						.addComponent(lblSummaVar)
						.addComponent(btnPoistu))
					.addGap(31)
					.addComponent(btnPoistaKaikkiTapahtumat)
					.addContainerGap(168, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
