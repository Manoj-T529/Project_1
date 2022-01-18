package com.instagram.Controller;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.instagram.Entity.instagramImage;
import com.instagram.Entity.instagramUser;
import com.instagram.Service.instagramServiceInterface;
import com.instagram.Utiler.ServiceFactory;


/**
 * Servlet implementation class UserController
 */


//@WebServlet("/UserController")
//@MultipartConfig(fileSizeThreshold=1024*1024*2,maxFileSize=1024*1024*10,maxRequestSize=1024*1024*50)
//@MultipartConfig
public class UserController extends HttpServlet {
	
	HttpSession hs;
	RequestDispatcher rd;
	public String useridcheck="";
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ch=request.getParameter("method");
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body>");
		
		instagramServiceInterface is=ServiceFactory.createObject();
		
		
	System.out.println(ch);	
		if(ch.equals("signUp"))
		{
			System.out.println("GreatWork");
			
			String email=request.getParameter("email");
			String name=request.getParameter("fullname");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			
			instagramUser iu=new instagramUser();
			
			iu.setEmail(email);
			iu.setFullname(name);
			iu.setUsername(username);
			iu.setPassword(password);
			
			
			int i=is.createProfileService(iu);
			
			if(i>0) {
				
				out.println("Sign Up Successful");
				 hs=request.getSession(true);
					hs.setAttribute("userid",email);
					hs.setAttribute("password",password );
					hs.setAttribute("username", username);
				
				
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/HomePage.html");
				rd.forward(request, response);
			}
			else {
				out.println("could not create profile");
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/signUp.html");
				rd.forward(request, response);
			}
	
		}
		
		if(ch.equals("signIn"))
		{
			System.out.println("signed in");
			
				String email=request.getParameter("email");
				String password=request.getParameter("password");
				
				
				instagramUser iu=new instagramUser();
				
				iu.setEmail(email);
				iu.setPassword(password);
				
				
				
				int i=is.createLoginService(iu);
				
				if(i>0)
				{
					out.println("Login Successful");
					useridcheck=email;
					
//					 hs=request.getSession(true);
//					hs.setAttribute("userid",email);
//					hs.setAttribute("password",password );
					
					
					 rd=getServletContext().getRequestDispatcher("/HomePage.html");
					rd.forward(request, response);
						
					}
				
				else
				{
					out.println("Invalid Username and Password");
					
					rd=getServletContext().getRequestDispatcher("/signUp.html");
					rd.forward(request,response);
				}
		}	
		
		
		
		if(ch.equals("viewProfile"))
		{
			
		System.out.println("viewing");
		
		  hs=request.getSession(true);
		 String email=hs.getAttribute("userid").toString();
		 
		 System.out.println(email);
		 
		 instagramUser iu=new instagramUser();
		 iu.setEmail(email);
		 
		 instagramUser iuser=is.viewProfileService(iu);
		
//			instagramUser iuser=is.viewProfileService(hs.getAttribute("userid").toString(),hs.getAttribute("password").toString());
//			JSONObject jsonObject = new JSONObject(iuser);
//			response.setContentType("application/json");
//			out=response.getWriter();
//			out.print(jsonObject);
		}
		
		if(ch.equals("deleteProfile"))
		{
			System.out.println("Deletion");
			
//			 hs=request.getSession(true);
//			String email=hs.getAttribute("userid").toString();
			 
			instagramUser iu=new instagramUser();
			iu.setEmail(useridcheck);
			
			int i=is.deleteProfileService(iu);
			
			if(i>0)
			{
				out.println("<html><body><center>");
				out.println("profile deleted");
				out.println("</center></body></html>");
			}
			else 
			{
				out.println("deletion failed");
			}
			
			
		}
		
