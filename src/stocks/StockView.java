package stocks;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StockView extends JPanel {
	
	private JButton header0 = new JButton("Enter a stock symbol to get the current price.");
	private JTextField text = new JTextField();
	private JButton tickerSymbol = new JButton("Symbol");
	private JButton tickerPrice = new JButton("Price");
	private JButton fullName = new JButton("Name");
	private JButton goButton = new JButton("GO");
	
	public StockView(){
		JPanel JPEverything = new JPanel();
		JPEverything.setPreferredSize(new Dimension(300, 600));
		JPEverything.setLayout(new GridLayout(6,1));
		JPEverything.setBackground(Color.gray);
		
		JPEverything.add(header0);
		JPEverything.add(text);
		JPEverything.add(goButton);
		JPEverything.add(tickerSymbol);
		JPEverything.add(fullName);
		JPEverything.add(tickerPrice);
		
		goButton.addActionListener(new ButtonListener());
		
		this.add(JPEverything);
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			
			String actionCommand = e.getActionCommand();
			
			if (actionCommand.equals("GO")){
				
				String base = "http://money.cnn.com/quote/quote.html?symb=";
				try {
					URL baseUrl = new URL("http://money.cnn.com/quote/quote.html?symb=");
				} catch (MalformedURLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String userString = text.getText().toString();
				String userU = base.concat(userString.toUpperCase());
				boolean found = false;
				
				// DOING THE ACTUAL SHIT HERE
				try {
					URL finalUrl = new URL(userU);
					URLConnection connect = finalUrl.openConnection();
					
					connect.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)" );
				    BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
					
				    String strLine = "";
				    String name = "";
				    while ((strLine = in.readLine()) != null){
				    	Pattern theNumber = Pattern.compile("streamFeed=\"SunGard\">([0-9]+\\.[0-9]+)");
				    	Matcher isMatched = theNumber.matcher(strLine);
				    	
				    	Pattern theName = Pattern.compile(userString.toUpperCase()+ " - (.+) Stock");
				    	Matcher nameMatched = theName.matcher(strLine);
				    	
				    	if (nameMatched.find()){
				    		name = nameMatched.group(1);
				    	}
				    	
				    	if (isMatched.find()){
				    		String ma = isMatched.group(1);
				    		tickerSymbol.setText(userString);
				    		tickerPrice.setText(ma.toString());
				    		fullName.setText(name);
				    		found = true;
				    		break;
				    	} 	
				    }  
				} catch(Exception e2){
					tickerPrice.setText(e2.getMessage());
				}
				
				if (!found){
					tickerPrice.setText("You entered an incorrect symbol,try again.");
				}
				
				
				
			}
			
		}
	}

}
