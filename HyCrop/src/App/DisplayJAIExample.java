package App;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.InterpolationBilinear;
import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.sun.media.jai.widget.DisplayJAI;
  
 /**
  * This application shows how to use the DisplayJAI class with a JScrollPane to display images.
  */
 public class DisplayJAIExample
   {
   /**
    * Entry point for this application.
    * @param args an image file name.
    */
   public static void main(String[] args)
     {
     // Load the image which file name was passed as the first argument to the
     // application.
     RenderedOp image = JAI.create("fileload", "D:/PRUEBA/IMAGES/LIBROS/TEST/BUS_A040360_0000.tif");
     
     // PRUEBAS REESCALADO
     //***************************************************************
     
     float escala = 0.16F;
     
     ParameterBlock pb = new ParameterBlock();
     pb.addSource(image); // imagen origen
     pb.add(escala); // escala x
     pb.add(escala); // escala y
     pb.add(0.0F); // translation x
     pb.add(0.0F); // translation y
     pb.add(new InterpolationNearest() ); // ;InterpolationNeaerst());
     RenderedOp image2 = JAI.create("scale", pb);
     
     
     // **************************************************************
     
     // Get some information about the image
     String imageInfo = "Dimensiones: "+image.getWidth()+"x"+image.getHeight()+ " Canales:"+image.getNumBands();
     // Create a frame for display.
     JFrame frame = new JFrame();
     frame.setTitle("Visualizador JAI");
     // Get the JFrame's ContentPane.
     Container contentPane = frame.getContentPane();
     contentPane.setLayout(new BorderLayout());
     contentPane.setSize(800, 600);
     contentPane.setBackground(Color.BLUE);
     // Create an instance of DisplayJAI.
     DisplayJAI dj = new DisplayJAI(image2);
     
     
     
     // Add to the JFrame's ContentPane an instance of JScrollPane containing the
     // DisplayJAI instance.
     contentPane.add(new JScrollPane(dj),BorderLayout.CENTER);
     
     // Add a text label with the image information.
     contentPane.add(new JLabel(imageInfo),BorderLayout.SOUTH);
     // Set the closing operation so the application is finished.
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
     Dimension dim;
     dim = frame.getToolkit().getScreenSize(); // detectamos el tamaño de la pantalla
     frame.setSize(900, 700); // adjust the frame size.
     frame.setLocationRelativeTo(null);
     frame.setVisible(true); // show the frame.
     }
   }