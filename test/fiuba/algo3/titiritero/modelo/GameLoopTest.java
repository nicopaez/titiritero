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
	public void ejecutarDeberiaInvocarVivir() throws InterruptedException {
		ObjetoVivo unObjetoVivo = Mockito.mock(ObjetoVivo.class);
		ObjetoVivo otroObjetoVivo = Mockito.mock(ObjetoVivo.class);
		int frecuencia = 500;
		GameLoop gameLoop = new GameLoop(frecuencia);
		gameLoop.agregar(unObjetoVivo);
		gameLoop.agregar(otroObjetoVivo);

		gameLoop.iniciarEjecucion();
		Thread.sleep(200);
		gameLoop.detenerEjecucion();
		
		Mockito.verify(unObjetoVivo).vivir();
		Mockito.verify(otroObjetoVivo).vivir();
	}

	@Test
	public void agregarNoDeberiaAgregarDuplicador() {
		ObjetoVivo unObjetoVivo = Mockito.mock(ObjetoVivo.class);
		GameLoop gameLoop = new GameLoop();
		gameLoop.agregar(unObjetoVivo);
		gameLoop.agregar(unObjetoVivo);

		Assert.assertEquals(1, gameLoop.getCantidadDeObjetosVivos());
	}

}
