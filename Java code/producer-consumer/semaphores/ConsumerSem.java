package semaforos;

public class ConsumidorSem extends Thread{
    
	private CintaSem cinta;

    public ConsumidorSem(String name, CintaSem cinta) {
        super(name);
        this.cinta = cinta;
    }

    @Override
    public void run() {
        //while(true){
    	
       	for(int i=0; i < 10; i++) {
            cinta.consumir(this.getName());
        }
    	
    }	
}  