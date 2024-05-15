import socket
import threading
import pickle
from datetime import datetime


class Server:

    def __init__(self, port):
        self.port = port
        self.server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.clients = {}
        self.start()

    def start(self):
        try:
            self.server_socket.bind(("localhost", self.port))
            self.server_socket.listen(5)
            print("Server is listening on port", self.port)
            self.accept_connections()
        except Exception as e:
            print("Error starting server:", e)

    def accept_connections(self):
        while True:
            client_socket, client_address = self.server_socket.accept()
            print("New connection from:", client_address)
            client_thread = threading.Thread(target=self.handle_client,
                                             args=(client_socket, ))
            client_thread.start()

    def handle_client(self, client_socket):
        username = pickle.loads(client_socket.recv(1024))
        print(username, "joined the chat room.")
        self.clients[client_socket] = username
        self.broadcast(username + " has joined the chat room.")
        while True:
            try:
                message = pickle.loads(client_socket.recv(1024))
                if message.type == ChatMessage.LOGOUT:
                    self.disconnect(client_socket)
                    break
                elif message.type == ChatMessage.WHOISIN:
                    self.send_active_users(client_socket)
                else:
                    self.broadcast(username + ": " + message.message)
            except Exception as e:
                print("Error handling client:", e)
                self.disconnect(client_socket)
                break

    def broadcast(self, message):
        for client_socket in self.clients:
            try:
                client_socket.send(pickle.dumps(message))
            except Exception as e:
                print("Error broadcasting message:", e)
                self.disconnect(client_socket)

    def send_active_users(self, client_socket):
        active_users = "Active users:\n"
        for username in self.clients.values():
            active_users += username + "\n"
        client_socket.send(pickle.dumps(active_users))

    def disconnect(self, client_socket):
        username = self.clients[client_socket]
        del self.clients[client_socket]
        self.broadcast(username + " has left the chat room.")
        client_socket.close()


class ChatMessage:
    WHOISIN = 0
    MESSAGE = 1
    LOGOUT = 2


def main():
    port = 1500
    server = Server(port)


if __name__ == "__main__":
    main()
