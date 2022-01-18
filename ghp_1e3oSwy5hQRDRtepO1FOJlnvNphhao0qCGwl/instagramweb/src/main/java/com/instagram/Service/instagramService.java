package com.instagram.Service;

import java.util.*;

import com.instagram.DAO.instagramDAOInterface;
import com.instagram.Entity.instagramImage;
import com.instagram.Entity.instagramUser;
import com.instagram.Utiler.DAOFactory;

public class instagramService implements instagramServiceInterface {

	
	private instagramDAOInterface id;
	
	public instagramService()
	{
		id=DAOFactory.createObject();
	}

	
	
	
	public int createProfileService(instagramUser iu) {
		
		return id.createProfileDAO(iu);
	}




	public int createLoginService(instagramUser iu) {

		return id.LoginProfileDAO(iu);
	}




	




	public List<instagramUser> searchProfileService(instagramUser iu) {
		
		return id.searchProfileDAO(iu);
	}




	public int deleteProfileService(instagramUser iu) {
		
		return id.deleteProfileDAO(iu);
	}



//
//	public instagramUser viewProfileService(String email,String password) {
//		
//		return id.viewProfileDAO(email,password);
//	}




	@Override
	public int editProfileService(instagramUser iu) {
		
		return id.editProfileDAO(iu);
	}




	@Override
	public List<instagramUser> viewAllProfileService() {
	
		return id.viewAllProfileDAO();
	}




	@Override
	public instagramUser viewProfileService(instagramUser iu) {
		
		return id.viewProfileDAO(iu);
	}




	@Override
	public instagramUser viewProfileService(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}





	


//
//	public int editProfileService(instagramImage ii) {
//		return id.editProfileDAO(ii);
//	}
	
	
	
	
	
}
