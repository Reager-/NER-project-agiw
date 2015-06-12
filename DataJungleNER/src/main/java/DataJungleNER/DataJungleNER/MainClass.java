package DataJungleNER.DataJungleNER;

public class MainClass {
	public static void main(String [] args){
	NERexecutor e=new NERexecutor("target/prova.txt","target/entità2.txt");
	e.exec();
		//NERexecutorSeriale e=new NERexecutorSeriale("target/prova.txt","target/entitàSeriale.txt");
		//e.exec();
	
	}

}
