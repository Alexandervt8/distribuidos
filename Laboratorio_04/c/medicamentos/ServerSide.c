#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <pthread.h>
#include "Medicine.h"
#include "Stock.h"

#define PORT 33331

void *handle_client(void *arg) {
    int new_socket = *((int *)arg);
    free(arg);

    char buffer[BUFFER_SIZE] = {0};
    if (read(new_socket, buffer, BUFFER_SIZE) <= 0) {
        perror("read");
        close(new_socket);
        return NULL;
    }

    if (strncmp(buffer, "1", 1) == 0) {
        list_medicines(new_socket);
    } else if (strncmp(buffer, "2", 1) == 0) {
        char name[50] = {0};
        int amount;

        send(new_socket, "Ingrese nombre de la medicina\n", 30, 0);
        if (read(new_socket, name, 50) <= 0) {
            perror("read");
            close(new_socket);
            return NULL;
        }

        send(new_socket, "Ingrese cantidad a comprar\n", 27, 0);
        if (read(new_socket, buffer, BUFFER_SIZE) <= 0) {
            perror("read");
            close(new_socket);
            return NULL;
        }
        amount = atoi(buffer);

        buy_medicine(new_socket, name, amount);
    }

    close(new_socket);
    return NULL;
}

int main(int argc, char const *argv[]) {
    int server_fd, new_socket;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);

    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) {
        perror("setsockopt failed");
        close(server_fd);
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
        perror("listen failed");
        close(server_fd);
        exit(EXIT_FAILURE);
    }

    initialize_stock();

    while (1) {
        if ((new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
            perror("accept failed");
            close(server_fd);
            exit(EXIT_FAILURE);
        }

        int *client_sock = malloc(sizeof(int));
        *client_sock = new_socket;

        pthread_t thread_id;
        pthread_create(&thread_id, NULL, handle_client, (void*)client_sock);
        pthread_detach(thread_id);
    }

    close(server_fd);
    return 0;
}
