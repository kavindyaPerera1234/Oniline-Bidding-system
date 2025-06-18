package delivery;

public class Delivery {
    private int id;
    private int bidId;
    private String winnerName;
    private String winnerAddress;
    private String deliveryCompanyName;
    
    public Delivery() {
    }
    
    public Delivery(int id, int bidId, String winnerName, String winnerAddress, String deliveryCompanyName) {
        this.id = id;
        this.bidId = bidId;
        this.winnerName = winnerName;
        this.winnerAddress = winnerAddress;
        this.deliveryCompanyName = deliveryCompanyName;
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getBidId() {
        return bidId;
    }
    
    public void setBidId(int bidId) {
        this.bidId = bidId;
    }
    
    public String getWinnerName() {
        return winnerName;
    }
    
    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }
    
    public String getWinnerAddress() {
        return winnerAddress;
    }
    
    public void setWinnerAddress(String winnerAddress) {
        this.winnerAddress = winnerAddress;
    }
    
    public String getDeliveryCompanyName() {
        return deliveryCompanyName;
    }
    
    public void setDeliveryCompanyName(String deliveryCompanyName) {
        this.deliveryCompanyName = deliveryCompanyName;
    }
} 