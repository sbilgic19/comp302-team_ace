package Database;

import domain.GameInfo;

public class Client {
    IDataStorageAdapter storageAdapter;

    public Client(String serviceUsed) {

        if (serviceUsed == "MongoDB")
            storageAdapter= new MongoDBStorageAdapter();
        else
            storageAdapter= null;
    }
    public boolean login(String userName, String password) {return storageAdapter.login(userName, password);}
    public boolean signUp(String userName, String password, String checkPassword) {
        if (password.equals(checkPassword) && userName.length() > 6 && password.length() > 6){
            return storageAdapter.signUp(userName, password);}
        else {
            return false;
        }
    }
    public void saveGame(String key, Object value) {
        storageAdapter.save(key, value);
    }

    public GameInfo loadGame(String key){
       return storageAdapter.load(key);
    }



}