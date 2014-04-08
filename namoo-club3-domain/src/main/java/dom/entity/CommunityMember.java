package dom.entity;

public class CommunityMember {

	private int comNo;
	private SocialPerson user;
	
	//-----------------------------------------------------------------
	public CommunityMember(int comNo, SocialPerson user) {
		//
		this.comNo = comNo;
		this.user = user;
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

}
