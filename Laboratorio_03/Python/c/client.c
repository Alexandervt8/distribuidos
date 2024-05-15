#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <pthread.h>

#define LENGTH 2048
#define PORT 1500  // Asegúrate de que el puerto coincida con el del servidor

void str_trim_lf(char *arr, int length) {
    for (int i = 0; i < length; i++) {
        if (arr[i] == '\n') {
            arr[i] = '\0';
            break;
        }
    }
}

void *send_msg_handler(void *arg) {
    char message[LENGTH] = {};
    char buffer[LENGTH + 32] = {};
    int sockfd = *((int *)arg);

    while (1) {
        fgets(message, LENGTH, stdin);
        str_trim_lf(message, LENGTH);

        if (strcmp(message, "exit") == 0) {
            break;
        } else {
            sprintf(buffer, "%s\n", message);
            send(sockfd, buffer, strlen(buffer), 0);
        }

        memset(message, 0, LENGTH);
        memset(buffer, 0, LENGTH + 32);
    }

    pthread_exit(NULL);
}

void *recv_msg_handler(void *arg) {
    char message[LENGTH] = {};
    int sockfd = *((int *)arg);

    while (1) {
        int receive = recv(sockfd, message, LENGTH, 0);

        if (receive > 0) {
            printf("%s", message);
            str_trim_lf(message, strlen(message));
        } else if (receive == 0) {
            break;
        }

        memset(message, 0, LENGTH);
    }

    pthread_exit(NULL);
}

int main() {
    int sockfd = 0;
    struct sockaddr_in serv_addr;
    pthread_t send_msg_thread;
    pthread_t recv_msg_thread;
    char name[32];

    printf("Enter your name: ");
    fgets(name, 32, stdin);
    str_trim_lf(name, strlen(name));

    if (strlen(name) >= 32 || strlen(name) < 2) {
        printf("Name must be less than 32 and more than 2 characters.\n");
        return EXIT_FAILURE;
    }

    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd == -1) {
        perror("ERROR: socket creation failed");
        return EXIT_FAILURE;
    }

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    serv_addr.sin_port = htons(PORT);

    // Connect to the server
    if (connect(sockfd, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) == -1) {
        perror("ERROR: connect failed");
        close(sockfd);
        return EXIT_FAILURE;
    }

    // Send name
    send(sockfd, name, 32, 0);

    printf("=== WELCOME TO THE CHATROOM ===\n");

    pthread_create(&send_msg_thread, NULL, send_msg_handler, (void*)&sockfd);
    pthread_create(&recv_msg_thread, NULL, recv_msg_handler, (void*)&sockfd);

    pthread_join(send_msg_thread, NULL);
    pthread_join(recv_msg_thread, NULL);

    close(sockfd);

    return EXIT_SUCCESS;
}