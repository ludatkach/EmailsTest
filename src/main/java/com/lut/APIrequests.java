package com.lut;

public class APIrequests {
	
	public APIrequests getCatImage() {
		return this;
	}
	
	public APIrequests getDogImage() {
		return this;
	}
	
	public APIrequests getFoxImage() {
		//APIrequests foxUrl = new APIrequests();
		//return foxUrl;
		return this;
	}

	public static void main(String[] args) {
		
		APIrequests request = new APIrequests();
		request.getCatImage();
		request.getDogImage();
		request.getFoxImage();
	}

}
