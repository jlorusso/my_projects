package com.jimcorp.checkers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class ImageSelectionTable extends JTable {
		
	private static final long serialVersionUID = 1L;

	private final Map<String, ImageIcon> imageMap;

    public ImageSelectionTable() {
        imageMap = createImageMap();
        String[] nameList = imageMap.keySet().toArray(new String[0]);
        DefaultTableModel model = new DefaultTableModel();
        
        ArrayList<Vector<String>> rows = new ArrayList<Vector<String>>();
        ArrayList<String> names = new ArrayList<String>();
        for(String name : nameList) {
        	names.add(name);
        	if(names.size() == 4) {
        		rows.add(new Vector<String>(names));
        		names.clear();
        	}
        }
        
        for(Vector<String> row : rows) {
        	System.out.println(row);
        	model.addRow(row);
        	
        }
        
        this.setModel(model);
        this.setShowGrid(true);
        this.setDefaultRenderer(getColumnClass(0), new ImageTableRenderer());

        this.setBorder((
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(
                                EtchedBorder.RAISED, Color.GRAY
                                , Color.DARK_GRAY))));
 //       this.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//	        this.setVisibleRowCount(3);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }
    

    public class ImageTableRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

		private static final long serialVersionUID = 1L;
		Font font = new Font("helvitica", Font.BOLD, 14);

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean cellHasFocus, int row, int column) {

            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, cellHasFocus, row, column);
            label.setIcon(imageMap.get((String) value));
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setFont(font);
            label.setForeground(Color.PINK);
            label.setOpaque(true);
            return label;
        }
        
        
        @Override
        public void setValue(Object value) {
        	
        }
    }
    
    
    private Map<String, ImageIcon> createImageMap() {
        Map<String, ImageIcon> map = new HashMap<>();
        
		String resourceRoot = "C:\\Users\\Jimmy\\Pictures\\Resource Pics";
		
		List<String> imageNames = new ArrayList<String>();
		File resourceDir = new File(resourceRoot);
		String[] files = resourceDir.list();
		for(String file : files) {
			if(file.endsWith(".png"))
				imageNames.add(file);
		}
		
		for(String imageName : imageNames) {
			String imagePath = resourceRoot + "\\" + imageName;
			try {
				Image image = ImageIO.read(new File(imagePath));
				map.put(removeExtention(imageName), new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
			} catch (IOException e) {
				System.err.println("ERROR!!! Unable to load image at '" + imagePath + "'.\n" +
									"Reason: " + e.getMessage());
				e.printStackTrace();
				System.exit(1);
			}
		}

        return map;
    }
    
    
    public static String removeExtention(String filePath) {
        File f = new File(filePath);

        if (f.isDirectory()) return filePath;

        String name = f.getName();

        final int lastPeriodPos = name.lastIndexOf('.');
        if (lastPeriodPos <= 0) {
            return filePath;
        }
        else {
            File renamed = new File(f.getParent(), name.substring(0, lastPeriodPos));
            return renamed.getPath();
        }
    }
    
    
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setLayout(new BorderLayout());
    	ImageSelectionTable table = new ImageSelectionTable();
    	frame.add(table, BorderLayout.CENTER);
    	frame.add(new JButton("Click me"), BorderLayout.SOUTH);
    	frame.setSize(700, 500);
    	frame.setVisible(true);
    }
}

