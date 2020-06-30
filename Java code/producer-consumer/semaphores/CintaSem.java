package semaforos;

import java.util.concurrent.*;

//en esta clase se implementan los semaforos

public class CintaSem {

    private int cintaCont = 0; 				
    
    private Semaphore libres = new Semaphore(8); 				
    private Semaphore ocupados = new Semaphore(0); 				
    private Semaphore mutex = new Semaphore(1);
    
    long time_start, time_end;
    
    public void producir(String nombreProductor) {
    	
    	if (cintaCont == 0) {
    		time_start = System.currentTimeMillis();
    	}
    	
        System.out.println(nombreProductor + " produciendo..."); 		
        try {
            
                libres.acquire(); 			//wait al libres
                mutex.acquire();

                cintaCont++;
                System.out.println("Termino " + nombreProductor + " Cinta +1 (Total: " + cintaCont + ")");
                mutex.release(); 

                Thread.sleep(200); 			//tiempo que tarda en producir
           
        } catch (InterruptedException ex) {} 
        finally {
            ocupados.release(); 			//signal al ocupados
        }

    }

    public void consumir(String nombreConsumidor) {
        
        try {
            
                ocupados.acquire(); 		//wait al ocupados
                mutex.acquire();

                cintaCont--;
                System.out.println("Retira " + nombreConsumidor + " Cinta -1 (Total: " + cintaCont + ")");
                System.out.println(nombreConsumidor + " consumiendo..."); 
                mutex.release();
                
          
                Thread.sleep(500); 			//tiempo que tarda en consumir
        } catch (InterruptedException ex) {} 
        finally {
            libres.release(); 				//signal al libres

        }
        
        if (cintaCont == 0) {
        	time_end = System.currentTimeMillis();
            System.out.println("The task has taken "+ ( time_end - time_start ) +" milliseconds");
        }
    }

}
