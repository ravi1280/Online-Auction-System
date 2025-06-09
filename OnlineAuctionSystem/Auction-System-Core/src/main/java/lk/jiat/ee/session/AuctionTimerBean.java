package lk.jiat.ee.session;



import lk.jiat.ee.entity.AuctionItem;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

@Singleton
@Startup
public class AuctionTimerBean {

    @Inject
    private AuctionBean auctionBean;

    @Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
    public void checkAuctionEndTime() {
        System.out.println("⏰ AuctionTimerBean running...");

        Map<Long, AuctionItem> items = auctionBean.getAuctionItemsMap();
        Iterator<Map.Entry<Long, AuctionItem>> iterator = items.entrySet().iterator();
        Date now = new Date();

        while (iterator.hasNext()) {
            Map.Entry<Long, AuctionItem> entry = iterator.next();
            AuctionItem item = entry.getValue();

            if (item.getEndTime().before(now)) {
                System.out.println("✅ Auction ended for item: " + item.getName());
                iterator.remove();
            }
        }
    }
}
