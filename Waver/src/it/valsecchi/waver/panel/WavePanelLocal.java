package it.valsecchi.waver.panel;

import it.valsecchi.waver.formule.WaveFormula;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class WavePanelLocal extends JPanel {

	private static final long serialVersionUID = -6584631792178767173L;
	private WaveFormula wave;
	private int width;
	private int height;
	private int maxX;
	private int maxY;
	private float fattoreX;
	private float fattoreY;

	public WavePanelLocal(WaveFormula wave, int x, int y, int width,
			int height, int maxX, int maxY) {
		super();
		this.setBounds(x, y, width, height);
		this.width = width;
		this.height = height;
		this.wave = wave;
		this.maxX = maxX;
		this.maxY = maxY;
		this.fattoreX = width / ((float) maxX);
		this.fattoreY = height / ((float) 2 * maxY);
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
		printWave(g);
	}

	private void printWave(Graphics g) {
		g.setColor(Color.blue);
		printPoint(g, 0, wave.calculate(0));
		// si prende ogni punto
		for (float xi = 1; xi < width; xi++) {
			float x2 = xi / fattoreX;
			printPoint(g,x2,wave.calculate(x2));
		}
	}

	private void printPoint(Graphics g, float x, float y) {
		// si disegna il punto
		int xv = Math.round(x * fattoreX);
		int yv = height-Math.round((y+maxY) * fattoreY);
		g.drawLine(xv, yv, xv, yv);
	}

}
