package DataJungleNER.hadoop.count;


import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class CountMapper1 extends Mapper<LongWritable,Text,Text,IntWritable>  {
	
	private static final IntWritable ONE = new IntWritable(1);
	
	protected void map(LongWritable key, Text value, Context ctx) throws IOException, InterruptedException {
		String[] token = value.toString().split("::");
		if (token.length>1){
			for(int i=1;i<token.length;i++){
				if (token[i]!=null)
					ctx.write(new Text("entities"), ONE);
				if (token[i].substring(0, 3).equals("(O)"))
					ctx.write(new Text("organizations"), ONE);
				if (token[i].substring(0, 3).equals("(P)"))
					ctx.write(new Text("persons"), ONE);
				if (token[i].substring(0, 3).equals("(L)"))
					ctx.write(new Text("locations"), ONE);
			}
		}
	    }
	  }
	
	
	

