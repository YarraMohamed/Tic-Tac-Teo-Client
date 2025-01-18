package Utils;

public class SharedData {
    private static SharedData instance;
    private String serverIp;
    private int playerID;

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    private SharedData() {
    }
    
    public static SharedData getInstance() {
    if (instance == null) {
        synchronized (SharedData.class) {
            if (instance == null) {
                instance = new SharedData();
            }
        }
    }
    return instance;
}

    public String getServerIp() {
        return serverIp;
    }
    
    public static boolean isValidIP(String ip) {
    return ip.matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");
   }
    
    public void setServerIp(String serverIp) {
        if(isValidIP(serverIp)){
            this.serverIp = serverIp;
            System.out.println(serverIp);
        }
    } 
}

   

