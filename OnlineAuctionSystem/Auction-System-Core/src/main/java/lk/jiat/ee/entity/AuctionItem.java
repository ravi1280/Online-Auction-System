package lk.jiat.ee.entity;


import java.io.Serializable;
import java.util.Date;

public class AuctionItem implements Serializable {
    private Long id;
    private String name;
    private double currentBid;
    private Date endTime;

    public AuctionItem() {}

    public AuctionItem(Long id, String name, double currentBid, Date endTime) {
        this.id = id;
        this.name = name;
        this.currentBid = currentBid;
        this.endTime = endTime;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getCurrentBid() { return currentBid; }
    public Date getEndTime() { return endTime; }

    public void setCurrentBid(double bid) { this.currentBid = bid; }
}

