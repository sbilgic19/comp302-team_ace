package Database;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
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
    private MongoCollection<Document> collection;

    public MongoDBStorageAdapter() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://AliOktay123:AliOktay123@escapfromkoc.mxq9rt9.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase db = mongoClient.getDatabase("GameFiles");
        this.collection =db.getCollection("Save");
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
        collection.replaceOne(new Document("name", key), doc);
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
            Document retrievedDoc = collection.find(query).first();
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


}