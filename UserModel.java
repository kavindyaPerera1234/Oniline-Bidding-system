package UserPackage;

public class UserModel {
	

		private int id;
		private String role;
		private String name;
		private String gmail;
		private String nic;
		private String address;
		private String phone;
		private String username;
		private String password;
		

		public UserModel(int id, String role, String name, String gmail, String nic, String address, String phone,String username, String password) {
			super();
			this.id = id;
			this.role = role;
			this.name = name;
			this.gmail = gmail;
			this.nic = nic;
			this.address = address;
			this.phone = phone;
			this.username = username;
			this.password = password;
		}
		
		
		public int getId() {
			return id;
		}

		public String getRole() {
			return role;
		}

		public String getName() {
			return name;
		}

		public String getGmail() {
			return gmail;
		}

		public String getNic() {
			return nic;
		}

		public String getAddress() {
			return address;
		}

		public String getPhone() {
			return phone;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setGmail(String gmail) {
			this.gmail = gmail;
		}

		public void setNic(String nic) {
			this.nic = nic;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		
		
		
		
	
		
		
		

}
