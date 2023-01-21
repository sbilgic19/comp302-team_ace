package Database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import domain.GameInfo;
import org.bson.BsonBinary;
import org.bson.BsonBinarySubType;
import org.bson.Document;
import org.bson.types.Binary;

import java.io.*;

public class MongoDBStorageAdapter implements IDataStorageAdapter {
    private final MongoCollection<Document> collectionGameInfo;
    private final MongoCollection<Document> collectionLogin;

    public MongoDBStorageAdapter() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://AliOktay123:AliOktay123@escapfromkoc.mxq9rt9.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase db = mongoClient.getDatabase("GameFiles");
        this.collectionGameInfo =db.getCollection("Save");
        MongoDatabase dbLogin = mongoClient.getDatabase("Users");
        this.collectionLogin =dbLogin.getCollection("loginInfo");
    }


    /**
     * Saves the given object as gameInfo an key as the name of the save..
     *
     * @param key name of the save.
     * @param value GameInfo.
     */

    @Override
    public void save(String key, Object value) {
        byte[] bytes = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(value);
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a BsonBinary object to store the serialized object
        BsonBinary binary = new BsonBinary(BsonBinarySubType.BINARY, bytes);

        // Create a new Document to store the object
        Document doc = new Document("name", key)
                .append("GameInfo",binary);
        // Insert the Document into the collection
        collectionGameInfo.replaceOne(new Document("name", key), doc);
    }

    /**
     * Loads data from a MongoDB server with the given key.
     *
     * @requires key != null
     * @modifies this
     * @effects Loads the data associated with the given key from the MongoDB server and stores it in this object.
     *          If the key does not exist, this method does nothing.
     * @param key The key of the data to load.
     * @return GameInfo that loaded from the given key.
     */
    @Override
    public GameInfo load(String key) {
        // To retrieve the object, you can use the following code:
        byte[] retrievedBytes = null;
        try
        {
            Document query = new Document("name", key);
            Document retrievedDoc = collectionGameInfo.find(query).first();
            Binary retrievedBinary = retrievedDoc.get("GameInfo", Binary.class);
            retrievedBytes = retrievedBinary.getData();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        // Deserialize the object from the byte array
        GameInfo retrievedGameInfo = null;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(retrievedBytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            retrievedGameInfo = (GameInfo) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }



        return retrievedGameInfo;

    }

    @Override
    public boolean login(String userName, String password) {
        try
        {
            Document query = new Document("userName", userName).append("password", password);
            boolean isContain = collectionLogin.find(query).first() != null;
            System.out.println("Successful");
            return isContain;
        } catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean signUp(String userName, String password) {
        try
        {
            Document doc = new Document("userName", userName)
              .append("password",password);
        // Insert the Document into the collection
        collectionLogin.insertOne(doc);
        return true;
        }
        catch (Exception e){
            return false;
        }
    }
}