package it.valsecchi.waver.panel;

import it.valsecchi.waver.formule.WaveData;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WaveCreator extends JFrame {

	private static final long serialVersionUID = 2895538961818336246L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnNewOnda;
	private JLabel lblAmpiezza;
	private JTextPane txtFase0;
	private JLabel lblFaseIniziale;
	private JTextPane txtPeriodo;
	private JLabel lblPeriodo;
	private JTextPane txtAmpiezza;
	private JLabel lblLunghezzaDonda;
	private JTextPane txtLambda;
	private JPanel panel_1;
	private JTable table;
	private int counter= 0;
	private Map<String,WaveData > waves;
	private List<WaveChangedListener> listeners;

	public WaveCreator() {
		setTitle("Waver");
		waves = new HashMap<>();
		listeners = new ArrayList<>();
		this.initComponent();
	}

	private void initComponent(){
		setBounds(100, 100, 912, 757);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel = new JPanel();
		
		btnNewOnda = new JButton("Aggiungi Nuova Onda");
		btnNewOnda.addActionListener(new WaveAdder()); 
		panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
						.addComponent(btnNewOnda, Alignment.LEADING))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(btnNewOnda)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(79, Short.MAX_VALUE))
		);
		panel_1.setLayout(new BorderLayout(0, 0));
		{
			table = new JTable();
			panel_1.add(table, BorderLayout.CENTER);
		}
		
		lblAmpiezza = new JLabel("Ampiezza:");
		
		txtAmpiezza = new JTextPane();
		
		txtPeriodo = new JTextPane();
		
		lblPeriodo = new JLabel("Periodo:");
		
		txtLambda = new JTextPane();
		
		lblLunghezzaDonda = new JLabel("Lunghezza d'onda:");
		
		txtFase0 = new JTextPane();
		
		lblFaseIniziale = new JLabel("Fase Iniziale:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addComponent(lblAmpiezza)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtAmpiezza, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblPeriodo, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPeriodo, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblLunghezzaDonda)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtLambda, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(lblFaseIniziale, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFase0, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtLambda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFaseIniziale)
						.addComponent(txtFase0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPeriodo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPeriodo)
						.addComponent(lblAmpiezza)
						.addComponent(txtAmpiezza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLunghezzaDonda))
					.addContainerGap(72, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void addWaveChangedListener(WaveChangedListener lis){
		listeners.add(lis);
	}

	private class WaveAdder implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// si crea l'onda
			WaveData wave = new WaveData(
					Float.parseFloat(txtAmpiezza.getText()),
					Float.parseFloat(txtPeriodo.getText()),
					Float.parseFloat(txtLambda.getText()),
					Float.parseFloat(txtFase0.getText()));
			//si aggiunge
			counter+= 1;
			waves.put(getCounter(), wave);
			for(WaveChangedListener l : listeners){
				l.WaveChanged(wave,getCounter());
			}
		}
	}
	
	private String getCounter(){
		return Integer.toString(counter);
	}
}
