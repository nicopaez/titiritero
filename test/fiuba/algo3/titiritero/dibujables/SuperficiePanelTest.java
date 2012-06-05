package fiuba.algo3.titiritero.dibujables;

import java.awt.Graphics;
import java.awt.Image;

import org.junit.Test;
import org.mockito.Mockito;

public class SuperficiePanelTest {

	@Test
	public void paintDeberiaInvocarDrawImageYLimpiar() {
		Graphics graphics = Mockito.mock(Graphics.class);
		Image imagen = Mockito.mock(Image.class);
		SuperficiePanel panel = new SuperficiePanel();
		Mockito.when(imagen.getGraphics()).thenReturn(graphics);
		panel.setImagen(imagen);
		panel.paint(graphics);
		
		Mockito.verify(graphics).drawImage(imagen, 0,0,null);
		Mockito.verify(graphics).setColor(panel.getBackground());
		Mockito.verify(graphics).fillRect(0, 0, panel.getWidth(), panel.getHeight());
	}

}
