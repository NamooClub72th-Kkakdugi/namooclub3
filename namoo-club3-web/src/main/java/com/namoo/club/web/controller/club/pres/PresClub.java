package com.namoo.club.web.controller.club.pres;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dom.entity.Club;
import dom.entity.ClubKingManager;
import dom.entity.ClubManager;
import dom.entity.ClubMember;


public class PresClub {
	//
	private Club club;
	private List<ClubMember> members;
	private String loginEmail;
	private ClubManager manager;
	private ClubKingManager kingManager;

	//--------------------------------------------------------------------------
	
	
	public PresClub(Club club){
		//
		this.club = club;
		this.members = new ArrayList<ClubMember>();
	}
	
	//--------------------------------------------------------------------------

	public Club getClub() {
		return club;
	}

	public String getName() {
		return club.getName();
	}

	public int getclubNo() {
		return club.getClubNo();
	}
	
	
	public Date getOpenDate() {
		return club.getOpenDate();
	}
	
	
	public String getDescription() {
		return club.getDescription();
	}
	
	
	public void setClub(Club club) {
		this.club = club;
	}
	
	public List<ClubMember> getMembers() {
		return members;
	}
	
	public void setMembers(List<ClubMember> members) {
		this.members = members;
	}
	
	public ClubManager getManager() {
		return manager;
	}
	
	public void setManager(ClubManager manager) {
		this.manager = manager;
	}

	
	public ClubKingManager getKingManager() {
		return kingManager;
	}

	public void setKingManager(ClubKingManager kingManager) {
		this.kingManager = kingManager;
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
	
	public boolean isKingManager() {
		//
		if (kingManager != null && loginEmail.equals(kingManager.getEmail())) {
			return true;
		}
		return false;
	}
}
