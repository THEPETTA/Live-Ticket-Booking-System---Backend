package com.demo.livenation.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="concert")
public class Concert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cId;
	@NotBlank(message="Give a name to the poor concert")
	private String name;
	@Min(value = 10, message="Number of tickets cannot be less than 10")
	private Long numberOfTickets;
	@Min(value = 10, message="Number of tickets cannot be less than 10")
	private Long remainingTickets;
	@ManyToOne
	@JoinColumn(name="uId")
	private User user;
	
	@OneToMany(mappedBy = "concert", cascade = CascadeType.ALL)
	private List<BookingDetails> attendees;
	
	


	public Concert(Long cId, @NotBlank(message = "Give a name to the poor concert") String name,
			@Min(value = 10, message = "Number of tickets cannot be less than 10") Long numberOfTickets,
			@Min(value = 10, message = "Number of tickets cannot be less than 10") Long remainingTickets, User user,
			List<BookingDetails> attendees) {
		super();
		this.cId = cId;
		this.name = name;
		this.numberOfTickets = numberOfTickets;
		this.remainingTickets = remainingTickets;
		this.user = user;
		this.attendees = attendees;
	}
//	public Concert(Long cId, @NotBlank(message = "Give a name to the poor concert") String name,
//			@Min(value = 10, message = "Number of tickets cannot be less than 10") Long numberOfTickets,
//			@Min(value = 10, message = "Number of tickets cannot be less than 10") Long remainingTickets, User user) {
//		super();
//		this.cId = cId;
//		this.name = name;
//		this.numberOfTickets = numberOfTickets;
//		this.remainingTickets = remainingTickets;
//		this.user = user;
//	}




	public Long getcId() {
		return cId;
	}




	public void setcId(Long cId) {
		this.cId = cId;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public Long getNumberOfTickets() {
		return numberOfTickets;
	}




	public void setNumberOfTickets(Long numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}




	public Long getRemainingTickets() {
		return remainingTickets;
	}




	public void setRemainingTickets(Long remainingTickets) {
		this.remainingTickets = remainingTickets;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public List<BookingDetails> getAttendees() {
		return attendees;
	}




	public void setAttendees(List<BookingDetails> attendees) {
		this.attendees = attendees;
	}




	public Concert() {
		
	}
	
}
