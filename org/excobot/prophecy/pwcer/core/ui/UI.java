package org.excobot.prophecy.pwcer.core.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import org.excobot.prophecy.pwcer.constants.Trees;
import org.excobot.prophecy.pwcer.data.Data;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class UI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4202137371983371289L;
	
	public boolean isRunning = true; 
	
	public UI() {
		init();
	}
	
	private void init() {
		setTitle("PWoodcutter");
		setSize(225, 130);
		setLayout(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		final JComboBox trees = new JComboBox(Trees.values());
		trees.setBounds(10, 10, 190, 30);
		add(trees);
		
		final JButton start = new JButton("Start");
		start.setBounds(10, 55, 190, 30);
		add(start);
		
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Data.tree = Trees.values()[trees.getSelectedIndex()];
				setVisible(false);
				isRunning = false;
			}
			
		});
		
		setVisible(true);
	}
	
	public static void main(String...args) {
		new UI();
	}

}
