package com.zenith.templates;

public class GetAdTemplate extends PostTemplate{
	private int ad_id;
	private String image;
	private String url;
	public GetAdTemplate(int ad_id, String image, String url) {
		super();
		this.ad_id = ad_id;
		this.image = image;
		this.url = url;
	}
	public int getAd_id() {
		return ad_id;
	}
	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
