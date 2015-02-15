package com.jimcorp.tests;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CardLayoutFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel panels;
	private JPanel panel1;
	private JPanel panel2;

	public CardLayoutFrame() {
		super("Card Layout Demo");
		this.setLayout(new BorderLayout());
		
		final CardLayout layout = new CardLayout();
		
		panels = new JPanel(layout);
		
		panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		JButton button1 = new JButton("Click Me!");
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.next(panels);
				doLayout();
			}
			
		});
		panel1.add(button1);
		
		panel2 = new JPanel();
		
		
		String resourceRoot = "C:\\Users\\Jimmy\\Pictures\\Resource Pics";
		
		List<String> imageNames = new ArrayList<String>();
		File resourceDir = new File(resourceRoot);
		String[] files = resourceDir.list();
		for(String file : files) {
			if(file.endsWith(".png"))
				imageNames.add(file);
		}
		
		Image[] images = new Image[imageNames.size()];
		ImageIcon[] imageIcons = new ImageIcon[imageNames.size()];
		
		for(int i=0; i<images.length; i++) {
			String imagePath = resourceRoot + "\\" + imageNames.get(i);
			try {
				images[i] = ImageIO.read(new File(imagePath));
				imageIcons[i] = new ImageIcon(images[i].getScaledInstance(200, 200, Image.SCALE_DEFAULT));
			} catch (IOException e) {
				System.err.println("ERROR!!! Unable to load image at '" + imagePath + "'.\n" +
									"Reason: " + e.getMessage());
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		JList<ImageIcon> list = new JList<ImageIcon>(imageIcons);
		JScrollPane listScrollPane = new JScrollPane(list);
		
		panel2.add(listScrollPane);
		
		panels.add(panel1);
		panels.add(panel2);
		add(panels);
	}
	
	
	public static void main(String[] args) {
		CardLayoutFrame frame = new CardLayoutFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
}
