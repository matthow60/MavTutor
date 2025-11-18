package mdi;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import people.Person;
import people.Student;
import people.Tutor;

import session.Course;
import session.InvalidCourseException;
import session.Session;

import rating.Rateable;
import rating.Comment;
import rating.Rating;

import menu.Menu;
import menu.MenuItem;

public class MavTutor{
private Menu menu;
private List view;
private File file;

private List<Course> courses = new ArrayList<>();
private List<Student> students = new ArrayList<>();
private List<Tutor> tutors = new ArrayList<>();
private List<Session> sessions = new ArrayList<>();

public MavTutor(){
 this.menu = new Menu();
 this.view = new ArrayList<>();
 this.file = null;
 menu.addMenuItem(new MenuItem("Exit", ()->quit()));
 menu.addMenuItem(new MenuItem("New Course", ()->newCourse()));
 menu.addMenuItem(new MenuItem("View Courses", ()->selectView(courses)));
 menu.addMenuItem(new MenuItem("New Student", ()->newStudent()));
 menu.addMenuItem(new MenuItem("View Students", ()->selectView(students)));
 menu.addMenuItem(new MenuItem("Rate Students", ()->review(students)));
 menu.addMenuItem(new MenuItem("New Tutor", ()->newTutor()));
 menu.addMenuItem(new MenuItem("View Tutors", ()->selectView(tutors)));
 menu.addMenuItem(new MenuItem("Rate Tutors", ()->review(tutors)));
 menu.addMenuItem(new MenuItem("New Session", ()->newSession()));
 menu.addMenuItem(new MenuItem("View Sessions", ()->selectView(sessions)));
 menu.addMenuItem(new MenuItem("Rate Sessions", ()->review(sessions)));
 menu.addMenuItem(new MenuItem("Clear Data", ()->newz()));
 menu.addMenuItem(new MenuItem("Save As...", ()->saveAs()));
 menu.addMenuItem(new MenuItem("Save", ()->save()));
 menu.addMenuItem(new MenuItem("Load Data", ()->open()));
 
 //title stuff
 final String clear = "\n".repeat(80);
 final String title = "\n========\nMavTutor\n========\n";
 Scanner scanner = new Scanner(System.in);
 while(true){
  System.out.println(clear+title+"\n"+menu+"Enter a selection: ");
  menu.result.setLength(0);
  int choice;
  try{
  choice = scanner.nextInt();
  menu.run(choice);
  }catch(Exception e){
  menu.result.append("Invalid selection!");
  e.printStackTrace();
  scanner.nextLine();
  }
 }
}
public static void main(String[] args){
 new MavTutor();
}
@Override
public String toString(){
 if(view==null||view.isEmpty()){
 return "No data to display\n";
 }
 String title="";
 if(view==courses){
 title="Courses\n";
 }else if(view==students){
 title="Students\n";
 }else if(view==tutors){
 title="Tutors\n";
 }else if(view==sessions){
 title="Sessions\n";
 }
 return title;
}
private void quit(){
 System.exit(0);
}
private void selectView(List list){
 view=list;
 menu.result.append(menu.listToString(toString(), list, '-'));
}
private void newCourse(){
 String dept = menu.getString("Enter the department code: ");
 int number = menu.getInt("Enter the course number: ");
 try{
  Course c = new Course(dept, number);
  if(courses.indexOf(c)==-1){
   courses.add(c);
   menu.result.append("Course added successfully!");
  }else{
   menu.result.append("Course already exists!");
  }
 }catch(InvalidCourseException e){
  menu.result.append("Invalid course: "+e.getMessage()+"\n");
 }
}
private void newTutor(){
 if(courses.isEmpty()){
  menu.result.append("No courses defined, please create a course first!\n");
  return;
 }
 String name = menu.getString("Enter the tutor name: ");
 String email = menu.getString("Enter the tutor email: ");
 int ssn = menu.getInt("Enter the tutor ssn: ");
 String bio = menu.getString("Enter the tutor bio: ");
 int index = menu.selectItemFromList("Select the index of the course to tutor: ", courses);
 if(index<0||index>=courses.size()){
  menu.result.append("Invalid selection...returning to main menu...");
  return;
 }
 Course course = courses.get(index);
 Tutor tutor = new Tutor(name, email, ssn, bio, course);
 tutors.add(tutor);
 menu.result.append("Tutor added successfully!");
}
private void newStudent(){
 if(courses.isEmpty()){
  menu.result.append("No courses defined, please create a course first!\n");
  return;
 }
 String name = menu.getString("Enter student name: ");
 String email = menu.getString("Enter student email: ");
 Student student = new Student(name, email);
 int index;
 while(true){
 index = menu.selectItemFromList("Select the courses you need tutoring in (-1 to finish): ", courses);
 if(index==-1){
  break;
 }
 if(index<-1||index>=courses.size()){
  menu.result.append("Invalid selection...returning to main menu...");
  return;
 }
 Course course = courses.get(index);
 student.addCourse(course);
 }
 students.add(student);
 menu.result.append("Student added successfully!");
}
private void newSession(){
if(students.isEmpty()){
  menu.result.append("No students defined, please add a student first!\n");
  return;
 }
 if(tutors.isEmpty()){
  menu.result.append("No tutors added, please add a tutor first!");
  return;
 }
 int index1 = menu.selectItemFromList("Select the course you would like to have a session over: ", courses);
 if(index1<0||index1>=courses.size()){
  menu.result.append("Invalid selection...returning to main menu...");
  return;
 }
 int index2 = menu.selectItemFromList("Select the tutor to host the session: ", tutors);
 if(index2<0||index2>=tutors.size()){
  menu.result.append("Invalid selection...returning to main menu...");
  return;
 }
 Course course = courses.get(index1);
 Tutor tutor = tutors.get(index2);
 Session session = new Session(course, tutor);
 String date = menu.getString("Enter the date for the session (YYMMDD): ");
 String time = menu.getString("Enter the time for the session to start (HH:MM): ");
 int duration = menu.getInt("Enter the duration for the session (in minutes): ");
 session.setSchedule(date, time, duration);
 int index=0;
 while(true){
 index = menu.selectItemFromList("Select the students to add to this session (-1 to finish): ", students);
 if(index<-1||index>=students.size()){
  menu.result.append("Invalid selection...returning to main menu...");
  return;
 }
 if(index==-1){
 break;
 }
 Student student = students.get(index);
 session.addStudent(student);
 }
 sessions.add(session);
 menu.result.append("Session added successfully!");
}
 private void newz(){
  courses.clear();
  students.clear();
  tutors.clear();
  sessions.clear();
  file=null;
 }
 private void saveAs(){
  file=null;
  save();
 }
 private void save(){
  if(file==null){
   file=menu.selectFile("Please enter a selection to save at: ",file,null);
  }
  if(file!=null){
   try(PrintStream ps = new PrintStream(file)){
    ps.println(courses.size());
    for(Course c : courses){
     c.save(ps);
    }
    ps.println(students.size());
    for(Student st : students){
     st.save(ps);
    }
    ps.println(tutors.size());
    for(Tutor t : tutors){
     t.save(ps);
    }
    ps.println(sessions.size());
    for(Session s : sessions){
     s.save(ps);
    }
    menu.result.append("File saved successfully!");
   }catch(Exception e){
    menu.result.append("Error saving files: "+e.getMessage()+"\n");
   }
   
  }
 }
 private void open(){
  file=menu.selectFile("Select a file to open: ",file,null);
  if(file!=null){
   try(Scanner in = new Scanner(file)){
   int sizeCourse = in.nextInt(); in.nextLine();
   while(sizeCourse-- > 0){
    courses.add(new Course(in));
   }
   int sizeStudent = in.nextInt(); in.nextLine();
   while(sizeStudent-- > 0){
    students.add(new Student(in));
   }
   int sizeTutor = in.nextInt(); in.nextLine();
   while(sizeTutor-- > 0){
    tutors.add(new Tutor(in));
   }
   int sizeSession = in.nextInt(); in.nextLine();
   while(sizeSession-- > 0){
    sessions.add(new Session(in));
   }
   menu.result.append("Files loaded successfully!");
   }catch(Exception e){
     menu.result.append("Error loading files: "+e.getMessage()+"\n");
     newz();
   }
  }
 }
 private void review(List<? extends Rateable> list){
  if(list==null||list.isEmpty()){
   menu.result.append("No items to review...\n");
   return;
  }
  Integer index = menu.selectItemFromList("Make a selection for reviewing: ", list);
  if(index==null||index<0||index>=list.size()){
  menu.result.append("Invalid selection...returning to main menu...");
  return;
  }
  Rateable selected = list.get(index);
  double average = selected.getAverageRating();
  if(Double.isNaN(average)){
   System.out.println("No ratings have been defined yet!\n");
  }else{
   System.out.printf("Average rating: "+average+"\n");//maybe error here
  }
  Person user = login();
  if(user!=null){
   Integer stars=menu.getInt("Enter a rating (1-5): ");
   if(stars==null){
    menu.result.append("No rating provided...returning to Main Menu...");
    return;
   }
   if(stars!=null){
    String reviewString=menu.getString("Enter a review: ");
    Comment review = new Comment(reviewString,user,null);
    Rating newRating = new Rating(stars, review);
    selected.addRating(newRating);
    menu.result.append("Rating added successfully!\n");
   }
  }
  Integer ratingSelected = menu.selectItemFromArray("Choose a rating: ", selected.getRatings());
  Rating tempRating = selected.getRatings()[ratingSelected];
  Comment rating = tempRating.getReview();
  while(true){
   printExpandedComments(rating, 0);
   String choise = menu.getString("Make a selection! (R)eply, (U)p, (D)own, (M)ain Menu: ");
   if(choise.equalsIgnoreCase("M")){
    return;
   }
   if(choise.equalsIgnoreCase("R")){
    if(user==null){
     menu.result.append("Please login.\n");
     user = login();
     if(user==null){
     System.out.println("No login detected, returning to Main Menu...");//maybe menu.result.append here
     return;
     }
    }
    String replyS = menu.getString("Enter a comment: ");
    rating.addReply(replyS,user);
   }
   if(choise.equalsIgnoreCase("U")){
    if(rating.getInReplyTo()!=null){
     rating=rating.getInReplyTo();
    }else{
     System.out.println("Already at the top!\n");
    }
   }
   if(choise.equalsIgnoreCase("D")){
    if(rating.numReplies()==0){
     System.out.println("No replies to go down to!\n");
    }
    if(rating.numReplies()!=0){
     int index1 = menu.getInt("Select a comment index to move to: ");
     rating=rating.getReply(index1);
    }
   }
  }
 }
 private Person login(){
  String[] choice = new String[]{"Student", "Tutor", "Neither/Cancel Login"};
  Integer index = menu.selectItemFromArray("Who are you logging in as?: ", choice);
  if(index==null){
   menu.result.append("Login canceled");
   return null;
  }
  String selection = choice[index];
   if(index==0){
    int indexS = menu.selectItemFromList("Select a student to log in as: ", students);
    Student student = students.get(indexS);
    return student;
   }
   if(index==1){
    int indexT = menu.selectItemFromList("Select a tutor to log in as: ", tutors);
    Tutor tutor = tutors.get(indexT);
    return tutor;
   }
  return null;
 }
 private void printExpandedComments(Comment c, int level){
  printIndented(c.toString(), level);
  System.out.println("\n");
  for(int i=0; i<c.numReplies(); ++i) {
   printExpandedComments(c.getReply(i), level+1);
  }
 }
 private void printIndented(String multiline, int level){
  String[] strings = multiline.split("\n");
  for(String s : strings) {
   System.out.println(" ".repeat(level) + s);
  }
 }
}
