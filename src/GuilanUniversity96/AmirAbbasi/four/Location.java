package GuilanUniversity96.AmirAbbasi.four;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Location {
static ArrayList<Location> location=new ArrayList<>();//liste location ha
	ArrayList<Contact> members=new ArrayList<>();//liste contact haye ozv dar locaton
private String country,town,city,neighbourHood,kooche;
private int x,y;
private double radius;
public Location() {
	
}
/**
 * Constructor 
 * @param country
 * @param town
 * @param city
 * @param neighbourHood
 * @param kooche
 * @param x
 * @param y
 * @param radius
 */
public Location(String country, String town, String city, String neighbourHood, String kooche,int x,int y,double radius) {
	
	this.country = country;
	this.town = town;
	this.city = city;
	this.neighbourHood = neighbourHood;
	this.kooche = kooche;
	this.x=x;
	this.y=y;
	this.radius=radius;
}
/**
 * setter baraye country
 * @param country
 */
public void countrySetter(String country) {
	this.country=country;
}
/**
 * setter baraye town
 * @param town
 */
public void townSetter(String town) {
	this.town=town;
}
/**
 * setter braye city
 * @param city
 */
public void citySetter(String city) {
	this.city=city;
}
/**
 * setter baraye neighbourhood
 * @param neighbourhood
 */
public void neighbpurhoodSetter(String neighbourhood) {
	this.neighbourHood=neighbourhood;
}
/**
 * setter baraye kooche
 * @param kooche
 */
public void koocheSetter(String kooche) {
	this.kooche=kooche;
}
/**
 * setter baraye x
 * @param x
 */
public void XSetter(int x) {
	this.x=x;
}
/**
 * setter baraye Y
 * @param y
 */
public void YSetter(int y) {
	this.y=y;
}
/*
 * setter baraye radius
 */
public void radiusSetter(double radius) {
	this.radius=radius;
}
/**
 * getter baraye country
 * @return
 */
public String countryGetter() {
 return country;
}
/**
 * getter baratown
 * @return
 */
public String townGetter() {
	 return town;
	}
/**
 * getter baraye city
 * @return
 */
public String cityGetter() {
	 return city;
	}
/**
 * getter baraye neighbourhood
 * @return
 */
public String neighbpurhoodGetter() {
	return neighbourHood;
}
/**
 * getter baraye kooche
 * @return
 */
public String koocheGetter() {
	return kooche;
}
/**
 * getter baraye x
 * @return
 */
public int XGetter() {
	return x;
}
/**
 * getter baraye y
 * @return
 */
public int YGetter() {
	return y;
}
/**
 * getter baraye radius
 * @return
 */
public double radiusGetter() {
	return radius;
}
/**
 * location jadid
 * @param country
 * @param town
 * @param city
 * @param neighbourHood
 * @param kooche
 * @param x
 * @param y
 * @param r
 */
public static  void newLocation(String country, String town, String city, String neighbourHood, String kooche,int x,int y,double r) {
	location.add(new Location(country, town, city, neighbourHood, kooche,x,y,r));
}
public String toString() {
	String mem="";
	for(int i=0;i<members.size();i++)
		mem+=String.valueOf(i+1)+"-"+members.get(i).nameGetter()+"\n";
	return this.country+","+this.town+","+this.city+","+this.neighbourHood+","+this.kooche+" ("+String.valueOf(this.x)+","+String.valueOf(this.y)+")\n"+mem;
}
/**
 * hafze location
 * @param i
 */
public static  void delLocation(int i) {
	for(int j=0;j<location.get(i).numberOfmembers();j++)
		location.get(i).members.get(j).delMyLocation();
	location.remove(i);
	
}
/**
 * liste contact hara return mikonad
 * @return
 */
public String toW() {
	String mem="";
	for(int i=0;i<members.size();i++)
		mem+=members.get(i).nameGetter()+":";
	return this.country+":"+this.town+":"+this.city+":"+this.neighbourHood+":"+this.kooche+":"+String.valueOf(this.x)+":"+String.valueOf(this.y)+":"+String.valueOf(this.radius)+":"+mem;
}
public static Location getLocation(int i) {
	return location.get(i);
}
/**
 * tedade location hara barmigardanad
 * @return
 */
public static  int numberOfLocations() {
	return location.size();
}
/**
 * mokhtasate location ke be onvan meiare shnakht be kar miravad ra bar migardanad
 * @return
 */
public String getPointer() {
	return "("+String.valueOf(this.x)+","+String.valueOf(this.y)+")";
}
/**
 * update file locations.txt
 * @param address : address ast
 */
public static  void writeLocations() {
	try {
		////////////////neveshtane file kolie location ha//////////////////////////////////
		FileWriter wr=new FileWriter("locations.txt");
 		for(int j=0;j<location.size();j++) {
 			wr.write(location.get(j).countryGetter()+":"+location.get(j).town+":"+location.get(j).cityGetter()+":"+location.get(j).neighbourHood+":"+location.get(j).koocheGetter()+":"+String.valueOf(location.get(j).XGetter()+":"+String.valueOf(location.get(j).YGetter()+":"+String.valueOf(location.get(j).radiusGetter())))+"#");}
 		wr.close();
 		///////////////////////////////////////////////////////////////////////////////////
 		
 		//////////////////////////neveshtane file ozviat//////////////////////////////
 		for(int j=0;j<PhoneBook.addressBook.size();j++) {
 	FileWriter writer=new FileWriter("Address books/"+PhoneBook.addressBook.get(j).nameGetter()+"/locations.txt");
 	if(location.size()!=0) {
 	for(int i=0;i<location.size();i++) {
 		writer.write(location.get(i).getPointer()+":");
 		for(int t=0;t<location.get(i).numberOfmembers();t++) {
 		
 			if(PhoneBook.addressBook.get(j).list.indexOf(location.get(i).members.get(t))!=-1)//agar contact dar nazar gerefte shode dar addressbook e dar nazar gerefte shode bashad
 		writer.write(PhoneBook.addressBook.get(j).list.indexOf(location.get(i).members.get(t))+":");}
 		writer.write("#");}
 	writer.close();}
		else {
		wr.write("");
			writer.close();}
	}///////////////////////////////////////////////////////////////////////
 		}
	catch(IOException exception) {
		
	}
}
/**
 * afzoodane contact be location
 * @param contact
 */
public void addMember(Contact contact) {
	contact.setMyLoaction(this);
	members.add(contact);
}
/**
 * hazfe contact az location
 * @param contact
 */
public void delMember(Contact contact) {
	contact.delMyLocation();
	members.remove(contact);
}
/**
 * tedade contact haye location
 * @return
 */
public int numberOfmembers() {
	return members.size();
}
/**
 * check mikonad contact dar location ozv hast ya na
 * @param contact
 * @return
 */
public boolean isThere(Contact contact) {
	boolean flag=false;
	for(int i=0;i<members.size();i++) {
		if(members.get(i)==contact) {
			flag=true;
			break;
		}
	}
	return flag;
}
/**
 * peyda kardane contact haye hamsaye
 * agar hamsaye peyda nashavad "There isn't any neighbour" ra return mioknad
 * @param contact
 * @return
 */
public  String findNeighbours(Contact contact) {
	String ans="";//javab
	int count=1;//tedad
	for(int i=0;i<location.size();i++) {
		//agar fasele do noghte az jame do shoa kamtar bashad hamsaye and(be dayere nesbat dade shode)
		if(Math.sqrt(Math.pow((location.get(i).YGetter()-contact.getLocation().XGetter()),2)+Math.pow((location.get(i).XGetter()-contact.getLocation().YGetter()),2))<location.get(i).radiusGetter()+contact.getLocation().radiusGetter()) {
			for(int j=0;j<location.get(i).numberOfmembers();j++) {
				if(location.get(i).members.get(j)!=contact) {//dalile if:chon khode contact ra baraye khodash hamsaye hesab nakonad
					ans+=String.valueOf(count)+"-"+location.get(i).members.get(j).nameGetter()+"\n";
					count++;}
			}
		}
	}
	if(ans.equals(""))
		ans="There isn't any neighbour";
	return ans;
}
}
