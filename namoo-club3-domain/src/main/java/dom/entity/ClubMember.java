package dom.entity;

public class ClubMember {
	
	private int clubNo;
	private SocialPerson user;
	private Character type;
	//-----------------------------------------------------------------
	public ClubMember(int clubNo, SocialPerson user, Character type) {
		//
		this.clubNo = clubNo;
		this.user = user;
		this.type = type;
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
	public Character getType() {
		return type;
	}
	public void setType(Character type) {
		this.type = type;
	}
}
