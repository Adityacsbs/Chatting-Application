# Real-Time Chatting Application

A real-time messaging application built using Java Swing, Socket Programming, and Java Multithreading. This application allows users to chat in real-time over a network with features such as a scrollable chat window, message timestamps, and user profiles.

Features
Real-Time Messaging: Instant message exchange between clients and server using socket communication.
Scrollable Chat Window: Allows users to scroll through messages in the chat window.
Profile Display: Users can view and edit their profile, including username and status.
Message Timestamps: Each message is timestamped to indicate when it was sent.
Multithreading: Server and client use threads to handle concurrent tasks and ensure responsive communication.
Technologies Used
Java Swing: For building the graphical user interface (GUI) of the application.
Socket Programming: To establish client-server communication over TCP/IP using Socket and ServerSocket for message exchange.
Multithreading: To ensure smooth handling of multiple tasks concurrently, including message sending and receiving.
JScrollPane: For enabling a scrollable chat window when the message history exceeds the visible window area.
Installation
Prerequisites
Java 8 or higher should be installed on your system.
Steps to Install
Clone the repository:

bash
Copy code
git clone https://github.com/Adityacsbs/Chatting-Application.git
Compile and run the project:

Open the project directory in your terminal.
Compile the Server.java and Client.java files using javac.
bash
Copy code
javac Server.java
javac Client.java
Run the server:
bash
Copy code
java Server
Run the client in a new terminal:
bash
Copy code
java Client
Open multiple instances of the client to simulate communication between users.

How It Works
The application is built using a client-server architecture where:

Server:
Listens for incoming connections from clients on a specific port (e.g., port 6001).
When a client connects, the server creates a socket and sets up a communication stream using DataInputStream and DataOutputStream.
Client:
The client connects to the server and can send and receive messages.
Messages typed in the client’s chat window are sent to the server, which then broadcasts them to all connected clients.
Message Exchange:
The server receives a message from a client and broadcasts it to the other clients.
Each client displays the message in their chat window in real time.
Timestamp: Each message shows a timestamp of when it was sent.
Chat Window Design:
Outgoing messages are shown on the right, and incoming messages are displayed on the left.
The chat window is scrollable using JScrollPane, ensuring users can view all messages.
Key Code Snippets:
Server Socket Initialization:

java
Copy code
ServerSocket serverSocket = new ServerSocket(6001);  // Server listens on port 6001
Socket socket = serverSocket.accept();  // Accept incoming client connection
Client Socket Connection:

java
Copy code
Socket socket = new Socket("127.0.0.1", 6001);  // Connect to server on port 6001
Sending Messages:

java
Copy code
dout.writeUTF(out);  // Send message from client to server
Challenges Faced
Managing Multiple Clients:

Solution: Used multithreading to handle each client connection in a separate thread.
Making the Chat Window Scrollable:

Solution: Integrated JScrollPane to enable scrolling for the chat window when there are more messages than the visible space.
Ensuring Thread Safety:

Solution: Swing components are not thread-safe. Used SwingUtilities.invokeLater() to ensure all UI updates happen on the Event Dispatch Thread (EDT).
Future Enhancements
File Sharing: Enable users to send and receive files such as images or documents.
Private Messaging: Implement private messaging to allow direct communication between users.
Message Encryption: Implement message encryption to secure communication between clients and the server.
User Authentication: Add a login system to authenticate users before they can access the chat.
License
This project is licensed under the MIT License. See the LICENSE file for more details.

Conclusion
This Real-Time Chatting Application demonstrates the use of Socket Programming, Java Swing, and Multithreading to create a responsive and interactive messaging application. While the basic version includes real-time chat with timestamped messages, future improvements could enhance its functionality with features like encryption, private messaging, and file sharing.
