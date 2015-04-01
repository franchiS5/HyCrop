package App;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainWindow {

	private JFrame MainWindow;

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
		MainWindow.setBounds(100, 100, 450, 300);
		MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
