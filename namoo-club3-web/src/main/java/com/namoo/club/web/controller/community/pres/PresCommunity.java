package com.namoo.club.web.controller.community.pres;

import java.util.ArrayList;
import java.util.List;

import dom.entity.CommunityManager;
import dom.entity.CommunityMember;


public class PresCommunity {
	//
	
	private List<CommunityMember> members;
	private CommunityManager manager;
	
	public PresCommunity() {
		//
		this.members = new ArrayList<CommunityMember>();
		this.manager = manager;
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
	
	

}
