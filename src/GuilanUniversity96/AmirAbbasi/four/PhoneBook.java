package GuilanUniversity96.AmirAbbasi.four;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PhoneBook {
	static JFrame frame = new JFrame();// frame asli

static ArrayList<AddressBook> addressBook = new ArrayList<>();// araye baraye AddressBook
	static JLabel total = new JLabel();// lable tedad dar frame

	/**
	 * ebteda group ha va location ha load mishavand
	 * AddressBook ha dar an load mishavand
	 * folder haye mojood dar folderre "Address books" list mishavand va be tabe fileList() ferestade mishavand ta file haye daroone anha bargardande shavand
	 * 
	 */
	public static void main(String[] args) {
		
		String files[]=fileList("Address books");// file ha(folder ha ke address book ha hastand)
		frame.setSize(600, 800);
		/////////////////////// searchBox settings///////////////////////////////////
		int tmp = 1;
		String temp = "";
		int i;
///////////////////////////////////////////////////////khandane group ha/////////////////////////////////
try {
FileReader reader = new FileReader("groups.txt");//file group ha

tmp = 1;
temp = "";
while (tmp != -1) {
tmp = reader.read();
temp += (char) tmp;
}
reader.close();
} catch (IOException exception) {
File file = new File("groups.txt");//sakhte  file
JOptionPane.showMessageDialog(null, "groups.txt not found.press OK to create it", "groups.txt", 0, null);
try {
file.createNewFile();
temp = "?";
} catch (IOException e) {
JOptionPane.showMessageDialog(null, "An error when creating file.Maybe you should run the program as Adminstrator/Root.So maybe the program doesn't work correctly.Press Ok to close the program","Couldn't make file", 0, null);
System.exit(0);
}


}
temp = temp.substring(0, temp.length() - 1);

if (temp.length() != 0) {
String[] group = temp.split("#");//group ha be soorate String[]
for (i = 0; i < group.length; i++) {
Group.newGroup(group[i]);//bargozari group
}
}

/////////////////////////////////////////////////////////////////bargozri location ha///////////////////////////////////////////////////
tmp = 1;
temp = "";
try {
FileReader reader = new FileReader("locations.txt");

while (tmp != -1) {
tmp = reader.read();
temp += (char) tmp;

}
reader.close();
} catch (IOException exception) {
File file = new File("locations.txt");//sakhte file
JOptionPane.showMessageDialog(null, "locations.txt not found.press Ok to create it", "locations.txt", 0, null);
try {
file.createNewFile();
temp = "?";
} catch (IOException e) {
JOptionPane.showMessageDialog(null, "An error when creating file.Maybe you should run the program as Adminstrator/Root.So maybe the program doesn't work correctly.Press Ok to close the program","Couldn't make file", 0, null);
System.exit(0);
}

//JOptionPane.showMessageDialog(null, "An error ecured in reading "+"Address
//books/"+name+"/locations.txt\n"+exception.getMessage(), "Error",0, null);
}

temp = temp.substring(0, temp.length() - 1);

if (temp.length() != 0) {
String[] locs=temp.split("#");
for( i=0;i<locs.length;i++) {
String[] rt = locs[i].split(":");//moshakhasae location dar nazar gerefte shode be soorate String[]
Location.newLocation(rt[0], rt[1], rt[2], rt[3], rt[4], Integer.parseInt(rt[5]),Integer.parseInt(rt[6]), Double.parseDouble(rt[7]));//bargozari location
}
}

/////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		////////////// khandane address book haye zakhire shode
		if (files != null) {
			for ( i = 0; i < files.length; i++) {
				addressBook.add(new AddressBook(files[i]));
				addressBook.get(i).loadAddressBook();
				}}
		


				mainPage();//sakhte page address book ha
	}
/**
 * vazife taskile safhe baraye addressBook ke be an ferestade shode ra bar ohde darad(page contact ha)
 * lsit shamele Arraylist az  JButton ha ast
 * 
 * @param addressBook
 */
	public static void bookLoader(AddressBook addressBook) {
frame.repaint();

	int i;
	////////////////tashkile panele monaseb/////////////////
		String[] inArr=new String[addressBook.list.size()];
		for(i=0;i<inArr.length;i++) {//copy az addressBook.list:Arraylist<Contact> dar String[] 
			inArr[i]=addressBook.list.get(i).nameGetter();}
		Draw panel=new Draw(inArr, 2,addressBook);//sakhte panel
		System.out.println();
		///////nam dehi be object haye pish farz/////////////
		panel.delete.setText("Delete");
		panel.newO.setText("New cntact");
		panel.underIt.setText("Groups");
		panel.back.setText("Back");
		panel.underIt2.setText("Location");
		//////////////////////////////////////
		
		///////////////tarife ActionListener haye dokme ha///////
		
		
		panel.back.addActionListener(new ActionListener() {//bargasht be safhe asli
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.remove(panel);//in panel hazf mishavad
				mainPage();//bargasht be safhe asli
				
			}
		});
	
		panel.underIt2.addActionListener(new ActionListener() {//Location ha
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				locationPnl(addressBook);//sakhte safhe location ha
			}
		});
		panel.setLayout(null);
		frame.add(panel);
		
		//////////tarife ActionListener baraye button haye contact/////////////////
		for(i=0;i<addressBook.nubmerOfConcats();i++) {
			final Contact t=addressBook.getContact(i);
			JButton bt=panel.list.get(i);
		panel.list.get(i).addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
			contactPnl(t, addressBook, bt);//Add Action
				}
			});}
		//////////////////////////////////////////////////////
		
		//////////////////////////////////////////////////////////////////////////////////newO Action///////////////////////////////////////////////
		panel.newO.addActionListener(new ActionListener() {//contact jadid
		
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Contact concat=new Contact(null);
				String sex=null;//jensiate contact
				String name=JOptionPane.showInputDialog(null, "Please enter name", "Name(obliging)", 1);//gereftane name
				boolean eror;//flage dorost sakhte shodan ya nashodan contact;agar try ta akhar anjam shavad flag=true va agar exception rokh dahad flag=false
				try {
				while(name.equals("") )//check kardane voroodi
				name=JOptionPane.showInputDialog(null, "Number can't be empty!Please enter name", "Name(obliging)", 1);
				String number=number=JOptionPane.showInputDialog(null, "Please enter number", "Number(obliging)", 1);//gereftane number
				while(number.equals("") || !number.toLowerCase().equals(number.toUpperCase())) {//check kardane voroodi
					if(!number.toLowerCase().equals(number.toUpperCase()))//check kardane voroodi az nazar adad boodan
						number=JOptionPane.showInputDialog(null, "It can't be a number!Please enter correct number", "Number(obliging)", 1);
					else
					number=JOptionPane.showInputDialog(null, "Number can't be empty!Please enter number", "Number(obliging)", 1);
					}
			
				concat.nameSetter(name);//set name
				concat.numberSetter(number);//set number
				concat.birthdaySetter(JOptionPane.showInputDialog(null, "Please enter birthday", "Birthday", 1));//gereftane birthday
				sex=JOptionPane.showInputDialog(null, "Please choose yuor sex", "Sex", 1, null, new String[] {"man","woman"}, "man").toString();//gereftane sex
				concat.sexSetter(sex);//set sex
				concat.emailSetter(JOptionPane.showInputDialog(null, "Please enter Email", "Email", 1));//gereftane email
				eror=false;//amaliat be dorosti anjam shode va error false mishavad
				}
				catch(NullPointerException exception) {
					eror=true;//exception rokh dade va eror true mishavad
				}
				if(eror==false) {//agar exceptionrokh nadade
			addressBook.addConcat(concat);//contact be addressbook ezafe mishavad
			addressBook.writeConcats();//update file contacts
				panel.barValues(6, addressBook.nubmerOfConcats());//update bar
				panel.adder(name, true,sex);//update panel
				
				
				/////////////////afzoodane Action/////////////
				panel.lastButtonAction(new ActionListener() {
					Contact t=concat;
					JButton bt=panel.list.get(panel.list.size()-1);
					@Override
					public void actionPerformed(ActionEvent arg0) {
						contactPnl(concat, addressBook, bt);						
						
					}
				});
				////////////////////////////////////////////
				panel.repaint();
				total.setText(String.valueOf(addressBook.nubmerOfConcats())+" conctacts");//update total
				frame.repaint();}
			}
		});
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////Delete Action//////////////////////////////////////////////////////////////
		panel.delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(panel.list.size()==0)
					JOptionPane.showMessageDialog(null, "There is nothing to delete!");
				else {
				panel.delPanel(2);//taghire panel be shekle hazf
				panel.del.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						///amaliat hazfe entekhab shode ha/////////
						int[] indexes=panel.delAction(2);
						for(int i=0;i<indexes.length;i++)
							addressBook.delete(indexes[i]);
						addressBook.writeConcats();//update file coctacts
						////////////////////////////////////
						total.setText(String.valueOf(addressBook.nubmerOfConcats())+" conctacts");//update total
						
					}
				});
				}		}}
		);
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		panel.bar.addAdjustmentListener(new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				panel.repaint();
				frame.repaint();
				
			}
		});
		////////////////underIt:group////////////////////////
		panel.underIt.addActionListener(new ActionListener() {
			
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				groupPnl(addressBook);
			}		
		});
		////////////////////////////////////////////////
		total.setText(String.valueOf(addressBook.nubmerOfConcats()) + " contacts");
		panel.searchBox.addActionListener(new ActionListener() {//ActionListener searchBox
			/**
			 * ebteda panel khali mishavad va sepas agar aval texte searchbox ba avale mored yek bood dar panel add mishavad
			 * agar texte searchbox khali bashad yani reset beshavad va ebtedda panel khali va sepas tamme mavared add mishavand
			 */
			String text=panel.searchBox.getText();
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				for(int i=0;i<panel.list.size();i++)//panel khali mishavad
				{panel.remove(panel.images.get(i));
					panel.remove(panel.list.get(i));
				}
				panel.list.clear();//khali kardane list button ha
				panel.images.clear();//khali kardane liste tasavir
int count=0;//tedad
				if(!panel.searchBox.getText().equals("")) {
					for(int i=0;i<addressBook.nubmerOfConcats();i++) {//shemordane mavarede yaft shavande
						if(addressBook.getContact(i).nameGetter().indexOf(panel.searchBox.getText())==0) 
							count++;
					}
					panel.barValues(6, count);//update bar
					for(int i=0;i<addressBook.nubmerOfConcats();i++) {
					if(addressBook.getContact(i).nameGetter().indexOf(panel.searchBox.getText())==0) 
						panel.adder(addressBook.getContact(i).nameGetter(),true,addressBook.getContact(i).sexGetter());
				}
					total.setText(String.valueOf(panel.list.size())+" address books;Filter="+panel.searchBox.getText());//update total
					
					panel.repaint();
					frame.repaint();
				}
				else {//halate reset
					panel.barValues(6, addressBook.nubmerOfConcats());//update bar
				for(int i=0;i<addressBook.nubmerOfConcats();i++)//yek beyek ezafe mishavand
					panel.adder(addressBook.getContact(i).nameGetter(),true,addressBook.getContact(i).sexGetter());
		
			total.setText(addressBook.nubmerOfConcats()+" contacts");//update total
			panel.repaint();
			frame.repaint();
			}

			}
		});
	}
	/**
	 * liste file ha ra bar migardanad
	 * @param address
	 * @return liste file ha
	 */
	public static String[] fileList(String address) {
		String[] files=null;
		File file=new File(address);
		if(file.exists())
			files=file.list();
			return files; 
		
	}
	/**
	 * sakhte mainPage(addressbook)
	 */
	public static void mainPage() {
		
		String[] inArr=new String [addressBook.size()];//araye name addressbook ha
		for(int i=0;i<addressBook.size();i++) {
			inArr[i]=addressBook.get(i).nameGetter();//name ha dar inArr[] rikhte mishavad
		}
		Draw panel=new Draw(inArr,1,null);//Constructor(name ha,bedoone aks,null)
		panel.setLayout(null);

				/////////////////////////////// total settings///////////////////////////
				total.setText(String.valueOf(addressBook.size()) + " address books");
				total.setBounds(10, 37, 600, 10);
				frame.add(total);
				////////////////////////////////////////////////////////////////////////

				for(int i=0;i<addressBook.size();i++) {
					AddressBook t=addressBook.get(i);
					panel.list.get(i).addActionListener(new ActionListener() {//afzoodane actionListener baraye button haye panel
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							frame.remove(panel);//panel az frame hazf mishavad ta badan panele marboot be addressbooke morede nazar be frame ezafe shavad
							bookLoader(t);
							
						}
					});
				}
				///////////////////////// bar settings////////////////////////////////
				panel.bar.addAdjustmentListener(new AdjustmentListener() {
					@Override
					public void adjustmentValueChanged(AdjustmentEvent arg0) {
					
						panel.repaint();
		frame.repaint();
					}
				});
				
				//////////////////////////////newAb settings////////////////////////////////////
				
				panel.newO.setBounds(10, 680, 200, 70);
				panel.newO.setText("New address book");
				panel.underIt.setVisible(false);
				panel.underIt2.setVisible(false);
				panel.back.setVisible(false);
				panel.delete.setBounds(300, 680, 200, 70);
				panel.delete.setText("Delete");
				panel.newO.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
					boolean error=false;//flagi baraye exeption;agar exception rokh dahad true vagarna fase misgavad
						boolean flag = true;// flag baraye tekrari boodan ya naboodane name address book;
						String name=null ;
						try {
						while (flag == true || name.equals("")) {// name voroodi check mishavad ta tekrari nabashad ya khali nabashad
							name = JOptionPane.showInputDialog(null, "Please enter name of address book", "New address book",1);
							flag = false;
							for (int i = 0; i < addressBook.size(); i++) {
								if (addressBook.get(i).nameGetter().equals(name)) {
									flag = true;
									JOptionPane.showMessageDialog(null, "Address book already exist,please enter another name","Error", 0, null);
									break;
								}
							}
						}}
						catch(NullPointerException exception) {
							error=true;
						}
						if(error==false) {//agar exception rokh nadade bashad
						AddressBook tmp = new AddressBook(name);//address book sakhte mishavad
						tmp.writeAddressBook();//foldere an sakhte mishavad
						addressBook.add(tmp);
						panel.bar.setValues(0, 6, 0, addressBook.size()+1);//update bar
						panel.adder(name, false,null);//update panel
						panel.list.get(panel.list.size()-1).addActionListener(new ActionListener() {//afzoodane Action Listener
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								frame.remove(panel);//panele alan hazf mishavad ta badan be frame paneli ezafe shavad
								bookLoader(addressBook.get(addressBook.size()-1));
								
							}
						});
						
						
		panel.repaint();
		total.setText(String.valueOf(addressBook.size())+" address books");//update total
		frame.repaint();
					}}
				});
				panel.delete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(panel.list.size()==0)
							JOptionPane.showMessageDialog(null, "There is nothing to delete!");
						else {
						panel.delPanel(1);//taghire panel be halate hafz
						panel.del.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								/////////////////amaliate hazf///////////////
								int []indexes=panel.delAction(1);
								for(int i=0;i<indexes.length;i++) {
									File file=new File("Address books/"+addressBook.get(indexes[i]).nameGetter());
								
									File[] files=file.listFiles();
									for(int k=0;k<files.length;k++)//hafz
										files[k].delete();
									//////////////////////////////////////////
									file.delete();//hazfe khode foldere address book
									addressBook.remove(indexes[i]);//hazfe addressbook az liste addressbook ha
									total.setText(String.valueOf(addressBook.size())+" address books");//update total
									
							}
							}
						});
						
					}
						}
				});
				frame.add(panel);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				///////////////////SearchBox///////////////////////
				
				panel.searchBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						/**
						 * ebteda panel khali mishavad va sepas agar aval texte searchbox ba avale mored yek bood dar panel add mishavad
						 * agar texte searchbox khali bashad yani reset beshavad va ebtedda panel khali va sepas tamme mavared add mishavand
						 */
						for(int i=0;i<panel.list.size();i++)//panel khali mishavad
						{
							panel.remove(panel.list.get(i));
						}
						panel.list.clear();//list khali mishavad
						int count=0;//tedad
						if(!panel.searchBox.getText().equals("")) {//search
							for(int i=0;i<addressBook.size();i++) {
								if(addressBook.get(i).nameGetter().indexOf(panel.searchBox.getText())==0) //shemordane tedade yaf shavande
									count++;
							}
							panel.barValues(6, count);//update bar
							for(int i=0;i<addressBook.size();i++) {//ezafe mavarede motabegh
							if(addressBook.get(i).nameGetter().indexOf(panel.searchBox.getText())==0) 
								panel.adder(addressBook.get(i).nameGetter(),false,null);
						}
							total.setText(String.valueOf(panel.list.size())+" address books;Filter="+panel.searchBox.getText());//update total
							
							panel.repaint();
							frame.repaint();
						}
						else {//halate reset
							panel.barValues(6, addressBook.size());//update bar
						for(int i=0;i<addressBook.size();i++)//tak be tak ezafe mishavand
							panel.adder(addressBook.get(i).nameGetter(),false,null);
				
					total.setText(addressBook.size()+" address books");//update total
					panel.repaint();
					frame.repaint();
					}

						}
				});
				////////////////////////////////////////////////
	}
	/**
	 * sakhtane panel baraye Group ra bar ohde darad
	 * @param addressBook
	 */
