#include "Stock.h"
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h> // Para send
#include <netinet/in.h> // Para send

Medicine stock[MAX_MEDICINES];
int medicine_count = 0;

void initialize_stock() {
    strcpy(stock[medicine_count].name, "Paracetamol");
    stock[medicine_count].unitPrice = 3.2;
    stock[medicine_count].stock = 10;
    medicine_count++;

    strcpy(stock[medicine_count].name, "Mejoral");
    stock[medicine_count].unitPrice = 2.0;
    stock[medicine_count].stock = 20;
    medicine_count++;

    strcpy(stock[medicine_count].name, "Amoxilina");
    stock[medicine_count].unitPrice = 1.0;
    stock[medicine_count].stock = 30;
    medicine_count++;

    strcpy(stock[medicine_count].name, "Aspirina");
    stock[medicine_count].unitPrice = 5.0;
    stock[medicine_count].stock = 40;
    medicine_count++;
}

void list_medicines(int sock) {
    char buffer[BUFFER_SIZE] = "";
    for (int i = 0; i < medicine_count; i++) {
        char medicine_info[256];
        snprintf(medicine_info, sizeof(medicine_info), "Name: %s\nPrice: %.2f\nStock: %d\n*--------------*\n",
                 stock[i].name, stock[i].unitPrice, stock[i].stock);
        if (strlen(buffer) + strlen(medicine_info) < BUFFER_SIZE - 1) {
            strncat(buffer, medicine_info, sizeof(buffer) - strlen(buffer) - 1);
        } else {
            break;
        }
    }
    send(sock, buffer, strlen(buffer), 0);
}
