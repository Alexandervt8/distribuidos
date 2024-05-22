#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>
#include "CreditCard.h"
#define PORT 8080
#define BUFFER_SIZE 1024
void handleError(const char *message) {
    perror(message);
    exit(EXIT_FAILURE);}
int main() {
    int sock = 0;
    struct sockaddr_in serv_addr;
    char buffer[BUFFER_SIZE] = {0};    // Crear socket
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        handleError("Error al crear el socket");
    }
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(PORT);
    // Convertir direcciones IPv4 y IPv6 de texto a binario
    if (inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr) <= 0) {
        handleError("Dirección inválida / Dirección no soportada");
    }    // Conectar al servidor
    if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) {
        handleError("Conexión fallida");
    }    // Solicitar todas las tarjetas de crédito
    send(sock, "GET_ALL_CARDS", strlen("GET_ALL_CARDS"), 0);
    printf("Solicitud enviada al servidor\n");
    // Leer la respuesta del servidor
    int bytesRead = read(sock, buffer, BUFFER_SIZE);
    if (bytesRead < 0) {
        handleError("Error de lectura");
    }    // Mostrar la respuesta
    printf("Respuesta del servidor:\n%s\n", buffer);
    close(sock);
    return 0;
}