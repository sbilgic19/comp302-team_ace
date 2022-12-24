package Database;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.BsonBinary;
import org.bson.BsonBinarySubType;
import org.bson.Document;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MongoDBStorageAdapter implements IDataStorageAdapter {
    private MongoCollection<Document> collection;

    public MongoDBStorageAdapter() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://AliOktay123:AliOktay123@escapfromkoc.mxq9rt9.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase db = mongoClient.getDatabase("GameFiles");
        this.collection =db.getCollection("Save");
    }

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
        Document doc = new Document(key, binary);

        // Insert the Document into the collection
        collection.insertOne(doc);
    }
}