public static void groupPnl(AddressBook addressBook) {
	String arr[]=new String [Group.groupList.size()];//araye baraye rikhtane name group ha dar an baraye sakhte panel
	for(int i=0;i<Group.groupList.size();i++)
		arr[i]=Group.groupList.get(i).nameGetter();

	Draw p=new Draw(arr,3,null);//sakhte panel
	p.setLayout(null);

JFrame d=new JFrame();//frame jadid 
d.add(p);
d.setVisible(true);
d.setBounds(50, 50, 500, 500);
p.delete.setText("Delete");
p.newO.setText("New");
p.underIt.setVisible(false);
////////////////Action listeners////////////
for(int i=0;i<p.list.size();i++){
Group er=Group.groupList.get(i);
p.list.get(i).addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent arg0) {
gpAction(er,addressBook);
}
});
}
/////////////////////////////////////


p.newO.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent arg0) {
	
	
	////gereftane name group va check kardane an//////////
	boolean flag=true,error;
String name="";
try {
while(name.equals("") || flag==true) {//agar tekrari ya khali bashad
	name=JOptionPane.showInputDialog("Please enter name of group");
	flag=false;
	for(int i=0;i<Group.numberOfGroups();i++) {
		if(Group.getGroup(i).nameGetter().equals(name)) {
			flag=true;
			break;
		}
	}
}
error=false;}
catch(NullPointerException e) {
error=true;	
}
if(error==false) {
Group.newGroup(name);//be group ha ezafe mishavad
Group.writeGroups();//update file groups
p.bar.setValues(0, 7, 0, Group.groupList.size());//update bar

p.adder(name);	//update panel
ActionListener action=new ActionListener() {//action listener
Group er=Group.getGroup(Group.numberOfGroups()-1);
@Override
public void actionPerformed(ActionEvent arg0) {
	gpAction(er, addressBook);
}
};
p.lastButtonAction(action);//afzoodane action listener

p.repaint();
}
}
});
//////////////////////////Delete//////////////
p.delete.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent arg0) {
if(p.list.size()==0)
	JOptionPane.showMessageDialog(null, "There is nothing to delete!");
else {
	p.delPanel(3);//taghire panel be halate hazf
	p.del.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			/////////////////amaliat hazf/////////////
			int []indexes=p.delAction(3);
			for(int i=0;i<indexes.length;i++)
				Group.delGroup(indexes[i]);
			Group.writeGroups();//update file groups
		
		}
	});
}
}
});		
}
/**
 * vazife sakhtane panel baraye location ra bar ohde darad
 * @param addressBook
 */
