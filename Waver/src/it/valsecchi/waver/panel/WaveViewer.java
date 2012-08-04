package it.valsecchi.waver.panel;

import it.valsecchi.waver.formule.WaveData;
import it.valsecchi.waver.formule.WaveType;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WaveViewer extends JFrame implements WaveChangedListener,
		TimerListener {

	private static final long serialVersionUID = -275640641614487453L;
	private JPanel contentPane;
	private Map<WaveData, String> waves;
	private WaveCreator creator;
	private WavePanel panelLocal;
	private WavePanel panelGlobal;
	private WavePanel panelTotal;
	private JTextField txtX;
	private JTextField txtT;
	private JButton btnStart;
	private JButton btnStop;
	private JLabel lblNewLabel;
	private JTextField txtTCorrente;

	public WaveViewer() {
		waves = new HashMap<>();
		this.initComponent();
		creator = new WaveCreator();
		creator.addWaveChangedListener(this);
		creator.setVisible(true);
	}

	private void initComponent() {
		setTitle("Waver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1800, 1050);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// si creano i pannelli
		panelTotal = new WavePanel(WaveType.TOTAL, 5, 5, 1500, 320, 100, 30);
		panelTotal.addTimerListener(this);
		panelGlobal = new WavePanel(WaveType.GLOBAL, 5, 330, 1500, 320, 100, 30);
		panelLocal = new WavePanel(WaveType.LOCAL, 5, 655, 1500, 320, 100, 30);
		contentPane.add(panelTotal);
		contentPane.add(panelGlobal);
		contentPane.add(panelLocal);

		txtX = new JTextField();
		txtX.setBounds(1552, 666, 65, 22);
		contentPane.add(txtX);
		txtX.setColumns(10);

		JLabel lblX = new JLabel("x");
		lblX.setBounds(1532, 669, 13, 16);
		contentPane.add(lblX);

		JLabel lblT = new JLabel("t");
		lblT.setBounds(1532, 358, 13, 16);
		contentPane.add(lblT);

		txtT = new JTextField();
		txtT.setColumns(10);
		txtT.setBounds(1552, 355, 65, 22);
		contentPane.add(txtT);

		JButton OKGlobal = new JButton("OK");
		OKGlobal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// si imposta la t globale
				for (WaveData w : waves.keySet()) {
					panelGlobal.addWaveFormula(waves.get(w),
							w.getGlobalWave(Float.parseFloat(txtT.getText())));
					panelGlobal.repaint();
				}

			}
		});
		OKGlobal.setBounds(1629, 352, 54, 25);
		contentPane.add(OKGlobal);

		JButton OKLocal = new JButton("OK");
		OKLocal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// si imposta la x locale
				for (WaveData w : waves.keySet()) {
					panelLocal.addWaveFormula(waves.get(w),
							w.getLocalWave(Float.parseFloat(txtX.getText())));
					panelLocal.repaint();
				}
			}
		});
		OKLocal.setBounds(1629, 663, 54, 25);
		contentPane.add(OKLocal);

		btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelTotal.startWaveTimer();
			}
		});
		btnStart.setBounds(1532, 151, 73, 25);
		contentPane.add(btnStart);

		btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelTotal.stopWaveTimer();
			}
		});
		btnStop.setBounds(1617, 151, 73, 25);
		contentPane.add(btnStop);

		lblNewLabel = new JLabel("t=");
		lblNewLabel.setBounds(1532, 33, 56, 16);
		contentPane.add(lblNewLabel);

		txtTCorrente = new JTextField();
		txtTCorrente.setColumns(10);
		txtTCorrente.setBounds(1552, 30, 65, 22);
		contentPane.add(txtTCorrente);

		JButton btnNewButton = new JButton("SET");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelTotal.setTime(Float.parseFloat(txtTCorrente.getText()));
			}
		});
		btnNewButton.setBounds(1629, 29, 61, 25);
		contentPane.add(btnNewButton);
		
		JButton btnInterferisci = new JButton("Interferisci");
		btnInterferisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelTotal.addInterferenza();
				panelLocal.addInterferenza();
				panelGlobal.addInterferenza();
				repaint();
			}
		});
		btnInterferisci.setBounds(1537, 207, 97, 25);
		contentPane.add(btnInterferisci);

	}

	@Override
	public void WaveChanged(WaveData wave, String id) {
		waves.put(wave, id);
		// si aggiunge al pannello totale
		panelTotal.addWaveFormula(id, wave.getTotalWave());
		panelTotal.repaint();
		// si aggiunge al panello locale
		panelLocal.addWaveFormula(id, wave.getLocalWave(0f));
		txtX.setText("0.0");
		panelLocal.repaint();
		// si aggiunge al pannello global
		panelGlobal.addWaveFormula(id, wave.getGlobalWave(0f));
		txtT.setText("0.0");
		panelGlobal.repaint();
	}

	@Override
	public void currentTimer(float t) {
		txtTCorrente.setText(Float.toString(t));
	}
}
