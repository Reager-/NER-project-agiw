package DataJungleNER.hadoop.apriori;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class JobMapper2 extends Mapper<Text,Text,Text,EntitiesWritable>  {
	
	
	
	protected void map(Text key, Text value, Context ctx) throws IOException, InterruptedException  {
		int intValue = Integer.parseInt(value.toString());
		EntitiesWritable prod = new EntitiesWritable();
		String[] token = key.toString().split("::");
		if (token.length>1){
			if (intValue>2){
				prod.setNomeCoppia(key);
				prod.setQuantita(new IntWritable(intValue));
				ctx.write(new Text(token[0]), prod);
			}
		} else{
			prod.setNomeCoppia(key);
			prod.setQuantita(new IntWritable(intValue));
			ctx.write(new Text(token[0]), prod);
		}
		
		
		
	}

}
