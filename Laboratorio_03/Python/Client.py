import socket
import sys
import pickle
from threading import Thread


class Client:

    def __init__(self, server, port, username):
        self.server = server
        self.port = port
        self.username = username
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def start(self):
        try:
            self.socket.connect((self.server, self.port))
            print("Connected to server:", self.server, "on port:", self.port)
            self.send_message(self.username)
            receiving_thread = Thread(target=self.receive)
            receiving_thread.start()
            self.send_messages()
        except Exception as e:
            print("Error connecting to server:", e)
            self.disconnect()

    def send_message(self, message):
        self.socket.send(pickle.dumps(message))

    def receive(self):
        while True:
            try:
                message = pickle.loads(self.socket.recv(1024))
                print(message)
            except Exception as e:
                print("Error receiving message:", e)
                break

    def send_messages(self):
        while True:
            message = input("> ")
            if message.upper() == "LOGOUT":
                self.send_message(ChatMessage(ChatMessage.LOGOUT, ""))
                break
            elif message.upper() == "WHOISIN":
                self.send_message(ChatMessage(ChatMessage.WHOISIN, ""))
            else:
                self.send_message(ChatMessage(ChatMessage.MESSAGE, message))

    def disconnect(self):
        try:
            self.socket.close()
        except Exception as e:
            pass


class ChatMessage:
    WHOISIN = 0
    MESSAGE = 1
    LOGOUT = 2

    def __init__(self, type, message):
        self.type = type
        self.message = message


def main():
    server_address = "localhost"
    port = 1500
    username = input("Enter your username: ")
    client = Client(server_address, port, username)
    client.start()


if __name__ == "__main__":
    main()
