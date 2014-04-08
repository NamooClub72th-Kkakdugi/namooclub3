package dom.entity;

public class ClubMember {
	
	private int clubNo;
	private SocialPerson user;
	
	//-----------------------------------------------------------------
	public ClubMember(int clubNo, SocialPerson user, Character type) {
		//
		this.clubNo = clubNo;
		this.user = user;
	}
	//-----------------------------------------------------------------
	public int getClubNo() {
		return clubNo;
	}
	public void setClubNo(int clubNo) {
		this.clubNo = clubNo;
	}
	public SocialPerson getUser() {
		return user;
	}
	public void setUser(SocialPerson user) {
		this.user = user;
	}

}
