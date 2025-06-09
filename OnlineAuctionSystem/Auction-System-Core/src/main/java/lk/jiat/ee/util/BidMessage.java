package lk.jiat.ee.util;

import java.io.Serializable;

public class BidMessage implements Serializable {
    private Long itemId;
    private double bidAmount;

    public BidMessage() {}

    public BidMessage(Long itemId, double bidAmount) {
        this.itemId = itemId;
        this.bidAmount = bidAmount;
    }

    public Long getItemId() { return itemId; }
    public double getBidAmount() { return bidAmount; }
}