public static void locationPnl(AddressBook addressBook) {
	String arr[]=new String [Location.numberOfLocations()];//araye az name location ha ke baraye sakhte panel estefade mishavad
	
	/////////////por kardane arr[]/////////////
	for(int i=0;i<Location.numberOfLocations();i++)
		arr[i]=Location.getLocation(i).getPointer();
	////////////////////////////////////////////
	
	Draw z=new Draw(arr,3,null);//sakhte panel
	z.setLayout(null);
	JFrame rose=new JFrame();//sakhte frame

rose.add(z);
rose.setVisible(true);
rose.setBounds(50, 50, 500, 500);
z.delete.setText("Delete");
z.newO.setText("New");
z.underIt.setVisible(false);

/////////////////////////////////////////////Actions///////////////////
for(int i=0;i<z.list.size();i++) {
Location er=Location.getLocation(i);
JButton bt=z.list.get(i);
z.list.get(i).addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent arg0) {

locAction(er, addressBook, bt);

}
});
}
///////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////newO action////////////////////////////////////////////////////////////
z.newO.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent arg0) {
	boolean error;//flag baraye rokh dadan ya nadadane exception
	String country=null,town=null,city=null,neighbourHood=null,kooche=null;
	int x=0,y=0;//mokhtasat
	double r=0;//shoa
	try {
 country=JOptionPane.showInputDialog(null, "Please enter the country");//gereftane country
town=JOptionPane.showInputDialog(null, "Please enter the town");//gereftane town
 city=JOptionPane.showInputDialog(null, "Please enter the city");//gereftane city
 neighbourHood=JOptionPane.showInputDialog(null, "Please enter the neighbourhood");//gereftane neighbourhood
 kooche=JOptionPane.showInputDialog(null, "Please enter the ");//gereftane kooche
x=Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter x"));//gereftane x
 y=Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter y"));//gereftane y
 r=Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter radius"));//gereftane radius
 error=false;//amaliat dorost anjam shode
	}
	catch(NullPointerException exception) {
		error=true;//exception rokh dade
	}
	catch(NumberFormatException exception) {
		error=true;//exception rokh dade
	}
	if(error==false) {//excpetion rokh nadade
Location.newLocation(country, town, city, neighbourHood, kooche,x,y,r);//new location
Location.writeLocations();//update file location
z.adder(Location.getLocation(Location.numberOfLocations()-1).getPointer());
Location er=Location.getLocation(Location.numberOfLocations()-1);
JButton bt=z.list.get(z.list.size()-1);

//////////// ezafe kardane actionlistener///////////////
ActionListener action=new ActionListener() {

@Override
public void actionPerformed(ActionEvent arg0) {
	locAction(er, addressBook, bt);
}
};
z.lastButtonAction(action);
/////////////////////////////////////////////////
z.repaint();
rose.repaint();}
}
});
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////Delete action///////////////////////////////////////////////////////////////
z.delete.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(z.list.size()==0)
			JOptionPane.showMessageDialog(null, "There is nothing to delete!");
		else {
		z.delPanel(3);//taghire panel be halate hazf
		z.del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				/////////////hazf/////////////
				int []indexes=z.delAction(3);
				for(int i=0;i<indexes.length;i++)
					Location.delLocation(indexes[i]);
				///////////////////////////////
					
					Location.writeLocations();//update file location
					z.repaint();
			}
		});
		
		}
		
	}
});
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
/**
 * tabe baraye safhe moshakhasate contact ke az JOptionPane estefade mikonad
 * @param t
 * @param addressBook
 * @param bt
 */
