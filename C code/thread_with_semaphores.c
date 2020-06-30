#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>

static void * thread1_function(void* arg);
static void * thread2_function(void* arg);

#define varC 10000
static int recurso = 0;      //variable compartida

sem_t sem1;

int main(void)
{
    pthread_t thread1, thread2;
   
    sem_init(&sem1, 0, 1);      //inicializa el semaforo en 1
   
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
        sem_wait(&sem1);   
        recurso += 1;                   //Seccion critica
        sem_post(&sem1);
    }
}

static void * thread2_function(void* arg)
{
    for(int i = 0; i< varC; i++)
    {
        sem_wait(&sem1);
        recurso -= 1;                 //Seccion critica
        sem_post(&sem1);
    }
}