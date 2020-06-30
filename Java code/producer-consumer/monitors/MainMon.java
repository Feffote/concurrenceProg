package monitores;

public class MainMon {
	
    public static void main(String[] args) {
    	final int PRODUCTOR = 3;
        final int CONSUMIDOR = 3;
        
        
        CintaMon cintaMon = new CintaMon();
        
        System.out.println("\t\tMONITORES\n");
        for (int i = 0; i < PRODUCTOR; i++) {
            new ProductorMon("Productor " + i, cintaMon).start();
        }
        
        for (int i = 0; i < CONSUMIDOR; i++) {
            new ConsumidorMon("Consumidor " + i, cintaMon).start();
        }
        
    }
    
    
    
} 