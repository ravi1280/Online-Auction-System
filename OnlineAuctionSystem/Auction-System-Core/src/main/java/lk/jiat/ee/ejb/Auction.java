package lk.jiat.ee.ejb;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.jms.*;

@Singleton
@Startup
public class Auction {

    @Resource(lookup = "jms/MyConnectionFactory")
    private TopicConnectionFactory connectionFactory;

    @Resource(lookup = "jms/MyTopic")
    private Topic auctionTopic;

    @PostConstruct
    public void init() {
        System.out.println("AuctionManager initialized");
    }

    public void broadcastBidUpdate(String itemId, String userId, double amount) {
        try {
            TopicConnection connection = connectionFactory.createTopicConnection();
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicPublisher publisher = session.createPublisher(auctionTopic);

            MapMessage message = session.createMapMessage();
            message.setString("itemId", itemId);
            message.setString("userId", userId);
            message.setDouble("amount", amount);
            message.setLong("timestamp", System.currentTimeMillis());

            publisher.publish(message);

            publisher.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}
