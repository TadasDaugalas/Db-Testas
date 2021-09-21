package Service;

import Data.User;
import client.provider.MongoClientProvider;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;


import java.util.Iterator;

public class UserService {
    MongoClient client = MongoClientProvider.getMongoClientWithCodec();
    MongoDatabase database = client.getDatabase("Transactions");
    MongoCollection<User> userMongoCollection = database.getCollection("User", User.class);

    public void addUser(User user) {
        userMongoCollection.insertOne(user);
    }

    public boolean checkUser(String name) {
        Iterator<User> users = userMongoCollection.find(Filters.eq("name", name)).iterator();
        if (users.hasNext()) {
            return true;
        }
        return false;
    }

    public void sendMoney(String sender, String reciever,int amount) {
        User user1 = userMongoCollection.find(Filters.eq("name", sender)).first();
        User user2 = userMongoCollection.find(Filters.eq("name", reciever)).first();
        if (user1.getBalance() > amount) {
            userMongoCollection.updateOne(Filters.eq("name", sender),
                    Updates.set("balance", (user1.getBalance() - amount)));
            userMongoCollection.updateOne(Filters.eq("name", reciever),
                    Updates.set("balance", ((user2.getBalance()+amount))));
        } else {
            System.out.println("Sender balance is too low");
        }
    }
    public void getInfo(String reciever){
        User user = userMongoCollection.find(Filters.eq("name", reciever)).first();
        System.out.println(user);

    }
}
