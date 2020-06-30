#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h> 	//para usar rand()

static void *productor_function(void* arg);
static void *consumidor_function(void* arg);

#define varC 50     //total de veces que se va a producir/consumir
static int cinta = 0;                  

sem_t sem_ocupados;
sem_t sem_libres;
sem_t sem_mutex;

int main(void)
{
    pthread_t productor, consumidor;
	
    sem_init(&sem_ocupados, 0, 0); 
    sem_init(&sem_libres, 0, 8); 
    sem_init(&sem_mutex, 0, 1);             
   
    pthread_create(&productor, NULL, *productor_function, NULL);
    pthread_create(&consumidor, NULL, *consumidor_function, NULL);
   
    pthread_join(productor, NULL);   // main espera a que el hilo termine
    pthread_join(consumidor, NULL);
   
    printf("valor cinta %d \n", cinta);

    return 0;
}

static void *productor_function(void* arg)
{
	for(int i=0;i<varC;i++){
		printf("Produciendo... \n");
		for(int i = 0; i<rand(); i++)
		{
		}

		sem_wait(&sem_libres);
		sem_wait(&sem_mutex);
		cinta += 1;                    //Seccion critica
		printf("Cinta + %d\n", cinta);
		sem_post(&sem_mutex);
		sem_post(&sem_ocupados);
	}
}

static void *consumidor_function(void* arg)
{
	for(int i=0;i<varC;i++){
		sem_wait(&sem_ocupados);
		sem_wait(&sem_mutex);
		cinta -= 1;                     //Seccion critica
		printf("Cinta - %d\n", cinta);
		sem_post(&sem_mutex);
		sem_post(&sem_libres);

		printf("Consumiendo...\n");
		for(int i = 0; i<rand(); i++)
		{
		}
	}
}
