package fiuba.algo3.titiritero.dibujables;

import java.awt.Graphics;

import org.junit.Test;
import org.mockito.Mockito;

import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public class CirculoTest {

	@Test
	public void testDibujar() {
		Graphics graphics = Mockito.mock(Graphics.class);
		SuperficiePanel unaSuperficie = Mockito.mock(SuperficiePanel.class);
		Mockito.when(unaSuperficie.getBuffer()).thenReturn(graphics);
		ObjetoPosicionable objeto = Mockito.mock(ObjetoPosicionable.class);
		Mockito.when(objeto.getX()).thenReturn(5);
		Mockito.when(objeto.getY()).thenReturn(5);
		
		Circulo circulo = new Circulo(5, objeto);
		circulo.dibujar(unaSuperficie);
		
		Mockito.verify(graphics).setColor(circulo.getColor());
		Mockito.verify(graphics).fillOval(objeto.getX(), objeto.getY(), circulo.getRadio(), circulo.getRadio());
	}
}
