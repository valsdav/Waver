package it.valsecchi.waver.panel;

import it.valsecchi.waver.formule.WaveFormula;
import it.valsecchi.waver.formule.WaveType;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import javax.swing.JPanel;

public class WavePanel extends JPanel {

	private static final long serialVersionUID = -6584631792178767173L;
	private int width;
	private int height;
	private int maxX;
	private int maxY;
	private float fattoreX;
	private float fattoreY;
	private WaveType panel_type;
	private float time = 0;
	private Timer timer;
	private float time_add = 0.10f;
	private List<WaveFormula> formulae;

	public WavePanel(WaveType type, int x, int y, int width, int height,
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
		if (panel_type == WaveType.LOCAL || panel_type == WaveType.TOTAL) {
			timer = new Timer(100, new WaveTimer());
		}
		this.formulae = new ArrayList<>();
	}

	public boolean addWaveFormula(WaveFormula formula) {
		if (formula.getWaveType() == panel_type) {
			if (formulae.size() < 4) {
				formulae.add(formula);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
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
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);
		g.setColor(Color.red);
		g.drawLine(0, height / 2, width, height / 2);
		g.drawLine(0, 0, 0, height);
		// ora si disegna
		int c = 0;
		for (WaveFormula wave : formulae) {
			switch(c){
			case 0:
				printWave(g, wave, Color.BLUE);
				c+= 1;
				break;
			case 1: 
				printWave(g, wave, Color.GREEN);
				c+= 1;
				break;
			case 2:
				printWave(g, wave, Color.RED);
				c+= 1;
				break;
			}
		}
	}

	private void printWave(Graphics g, WaveFormula wave, Color wave_colour) {
		g.setColor(wave_colour);
		switch (panel_type) {
		case LOCAL:
			printPoint(g, 0, wave.calculate(0));
			// si prende ogni punto
			for (float xi = 1; xi < width; xi++) {
				float x2 = xi / fattoreX;
				printPoint(g, x2, wave.calculate(x2));
			}
		case GLOBAL:
			printPoint(g, 0, wave.calculate(0));
			// si prende ogni punto
			// si prende ogni punto
			// si prende ogni punto
			for (float xi = 1; xi < width; xi++) {
				float x2 = xi / fattoreX;
				printPoint(g, x2, wave.calculate(x2));
			}

		case TOTAL:
			printPoint(g, 0, wave.calculate(0, time));
			// si prende ogni punto
			// si prende ogni punto
			for (float xi = 1; xi < width; xi++) {
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
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
		this.fattoreY = height / ((float) 2 * maxY);
	}

	public List<WaveFormula> getFormulae() {
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

}
