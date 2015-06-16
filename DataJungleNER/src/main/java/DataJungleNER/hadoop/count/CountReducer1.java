package DataJungleNER.hadoop.count;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CountReducer1 extends Reducer<Text,IntWritable,Text,IntWritable> {
	
	
protected void reduce(Text key, Iterable<IntWritable> values, Context ctx) throws IOException, InterruptedException {	
	int count=0;
	for(IntWritable i:values){
     count+=i.get();
		}
	ctx.write(key, new IntWritable(count));
	

}	
}
