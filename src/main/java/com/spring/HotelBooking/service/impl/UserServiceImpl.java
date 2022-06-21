package com.spring.HotelBooking.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.HotelBooking.entity.User;
import com.spring.HotelBooking.payload.UserDto;
import com.spring.HotelBooking.repository.UserRepository;
import com.spring.HotelBooking.service.UserService;




@Service
public class UserServiceImpl implements UserService {
	
	
private UserRepository userrepository;

	
	public UserServiceImpl(UserRepository userrepository) {
		super();
		this.userrepository = userrepository;
	}
	
	@Override
	public Boolean ExistsByEmail(UserDto dto) {
		// TODO Auto-generated method stub
		List<User> user=userrepository.findAll();
		
	boolean bool=true;
	for(User u:user) {
			String email=u.getEmail();
			if(dto.getEmail().equalsIgnoreCase(email)) {
				bool=true;
				
			}else { bool=false;}
		}
		return bool;
	}

	
	
	@Override
	public UserDto createUser(UserDto userdto) {
		
		//convert dto to entity 
		User user =mapToEntity(userdto);
		
		User newuser =userrepository.save(user);
		
		//convert  entity to dto 
		
		UserDto  response=mapToDto(newuser);
		
		return response;
		
	}
	
	
	//convert entity to dto 
		private UserDto mapToDto(User user) {
			UserDto userDto=new UserDto();
			//postDto.setId(post.getId());
			userDto.setId(user.getUserid());
			userDto.setEmail(user.getEmail());
			userDto.setFname(user.getFname());
			userDto.setLname(user.getLname());
			
			return userDto;
			
		}
		//convert dto to entity 
		private User mapToEntity(UserDto userdto) {
			User user =new User();
			//user.setUserid(userdto.getId());
				user.setEmail(userdto.getEmail());
				user.setFname(userdto.getFname());
				user.setLname(userdto.getLname());
				
				return user;
				
				
			
		}




	
		

	

}
