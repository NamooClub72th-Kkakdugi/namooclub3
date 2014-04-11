package com.namoo.club.web.controller.community.pres;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dom.entity.Club;
import dom.entity.Community;
import dom.entity.CommunityManager;
import dom.entity.CommunityMember;


public class PresCommunity {
	//
	private Community community;
	private List<CommunityMember> members;
	private String loginEmail;
	private CommunityManager manager;
	private List<Club> clubs;

	//--------------------------------------------------------------------------
	
	
	public PresCommunity(Community community) {
		//
		this.community = community;
		this.members = new ArrayList<CommunityMember>();
		this.clubs = new ArrayList<Club>();
	}
	
	//--------------------------------------------------------------------------

	public int getCommunityNo() {
		return community.getComNo();
	}

	public String getName() {
		return community.getName();
	}
	
	public Date getOpenDate() {
		return community.getOpenDate();
	}
	
	public List<Club> getClubs() {
		return clubs;
	}
	
	public String getDescription() {
		return community.getDescription();
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
	
	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}

	//------------------------------------------------------------------------
	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}
	
	public boolean isManager() {
		//
		if (manager != null && loginEmail.equals(manager.getEmail())) {
			return true; 
		}
		return false;
	}
}
