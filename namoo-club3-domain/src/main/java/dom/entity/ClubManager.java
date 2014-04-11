package dom.entity;

public class ClubManager  {

	private int clubNo;
	private SocialPerson rolePerson;
	//----------------------------------------------------------------
	public ClubManager(int clubNo) {
		//
		this.clubNo = clubNo;
	}
	
	public ClubManager(int clubNo, SocialPerson rolePerson) {
		//
		this.rolePerson = rolePerson;
		this.clubNo = clubNo;
	}
	//--------------------------------------------------
	public String getEamil() {
		return rolePerson.getEmail();
	}

	public int getClubNo() {
		return clubNo;
	}

	public void setClubNo(int clubNo) {
		this.clubNo = clubNo;
	}
	//-------------------------------------------------------------------------
}
