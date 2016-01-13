package me.ruyili;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.awt.event.*;

public class GUI{
	static final JFrame f = new JFrame("ZippyJar - Jar to zip Converter");
	public static void main(String[] args){
		JPanel p = new JPanel();
		final JTextField jar = new JTextField(13);
		JButton browseJar = new JButton("Browse...");
		JButton convert = new JButton("Convert");
		final String[] s1 = {"/"};
		jar.setEditable(false);
		jar.setText("Jar directory");
		
		f.setSize(300, 125);
		f.setResizable(false);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.add(p);
		p.add(jar);
		p.add(browseJar);
		p.add(convert);
		f.revalidate();
		p.revalidate();
		
		browseJar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jfc1 = new JFileChooser();
				jfc1.setFileFilter(new FileNameExtensionFilter(null, "jar"));
	            jfc1.setCurrentDirectory(new File("."));
	            jfc1.setDialogTitle("Browse for jar file...");
	            jfc1.setAcceptAllFileFilterUsed(false);
	            
	            if (jfc1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	                jar.setText("" + jfc1.getSelectedFile());
	                s1[0] = jar.getText();
	            }
			}
			
		
		});
		
		convert.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(s1[0] == "/"){
					JOptionPane.showMessageDialog(f, "You didn't specify the jar directory.");
				}else{
					try {
						jarToZip(s1[0], "/" + s1[0] + "_converted.zip");
						JOptionPane.showMessageDialog(f, "The file was successfully converted!");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(f, "Invalid input!");
					}
				}
			}
			
		});
		
		
	}
	

	public static void jarToZip(String oldName, String newName) throws IOException{
		
		File file = new File(oldName);
		File file2 = new File(newName);
		FileUtils.copyFile(file, file2);
		
	}
}
