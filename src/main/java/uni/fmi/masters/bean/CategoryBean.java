package uni.fmi.masters.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length = 250, nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private List<MemoryBean> memories;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MemoryBean> getMemories() {
		return memories;
	}

	public void setMemories(List<MemoryBean> memories) {
		this.memories = memories;
	}
}	
