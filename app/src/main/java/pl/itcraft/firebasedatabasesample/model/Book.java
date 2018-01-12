package pl.itcraft.firebasedatabasesample.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Book {

	public String  title;
	public String  description;
	public Integer pages;
	public Integer quantity;
	public String  author;
	public String  cover;
	public String  house;
	public String  isbn;
	public String  img;

	public Book() {
		// Firebase requires default constructor.
	}
}
