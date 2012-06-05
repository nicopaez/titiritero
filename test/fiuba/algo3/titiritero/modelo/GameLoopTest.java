package fiuba.algo3.titiritero.modelo;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GameLoopTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void iniciarEjecucionDeberiaInvocarVivir() throws InterruptedException {
		ObjetoVivo unObjetoVivo = Mockito.mock(ObjetoVivo.class);
		ObjetoVivo otroObjetoVivo = Mockito.mock(ObjetoVivo.class);		
		SuperficieDeDibujo superficieDeDibujo = Mockito.mock(SuperficieDeDibujo.class);
		int frecuencia = 500;
		GameLoop gameLoop = new GameLoop(frecuencia, superficieDeDibujo);
		gameLoop.agregar(unObjetoVivo);
		gameLoop.agregar(otroObjetoVivo);

		gameLoop.iniciarEjecucion();
		Thread.sleep(200);
		gameLoop.detenerEjecucion();
		
		Mockito.verify(unObjetoVivo).vivir();
		Mockito.verify(otroObjetoVivo).vivir();
	}

	@Test
	public void iniciarEjecucionDeberiaInvocarDibujar() throws InterruptedException {
		ObjetoDibujable unObjetoDibujable = Mockito.mock(ObjetoDibujable.class);
		ObjetoDibujable otroObjetoDibujable = Mockito.mock(ObjetoDibujable.class);
		SuperficieDeDibujo superficieDeDibujo = Mockito.mock(SuperficieDeDibujo.class);
		int frecuencia = 500;
		GameLoop gameLoop = new GameLoop(frecuencia, superficieDeDibujo);
		gameLoop.agregar(unObjetoDibujable);
		gameLoop.agregar(otroObjetoDibujable);

		gameLoop.iniciarEjecucion();
		Thread.sleep(200);
		gameLoop.detenerEjecucion();
		
		Mockito.verify(unObjetoDibujable).dibujar(superficieDeDibujo);
		Mockito.verify(otroObjetoDibujable).dibujar(superficieDeDibujo);
	}

	@Test
	public void iniciarEjecucionDeberiaInvocarLimpiar() throws InterruptedException {
		SuperficieDeDibujo unaSuperficieDeDibujo = Mockito.mock(SuperficieDeDibujo.class);
		int frecuencia = 500;
		GameLoop gameLoop = new GameLoop(frecuencia, unaSuperficieDeDibujo);

		gameLoop.iniciarEjecucion();
		Thread.sleep(200);
		gameLoop.detenerEjecucion();
		
		Mockito.verify(unaSuperficieDeDibujo).actualizar();
	}

	@Test
	public void agregarNoDeberiaAgregarDuplicador() {
		ObjetoVivo unObjetoVivo = Mockito.mock(ObjetoVivo.class);
		SuperficieDeDibujo superficieDeDibujo = Mockito.mock(SuperficieDeDibujo.class);
		GameLoop gameLoop = new GameLoop(superficieDeDibujo);
		gameLoop.agregar(unObjetoVivo);
		gameLoop.agregar(unObjetoVivo);

		Assert.assertEquals(1, gameLoop.getCantidadDeObjetosVivos());
	}

}