public static void contactPnl(Contact t,AddressBook addressBook,JButton bt) {
	ImageIcon icon=null;//icon:baraye tasvir
	/////////////////////check mishavad ke tasviri baray Contact vojjod darad ya na va agar nadarad ba asase jensiat tasviri pishfarz migozarad(agar tasvir barabar name contact bashad tasvir baraye ou ast
	File file=new File("Address books/"+addressBook.nameGetter()+"/"+t.nameGetter()+".png");
	if(file.exists())
		 icon=new ImageIcon("Address books/"+addressBook.nameGetter()+"/"+t.nameGetter()+".png");
	else {
		if(t.sexGetter().equals("man"))
		icon=new ImageIcon("man.png");
		else
			icon=new ImageIcon("woman.png");}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	///sakhte safhe moshakhasat contact////////////
	int bit=JOptionPane.showOptionDialog(null, t.details(), t.nameGetter(), 0, 0, icon, new String[] {"Ok","Add picture","Add to group","Edit","Teammate","Neighours"}, null);
	switch(bit)
	{
	case 0://<OK>
		break;
		case 1://<Add picture> :addresse tasviri ra migirad va ba name contact dar pooshe addressbooke contact zakhre mikonad
		String path=JOptionPane.showInputDialog("Please wrtie address of your picture ;\nYour picture should be 100*100 and png format");
		
		

		while(path.lastIndexOf(".png")==-1) {//formate file mahdood mishavad be png va bayad "path" be .png khatm shavad
			path=JOptionPane.showInputDialog("Invalid format!Please try again");
		}
		
		//////////////////////////////////file byte be byte copy mishavad////////////
		try {
		FileInputStream input=new FileInputStream(path);
		
		
		int tmp=1;
		
		FileOutputStream output=new FileOutputStream("Address books/"+addressBook.nameGetter()+"/"+t.nameGetter()+".png");//file khorooji

		while(tmp!=-1) {
			tmp=input.read();
			output.write((char)tmp);
		}
	
		output.close();
		input.close();
		}
		catch(IOException exception) {
			
		}
		/////////////////////////////////////////////////////////////////////////////////
		break;
		case 2://<Add to group>:be gorooh ezafe mishavad.ebteda list gorooh haye ke contact dar anha ozv nist list mishavad ve sepas ba entekhab be gorooh ezafe mishavad
			String out="";//out:liste gorooh haye ozv nashode be soorate string baraye namayesh dar msgbox 
			ArrayList<Group> gp=new ArrayList<>();//arraye az gorooh haye ozv nashode
			int count=1;//tedad
			/////ckeck shodane gorooh haye ozv nashode/////////
			for(int i=0;i<Group.numberOfGroups();i++) {

				if(!Group.getGroup(i).isThere(t)) {//hey gorooh!aya contact anjast!?
					out+=String.valueOf(count)+"-"+Group.getGroup(i).nameGetter()+"\n";//name gorooh ezafe mishavad
					
					gp.add(Group.getGroup(i));
					count++;}
			}

			String l=JOptionPane.showInputDialog("Please choose\n"+out);
			while(Integer.parseInt(l)<0 || Integer.parseInt(l)>Group.groupList.size()) {//agar adad vared shode beine 0 ta Integer.parseInt(l) yani nabashe karbar...
				l=JOptionPane.showInputDialog("what are you talking about?!Please choose\n"+out);//adad dobare gerefte mishavad
			}
			gp.get(Integer.parseInt(l)-1).addMember(t);//contact be gorooh ezafe mishavad
			addressBook.writeConcats();
Group.writeGroups();//file etelat gorooh baz nevesgte mishavad
break;
		case 3://<Edit>:virayeshe contact
			String name=JOptionPane.showInputDialog(null, "Please enter name", t.nameGetter());//name
			while(name.equals(""))//karbar name ra vared nakarde
			name=JOptionPane.showInputDialog(null, "Number can't be empty!Please enter name", t.nameGetter());
			String number=JOptionPane.showInputDialog(null, "Please enter number", t.numberGetter());
			while(number.equals("") || !number.toLowerCase().equals(number.toUpperCase())) {//karbar number ra vared nakarde ya number shamele horoof ast
				if(!number.toLowerCase().equals(number.toUpperCase()))//aga voroodi shamele horoof bashad
					number=JOptionPane.showInputDialog(null, "It can't be a number!Please enter correct number", t.numberGetter());//gereftane dobare number
				else
				number=JOptionPane.showInputDialog(null, "Number can't be empty!Please enter number", t.numberGetter());//agar number khali bashad
				}
			
			t.nameSetter(name);//add e name
			t.numberSetter(number);//add e number
			t.birthdaySetter(JOptionPane.showInputDialog(null, "Please enter birthday", t.birthdayGetter()));//gereftane birthday
			t.sexSetter(JOptionPane.showInputDialog(null, "Please choose yuor sex", "Sex", 1, null, new String[] {"man","woman"}, "man").toString());//gereftane sex
			t.emailSetter(JOptionPane.showInputDialog(null, "Please enter Email", t.emailGetter()));//gereftane email
			addressBook.writeConcats();//update file conatcts.txt
			Group.writeGroups();//update file groups.txt
			Location.writeLocations();//update file locations.txt
			bt.setText(t.nameGetter());
			break;
		case 4://<teammate>: neshan dadane ham gorooh ha
			JOptionPane.showMessageDialog(null, t.teamMate(), "Teamate", 1, null);
			break;
		case 5://<neighbours>:hamsaye ha
			JOptionPane.showMessageDialog(null, t.findNeighbours(), "neighbours", 1, null);
		
			
	}
	
	
}
/**
 * tabe safhe moshakhasate group ha ke az JOptionePane estefade mikonad
 * @param er
 * @param addressBook
 */
