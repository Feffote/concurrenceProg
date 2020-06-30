package semaforos;

public class MainSem {

    public static void main(String[] args) {
        final int PRODUCTOR = 3;
        final int CONSUMIDOR = 3;
        
        
        CintaSem cinta = new CintaSem();
        
        System.out.println("\t\tSEMÁFOROS\n");
        for (int i = 0; i < PRODUCTOR; i++) {
            new ProductorSem("Productor " + i, cinta).start();
        }
        
        for (int i = 0; i < CONSUMIDOR; i++) {
            new ConsumidorSem("Consumidor " + i, cinta).start();
        }
        
              
    }
    
} 