package com.instagram.Utiler;

import com.instagram.DAO.instagramDAO;
import com.instagram.DAO.instagramDAOInterface;

public class DAOFactory {

	public static instagramDAOInterface createObject()
	{
		return new instagramDAO();
	}
	
}
