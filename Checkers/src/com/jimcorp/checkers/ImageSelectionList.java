package com.jimcorp.checkers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

public class ImageSelectionList extends JList<String> {

	private static final long serialVersionUID = 1L;

	private final Map<String, ImageIcon> imageMap;

    public ImageSelectionList(Map<String, ImageIcon> imageMap) {
//        imageMap = createImageMap();
    	this.imageMap = imageMap;
        String[] nameList = this.imageMap.keySet().toArray(new String[0]);
        DefaultListModel<String> model = new DefaultListModel<String>();
        for(String elem : nameList)
        	 model.addElement(elem);
        this.setModel(model);
        this.setCellRenderer(new ImageListRenderer());
        this.setBorder((
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(
                                EtchedBorder.RAISED, Color.GRAY
                                , Color.DARK_GRAY))));
        this.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.setVisibleRowCount(2);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public class ImageListRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = 1L;
		Font font = new Font("helvitica", Font.BOLD, 14);

        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setIcon(imageMap.get((String) value));
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setFont(font);
            label.setForeground(Color.PINK);
            label.setOpaque(true);
            return label;
        }
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
 //   	g.drawImage(ImageSelectionPanel.getBackgroundImage(),0,0,null);
    }
}
