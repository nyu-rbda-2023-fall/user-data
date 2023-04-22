import org.apache.hadoop.io.Writable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class UserDataTuple implements Writable{
    private String name = "";
    private String review_count = "";
    private String average_stars = "";

    public UserDataTuple(){}

    @Override
    public String toString() {
        return "UserDataTuple{" +
                "name='" + name + '\'' +
                ", review_count='" + review_count + '\'' +
                ", average_stars='" + average_stars + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReview_count(){
        return review_count;
    }

    public void setReview_count(String review_count) {
        this.review_count = review_count;
    }

    public String getAverage_stars() {
        return average_stars;
    }

    public void setAverage_stars(String average_stars) {
        this.average_stars = average_stars;
    }

    @Override
    public void readFields(DataInput in)throws IOException {
        name = in.readUTF();
        review_count = in.readUTF();
        average_stars = in.readUTF();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(name);
        out.writeUTF(review_count);
        out.writeUTF(average_stars);
    }
}
