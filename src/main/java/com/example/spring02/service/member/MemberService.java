package com.example.spring02.service.member;

import javax.servlet.http.HttpSession;

import com.example.spring02.model.member.dto.MemberDTO;

public interface MemberService {
	String loginCheck(MemberDTO dto, HttpSession session);
	void logout(HttpSession session);
}
