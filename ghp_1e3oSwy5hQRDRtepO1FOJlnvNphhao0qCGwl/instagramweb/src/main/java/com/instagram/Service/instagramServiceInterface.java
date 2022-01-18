package com.instagram.Service;

import java.util.List;

import com.instagram.Entity.instagramImage;
import com.instagram.Entity.instagramUser;

public interface instagramServiceInterface {

	int createProfileService(instagramUser iu);

	int createLoginService(instagramUser iu);



	List<instagramUser> searchProfileService(instagramUser iu);

	int deleteProfileService(instagramUser iu);

	public instagramUser viewProfileService(String string, String string2);

	public int editProfileService(instagramUser iu);

	public List<instagramUser> viewAllProfileService();

	instagramUser viewProfileService(instagramUser iu);



}
