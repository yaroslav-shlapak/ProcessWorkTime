package com.shlapak.yaroslav.worktime;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/*
 * FileChooserDemo.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class FileChooser extends JPanel {
	static private final String newline = "\n";
	private static final String defaultOpenFilePath = 
			"C:\\Users\\y.shlapak\\Google Drive\\documents\\worked_hours.txt";
	private static final String defaultSaveFilePath = 
			"C:\\Users\\y.shlapak\\Google Drive\\documents\\worked_hours_result.txt";
	JButton openButton, saveButton, processButton;
	JTextArea log;
	private JTextField openPathTxt, savePathTxt;
	JFileChooser fc;
	private File openFile, saveFile;
	Histogram hist;
    ChartPanel histPanel;
    XYChart graph;
    ChartPanel graphPanel;
	

	public FileChooser() {
		super(new BorderLayout());
		JPanel openSavePanel = new JPanel(new GridLayout(2, 2));
		JPanel buttonTextPanel = new JPanel(new GridLayout(3, 1));
		JPanel chartPanel = new JPanel(new GridLayout(1, 2));
  		// Create the log first, because the action listeners
		// need to refer to it.
		log = new JTextArea(5, 5);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
		
		//create Listeners
		ActionListener openButtonListener = new OpenButtonListener();
		ActionListener saveButtonListener = new SaveButtonListener();
		ActionListener processButtonListener = new ProcessButtonListener();

		// Create a file chooser
		fc = new JFileChooser();

		openFile = (Paths.get(defaultOpenFilePath)).toFile();
		saveFile = (Paths.get(defaultSaveFilePath)).toFile();
		openPathTxt = new JTextField(openFile.toString());
		savePathTxt = new JTextField(saveFile.toString());
				
		openButton = new JButton("Open a File", createImageIcon("/Open16.gif"));
		openButton.addActionListener(openButtonListener);

		// Create the save button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).

		saveButton = new JButton("Save a file", createImageIcon("/Save16.gif"));
		saveButton.addActionListener(saveButtonListener);
				
		processButton = new JButton("Process data", createImageIcon("/Sort16.gif"));
		processButton.addActionListener(processButtonListener);

		// For layout purposes, put the buttons in a separate panel;
        openSavePanel.add(openPathTxt);
		openSavePanel.add(openButton);
		openSavePanel.add(savePathTxt);
		openSavePanel.add(saveButton);
		buttonTextPanel.add(openSavePanel);
		buttonTextPanel.add(processButton);
		buttonTextPanel.add(logScrollPane);
		
		hist = new Histogram();
	    histPanel = new ChartPanel(hist.getChart());
	    
	    graph = new XYChart(openFile.getName());
	    graphPanel = new ChartPanel(graph.getChart());
	    chartPanel.add(histPanel);
	    chartPanel.add(graphPanel);
	    

		// Add the buttons and the log to this panel.
		add(buttonTextPanel, BorderLayout.PAGE_START);
		add(chartPanel, BorderLayout.CENTER);
		//add(logScrollPane, BorderLayout.PAGE_END);
	}
	
	public static void run() {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
	
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("WorkTimeExtractor");
        //frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1000, 500);
        frame.setLocation(300, 300);
        frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new FileChooser());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = FileChooser.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */

	private class OpenButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == openButton) {
				int returnVal = fc.showOpenDialog(FileChooser.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					openFile = fc.getSelectedFile();
					openPathTxt.setText(openFile.toString());
					// This is where a real application would open the file.
					log.append("Opening: " + openFile.getName() + "." + newline);
				} else {
					log.append("Open command cancelled by user." + newline);
				}
				log.setCaretPosition(log.getDocument().getLength());
			}
		}
	}
	
	private class ProcessButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getSource() == processButton) {
				String charsetName = "UTF-8";
				// Read a file
				BufferedFileIO<String> bufFile = new BufferedFileIO<String>(charsetName);
				
				String ls = new String();
				List<String> vv = new ArrayList<String>();
				
				ls = bufFile.readFile(openFile.toPath(), ls);
				WorkTimeParser wtp = new WorkTimeParser(ls);
				List<Double> intTime = wtp.getWorkTime();
				
				hist.addSeries(openFile.getName(), intTime, 100);
				histPanel.updateUI();
				System.out.println(intTime);
				System.out.println(intTime.get(intTime.size() / 2));
				log.append("Processed!" + newline);
				log.setCaretPosition(log.getDocument().getLength());
				System.out.println(vv);
			}
		}
		
	}
	
	
	private class SaveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == saveButton) {
				saveFile();
			}
		}
	}
	
	private void saveFile() {
		int returnVal = fc.showSaveDialog(FileChooser.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			saveFile = fc.getSelectedFile();
			// This is where a real application would save the file.
			savePathTxt.setText(saveFile.toString());
			log.append("Saving: " + saveFile.getName() + "." + newline);
		} else {
			log.append("Save command cancelled by user." + newline);
		}
		log.setCaretPosition(log.getDocument().getLength());
	}

}


