package com.demo.livenation.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="booking")
public class BookingDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bId;
	@NotNull(message="What is the seat number")
	private Long seatNo;
	
	@ManyToOne
	@JoinColumn(name="uId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "cId")
	private Concert concert;
	
	
	
	@Override
	public String toString() {
		return "BookingDetails [bId=" + bId + ", seatNo=" + seatNo + ", user=" + user + ", concert=" + concert + "]";
	}
	public BookingDetails() {
		this.user = null;
	}
	public Long getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(Long seatNo) {
		this.seatNo = seatNo;
	}
	public Long getbId() {
		return bId;
	}
	public void setbId(Long bId) {
		this.bId = bId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Concert getConcert() {
		return concert;
	}
	public void setConcert(Concert concert) {
		this.concert = concert;
	}
	public BookingDetails(Long bId, @NotNull(message = "What is the seat number") Long seatNo, User user,
			Concert concert) {
		super();
		this.bId = bId;
		this.seatNo = seatNo;
		this.user = user;
		this.concert = concert;
	}
	
}