		if(ch.equals("search"))	
		{
			
			System.out.println("Searching Started");
			
//			 hs=request.getSession(true);
//				String username1=hs.getAttribute("username").toString();
			
			
			String username=request.getParameter("username");
			
			instagramUser iu=new instagramUser();
			
			iu.setUsername(username);
			
//			instagramUser iuser=is.searchProfileService(hs.getAttribute("email").toString(), hs.getAttribute("password").toString());
//			JSONObject jsonObject = new JSONObject(iuser);
//			response.setContentType("application/json");
//			out=response.getWriter();
//			out.print(jsonObject);
			
		List<instagramUser> i2=is.searchProfileService(iu);
		
		if(i2.size()>0)
		{
			out.println("Profile Searched");
			for(instagramUser ii:i2)
			{
				out.println("<br/>The Searched UserNames are<br/>");
				out.println("<br>"+ii.getUsername()+"<br>");
				out.println("<=============================================>");
			
			}
			
			//out.println("<br>Your Username is "+email+"  and password is "+password);
		}
		else
		{
			out.println("Searching Failed");
		}
		
		}
		
		
		if(ch.equals("viewAllProfile"))	
		{
			
			System.out.println("viewALL Started");
		
			
//			instagramUser iuser=is.searchProfileService(hs.getAttribute("email").toString(), hs.getAttribute("password").toString());
//			JSONObject jsonObject = new JSONObject(iuser);
//			response.setContentType("application/json");
//			out=response.getWriter();
//			out.print(jsonObject);
			
		List<instagramUser> i2=is.viewAllProfileService();
		
		if(i2.size()>0)
		{
			out.println("ALLProfile Viewed");
			for(instagramUser ii:i2)
			{
				out.println("<==========================================================>");
				out.println("<br> Your Email is:"+ii.getEmail()+"<br>");
				out.println("<br> Your Username is:"+ii.getUsername()+"<br>");
				out.println("<br> Your FullName is:"+ii.getFullname()+"<br>");
				out.println("<==========================================================>");
			}
			
			//out.println("<br>Your Username is "+email+"  and password is "+password);
		}
		else
		{
			out.println("ViewAll Failed");
		}
		
		}


		
		
		
		
		

