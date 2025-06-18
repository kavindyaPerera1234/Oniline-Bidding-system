package onlineBiddingPackage;

public class BiddingModel {
      private int bid_id;
      private String buyer_name;
      private String category;
      private String bid_date;
      private double amount;
      private String email;
      private String phone;
      
   public BiddingModel(int bid_id, String buyer_name, String category, String bid_date, double amount, String email, String phone) {
		super();
		this.bid_id = bid_id;
		this.buyer_name = buyer_name;
		this.category = category;
		this.bid_date = bid_date;
		this.amount = amount;
		this.email = email;
		this.phone = phone;
	}

public int getBid_id() {
	return bid_id;
}

public void setBid_id(int buyer_id) {
	this.bid_id = buyer_id;
}

public String getBuyer_name() {
	return buyer_name;
}

public void setBuyer_name(String buyer_name) {
	this.buyer_name = buyer_name;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public String getBid_date() {
	return bid_date;
}

public void setBid_date(String bid_date) {
	this.bid_date = bid_date;
}

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}



   
}
