package session;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintStream;
/**
*Represents a range of time on a given date
*/
public class DateRange{
 private String date;
 private String startTime;
 private String endTime;
/**
*Constructs a date range with specific start and end times
*@param date	the date the session takes place
*@param startTime	the time the session starts
*@param endTime		the time the session ends
*/
 public DateRange(String date, String startTime, String endTime){
  this.date=date;
  this.startTime=startTime;
  this.endTime=endTime;
 }
 /**
 *Creates the date range by calculating the end time using the start time and duration on a given date.
 *@param date	the given date in MMDDYYYY
 *@param startTime	the given time in HH:MM
 *@param duration	the amount of time taken, in minutes
 */
 public DateRange(String date, String startTime, long duration){
  String time[] = startTime.split(":");
  int hours = Integer.parseInt(time[0]);
  int minutes = Integer.parseInt(time[1]);
  int startMinutes = hours*60+minutes+(int)duration;
  int endHours=startMinutes/60;
  int endMinutes=startMinutes%60;
  endTime=String.format("%02d:%02d", endHours, endMinutes);
 }
 /**
 *Creates a date range by pulling the fields from the existing save file
 *@param in		the variable for the file we read from
 */
 public DateRange(Scanner in){
  this.date=in.nextLine();
  this.startTime=in.nextLine();
  this.endTime=in.nextLine();
 }
 /**
 *Saves the current data fields into a file using PrintStream
 @param out		the PrintStream variable for the file we save the fields to
 */
 public void save(PrintStream out){
  out.println(date);
  out.println(startTime);
  out.println(endTime);
 }
 /**
 *Calculates the amount of time between the start time and end time and returns the value in minutes
 *@return the duration in minutes
 */
 public long duration(){
  String time1[] = startTime.split(":");
  String time2[] = endTime.split(":");
  int hours1 = Integer.parseInt(time1[0]);
  int minutes1 = Integer.parseInt(time1[1]);
  int hours2 = Integer.parseInt(time2[0]);
  int minutes2 = Integer.parseInt(time2[1]);
  int startMinutes = hours1*60+minutes1;
  int endMinutes = hours2*60+minutes2;
  return endMinutes-startMinutes;
 }
 /**
 *Returns the date, start time, end time, and duration in minutes formatted as a string
 *@return date and times
 */
 @Override
 public String toString(){
  return (date+" "+startTime+"-"+endTime+" ("+duration()+" minutes)");
 }
}
