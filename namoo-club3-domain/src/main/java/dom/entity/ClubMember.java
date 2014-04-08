package dom.entity;

public class ClubMember {
	
	private int clubNo;
	private SocialPerson rolePerson;
	
	//--------------------------------------------------------------------
	
	public ClubMember(int clubNo, SocialPerson rolePerson) {
		//
		this.clubNo = clubNo;
		this.rolePerson = rolePerson;
	}
	//---------------------------------------------------------------------------
	
	public String getName() {
		return rolePerson.getName();
	}
	
	public String getUserId() {
		return rolePerson.getUserId();
	}

	public int getClubNo() {
		return clubNo;
	}

	public void setClubNo(int clubNo) {
		this.clubNo = clubNo;
	}

	public SocialPerson getRolePerson() {
		return rolePerson;
	}

	public void setRolePerson(SocialPerson rolePerson) {
		this.rolePerson = rolePerson;
	}
	
	
}
