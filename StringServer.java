import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String[] stringDisplay = new String[200];
    int count = 0;

    public String handleRequest(URI url){

        if (url.getPath().equals("/add-message")){
            return String.format("Need to input query!");
        }

        else if (url.getPath().contains("/add-message?s=")){
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")){
                stringDisplay[count] = parameters[1];
                count++;
            }
            
            for (int i = 0; i <= count; i++){
                return "\n" + stringDisplay[i];
            }
        }
        return "404 not Found";
    }

    class StringServer {
        public static void main(String[] args) throws IOException {
            if(args.length == 0){
                System.out.println("Missing port number! Try any number between 1024 to 49151");
                return;
            }
    
            int port = Integer.parseInt(args[0]);
    
            Server.start(port, new Handler());
        }
    } 
}
