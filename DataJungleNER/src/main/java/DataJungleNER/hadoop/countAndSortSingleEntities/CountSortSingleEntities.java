package DataJungleNER.hadoop.countAndSortSingleEntities;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CountSortSingleEntities extends Configured implements Tool {
    public static void main( String[] args ) throws Exception
    {
    	Long start=System.currentTimeMillis();
    	int res = ToolRunner.run(new Configuration(), new CountSortSingleEntities(), args);
    	Long end=(System.currentTimeMillis()-start)/1000;
    	System.out.println(end);
    	
        System.exit(res);
    }


	public int run(String[] args) throws Exception {
		Path input = new Path(args[0]);
	    Path temp1 = new Path("temp");
	    Path output = new Path(args[1]);
	    Configuration conf = getConf();
	    Job job1 = new Job(conf, "CountSortSingleEntities1");
	    FileInputFormat.addInputPath(job1, input);
	    FileOutputFormat.setOutputPath(job1, temp1);
	    job1.setJarByClass(CountSortSingleEntities.class);
	    job1.setMapperClass(CountSortSingleEntitiesMapper1.class);
	    job1.setReducerClass(CountSortSingleEntitiesReducer1.class);
	    job1.setInputFormatClass(TextInputFormat.class);
	    job1.setMapOutputKeyClass(Text.class);
	    job1.setMapOutputValueClass(IntWritable.class);
	    boolean succ = job1.waitForCompletion(true);
	    if (! succ) {
	      System.out.println("Job1 failed, exiting");
	      return -1;
	    }
	    //serve per fare ordine decrescente
	    JobConf f=new JobConf(new Configuration(),CountSortSingleEntities.class);
	    f.setKeyFieldComparatorOptions("-r");
	    
	    
	    
	    Job job2 = new Job(f, "CountSortSingleEntities2");
	    FileInputFormat.setInputPaths(job2, temp1);
	    FileOutputFormat.setOutputPath(job2, output);
	    
	    job2.setJarByClass(CountSortSingleEntities.class);
	    job2.setMapperClass(CountSortSingleEntitiesMapper2.class);
	    job2.setReducerClass(CountSortSingleEntitiesReducer2.class);
	    job2.setInputFormatClass(KeyValueTextInputFormat.class);
	    job2.setMapOutputKeyClass(IntWritable.class);
	    job2.setMapOutputValueClass(Text.class);
	    succ = job2.waitForCompletion(true);
	    if (! succ) {
	      System.out.println("Job2 failed, exiting");
	      return -1;
	    }
	    
		return 0;
	}
}
