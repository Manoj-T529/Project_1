package com.instagram.DAO;

import java.util.List;

import com.instagram.Entity.instagramImage;
import com.instagram.Entity.instagramUser;

public interface instagramDAOInterface {

	int createProfileDAO(instagramUser iu);

	int LoginProfileDAO(instagramUser iu);

	//public instagramUser viewProfileDAO(String email,String password);

	List<instagramUser> searchProfileDAO(instagramUser iu);

	int deleteProfileDAO(instagramUser iu);

	int editProfileDAO(instagramUser iu);

	public List<instagramUser> viewAllProfileDAO();

	public instagramUser viewProfileDAO(instagramUser iu);

//	int editProfileDAO(instagramImage ii);

}