public static void gpAction(Group er,AddressBook addressBook) {
	
	int bit=JOptionPane.showOptionDialog(null, "Members: "+String.valueOf(er.members.size())+" members\n"+er.menu(), er.nameGetter(), 1, 1, null, new String[] {"Add member","Delete member","Edit"}, null);
	if(bit==0)//<Add member>
	{
		ArrayList<Contact> addList=new ArrayList<>();
		for(int i=0;i<addressBook.nubmerOfConcats();i++) {//bedast avardane contact hayi ke dar location ozv nistand
				if(!er.isThere(addressBook.getContact(i))){
					addList.add(addressBook.getContact(i));
				}
			}
			
			JCheckBox[] checks=new JCheckBox[addList.size()];
			Object[] menu=new Object[addList.size()];
			for(int i=0;i<menu.length;i++) {
				checks[i]=new JCheckBox();
				menu[i]=new Object();
				Object[] tr= {addList.get(i).nameGetter(),checks[i]};
				menu[i]=tr;}
			int l=JOptionPane.showConfirmDialog(null, menu, "sd", JOptionPane.CANCEL_OPTION);
			if(l==0) {
			for(int i=0;i<checks.length;i++) {
				if(checks[i].isSelected())
		er.addMember(addList.get(i));//contact be gorooh ezafe mishavad
		Group.writeGroups();//update file groups
			}
	}}
	if(bit==1) {//<Delete member>
		 ArrayList<Contact> delList=new ArrayList<>();
			for(int i=0;i<er.numberOfMembers();i++) {//bedast avardane contact hayi ke dar location ozv hastand
				if(er.isThere(addressBook.getContact(i))){
					delList.add(addressBook.getContact(i));
				}
			}
			JCheckBox[] checks=new JCheckBox[delList.size()];
			Object[] menu=new Object[delList.size()];
			for(int i=0;i<menu.length;i++) {
				checks[i]=new JCheckBox();
				menu[i]=new Object();
				Object[] ty= {delList.get(i).nameGetter(),checks[i]};
				menu[i]=ty;}
			int l=JOptionPane.showConfirmDialog(null, menu, "sd", JOptionPane.CANCEL_OPTION);
			if(l==0) {
			for(int i=0;i<checks.length;i++) {
				if(checks[i].isSelected())
					er.delMember(i);//contact e entekhabi az location hazf mishavad

			}
				Location.writeLocations();//file locaton ha update mishavad
		}
		Group.writeGroups();//update file groups
	}
	if(bit==2) {//<Edit>
		String name=JOptionPane.showInputDialog(null, "Please enter the new name", er.nameGetter());//name jadid gerefte mishavad
		er.nameSetter(name);//name set mishavad
		Group.writeGroups();//update file groups
		addressBook.writeConcats();//update file contacts
	}

}
/**
 * tabe safe moshakhasate location ke az JOptionPane estefade mikonad
 * @param er
 * @param addressBook
 * @param bt
 */
