package App;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.RenderedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileSystemView;

import com.sun.media.jai.codec.ByteArraySeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.SeekableStream;



public class MainWindow extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame MainWindow;
	private int anchowindow = 300;
	private int anchopanel;
	private int altopanel;
	
	/*
	 * File system view.
	 */
	protected static FileSystemView fsv = FileSystemView.getFileSystemView();
	
    static Image load(byte[] data) throws Exception{
        Image image = null;
        SeekableStream stream = new ByteArraySeekableStream(data);
        String[] names = ImageCodec.getDecoderNames(stream);
        ImageDecoder dec = 
          ImageCodec.createImageDecoder(names[0], stream, null);
        RenderedImage im = dec.decodeAsRenderedImage();
        image = PlanarImage.wrapRenderedImage(im).getAsBufferedImage();
        return image;
      }
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.MainWindow.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
public MainWindow() {
		initialize();
}

	
	@SuppressWarnings({ })
	private void initialize() {
		MainWindow = new JFrame();
		MainWindow.setTitle("HyCrop 1.0 Beta");
		JMenuBar menuBar = new JMenuBar();
		FileTreePanel ftp = new FileTreePanel();
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(353, 22, 100, 100));
		panel.setBackground(Color.WHITE);
		
				
		ftp.setBackground(UIManager.getColor("Button.background"));
		ftp.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ftp.setBounds(0, 22, 350, 400);
		ftp.setVisible(true);
		MainWindow.getContentPane().add(ftp);
		menuBar.setBounds(0, 0, 1008, 21);
		
		
		MainWindow.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				anchowindow = MainWindow.getWidth();
				menuBar.setBounds(0, 0, anchowindow, 21);
				anchopanel = MainWindow.getWidth() - ftp.getWidth();
				altopanel = MainWindow.getHeight() - menuBar.getHeight();
				panel.setBounds(new Rectangle(353, 22, anchopanel, altopanel));
				
			}
		});
		MainWindow.setBounds(100, 100, 1024, 768);
		MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainWindow.getContentPane().setLayout(null);
		
		menuBar.setAutoscrolls(true);
		menuBar.setMaximumSize(new Dimension(anchowindow, 2));
		menuBar.setBorder(UIManager.getBorder("MenuBar.border"));
		MainWindow.getContentPane().add(menuBar);
		
		
		
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMinimumSize(new Dimension(60, 0));
		menuBar.add(mnArchivo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnArchivo.add(mntmAbrir);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Guardar");
		mnArchivo.add(mntmNewMenuItem);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);
		
		JMenu mnNewMenu = new JMenu("Opciones");
		mnNewMenu.setMinimumSize(new Dimension(100, 0));
		menuBar.add(mnNewMenu);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem menuItem = new JMenuItem("?");
		mnAyuda.add(menuItem);
		
		String[] columnNames = {"Imagen", "X", "Y", "XSize", "YSize"};
        Object[][] data = {
            {"0001.tif", "adsad", "afsdas", "55555", "6666", 2},
            {"0002.tif", "ewrewr","afsdas", "55555", "6666", 4},
            {"0003.tif", "zxczxc","afsdas", "55555", "6666", 6},
            {"0001.tif", "adsad", "afsdas", "55555", "6666", 8},
            {"0002.tif", "ewrewr","afsdas", "55555", "6666", 10},
            {"0003.tif", "zxczxc","afsdas", "55555", "6666", 12},
            {"0001.tif", "adsad", "afsdas", "55555", "6666", 14},
            {"0002.tif", "ewrewr","afsdas", "55555", "6666", 16},
            {"0003.tif", "zxczxc","afsdas", "55555", "6666", 18},
            {"0001.tif", "adsad", "afsdas", "55555", "6666", 20},
            {"0002.tif", "ewrewr","afsdas", "55555", "6666", 22},
            {"0003.tif", "zxczxc","afsdas", "55555", "6666", 24}
        };

        JTable table = new JTable(data, columnNames);
		JScrollPane scrolltabla = new JScrollPane(table);
		scrolltabla.setBounds(0, 422, 350, 480);
		scrolltabla.setVisible(true);
		MainWindow.getContentPane().add(scrolltabla);
		MainWindow.getContentPane().add(panel);
		MainWindow.setLocationRelativeTo(null);
		panel.setVisible(true);
		
		
		
		// CARGA DE LA IMAGEN
		
		FileInputStream in;
		try {
			in = new FileInputStream("D:/PRUEBA/IMAGES/LIBROS/01/0001.tif");
			FileChannel channel = in.getChannel();
		    ByteBuffer buffer = ByteBuffer.allocate((int)channel.size());
		    channel.read(buffer);
		    Image image = load(buffer.array());
		    // make sure that the image is not too big
		    //  scale with a width of 500 
		    Image imageScaled = 
		      image.getScaledInstance(500, -1,  Image.SCALE_SMOOTH);
		    
		   
		    Graphics g = image.getGraphics();
		    
		   panel.paint(g);
		   panel.repaint();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
		
		
		
	

