import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.NullWritable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class UserDataTuple implements Writable{
    private String id = "";
    private String name = "";
    private String review_count = "";
    private String average_stars = "";

    public UserDataTuple(){}

    @Override
    public String toString() {
        return  id + ',' +
                name + ',' +
                review_count + ',' +
                average_stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getId(){
	    return id; 
		     }
    public void setId(String id){
	   this.id = id;
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
        id = in.readUTF();
	name = in.readUTF();
        review_count = in.readUTF();
        average_stars = in.readUTF();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
	out.writeUTF(name);
        out.writeUTF(review_count);
        out.writeUTF(average_stars);
	
    }
}
