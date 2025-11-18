package rating;
public class Rating{
 private int stars;
 private Comment review;
 public Rating(int stars, Comment review){
  if(stars<1||stars>5){
   throw new IllegalArgumentException("Amount of stars not between 1-5");
  }
  this.stars=stars;
  this.review=review;
 }
 public int getStars(){
  return stars;
 }
 public Comment getReview(){
  return review;
 }
 @Override
 public String toString(){
  StringBuilder s = new StringBuilder();
  for(int i=0; i<stars; ++i) s.appendCodePoint(0x2605);
  for(int i=stars; i<5; ++i) s.appendCodePoint(0x2606);
  return s.toString();
 }
}
