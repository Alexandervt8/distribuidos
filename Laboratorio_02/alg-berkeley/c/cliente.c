#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <time.h>

#define PORT 12345
#define SERVER_IP "127.0.0.1"

int main() {
    int sock;
    struct sockaddr_in serv_addr;
    time_t current_time;
    // Crear el socket del cliente
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        perror("error al crear el socket");
        exit(EXIT_FAILURE);
    }
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(PORT);
    // Convertir la dirección IP del servidor de texto a binario
    if(inet_pton(AF_INET, SERVER_IP, &serv_addr.sin_addr)<=0) {
        perror("dirección inválida o no soportada");
        exit(EXIT_FAILURE);
    }
    // Conectar al servidor
    if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0){
        perror("error al conectar con el servidor");
        exit(EXIT_FAILURE);
    }
    // Leer la hora del servidor
    read(sock, &current_time, sizeof(current_time));
    printf("Hora recibida del servidor: %s", asctime(localtime(&current_time)));
    return 0;
}