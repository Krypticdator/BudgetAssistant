package GUI;
import controller.Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class MenotGUI {
	private Controller cntr;
	private JFrame frame;
	private JTextField fieldVuokra;
	private JTextField fieldSahko;
	private JTextField fieldMatkakortti;
	private JTextField fieldPuhelin;
	private JTextField fieldLainat;
	private JTextField fieldRuoka;
	private JTextField fieldVakuutukset;
	private JTextField fieldTerveys;
	private JTextField fieldMuut;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public MenotGUI(Controller cntrl) {
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
		
		JLabel lblMenot = new JLabel("Menot");
		lblMenot.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblVuokra = new JLabel("Vuokra");
		lblVuokra.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblShklasku = new JLabel("S\u00E4hk\u00F6lasku");
		lblShklasku.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblMatkakortti = new JLabel("Matkakortti");
		lblMatkakortti.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblPuhelinlasku = new JLabel("Puhelinlasku");
		lblPuhelinlasku.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblLainat = new JLabel("Lainat");
		lblLainat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblRuokamenot = new JLabel("Ruokamenot");
		lblRuokamenot.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		fieldVuokra = new JTextField();
		fieldVuokra.setColumns(10);
		
		fieldSahko = new JTextField();
		fieldSahko.setColumns(10);
		
		fieldMatkakortti = new JTextField();
		fieldMatkakortti.setColumns(10);
		
		fieldPuhelin = new JTextField();
		fieldPuhelin.setColumns(10);
		
		fieldLainat = new JTextField();
		fieldLainat.setColumns(10);
		
		fieldRuoka = new JTextField();
		fieldRuoka.setColumns(10);
		
		JButton btnErittele = new JButton("Erittele");
		btnErittele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cntr.openDetailsWindow();
			}
		});
		btnErittele.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblVakuutukset = new JLabel("Vakuutukset");
		lblVakuutukset.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		fieldVakuutukset = new JTextField();
		fieldVakuutukset.setColumns(10);
		
		JLabel lblTerveydenhoidonMenot = new JLabel("Terveydenhoito");
		lblTerveydenhoidonMenot.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		fieldTerveys = new JTextField();
		fieldTerveys.setColumns(10);
		
		JButton btnTallenna = new JButton("Tallenna");
		btnTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String,String> mp=new HashMap<String, String>();
				mp.put("vuokra", fieldVuokra.getText());
				mp.put("sahko", fieldSahko.getText());
				mp.put("terveys", fieldTerveys.getText());
				mp.put("puhelin", fieldPuhelin.getText());
				mp.put("lainat", fieldLainat.getText());
				mp.put("ruoka", fieldRuoka.getText());
				mp.put("vakuutukset", fieldVakuutukset.getText());
				 //Get Map in Set interface to get key and value
		        Set<Entry<String, String>> s=mp.entrySet();

		        //Move next key and value of Map by iterator
		        Iterator<Entry<String, String>> it=s.iterator();
		        
		        while(it.hasNext())
		        {
		            // key=value separator this by Map.Entry to get key and value
		            Map.Entry m =(Map.Entry)it.next();

		            // getKey is used to get key of Map
		            String key=(String)m.getKey();

		            // getValue is used to get value of key in Map
		            String value=(String)m.getValue();
		            try{
		            	double ex =-1* Double.parseDouble(value);
		            	if(ex!=0){
		            		cntr.saveSumToDatabase(key, ex);
		            	}
		            }catch(Exception err){
		            	
		            }
		        }
			}
		    
			
		});
		btnTallenna.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblMuutMenot = new JLabel("Muut menot");
		lblMuutMenot.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		fieldMuut = new JTextField();
		fieldMuut.setColumns(10);
		
		JButton btnPoistu = new JButton("P\u00E4\u00E4valikko");
		btnPoistu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnPoistu.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblVuokra)
							.addComponent(lblMenot))
						.addComponent(lblShklasku)
						.addComponent(lblMatkakortti)
						.addComponent(lblPuhelinlasku)
						.addComponent(lblLainat)
						.addComponent(lblVakuutukset)
						.addComponent(lblTerveydenhoidonMenot)
						.addComponent(lblRuokamenot)
						.addComponent(lblMuutMenot)
						.addComponent(btnPoistu))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnTallenna)
						.addComponent(fieldMuut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(fieldRuoka, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(41)
							.addComponent(btnErittele))
						.addComponent(fieldTerveys, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldLainat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldPuhelin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldMatkakortti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldSahko, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldVuokra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldVakuutukset, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(265, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMenot)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVuokra)
						.addComponent(fieldVuokra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblShklasku)
						.addComponent(fieldSahko, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMatkakortti)
						.addComponent(fieldMatkakortti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPuhelinlasku)
						.addComponent(fieldPuhelin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLainat)
						.addComponent(fieldLainat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVakuutukset)
						.addComponent(fieldVakuutukset, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTerveydenhoidonMenot)
						.addComponent(fieldTerveys, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRuokamenot)
						.addComponent(fieldRuoka, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnErittele))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMuutMenot)
						.addComponent(fieldMuut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnPoistu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnTallenna, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(26))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
