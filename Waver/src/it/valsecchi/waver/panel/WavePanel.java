package it.valsecchi.waver.panel;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import it.valsecchi.waver.formule.TimerListener;
import it.valsecchi.waver.formule.WaveData;
import it.valsecchi.waver.formule.WaveFormula;
import it.valsecchi.waver.formule.WaveManager;
import it.valsecchi.waver.formule.WaveType;
import javax.swing.JPanel;

public class WavePanel extends JPanel implements WaveManager, TimerListener {

	private static final long serialVersionUID = -8343000845398126167L;
	private int width;
	private int height;
	private WaveType panel_type;
	private TotalWaveWidget total_widget;
	private LocalWaveWidget local_widget;
	private GlobalWaveWidget global_widget;
	private Map<String, WaveData> waves;
	private WavePanelGraph graph;
	private JPanel panel;

	public WavePanel(WaveType type, int x, int y, int width, int height,
			int maxX) {
		this.setBounds(x, y, width, height);
		this.panel_type = type;
		this.width = width;
		this.height = height;
		this.waves = new HashMap<>();
		// maxY impostata di default a 10;
		this.graph = new WavePanelGraph(type, 5, 5, width - (width / 6),
				height - 11, maxX, 5);
		graph.addTimerListener(this);
		panel = this;
		panel.setLayout(null);
		panel.add(graph);
		initComponent();
	}

	/**
	 * In questo metodo vengono inseriti i pannelli "widget" a seconda del tipo
	 * di pannello.
	 */
	private void initComponent() {
		// si aggiungono i pulsanti in base al tipo
		switch (panel_type) {
		case TOTAL:
			total_widget = new TotalWaveWidget();
			total_widget.setBounds(width - (width / 6), 5, width / 6 - 5,
					height - 11);
			panel.add(total_widget);
			total_widget.txtTime.setText("0.0");
			total_widget.btnSetTime.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graph.setTime(Float.parseFloat(total_widget.txtTime
							.getText()));
				}
			});
			total_widget.btnStart.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graph.startWaveTimer();
				}
			});
			total_widget.btnStop.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graph.stopWaveTimer();
				}
			});
			total_widget.btnInterferenza
					.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							graph.addInterferenza();
						}
					});
			total_widget.btnMaxX.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graph.setMaxX(Math.round(Float
							.parseFloat(total_widget.txtMaxX.getText())));
				}
			});
			total_widget.btnMaxY.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graph.setMaxY(Math.round(Float
							.parseFloat(total_widget.txtMaxY.getText())));
				}
			});
			total_widget.btnInterval.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graph.setValues_interval(Math.round(Float
							.parseFloat(total_widget.txtInterval.getText())));
				}
			});
			break;
		case LOCAL:
			local_widget = new LocalWaveWidget();
			local_widget.setBounds(width - (width / 6), 5, width / 6 - 5,
					height - 11);
			panel.add(local_widget);
			local_widget.txtPosition.setText("0.0");
			local_widget.btnSetX.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					for (String id : waves.keySet()) {
						graph.addWaveFormula(
								id,
								waves.get(id)
										.getLocalWave(
												Float.parseFloat(local_widget.txtPosition
														.getText())));
					}
					if (graph.containsFormula("intererenza")) {
						graph.addInterferenza();
					}
					graph.repaint();
				}
			});
			local_widget.btnInterferenza
					.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							graph.addInterferenza();
						}
					});
			local_widget.btnMaxX.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graph.setMaxX(Math.round(Float
							.parseFloat(local_widget.txtMaxX.getText())));
				}
			});
			local_widget.btnMaxY.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graph.setMaxY(Math.round(Float
							.parseFloat(local_widget.txtMaxY.getText())));
				}
			});
			local_widget.btnInterval.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graph.setValues_interval(Math.round(Float
							.parseFloat(local_widget.txtInterval.getText())));
				}
			});
			break;
		case GLOBAL:
			global_widget = new GlobalWaveWidget();
			global_widget.setBounds(width - (width / 6), 5, width / 6 - 5,
					height - 11);
			panel.add(global_widget);
			global_widget.txtTime.setText("0.0");
			global_widget.btnSetTime.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					for (String id : waves.keySet()) {
						graph.addWaveFormula(
								id,
								waves.get(id).getGlobalWave(
										Float.parseFloat(global_widget.txtTime
												.getText())));
					}
					if (graph.containsFormula("interferenza")) {
						graph.addInterferenza();
					}
					graph.repaint();
				}
			});
			global_widget.btnInterferenza
					.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							graph.addInterferenza();
						}
					});
			global_widget.btnMaxX.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graph.setMaxX(Math.round(Float
							.parseFloat(global_widget.txtMaxX.getText())));
				}
			});
			global_widget.btnMaxY.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graph.setMaxY(Math.round(Float
							.parseFloat(global_widget.txtMaxY.getText())));
				}
			});
			global_widget.btnInterval.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graph.setValues_interval(Math.round(Float
							.parseFloat(global_widget.txtInterval.getText())));
				}
			});
			break;
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
		waves.put(id, wave);
		graph.addWaveFormula(id, formula);
		graph.setMaxY(graph.getMaxY() + (int) wave.getAmpiezza());
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
		switch (panel_type) {
		case TOTAL:
			total_widget.txtTime.setText(Float.toString(t));
		}
	}
}
