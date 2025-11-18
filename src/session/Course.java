package session;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import mdi.MavTutor;
/**
*Represents the course used for the respective tutoring session 
*/
public class Course{
	private String dept;
	private int number;
/**
*Constructs the course and uses data validation to make sure the course department and course number are valid
*@param dept	the course department
*@param number	the course number
*/
	public Course(String dept, int number){
		if(dept.length()>4||dept.length()<3) throw new InvalidCourseException(dept);
		if(number<1000||number>9999) throw new InvalidCourseException(dept, number);//might change
		this.dept=dept;
		this.number=number;
	}
/**
*Constructs the course from a saved file
*@param in	the varaible used for the scanner
*/
	public Course(Scanner in){
	 this.dept=in.nextLine();
	 this.number=in.nextInt(); in.nextLine();
	}
/**
*This method saves the fields into a named file from the mdi.MavTutor class
*@param out		the variable used for the PrintStream
*/
	public void save(PrintStream out){
	 out.println(dept);
	 out.println(number);
	}
/**
*Formats the course into a string
*@return the course
*/
	@Override
	public String toString(){
		return (dept+number);
	}
/**
*Checks to see if both dept and number are equal within a course
*@param o	the object used for checking equality
*@return {@code true} if the object is a course with the same dept and number; {@code false} if otherwise
*/
	@Override
	public boolean equals(Object o){
		if(o==this) return true;
		if(o==null||o.getClass()!=getClass()) return false;
		Course that = (Course) o;
		return (this.dept.equals(that.dept) && this.number==that.number);
	}
/**
*Checks the hash code value for the course based on the dept and number
*@return the hash code value for the course
*/
	@Override
	public int hashCode(){
		return Objects.hash(dept, number);
	}
	
} 
