package com.namoo.club.web.controller.community.pres;

import java.util.ArrayList;
import java.util.List;

import dom.entity.CommunityManager;
import dom.entity.CommunityMember;


public class PresCommunity {
	//
	
	private List<CommunityMember> members;
	private String loginEmail;
	private CommunityManager manager;
	
	
	public PresCommunity() {
		//
		this.members = new ArrayList<CommunityMember>();
	}

	public List<CommunityMember> getMembers() {
		return members;
	}

	public void setMembers(List<CommunityMember> members) {
		this.members = members;
	}

	public CommunityManager getManager() {
		return manager;
	}

	public void setManager(CommunityManager manager) {
		this.manager = manager;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}
	
	public boolean isManager() {
		//
		if (loginEmail.equals(manager.getEmail())) {
			return true; 
		}
		return false;
	}
}
