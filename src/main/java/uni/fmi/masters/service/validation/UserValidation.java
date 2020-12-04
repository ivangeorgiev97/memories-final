package uni.fmi.masters.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import uni.fmi.masters.bean.UserBean;
import uni.fmi.masters.service.UserService;

@Component
public class UserValidation implements Validator {
	
	private UserService userService;

	@Autowired
	public UserValidation(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean supports(Class<?> classHere) {
		return UserBean.class.equals(classHere);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserBean currentUser = (UserBean) target;
		
		// Username validaiton
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if (currentUser.getUsername().length() < 4 || currentUser.getUsername().length() > 40) {
			errors.rejectValue("username", "Size.mainUserForm.username");
		}
		if(userService.getByUsername(currentUser.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.mainUserForm.username");
		}
		
		// Password validation
		if (currentUser.getPassword().length() < 4 || currentUser.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.mainUserForm.password");
		}
		// Password confirm too
	}

}
