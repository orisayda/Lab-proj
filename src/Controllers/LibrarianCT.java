package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import Entities.*;
import Mains.IBookClient;
import Views.*;


public class LibrarianCT implements Observer, ActionListener{
	
	public static LibrarianCT librarianCT;
	public static IBookClient client;
	public static LibririanUI libririanFrame;
	public static AddUserUI adduserFrame;
	public static inventoryUpdateUI IUpdateFrame;
	public static AddBookUI AddBFrame;
	public static RemoveBookUI RemoveBFrame;
	public static UpdateBookUI UpdateBFrame;
	public static PaymentConfirmationUI paymentFrame;
	public static ReviewConfirmationUI reviewFrame;
	public static EditReviewUI editFrame;
	public static ControlstructureUI CStructFrame;
	public static PairingBookUI PairingFrame;
	public static SettingDivisionUI SDivisionFrame;
	public static AddGenreUI AddGenreFrame;
	public static AddSubjectUI AddSubjectFrame;
	public static RemoveGenreUI RemoveGenreFrame;
	public static RemoveSubjectUI RemoveSubjectFrame;
	
	public static UserET userET;
	public static BookET bookET;
	public static ArrayList<GenreET> genresET;
	public ArrayList<ReaderET> readers;
	public ArrayList<ReviewET> reviews;
	public static ArrayList<BookET> BooksET;
	int check=0;
	
