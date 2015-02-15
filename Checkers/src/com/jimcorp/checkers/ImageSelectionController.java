package com.jimcorp.checkers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageSelectionController {

	private ImageSelectionPanel view;
	private ImageSelectionModel model;
	
	public ImageSelectionController(ImageSelectionPanel view, ImageSelectionModel model) {
		this.view = view;
		this.model = model;
		
		view.addOkClickedListener(new OkClickedListener());
		view.addCancelClickedListener(new CancelClickedListener());
	}
	
	
	private class OkClickedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			ImageSelectionList list = view.getList();
			String imageName = list.getSelectedValue();
			model.setImageName(imageName);
			model.setImageSelected(model.getImageMap().get(imageName));
			
			System.out.println(imageName);
		}
		
	}
	
	
	private class CancelClickedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			
		}
		
	}
}
