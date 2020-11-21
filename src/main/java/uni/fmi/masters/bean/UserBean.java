package uni.fmi.masters.bean;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GenerationType;

@Entity
@Table(name = "user")
public class UserBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="username", nullable = false, unique = true, length = 40)
	private String username;
	
	@Column(name="password", nullable = false, length = 32)
	private String password;
	
	@Column(name="email", nullable = false, unique = true, length = 254)
	private String email;
	
	/*
	@Column(name="image")
	private String userImage;
	*/
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<MemoryBean> memories;
	
	@ManyToMany
	@JoinTable(name = "account_role", 
	joinColumns=@JoinColumn(name="account_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<UserRoleBean> roles;
	
	public Set<UserRoleBean> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRoleBean> roles) {
		this.roles = roles;
	}

	public UserBean() {}
	
	public UserBean(String username, String email) {
		this.username = username;
		this.email = email;
	}
	
	public UserBean(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;	
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	*/

	public List<MemoryBean> getMemories() {
		return memories;
	}

	public void setMemories(List<MemoryBean> memories) {
		this.memories = memories;
	}	

}
