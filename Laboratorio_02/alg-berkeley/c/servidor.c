#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <time.h>

#define PORT 12345
#define MAX_CLIENTS 5

int main() {
    int server_fd, client_sockets[MAX_CLIENTS];
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    time_t current_time;
    time_t time_sum = 0;

    // Crear el socket del servidor
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("error al crear el socket");
        exit(EXIT_FAILURE);
    }
    // Asignar opciones al socket
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) {
        perror("error al establecer las opciones del socket");
        exit(EXIT_FAILURE);
    }

    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);
    // Enlazar el socket a la dirección y puerto
    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address))<0) {
        perror("error al enlazar el socket");
        exit(EXIT_FAILURE);
    }
    // Escuchar por conexiones entrantes
    if (listen(server_fd, MAX_CLIENTS) < 0) {
        perror("error al escuchar por conexiones entrantes");
        exit(EXIT_FAILURE);
    }
    printf("Servidor escuchando en el puerto %d...\n", PORT);
    // Aceptar conexiones entrantes en un bucle
    for (int i = 0; i < MAX_CLIENTS; ++i) {
        if ((client_sockets[i] = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen))<0) {
            perror("error al aceptar la conexión entrante");
            exit(EXIT_FAILURE);
        }
        // Obtener la hora actual
        current_time = time(NULL);
        time_sum += current_time;
        printf("Cliente conectado. Hora actual recibida: %s", asctime(localtime(&current_time)));
        // Enviar la hora actual al cliente
        send(client_sockets[i], &current_time, sizeof(current_time), 0);
        // Cerrar la conexión con el cliente
        close(client_sockets[i]);
    }
    // Calcular el tiempo promedio
    time_t average_time = time_sum / MAX_CLIENTS;
    printf("Tiempo promedio calculado: %s", asctime(localtime(&average_time)));
    return 0;
}
