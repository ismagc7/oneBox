package com.example.onebox.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true)
	@ManyToMany
	private List<Product> products;

	private Timestamp createdAt;

	public Cart(){
		this.createdAt = new Timestamp(System.currentTimeMillis());
	}
}
