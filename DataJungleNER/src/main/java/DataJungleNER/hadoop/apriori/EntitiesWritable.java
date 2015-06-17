package DataJungleNER.hadoop.apriori;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class EntitiesWritable implements WritableComparable<EntitiesWritable> {
    private Text nomeCoppia;
    private IntWritable  quantita;
    
    
    
    public EntitiesWritable(){
    	
    }
    public EntitiesWritable(Text n,IntWritable q){
    	this.nomeCoppia=n;
    	this.quantita=q;
    	
    }
    
    
	public void readFields(DataInput in) throws IOException {
		this.nomeCoppia=new Text(in.readUTF());
		this.quantita=new IntWritable(in.readInt());
		
	}
     
	public void write(DataOutput out) throws IOException {
      out.writeUTF(this.nomeCoppia.toString());	
      out.writeInt(this.quantita.get());
	}
	
	@Override
    public String toString() {
        return this.nomeCoppia.toString() + " " + this.quantita.toString();
    }

    @Override
    public int hashCode() {
        return this.nomeCoppia.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof EntitiesWritable) {
        	EntitiesWritable secondPair = (EntitiesWritable) o;
            return this.nomeCoppia.toString().equals(secondPair.getNomeCoppia().toString());
        }  
        return false;
    }

	public int compareTo(EntitiesWritable o) {
		return this.nomeCoppia.compareTo(o.getNomeCoppia());
	}

	public Text getNomeCoppia() {
		return nomeCoppia;
	}

	public void setNomeCoppia(Text nomeCoppia) {
		this.nomeCoppia = nomeCoppia;
	}

	public IntWritable getQuantita() {
		return quantita;
	}

	public void setQuantita(IntWritable quantita) {
		this.quantita = quantita;
	}

}
