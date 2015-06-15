package DataJungleNER.DataJungleNER;



public class MainClass {
	public static void main(String [] args){
		
		/*PropertiesManager p=new PropertiesManager();
		if(p.getValueForKey("typeExecution").equals("dual")){
			DualNERexecutor e=new DualNERexecutor(p.getValueForKey("urls"),p.getValueForKey("outputEnt"),p.getValueForKey("outputEnt2"));
			e.exec();
		}else{
			NERexecutor e=new NERexecutor(p.getValueForKey("urls"),p.getValueForKey("outputEnt"));
			e.exec();
		}*/
		
	//NERexecutor e=new NERexecutor(p.getValueForKey("urls"),p.getValueForKey("outputEnt"));
	//e.exec();

		//NERexecutorSeriale e=new NERexecutorSeriale("target/prova.txt","target/entitàSeriale.txt");

		//e.exec();	
		String s="http://www.10news.com/about::ORGANIZATION::ABC::PERSON::Clarifications::Story Removal Requests::Stay Connected::LOCATION";
	   String[] h=s.replace("::ORGANIZATION", "").replace("::LOCATION", "").split("::");
	   for(String d:h)
	   System.out.println(d);
	
	
	}
	


}
