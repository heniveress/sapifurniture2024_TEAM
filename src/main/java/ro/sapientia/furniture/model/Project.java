package ro.sapientia.furniture.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FurnitureBody> furnitures = new ArrayList<>();
	
	public Project() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<FurnitureBody> getFurnitures() {
		return furnitures;
	}
	
	public void setFurnitures(List<FurnitureBody> furnitures) {
		this.furnitures = furnitures;
	}
}