		if(ch.equals("editProfile"))
		{
			System.out.println("Editing");
			
			
			
				//String email=hs.getAttribute("userid").toString();
//			
//			Part part=request.getPart("file");
			instagramUser iu=new instagramUser();
			
			//String username=request.getParameter("username");
			
			
			
			
			
//			InputStream it=part.getInputStream();
//			byte[] data=new byte[it.available()];
//			
//			it.read(data);
//			
//			 String path= "D:\\Front-End";
//			path+="\\"+username;
//			
//			File fol = new File(path); 
//			System.out.println(fol.mkdirs());
//		
//			
//			String image ="nature3.jpg";
//			String file=path+File.separator+image;
//			System.out.println(file);
//			
//			File myPostFile = new File(file);
//		
//			FileOutputStream fos=new FileOutputStream(myPostFile);
//			fos.write(data);
//			
	//		iu.setUserimg(image);
			
			iu.setEmail(useridcheck);
			iu.setFullname(request.getParameter("fullname"));
			iu.setUsername(request.getParameter("username"));
			iu.setGender(request.getParameter("gender"));
			iu.setMobile(request.getParameter("mobile"));
			
			
			
			
			int result=is.editProfileService(iu);
			
		
			
			if(result>0)
			{
				
				out.println(iu.getUsername());
				out.println(iu.getFullname());
				out.println(iu.getGender());
				out.println(iu.getMobile());
				
//				out.println("<html><body><center>");
//				out.println("<font size=5 color=grey><b>Profile Updated</b></font>");
//				out.println("<br>your user name  is "+iu.getEmail());
//				out.println("</center></body></html>");
				
			}
			
			else
			{
				out.println("could not edit");
			}
			
			
			
			
				
//				if(i4>0)
//				{
//					out.println("Profile Edited");
//					
//					ServletContext c = this.getServletContext();
//					String path = c.getRealPath("/");
//					File f = new File(path + "/D:/" + email + "/Front-End/");
//					f.mkdirs();
//
//					File f1 = new File(path + "/user/images/nature.jpg");
//					File f2 = new File(path + "/D:/" + email + "/profilePhoto/nature3.jpg");
//					f1.renameTo(f2);
//				}
		}

		
		
		
		if(ch.equals("uploadphoto")) {
			out.println("<html lang=\"en\">\r\n" + 
					"<head>\r\n" + 
					"  <meta charset=\"UTF-8\">\r\n" + 
					"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
					"  <title>Uploadphoto</title>\r\n" + 
					"  <link rel=\"stylesheet\" href=\"style.css\">\r\n" + 
					"</head>\r\n" + 
					"<body style='background-color: pink;'>\r\n" + 
					"\r\n" + 
					 
					"<div style='border:5px white solid;box-shadow:0px 0px 10px black;' class=\"profile-pic-div\">\r\n" + 
					"  <img src=\"image.jpg\" id=\"photo\">\r\n" + 
					"  <input type=\"file\" id=\"file\">\r\n" + 
					"  <label for=\"file\" id=\"uploadBtn\">Choose Photo</label>\r\n" + 
					"</div>\r\n" + 
					"\r\n" + 
					"<script src=\"app.js\"></script>\r\n" + 
					
					"\r\n" + 
					"<style>*{\r\n" + 
					"					    margin: 0;\r\n" + 
					"					    padding: 0;\r\n" + 
					"					    box-sizing: border-box;\r\n" + 
					"					}\r\n" + 
					"\r\n" + 
					"					body{\r\n" + 
					"					    height: 100vh;\r\n" + 
					"					    width: 100%;\r\n" + 
					"					}\r\n" + 
					"\r\n" + 
					"					h1{\r\n" + 
					"					    font-family: sans-serif;\r\n" + 
					"					    text-align: center;\r\n" + 
					"					    font-size: 30px;\r\n" + 
					"					    color: #222;\r\n" + 
					"					}\r\n" + 
					"\r\n" + 
					"					.profile-pic-div{\r\n" + 
					"					    height: 200px;\r\n" + 
					"					    width: 200px;\r\n" + 
					"					    position: absolute;\r\n" + 
					"					    top: 50%;\r\n" + 
					"					    left: 50%;\r\n" + 
					"					    transform: translate(-50%,-50%);\r\n" + 
					"					    border-radius: 5;\r\n" + 
					"					    overflow: hidden;\r\n" + 
					"					    border: 1px solid grey;\r\n" + 
					"					}\r\n" + 
					"\r\n" + 
					"					#photo{\r\n" + 
					"					    height: 100%;\r\n" + 
					"					    width: 100%;\r\n" + 
					"					}\r\n" + 
					"\r\n" + 
					"					#file{\r\n" + 
					"					    display: none;\r\n" + 
					"					}\r\n" + 
					"\r\n" + 
					"					#uploadBtn{\r\n" + 
					"					    height: 40px;\r\n" + 
					"					    width: 100%;\r\n" + 
					"					    position: absolute;\r\n" + 
					"					    bottom: 0;\r\n" + 
					"					    left: 50%;\r\n" + 
					"					    transform: translateX(-50%);\r\n" + 
					"					    text-align: center;\r\n" + 
					"					    background: rgba(0, 0, 0, 0.7);\r\n" + 
					"					    color: wheat;\r\n" + 
					"					    line-height: 30px;\r\n" + 
					"					    font-family: sans-serif;\r\n" + 
					"					    font-size: 15px;\r\n" + 
					"					    cursor: pointer;\r\n" + 
					"					    display: none;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</body>\r\n" +
					"<script> //declearing html elements\r\n" + 
					"\r\n" + 
					"const imgDiv = document.querySelector('.profile-pic-div');\r\n" + 
					"const img = document.querySelector('#photo');\r\n" + 
					"const file = document.querySelector('#file');\r\n" + 
					"const uploadBtn = document.querySelector('#uploadBtn');\r\n" + 
					"\r\n" + 
					"//if user hover on img div \r\n" + 
					"\r\n" + 
					"imgDiv.addEventListener('mouseenter', function(){\r\n" + 
					"    uploadBtn.style.display = \"block\";\r\n" + 
					"});\r\n" + 
					"\r\n" + 
					"//if we hover out from img div\r\n" + 
					"\r\n" + 
					"imgDiv.addEventListener('mouseleave', function(){\r\n" + 
					"    uploadBtn.style.display = \"none\";\r\n" + 
					"});\r\n" + 
					"\r\n" + 
					"//lets work for image showing functionality when we choose an image to upload\r\n" + 
					"\r\n" + 
					"//when we choose a foto to upload\r\n" + 
					"\r\n" + 
					"file.addEventListener('change', function(){\r\n" + 
					"    //this refers to file\r\n" + 
					"    const choosedFile = this.files[0];\r\n" + 
					"\r\n" + 
					"    if (choosedFile) {\r\n" + 
					"\r\n" + 
					"        const reader = new FileReader(); //FileReader is a predefined function of JS\r\n" + 
					"\r\n" + 
					"        reader.addEventListener('load', function(){\r\n" + 
					"            img.setAttribute('src', reader.result);\r\n" + 
					"        });\r\n" + 
					"\r\n" + 
					"        reader.readAsDataURL(choosedFile);\r\n" + 
					"\r\n" + 
					"        //Allright is done\r\n" + 
					"\r\n" + 
					 
					"    }\r\n" + 
					"});</script>\r\n" +
 
					"</html>");
		;
			String email=hs.getAttribute("userid").toString(); 
			out.println(" <h1 style='text-align:center;color:blue;'>Upload Photo</h1>");
			out.println("  <br><br><p style=\"text-align: left;\">  <font size=5 color=black><b> Welcome " +email+"</b></font></p> ");
				out.println("<html><body> <style type=\"text/css\">"
						+ ""
						+ "body{\r\n" + 
						"  text-align:center;\r\n" + 
						"  vertical-align:center;\r\n" + 
						"  background-color:grey;\r\n" + 
						"}</style></html></body>");
				
				out.println(" <a href='Homepage.html'> HomePage</a> ");	 
			}
		
		
		

		
			if(ch.equals("logoutProfile"))
			{
				//hs.invalidate();
				useridcheck="";
				rd=getServletContext().getRequestDispatcher("/signUp.html");
				rd.forward(request, response);
				
			}

		
		
		out.println("</html></body>");
	}

	
}
