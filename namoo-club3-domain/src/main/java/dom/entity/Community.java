package dom.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Community {
	
	private int comNo;
	private String name;
	private String description;
	private Date openDate;
	
	private CommunityManager manager;
	private List<CommunityMember> members;
	private List<Club> clubs;
	private List<ClubCategory> categories;
	
	//----------------------------------------------------------------
	public Community(String name, String description) {
		//
		this.name = name;
		this.description = description;
		this.members = new ArrayList<CommunityMember>();
		this.categories = new ArrayList<ClubCategory>();
		this.clubs = new ArrayList<Club>();
	}
	
	public Community(String name, String description, SocialPerson user) {
		//
		this.name = name;
		this.description = description;
		this.members = new ArrayList<CommunityMember>();
		this.categories = new ArrayList<ClubCategory>();
		this.clubs = new ArrayList<Club>();
		
		setManager(user);
		addMember(user);
	}

	//-------------------------------------------------------------------
	public int getComNo() {
		return comNo;
	}
	
	public void setComNo(int comNo) {
		this.comNo = comNo;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getOpenDate() {
		return openDate;
	}
	
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	public List<CommunityMember> getMembers() {
		return members;
	}
	
	public void setMembers(List<CommunityMember> members) {
		this.members = members;
	}
	
	public List<Club> getClubs() {
		return clubs;
	}
	
	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}
	
	public CommunityManager getManager() {
		return manager;
	}
	
	public void setManager(CommunityManager manager) {
		this.manager = manager;
	}
	
	public List<ClubCategory> getCategories() {
		return categories;
	}
	
	public void setCategories(List<ClubCategory> categories) {
		this.categories = categories;
	}
	
	
//-----------------------------------------------------------------------------

	public void setManager(SocialPerson user) {
		//
		CommunityManager manager = new CommunityManager(comNo, user);
		this.manager = manager;
	}
	
	public void addMember(SocialPerson user){
		//
		CommunityMember member = new CommunityMember(comNo, user);
		this.members.add(member);
	}
	
	public void addCategory(ClubCategory category) {
		//
		if (this.categories == null) {
			this.categories = new ArrayList<ClubCategory>();
		}
		this.categories.add(category);
	}
	
	public CommunityMember findMember(String email) {
		//
		for (CommunityMember member : members) {
			if(member.getEmail().equals(email)) {
				return member;
			};
		}
		return null;
	}
	
	public void removeMember(String email) {
		// 
		CommunityMember found = null;
		for (CommunityMember member : members) {
			if (member.getEmail().equals(email)) {
				found = member;
			}
		}
		if (found != null) {
			members.remove(found);
		}
	}
	
	public void removeClub(int clubNo) {
		//
		Club found = null;
		for (Club club : clubs) {
			if (club.getClubNo() == clubNo) {
				found = club;
			}
		}
		if (found != null) {
			clubs.remove(found);
		}
	}
}
