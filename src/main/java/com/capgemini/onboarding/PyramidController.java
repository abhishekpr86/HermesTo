package com.capgemini.onboarding;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import ChartDirector.*;

public class PyramidController {// Name of demo program
	public String toString() {
		return "Simple Pyramid Chart";
	}

	// Number of charts produced in this demo
	public int getNoOfCharts() {
		return 1;
	}

	// Main code for creating charts
	public void createChart(ChartViewer viewer, int chartIndex) {
		// The data for the pyramid chart
		double[] data = { 156, 123, 211, 179 };

		// The labels for the pyramid chart
		String[] labels = { "Funds", "Bonds", "Stocks", "Cash" };

		// Create a PyramidChart object of size 360 x 360 pixels
		PyramidChart c = new PyramidChart(360, 360);

		// Set the pyramid center at (180, 180), and width x height to 150 x 180
		// pixels
		c.setPyramidSize(180, 180, 150, 300);

		// Set the pyramid data and labels
		c.setData(data, labels);

		// Add labels at the center of the pyramid layers using Arial Bold font.
		// The labels will
		// have two lines showing the layer name and percentage.
		c.setCenterLabel("{label}\n{percent}%", "Arial Bold");

		// Output the chart
		viewer.setChart(c);

		// include tool tip for the chart
		viewer.setImageMap(c.getHTMLImageMap("clickable", "", "title='{label}: US$ {value}M ({percent}%)'"));
		
		
		 JFrame frame = new JFrame(toString());
	        frame.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {System.exit(0);} });
	        frame.getContentPane().setBackground(Color.white);
	        
	        // Create the chart and put them in the content pane
	        ChartViewer viewer1 = new ChartViewer();
	        frame.getContentPane().add(viewer1);

	        // Display the window
	        frame.pack();
	        frame.setVisible(true);
	}

}
