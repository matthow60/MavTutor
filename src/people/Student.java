package people;
import session.Course;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintStream;
public class Student extends Person{
	private static int nextStudentID=0;
	private int studentID;
	private ArrayList<Course> courses;
	public Student(String name, String email){
	super(name, email);
	this.studentID=nextStudentID;
	this.nextStudentID++;
	this.courses = new ArrayList<>();
	}
	public Student(Scanner in){
	 super(in);
	 this.studentID=in.nextInt(); in.nextLine();
	 this.nextStudentID=in.nextInt(); in.nextLine();
	 int size = in.nextInt(); in.nextLine();
	 this.courses=new ArrayList<>();
	 while(size-- > 0){
	  courses.add(new Course(in));
	 }
	}
	public void save(PrintStream out){
	 super.save(out);
	 out.println(studentID);
	 out.println(nextStudentID);
	 out.println(courses.size());
	 for(Course c : courses){
	  c.save(out);
	 }
	}
	public void addCourse(Course course){
		courses.add(course);
	}
	public Course[] getCourses(){
		return courses.toArray(new Course[0]);
	}
	@Override
	public String toString(){
	String s = super.toString();
	return s.replace(")", ", #"+studentID+")");
	}
} 
