package dom.entity;

public class CommunityMember {

	private String comName;
	private SocialPerson user;
	
	//-----------------------------------------------------------------
	public CommunityMember(String comName, SocialPerson user) {
		//
		this.comName = comName;
		this.user = user;
	}

	//--------------------------------------------------------------------

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public SocialPerson getUser() {
		return user;
	}

	public void setUser(SocialPerson user) {
		this.user = user;
	}
}
