package it.valsecchi.waver.panel;

import it.valsecchi.waver.formule.WaveType;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class WaveViewer extends JFrame {

	private static final long serialVersionUID = -275640641614487453L;
	private JPanel contentPane;
	private WaveCreator creator;
	private WavePanel panelTotal;
	private WavePanel panelGlobal;
	private WavePanel panelLocal;
	private JLabel lblNewLabel;

	public WaveViewer() {
		creator = new WaveCreator();
		creator.setVisible(true);
		this.initComponent();
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
		panelTotal = new WavePanel(WaveType.TOTAL, 5, 5, 1500, 320, 100);
		panelGlobal = new WavePanel(WaveType.GLOBAL, 5, 330, 1500, 320, 100);
		panelLocal = new WavePanel(WaveType.LOCAL, 5, 655, 1500, 320, 100);
		contentPane.add(panelTotal);
		contentPane.add(panelGlobal);
		contentPane.add(panelLocal);
		creator.addWaveManager(panelGlobal);
		creator.addWaveManager(panelLocal);
		creator.addWaveManager(panelTotal);
		
	}
}
