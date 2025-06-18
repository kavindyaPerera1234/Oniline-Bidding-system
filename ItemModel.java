package seller;


public class ItemModel {
	
	
	private int item_id;
	private String seller_name;
	private String item_title;
	private String category;
	private String price;
	private String start_time;
	private String end_time;
	private String description;
	
	public ItemModel(int item_id, String seller_name, String item_title, String category, String price,
			String start_time, String end_time, String description) {
		super();
		this.item_id = item_id;
		this.seller_name = seller_name;
		this.item_title = item_title;
		this.category = category;
		this.price = price;
		this.start_time = start_time;
		this.end_time = end_time;
		this.description = description;
	}

	public int getItem_id() {
		return item_id;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public String getItem_title() {
		return item_title;
	}

	public String getCategory() {
		return category;
	}

	public String getPrice() {
		return price;
	}

	public String getStart_time() {
		return start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public String getDescription() {
		return description;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public void setItem_title(String item_title) {
		this.item_title = item_title;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}