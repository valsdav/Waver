package it.valsecchi.waver.panel;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import it.valsecchi.waver.formule.TimerListener;
import it.valsecchi.waver.formule.WaveData;
import it.valsecchi.waver.formule.WaveFormula;
import it.valsecchi.waver.formule.WaveManager;
import it.valsecchi.waver.formule.WaveType;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WavePanel extends JPanel implements WaveManager, TimerListener {

	private static final long serialVersionUID = -8343000845398126167L;
	private int width;
	private int height;
	private WaveType panel_type;
	private Map<String, WaveFormula> waves;
	private WavePanelGraph graph;
	private JPanel panel;

	public WavePanel(WaveType type, int x, int y, int width, int height,
			int maxX, int maxY) {
		this.setBounds(x, y, width, height);
		this.panel_type = type;
		this.width = width;
		this.height = height;
		this.waves = new HashMap<>();
		this.graph = new WavePanelGraph(type, 5, 5, width - (width / 6),
				height - 11, maxX, maxY);
		graph.addTimerListener(this);
		panel = this;
		panel.setLayout(null);
		panel.add(graph);
		initComponent();
	}

	private void initComponent(){
		//si aggiungono i pulsanti in base al tipo
		switch(panel_type){
		case TOTAL:
			
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawRect(0, 0, width - 1, height - 1);
	}

	@Override
	public void addWaveFormula(String id, WaveData wave) {
		WaveFormula formula = null;
		switch (panel_type) {
		case LOCAL:
			formula = wave.getLocalWave(0);
			break;
		case GLOBAL:
			formula = wave.getGlobalWave(0);
			break;
		case TOTAL:
			formula = wave.getTotalWave();
			break;
		}
		waves.put(id,formula);
		graph.addWaveFormula(id, formula);
		graph.repaint();
	}

	@Override
	public void removeWaveFormula(String id) {
		waves.remove(id);
		graph.removeWaveFormula(id);
		graph.repaint();
	}

	@Override
	public void currentTimer(float t) {

	}
}
