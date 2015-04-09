package App;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class MainWindow {

	private JFrame MainWindow;
	private int anchowindow = 300;
	private JTree fileTree;
	private FileSystemModel fileSystemModel;
	File unidades[];
	Object items[];
	String devices;
	
	

	  
	  
	
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
		
		JComboBox combo = new JComboBox();
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("hola");
				devices= (String) combo.getSelectedItem();
				char letra[] = new char[4];
				devices.getChars(devices.length()-4, devices.length(), letra, 0);
				char W1 = letra[1];
				char W2 = letra[2];
				String letraSeleccionada = Character.toString(W1) + Character.toString(W2) + "/";
				System.out.println(letraSeleccionada);
				
				fileSystemModel = new FileSystemModel(new File(letraSeleccionada));		
		        fileTree = new JTree(fileSystemModel);
		        fileTree.setEditable(true);
		        fileTree.setBorder(BorderFactory.createEtchedBorder());
				fileTree.setBounds(0, 22, 500, 200);
				
				//Cargamos el Scroll con el JFileTree
				
				JScrollPane jsc=new JScrollPane(fileTree);
				jsc.setBorder(UIManager.getBorder("Tree.editorBorder"));
				jsc.setBounds(0,42,350,190);
				jsc.setVisible(true);
				MainWindow.getContentPane().add(jsc);
				
				
			}
		});
		
		
		combo.setBounds(0,21,350,21);
		buscarUnidades(combo);
		MainWindow.getContentPane().add(combo);
		
		
		/*devices= (String) combo.getSelectedItem();
		char letra[] = new char[4];
		devices.getChars(devices.length()-4, devices.length(), letra, 0);
		char W1 = letra[1];
		char W2 = letra[2];
		String letraSeleccionada = Character.toString(W1) + Character.toString(W2) + "/";
		
		fileSystemModel = new FileSystemModel(new File(letraSeleccionada));		
        fileTree = new JTree(fileSystemModel);
        fileTree.setEditable(true);
        fileTree.setBorder(BorderFactory.createEtchedBorder());
		fileTree.setBounds(0, 22, 500, 200);
		
		//Cargamos el Scroll con el JFileTree
		
		JScrollPane jsc=new JScrollPane(fileTree);
		jsc.setBorder(UIManager.getBorder("Tree.editorBorder"));
		jsc.setBounds(0,42,350,190);
		jsc.setVisible(true);
		MainWindow.getContentPane().add(jsc);
		
		

				
			
		
		
		
		
				
				
		
		
		
		
		
		/* Depuracion de la letra seleccionada en el comboBox
		
		devices= (String) combo.getSelectedItem();
		char letra[] = new char[4];
		devices.getChars(devices.length()-4, devices.length(), letra, 0);
		char W1 = letra[1];
		char W2 = letra[2];
		String letraSeleccionada = Character.toString(W1) + Character.toString(W2) + "/";
		
		//Creacion del JFileTree
        
		fileSystemModel = new FileSystemModel(new File(letraSeleccionada));		
        fileTree = new JTree(fileSystemModel);
        fileTree.setEditable(true);
        fileTree.setBorder(BorderFactory.createEtchedBorder());
		fileTree.setBounds(0, 22, 500, 200);
		
		//Cargamos el Scroll con el JFileTree
		
		JScrollPane jsc=new JScrollPane(fileTree);
		jsc.setBorder(UIManager.getBorder("Tree.editorBorder"));
		jsc.setBounds(0,42,350,190);
		jsc.setVisible(true);
		MainWindow.getContentPane().add(jsc);
        
        */
		
	}
	
	public void buscarUnidades(JComboBox comboBox){
		  unidades = File.listRoots();
		  Object it[]=new Object[unidades.length];
		  for (int i=0;i<unidades.length;i++) {
		         String s1 = FileSystemView.getFileSystemView().getSystemDisplayName (unidades[i]);
		         String s2 = FileSystemView.getFileSystemView().getSystemTypeDescription(unidades[i]);
		         if(FileSystemView.getFileSystemView().isFloppyDrive(unidades[i])){
		          s1="Unidad de Disquete (A:)";
		         }
		         if(s1.equalsIgnoreCase("")){
		          s1=s2;
		         }
		         it[i]=s1;
		       } 
		  items=it;
		  comboBox.removeAllItems();
		  for(int i=0;i<it.length;i++){
		   comboBox.addItem(it[i]);
		  }
		 }
	
	public void TreeRefresh (JComboBox combo, String letraSeleccionada){
		
		//Creacion del JFileTree
        
		fileSystemModel = new FileSystemModel(new File(letraSeleccionada));		
        fileTree = new JTree(fileSystemModel);
        fileTree.setEditable(true);
        fileTree.setBorder(BorderFactory.createEtchedBorder());
		fileTree.setBounds(0, 22, 500, 200);
		
		//Cargamos el Scroll con el JFileTree
		
		JScrollPane jsc=new JScrollPane(fileTree);
		jsc.setBorder(UIManager.getBorder("Tree.editorBorder"));
		jsc.setBounds(0,42,350,190);
		jsc.setVisible(true);
		MainWindow.getContentPane().add(jsc);
		
        }
}
		
		
		
	

