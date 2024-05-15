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
    def disconnect(self, client_socket):
        username = self.clients[client_socket]
        del self.clients[client_socket]
        self.broadcast(username + " has left the chat room.")
        client_socket.close()