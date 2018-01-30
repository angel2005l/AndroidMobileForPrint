package com.icss.entity;

import java.io.Serializable;

public class SpinnerKeyValue<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2863097323348736362L;

	private String key;
	private T value;

	public SpinnerKeyValue(String key, T value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public T getValue() {
		return value;
	}

	@SuppressWarnings("unchecked")
	public void setValue(String value) {
		this.value = (T) value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return key;
	}
}
