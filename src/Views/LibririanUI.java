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

public class LibririanUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private WebButton btnCpayment;
	public JButton btnBack;
	public WebButton btnCstructure;
	public WebButton btnIupdate;
	public WebButton btnCreview;
	public WebButton btnAdduser;
	/*
	 * Create the application.
	 * @throws URISyntaxException 
	 */
	public LibririanUI() {
		
		//*** DO NOT DELETE! ***//
		
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		
		
		//*** DO NOT DELETE! - END ***//
		
		btnAdduser = new WebButton("Add user");
		btnAdduser.setBounds(196, 200, 146, 54);
		add(btnAdduser);
		
		
		btnCpayment = new WebButton("Payment confirmation");
		btnCpayment.setBounds(196, 270, 146, 54);
		add(btnCpayment);
		
		btnCreview = new WebButton("Review confirmation");
		btnCreview.setBounds(196, 340, 146, 54);
		add(btnCreview);

		
		JLabel lblNewLabel = new JLabel("Libririan panel");
		lblNewLabel.setFont(new Font("Narkisim", Font.BOLD, 26));
		lblNewLabel.setBounds(273, 149, 335, 41);
		add(lblNewLabel);
		
		btnIupdate = new WebButton("Inventory update");

		btnIupdate.setBounds(365, 200, 146, 54);
		add(btnIupdate);
		
		btnCstructure = new WebButton("Control structure");
		btnCstructure.setBounds(365, 270, 146, 54);
		add(btnCstructure);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(58, 483, 89, 30);
		add(btnBack);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}
}
