#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include "Medicine.h"

#define PORT 33331

int main(int argc, char const *argv[]) {
    struct sockaddr_in address;
    int sock = 0;
    struct sockaddr_in serv_addr;
    char buffer[BUFFER_SIZE] = {0};

    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        printf("\n Socket creation error \n");
        return -1;
    }

    memset(&serv_addr, '0', sizeof(serv_addr));

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(PORT);

    if (inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr) <= 0) {
        printf("\nInvalid address/ Address not supported \n");
        return -1;
    }

    if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) {
        printf("\nConnection Failed \n");
        return -1;
    }

    printf("Ingresa la opcion\n1: Listar productos\n2: Comprar Producto\n");
    int option;
    scanf("%d", &option);

    if (option == 1) {
        send(sock, "1", 1, 0);
        read(sock, buffer, BUFFER_SIZE);
        printf("%s", buffer);
    } else if (option == 2) {
        send(sock, "2", 1, 0);
        read(sock, buffer, BUFFER_SIZE);
        printf("%s", buffer);

        char name[50];
        int amount;

        printf("Ingrese el nombre de la medicina:\n");
        scanf("%s", name);
        send(sock, name, strlen(name), 0);

        printf("Ingrese la cantidad a comprar:\n");
        scanf("%d", &amount);
        snprintf(buffer, sizeof(buffer), "%d", amount);
        send(sock, buffer, strlen(buffer), 0);

        read(sock, buffer, BUFFER_SIZE);
        printf("%s", buffer);
    }

    close(sock);
    return 0;
}
