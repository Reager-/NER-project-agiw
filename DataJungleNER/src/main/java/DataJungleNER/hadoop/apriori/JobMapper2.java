package DataJungleNER.hadoop.apriori;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class JobMapper2 extends Mapper<Text,Text,Text,EntitiesWritable>  {
	
	
	
	protected void map(Text key, Text value, Context ctx) throws IOException, InterruptedException  {
		EntitiesWritable prod = new EntitiesWritable();
		String[] token = key.toString().split("::");
		new IntWritable(Integer.parseInt(value.toString()));
		prod.setNomeCoppia(key);
		prod.setQuantita(new IntWritable(Integer.parseInt(value.toString())));
		
		
		
		
		ctx.write(new Text(token[0]), prod);
		
		
		
	}

}
