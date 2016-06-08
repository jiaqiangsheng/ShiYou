package com.jqs.example.beans;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<BookBeans> listbooBeans;
	private List<BookNovel> listnoNovels;
	public Book(List<BookBeans> listbooBeans, List<BookNovel> listnoNovels) {
		super();
		this.listbooBeans = listbooBeans;
		this.listnoNovels = listnoNovels;
	}
	public List<BookBeans> getListbooBeans() {
		return listbooBeans;
	}
	public void setListbooBeans(List<BookBeans> listbooBeans) {
		this.listbooBeans = listbooBeans;
	}
	public List<BookNovel> getListnoNovels() {
		return listnoNovels;
	}
	public void setListnoNovels(List<BookNovel> listnoNovels) {
		this.listnoNovels = listnoNovels;
	}
	
	

}