public static void locAction(Location er,AddressBook addressBook,JButton bt) {
	int bit=JOptionPane.showOptionDialog(null,er.toString(), er.getPointer(), 1, 1, null, new String[] {"Add member","Delete member","Edit"}, null);
	 if(bit==0) {
	ArrayList<Contact> addList=new ArrayList<>();
	for(int i=0;i<addressBook.nubmerOfConcats();i++) {//bedast avardane contact hayi ke dar location ozv nistand
			if(!er.isThere(addressBook.getContact(i)) && addressBook.getContact(i).getLocation()==null){
				addList.add(addressBook.getContact(i));
			}
		}
		
		JCheckBox[] checks=new JCheckBox[addList.size()];
		Object[] menu=new Object[addList.size()];
		for(int i=0;i<menu.length;i++) {
			checks[i]=new JCheckBox();
			menu[i]=new Object();
			Object[] tr= {addList.get(i).nameGetter(),checks[i]};
			menu[i]=tr;}
		int l=JOptionPane.showConfirmDialog(null, menu, "sd", JOptionPane.CANCEL_OPTION);
		if(l==0) {
		for(int i=0;i<checks.length;i++) {
			if(checks[i].isSelected())
		er.addMember(addList.get(i));}//contacte entekhab shode be location afzoode mishavad
		Location.writeLocations();//update file location ha
		}	}
	if(bit==1) {//<Delete member>:hazfe contact az location
		 ArrayList<Contact> delList=new ArrayList<>();
		for(int i=0;i<er.numberOfmembers();i++) {//bedast avardane contact hayi ke dar location ozv hastand
			if(er.isThere(addressBook.getContact(i))){
				delList.add(addressBook.getContact(i));
			}
		}
		JCheckBox[] checks=new JCheckBox[delList.size()];//check box ha
		Object[] menu=new Object[delList.size()];//check box va name mavared
		for(int i=0;i<menu.length;i++) {
			checks[i]=new JCheckBox();
			menu[i]=new Object();
			Object[] ty= {delList.get(i).nameGetter(),checks[i]};//object movaghat baraye gozashtane name va check box kenar ham dar yek deraye menu[]
			menu[i]=ty;}
		int l=JOptionPane.showConfirmDialog(null, menu, "sd", JOptionPane.CANCEL_OPTION);
		if(l==0) {
		for(int i=0;i<checks.length;i++) {
			if(checks[i].isSelected())
				er.delMember(delList.get(i));//contact e entekhabi az location hazf mishavad

		}
			Location.writeLocations();//file locaton ha update mishavad
	}
		}
	
	if(bit==2) {//<Edit>:virayeshe location
		String country=JOptionPane.showInputDialog(null, "Please enter the country", er.countryGetter());//country
		String town=JOptionPane.showInputDialog(null, "Please enter the twon", er.townGetter());//twon
		String city=JOptionPane.showInputDialog(null, "Please enter the city", er.cityGetter());//city
		String neighbourHood=JOptionPane.showInputDialog(null, "Please enter the neighbourhood", er.neighbpurhoodGetter());//neighbourhood
		String kooche=JOptionPane.showInputDialog(null, "Please enter the country", er.koocheGetter());//kooche
		int x=Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter x", er.XGetter()));//x
		int y=Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter y", er.YGetter()));//y
		double r=Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter radius", er.radiusGetter()));//radius
		er.countrySetter(country);
		er.townSetter(town);
		er.citySetter(city);
		er.neighbpurhoodSetter(neighbourHood);
		er.koocheSetter(kooche);
		er.XSetter(x);
		er.YSetter(y);
		er.radiusSetter(r);
		addressBook.writeConcats();//update file contacts
		Location.writeLocations();//update file location
		bt.setText(er.getPointer());//update button
	}
		
}
}
