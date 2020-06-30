#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>

static void * thread1_function(void* arg);
static void * thread2_function(void* arg);

#define varC 10000
static int recurso = 0;      //variable compartida

int main(void)
{
    pthread_t thread1, thread2;
   
    pthread_create(&thread1, NULL, *thread1_function, NULL);
    pthread_create(&thread2, NULL, *thread2_function, NULL);
   
    pthread_join(thread1, NULL);   //main espera a que el hilo termine
    pthread_join(thread2, NULL);
   
    printf(" valor recurso %d \n", recurso);

    return 0;
}

static void * thread1_function(void* arg)
{
    for(int i = 0; i< varC; i++)
    {  
        recurso += 1;                   //Seccion critica
    }
}

static void * thread2_function(void* arg)
{
    for(int i = 0; i< varC; i++)
    {
        recurso -= 1;                 //Seccion critica
    }
}