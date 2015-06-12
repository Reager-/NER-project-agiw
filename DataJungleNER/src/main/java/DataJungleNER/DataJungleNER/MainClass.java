package DataJungleNER.DataJungleNER;











public class MainClass {
	public static void main(String [] args){
		PropertiesManager p=new PropertiesManager();
		if(p.getValueForKey("typeExecution").equals("dual")){
			DualNERexecutor e=new DualNERexecutor(p.getValueForKey("urls"),p.getValueForKey("outputEnt"),p.getValueForKey("outputEnt2"));
			e.exec();
		}else{
			NERexecutor e=new NERexecutor(p.getValueForKey("urls"),p.getValueForKey("outputEnt"));
			e.exec();
		}
		
	//NERexecutor e=new NERexecutor(p.getValueForKey("urls"),p.getValueForKey("outputEnt"));
	//e.exec();

		//NERexecutorSeriale e=new NERexecutorSeriale("target/prova.txt","target/entitàSeriale.txt");

		//e.exec();
		
	
		


	}
	


}
