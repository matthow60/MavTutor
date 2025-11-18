package session;
/**
*Extends from the IllegalArgumentException function in order to make a custom exception to be thrown when invalid courses are created
*/
public class InvalidCourseException extends IllegalArgumentException{
/**
*Constructs a new exception message indicating an invalid department
*@param dept	the course department
*/
	public InvalidCourseException(String dept){
		super("Invalid dept in new Course: "+dept);
	}
/**
*Constructs a new exception message indicating an invalid course number
*@param dept	the course department
*@param number	the course number
*/
	public InvalidCourseException(String dept, int number){
		super("Invalid course number in new Course: "+dept+""+number);
	}
} 