	/**
	 * @param frame
	 */
	public LibrarianCT(LibririanUI frame) {
		// TODO Auto-generated constructor stub
		librarianCT=this;
		client = IBookClient.getInstance();
		this.libririanFrame=frame;
		
		BringBooks();
		libririanFrame.btnCstructure.addActionListener((ActionListener)this);
		libririanFrame.btnAdduser.addActionListener((ActionListener)this);
		libririanFrame.btnIupdate.addActionListener((ActionListener)this);
		libririanFrame.btnCpayment.addActionListener((ActionListener)this);
		libririanFrame.btnCreview.addActionListener((ActionListener)this);
		UserCT.userCT.changeObserver(this,UserCT.userCT);
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==libririanFrame.btnAdduser){
			adduserFrame = new AddUserUI();
			adduserFrame.btnBack.addActionListener((ActionListener)this);
			adduserFrame.btnAddUser.addActionListener((ActionListener)this);
			MainUI.MV.setView(adduserFrame);
		}
		if(e.getSource()==libririanFrame.btnIupdate){
			check=6;
			BringGandS();
			
		}
		if(e.getSource()==libririanFrame.btnCstructure){
			CStructFrame = new ControlstructureUI();
			CStructFrame.btnBack.addActionListener((ActionListener)this);
			CStructFrame.btnPBook.addActionListener((ActionListener)this);
			CStructFrame.btnSdivision.addActionListener((ActionListener)this);
			MainUI.MV.setView(CStructFrame);
		}
		if(CStructFrame!=null)
		{
			if(e.getSource()==CStructFrame.btnPBook)
			{
				check=5;
				BringGandS();
				
			}
			if(e.getSource()==CStructFrame.btnBack)
				MainUI.MV.setView(libririanFrame);
			if(e.getSource()==CStructFrame.btnSdivision)
			{
				SDivisionFrame = new SettingDivisionUI();
				SDivisionFrame.btnBack.addActionListener((ActionListener)this);
				SDivisionFrame.btnAddGenre.addActionListener((ActionListener)this);
				SDivisionFrame.btnAddSubject.addActionListener((ActionListener)this);
				SDivisionFrame.btnRGenre.addActionListener((ActionListener)this);
				SDivisionFrame.btnRSubject.addActionListener((ActionListener)this);
				MainUI.MV.setView(SDivisionFrame);
			}
		}
		if(SDivisionFrame!=null)
		{
			if(e.getSource()==SDivisionFrame.btnBack)
				MainUI.MV.setView(CStructFrame);
			if(e.getSource()==SDivisionFrame.btnAddGenre)
			{
				check=1;
				BringGandS();
				
			}
			if(e.getSource()==SDivisionFrame.btnAddSubject)
			{
				check=2;
				BringGandS();

			}
			if(e.getSource()==SDivisionFrame.btnRGenre)
			{
				check=3;
				BringGandS();
				
			}
			if(e.getSource()==SDivisionFrame.btnRSubject)
			{
				check=4;
				BringGandS();
				
			}
		}
		if(RemoveSubjectFrame!=null)
		{
			if(e.getSource()==RemoveSubjectFrame.btnBack)
				MainUI.MV.setView(SDivisionFrame);
			if(e.getSource()==RemoveSubjectFrame.btnRemove)
				RemoveSubject(RemoveSubjectFrame.getComboBoxGenres().getSelectedIndex()+1,(String) RemoveSubjectFrame.getComboBoxSubject().getSelectedItem());
		}
		if(RemoveGenreFrame!=null)
		{
			if(e.getSource()==RemoveGenreFrame.btnBack)
				MainUI.MV.setView(SDivisionFrame);
			if(e.getSource()==RemoveGenreFrame.btnRemove)
				RemoveGenre(RemoveGenreFrame.getComboBoxGenres().getSelectedIndex()+1);
		}
		if(AddSubjectFrame!=null)
		{
			if(e.getSource()==AddSubjectFrame.btnBack)
				MainUI.MV.setView(SDivisionFrame);
			if(e.getSource()==AddSubjectFrame.btnAdd)
			{
				AddSubject(new SubjectET(0, AddSubjectFrame.getSubjectTitle(), (AddSubjectFrame.getComboBoxGenres().getSelectedIndex())+1));
			}
		}
		if(AddGenreFrame!=null)
		{
			if(e.getSource()==AddGenreFrame.btnBack)
				MainUI.MV.setView(SDivisionFrame);
			if(e.getSource()==AddGenreFrame.btnAdd)
			{
				AddGenre(new GenreET(0,AddGenreFrame.getGenreTitle()));
			}
		}
		if(PairingFrame!=null)
		{
			if(e.getSource()==PairingFrame.btnBack)
				MainUI.MV.setView(CStructFrame);
			if(e.getSource()==PairingFrame.btnPairBook)
				PairBook();
		}
		if(e.getSource()==libririanFrame.btnCpayment){
			GetPaymentList();
		}
		if(e.getSource()==libririanFrame.btnCreview){
			GetReviewList();
		}
		if(IUpdateFrame!=null)
		{
			if(e.getSource()==IUpdateFrame.btnBack)
				MainUI.MV.setView(libririanFrame);
		}
		if(IUpdateFrame!=null)
		{
			if(e.getSource()==IUpdateFrame.btnUBook)
			{
				UpdateBFrame=new UpdateBookUI();
				UpdateBFrame.btnBack.addActionListener((ActionListener)this);
				UpdateBFrame.btnChoose.addActionListener((ActionListener)this);
				UpdateBFrame.btnUpdate.addActionListener((ActionListener)this);
				MainUI.MV.setView(UpdateBFrame);
			}
			else if(e.getSource()==IUpdateFrame.btnRBook)
			{
				
				RemoveBFrame=new RemoveBookUI(BooksET);
				RemoveBFrame.btnBack.addActionListener((ActionListener)this);
				RemoveBFrame.btnRBook.addActionListener((ActionListener)this);
				MainUI.MV.setView(RemoveBFrame);
			}
			else if(e.getSource()==IUpdateFrame.btnAddBook)
			{
				AddBFrame=new AddBookUI(genresET);
				AddBFrame.btnBack.addActionListener((ActionListener)this);
				AddBFrame.btnAddBook.addActionListener((ActionListener) this);
				MainUI.MV.setView(AddBFrame);
			}
		}
		if(UpdateBFrame!=null)
		{
			if(e.getSource()==UpdateBFrame.btnBack)
				MainUI.MV.setView(IUpdateFrame);
			if(e.getSource()==UpdateBFrame.btnChoose)
				BringBook(UpdateBFrame.getTxtBid());
			if(e.getSource()==UpdateBFrame.btnUpdate)
				UpdateBook();
		}
		if(RemoveBFrame!=null)
		{
			if(e.getSource()==RemoveBFrame.btnBack)
				MainUI.MV.setView(IUpdateFrame);
			if(e.getSource()==RemoveBFrame.btnRBook)
			{
				DeleteBook((int)RemoveBFrame.comboBoxBooks.getSelectedIndex()+1);
			}
		}
		if(AddBFrame!=null)
		{
			if(e.getSource()==AddBFrame.btnBack)
				MainUI.MV.setView(IUpdateFrame);
			if(e.getSource()==AddBFrame.btnAddBook)
				AddBook();
		}
		if(adduserFrame!=null){
		if(e.getSource()==adduserFrame.btnBack){
			MainUI.MV.setView(libririanFrame);
		}
		if(e.getSource()==adduserFrame.btnAddUser)
		{
			AddUser(adduserFrame.GetUserName(), adduserFrame.GetUserPassword(), adduserFrame.GetFirstName(), adduserFrame.GetLastName(), adduserFrame.GetEmail());
		}
		}
		if(paymentFrame!=null){
			if(e.getSource()==paymentFrame.btnBack){
				MainUI.MV.setView(libririanFrame);
			}
			if(e.getSource()==paymentFrame.btnConfirm){
				pConfirm(1);
			}
			if(e.getSource()==paymentFrame.btnReject){
				pConfirm(2);
			}
		}
		if(reviewFrame!=null){
			if(e.getSource()==reviewFrame.btnBack){
				MainUI.MV.setView(libririanFrame);
			}
			if(e.getSource()==reviewFrame.btnConfirm){
				rConfirm(1);
			}
			if(e.getSource()==reviewFrame.btnEdit){
				if(reviewFrame.row<0) JOptionPane.showMessageDialog(null, "please select application", "please select application", JOptionPane.ERROR_MESSAGE);
				else{
				editFrame=new EditReviewUI(reviews.get(reviewFrame.row));
				MainUI.MV.setView(editFrame);
				}
			}
			if(e.getSource()==reviewFrame.btnReject){
				rConfirm(2);
			}
		}
	}

	@Override
	public void update(Observable o, Object obj) {
		// TODO Auto-generated method stub
		int massage;
		if (obj instanceof String)
			System.out.println(obj);
		else{
		Map<String, Object> map = (HashMap<String, Object>) obj;

		String op = (String) map.get("op");
		switch (op) 
		{
		case "AddUser": 
			if ((int)map.get("obj") == -1) 
			{
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
				adduserFrame.clearFields();	
			}
			else if((int)map.get("obj") == 0)
			{
				JOptionPane.showMessageDialog(null, "The user name already in DB", "The user name already in DB", JOptionPane.INFORMATION_MESSAGE);
				adduserFrame.clearFields();	
			}
			else if ((int)map.get("obj")==1) 
			{
				JOptionPane.showMessageDialog(null, "Insert user to DB", "Insert user to DB", JOptionPane.INFORMATION_MESSAGE);
				adduserFrame.clearFields();
			}
			break;
		case "BringBook":
			if (map.get("obj") instanceof Integer)
			{
				massage=(int)map.get("obj");
				if(massage==0){
					JOptionPane.showMessageDialog(null, "The book id not exist in DB", "The book id not exist in DB", JOptionPane.INFORMATION_MESSAGE);
					UpdateBFrame.clearFields();
				}
				if(massage==-1)
				{
					JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
					UpdateBFrame.clearFields();
				}	
			}
			else {
				bookET = (BookET)map.get("obj");
				UpdateBFrame.getTxtTitle().setText(bookET.getBTitle());
				UpdateBFrame.getTxtAuthor().setText(bookET.getBAuthor());
				UpdateBFrame.getTxtLan().setText(bookET.getBLanguage());
				UpdateBFrame.getTxtContent().setText(bookET.getBContent());
				UpdateBFrame.getTxtKwords().setText(bookET.getbKeywords());
				UpdateBFrame.getTxtASummary().setText(bookET.getBSummary());
			}
			break;
		case "UpdateBook":
			if ((boolean)map.get("obj") == false) 
			{
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
				UpdateBFrame.clearFields();	
			}
			else if((boolean)map.get("obj") == true)
			{
				JOptionPane.showMessageDialog(null, "The book is update in DB", "The book is update in DB", JOptionPane.INFORMATION_MESSAGE);
				UpdateBFrame.clearFields();	
			}
			break;
		case "BringGandS":
			if(map.get("obj") instanceof Integer){
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
			}
			else
			{

				genresET=(ArrayList<GenreET>)map.get("obj");
				switch(check){
				case 1:{
					AddGenreFrame = new AddGenreUI();
					AddGenreFrame.btnAdd.addActionListener((ActionListener)this);
					AddGenreFrame.btnBack.addActionListener((ActionListener)this);
					MainUI.MV.setView(AddGenreFrame);
					break;
				}
				
				case 2:{
					AddSubjectFrame = new AddSubjectUI(genresET);
					AddSubjectFrame.btnBack.addActionListener((ActionListener)this);
					AddSubjectFrame.btnAdd.addActionListener((ActionListener)this);
					MainUI.MV.setView(AddSubjectFrame);
					break;
				}
				case 3:{
					RemoveGenreFrame = new RemoveGenreUI(genresET);
					RemoveGenreFrame.btnBack.addActionListener((ActionListener)this);
					RemoveGenreFrame.btnRemove.addActionListener((ActionListener)this);
					MainUI.MV.setView(RemoveGenreFrame);
					break;
				}
				case 4:{
					RemoveSubjectFrame = new RemoveSubjectUI(genresET);
					RemoveSubjectFrame.btnBack.addActionListener((ActionListener)this);
					RemoveSubjectFrame.btnRemove.addActionListener((ActionListener)this);
					MainUI.MV.setView(RemoveSubjectFrame);
					break;
				}
				case 5:{
					PairingFrame = new PairingBookUI(BooksET, genresET);
					PairingFrame.btnBack.addActionListener((ActionListener)this);
					PairingFrame.btnPairBook.addActionListener((ActionListener)this);
					MainUI.MV.setView(PairingFrame);
					break;
				}
				case 6:{
					IUpdateFrame= new inventoryUpdateUI();
					IUpdateFrame.btnBack.addActionListener((ActionListener)this);
					IUpdateFrame.btnAddBook.addActionListener((ActionListener)this);
					IUpdateFrame.btnRBook.addActionListener((ActionListener)this);
					IUpdateFrame.btnUBook.addActionListener((ActionListener)this);
					MainUI.MV.setView(IUpdateFrame);
					break;
				}
				
			}
				}break;

		case "AddBook":
			if((int)map.get("obj")==1)
			{
				JOptionPane.showMessageDialog(null, "Insert Book to DB", "Insert Book to DB", JOptionPane.INFORMATION_MESSAGE);
				AddBFrame.clearFields();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
				AddBFrame.clearFields();
			}
			break;
		case "GetPaymentList":
			readers=(ArrayList<ReaderET>)map.get("obj");
			String sub=new String();
			paymentFrame=new PaymentConfirmationUI();
			paymentFrame.btnBack.addActionListener((ActionListener)this);
			paymentFrame.btnConfirm.addActionListener((ActionListener)this);
			paymentFrame.btnReject.addActionListener((ActionListener)this);
			for(int i=0 ; i<readers.size(); i++){
				if(readers.get(i).getSubscription()==0) sub="Pay per book";
				if(readers.get(i).getSubscription()==1) sub="Monthly Subscription";
				if(readers.get(i).getSubscription()==2) sub="Yearly Subscription";
				paymentFrame.model.addRow(new Object[] {
						readers.get(i).getId(),sub,
						readers.get(i).getCard_num(),readers.get(i).getrId()});
				}
			MainUI.MV.setView(paymentFrame);
			break;
		case "pConfirm":
			MainUI.MV.setView(libririanFrame);
			JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "GetReviewList":
			reviews=(ArrayList<ReviewET>)map.get("obj");
			reviewFrame=new ReviewConfirmationUI();
			reviewFrame.btnBack.addActionListener((ActionListener)this);
			reviewFrame.btnConfirm.addActionListener((ActionListener)this);
			reviewFrame.btnReject.addActionListener((ActionListener)this);
			reviewFrame.btnEdit.addActionListener((ActionListener)this);
			for(int i=0 ; i<reviews.size(); i++){
				reviewFrame.model.addRow(new Object[] {
						reviews.get(i).getBookName(),reviews.get(i).getUserName(),
						reviews.get(i).getRate(),reviews.get(i).getReview()});
				}
			MainUI.MV.setView(reviewFrame);
			break;
		case "rConfirm":
			MainUI.MV.setView(libririanFrame);
			JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "EditReview":
			MainUI.MV.setView(libririanFrame);
			JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "BringBooks":
			if(map.get("obj") instanceof Integer){
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				BooksET=(ArrayList<BookET>)map.get("obj");
			}
			break;
		case "DeleteBook":
			if((int)map.get("obj")==1)
			{
				JOptionPane.showMessageDialog(null, "The Book Remove From DB", "The Book Remove From DB", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "PairBook":
			if ((boolean)map.get("obj") == false) 
			{
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
				UpdateBFrame.clearFields();	
			}
			else if((boolean)map.get("obj") == true)
			{
				JOptionPane.showMessageDialog(null, "Success Pairing the book in DB", "Success Pairing the book in DB", JOptionPane.INFORMATION_MESSAGE);
				UpdateBFrame.clearFields();	
			}
			break;
		case "AddGenre":
			if ((int)map.get("obj") == -1) 
			{
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
				AddGenreFrame.clearFields();	
			}
			else if((int)map.get("obj") == 0)
			{
				JOptionPane.showMessageDialog(null, "The Genre already in DB", "The Genre already in DB", JOptionPane.INFORMATION_MESSAGE);
				AddGenreFrame.clearFields();	
			}
			else if ((int)map.get("obj")==1) 
			{
				JOptionPane.showMessageDialog(null, "Insert Genre to DB", "Insert Genre to DB", JOptionPane.INFORMATION_MESSAGE);
				AddGenreFrame.clearFields();
			}
			break;
		case "AddSubject":
			if ((int)map.get("obj") == -1) 
			{
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
				AddSubjectFrame.clearFields();	
			}
			else if((int)map.get("obj") == 0)
			{
				JOptionPane.showMessageDialog(null, "The Subject already in DB", "The Subject already in DB", JOptionPane.INFORMATION_MESSAGE);
				AddSubjectFrame.clearFields();	
			}
			else if ((int)map.get("obj")==1) 
			{
				JOptionPane.showMessageDialog(null, "Insert Subject to DB", "Insert Subject to DB", JOptionPane.INFORMATION_MESSAGE);
				AddSubjectFrame.clearFields();
				
			}
			break;
		case "RemoveGenre":
			System.out.println((int)map.get("obj"));
			if ((int)map.get("obj") == -1) 
			{
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);	
			}
			else if((int)map.get("obj") == 0)
			{
				JOptionPane.showMessageDialog(null, "This Genre has subject/s attached in DB! ", "This Genre has subject/s attached in DB! ", JOptionPane.INFORMATION_MESSAGE);
			}
			else if ((int)map.get("obj")==1) 
			{
				JOptionPane.showMessageDialog(null, "The genre delete from DB", "The genre delete from DB", JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		case "RemoveSubject":
			if ((int)map.get("obj") == -1) 
			{
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);	
			}
			else if((int)map.get("obj") == 0)
			{
				JOptionPane.showMessageDialog(null, "There is a book attached only to this subject! ", "There is a book attached only to this subject! ", JOptionPane.INFORMATION_MESSAGE);
			}
			else if ((int)map.get("obj")==1) 
			{
				JOptionPane.showMessageDialog(null, "The subject delete from DB", "The subject delete from DB", JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		}
		}
	}
	
	public void BringBook(int Bid)
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "BringBook");
		hmap.put("obj", Bid);
		
		client.handleMessageFromUI(hmap);
	}
	public void AddUser(String UserName,String UserPassword,String FirstN,String LastN,String eMail)
	{
		userET =new UserET(UserName,UserPassword);
		userET.setEmail(eMail);
		userET.setFirstName(FirstN);
		userET.setLastName(LastN);
		userET.setPermission(1);
		
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "AddUser");
		hmap.put("obj", userET);
		
		client.handleMessageFromUI(hmap);
	}
	public void UpdateBook()
	{
		bookET.setBTitle(UpdateBFrame.getTxtTitle().getText());
		bookET.setBAuthor(UpdateBFrame.getTxtAuthor().getText());
		bookET.setBLanguage(UpdateBFrame.getTxtLan().getText());
		bookET.setBContent(UpdateBFrame.getTxtContent().getText());
		bookET.setbKeywords(UpdateBFrame.getTxtKwords().getText());
		bookET.setBSummary(UpdateBFrame.getTxtASummary().getText());
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "UpdateBook");
		hmap.put("obj", bookET);
		
		client.handleMessageFromUI(hmap);
	}
	public void BringGandS()
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "BringGandS");
		
		client.handleMessageFromUI(hmap);
	}
	public void BringBooks()
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "BringBooks");
		
		client.handleMessageFromUI(hmap);
	}
	public void DeleteBook(int Bid)
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "DeleteBook");
		hmap.put("obj", Bid);
		
		client.handleMessageFromUI(hmap);
	}
	public void AddBook()
	{
		BookET NewBookET = new BookET(0, AddBFrame.getTxtTitle().getText(), AddBFrame.getTxtAuthor().getText(), AddBFrame.getTxtLan().getText(), AddBFrame.getTxtASummary().getText(), AddBFrame.getTxtContent().getText(), AddBFrame.getTxtKwords().getText(), (String)AddBFrame.getComboBoxGenres().getSelectedItem(),(String)AddBFrame.getComboBoxSubject().getSelectedItem(), "BookProfile", 0, 0, 0, 0, 0,Integer.parseInt(AddBFrame.getTxtPrice().getText()));
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "AddBook");
		hmap.put("obj", NewBookET);
		
		client.handleMessageFromUI(hmap);
	}
	public void GetPaymentList()
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "GetPaymentList");
		client.handleMessageFromUI(hmap);
	}
	public void pConfirm(int confirm)
	{
		if(paymentFrame.row<0) JOptionPane.showMessageDialog(null, "please select application", "please select application", JOptionPane.ERROR_MESSAGE);
		else{
		int id=(readers.get(paymentFrame.row)).getId();
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "pConfirm");
		hmap.put("confirm", confirm);
		hmap.put("id", id);
		client.handleMessageFromUI(hmap);
		}

	}
	public void GetReviewList()
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "GetReviewList");
		client.handleMessageFromUI(hmap);
	}
	public void rConfirm(int confirm)
	{
		if(reviewFrame.row<0) JOptionPane.showMessageDialog(null, "please select application", "please select application", JOptionPane.ERROR_MESSAGE);
		else{
		int id=(reviews.get(reviewFrame.row)).getId();
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "rConfirm");
		hmap.put("confirm", confirm);
		hmap.put("id", id);
		client.handleMessageFromUI(hmap);
		}

	}
	public void	EditReview(ReviewET review)
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "EditReview");
		hmap.put("review", review.getReview());
		hmap.put("id", review.getId());
		client.handleMessageFromUI(hmap);
	}

	public void PairBook()
	{
		BookET NewBookET = new BookET();
		NewBookET.setBID(Integer.parseInt(PairingFrame.txtIdBooks.getText()));
		NewBookET.setBGenre((String)PairingFrame.getComboGenre().getSelectedItem());
		NewBookET.setBSubject((String)PairingFrame.getComboSubject().getSelectedItem());
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "PairBook");
		hmap.put("obj", NewBookET);
		
		client.handleMessageFromUI(hmap);
	}
	public void AddGenre(GenreET Newgenre)
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "AddGenre");
		hmap.put("obj", Newgenre);
		
		client.handleMessageFromUI(hmap);
	}
	public void AddSubject(SubjectET Newsubject)
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "AddSubject");
		hmap.put("obj", Newsubject);
		
		client.handleMessageFromUI(hmap);
	}
	public void RemoveGenre(int Gid)
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "RemoveGenre");
		hmap.put("obj", Gid);
		
		client.handleMessageFromUI(hmap);
	}
	public void RemoveSubject(int GTitle,String STitle)
	{
		Map<Integer,Object> Titles = new HashMap<Integer,Object>();
		Titles.put(1,GTitle);
		Titles.put(2,STitle);
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "RemoveSubject");
		hmap.put("obj", Titles);
		
		client.handleMessageFromUI(hmap);
	}
}
