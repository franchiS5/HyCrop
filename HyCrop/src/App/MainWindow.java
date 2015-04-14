package App;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

import App.FileTreePanel.FileTreeCellRenderer;
import App.FileTreePanel.FileTreeNode;
import javax.swing.JInternalFrame;
import java.awt.Rectangle;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;



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
		
		JInternalFrame internalFrame = new JInternalFrame("");
		internalFrame.setFrameIcon(null);
		internalFrame.setBorder(UIManager.getBorder("Tree.editorBorder"));
		internalFrame.setBounds(0, 22, 250, 200);
		internalFrame.getContentPane().add(new FileTreePanel());
		MainWindow.getContentPane().add(internalFrame);
		internalFrame.setVisible(true);
		
		
		
	}
	
	private static class FileTreeCellRenderer extends DefaultTreeCellRenderer {
		/**
		 * Icon cache to speed the rendering.
		 */
		private Map<String, Icon> iconCache = new HashMap<String, Icon>();

		/**
		 * Root name cache to speed the rendering.
		 */
		private Map<File, String> rootNameCache = new HashMap<File, String>();

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.DefaultTreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree,
		 *      java.lang.Object, boolean, boolean, boolean, int, boolean)
		 */
		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean sel, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {
			FileTreeNode ftn = (FileTreeNode) value;
			File file = ftn.file;
			String filename = "";
			if (file != null) {
				if (ftn.isFileSystemRoot) {
					// long start = System.currentTimeMillis();
					filename = this.rootNameCache.get(file);
					if (filename == null) {
						filename = fsv.getSystemDisplayName(file);
						this.rootNameCache.put(file, filename);
					}
					// long end = System.currentTimeMillis();
					// System.out.println(filename + ":" + (end - start));
				} else {
					filename = file.getName();
				}
			}
			JLabel result = (JLabel) super.getTreeCellRendererComponent(tree,
					filename, sel, expanded, leaf, row, hasFocus);
			if (file != null) {
				Icon icon = this.iconCache.get(filename);
				if (icon == null) {
					// System.out.println("Getting icon of " + filename);
					icon = fsv.getSystemIcon(file);
					this.iconCache.put(filename, icon);
				}
				result.setIcon(icon);
			}
			return result;
		}
	}
	
	private static class FileTreeNode implements TreeNode {
		/**
		 * Node file.
		 */
		private File file;

		/**
		 * Children of the node file.
		 */
		private File[] children;

		/**
		 * Parent node.
		 */
		private TreeNode parent;

		/**
		 * Indication whether this node corresponds to a file system root.
		 */
		private boolean isFileSystemRoot;

		/**
		 * Creates a new file tree node.
		 * 
		 * @param file
		 *            Node file
		 * @param isFileSystemRoot
		 *            Indicates whether the file is a file system root.
		 * @param parent
		 *            Parent node.
		 */
		public FileTreeNode(File file, boolean isFileSystemRoot, TreeNode parent) {
			this.file = file;
			this.isFileSystemRoot = isFileSystemRoot;
			this.parent = parent;
			this.children = this.file.listFiles();
			if (this.children == null)
				this.children = new File[0];
		}

		/**
		 * Creates a new file tree node.
		 * 
		 * @param children
		 *            Children files.
		 */
		public FileTreeNode(File[] children) {
			this.file = null;
			this.parent = null;
			this.children = children;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#children()
		 */
		public Enumeration<?> children() {
			final int elementCount = this.children.length;
			return new Enumeration<File>() {
				int count = 0;

				/*
				 * (non-Javadoc)
				 * 
				 * @see java.util.Enumeration#hasMoreElements()
				 */
				public boolean hasMoreElements() {
					return this.count < elementCount;
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see java.util.Enumeration#nextElement()
				 */
				public File nextElement() {
					if (this.count < elementCount) {
						return FileTreeNode.this.children[this.count++];
					}
					throw new NoSuchElementException("Vector Enumeration");
				}
			};

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#getAllowsChildren()
		 */
		public boolean getAllowsChildren() {
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#getChildAt(int)
		 */
		public TreeNode getChildAt(int childIndex) {
			return new FileTreeNode(this.children[childIndex],
					this.parent == null, this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#getChildCount()
		 */
		public int getChildCount() {
			return this.children.length;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
		 */
		public int getIndex(TreeNode node) {
			FileTreeNode ftn = (FileTreeNode) node;
			for (int i = 0; i < this.children.length; i++) {
				if (ftn.file.equals(this.children[i]))
					return i;
			}
			return -1;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#getParent()
		 */
		public TreeNode getParent() {
			return this.parent;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.tree.TreeNode#isLeaf()
		 */
		public boolean isLeaf() {
			return (this.getChildCount() == 0);
		}
	}

	/**
	 * The file tree.
	 */
	private JTree tree;

	/**
	 * Creates the file tree panel.
	 * @return 
	 */
	public void FileTreePanel() {
		this.setLayout(new BorderLayout());

		File[] roots = File.listRoots();
		FileTreeNode rootTreeNode = new FileTreeNode(roots);
		tree = new JTree(rootTreeNode);
		tree.setCellRenderer(new FileTreeCellRenderer());
		tree.setRootVisible(false);
		final JScrollPane jsp = new JScrollPane(this.tree);
		//jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
		jsp.setBounds(0, 25, 200, 500);
		//add(jsp, BorderLayout.CENTER);
		MainWindow.getContentPane().add(jsp);
	}
}
		
		
		
	

