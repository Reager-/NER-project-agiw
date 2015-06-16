package DataJungleNER.hadoop.countAndSortSingleEntities;


import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountSortSingleEntitiesMapper2 extends Mapper<Text,Text,IntWritable,Text>  {
	protected void map(Text key, Text value, Context ctx)throws IOException, InterruptedException {
		ctx.write(new IntWritable(Integer.parseInt(value.toString())), key);
	}
}
