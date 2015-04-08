package App;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.filechooser.FileSystemView;

import java.awt.SystemColor;

import javax.swing.JFileChooser;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;



public class MainWindow {

	private JFrame MainWindow;
	private int anchowindow = 300;
	File unidades[];
	Object items[];
	String devices;

	/////////////////////////////////////////////////////////////////////
	private JTree fileTree;
	private FileSystemModel fileSystemModel;
	private JTextArea fileDetailsTextArea = new JTextArea();
/////////////////////////////////////////////////////////////////////////
	  
	  
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
		combo.setBounds(0,21,350,21);
		buscarUnidades(combo);
		MainWindow.getContentPane().add(combo);
		//JComboBox<Object> comboBox = new JComboBox<Object>();
		
		//comboBox.setBounds(0, 21, 350, 21);
		//MainWindow.getContentPane().add(comboBox);
		
		
		        //fileTree.setBounds(0,0,50,50);
		        //JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, new JScrollPane(
		            //fileTree), new JScrollPane(fileDetailsTextArea));
		        //MainWindow.getContentPane().add(splitPane);
		        
		        //splitPane.setBounds(50,50,500,500);
		        //MainWindow.getContentPane().add(fileTree);
		
		devices= (String) combo.getSelectedItem();
		fileDetailsTextArea.setEditable(false);
        fileSystemModel = new FileSystemModel(new File("C:/"));	
        fileTree = new JTree(fileSystemModel);
        //fileTree.setAlignmentY(Component.TOP_ALIGNMENT);
        //fileTree.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fileTree.setEditable(true);
        fileTree.setBorder(BorderFactory.createEtchedBorder());
		fileTree.setBounds(0, 22, 500, 200);
		JScrollPane jsc=new JScrollPane(fileTree);
		jsc.setBorder(UIManager.getBorder("Tree.editorBorder"));
		jsc.setBounds(0,42,350,190);
		jsc.setVisible(true);
		MainWindow.getContentPane().add(jsc);
        
        fileTree.addTreeSelectionListener(new TreeSelectionListener() {
        public void valueChanged(TreeSelectionEvent event) {
        File file = (File) fileTree.getLastSelectedPathComponent();
        fileDetailsTextArea.setText(getFileDetails(file));
          }
        });

		
		
		
		/*DirectoryTree dt=new DirectoryTree();
		dt.getTree().expandPath(null);
        JScrollPane jsc=new JScrollPane(dt.getTree());
        jsc.setBounds(0,menuBar.getHeight(),200,400);
        jsc.setVisible(true);
        jsc.setEnabled(true);
        
        
        MainWindow.getContentPane().add(jsc);*/
        
		//////////////////////////////////////////////////////////////////////////
        /*fileDetailsTextArea.setEditable(false);
        if(devices != null){
        	fileSystemModel = new FileSystemModel(new File(devices));
        }else{
        	fileSystemModel = new FileSystemModel(new File("C:/"));	
        }
        
        fileTree = new JTree(fileSystemModel);
        fileTree.setAlignmentY(Component.TOP_ALIGNMENT);
        fileTree.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fileTree.setEditable(true);
        
        fileTree.addTreeSelectionListener(new TreeSelectionListener() {
          public void valueChanged(TreeSelectionEvent event) {
            File file = (File) fileTree.getLastSelectedPathComponent();
            fileDetailsTextArea.setText(getFileDetails(file));
          }
        });
        
        JScrollPane jsc=new JScrollPane(fileTree);
        jsc.setBorder(UIManager.getBorder("Tree.editorBorder"));
        jsc.setBounds(0,42,350,190);
        jsc.setVisible(true);
        //fileTree.setBounds(0,0,50,50);
        //JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, new JScrollPane(
            //fileTree), new JScrollPane(fileDetailsTextArea));
        //MainWindow.getContentPane().add(splitPane);
        
        //splitPane.setBounds(50,50,500,500);
        //MainWindow.getContentPane().add(fileTree);
        fileTree.setBorder(BorderFactory.createEtchedBorder());
		fileTree.setBounds(0, 22, 500, 200);
		MainWindow.getContentPane().add(jsc);*/
		
		
		
	}
	////////////////////////////////////////////////////////
	private String getFileDetails(File file) {
        if (file == null)
          return "";
        StringBuffer buffer = new StringBuffer();
        buffer.append("Name: " + file.getName() + "\n");
        buffer.append("Path: " + file.getPath() + "\n");
        buffer.append("Size: " + file.length() + "\n");
        return buffer.toString();
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
		 
}
		
		
		
	

