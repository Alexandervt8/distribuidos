#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#define PORT 12345
int main() {
    struct sockaddr_in serv_addr;
    int sock = 0, valread;
    char buffer[1024] = {0};
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        printf("\n Error al crear el socket \n");
        return -1;
    }
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(PORT);
    if(inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr)<=0) {
        printf("\n Dirección inválida o no soportada \n");
        return -1;
    }
    if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0){
        printf("\n Error al conectar con el servidor \n");
        return -1;
    }
    valread = read(sock, buffer, 1024);
    printf("%s", buffer);
    return 0;
    }