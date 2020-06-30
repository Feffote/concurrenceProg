package monitores;

//en esta clase se implementan los monitores

public class CintaMon {

    public int cintaCont = 0; 				
    private int espacios = 8; 
    
    static long time_start, time_end;

    
    public synchronized void producir(String nombreProductor) {
    	
    	if (cintaCont == 0) {
    		time_start = System.currentTimeMillis();
    	}
    	
    	System.out.println(nombreProductor + " produciendo..."); 		
        try {
        		while(cintaCont>=espacios) wait();
                cintaCont++;
                System.out.println("Terminó " + nombreProductor + " Cinta +1 (Total: " + cintaCont + ")");
                
                Thread.sleep(200); 			//tiempo que tarda en producir
             
        } catch (InterruptedException e) {
        } finally {
            notifyAll();
        }

	        	
    }		

    public synchronized void consumir(String nombreConsumidor) {
        
    	//while(cintaCont>0) {
	        try {	        	
	                while(cintaCont<=0) wait();
	                
	                cintaCont--;
	                System.out.println("Retira " + nombreConsumidor + " Cinta -1 (Total: " + cintaCont + ")");
	                System.out.println(nombreConsumidor + " consumiendo..."); //consumiendo...
	                
	                
	                Thread.sleep(500); 			//tiempo que tarda en consumir
	                
	        } catch (InterruptedException e) {
	        } finally {
	        	notify();
	        }
	        
	        if (cintaCont == 0) {
	        	time_end = System.currentTimeMillis();
	            System.out.println("The task has taken "+ ( time_end - time_start ) +" milliseconds");
	        }
    //	}
	        
    }
    
    
      
    
}
