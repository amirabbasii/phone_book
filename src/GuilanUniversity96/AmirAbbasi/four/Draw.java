package GuilanUniversity96.AmirAbbasi.four;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
/*
 * GUI be soorate daste kod zade shode ast
 */
public class Draw extends JPanel{
	int n=0;
protected JButton del=new JButton();//dokme hazfe nahayi
protected JButton cancel=new JButton("Cancel");
	ArrayList<JButton > list=new ArrayList<>();//JButoon ha
	ArrayList<JLabel> images=new ArrayList<>();//Label ha(baraye tasvir)
	protected JButton delete = new JButton();//dokme hazf
	protected JButton newO = new JButton();//dokme new
	protected JButton underIt = new JButton();//dokme zirshahke 1
	protected JButton underIt2=new JButton();//dokme zirshahke 2
	protected JButton back=new JButton();//dokme bargasht
	protected JScrollBar bar=new JScrollBar();
	protected JTextField searchBox = new JTextField("Search  box");// texte filed frame
	private ArrayList<JCheckBox> check=new ArrayList<>();

	/**
	 * sakhte panel baraye:list ke mitavanad aks dashte bashad ya na
	 * flag==true --> ba aks
	 * flag==false --> bi aks
	 * addressbook ha bare geft
	 * @param names
	 * @param flag
	 * @param addressBook
	 */
		public Draw(String[] names,int type,AddressBook addressBook) {
			searchBox.setBounds(10, 0, 540, 30);
		
			this.setBounds(0, 0, 600, 800);
			this.setBackground(Color.cyan);//pas zamine panel
				for (int i = 0; i < names.length; i++) {

					JButton tmp = new JButton();
					tmp.setFont(new Font("TimesRoman", Font.PLAIN, 20));//font
					tmp.setText(names[i]);//text
					switch(type) {
					case 1://addressbook page
						tmp.setBounds(10, 60 + 100 * i, 540, 100);
						if (tmp.getY() >= 660)
							tmp.setVisible(false);
						break;
					case 2://contact page
						tmp.setBounds(10, 60 + 100 * i, 440, 100);
						if (tmp.getY() >= 660)
							tmp.setVisible(false);
						break;
					case 3://group,loaction page
						tmp.setBounds(10, 20 + 50*i, 450, 50);
						if (tmp.getY() >= 350)
							tmp.setVisible(false);
						break;
						
					}
					tmp.setBackground(Color.WHITE);//range button
					
					list.add(tmp);//button be list ezafe mishavad
				this.add(list.get(i));//button be panel ezafe mishavad
			}
				if(type==2) {//flag==true :ask are!
					File file;
					for(int i=0;i<list.size();i++) {
						//check mishavad contact  aks darad ya na
						file=new File("Address books/"+addressBook.nameGetter()+"/"+addressBook.list.get(i).nameGetter()+".png");//address aksi ke momkan ast bashad
						ImageIcon tmpImg;
						if(file.exists())//agar aks vojood dasht
							 tmpImg= new ImageIcon("Address books/"+addressBook.nameGetter()+"/"+addressBook.list.get(i).nameGetter()+".png");
						else {//agar vojood nadasht bar asase jensiat aks dar nazar migirad
							if(addressBook.getContact(i).sexGetter().equals("man"))
								tmpImg = new ImageIcon("man.png");
							else
								tmpImg = new ImageIcon("woman.png");
						}
						
						JLabel tmpLable = new JLabel();//label baraye aks
						tmpLable.setIcon(tmpImg);//aks be tmpLable afzoode mishavad
						tmpLable.setBounds(450, 60 + 100 * i, 100, 100);
						if (tmpLable.getY() >= 660)//agar Y>660 az mahoode namayesh kharej shode ast
							tmpLable.setVisible(false);
						images.add(tmpLable);//be list ezafe mishavad
						this.add(images.get(i));//be panel ezafe mishavad
						}
				}
				///////////tanzime dokme haye pish farz////////////
				if(type==1 || type==2) {
				back.setBounds(450, 680, 110, 70);
				this.add(back);
				this.add(searchBox);
				newO.setBounds(10, 680, 110, 70);
				this.add(newO);
				delete.setBounds(120, 680, 110, 70);
				this.add(delete);
				underIt.setBounds(230, 680, 110, 70);
				this.add(underIt);
				underIt2.setBounds(340, 680, 110, 70);
				this.add(underIt2);
				bar.setBounds(560, 0, 20, 750);
				bar.setValues(0, 6, 0, list.size());}
				else
				{	
					delete.setBounds(360, 400, 100, 50);
				this.add(delete);
				
				newO.setBounds(10, 400, 100, 50);
				this.add(newO);
				
				underIt.setBounds(110, 400, 100, 50);
				this.add(underIt);
				
				bar.setBounds(465, 0, 15, 450);
				bar.setValues(0, 7, 0, list.size());
				}
				this.add(bar);
				/////////////////////////////////////////////////
				
				
				
				//////tanzime bar////////////////////////////
				bar.addAdjustmentListener(new AdjustmentListener() {
					int defaultValue = 0;

					@Override
					public void adjustmentValueChanged(AdjustmentEvent action) {
						if (bar.getValue() > defaultValue) {
							mvObj(type,-1);
							defaultValue = bar.getValue();
							
						} else {
							mvObj(type,1);
							defaultValue = bar.getValue();
						}
					}
					
				});
				//////////////////////////////////////////////////
		}
	
