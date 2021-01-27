package GuilanUniversity96.AmirAbbasi.four;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AddressBook {
	private String name;
//	protected Group group;
//	Location location = new Location();// bargozari location
	ArrayList<Contact> list = new ArrayList();// liste contact ha

	/**
	 * afzoodane contact
	 * 
	 * @param concat
	 */
	public void addConcat(Contact concat) {
		list.add(concat);
	}

	/**
	 * location jadid
	 * 
	 * @param country
	 * @param town
	 * @param city
	 * @param neighbourHood
	 * @param kooche
	 * @param x
	 * @param y
	 * @param r
	 */
//	public void newLocation(String country, String town, String city, String neighbourHood, String kooche, int x, int y,
//			double r) {
//		location.location.add(new Location(country, town, city, neighbourHood, kooche, x, y, r));// location be list
//																									// ezafe mishavad
//
//	}

	/**
	 * Contructor AddressBook
	 * 
	 * @param name
	 */
	public AddressBook(String name) {
		this.name = name;
	}

	/**
	 * Setter baraye name
	 * 
	 * @return
	 */
	public String nameGetter() {
		return name;
	}

	/**
	 * hazfe contact az list
	 * 
	 * @param i
	 */
	public void delete(int i) {
		for(int j=0;j<list.get(i).list.size();j++)
		{
			list.get(i).leave(list.get(i).list.get(j));
		}
		list.get(i).delMyLocation();
		Group.writeGroups();
		Location.writeLocations();
		list.remove(i);
	}

	/**
	 * Contact ra return mikonad
	 * 
	 * @param index
	 * @return
	 */
	public Contact getContact(int i) {
		return list.get(i);
	}

	/**
	 * tedade contact ha
	 * 
	 * @return
	 */
	public int nubmerOfConcats() {
		return list.size();
	}

	/**
	 * Update file contact haye adressBook(update contacts.txt)
	 */
	public void writeConcats() {
		FileWriter writer;

		try {
			writer = new FileWriter("Address books/" + this.name + "/concats.txt");// file contact ha

			//// update
			if (list.size() == 0)
				writer.write("");
			else {
			for (int i = 0; i <= list.size() - 2; i++) {
				writer.write(list.get(i).toString());
			}
			writer.write(list.get(list.size() - 1).toString().substring(0,list.get(list.size() - 1).toString().length() - 1));}
			writer.close();
			//////////////////////
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(null, "A problem occured when making cocntacts", "Error", 0, null);
		}

	}

	/**
	 * neveshtane file haye addressBook(contact.txt.groups.txt,locations.txt) tanha
	 * vaghti az on estefade mihavad ke addressBokk jadid ijad shavad addressBokk
	 * folder ast
	 */
	public void writeAddressBook() {

		File file = new File("Address books/" + name);// foldere addressBook
		file.mkdirs();// sakhte an

		File file1 = new File("Address books/" + name + "/concats.txt");
		File file2 = new File("Address books/" + name + "/groups.txt");
		File file3 = new File("Address books/" + name + "/locations.txt");
		try {
			file1.createNewFile();
			file2.createNewFile();
			file3.createNewFile();
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(null, "An error when creating files.Maybe you should run the program as Adminstrator/Root.So maybe the program doesn't work correctly.Press Ok to close the program","Couldn't make file", 0, null);
System.exit(0);
		}

	}


	/**
	 * override toString contact ha be soorate liste zire ham return mishavad
	 */
	public String toString() {
		String out = "";
		for (int i = 0; i < list.size(); i++)
			out += String.valueOf(i) + "-" + list.get(i).nameGetter() + "\n";
		return out;
	}

	/**
	 * bargozari addressBook(contact ha va location ha va gorooh ha bargozari
	 * mishavad) tanha zamani estefade mishavad ke barname baz shavad va addressBook
	 * haye zakhire shode bargozari shavand
	 *
	 */
	public void loadAddressBook() {

		int tmp = 1;
		String temp = "";
///////////////////////////////khandae contact ha//////////////////////////////////////////
		try {

			FileReader read = new FileReader("Address books/" + name + "/concats.txt");
			while (tmp != -1) {
				tmp = read.read();
				temp += (char) tmp;
			}
			read.close();
		} catch (IOException exception) {
			File file = new File("Address books/" + name + "/contacts.txt");//contacts sakhte mishavad
			JOptionPane.showMessageDialog(null, "contacts.txt not found.press OK to create it", "contacts.txt", 0, null);

			try {
				file.createNewFile();//new 
				temp = "?";
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "An error when creating file.Maybe you should run the program as Adminstrator/Root.So maybe the program doesn't work correctly.Press Ok to close the program","Couldn't make file", 0, null);
				System.exit(0);
			}
		}

		temp = temp.substring(0, temp.length() - 1);

		String[] strConcats = temp.split("#");//contact ha be soorate String[]

		int i;
		if (temp.length() != 0) {
			for (i = 0; i < strConcats.length; i++) {

				String[] tmp2;//moshakhaste contact dar nazar gerefteshode be soorate String[]
				tmp2 = strConcats[i].split(":");
				
				switch (tmp2.length) {
				case 1:
			
					this.addConcat(new Contact(tmp2[0]));
					break;
				case 2:
					
					this.addConcat(new Contact(tmp2[0], tmp2[1]));
					break;
				case 3:
					
					this.addConcat(new Contact(tmp2[0], tmp2[1], tmp2[2]));
					break;
				case 4:
					
					this.addConcat(new Contact(tmp2[0], tmp2[1], tmp2[2], tmp2[3]));
					break;
				case 5:

					this.addConcat(new Contact(tmp2[0], tmp2[1], tmp2[2], tmp2[3], tmp2[4]));
					break;
				case 6:
				
					this.addConcat(new Contact(tmp2[0], tmp2[1], tmp2[2], tmp2[3], tmp2[4], tmp2[5]));
					break;

			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		}
///////////////////////////////////////////////////////khandane group ha/////////////////////////////////
try {
FileReader reader = new FileReader("Address books/"+name+"/groups.txt");

tmp = 1;
temp = "";
while (tmp != -1) {
tmp = reader.read();
temp += (char) tmp;
}
reader.close();
} catch (IOException exception) {
File file = new File("Address books/"+name+"/groups.txt");//sakhte  file
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
String[] con = group[i].split(":");//moshakhaste group dar nazar gerefte shode
if (con.length > 1) {//bargozari contacthaye ozv
for (int j = 1; j < con.length; j++) {
Group.groupList.get(i).addMember(list.get(Integer.parseInt(con[j])));//contact dar gorooh bargozari mishavad
}
}
}

}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////bargozri location ha///////////////////////////////////////////////////
tmp = 1;
temp = "";
try {
FileReader reader = new FileReader("Address books/" + name + "/locations.txt");

while (tmp != -1) {
tmp = reader.read();
temp += (char) tmp;

}
reader.close();
} catch (IOException exception) {
File file = new File("Address books/" + name + "/locations.txt");//sakhte file
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
String[] locations = temp.split("#");//location ha be soorate String[]
for (i = 0; i < locations.length; i++) {
String[] rt = locations[i].split(":");//moshakhasae location dar nazar gerefte shode be soorate String[]
for (int j = 1; j < rt.length; j++)
Location.getLocation(i).addMember(list.get(Integer.parseInt(rt[j])));//bargozari contact ha dar location
}
}
/////////////////////////////////////////////////////////////////////////////
		
	}
}
