package it.valsecchi.waver.formule;

/**
 * Classe che rappresenta tutti i dati dell'onda
 * 
 * @author Davide
 * 
 */
public class WaveData {

	private float ampiezza;
	private float periodo;
	private float lambda;
	private float w;
	private float k;
	private float frequenza;
	private float veloc;
	private float fase0;

	public WaveData(float ampiezza, float periodo, float lambda, float fase0,
			float velocità) {
		this.ampiezza = ampiezza;
		this.periodo = periodo;
		this.lambda = lambda;
		this.w = (float) ((Math.PI * 2) / periodo);
		this.k = (float) ((Math.PI * 2) / lambda);
		this.frequenza = 1 / periodo;
		this.veloc = velocità;
		this.fase0 = fase0;
	}

	public WaveData() {
		this.ampiezza = 0;
		this.periodo = 0;
		this.lambda = 0;
		this.w = 0;
		this.k = 0;
		this.frequenza = 0;
		this.veloc = 0;
		this.fase0 = 0;
	}

	public WaveFormula getLocalWave(int x) {
		// si deve calcolare la fase iniziale
		// prima di tutto si trova l'h0 per quel punto al tempo 0
		float y0 = (float) (Math.sin(k * x + fase0));
		final float teta0 = (float) Math.asin(y0);
		WaveFormula formula = new WaveFormula() {
			@Override
			public float calculate(float t) {
				return (float) (ampiezza * Math.sin(w * t + teta0));
			}
		};
		formula.setWaveType(WaveType.LOCAL);
		return formula;
	}

	public WaveFormula getGlobalWave(float time) {
		WaveFormula formula = new WaveFormula() {
			@Override
			public float calculate(float x) {
				return (float) (ampiezza * Math.sin(k * x + fase0));
			}
		};
		formula.setWaveType(WaveType.GLOBAL);
		return formula;
	}

	public WaveFormula getTotalWave() {
		WaveFormula formula = new WaveFormula() {
			@Override
			public float calculate(float x, float t) {
				return (float) (ampiezza * Math.sin(k * x - veloc * t + fase0));
			}
		};
		formula.setWaveType(WaveType.TOTAL);
		return formula;
	}

	public static WaveFormula getWaveSomma(WaveType type,
			final WaveFormula... waves) {
		switch (type) {
		case LOCAL:
			WaveFormula formula = new WaveFormula() {
				@Override
				public float calculate(float x) {
					float result = 0;
					for (WaveFormula wave : waves) {
						result += wave.calculate(x);
					}
					return result;
				}
			};
			formula.setWaveType(WaveType.LOCAL);
			return formula;
		case GLOBAL:
			WaveFormula formula2 = new WaveFormula() {
				@Override
				public float calculate(float x) {
					float result = 0;
					for (WaveFormula wave : waves) {
						result += wave.calculate(x);
					}
					return result;
				}
			};
			formula2.setWaveType(WaveType.GLOBAL);
			return formula2;
		case TOTAL:
			WaveFormula formula3 = new WaveFormula() {
				@Override
				public float calculate(float x, float t) {
					float result = 0;
					for (WaveFormula wave : waves) {
						result += wave.calculate(x, t);
					}
					return result;
				}
			};
			formula3.setWaveType(WaveType.TOTAL);
			return formula3;
		}
		return null;
	}

	public float getAmpiezza() {
		return ampiezza;
	}

	public void setAmpiezza(float ampie) {
		this.ampiezza = ampie;
	}

	public float getLambda() {
		return lambda;
	}

	public void setLambda(float lam) {
		// si aggiorna la velocità
		this.lambda = lam;
		this.setVeloc();
	}

	public float getK() {
		return k;
	}

	public void setK() {
		this.k = (float) ((Math.PI * 2) / lambda);
	}

	public void setK(float k2) {
		// si aggiorna lambda
		this.lambda = (float) ((Math.PI * 2) / k);

	}

	public float getFrequenza() {
		return frequenza;
	}

	public void setFrequenza(float freq) {
		this.frequenza = freq;
		// si aggiorna la velocità
		this.setVeloc();
		// si aggiorna il periodo
		this.setPeriodo();
	}

	public void setFrequenza() {
		this.frequenza = 1 / periodo;
	}

	public float getPeriodo() {
		return periodo;
	}

	public void setPeriodo() {
		// si aggiorna il periodo
		this.periodo = 1 / frequenza;
		// si aggiorna w
		this.setW();
	}

	public void setPeriodo(float per) {
		// si aggiorna frequenza e w
		this.periodo = per;
		this.setFrequenza();
		// si aggiorna w
		this.setW();
	}

	public float getW() {
		return w;
	}

	public void setW() {
		// si aggiorna
		this.w = (float) ((Math.PI * 2) / periodo);
	}

	public void setW(float w2) {
		// si aggiorna periodo e frequenza
		this.w = w2;
		this.periodo = (float) ((Math.PI * 2) / w);
		this.setFrequenza();
	}

	public float getFase0() {
		return fase0;
	}

	public void setFase0(float fase0) {
		this.fase0 = fase0;
	}

	public float getVeloc() {
		return veloc;
	}

	public void setVeloc(float veloc) {
		this.veloc = veloc;
		// si imposta la nuova lambda
		// la frequenza non cambia automaticamente
		this.lambda = veloc / frequenza;
		// si cambia k
		this.setK();
	}

	public void setVeloc() {
		// si ricalcola la velocità
		this.veloc = lambda * frequenza;
	}

}
