package GuilanUniversity96.AmirAbbasi.four;

import java.util.ArrayList;
import java.util.BitSet;

public class Contact {
	private String name="unasigned",number="unasigned",birthday="unasigned",sex="unasigned",group="",email="unasigned",location="unasigned";
	private Location mylocation=null;//location
	 ArrayList<Group> list=new  ArrayList<>();//liste gorooh haye ozv shode
	 /**
	  * Contructor ba name va number
	  * @param name
	  * @param number
	  */
	public Contact(String name,String number) {
		this.name=name;
		this.number=number;
	}
	/**
	 * hazf az gorooh
	 * @param group
	 */
	public void leave(Group group) {
		group.members.remove(this);
		list.remove(group);
	}
	/**
	 * Constructor ba name
	 * @param name
	 */
	public Contact(String name) {
		this.name=name;
	}
	/**
	 * Constructor ba name,number va birthday
	 * @param name
	 * @param number
	 * @param birthday
	 */
	public Contact(String name,String number,String birthday) {
		this.name=name;
		this.number=number;
		this.birthday=birthday;
	}
	/**
	 * Constructor ba name,number,birthday va sex
	 * @param name
	 * @param number
	 * @param birthday
	 * @param sex
	 */
	public Contact(String name,String number,String birthday,String sex) {
		this.name=name;
		this.number=number;
		this.birthday=birthday;
		this.sex=sex;
	}
	/**
	 * Constructor ba name,number,birthday,sex va group
	 * @param name
	 * @param number
	 * @param birthday
	 * @param sex
	 * @param group
	 */
	public Contact(String name,String number,String birthday,String sex,String group) {
		this.name=name;
		this.number=number;
		this.birthday=birthday;
		this.sex=sex;
		this.group=group;
	}
	/**
	 * Constructor ba name,number,birthday,sex,group va email
	 * @param name
	 * @param number
	 * @param birthday
	 * @param sex
	 * @param group
	 * @param email
	 */
	public Contact(String name,String number,String birthday,String sex,String group,String email) {
		this.name=name;
		this.number=number;
		this.birthday=birthday;
		this.sex=sex;
		this.group=group;
		this.email=email;
	}
	/**
	 * getter baraye name
	 * @return
	 */
	public  String nameGetter() {
		return this.name;
	}
	/**
	 * setter baraye name
	 * @param name
	 */
	public void nameSetter(String name) {
		this.name=name;
	}
	/**
	 * getter baraye number
	 * @return
	 */
	public  String numberGetter() {
		return number;
	}
	/**
	 * getter baraye number
	 * @param number
	 */
	public  void numberSetter(String number) {
		this.number=number;
	}
	/**
	 * getter baraye birthday
	 * @return
	 */
	public  String birthdayGetter() {
		return birthday;
	}
	/**
	 * setter baraye birthday
	 * @return
	 */
	public  void birthdaySetter(String birthday) {
		if(birthday.equals(""))
			this.birthday="unasigned";
		else
		this.birthday=birthday;
	}
	/**
	 * getter baraye sex
	 * @return
	 */
	public  String sexGetter() {
		return this.sex;
	}
	/**
	 * setter baraye sex
	 * @return
	 */
	public  void sexSetter(String sex) {
		if(sex.equals(""))
			this.sex="unasigned";
		else
		this.sex=sex;
	}
	/**
	 * getter gorooh haye ozv shode
	 * agar dar goroohi ozv nabashad "unasigned" return mishavad
	 * vagarna name gorooh ha ba fasele return mishavad
	 * @return
	 */
	public  String groupGetter() {
		String ans="";
		if(list.size()==0)
			ans="unasigned";
		else {
		for(int i=0;i<list.size();i++)
			ans+=list.get(i).nameGetter()+",";}
		return ans;
	}
	/**
	 * getter baraye email
	 * @return
	 */
	public  String emailGetter() {
		return this.email;
	}
	/**
	 * setter baraye email
	 * @param email
	 */
	public  void emailSetter(String email) {
		if(email.equals(""))
			this.email="unasigned";
		else
	this.email=email;
	}
	/**
	 * override toString
	 * moshakhasat ra return mikonad
	 * beyne moshakhasat ":" gharar migirad baraye tafkik
	 * entehaye anha "#" gharar migirad bararye payane in contact baraye tafkik dar class haye digar
	 */
	public String toString() {
		String ans=nameGetter()+":";
			ans+=numberGetter()+":";
			if(birthdayGetter().equals("unasigned"))
				ans+=":";
			else
			ans+=birthdayGetter()+":";
			if(sexGetter().equals("unasigned"))
				ans+=":";
			else
			ans+=sexGetter()+":";
			if(groupGetter().equals("unasigned"))
				ans+=":";
			else
			ans+=groupGetter()+":";
			if(emailGetter().equals("unasigned"))
				ans+=":";
			else
			ans+=emailGetter()+":";
		ans+="#";
		return ans;
	}
	/**
	 * ozviat dar gorooh
	 * gorooh be list afzoode mishavad
	 * @param group
	 */
	public void addToGroup(Group group) {
	list.add(group);
	
	}
	/**
	 * return moshakasat ba adad gozari
	 * @return
	 */
	public String details() {
		return "name : "+nameGetter()+"\nPhone number : "+numberGetter()+"\nBirthday : "+birthdayGetter()+"\nSex : "+sexGetter()+"\nGroup : "+groupGetter()+"\nLocation : "+showMyLocation()+"\nE-mail : "+emailGetter();
	}
	/**
	 * check karadane ozviat dar gorooh
	 * @param group
	 * @return
	 */
	public boolean IsThere(Group group) {
		boolean flag=false;
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i)==group) {
				flag=true;
				break;
			}
		}
		return flag;
	}
	/**
	 * hamgoroohi ha return mishavad
	 * be soorat adad gozari shode
	 * @return
	 */
	public String teamMate() {
		String out="";//khorooji
	int count=1;//tedad
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<list.get(i).numberOfMembers();j++) {
			if(list.get(i).getMember(j)!=this) {
				out+=String.valueOf(count)+"-"+list.get(i).getMember(j).nameGetter()+"\n";
			count++;}}
			
		}
		if(out.equals(""))//agar ham gorooh nadashte bashad
			out="There insn't any teammate!";
		return out;
	}
	/**
	 * hazfe location
	 */
	public void delMyLocation() {
		mylocation.members.remove(this);
		mylocation=null;
	}
	/**
	 * setter baraye location
	 * @param location
	 */
	public void setMyLoaction(Location location) {
		mylocation=location;
	}
	/**
	 * getter baraye location
	 * mokhtasat return mishavad
	 * @return
	 */
	public String showMyLocation() {
		if(mylocation!=null)
		return mylocation.getPointer();
		else
			return "No location";
	}
	/**
	 * hamsaye hara return mikonad
	 * @return
	 */
	public String findNeighbours() {
		if(mylocation!=null)
			return mylocation.findNeighbours(this);
		else
			return "There isn't any location for this contact";
	}
	public Location getLocation() {
		return mylocation;
	}
}
