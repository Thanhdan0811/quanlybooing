package com.example.qLyDatBan.quanLyDatBan.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {
	private int statusCode;
	private String message;
	private T data; // This can be null if no data is returned

	// Constructor for responses without data
	public Response(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
}
