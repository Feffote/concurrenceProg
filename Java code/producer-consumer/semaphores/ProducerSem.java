package semaforos;

public class ProductorSem extends Thread {

    private CintaSem cinta;

    public ProductorSem(String name, CintaSem cinta) {
        super(name);
        this.cinta = cinta;
    }

    @Override
    public void run() {  	
    	for(int i=0; i < 10; i++) {
            cinta.producir(this.getName());
        }
    	  
    }

}  