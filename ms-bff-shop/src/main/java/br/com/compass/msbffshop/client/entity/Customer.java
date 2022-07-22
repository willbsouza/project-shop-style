package br.com.compass.msbffshop.client.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
}