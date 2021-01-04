# java-backend
A simple web back-end built with java.net. Supports dynamic startup configurations,
such as setting the server port and client directory.

## System Requirements
- Operating System should not matter, as long as Java is installed.
- Java Runtime Environment 32 or 64 bit (1.8 or later)
- 1GB Free RAM

## What I Learned
Before dabbling in backend development with java.net, I have stuck with NodeJS
for nearly a year. In my time building apps with Node, I sort of took the
abstractedness of the language, and the Express server framework, for granted.
Building even a simple web server with java.net opened my eyes to just how
versatile, expansive, and bare-bones web development can be, and what I can do
with my knowledge. I hope this sample project helps you as well. :)

## Startup Arguments:

### Arguments
```
java -jar javabackend.jar [port=%number%] ...
```


### Example
`java -jar javabackend.jar port=80 client=./client`

Opens a server on the default web port. The client param
implies that the client folder is in the same directory as
the backend. The project structure would look something like:
```
your/folder
├───javabackend.jar
└───client
    └───index.html
    └───styles.css
```
If you want your server to look like this instead:
```
your/folder/
├───backend
│   └───javabackend.jar
└───client
    └───index.html
    └───styles.css
```
You would set the client param to `client=../client`

### Argument Usage
| Argument | Default | Usage |
| :--- | :---: | :--- |
| `port=%number%` | 3000 | Server port. Note that this port not default to 443 on an HTTPS connection. |
| `client=%path%` | "/" | Absolute path of your client folder. All requests made from the home page at the default endpoint will be redirected to this path. If your backend and client folder are in the same directory, providing the client argument is not necessary. Note that the backend will only fetch a local folder with the file protocol. |
| `cors=%string%` | " " | Origins to enable CORS with the Access-Control-Allow-Origin header. Set to '*' to enable all origins. More about CORS at  https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS |

```cmd
java -jar javabackend.jar [port=%number%] [client=%path%] [cors=%string%]

  port=%number%        Server port. Note that this port will not
   default: 3000       default to 443 on an HTTPS connection.

  client=%path%        Absolute path of your client folder.
   default: "/"        All requests made from the home page
                       at the default endpoint will be
                       redirected to this path. If your
                       backend and client folder are in the
                       same directory, providing the client
                       argument is not necessary. Note that
                       the backend will only fetch a local
                       folder with the file protocol.

  cors=%string%        Origins to enable CORS with the
   default: " "        Access-Control-Allow-Origin header.
                       Set to '*' to enable all origins.
                       More about CORS at 
                       https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
```
