package com.demo.livenation.entities;

import jakarta.persistence.GenerationType;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long uId;
	
	@NotBlank(message="Name is a mandatory")
	private String name;
	
	@NotNull(message="Email is a mandatory")
	@Email(message="Email should be valid")
	@Size(max=255, message="Email should be 255 charecters long")
	private String email;
	
	@NotNull(message="Password is a mandatory")
	@Size(min=8,max=15, message="Password should be 8-15 charecters")
	private String password;
	@NotNull(message = "Artist or Not")
	private boolean isArtist;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Concert> concerts;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<BookingDetails> bookingHistory;
	
	
	
	@Override
	public String toString() {
		return "User [uId=" + uId + ", name=" + name + ", email=" + email + ", password=" + password + ", isArtist="
				+ isArtist + "]";
	}



	public Long getuId() {
		return uId;
	}



	public void setuId(Long uId) {
		this.uId = uId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isArtist() {
		return isArtist;
	}



	public void setArtist(boolean isArtist) {
		this.isArtist = isArtist;
	}



	public List<Concert> getConcerts() {
		return concerts;
	}



	public void setConcerts(List<Concert> concerts) {
		this.concerts = concerts;
	}



	public List<BookingDetails> getBookingHistory() {
		return bookingHistory;
	}



	public void setBookingHistory(List<BookingDetails> bookingHistory) {
		this.bookingHistory = bookingHistory;
	}
	
	
	public User(Long uId, @NotBlank(message = "Name is a mandatory") String name,
			@NotNull(message = "Email is a mandatory") @Email(message = "Email should be valid") @Size(max = 255, message = "Email should be 255 charecters long") String email,
			@NotNull(message = "Password is a mandatory") @Size(min = 8, max = 15, message = "Password should be 8-15 charecters") String password,
			@NotNull(message = "Artist or Not") boolean isArtist, List<Concert> concerts,
			List<BookingDetails> bookingHistory) {
		super();
		this.uId = uId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.isArtist = isArtist;
		this.concerts = concerts;
		this.bookingHistory = bookingHistory;
	}
//	public User(Long uId, @NotBlank(message = "Name is a mandatory") String name,
//			@NotNull(message = "Email is a mandatory") @Email(message = "Email should be valid") @Size(max = 255, message = "Email should be 255 charecters long") String email,
//			@NotNull(message = "Password is a mandatory") @Size(min = 8, max = 15, message = "Password should be 8-15 charecters") String password,
//			@NotNull(message = "Artist or Not") boolean isArtist, List<Concert> concerts) {
//		super();
//		this.uId = uId;
//		this.name = name;
//		this.email = email;
//		this.password = password;
//		this.isArtist = isArtist;
//		this.concerts = concerts;
//	}



	public User() {
		
	}	
	
}
