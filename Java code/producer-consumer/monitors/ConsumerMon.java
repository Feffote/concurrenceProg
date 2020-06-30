package monitores;

public class ConsumidorMon extends Thread{
    
	private CintaMon cinta;

    public ConsumidorMon(String name, CintaMon cinta) {
        super(name);
        this.cinta = cinta;
    }

    @Override
    public void run() {
        //while(Boolean.TRUE){
	    	for(int i=0; i < 10; i++) {
	            cinta.consumir(this.getName());
	        }
        //}
    }	
}  