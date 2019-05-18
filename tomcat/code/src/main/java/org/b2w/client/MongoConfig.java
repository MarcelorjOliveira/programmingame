package org.b2w.client;

/*import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

//extends AbstractMongoConfiguration 

@Configuration
@EnableMongoRepositories(basePackages = "org.b2w.client.repository")
*/
public class MongoConfig{
/*
	@Value("${mongo.host}")
    private  String host;

    @Value("#{new Integer('${mongo.port}')}")
    private  Integer port;

    @Value("${mongo.database}")
    private  String database;

    @Value("${mongo.username}")
    private  String username;

    @Value("${mongo.password}")
    private  String password;

    public @Bean MongoClientFactoryBean mongoDbFactory() throws Exception {
        MongoClientFactoryBean clientFactoryBean = new MongoClientFactoryBean();
        clientFactoryBean.setHost(host);
        clientFactoryBean.setPort(port);
        MongoCredential credential = MongoCredential.createScramSha1Credential(username, database, password.toCharArray());
        clientFactoryBean.setCredentials(new MongoCredential[]{credential});
        return clientFactoryBean;
    }

    public @Bean MongoTemplate mongoTemplate(MongoClient mongo) throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongo, database);
        return mongoTemplate;

    }
    
    /*
	
	
	@Override
	public MongoClient mongoClient() {
		return new MongoClient("172.20.0.2", 27017);
	}

	@Override
	protected String getDatabaseName() {
		return "series";
	}
*/
  
}