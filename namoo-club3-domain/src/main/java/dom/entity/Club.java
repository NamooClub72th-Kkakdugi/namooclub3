package dom.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Club {
	
	private int comNo;
	private int clubNo;
	private int categoryNo;
	private String clubName;
	private String clubDes;
	private Date openDate;
	
	private List<ClubMember> members;
	
	//---------------------------
	public Club(int comNo, String clubName, String clubDes, SocialPerson user) {
		//
		this.comNo = comNo;
		this.clubName = clubName;
		this.clubDes = clubDes;
		this.members = new ArrayList<ClubMember>();

		addMember(user);
	}
	
	public int getComNo() {
		return comNo;
	}

	public void setComNo(int comNo) {
		this.comNo = comNo;
	}

	public int getClubNo() {
		return clubNo;
	}

	public void setClubNo(int clubNo) {
		this.clubNo = clubNo;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClubDes() {
		return clubDes;
	}

	public void setClubDes(String clubDes) {
		this.clubDes = clubDes;
	}
	
	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public List<ClubMember> getMembers() {
		return members;
	}

	public void setMembers(List<ClubMember> members) {
		this.members = members;
	}

	private void addMember(SocialPerson rolePerson) {
		// 
		ClubMember member = new ClubMember(clubNo, rolePerson, null);
		this.members.add(member);
		
	}
}
