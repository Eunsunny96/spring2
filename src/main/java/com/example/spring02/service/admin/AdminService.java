package com.example.spring02.service.admin;

import com.example.spring02.model.member.dto.MemberDTO;

public interface AdminService {
	String loginCheck(MemberDTO dto);
}
