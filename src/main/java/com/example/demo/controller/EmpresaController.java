package com.example.demo.controller;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmpresaDto;
import com.example.demo.service.EmpresaService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping(path = "api/v1/empresa")
public class EmpresaController {
	
	private EmpresaService service;
	
	public EmpresaController (EmpresaService service) {
		super();
		this.service = service;
	}
	
	//-----------------------------------GET ALL ------------------------------------
	@GetMapping("/")
	
	@Transactional
	public ResponseEntity getAll() {
		
try {
			
			
			
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body
					("{\"message\": \"Error. Registro NO encontrado.\"}");
			
		}
		
	}
	
	//---------------------------------POST---------------------------------------
	
		@PostMapping("/")
		
		@Transactional
		public ResponseEntity post (@RequestBody EmpresaDto dto) {
			
	try {
				

				return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
				
			} catch (Exception e) {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
			("{\"message\": \"Error. Please check the BODY request, and try again later.\"}");
							
			}
			
		}
		
		//-------------------------------------PUT---------------------------------
		
		@PutMapping("/{id}")
		@Transactional
		public ResponseEntity put(@PathVariable int id, @RequestBody EmpresaDto dto) {
			
	try {
				
				
				return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
				
			} catch (Exception e) {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
						("{\"message\": \"Error. Please check the ID or BODY request, and try again later.\"}");
							
			}
			
		}
		
		//--------------------------------DELETE------------------------------
		
		@DeleteMapping("/{id}")
		@Transactional
		public ResponseEntity delete(@PathVariable int id) {
			try {
				System.out.println("Se recibioid:" +id);
						service.delete(id);
						return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Registro eliminado\"}");
						
					} catch (Exception e) {
						
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
				("{\"message\": \"Error. Please check the ID or BODY request, and try again later.\"}");
									
					}
		}

}
