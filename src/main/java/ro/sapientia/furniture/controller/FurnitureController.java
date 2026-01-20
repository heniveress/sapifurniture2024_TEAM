package ro.sapientia.furniture.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sapientia.furniture.model.FurnitureBody;
import ro.sapientia.furniture.service.FurnitureBodyService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.sapientia.furniture.model.Project;
import ro.sapientia.furniture.repository.ProjectRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/furniture")
public class FurnitureController {

	private final FurnitureBodyService furnitureBodyService;
	private final ProjectRepository projectRepository;
	
	public FurnitureController(final FurnitureBodyService furnitureBodyService, final ProjectRepository projectRepository) {
		this.furnitureBodyService = furnitureBodyService;
		this.projectRepository = projectRepository;
	}
	
	@PostMapping("/project/save")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Project> saveProject(@RequestBody Project project) {
	    if (project.getFurnitures() != null) {
	        project.getFurnitures().forEach(f -> f.setProject(project));
	    }
	    return ResponseEntity.ok(projectRepository.save(project));
	}
	
	@GetMapping("/project/all")
	public ResponseEntity<List<Project>> getAllProjects() {
	    return ResponseEntity.ok(projectRepository.findAll());
	}
	
	@DeleteMapping("/project/delete/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable("id") Long id) {
		projectRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<FurnitureBody>> getAllFurnitureBodies(){
		final List<FurnitureBody> furnitureBodies = furnitureBodyService.findAllFurnitureBodies();
		return new ResponseEntity<>(furnitureBodies,HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<FurnitureBody> getFurnitureBodyById(@PathVariable("id") Long id){
		final FurnitureBody furnitureBody = furnitureBodyService.findFurnitureBodyById(id);
		return new ResponseEntity<>(furnitureBody,HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<FurnitureBody> addFurnitureBody(@RequestBody FurnitureBody furnitureBody){
		final FurnitureBody persistenFurnitureBody = furnitureBodyService.create(furnitureBody);
		return new ResponseEntity<>(persistenFurnitureBody,HttpStatus.CREATED);
	}

	@PostMapping("/update")
	public ResponseEntity<FurnitureBody> updateFurnitureBody(@RequestBody FurnitureBody furnitureBody){
		final FurnitureBody persistenFurnitureBody = furnitureBodyService.update(furnitureBody);
		return new ResponseEntity<>(persistenFurnitureBody,HttpStatus.OK);
	}

	@GetMapping("delete/{id}")
	public ResponseEntity<?> deleteFurnitureBodyById(@PathVariable("id") Long id){
		furnitureBodyService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
