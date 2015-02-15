package com.jimcorp.tests.networking_24;

import java.applet.AppletContext;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SiteSelector extends JApplet {

	private static final long serialVersionUID = 1L;

	private HashMap<String, URL> sites;
	private List<String> siteNames;
	private JList<String> siteChooser;

	@Override
	public void init() {
		sites = new HashMap<String, URL>();
		siteNames = new ArrayList<String>();
		
		getSitesFromHTMLParameters();
		
		add(new JLabel("Choose a site to browse"), BorderLayout.NORTH);
		
		siteChooser = new JList<String>(siteNames.toArray(new String[0]));
		siteChooser.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent event) {
				Object object = siteChooser.getSelectedValue();
				URL newDocument = sites.get(object);
				AppletContext browser = getAppletContext();
				browser.showDocument(newDocument);
			}
		});
		
		add(new JScrollPane(siteChooser), BorderLayout.CENTER);
	}


	private void getSitesFromHTMLParameters() {
		
		String title;
		String location;
		URL url;
		int counter = 0;
		
		title = getParameter("title" + counter);
		
		while(title != null) {
			location = getParameter("location"+counter);
			
			try {
				url = new URL(location);
				sites.put(title, url);
				siteNames.add(title);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			++counter;
			title = getParameter("title"+counter);
		}
	}

}
