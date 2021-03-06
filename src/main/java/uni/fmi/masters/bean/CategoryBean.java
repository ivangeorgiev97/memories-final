package uni.fmi.masters.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "category")
@Data
@JsonIgnoreProperties({"memories"})
public class CategoryBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length = 250, nullable = false, unique = true)
	private String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private List<MemoryBean> memories;
	
	public CategoryBean() {}
	
	public CategoryBean(String name) {
		this.name = name;
	}
}	
