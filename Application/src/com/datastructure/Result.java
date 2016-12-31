package com.datastructure;

import java.util.HashSet;

public class Result {
	HashSet<String> websiteOfGet;
	HashSet<String> websiteOfPost;
	HashSet<String> websiteOfAdmin;
	public HashSet<String> getWebsiteOfAdmin() {
		return websiteOfAdmin;
	}
	public void setWebsiteOfAdmin(HashSet<String> websiteOfAdmin) {
		this.websiteOfAdmin = websiteOfAdmin;
	}
	HashSet<String> otherWebsite;
	public HashSet<String> getWebsiteOfGet() {
		return websiteOfGet;
	}
	public void setWebsiteOfGet(HashSet<String> websiteOfGet) {
		this.websiteOfGet = websiteOfGet;
	}
	public HashSet<String> getWebsiteOfPost() {
		return websiteOfPost;
	}
	public void setWebsiteOfPost(HashSet<String> websiteOfPost) {
		this.websiteOfPost = websiteOfPost;
	}
	public HashSet<String> getOtherWebsite() {
		return otherWebsite;
	}
	public void setOtherWebsite(HashSet<String> otherWebsite) {
		this.otherWebsite = otherWebsite;
	}
}
