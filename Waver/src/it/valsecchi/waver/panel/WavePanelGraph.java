package it.valsecchi.waver.panel;

import it.valsecchi.waver.formule.TimerListener;
import it.valsecchi.waver.formule.WaveData;
import it.valsecchi.waver.formule.WaveFormula;
import it.valsecchi.waver.formule.WaveManager;
import it.valsecchi.waver.formule.WaveType;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import javax.swing.JPanel;

public class WavePanelGraph extends JPanel {

	private static final long serialVersionUID = -6584631792178767173L;
	private int width;
	private int height;
	private int maxX;
	private int maxY;
	private float fattoreX;
	private float fattoreY;
	private int values_interval = 5;
	private WaveType panel_type;
	private float time = 0;
	private Timer timer;
	private float time_add = 0.1f;
	private Map<String, WaveFormula> formulae;
	private List<TimerListener> timer_listeners;

	public WavePanelGraph(WaveType type, int x, int y, int width, int height,
			int maxX, int maxY) {
		super();
		this.setBounds(x, y, width, height);
		this.width = width;
		this.height = height;
		this.maxX = maxX;
		this.maxY = maxY;
		this.fattoreX = width / ((float) maxX);
		this.fattoreY = height / ((float) 2 * maxY);
		this.panel_type = type;
		if (panel_type == WaveType.TOTAL) {
			timer = new Timer(100, new WaveTimer());
		}
		this.formulae = new HashMap<>();
		this.timer_listeners = new ArrayList<>();
	}

	public void addWaveFormula(String id, WaveFormula formula) {
		if (formula.getWaveType() == panel_type) {
			formulae.put(id, formula);
		}
	}

	public void removeWaveFormula(String id) {
		if (formulae.containsKey(id)) {
			formulae.remove(id);
		}
	}

	public void addTimerListener(TimerListener t) {
		timer_listeners.add(t);
	}

	public void startWaveTimer() {
		timer.start();
	}

	public void stopWaveTimer() {
		timer.stop();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// si disegnano gli assi
		g.setColor(Color.red);
		g.drawLine(0, height / 2, width, height / 2);
		g.drawLine(0, 0, 0, height);
		// si disegnano i valori
		this.printValues(g, values_interval);
		// ora si disegna
		int paint_index = 0;
		for (String id : formulae.keySet()) {
			if (id.equals("interferenza")) {
				printWave(g, formulae.get(id), Color.RED);
			}else if((paint_index%2)==0){
				printWave(g, formulae.get(id), Color.BLUE);
				paint_index+=1;
			}else if((paint_index%2)==1){
				printWave(g, formulae.get(id), Color.GREEN);
				paint_index+=1;
			}
		}
	}

	private void printWave(Graphics g, WaveFormula wave, Color wave_colour) {
		g.setColor(wave_colour);
		switch (panel_type) {
		case LOCAL:
			printPoint(g, 0, wave.calculate(0));
			// si prende ogni punto
			for (float xi = 0.5f; xi < width; xi += 0.5) {
				float x2 = xi / fattoreX;
				printPoint(g, x2, wave.calculate(x2));
			}
		case GLOBAL:
			printPoint(g, 0, wave.calculate(0));
			// si prende ogni punto
			for (float xi = 0.5f; xi < width; xi += 0.5f) {
				float x2 = xi / fattoreX;
				printPoint(g, x2, wave.calculate(x2));
			}
		case TOTAL:
			printPoint(g, 0, wave.calculate(0, time));
			// si prende ogni punto
			// si prende ogni punto
			for (float xi = 0.5f; xi < width; xi += 0.5f) {
				float x2 = xi / fattoreX;
				printPoint(g, x2, wave.calculate(x2, time));
			}
		}
	}

	private void printPoint(Graphics g, float x, float y) {
		// si disegna il punto
		int xv = Math.round(x * fattoreX);
		int yv = height - Math.round((y + maxY) * fattoreY);
		g.drawLine(xv, yv, xv, yv);
	}

	private class WaveTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			time += time_add;
			// si ridisegna
			repaint();
			// si avvisa
			for (TimerListener t : timer_listeners) {
				t.currentTimer(time);
			}
		}
	}

	private void printValues(Graphics g, int interval) {
		for (int i = 0; i <= maxX; i += interval) {
			g.drawLine(Math.round(i * fattoreX), height / 2 + 5,
					Math.round(i * fattoreX), height / 2 - 5);
			g.drawString(Integer.toString(i), Math.round(i * fattoreX),
					height / 2 + 20);
		}
		for (int z = -maxY; z <= maxY; z += interval) {
			g.drawLine(0, height / 2 - Math.round(z * fattoreY), 10, height / 2
					- Math.round(z * fattoreY));
			g.drawString(Integer.toString(z), 15,
					height / 2 - Math.round((z * fattoreY)));
		}
		// si scrivono le unità di misura
		g.drawString("y", 50, 20);
		switch (this.panel_type) {
		case TOTAL:
		case GLOBAL:
			g.drawString("m(metri)", width - 55, height / 2 - 10);
			break;
		case LOCAL:
			g.drawString("t(secondi)", width - 55, height / 2 - 10);
			break;
		}
	}

	public void addInterferenza() {
		if (formulae.size() >= 2) {
			List<WaveFormula> fors = new ArrayList<>();
			for (String id:formulae.keySet()){
				if(!id.equals("interferenza")){
					fors.add(formulae.get(id));
				}
			}
			WaveFormula somma = WaveData.getWaveSomma(this.panel_type, fors);
			this.addWaveFormula("interferenza", somma);
			repaint();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
		this.fattoreX = width / ((float) maxX);
		repaint();
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
		this.fattoreY = height / ((float) 2 * maxY);
		repaint();
	}

	public Collection<WaveFormula> getFormulae() {
		return formulae.values();
	}
	
	public Map<String,WaveFormula> getFormulaeMap(){
		return formulae;
	}

	public WaveType getPanel_type() {
		return panel_type;
	}

	public float getTime_add() {
		return time_add;
	}

	public void setTime_add(float time_add) {
		this.time_add = time_add;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float t) {
		time = t;
		repaint();
	}

	public int getValues_interval() {
		return values_interval;
	}

	public void setValues_interval(int values_interval) {
		this.values_interval = values_interval;
	}
}
