package DataJungleNER.hadoop.count;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Count extends Configured implements Tool {
    public static void main( String[] args ) throws Exception
    {
    	Long start=System.currentTimeMillis();
    	int res = ToolRunner.run(new Configuration(), new Count(), args);
    	Long end=(System.currentTimeMillis()-start)/1000;
    	System.out.println(end);
    	
        System.exit(res);
    }


	public int run(String[] args) throws Exception {
		Path input = new Path(args[0]);
	    Path output = new Path(args[1]);
	    Configuration conf = getConf();
	    Job job1 = new Job(conf, "count");
	    FileInputFormat.addInputPath(job1, input);
	    FileOutputFormat.setOutputPath(job1, output);
	    job1.setJarByClass(Count.class);
	    job1.setMapperClass(CountMapper1.class);
	    job1.setReducerClass(CountReducer1.class);
	    job1.setInputFormatClass(TextInputFormat.class);
	    job1.setMapOutputKeyClass(Text.class);
	    job1.setMapOutputValueClass(IntWritable.class);
	    boolean succ = job1.waitForCompletion(true);
	    if (! succ) {
	      System.out.println("Job1 failed, exiting");
	      return -1;
	    }
	    
		return 0;
	}
}
