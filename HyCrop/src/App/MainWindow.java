package App;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;



public class MainWindow extends JPanel{

	private JFrame MainWindow;
	private int anchowindow = 300;
	
	/*
	 * File system view.
	 */
	protected static FileSystemView fsv = FileSystemView.getFileSystemView();

	
	
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
		menuBar.setBounds(0, 0, 1008, 21);
		MainWindow.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				anchowindow = MainWindow.getWidth();
				menuBar.setBounds(0, 0, anchowindow, 21);
				
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
		
		
		
		//treeframe.getContentPane().add(new FileTreePanel());
		
		//JInternalFrame internalFrame = new JInternalFrame("");
		//internalFrame.setFrameIcon(null);
		//internalFrame.setBorder(UIManager.getBorder("Tree.editorBorder"));
		//internalFrame.setBounds(0, 22, 250, 200);
		//internalFrame.getContentPane().add(new FileTreePanel());
		//MainWindow.getContentPane().add(internalFrame);
		FileTreePanel ftp = new FileTreePanel();
		ftp.setBounds(0, 22, 200, 200);
		ftp.setVisible(true);
		MainWindow.getContentPane().add(ftp);
		//internalFrame.setVisible(true);
		
		
		
	}
}
		
		
		
	

