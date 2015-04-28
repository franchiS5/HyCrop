package App;

import java.awt.BorderLayout;
import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sun.media.jai.widget.DisplayJAI;

import javax.swing.ScrollPaneConstants;

public class ShowImage{

	private RenderedOp image;
	private DisplayJAI dj;
	private String imagen;
	private float escala;
	private float escala_alto;
	private float escala_ancho;
	
	public ShowImage(String imagen){
		this.imagen=imagen;
		image = JAI.create("fileload", imagen);
	}
	
	public JPanel mostrar(){
		dj = new DisplayJAI(image);
		return dj;
	}
	
	public JPanel escalar(float w, float h){
		
		System.out.println("W: " + w + " " + "H: " + h);
		System.out.println("imageW: " + image.getWidth() + " " + "imageH: " + image.getHeight());
		
		/*if (w > h){
			lmR = w;
		}else{
			lmR = h;
		}
		
		if (image.getHeight() > image.getWidth()){
			lmI = image.getHeight();
			if (lmI > lmR){
				escala = lmR / lmI;
			}else{
				escala = lmI / lmR;
			}
			//escala = h / lmI;
		}else{
			lmI= image.getWidth();
			if (lmI > lmR){
				escala = lmI / lmR;
			}else{
				escala = lmR / lmI;
			}
			
			//escala = w / lmI;
		}*/
		
		escala_alto = h / image.getHeight();
		escala_ancho = w / image.getWidth();
		
		if(escala_ancho >= escala_alto){
			
			escala = escala_alto;
		}else{
			escala = escala_ancho;
		}
		
		
		
		
		System.out.println("Ancho dj antes: " + image.getWidth() + " Alto  antes: " + image.getHeight());
		
		ParameterBlock pb = new ParameterBlock();
	     pb.addSource(image); // imagen origen
	     pb.add(escala); // escala x
	     pb.add(escala); // escala y
	     pb.add(0.0F); // translation x
	     pb.add(0.0F); // translation y
	     pb.add(new InterpolationNearest() ); // ;InterpolationNeaerst());
	     RenderedOp image2 = JAI.create("scale", pb);
	     dj=new DisplayJAI(image2);
	     
	     System.out.println("Ancho dj nuevo: " + image2.getWidth() + " Alto  nuevo: " + image2.getHeight());
	     return dj;
	}
}
