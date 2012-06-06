package fiuba.algo3.titiritero.dibujables;

import java.awt.Graphics;
import org.junit.Test;
import org.mockito.Mockito;

import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public class CuadradoTest {

	@Test
	public void testDibujar() {
		Graphics graphics = Mockito.mock(Graphics.class);
		SuperficiePanel unaSuperficie = Mockito.mock(SuperficiePanel.class);
		Mockito.when(unaSuperficie.getBuffer()).thenReturn(graphics);
		ObjetoPosicionable objeto = Mockito.mock(ObjetoPosicionable.class);
		Mockito.when(objeto.getX()).thenReturn(5);
		Mockito.when(objeto.getY()).thenReturn(5);
		
		Cuadrado cuadrado = new Cuadrado(5,5, objeto);
		cuadrado.dibujar(unaSuperficie);
		
		Mockito.verify(graphics).setColor(cuadrado.getColor());
		Mockito.verify(graphics).fillRect(objeto.getX(), objeto.getY(),5,5);
	}

}
