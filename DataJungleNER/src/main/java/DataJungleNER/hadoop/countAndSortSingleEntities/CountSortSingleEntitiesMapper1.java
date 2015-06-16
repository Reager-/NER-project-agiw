package DataJungleNER.hadoop.countAndSortSingleEntities;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountSortSingleEntitiesMapper1 extends Mapper<LongWritable,Text,Text,IntWritable>  {
	
	private static final IntWritable ONE = new IntWritable(1);
	
	
	
	protected void map(LongWritable key, Text value, Context ctx) throws IOException, InterruptedException {
		String[] token = value.toString().split("::");
		if (token.length>1){
			for(int i=1;i<token.length;i++){
				ctx.write(new Text(token[i]), ONE);
			}
		}
	    }
	  }
	
	
	

