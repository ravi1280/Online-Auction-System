package lk.jiat.ee.message;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.*;
import lk.jiat.ee.session.AuctionBean;
import lk.jiat.ee.util.BidMessage;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(
                        propertyName = "destinationLookup",
                        propertyValue = "jms/bidQueue"),
                @ActivationConfigProperty(
                        propertyName = "destinationType",
                        propertyValue = "jakarta.jms.Queue")
        }
)
public class AuctionBidMDB implements MessageListener {

    @Inject
    private AuctionBean auctionBean;

    @Override
    public void onMessage(Message message) {

        try {
            ObjectMessage objMsg = (ObjectMessage) message;
            BidMessage bidMsg = (BidMessage) objMsg.getObject();
            auctionBean.updateBid(bidMsg.getItemId(), bidMsg.getBidAmount());
            System.out.println("bid updated 2 "+bidMsg.getItemId()+" "+bidMsg.getBidAmount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}