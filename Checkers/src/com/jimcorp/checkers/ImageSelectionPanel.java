package com.jimcorp.checkers;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ImageSelectionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private static Image background;

	private JLabel chooseLbl;
	private ImageSelectionList list;
	private JButton okBtn;
	private JButton cancelBtn;
	private Map<String, ImageIcon> imageMap;
	
	public ImageSelectionPanel() {
		this(new HashMap<String, ImageIcon>());
	}
	
	public ImageSelectionPanel(Map<String, ImageIcon> aImageMap) {
		
		this.imageMap = aImageMap;
		
		setLayout(new GridBagLayout()); 
		
		chooseLbl = new JLabel("Choose your character:");
		chooseLbl.setHorizontalTextPosition(JLabel.CENTER);
		chooseLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
		chooseLbl.setForeground(Color.MAGENTA);
		GridBagConstraints chooseLblC = new GridBagConstraints();
		chooseLblC.gridx = 0;
		chooseLblC.gridy = 0;
		chooseLblC.gridwidth = 8;
		chooseLblC.gridheight = 1;
		chooseLblC.ipady= 35;
		add(chooseLbl, chooseLblC);
		
		list = new ImageSelectionList(imageMap);
		list.setOpaque(false);
		list.setBackground(new Color(70, 180, 30));
		JScrollPane scroll = new JScrollPane(list);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		GridBagConstraints scrollC = new GridBagConstraints();
		scrollC.gridx = 0;
		scrollC.gridy = 1;
		scrollC.gridwidth = 8;
		scrollC.gridheight = 8;
		scrollC.fill = GridBagConstraints.BOTH;
		scrollC.weightx = 1;
		scrollC.weighty = 1;
		add(scroll, scrollC);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setOpaque(false);
		btnPanel.setLayout(new FlowLayout());
		okBtn = new JButton("Ok");
		btnPanel.add(okBtn);
		
		cancelBtn = new JButton("Cancel");
		btnPanel.add(cancelBtn);
		
		GridBagConstraints btnPanelC = new GridBagConstraints();
		btnPanelC.gridx = 4;
		btnPanelC.gridy = 10;
		btnPanelC.gridwidth = 4;
		btnPanelC.gridheight = 1;
		btnPanelC.insets = new Insets(10,0,10,0);
		btnPanelC.anchor = GridBagConstraints.LAST_LINE_END;
		add(btnPanel, btnPanelC);
		
		String backgroundPath = ImageSelectionModel.resourceRoot + "\\Backgrounds\\Jungle.jpg";
		try {
			background = ImageIO.read(new File(backgroundPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public ImageSelectionList getList() {
		return list;
	}
	
	public Map<String, ImageIcon> getImageMap() {
		return imageMap;
	}
	
	public static Image getBackgroundImage() {
		
		return ImageSelectionPanel.background;
	}
	
	public void addOkClickedListener(ActionListener listener) {
		okBtn.addActionListener(listener);
	}
	
	public void addCancelClickedListener(ActionListener listener) {
		cancelBtn.addActionListener(listener);
	}

	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT), 0, 0, null);
	}

}