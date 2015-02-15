package com.jimcorp.checkers;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageSelectionModel {

	protected static final String resourceRoot = "C:\\Users\\Jimmy\\Pictures\\Resource Pics";

	private Map<String, ImageIcon> imageMap;
	private ImageIcon imageSelected;
	private String imageName;
	
	public ImageSelectionModel() {
		this.imageMap = createImageMap();
	}
	
	public Map<String, ImageIcon> getImageMap() {
		return imageMap;
	}
	
    private Map<String, ImageIcon> createImageMap() {
        Map<String, ImageIcon> map = new HashMap<>();
        
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
    
    
    private static String removeExtention(String filePath) {
        File f = new File(filePath);

        if (f.isDirectory()) return filePath;

        String name = f.getName();

        final int lastPeriodPos = name.lastIndexOf('.');
        if (lastPeriodPos <= 0)
        {
            return filePath;
        }
        else
        {
            File renamed = new File(f.getParent(), name.substring(0, lastPeriodPos));
            return renamed.getPath();
        }
    }
	
	
	public ImageIcon getImageSelected() {
		return imageSelected;
	}
	
	public void setImageSelected(ImageIcon imageIcon) {
		this.imageSelected = imageIcon;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
	
}
