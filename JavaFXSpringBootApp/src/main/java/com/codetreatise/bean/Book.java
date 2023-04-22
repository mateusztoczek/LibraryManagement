package com.codetreatise.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mateusz Toczek & Beata Korzeniewska
 * @since 08-01-2023
 */

@Entity(name="Book")
@Table(name="Book")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idBook", updatable = false, nullable = false)
	private long idBook;
	private String title;
	private String author;
	private String print;	
	private String category;
	private int year;
	private int num_rent;

	
	public long getId() {
		return idBook;
	}

	public void setId(long idBook) {
		this.idBook = idBook;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author=author;
	}
	

	public String getPrint() {
		return print;
	}

	public void setPrint(String print) {
		this.print = print;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public int getNum_rent() {
		return num_rent;
	}

	public void setNum_rent(int num_rent) {
		this.num_rent = num_rent;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Book [id=" + idBook + ", title=" + title + ", print=" + print + ", category=" + category +  ", year=" + year + ", num_rent=" + num_rent + "]";
	}

	
}
