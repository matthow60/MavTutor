package session;
import people.Person;
import people.Tutor;
import people.Student;
import rating.Rating;
import rating.Rateable;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintStream;
/**
*Creates a tutoring session at a given date range with tutors and a list of students
*/
public class Session implements Rateable{
 private Course course;
 private DateRange dates;
 private Tutor tutor;
 private List<Student> students;
 private List<Rating> ratings;
/**
*Constructs a session for a given course and tutor and initializes the list of students, ratings, and field of dates
*@param course	the course being tutored
*@param tutor	the tutor helping with the course
*/
 public Session(Course course, Tutor tutor){
 this.students=new ArrayList<>();
 this.course=course;
 this.tutor=tutor;
 this.dates=null;
 this.ratings=new ArrayList<>();
 }
 public Session(Scanner in){
  int size=in.nextInt(); in.nextLine();
  this.students = new ArrayList<>();
  while(size-- > 0){
   students.add(new Student(in));
  }
  this.course = new Course(in);
  this.tutor = new Tutor(in);
  this.dates = new DateRange(in);
 
 }
 public void save(PrintStream out){
  out.println(students.size());
  for(Student s : students){
   s.save(out);
  }
  course.save(out);
  tutor.save(out);
  dates.save(out);
 }
 /**
 *Sets the schedule for the tutoring session by instancing a date range object
 *@param date	the date the session takes place in YYYYMMDD
 *@param startTime	the time the session begins in HH:MM
 *@param duration	the time of the session in minutes
 */
 public void setSchedule(String date, String startTime, long duration){
  dates=new DateRange(date, startTime, duration);
 }
 /**
 *Adds a student into the list of students
 *@param student	the student to be added
 */
 public void addStudent(Student student){
  students.add(student);
 }
 /**
 *Uses StringBuilder to output a string that represents the sessions course, date range, tutor, and students involved
 *@return a formatted string of session information
 */
 @Override
 public String toString(){
  StringBuilder s = new StringBuilder();
  s.append("Session on ").append(course).append(" at ").append(dates).append("\n").append("Tutor: ").append(tutor).append("\n").append("Students: ");
  for(Student stu : students){
  s.append(stu).append(", ");
  }
  return s.toString();
 }
 @Override
 public void addRating(Rating rating){
  ratings.add(rating);
 }
 @Override
 public double getAverageRating(){
  if(ratings==null||ratings.isEmpty()){
   return Double.NaN;
  }
  double average = 0;
  for(var r : ratings){
   average+=r.getStars();
  }
  average=average/ratings.size();
  return average;
 }
 @Override
 public Rating[] getRatings(){
  return ratings.toArray(new Rating[ratings.size()]);
 }
} 
