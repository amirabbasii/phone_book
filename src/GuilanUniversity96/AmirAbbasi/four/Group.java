package GuilanUniversity96.AmirAbbasi.four;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Group {
	 private  String name;//name group
	static private String  address="Address books/b/groups.txt";//adresse file
	 		 static ArrayList<Group> groupList=new ArrayList();//liste group ha
	 		ArrayList<Contact> members=new ArrayList<>();//Contact haye ozv dar gorooh
	 		private int numberOfContacts;//tedade Contact haye ozve gorooh
	 		
	 		/**
	 		 * Constructor baraye group be soorate koli(shamele group ha ast) 
	 		 * @param address :addresse file group.txt
	 		 */
	 		 public Group(String address) {
	 			 this.address=address;
	 		
	 		
	 		 }
	 	/**
	 	 * goroohe jadid
	 	 * @param name name group
	 	 */
	 public static void newGroup(String name) {
	 	groupList.add(new Group(name,false));
		
	 }
	 /**
	  * 
	  * @return tedade gorooh ha
	  */
	 public static  int numberOfGroups() {
	 	return groupList.size();
	 }
	 /**
	  * hazfe gorooh
	  * @param index 
	  */
	 public static  void delGroup(int i) {
	 	for(int z=0;z<groupList.get(i).numberOfMembers();z++)
	 	{
	 		groupList.get(i).getMember(z).leave(groupList.get(i));//gorooh az liste grouh haye ozvie contact haye ozv gorooh hazf mishavad 
	 	}
	 	groupList.remove(i);//gorooh hafz mishavad
	 }
	 /**
	  * file negahdari etelaate gorooh hara minevisad
	  */
	 public static  void writeGroups() {
		
	 	try {
	 		///////////////////file etelate koli az group ha ha neveshte mishavad////////
	 		FileWriter wr=new FileWriter("groups.txt");
	 		for(int j=0;j<groupList.size();j++)
	 			wr.write(groupList.get(j).nameGetter()+"#");
	 		wr.close();
	 		//////////////////////////////////////////////////////////////////////
	 		
	 		////////////////////////////file etelate ozviat///////////////////
	 		for(int j=0;j<PhoneBook.addressBook.size();j++) {
	 	FileWriter writer=new FileWriter("Address books/"+PhoneBook.addressBook.get(j).nameGetter()+"/groups.txt");
	 	if(groupList.size()!=0) {
	 	for(int i=0;i<groupList.size();i++) {
	 		writer.write(groupList.get(i).nameGetter()+":");
	 		for(int t=0;t<groupList.get(i).numberOfMembers();t++) {
	 		
	 			if(PhoneBook.addressBook.get(j).list.indexOf(groupList.get(i).getMember(t))!=-1)//agar contact dar nazar gerefte shode dar addressbook e mored nazar bashad
	 		writer.write(PhoneBook.addressBook.get(j).list.indexOf(groupList.get(i).getMember(t))+":");}
	 		writer.write("#");}
	 
	 	}
	 	else
	 	{//file khali neveshte mishavad
	 		writer.write("");
	 	}
	 	writer.close();}
	 		////////////////////////////////////////////////////////////////////
	 	}
	 	catch(IOException exceptin) {
	 		exceptin.printStackTrace();
	 	}
	 }
	 ////////////////////////////////

	 public Group(String name,boolean t) {
	 this.name=name;
	 	 }
	 /**
	  * 
	  * @param i
	  * @return
	  */
	 public static Group getGroup(int i) {
	 	return groupList.get(i);
	 }
/**
 * hazfe contact
 * @param i
 */
	 public void delMember(int i) {
	 	members.get(i).list.remove(this);
	 	members.remove(i);
	 }
	 /**
	  * Setter baraye name gorooh
	  * @param name
	  */
	 public void nameSetter(String name) {
	 	this.name=name;
	 }
	 /**
	  * name gorooh ra return miknoad
	  * @return
	  */
	 public String nameGetter() {
	 	return this.name;
	 }
	 /**
	  * afzoodane contact be gorooh
	  * @param contact
	  */
	 public void addMember(Contact contact) {
		 contact.addToGroup(this);
	 	members.add(contact);
	 }
	 /**
	  * liste contact ha ra return mikonad
	  * @return
	  */
	 public String menu() {
	 	String out="";
	 	for(int i=0;i<members.size();i++) 
	 		out+=String.valueOf(i+1)+"-"+members.get(i).nameGetter()+"\n";
	 	return out;
	 }
	 /**
	  * override toString
	  */
	 public String toString() {
	 	String out="";
	 	for(int i=0;i<members.size();i++) 
	 		out+=members.get(i).nameGetter()+":";
	 	return out;
	 }
	 /**
	  * tedade contact ha ra bar migardanad
	  * @return
	  */
	 public int numberOfMembers() {
	 	return members.size();
	 }
	 /**
	  * Contact ra ba migardanad 
	  * @param i
	  * @return
	  */
	 public Contact getMember(int i) {
	 	return members.get(i);
	 }
	 /**
	  * check mioknad contact dar gorooh vojood darad ya na(felani injast? ;) )
	  * flag dar ebteda fasle ast va engar peyda nashode.vaghti peyda shode true mishavad
	  * @param contact
	  * @return
	  */
	 public boolean isThere(Contact contact) {
	 	boolean flag=false;
	 	for(int i=0;i<members.size();i++) {
	 		if(getMember(i)==contact) {
	 			flag=true;
	 			break;
	 		}
	 	}
	 	return flag;
	 }
}
