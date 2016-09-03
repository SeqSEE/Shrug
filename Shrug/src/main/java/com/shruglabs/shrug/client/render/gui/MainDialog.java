package com.shruglabs.shrug.client.render.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainDialog extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1938294166692662375L;
	private JTextField[] fields;
	public String name;
	public String ip;
	public String port;
	public String password;
	

	// Create a form with the specified labels, tooltips, and sizes.
	public MainDialog(String[] labels, char[] mnemonics, int[] widths, String[] tips) {
		super(new BorderLayout());
		JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
		JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
		add(labelPanel, BorderLayout.CENTER);
		add(fieldPanel, BorderLayout.LINE_END);
		fields = new JTextField[labels.length];

		for (int i = 0; i < labels.length; i += 1) {
			fields[i] = new JTextField();
			if (i < tips.length)
				fields[i].setToolTipText(tips[i]);
			if (i < widths.length)
				fields[i].setColumns(widths[i]);

			JLabel lab = new JLabel(labels[i], JLabel.RIGHT);
			lab.setLabelFor(fields[i]);
			if (i < mnemonics.length)
				lab.setDisplayedMnemonic(mnemonics[i]);

			labelPanel.add(lab);
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
			p.add(fields[i]);
			fieldPanel.add(p);
		}
	}

	

	public String getText(int i) {
		return (fields[i].getText());
	}

	public MainDialog() {
		String[] labels = { "Name", "IP", "Port", "Password" };
		char[] mnemonics = { 'N', 'I', 'P', 'W' };
		int[] widths = { 15, 20, 5, 15 };
		String[] descs = { "Name", "IPv4", "1025-61999", "Optional" };

		final MainDialog form = new MainDialog(labels, mnemonics, widths, descs);
		JFrame f = new JFrame("Shrug");
		JButton connect = new JButton("Connect");
		
		connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Username: " +form.getText(0));
				name = form.getText(0);
				System.out.println(	"IP: " + form.getText(1));
				ip = form.getText(1);
				System.out.println("Port: " + form.getText(2));
				port = form.getText(2);
				System.out.println("Password:" + form.getText(3));
				password = form.getText(3);
			}
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				f.dispose();
			}
		});

		
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.getContentPane().add(form, BorderLayout.NORTH);
		JPanel p = new JPanel();
		p.add(connect);
		p.add(cancel);
		f.getContentPane().add(p, BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
	}


}
