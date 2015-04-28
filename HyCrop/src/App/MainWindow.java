package App;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.RenderedImage;

import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DebugGraphics;



public class MainWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame MainWindow;
	private int anchowindow = 300;
	private int anchopanel;
	private int altopanel;
	private JScrollPane jsp=new JScrollPane();
	JPanel panelImagen;
	
	
	
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
		jsp.setPreferredSize(new Dimension(10, 10));
		jsp.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		jsp.setAutoscrolls(true);
		
		panel.add(jsp);
		MainWindow.setLocationRelativeTo(null);
		panel.setVisible(true);
		
        
		
		//ShowImage si = new ShowImage("D:/PRUEBA/IMAGES/LIBROS/01/0002.tif");
		//JPanel panelImagen = si.escalar(0.16F);
		//panel.setLayout(null);
		//jsp.setViewportView(panelImagen);
		
		
		MainWindow.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				anchowindow = MainWindow.getWidth();
				menuBar.setBounds(0, 0, anchowindow, 21);
				System.out.println("Ancho antes: " + anchopanel);
				anchopanel = MainWindow.getWidth() - ftp.getWidth();
				altopanel = MainWindow.getHeight() - menuBar.getHeight();
				panel.setBounds(new Rectangle(353, 22, anchopanel, altopanel));
				panel.repaint();
				jsp.setBounds(0, 0, anchopanel-20, altopanel-40);
				
				jsp.setViewportView(panelImagen);
				jsp.repaint();
				
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		JOptionPane.showMessageDialog(null, data[table.getSelectedRow()][0]);
        		mostrarImagen(jsp,"D:/PRUEBA/IMAGES/LIBROS/01/0001.tif");
        		panel.add(jsp);
        		jsp.repaint();
        	}
        });

}
		 
		
	public void mostrarImagen(JScrollPane panelScroll,String imagen){
		ShowImage si = new ShowImage("D:/PRUEBA/IMAGES/LIBROS/01/0003.tif");
		JPanel panelImagen=si.escalar(0.16F);
		panelScroll.setLayout(null);
		panelScroll.setViewportView(panelImagen);
	}
	
	public class ListenForMaximize extends WindowAdapter {
	    public void windowActivated(WindowEvent e) {
	      MainWindow.invalidate();
	      MainWindow.validate();
	      MainWindow.repaint();
	      }
	  }
}
		
		
		
	

