# Chat App
## Socket-Based Client-Server Chat Application

This project demonstrates a simple client-server chat application using sockets in Java. The server listens for incoming connections from clients, and both the server and client can send and receive messages.

## Features

- Two-way communication between the client and server.
- Simple GUI using AWT components.
- Multi-threaded to handle incoming messages.


## Running the Application

1. **Start the Server:**
   - Navigate to the `Server.java` file.
   - Run the `Server` class. The server will start listening on port `1200`.

2. **Start the Client:**
   - Navigate to the `Client.java` file.
   - Run the `Client` class. The client will connect to the server on `localhost` at port `1200`.

## Code Overview

### Server.java

The `Server` class is responsible for:
- Creating a `ServerSocket` to listen for incoming client connections.
- Accepting a client connection and setting up input and output streams for communication.
- Using a separate thread to handle incoming messages from the client.

### Client.java

The `Client` class is responsible for:
- Creating a `Socket` to connect to the server.
- Setting up input and output streams for communication.
- Using a separate thread to handle incoming messages from the server.

### User Interface

Both the server and client have a simple graphical user interface (GUI) built with AWT components:
- `TextField` for inputting messages.
- `TextArea` for displaying chat history.
- `Button` to send messages.

## Example Usage

1. Start the server.
2. Start the client.
3. Type a message in the client's text field and click the "Send" button. The message will appear in both the client's and server's text areas.
4. Type a message in the server's text field and click the "Send" button. The message will appear in both the server's and client's text areas.

## Troubleshooting

- Ensure the server is running before starting the client.
- Check for any exceptions in the console output and resolve any issues accordingly.
- Make sure the port number (`1200`) is not being used by another application.
