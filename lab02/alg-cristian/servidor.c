#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#define PORT 12345
int main() {
    int server_fd, client_socket;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    char buffer[1024] = {0};
    time_t current_time;
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("error al crear el socket");
        exit(EXIT_FAILURE);
    }
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) {
        perror("error al establecer las opciones del socket");
        exit(EXIT_FAILURE);
    }
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);
    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address))<0) {
        perror("error al enlazar el socket");
        exit(EXIT_FAILURE);
    }
    if (listen(server_fd, 3) < 0) {
        perror("error al escuchar por conexiones entrantes");
        exit(EXIT_FAILURE);
    }
    printf("El servidor está escuchando en el puerto %d...\n", PORT);
    while (1) {
        if ((client_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen))<0) {
            perror("error al aceptar la conexión entrante");
            exit(EXIT_FAILURE);
        }
        current_time = time(NULL);
        snprintf(buffer, sizeof(buffer), "%s", asctime(localtime(&current_time)));
        send(client_socket, buffer, strlen(buffer), 0);
        printf("Hora enviada al cliente\n");
        close(client_socket);
    }
    return 0;
}