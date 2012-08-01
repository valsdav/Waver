package it.valsecchi.waver.panel;

import it.valsecchi.waver.formule.WaveData;
import it.valsecchi.waver.formule.WaveType;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WaveViewer extends JFrame implements WaveChangedListener {

	private static final long serialVersionUID = -275640641614487453L;
	private JPanel contentPane;
	private Map<String,WaveData> waves;
	private WaveCreator creator;
	private WavePanel panelLocal;
	private WavePanel panelGlobal;
	private WavePanel panelTotal;

	public WaveViewer() {
		waves = new HashMap<>();
		this.initComponent();
		creator = new WaveCreator();
		creator.addWaveChangedListener(this);
		creator.setVisible(true);
	}
	
	private void initComponent(){
		setTitle("Waver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,1800,1050);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//si creano i pannelli
		panelLocal = new WavePanel(WaveType.LOCAL,5,5,1500,320,100,30);
		contentPane.add(panelLocal);
		panelGlobal = new WavePanel(WaveType.GLOBAL,5,330,1500,320,100,30);
		contentPane.add(panelGlobal);
		panelTotal = new WavePanel(WaveType.TOTAL,5,655,1500,320,100,30);
		contentPane.add(panelTotal);
	}

	@Override
	public void WaveChanged(WaveData wave, String id) {
		if(waves.containsKey(id)){
			WaveData w = waves.get(id);
			w = wave;
		}else{
			waves.put(id, wave);
		}
	}

}
