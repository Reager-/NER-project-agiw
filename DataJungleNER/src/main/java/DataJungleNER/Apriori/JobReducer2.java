package DataJungleNER.Apriori;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JobReducer2 extends Reducer<Text,EntitiesWritable,Text,Text> {
	
	
protected void reduce(Text key, Iterable<EntitiesWritable> values, Context ctx) throws IOException, InterruptedException {	
	EntitiesWritable temp=new EntitiesWritable();
	LinkedList<EntitiesWritable> valueList = new LinkedList<EntitiesWritable>();
	for(EntitiesWritable p:values){
		if(p.getNomeCoppia().equals(key)){
	           temp.setNomeCoppia(p.getNomeCoppia());
	           temp.setQuantita(p.getQuantita());
			}else{
				valueList.add(new EntitiesWritable(p.getNomeCoppia(),p.getQuantita()));
			}
		
	}
	for(EntitiesWritable z:valueList){
		if(z.getQuantita()!=null&&temp.getQuantita()!=null){
		double res=(double)z.getQuantita().get()/(double)temp.getQuantita().get();
		ctx.write(z.getNomeCoppia(),new Text(""+res));
		}
		
		
	}
}

}
