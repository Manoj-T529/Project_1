package com.instagram.DAO;




import java.util.*;


import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.instagram.Entity.instagramImage;
import com.instagram.Entity.instagramUser;

public class instagramDAO implements instagramDAOInterface {
	
		private SessionFactory sf;
	
		
		public instagramDAO()
		{
			sf=new Configuration().configure().buildSessionFactory();
		}
	
	
	
	

	public int createProfileDAO(instagramUser iu) {
		
		System.out.println("GreatWork DAO");
		System.out.println(iu.getEmail());	
			int i=0;
		
		Session s=sf.openSession();
		EntityTransaction et=s.getTransaction();
		
		System.out.println("GreatWork DAO begins");
		et.begin();
		System.out.println("GreatWork DAO On");
		s.save(iu);
		System.out.println("GreatWork DAO Lock");
		et.commit();
		i++;
		
		System.out.println("GreatWork DAO Ends");
		
		return i;
	}





	public int LoginProfileDAO(instagramUser iu) {
		
		System.out.println("LoginDAO done");
		
		int i=0;
		
		Session s=sf.openSession();
		
		Query q=s.createQuery("from com.instagram.Entity.instagramUser i where i.email=:e and i.password=:p ");
		q.setParameter("e", iu.getEmail());
		q.setParameter("p", iu.getPassword());
		
		instagramUser ii=(instagramUser)q.getSingleResult();
		
		
		
//		EntityTransaction et=s.getTransaction();
//		et.begin();
//		s.save(iu);
//		et.commit();
		System.out.println("GreatWork LoginDAO Ends");
		if(ii!=null)
		{
		i++;
		}
		
		return i;
	}





//public instagramUser viewProfileDAO(String email,String password) {
//	
//		
//		int i=0;
//		System.out.println("viewing");
//		System.out.println(email);
//		
//		Session s=sf.openSession();
//		
//		Query q=s.createQuery("select i.username from com.instagram.Entity.instagramUser i where i.email=:e and i.password=:p");
//		q.setParameter("e",email);
//		q.setParameter("p",password);
//		
//		instagramUser ii=(instagramUser)q.getSingleResult();
//		System.out.println("viewing done");
//		
//		return ii;
//	}





	public List<instagramUser> searchProfileDAO(instagramUser iu) {
		
		System.out.println("DAOSearch");
		System.out.println(iu.getUsername());
		
		Session s=sf.openSession();
		Query q=s.createQuery("from com.instagram.Entity.instagramUser i where i.username like :e");
		q.setParameter("e","%"+iu.getUsername()+"%");
		
		List<instagramUser> ii=q.getResultList();
		
		System.out.println(ii.size());
		
		return ii;
	}





	public int deleteProfileDAO(instagramUser iu) {
		
		int i=0;
		System.out.println(iu.getEmail());
		
		Session s=sf.openSession();
		EntityTransaction et=s.getTransaction();
		et.begin();
		Query q=s.createQuery("delete from com.instagram.Entity.instagramUser i where i.email=:e");
		q.setParameter("e",iu.getEmail());

		
		i=q.executeUpdate();
		
		et.commit();
		
		System.out.println(i);
		
		return i;
	}





	@Override
	public int editProfileDAO(instagramUser iu) {
	
		System.out.println("editingdao");
	System.out.println(iu.getEmail());
	
	int i=0;
		
		Session s=sf.openSession();
		EntityTransaction et=s.getTransaction();
		et.begin();
		Query q=s.createQuery("update com.instagram.Entity.instagramUser i set i.fullname=:n,i.username=:u,i.gender=:g,i.mobile:m where i.email=:e");
		q.setParameter("e",iu.getEmail());
		q.setParameter("n",iu.getFullname());
		q.setParameter("u",iu.getUsername());
		q.setParameter("g",iu.getGender());
		q.setParameter("m",iu.getMobile());
		//q.setParameter("im", iu.getUserimg());
		
		 i=q.executeUpdate();
	
		 et.commit();
	System.out.println("editing done");
		return i;
	}





	@Override
	public List<instagramUser> viewAllProfileDAO() {
		
		System.out.println("DAOviewAll");
		
		Session s=sf.openSession();
		Query q=s.createQuery("from com.instagram.Entity.instagramUser");
		

		List<instagramUser> ii=q.getResultList();
		
		System.out.println(ii.size());
		
		return ii;
	}





	@Override
	public instagramUser viewProfileDAO(instagramUser iu) {
	
		int i=0;
		System.out.println("viewing");
		System.out.println(iu.getEmail());
		
		Session s=sf.openSession();
		
		Query q=s.createQuery("select i.username from com.instagram.Entity.instagramUser i where i.email=:e and i.password=:p");
		q.setParameter("e",iu.getEmail());
		q.setParameter("p",iu.getPassword());
		
		instagramUser ii=(instagramUser)q.getSingleResult();
		System.out.println("viewing done");
		
		return ii;
	}








//	public int editProfileDAO(instagramUser iu) {
//		
//		System.out.println(iu.getGender());
//	
//		int i=0;
//		
//		Session s=sf.openSession();
//		Query q=s.createQuery("update com.instagram.Entity.instagramImage set i.userimg=:f where i.email=:e");
//		q.setParameter("e",ii.getEmail());
//		q.setParameter("f",iu.getUserimg());
//		
//		instagramImage ii1=(instagramImage)q.getSingleResult();
//		if(ii1!=null)
//		{
//			i++;
//		}
//		
//		System.out.println(i);
//		return i;
//	}

}
