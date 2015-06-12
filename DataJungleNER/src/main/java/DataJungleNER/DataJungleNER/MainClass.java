package DataJungleNER.DataJungleNER;











public class MainClass {
	public static void main(String [] args){
		PropertiesManager p=new PropertiesManager();
	NERexecutor e=new NERexecutor(p.getValueForKey("urls"),p.getValueForKey("outputEnt"));
	e.exec();

		//NERexecutorSeriale e=new NERexecutorSeriale("target/prova.txt","target/entitàSeriale.txt");

		//e.exec();
		
		


	}
	


}
