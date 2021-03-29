**Classes**

_Client-side_
Client:
- Main method for the client

RunClient:
- Class for running the client
- Connects a client to a server (socket)
- Starts a new thread that handles the requests from the server

ServerHandler:
- Handles request from server
- Replies with string from (System.in)

_Server-side_
Server:
- Main method for server

RunServer:
- Class for running the server
- Creates a thread to handle broadcasting strings from server to connected clients 
- Method that listens for client-request (connection request)
- and handles the client with a thread

ClientHandler:
- Class for handling the client-request/replies

BroadcastThread:
-Class for handling the broadcast msg from server to connected clients


**End of classes**




