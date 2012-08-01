package it.valsecchi.waver.panel;

import it.valsecchi.waver.formule.WaveData;
import it.valsecchi.waver.formule.WaveType;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class WaveViewer extends JFrame {

	private static final long serialVersionUID = -275640641614487453L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WaveViewer frame = new WaveViewer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WaveViewer() {
		setTitle("Waver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1627, 987);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		WaveData data = new WaveData(20, 5, 10,0, 5);
		WaveData data2 = new WaveData(20, 4f, 10,(float) (Math.PI/3), 5);
		
		WavePanel panel1 = new WavePanel(WaveType.LOCAL, 0, 0, 1200,400,50,50);
		panel1.addWaveFormula(data.getLocalWave(0));
		panel1.addWaveFormula(data2.getLocalWave(0));
		panel1.addWaveFormula(WaveData.getWaveSomma(WaveType.LOCAL,data.getLocalWave(0),data2.getLocalWave(0)));
		contentPane.add(panel1);
		
		WavePanel panel3 = new WavePanel(WaveType.TOTAL,0,450,1200,400,50,50);
		panel3.setBounds(0, 450, 1404, 463);
		panel3.setMaxY(50);
		panel3.addWaveFormula(data.getTotalWave());
		panel3.addWaveFormula(data2.getTotalWave());
		panel3.addWaveFormula(WaveData.getWaveSomma(WaveType.TOTAL,data.getTotalWave(),data2.getTotalWave()));
		contentPane.add(panel3);
		
		panel3.startWaveTimer();

	}

}
