package com.example.spring02.model.shop.dao;

import java.util.List;

import com.example.spring02.model.shop.dto.CartDTO;

public interface CartDAO {
	List<CartDTO> cartMoney();
	void insert(CartDTO dto);
	List<CartDTO> listCart(String userid);
	void delete(int cart_id);
	void deleteAll(String userid);
	int sumMoney(String userid);
	void modifyCart(CartDTO dto);
}
