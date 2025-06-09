package lk.jiat.ee.ejb;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class Bid {
    @EJB
    private Auction auctionManager;

    public void processBid(String itemId, String userId, double amount) {

        auctionManager.broadcastBidUpdate(itemId, userId, amount);
    }
}
