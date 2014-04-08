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
	
	public String getName() {
		//
		return rolePerson.getName();
	}

	public String getUserId() {
		// 
		return rolePerson.getUserId();
	}

	public int getCommunityNo() {
		//
		return 0;
	}

	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}
	
	

}