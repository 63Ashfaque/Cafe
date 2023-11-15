package com.ashfaque.cafe.model;

public class OrderModelClass {

	private int orderId;
	private int orderTableId;
	private int orderMenuId;
	private int orderQuantity;
	private int orderStatus;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderTableId() {
		return orderTableId;
	}

	public void setOrderTableId(int orderTableId) {
		this.orderTableId = orderTableId;
	}

	public int getOrderMenuId() {
		return orderMenuId;
	}

	public void setOrderMenuId(int orderMenuId) {
		this.orderMenuId = orderMenuId;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
}