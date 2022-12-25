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
    public void saveGame(String key, Object value) {
        storageAdapter.save(key, value);
    }

    public GameInfo loadGame(String key){
       return storageAdapter.load(key);
    }



}