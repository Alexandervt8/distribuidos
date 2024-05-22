#include "Medicine.h"
#include "Stock.h"
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h> // Para send
#include <netinet/in.h> // Para send

// Declaración de variables externas definidas en algún otro lugar
extern int medicine_count;
extern Medicine stock[MAX_MEDICINES];

void buy_medicine(int sock, const char *name, int amount) {
    char buffer[256];
    for (int i = 0; i < medicine_count; i++) {
        if (strcmp(stock[i].name, name) == 0) {
            if (stock[i].stock >= amount) {
                stock[i].stock -= amount;
                snprintf(buffer, sizeof(buffer), "Bought %s\nPrice: %.2f\nStock: %d\n",
                         stock[i].name, stock[i].unitPrice * amount, stock[i].stock);
            } else {
                snprintf(buffer, sizeof(buffer), "Not enough stock for %s\n", name);
            }
            send(sock, buffer, strlen(buffer), 0);
            return;
        }
    }
    snprintf(buffer, sizeof(buffer), "Medicine %s not found\n", name);
    send(sock, buffer, strlen(buffer), 0);
}

