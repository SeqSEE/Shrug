package com.shruglabs.shrug.client.render.gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.shruglabs.shrug.Shrug;
import com.shruglabs.shrug.client.render.TextureRegistry;

public class MainDialog extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1938294166692662375L;
	private JTextField[] fields;
	public String name;
	public String ip;
	public int port;
	public String password;
	JFrame frame;

	// Create a form with the specified labels, tooltips, and sizes.
	public MainDialog(String[] labels, char[] mnemonics, int[] widths, String[] tips) {
		super(new BorderLayout());
		JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
		JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
		add(labelPanel, BorderLayout.CENTER);
		add(fieldPanel, BorderLayout.LINE_END);
		fields = new JTextField[labels.length];

		for (int i = 0; i < labels.length; i += 1) {
			switch (i) {
			case 0:
				fields[0] = new JTextField("Player");
				if (i < tips.length)
					fields[0].setToolTipText(tips[0]);
				if (i < widths.length)
					fields[0].setColumns(widths[0]);
				break;
			case 1:
				fields[1] = new JTextField("localhost");
				if (i < tips.length)
					fields[1].setToolTipText(tips[1]);
				if (i < widths.length)
					fields[1].setColumns(widths[1]);
				break;
			case 2:
				fields[2] = new JTextField("37756");
				if (i < tips.length)
					fields[2].setToolTipText(tips[2]);
				if (i < widths.length)
					fields[2].setColumns(widths[2]);
				break;
			default:
				fields[i] = new JTextField();
				if (i < tips.length)
					fields[i].setToolTipText(tips[i]);
				if (i < widths.length)
					fields[i].setColumns(widths[i]);
				break;
			}

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
		char[] mnemonics = { 'N', 'I', 'O', 'P' };
		int[] widths = { 15, 20, 5, 15 };
		String[] descs = { "Name", "IPv4", "1025-61999", "Optional" };

		final MainDialog form = new MainDialog(labels, mnemonics, widths, descs);

		frame = new JFrame("Shrug");
		frame.setResizable(false);
		JButton connect = new JButton("Connect");

		connect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Username: " + form.getText(0));
				name = form.getText(0);
				System.out.println("IP: " + form.getText(1));
				ip = form.getText(1);
				System.out.println("Port: " + form.getText(2));
				try {
					port = Integer.parseInt(form.getText(2));
				} catch (NumberFormatException e) {
					new JDialog(new Dialog(frame, "Invalid Port!"));
				}

				System.out.println("Password:" + form.getText(3));
				password = form.getText(3);
			}
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				Shrug.shrug.getClient().close();
				frame.setVisible(false);
				frame.dispose();
				
			}
		});

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(form, BorderLayout.NORTH);
		JPanel p = new JPanel();
		p.add(connect);
		p.add(cancel);
		frame.getContentPane().add(p, BorderLayout.SOUTH);
		frame.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((dim.width - frame.getWidth()) / 2,
				(dim.height - frame.getHeight()) / 2 + frame.getHeight() + (frame.getHeight() / 5), frame.getWidth(),
				frame.getHeight());
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
	}

}