	/**
	 * update kardan
	 * yek khane be list ezafe mikonad
	 * flag==false --> bedoone tasvir
	 * flag==true --> ba tasvir
	 * @param name
	 * @param flag
	 */
	public void adder(String name,boolean flag,String sex) {
	for(int i=0;i<list.size();i++)
		list.get(i).setBounds(10,  60+ 100 * i, list.get(list.size()-1).getWidth(), 100);
	if(flag==true) {//afzoodane tasvir
		for(int i=0;i<images.size();i++)
			images.get(i).setBounds(450, 60 + 100 * i, 100, 100);
		
		ImageIcon tmpImg = new ImageIcon(sex+".png");
		JLabel tmpLable = new JLabel();

		tmpLable.setIcon(tmpImg);
		tmpLable.setBounds(450, 60 + 100 * images.size(), 100, 100);
		if (tmpLable.getY() >= 660)//agar y>660 yani az mahdoode namayesh kharej shode
			tmpLable.setVisible(false);
		images.add(tmpLable);
		this.add(images.get(images.size()-1));
	}
	if(list.size()>5)//rafe buge ajib
		list.get(5).setVisible(true);
		JButton tmp = new JButton();
		tmp.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		tmp.setText(name);
		if(flag==false)
		tmp.setBounds(10, 60 + 100 * list.size(), 540, 100);//bi aks
		else
			tmp.setBounds(10, 60 + 100 * list.size(), 440, 100);//ba aks
		if (tmp.getY() >= 660)//kharej az mahdoode
			tmp.setVisible(false);
		tmp.setBackground(Color.WHITE);
		list.add(tmp);
	this.add(list.get(list.size()-1));

			
		}
	/**
	 * afzoodan be list
	 * baraye panel haye fari(group,location)
	 * @param name
	 */
	public void adder(String name) {
		for(int i=0;i<list.size();i++) 
			list.get(i).setBounds(10, 20 + 50*i, 450, 50);
		
		if(list.size()>7)
			list.get(7).setVisible(true);
		JButton tmp = new JButton();
		tmp.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		tmp.setText(name);
		
			tmp.setBounds(10, 20 + 50*list.size(), 450, 50);
			if (tmp.getY() >= 350)//kharej az mahdoode
				tmp.setVisible(false);
			tmp.setBackground(Color.WHITE);
			list.add(tmp);
			this.add(list.get(list.size()-1));
		


	}
	/**
	 * hazf JButton haye gofte shodwe az list va menu
	 * baraye location va group
	 * @param names
	 */
	public void remover(String[] indexes) {
		
		for(int i=0;i<list.size();i++) 
			list.get(i).setBounds(10, 20 + 50*i, 450, 50);
		for(int i=0;i<indexes.length;i++) {
			this.remove(list.get(Integer.parseInt(indexes[i])));
			list.remove(Integer.parseInt(indexes[i]));
			}
		for(int i=0;i<list.size();i++) {
			list.get(i).setBounds(10, 20 + 50*i, 450, 50);
			list.get(i).removeAll();
			if(list.get(i).getY()>=350)
				list.get(i).setVisible(false);
			else
				list.get(i).setVisible(true);}
		check.clear();
		repaint();
	}
	/**
	 * hazf JButton haye gofte shodwe az list va menu
	 * baraye addressbook va contact
	 * @param names
	 * @param flag
	 */
	public void remover(String names[],boolean flag) {
		for(int i=0;i<list.size();i++) 
			list.get(i).setBounds(10, 20 + 50*i, list.get(list.size()-1).getWidth(), 50);
		int w;
		if(flag==false) 
			w=540;
		else 
			w=440;
		for(int i=0;i<names.length;i++) {
			this.remove(list.get(Integer.parseInt(names[i])));
			list.remove(Integer.parseInt(names[i]));
			if(flag==true) {
				this.remove(images.get(Integer.parseInt(names[i])));
				images.remove(Integer.parseInt(names[i]));
			}
			}
		for(int i=0;i<list.size();i++) {
			list.get(i).removeAll();
			list.get(i).setBounds(10, 60+100*i, w, 100);
			if(list.get(i).getY()>=660)
				list.get(i).setVisible(false);
			else
				list.get(i).setVisible(true);
			if(flag==true) {
				images.get(i).setBounds(450, 60 + 100 * i, 100, 100);
				if(images.get(i).getY()>=660)
					images.get(i).setVisible(false);
				else
					images.get(i).setVisible(true);
			}
			}
		check.clear();
		
	}
	/**
	 * tanzime value bar
	 * @param a
	 * @param b
	 */
	public void barValues(int a,int b) {
		bar.setValues(0, a, 0, b);
	}
	/**
	 * afzoode action be dokme akhar dar list
	 * @param action
	 */
	public void lastButtonAction(ActionListener action) {
		list.get(list.size()-1).addActionListener(action);
	}
	/**
	 * "name anjast?
	 * dar vaghe chon texte dokme ha name aza hastand tarfandi baraye search ast
	 * @param name
	 * @return boolean
	 */
	public boolean isThereBt(String name) {
		boolean flag=false;
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getText().equals(name)) {
				flag=true;
				break;
			}
		}
		return flag;
	}
	/**
	 * jabejayi menu
	 * dar bar estefade shode ast
	 * @param type  :noe panel(1,2,3)
	 * 
	 * @param l :ya -1 ya 1 ast va baraye bala payin raftan naghsh darad
	 */
	public  void mvObj(int type,int l) {
		for (int i = 0; i < list.size(); i++) {
			int y=list.get(i).getY();
			switch(type) {
			case 1://addressbokk page
				list.get(i).setBounds(10, y + l*100, 540, 100);
				if(list.get(i).getY()>=660 || list.get(i).getY()<60)//check kardane mahdoode
					list.get(i).setVisible(false);
				else
					list.get(i).setVisible(true);
				break;
			case 2://contacts page
				list.get(i).setBounds(10, y + 100, 440, 100);
				images.get(i).setBounds(450, images.get(i).getY() + 100, 100, 100);
				if(list.get(i).getY()>=660 || list.get(i).getY()<60) {//check kardane mahdoode
					list.get(i).setVisible(false);
					images.get(i).setVisible(false);
				}
				else {
					list.get(i).setVisible(true);
				images.get(i).setVisible(true);
			}
				break;
			case 3://group,location
				list.get(i).setBounds(10, y + l*50, 455, 50);
				if(list.get(i).getY()>=350 || list.get(i).getY()<20)//check kardane mahdoode
					list.get(i).setVisible(false);
				else
					list.get(i).setVisible(true);
			}
			
				

			}
		
	}
	/**
	 * taghire halate panel be hazf kardan
	 * @param type
	 */
	public void delPanel(int type) {
		n=0;//tedade entekhab shode ha
		
		del.setText("Delete");
		/////////////dokme haye ezafi makhfi mishavand
		switch(type) {
		case 1://addressbook page
			
			newO.setVisible(false);
			delete.setVisible(false);
			searchBox.setVisible(false);
			del.setBounds(250, 680, 200, 70);
			cancel.setBounds(100, 680, 150, 70);
			break;
		case 2://contacts page
			delete.setVisible(false);
			newO.setVisible(false);
			underIt.setVisible(false);
			underIt2.setVisible(false);
			back.setVisible(false);
			searchBox.setVisible(false);
			del.setBounds(250, 680, 200, 70);
			cancel.setBounds(100, 680, 150, 70);
			break;
		case 3://group,location
			del.setBounds(300, 400, 150, 50);
			cancel.setBounds(100, 400, 120, 50);
			
			this.newO.setVisible(false);
			this.delete.setVisible(false);
	break;
			
		}
cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch(type) {
				case 1://addressbook page
					delete.setVisible(true);
					newO.setVisible(true);
					searchBox.setVisible(true);
				break;
				case 2://contacts page
					delete.setVisible(true);
					newO.setVisible(true);
					underIt.setVisible(true);
					underIt2.setVisible(true);
					back.setVisible(true);
					searchBox.setVisible(true);
					
					break;
				case 3://group,location
					delete.setVisible(true);
					newO.setVisible(true);
			
					
				}
				for(int i=0;i<list.size();i++)
					list.get(i).removeAll();//pak shodane checkbox ha
				check.clear();//tamiz shodan arraye check box
		
	remove(del);
	remove(cancel);
	repaint();
				
			}
		});
		add(del);
		add(cancel);
		/////////be tedade button ha JCheckBox sakhte mishavad///////////////
		for(int i=0;i<this.list.size();i++) {
			JCheckBox chTmp=new JCheckBox();
			chTmp.setBounds(0, 0, 20, 20);
			
			chTmp.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {//ActionListener
					if(chTmp.isSelected())
						n++;//tedad ziad mishavad
					else
						n--;///tedad kam mishavad
					del.setText("Delete "+String.valueOf(n)+" choices");//update texte dokme hazf
				}
			});
			this.list.get(i).add(chTmp);
			check.add(chTmp);
		}
		//////////////////////////////////////////////////
		
		}
	/**
	 * vazife  hazf az panel va bargardandane  gozine haye entekhabi ra bar ohde darad
	 * @param type
	 * @return
	 */
