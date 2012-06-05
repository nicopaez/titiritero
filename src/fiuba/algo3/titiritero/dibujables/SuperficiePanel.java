package fiuba.algo3.titiritero.dibujables;

import java.awt.Graphics;

import java.awt.Image;

import javax.swing.JPanel;

import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class SuperficiePanel extends JPanel implements SuperficieDeDibujo {

	private static final long serialVersionUID = 1L;
	private Image imagen;

	// es llamado internamente por el metodo repaint() de la clase Frame
	public void update(Graphics g) {
		paint(g);
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
	
	public void paint(Graphics g) {
		System.out.println("repintando");
		g.drawImage(this.imagen, 0, 0, null);
		this.limpiar();
	}
	
	public void actualizar() {
		this.repaint();
	}

	private void limpiar() {
		if(this.imagen == null)
			this.imagen = this.createImage(getSize().width, getSize().height);
		Graphics superficieParaDibujar =  this.imagen.getGraphics();
		superficieParaDibujar.setColor(this.getBackground());// 
		superficieParaDibujar.fillRect(0, 0, this.getSize().width, this.getSize().height);
	}

	public Graphics getBuffer(){
		return this.imagen.getGraphics();
	}
}