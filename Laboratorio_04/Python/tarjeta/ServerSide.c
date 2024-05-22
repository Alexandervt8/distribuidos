#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include "CreditCard.h"
#include "CreditCardWallet.h"
#define PORT 8080
#define BUFFER_SIZE 1024
void handleClient(int client_socket, CreditCardWallet *wallet) {
    char buffer[BUFFER_SIZE] = {0};
    read(client_socket, buffer, BUFFER_SIZE); // Leer la solicitud del cliente
    if (strcmp(buffer, "GET_ALL_CARDS") == 0) {
        // Preparar la respuesta con toda la información de las tarjetas de crédito
        char response[BUFFER_SIZE * 10] = {0}; // Buffer grande para respuesta
        for (int i = 0; i < wallet->size; i++) {
            char cardInfo[256];
            sprintf(cardInfo, "Número de Tarjeta: %s\nTitular: %s\nFecha de Expiración: %s\n----------------------------------------\n",
                    wallet->cards[i]->cardNumber, wallet->cards[i]->cardHolderName, wallet->cards[i]->expirationDate);
            strcat(response, cardInfo);
        }
        send(client_socket, response, strlen(response), 0);
    }
}
int main() {
    int server_fd, new_socket;
    struct sockaddr_in address;
    int addrlen = sizeof(address);
    CreditCardWallet *wallet = createWallet();
    addCreditCard(wallet, createCreditCard("1234-5678-9101-1121", "Juan Pérez", "12/24"));
    addCreditCard(wallet, createCreditCard("1111-2222-3333-4444", "María García", "11/23"));
    addCreditCard(wallet, createCreditCard("5555-6666-7777-8888", "Carlos López", "10/25"));
    addCreditCard(wallet, createCreditCard("9999-0000-1111-2222", "Ana Martínez", "09/26"));
    addCreditCard(wallet, createCreditCard("3333-4444-5555-6666", "Luis Suarez", "08/27"));
    
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);
    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0) {
        perror("bind failed");
        close(server_fd);
        exit(EXIT_FAILURE);
    }
    if (listen(server_fd, 3) < 0) {
        perror("listen");
        close(server_fd);
        exit(EXIT_FAILURE);
    }
    printf("Server ready\n");
    while (1) {
        if ((new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
            perror("accept");
            close(server_fd);
            exit(EXIT_FAILURE);
        }
        handleClient(new_socket, wallet);
        close(new_socket);
    }
    close(server_fd);
    return 0;
}
