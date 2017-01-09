package Views;

import graphics.GUIimage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.net.URISyntaxException;
import javax.swing.JSeparator;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicProgressBarUI;

import com.alee.laf.button.WebButton;

import Entities.UserET;

import java.awt.Font;

import javax.swing.JProgressBar;

public class ManagerUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private WebButton btnBreport;
	public JButton btnBack;
	public WebButton btnCpermission;
	public WebButton btnThidebook;
	public WebButton btnPbook;
	public WebButton btnPreport;
	public WebButton btnAFreezing;
	/*
	 * Create the application.
	 * @throws URISyntaxException 
	 */
	public ManagerUI() {
		
		//*** DO NOT DELETE! ***//
		
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		
		
		//*** DO NOT DELETE! - END ***//
		
		btnPreport = new WebButton("Periodic report");
		btnPreport.setBounds(196, 200, 146, 54);
		add(btnPreport);
		
		
		btnBreport = new WebButton("Book report");
		btnBreport.setBounds(196, 270, 146, 54);
		add(btnBreport);
		
		btnPbook = new WebButton("Popular book");
		btnPbook.setBounds(196, 340, 146, 54);
		add(btnPbook);

		
		JLabel lblNewLabel = new JLabel("Manager panel");
		lblNewLabel.setFont(new Font("Narkisim", Font.BOLD, 26));
		lblNewLabel.setBounds(273, 149, 335, 41);
		add(lblNewLabel);
		
		btnThidebook = new WebButton("Temporary hide book");

		btnThidebook.setBounds(365, 200, 146, 54);
		add(btnThidebook);
		
		btnCpermission = new WebButton("Changing permission");
		btnCpermission.setBounds(365, 270, 146, 54);
		add(btnCpermission);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(58, 483, 89, 30);
		add(btnBack);
		
		btnAFreezing = new WebButton("Account freezing");
		btnAFreezing.setBounds(365, 340, 146, 54);
		add(btnAFreezing);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}
}
