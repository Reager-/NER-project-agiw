package DataJungleNER.hadoop.countAndSortSingleEntities;


import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class CountSortSingleEntitiesReducer2 extends Reducer<IntWritable,Text,Text,IntWritable> {
	public void reduce(IntWritable key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
	for(Text prod:values){
		context.write(prod, key);
	}
	}
}
