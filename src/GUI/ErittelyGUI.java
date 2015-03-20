package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;


public class ErittelyGUI {

	private JFrame frame;
	private JTextField field_tuote;
	private JTextField field_hinta;
	private int lines =0;
	private int x_lines = 3;
	Map<String, Object> elements = new HashMap<String, Object>();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public ErittelyGUI() {
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 255, 153));
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{86, 32, 86, 37, 87, 0};
		gridBagLayout.rowHeights = new int[]{31, 18, 18, 0, 27, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblErittely = new JLabel("Erittely");
		lblErittely.setFont(new Font("Times New Roman", Font.BOLD, 15));
		GridBagConstraints gbc_lblErittely = new GridBagConstraints();
		gbc_lblErittely.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblErittely.insets = new Insets(0, 0, 5, 5);
		gbc_lblErittely.gridx = 0;
		gbc_lblErittely.gridy = 1;
		frame.getContentPane().add(lblErittely, gbc_lblErittely);
		
		JLabel lblTuote = new JLabel("Tuote");
		lblTuote.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_lblTuote = new GridBagConstraints();
		gbc_lblTuote.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTuote.insets = new Insets(0, 0, 5, 5);
		gbc_lblTuote.gridx = 0;
		gbc_lblTuote.gridy = 2;
		frame.getContentPane().add(lblTuote, gbc_lblTuote);
		
		JLabel lblHinta = new JLabel("Hinta");
		lblHinta.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_lblHinta = new GridBagConstraints();
		gbc_lblHinta.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblHinta.insets = new Insets(0, 0, 5, 5);
		gbc_lblHinta.gridx = 2;
		gbc_lblHinta.gridy = 2;
		frame.getContentPane().add(lblHinta, gbc_lblHinta);
		
		field_tuote = new JTextField();
		field_tuote.setColumns(10);
		GridBagConstraints gbc_field_tuote = new GridBagConstraints();
		gbc_field_tuote.anchor = GridBagConstraints.WEST;
		gbc_field_tuote.insets = new Insets(0, 0, 5, 5);
		gbc_field_tuote.gridx = 0;
		gbc_field_tuote.gridy = 3;
		frame.getContentPane().add(field_tuote, gbc_field_tuote);
		
		field_hinta = new JTextField();
		field_hinta.setColumns(10);
		GridBagConstraints gbc_field_hinta = new GridBagConstraints();
		gbc_field_hinta.anchor = GridBagConstraints.WEST;
		gbc_field_hinta.insets = new Insets(0, 0, 5, 5);
		gbc_field_hinta.gridx = 2;
		gbc_field_hinta.gridy = 3;
		frame.getContentPane().add(field_hinta, gbc_field_hinta);
		
		JButton btnLisRivi = new JButton("Lis\u00E4\u00E4 rivi");
		btnLisRivi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tuote = "fieldTuote" + lines;
				String hinta = "fieldHinta" + lines;
				JTextField hinta2 = new JTextField();
				JTextField tuote2 = new JTextField();
				
				Dimension d = field_hinta.getPreferredSize();
				
				
				tuote2.setPreferredSize(d);
				
				hinta2.setColumns(10);
				GridBagConstraints gbc_tuote2 = new GridBagConstraints();
				GridBagConstraints gbc_hinta2 = new GridBagConstraints();
				x_lines++;
				gbc_tuote2.anchor = GridBagConstraints.NORTHWEST;
				gbc_tuote2.insets = new Insets(0, 0, 5, 5);
				//gbc_tuote2.gridx = 0;
				//gbc_tuote2.gridy = x_lines;
				
				gbc_tuote2.gridx = 0;
				gbc_tuote2.gridy = x_lines;
				
				
				//tuote2.setVisible(true);
				frame.getContentPane().add(tuote2, gbc_tuote2);
				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
				elements.put(tuote, tuote2);
				lines++;
				frame.validate();
				
				
				
			}
		});
		btnLisRivi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_btnLisRivi = new GridBagConstraints();
		gbc_btnLisRivi.insets = new Insets(0, 0, 5, 0);
		gbc_btnLisRivi.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnLisRivi.gridx = 4;
		gbc_btnLisRivi.gridy = 3;
		frame.getContentPane().add(btnLisRivi, gbc_btnLisRivi);
	}

}
