package Views;

import graphics.GUIimage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

import Entities.BookET;

public class RemoveBookUI extends JPanel{
	
	public JButton btnBack;
	public JButton btnRBook;
	public JComboBox comboBoxBooks;
	public ArrayList<BookET> books;
	
	public RemoveBookUI(ArrayList<BookET> booksET) {
		// TODO Auto-generated constructor stub
		
		books=booksET;
		
		setBackground(new Color(153, 204, 204));
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(40, 466, 89, 30);
		add(btnBack);
		
		comboBoxBooks = new JComboBox();
		comboBoxBooks.setBounds(210, 269, 217, 30);
		add(comboBoxBooks);
		BringBooksTCB();
		comboBoxBooks.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnRBook = new JButton("Delete");
		btnRBook.setBounds(439, 269, 89, 30);
		add(btnRBook);
		
		JLabel lblAddUserTo = new JLabel("Remove Book From DB");
		lblAddUserTo.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblAddUserTo.setBounds(210, 170, 250, 50);
		add(lblAddUserTo);
		
		
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}
	public void BringBooksTCB()
	{
		for(int i=0;i<books.size();i++)
			comboBoxBooks.addItem(books.get(i).getBID()+" - "+books.get(i).getBTitle());
	}
}