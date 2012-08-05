package it.valsecchi.waver.formule;

/**
 * Classe astratta che fornisce i metodi per ricavare i dati da una WaveFormula in vari tipi:
 * -punto di vista locale
 * -punto di vista globale
 * -punto di vista totale
 * @author Davide
 *
 */
public abstract class WaveFormula {
	private WaveType waveType;
	public  abstract float calculate(float x);
	public abstract float calculate(float x,float t);
	public WaveType getWaveType() {
		return waveType;
	}
	public void setWaveType(WaveType waveType) {
		this.waveType = waveType;
	}	
	
}
