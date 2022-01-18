package com.instagram.Utiler;

import com.instagram.Service.instagramService;
import com.instagram.Service.instagramServiceInterface;

public class ServiceFactory {
	
	public static instagramServiceInterface createObject()
	{
		return new instagramService();
	}

}
