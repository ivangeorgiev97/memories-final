package uni.fmi.masters.bean;

import java.io.Serializable;
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

import lombok.Data;

import javax.persistence.GenerationType;

@Entity
@Table(name = "user")
@Data
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

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
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "account_role", 
	joinColumns=@JoinColumn(name="account_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<UserRoleBean> roles;
	

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

}
