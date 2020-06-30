package monitores;

public class ProductorMon extends Thread {

    private CintaMon cinta;

    public ProductorMon(String name, CintaMon cinta) {
        super(name);
        this.cinta = cinta;
    }

    @Override
    public void run() {
       // while (Boolean.FALSE) {
	    	for(int i=0; i < 10; i++) {
	            cinta.producir(this.getName());
	        }
    }

}  