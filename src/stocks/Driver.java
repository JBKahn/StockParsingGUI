package stocks;

import javax.swing.JFrame;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		JFrame JFwindow = new JFrame("Jamison's Stock Application");
		JFwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	
		JFwindow.getContentPane().add(new StockView()); 
		JFwindow.pack();
		JFwindow.setVisible(true);	

	}

}
