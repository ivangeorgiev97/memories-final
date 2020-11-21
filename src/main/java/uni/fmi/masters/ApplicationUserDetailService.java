package uni.fmi.masters;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uni.fmi.masters.bean.UserRoleBean;
import uni.fmi.masters.bean.UserBean;
import uni.fmi.masters.repository.UserRepository;

@Service
public class ApplicationUserDetailService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	public ApplicationUserDetailService(UserRepository UserRepository) {
		this.userRepository = UserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserBean user = userRepository.getByUsername(username);
		
		if (user == null)
			throw new UsernameNotFoundException(username);
		
		Set<UserRoleBean> roles = user.getRoles();
		
		return new UserPrincipal(user, roles);
	}

}
