package it.valsecchi.waver.formule;

/**
 * Classe che rappresenta tutti i dati dell'onda
 * @author Davide
 *
 */
public class WaveData {

	private float ampiezza;
	private  float periodo;
	private  float lambda;
	private float w;
	private float k;
	private  float frequenza;
	private  float fase0;
	
	public WaveData( float ampiezza, float periodo,float lambda,float k,float w,float frequenza,float fase0){
		this.ampiezza= ampiezza;
		this.periodo=periodo;
		this.lambda = lambda;
		this.w = w;
		this.k = k;
		this.frequenza= frequenza;
		this.fase0 = fase0;
	}
	
	public WaveFormula getLocalWave(int x){
		//si deve calcolare la fase iniziale
		//prima di tutto si trova l'h0 per quel punto al tempo 0
		float y0 = (float) (Math.sin(k*x+fase0));
		final float teta0 = (float) Math.asin(y0);
		WaveFormula formula= new WaveFormula(){
			@Override
			public float calculate(float t) {
				return (float) (ampiezza*Math.sin(w*t+teta0));
			}
		};
		formula.setWaveType(WaveType.LOCAL);
		return formula;
	}

	public float getAmpiezza() {
		return ampiezza;
	}

	public float getPeriodo() {
		return periodo;
	}

	public float getLambda() {
		return lambda;
	}

	public float getK() {
		return k;
	}

	public float getFrequenza() {
		return frequenza;
	}

	public float getFase0() {
		return fase0;
	}

	
}
