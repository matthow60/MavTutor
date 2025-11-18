package rating;
import people.Person;
import java.util.ArrayList;
public class Comment{
 private String text;
 private Person author;
 private Comment inReplyTo;
 private ArrayList<Comment> replies;
  public Comment(String text, Person author, Comment inReplyTo){
   replies=new ArrayList<>();
   if(text==null||text.isEmpty()) throw new IllegalArgumentException("Text cannot be empty or null");
   if(author==null) throw new IllegalArgumentException("Author cannot be null");
   this.text=text;
   this.author=author;
   this.inReplyTo=inReplyTo;
  }
 public void addReply(String text, Person author){
  if(text==null||author==null||text.isEmpty()){
    throw new IllegalArgumentException("Error in text input or author is null.");
   }
  Comment reply = new Comment(text, author, this);
  replies.add(reply);
 }
 public int numReplies(){
  return replies.size();
 }
 public Comment getReply(int index){
  return replies.get(index);
 }
 public Comment getInReplyTo(){
  return inReplyTo;
 }
 @Override
 public String toString(){
  StringBuilder s = new StringBuilder("Comment by " + author.getName());
  if(inReplyTo!=null){
   s.append(" in reply to ").append(inReplyTo.author.getName());
  }
  if(!replies.isEmpty()){
    s.append("\nReplies:\n");
    for(int i=0; i<replies.size(); ++i){
     s.append(i).append(": ").append(replies.get(i).author.getName()+text).append("\n");
    }
  }
  return s.toString();
 }
}
