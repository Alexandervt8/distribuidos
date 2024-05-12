#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>
struct LamportClock {
    int clock;        pthread_mutex_t mutex;
};
struct LamportClock lamport_clock; 
void initClock() {
    lamport_clock.clock = 0; 
    pthread_mutex_init(&lamport_clock.mutex, NULL); 
}
void tick() {
    pthread_mutex_lock(&lamport_clock.mutex); 
    lamport_clock.clock++; 
    pthread_mutex_unlock(&lamport_clock.mutex); 
}
void update(int receivedTime) {
    pthread_mutex_lock(&lamport_clock.mutex); 
    lamport_clock.clock = (lamport_clock.clock > receivedTime ? lamport_clock.clock : receivedTime) + 1; 
    pthread_mutex_unlock(&lamport_clock.mutex); 
}
int getTime() {
    int time;
    pthread_mutex_lock(&lamport_clock.mutex); 
    time = lamport_clock.clock; 
    pthread_mutex_unlock(&lamport_clock.mutex); 
    return time;
}
void* threadFunction(void* arg) {
    int threadId = *((int*)arg);    tick();
    printf("Thread %d created event with Lamport time %d\n", threadId, getTime());
    usleep(rand() % 1000000);        tick();
    printf("Thread %d received event with Lamport time %d\n", threadId, getTime());
    update(getTime());
    return NULL;
}
int main() {
    srand(time(NULL));
    initClock();
    pthread_t threads[5];
    int threadIds[5];
    for (int i = 0; i < 5; ++i) {
        threadIds[i] = i;
        pthread_create(&threads[i], NULL, threadFunction, &threadIds[i]);
    }
    for (int i = 0; i < 5; ++i) {
        pthread_join(threads[i], NULL);
    }
    printf("Final Lamport time: %d\n", getTime());
    pthread_mutex_destroy(&lamport_clock.mutex); 
    return 0;
}