package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.JMenu;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainWindow {

	private JFrame MainWindow;
	private int anchowindow = 300;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainWindow = new JFrame();
		JMenuBar menuBar = new JMenuBar();
		MainWindow.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				System.out.println("caca");
				anchowindow = MainWindow.getWidth();
				menuBar.setBounds(0, 0, anchowindow, 21);
				
			}
		});
		MainWindow.setBounds(100, 100, 450, 300);
		MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainWindow.getContentPane().setLayout(null);
		
		menuBar.setAutoscrolls(true);
		menuBar.setMaximumSize(new Dimension(anchowindow, 2));
		menuBar.setBorder(UIManager.getBorder("MenuBar.border"));
		menuBar.setBounds(0, 0, anchowindow, 21);
		MainWindow.getContentPane().add(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMinimumSize(new Dimension(60, 0));
		menuBar.add(mnArchivo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnArchivo.add(mntmAbrir);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Guardar");
		mnArchivo.add(mntmNewMenuItem);
		
		JMenu mnNewMenu = new JMenu("Opciones");
		mnNewMenu.setMinimumSize(new Dimension(100, 0));
		menuBar.add(mnNewMenu);
	}
}
