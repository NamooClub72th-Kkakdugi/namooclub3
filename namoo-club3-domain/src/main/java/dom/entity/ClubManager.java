package dom.entity;


public class ClubManager  {

	private int clubNo;
	private SocialPerson rolePerson;
	private String userId;
	
	//----------------------------------------------------------------
	public ClubManager( SocialPerson rolePerson) {
		//
		this.rolePerson = rolePerson;
		this.clubNo = clubNo;
		this.userId = userId;
	}

	//--------------------------------------------------
	public String getId() {
		return rolePerson.getUserId();
	}

	public int getClubNo() {
		return clubNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setClubNo(int clubNo) {
		this.clubNo = clubNo;
	}
	
	
	//-------------------------------------------------------------------------



}
