package DataJungleNER.hadoop.apriori;

import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountEntitiesMapper1 extends Mapper<LongWritable,Text,Text,IntWritable>  {
	
	private static final IntWritable ONE = new IntWritable(1);
	
	
	
	protected void map(LongWritable key, Text value, Context ctx) throws IOException, InterruptedException  {
		String[] token = value.toString().split("::");
		String prod;
		Collection<String> entities = new TreeSet<String>();
		for (int k=1; k<token.length; k++)
			entities.add(token[k].toLowerCase());
		String[] sortedToken = new String[entities.size()];
		sortedToken = entities.toArray(sortedToken);
		for(int i=1;i<sortedToken.length;i++){
			ctx.write(new Text(sortedToken[i].toLowerCase() ),ONE);
			for(int j=1;j<sortedToken.length;j++){
				if(i!=j){
					prod=sortedToken[i]+"::"+sortedToken[j];
					ctx.write(new Text(prod), ONE);
				}
			}
			
		}
		
		
		
		
		
		
	}

}
