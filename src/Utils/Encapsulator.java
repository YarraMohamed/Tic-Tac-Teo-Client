package Utils;

public class Encapsulator {
    
    public static String encapsulate(String requestType, String ...args){
        String message = requestType;
        message = message.concat(":");
        for(int i=0;i<args.length;i++){
            message = message.concat(args[i]);
            if(i<args.length-1){
                message=message.concat(":");
                
            }
        }
        return message;
    }  
}
