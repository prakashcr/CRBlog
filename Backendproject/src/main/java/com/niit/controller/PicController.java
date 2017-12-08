package com.niit.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.model.User;
import com.niit.dao.UserDao;
import com.niit.model.Error;

@Controller
public class PicController {

	@Autowired
	private UserDao userDao;
	
	User user;
	
	@Autowired
	HttpServletRequest request;
	
	
	@RequestMapping(value = "image/upload/{username}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> imageUpload(HttpServletRequest req, @PathVariable String username) {
		
		
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		Iterator<String> itr = mr.getFileNames();
		while (itr.hasNext()) {
			// org.springframework.web.multipart.MultipartFile
			MultipartFile fd = mr.getFile(itr.next());
			String fileName = fd.getOriginalFilename();
			System.out.println("*****" + fileName);

			User user = userDao.getUserByUsername(username);

			try {
				/*
				 * Creating the directory in the server context to store.
				 */
				String imagePath = request.getServletContext().getRealPath("/resources/images");
				System.out.println("------- Context Path set -------");
				File dir = new File(imagePath + File.separator);
				System.out.println("------- Directory set to" + dir + "-------");
				if (!dir.exists())
					dir.mkdirs();
				String orgName = user.getUsername();
				String filePath = imagePath + File.separator + orgName + ".jpg";
				File dest = new File(filePath);
				System.out.println("------- Image uploaded to " + dest + "-------");
				fd.transferTo(dest);

			} catch (Exception e) {
				System.out.println("You failed to upload " + user.getUsername() + " => " + e.getMessage());
			}

		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
