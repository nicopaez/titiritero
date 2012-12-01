package fiuba.algo3.titiritero.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameLoop implements Runnable{

	private static final int FRECUENCIA_DEFAULT = 500;
	private Set<ObjetoVivo> objetosVivos;
	private Set<ObjetoDibujable> objetosDibujables;
	private List<ObservadorDeGameLoop> observadores;
	private boolean estaEjecutando;
	private int frecuencia;
	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}

	public boolean estaEjecutando() {
		return estaEjecutando;
	}

	public SuperficieDeDibujo getSuperficieDeDibujo() {
		return superficieDeDibujo;
	}

	private SuperficieDeDibujo superficieDeDibujo;
	private Thread hilo;
	
	public GameLoop(int frecuencia, SuperficieDeDibujo superficieDeDibujo) {
		this.frecuencia = frecuencia;
		this.superficieDeDibujo = superficieDeDibujo;
		this.objetosVivos = new HashSet<ObjetoVivo>();
		this.objetosDibujables= new HashSet<ObjetoDibujable>();
		this.observadores = new ArrayList<ObservadorDeGameLoop>();
	}

	public GameLoop(SuperficieDeDibujo superficieDeDibujo) {
		this(FRECUENCIA_DEFAULT, superficieDeDibujo);
	}

	public synchronized void agregar(ObjetoVivo objetoVivo) {
		this.objetosVivos.add(objetoVivo);
	}

	public synchronized void remover(ObjetoVivo objetoVivo) {
		this.objetosVivos.remove(objetoVivo);
	}

	public synchronized void agregar(ObjetoDibujable objetoDibujable) {
		this.objetosDibujables.add(objetoDibujable);
	}

	public synchronized void remover(ObjetoDibujable objetoDibujable) {
		this.objetosDibujables.remove(objetoDibujable);
	}
	
	@Override
	public void run() {
		while(this.estaEjecutando) {
			for(ObjetoVivo objetoVivo : this.objetosVivos) {
				objetoVivo.vivir();
			}
			for(ObjetoDibujable objetoDibujable : this.objetosDibujables) {
				objetoDibujable.dibujar(this.superficieDeDibujo);
			}
			this.superficieDeDibujo.actualizar();
			for(ObservadorDeGameLoop observador: this.observadores) {
				observador.notificarCicloFinalizado();
			}			
			try {
				Thread.sleep(this.frecuencia);
			} catch (InterruptedException e) {
				// TODO log de exception
				e.printStackTrace();
			}
		}
	}

	public int getCantidadDeObjetosVivos() {
		return this.objetosVivos.size();
	}

	public void iniciarEjecucion() {
		this.estaEjecutando = true;
		this.hilo = new Thread(this);
		this.hilo.start();
	}

	public void detenerEjecucion() {
		this.estaEjecutando = false;
		this.hilo.interrupt();
	}

	public synchronized void agregarObservador(ObservadorDeGameLoop unObservador) {
		this.observadores.add(unObservador);		
	}

	public synchronized void removerObservador(ObservadorDeGameLoop unObservador) {
		this.observadores.remove(unObservador);		
	}
}