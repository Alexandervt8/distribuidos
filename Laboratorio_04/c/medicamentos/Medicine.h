#ifndef MEDICINE_H
#define MEDICINE_H

#define MAX_MEDICINES 10
#define BUFFER_SIZE 1024

typedef struct {
    char name[50];
    double unitPrice;
    int stock;
} Medicine;

void buy_medicine(int sock, const char *name, int amount);

#endif // MEDICINE_H
