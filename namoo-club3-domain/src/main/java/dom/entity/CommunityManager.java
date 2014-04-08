package dom.entity;


public class CommunityManager {
	
	private int communityNo;
	private SocialPerson rolePerson;

	//--------------------------------------------------------------------------
	// constructor
	
	/**
	 * 
	 * @param rolePerson
	 */
	public CommunityManager(int communityNo, SocialPerson rolePerson){
		//
		this.communityNo = communityNo;
		this.rolePerson = rolePerson;
	}
	
	//--------------------------------------------------------------------------
	// getter/setter
	
	public int getCommunityNo() {
		return communityNo;
	}

	public String getUserId() {
		// 
		return rolePerson.getUserId();
	}

}