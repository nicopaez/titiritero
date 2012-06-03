package fiuba.algo3.titiritero.modelo;

import java.util.HashSet;
import java.util.Set;

public class GameLoop extends Thread {

	private static final int FRECUENCIA_DEFAULT = 500;
	private Set<ObjetoVivo> objetosVivos;
	private boolean estaEjecutando;
	private int frecuencia;
	
	
	public GameLoop(int frecuencia) {
		this.frecuencia = frecuencia;
		this.objetosVivos = new HashSet<ObjetoVivo>();
	}

	public GameLoop()  {
		this(FRECUENCIA_DEFAULT);
	}

	public void agregar(ObjetoVivo objetoVivo) {
		this.objetosVivos.add(objetoVivo);
	}

	public void remover(ObjetoVivo objetoVivo) {
		this.objetosVivos.remove(objetoVivo);
	}

	@Override
	public void run() {
		while(this.estaEjecutando) {
			for(ObjetoVivo objetoVivo : this.objetosVivos) {
				objetoVivo.vivir();
			}
			try {
				Thread.sleep(this.frecuencia);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getCantidadDeObjetosVivos() {
		return this.objetosVivos.size();
	}

	public void iniciarEjecucion() {
		this.estaEjecutando = true;
		this.start();
	}

	public void detenerEjecucion() {
		this.interrupt();
		this.estaEjecutando = false;
	}
}