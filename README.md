# java-backend
A simple web back-end built with java.net

### System Requirements
- Java Runtime Environment 32 or 64 bit (1.8 or later)
- >1GB Free RAM
- Operating System should not matter, as long as Java is installed.

## Startup Arguments:

### Arguments
```
java -jar javabackend.jar [port=%number%] ...
```
### Argument Usage
| Argument | Default | Usage |
| :--- | :---: | :--- |
| `port=%number%` | 80 | Server port. Note that this port not default to 443 on an HTTPS connection. |
| `client=%path%` | "/" | Absolute path of your client folder. All requests made from the home page at the default endpoint will be redirected to this path. If your backend and client folder are in the same directory, providing the client argument is not necessary. Note that the backend will only fetch a local folder with the file protocol. |
| `cors=%string%` | " " | Origins to enable CORS with the Access-Control-Allow-Origin header. Set to '*' to enable all origins. More about CORS at  https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS |

```cmd
java -jar javabackend.jar [port=%number%] [client=%path%] [cors=%string%]

  port=%number%        Server port. Note that this port not
   default: 80         default to 443 on an HTTPS connection.

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
   default: " "        ccess-Control-Allow-Origin header.
                       Set to '*' to enable all origins.
                       More about CORS at 
                       https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
```
