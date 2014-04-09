package com.namoo.club.dao;

import java.util.List;

import dom.entity.CommunityManager;
import dom.entity.CommunityMember;

public interface MemberDao {
	//
	//community
	CommunityMember addCommunityMember(int comNo, CommunityMember comMember);
	CommunityManager addCommunityManager(int comNo, CommunityManager comManager);
	CommunityMember readCommunityMember(int comNo, String email);
	List<CommunityMember> readAllCommunityMember(int comNo);
	CommunityManager readCommunityManager(int comNo);
	void deleteAllComMember(int comNo);
	void deleteAllComManager(int comNo);
	void deleteCommuninyMember(int comNo, String email);
	void deleteCommunityManager(int comNo, String email);
}
