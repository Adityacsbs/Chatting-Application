# Real-Time Chatting Application

A real-time messaging application built using **Java Swing**, **Socket Programming**, and **Java Multithreading**. This application allows users to chat in real-time over a network with features such as a scrollable chat window, message timestamps, and user profiles.

## Features

- **Real-Time Messaging**: Instant message exchange between clients and server using socket communication.
- **Scrollable Chat Window**: Allows users to scroll through messages in the chat window.
- **Profile Display**: Users can view and edit their profile, including username and status.
- **Message Timestamps**: Each message is timestamped to indicate when it was sent.
- **Multithreading**: Server and client use threads to handle concurrent tasks and ensure responsive communication.

## Technologies Used

- **Java Swing**: For building the graphical user interface (GUI) of the application.
- **Socket Programming**: To establish client-server communication over TCP/IP using **`Socket`** and **`ServerSocket`** for message exchange.
- **Multithreading**: To ensure smooth handling of multiple tasks concurrently, including message sending and receiving.
- **JScrollPane**: For enabling a scrollable chat window when the message history exceeds the visible window area.

## Installation

### Prerequisites

- **Java 8 or higher** should be installed on your system.

### Steps to Install

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Adityacsbs/Chatting-Application.git

2. **Navigate to the project directory**: Open a terminal or command prompt and go to the folder where the repository has been cloned.
  ```bash
   cd Chatting-Application
```
3 .Compile the Java files: Use the Java compiler to compile the server and client files.
 ```bash
javac Server.java
javac Client.java
```
4. Run the Server: The server needs to be running before clients can connect. Start the server using the following command:

```bash

java Server
```
The server will listen for incoming client connections.

5.Run the Client: Open multiple terminals (or command prompts) for each client you want to run. To start a client, use:

```bash

java Client
```
Each client will connect to the server and can start sending and receiving messages.


