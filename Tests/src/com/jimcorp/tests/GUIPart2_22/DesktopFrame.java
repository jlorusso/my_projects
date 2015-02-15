package com.jimcorp.tests.GUIPart2_22;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class DesktopFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JDesktopPane theDesktop;
	
	public DesktopFrame() {
		super("Using a JDesktopPane");
		
		JMenuBar bar = new JMenuBar();
		JMenu addMenu = new JMenu("Add");
		JMenuItem newFrame = new JMenuItem("Cool Picture");
		
		addMenu.add(newFrame);
		bar.add(addMenu);
		
		this.setJMenuBar(bar);
		
		theDesktop = new JDesktopPane();
		this.add(theDesktop);
		
		newFrame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				
				MyJPanel panel = new MyJPanel();
				JInternalFrame frame = new JInternalFrame(panel.getPictureName() , true, true, true, true);
				
				frame.add(panel, BorderLayout.CENTER);
				frame.pack(); // Set internal frame to size of contents
				
				theDesktop.add(frame);
				frame.setVisible(true);
			}
			
		});
	}
	
}


class MyJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static Random generator = new Random();
	private ImageIcon picture;
	private String pictureName;
	
	public MyJPanel() {
		File rootdir = new File("C:/Users/Public/Pictures/Sample Pictures");
		List<String> files = Arrays.asList(rootdir.list());
		List<String> matches = filterList(Pattern.compile(".*.jpg$"), files);
		
		int randomNumber = generator.nextInt(matches.size());
		pictureName = matches.get(randomNumber);
		String path = "C:/Users/Public/Pictures/Sample Pictures/" + pictureName;
		System.out.println("path = " + path);
		picture = new ImageIcon(path);
	}
	
	
	public String getPictureName() {
		return pictureName;
	}
	
	
	private List<String> filterList(Pattern regex, List<String> list) {
		List<String> matches = new ArrayList<String>();
		
		for(String elem : list) {
			Matcher match = regex.matcher(elem);
			if(match.matches()) {
				matches.add(elem);
			}
		}
		return matches;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		picture.paintIcon(this, g, 0, 0);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(picture.getIconWidth(), picture.getIconHeight());
	}
}
