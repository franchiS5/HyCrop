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
	
	public ShowImage(String imagen){
		this.imagen=imagen;
		image = JAI.create("fileload", imagen);
	}
	
	public JPanel mostrar(){
		dj = new DisplayJAI(image);
		return dj;
	}
	
	public JPanel escalar(float escala){
		
		ParameterBlock pb = new ParameterBlock();
	     pb.addSource(image); // imagen origen
	     pb.add(escala); // escala x
	     pb.add(escala); // escala y
	     pb.add(0.0F); // translation x
	     pb.add(0.0F); // translation y
	     pb.add(new InterpolationNearest() ); // ;InterpolationNeaerst());
	     RenderedOp image2 = JAI.create("scale", pb);
	     dj=new DisplayJAI(image2);
	     
	     
	     return dj;
	}
}
