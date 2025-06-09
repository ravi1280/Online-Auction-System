package lk.jiat.ee.session;


import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import lk.jiat.ee.entity.AuctionItem;
import jakarta.ejb.Singleton;
import java.util.*;

@Singleton
public class AuctionBean {
    @Lock(LockType.WRITE)
    public void updateBid(Long id, double bid) {
        AuctionItem item = auctionItems.get(id);
        if (item != null && bid > item.getCurrentBid()) {
            item.setCurrentBid(bid);
        }
    }
    public AuctionItem findById(Long id) {
        return auctionItems.get(id);
    }
    public Map<Long, AuctionItem> getAuctionItemsMap() {
        return auctionItems;
    }

    private final Map<Long, AuctionItem> auctionItems = new HashMap<>();

    public List<AuctionItem> getAllItems() {
        return new ArrayList<>(auctionItems.values());
    }

    public AuctionBean() {
        auctionItems.put(1L, new AuctionItem(1L, "Apple iPhone", 200.0, new Date(System.currentTimeMillis() + 60000)));
        auctionItems.put(2L, new AuctionItem(2L, "Smart TV", 150.0, new Date(System.currentTimeMillis() + 120000)));
        auctionItems.put(3L, new AuctionItem(3L, "Laptop", 300.0, new Date(System.currentTimeMillis() + 180000)));
    }

}
