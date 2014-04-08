package dom.entity;

public class ClubKingManager {

	private int clubNo;
	private SocialPerson rolePerson;
	//----------------------------------------------------------------
	public ClubKingManager(int clubNo, SocialPerson rolePerson) {
		//
		this.rolePerson = rolePerson;
		this.clubNo = clubNo;
	}
	//--------------------------------------------------
	public String getUserId() {
		return rolePerson.getUserId();
	}

	public int getClubNo() {
		return clubNo;
	}

	public void setClubNo(int clubNo) {
		this.clubNo = clubNo;
	}
	//-------------------------------------------------------------------------
}
