#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include "Calculator.h"

#define PORT 8080
#define BUFFER_SIZE 1024

// Función para manejar la conexión con el cliente
void handle_client(int new_socket) {
    char buffer[BUFFER_SIZE] = {0};
    ssize_t bytes_read;

    // Leer datos del socket y almacenar la cantidad de bytes leídos
    bytes_read = read(new_socket, buffer, BUFFER_SIZE);

    // Verificar si la lectura fue exitosa
    if (bytes_read < 0) {
        perror("Error al leer del socket");
        exit(EXIT_FAILURE);
    }

    int num1, num2;
    sscanf(buffer, "%d %d", &num1, &num2);

    // Realizar las operaciones con los números recibidos
    int sum = add(num1, num2);
    int diff = sub(num1, num2);
    int product = mul(num1, num2);
    int division = divide(num1, num2);

    // Enviar los resultados al cliente
    sprintf(buffer, "Suma: %d\nResta: %d\nMultiplicación: %d\nDivisión: %d\n", sum, diff, product, division);
    send(new_socket, buffer, strlen(buffer), 0);
    close(new_socket);
}

int main(int argc, char const *argv[]) {
    int server_fd, new_socket;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);

    // Crear descriptor de archivo para el socket
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("fallo en el socket");
        exit(EXIT_FAILURE);
    }   

    // Adjuntar el socket al puerto
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) {
        perror("setsockopt");
        exit(EXIT_FAILURE);
    }

    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);

    // Enlazar el socket al puerto
    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0) {
        perror("fallo en el bind");
        exit(EXIT_FAILURE);
    }

    if (listen(server_fd, 3) < 0) {
        perror("fallo en el listen");
        exit(EXIT_FAILURE);
    }

    printf("El servidor de la calculadora está listo.\n");

    while (1) {
        // Aceptar nuevas conexiones
        if ((new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
            perror("fallo en el accept");
            exit(EXIT_FAILURE);
        }

        // Manejar la conexión con el cliente
        handle_client(new_socket);
    }

    return 0;
}