public int[] delAction(int type) {
	ArrayList<Integer> indexes=new ArrayList<>();


			int j=0;//chon ba hazf index ha yeki yeki kam mishavad
			if(n==0)//yani hich entekhabi nashode
				JOptionPane.showMessageDialog(null, "You didn't choose any address book", "Delete", 0, null);
			else {
			int bit=JOptionPane.showOptionDialog(null, "Are you sure?", "Delete groups", 0, 1, null, new String[] {"Yes","No"}, null);//motmaeni?
		String temp="";//liste index addressbook haye entekhab shode
			if(bit==0) {//Are mikham hazf konam!
				
				for(int i=0;i<check.size();i++) {
					if(check.get(i).isSelected()) {
						temp+=String.valueOf(i-j)+"#";//index sabt mishavad(string)
						indexes.add(i-j);//index sabt mishavad(araye)
					
						j++;
					}
				}
			}
			String arr[]=temp.split("#");//araye index ha
			remove(del);
			remove(cancel);
			barValues(6,list.size()-j );//update bar
			switch(type) {
			case 1://addressbook page
				delete.setVisible(true);
				newO.setVisible(true);
				searchBox.setVisible(true);
			remover(arr,false);//update panel
			break;
			case 2://contacts page
				delete.setVisible(true);
				newO.setVisible(true);
				underIt.setVisible(true);
				underIt2.setVisible(true);
				back.setVisible(true);
				searchBox.setVisible(true);
				remover(arr,true);//update panel
				
				break;
			case 3://group,location
				delete.setVisible(true);
				newO.setVisible(true);
				remover(arr);//update panel
				break;
			}
	
remove(del);
remove(cancel);
repaint();
			}

	
int [] index=new int[indexes.size()];//tabdile ArrayList<Integer> be int[]
for(int i=0;i<index.length;i++)
	index[i]=indexes.get(i);
check.clear();//reset check
return index;
}
	
}
