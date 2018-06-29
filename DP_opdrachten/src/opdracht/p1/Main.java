package opdracht.p1;

public class Main {
	public static void main(String[] args) {
		CreateFile f = new CreateFile();
		
		f.newFile();
		f.addRecord();
		f.removeRecord();
		f.closeFile();
	}
}
