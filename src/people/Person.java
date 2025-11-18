package people;
import rating.Rating;
import rating.Rateable;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintStream;
public class Person implements Rateable{
 protected String name;
 protected String email;
 private ArrayList<Rating> ratings;
  public Person(String name, String email){
  ratings=new ArrayList<>();
  if(name==null||name.isEmpty()||email==null||email.isEmpty()){
   throw new IllegalArgumentException("One or more arguments was empty, please try again!");
  }
   this.name=name;
   this.email=email;
  }
  public Person(Scanner in){
   this.name=in.nextLine();
   this.email=in.nextLine();
  }
  public void save(PrintStream out){
   out.println(name);
   out.println(email);
  }
 public String getName(){
  return name;
 }
 @Override
 public boolean equals(Object o){
  if(o==this) return true;
  if(o==null||o.getClass()!=getClass()) return false;
  Person that = (Person) o;
  return this.name.equals(that.name) && this.email.equals(that.email);
 }
 @Override
 public int hashCode(){
  return Objects.hash(name, email);
 }
 @Override
 public String toString(){
  return (name+" ("+email+")");
 }
 @Override
 public void addRating(Rating rating){
  ratings.add(rating);
 }
 @Override
 public double getAverageRating(){
  double sum=0;
  for(Rating r : ratings){
   sum+=r.getStars();
  }
  return sum/ratings.size();
 }
 @Override
 public Rating[] getRatings(){
  return ratings.toArray(new Rating[ratings.size()]);
 }
}
