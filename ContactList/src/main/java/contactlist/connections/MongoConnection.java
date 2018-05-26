package contactlist.connections;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import com.mongodb.*;

public class MongoConnection {

    private static MongoConnection instance;

    /**
     * ContactList configuration from properties file
     */
    private String configFileName;
    private Properties configuration;

    /**
     * info for Mongo database on localhost
     */
    private String host;
    private int port;
    private String dataBase;
    private String login;
    private String password;

    public String getDataBase() {
        return dataBase;
    }

    /**
     * private constructor to exclude creation of instance
     */
    private MongoConnection(){}

    /**
     * @return instance of PostgreSQLConnection if it exists or create new one
     */
    public static MongoConnection getInstance() {
        if (instance == null) {
            MongoConnection.instance = new MongoConnection();
        }
        return instance;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    /**
     * setting info for connection to DB
     */
    public void setConnectionInfo() {
        configuration = new Properties();
        try {
            configuration.load(new FileReader(configFileName));

            this.host = configuration.getProperty("mongo.host");
            this.port = Integer.parseInt(configuration.getProperty("mongo.port"));
            this.dataBase = configuration.getProperty("mongo.dataBase");
            this.login = configuration.getProperty("mongo.login");
            this.password = configuration.getProperty("mongo.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setConnectionInfo(String host, int port, String dataBase, String login, String password) {
        this.host = host;
        this.port = port;
        this.dataBase = dataBase;
        this.login = login;
        this.password = password;
    }

    /**
     * create connection to DB
     */
    public Mongo getConnectionToDB() {
        Mongo mongo = new Mongo(host, port);
        return mongo;
    }
}
