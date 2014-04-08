package dom.entity;

public class CommunityMember {

	private int comNo;
	private SocialPerson user;
	private Character type;
	
	//-----------------------------------------------------------------
	public CommunityMember(int comNo, SocialPerson user, Character type) {
		//
		this.comNo = comNo;
		this.user = user;
		this.type = type;
	}

	//--------------------------------------------------------------------

	public int getComNo() {
		return comNo;
	}
	
	public void setComNo(int comNo) {
		this.comNo = comNo;
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
