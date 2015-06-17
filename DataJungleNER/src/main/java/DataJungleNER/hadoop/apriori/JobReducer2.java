package DataJungleNER.hadoop.apriori;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JobReducer2 extends Reducer<Text,EntitiesWritable,Text,Text> {
	
	
protected void reduce(Text key, Iterable<EntitiesWritable> values, Context ctx) throws IOException, InterruptedException {	
	EntitiesWritable temp=new EntitiesWritable();
	LinkedList<EntitiesWritable> valueList = new LinkedList<EntitiesWritable>();
	for(EntitiesWritable p:values){
		if(p.getNomeCoppia().toString().equals(key.toString())){
	           temp.setNomeCoppia(p.getNomeCoppia());
	           temp.setQuantita(p.getQuantita());
			}else{
				valueList.add(new EntitiesWritable(p.getNomeCoppia(),p.getQuantita()));
			}
		
	}
	for(EntitiesWritable z:valueList){
		double res=(double)z.getQuantita().get()/(double)temp.getQuantita().get();
		DoubleWritable resDW = new DoubleWritable(res);
		ctx.write(z.getNomeCoppia(),new Text(resDW.toString()));
		
	}
}

}
