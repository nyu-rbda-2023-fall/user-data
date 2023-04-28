import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

enum ANOMALY{
    MISSING
}
public class UserCleanMapper extends Mapper<LongWritable, Text, NullWritable, UserDataTuple>
{
    UserDataTuple userDataTuple = new UserDataTuple();
    private ObjectMapper mapper = new ObjectMapper();

    public boolean isValidTuple(String name, String review_count, String average_stars)
    {
        if(name.equals("")) return false;
        if(review_count.equals("")) return false;
        if(average_stars.equals("")) return false;

        return true;
    }

    @Override
    public void map(LongWritable key, Text value, Context context)throws IOException, InterruptedException
    {
        try {
            JsonNode jsonNode = mapper.readTree(value.toString());

            //Extract fields from JSON record
            String user_id = jsonNode.get("user_id").asText();
            String name = jsonNode.get("name").asText();
            String review_count = jsonNode.get("review_count").asText();
            String average_stars = jsonNode.get("average_stars").asText();

            if(isValidTuple(name, review_count, average_stars)) {
		userDataTuple.setId(user_id);
                userDataTuple.setName(name);
                userDataTuple.setReview_count(review_count);
                userDataTuple.setAverage_stars(average_stars);
                context.write(NullWritable.get(), userDataTuple);
            }
            else context.getCounter(ANOMALY.MISSING).increment(1);

        } catch (Exception e) {
            //Ignore invalid JSON records
        }
    }
}
