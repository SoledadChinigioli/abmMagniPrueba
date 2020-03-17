package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.EmpresaDto;
import com.example.demo.entity.EmpresaEntity;
import com.example.demo.entity.NoticiaEntity;
import com.example.demo.repository.EmpresaRepository;

@Service
public class EmpresaService {
	private EmpresaRepository repository;

	public EmpresaService (EmpresaRepository repository) {
		super();
		this.repository = repository;
		
		
	}	
		//-------------------------------FIND ALL-----------------------------------------------
		
		@Transactional
		public List<EmpresaDto> findAll() throws Exception{
			List<EmpresaEntity> entities = repository.findAll();
			
			
			List<EmpresaDto> dtos = new ArrayList<>();
			
			try {
				for(EmpresaEntity i : entities) {
					EmpresaDto unDto = new EmpresaDto();
					unDto.setId(i.getId());
					unDto.setDenominacion(i.getDenominacion());
					unDto.setTelefono(i.getTelefono());
					unDto.setHorarioAtencion(i.getHorarioAtencion());
					unDto.setQuienesSomos(i.getQuienesSomos());
					unDto.setLongitud(i.getLongitud());
					unDto.setLatitud(i.getLatitud());
					unDto.setDomicilio(i.getDomicilio());
					unDto.setEmail(i.getEmail());
					//unDto.setNoticias(i.getNoticias());
					
					
					dtos.add(unDto);
					
					
				}
				return dtos;
				
			} catch (Exception e) {
				// TODO: handle exception
				throw new Exception();
			}
			
			
		}
		
		//----------------------------------------FIND BY ID---------------------------
		@Transactional
		public EmpresaDto findById(int id) throws Exception {
			
			Optional<EmpresaEntity> entityOptional = repository.findById(id);
			
			EmpresaDto unDto = new EmpresaDto();
			
			try {
				EmpresaEntity entity = entityOptional.get();	
				
				unDto.setId(entity.getId());
				unDto.setDenominacion(entity.getDenominacion());
				unDto.setTelefono(entity.getTelefono());
				unDto.setHorarioAtencion(entity.getHorarioAtencion());
				unDto.setQuienesSomos(entity.getQuienesSomos());
				unDto.setLongitud(entity.getLongitud());
				unDto.setLatitud(entity.getLatitud());
				unDto.setDomicilio(entity.getDomicilio());
				unDto.setEmail(entity.getEmail());
			//	unDto.setNoticias(entity.getNoticias());
				
				
				
				return unDto;
				
			} catch (Exception e) {
				// TODO: handle exception
				throw new Exception();
			}
			
		}
		
		//------------------------------------SAVE------------------------------------
		@Transactional
		public EmpresaDto save (EmpresaDto dto) throws Exception{
			EmpresaEntity entity = new EmpresaEntity();
			entity.setDenominacion(dto.getDenominacion());
			entity.setTelefono(dto.getTelefono());
			entity.setHorarioAtencion(dto.getHorarioAtencion());
			entity.setQuienesSomos(dto.getQuienesSomos());
			entity.setLongitud(dto.getLongitud());
			entity.setLatitud(dto.getLatitud());
			entity.setDomicilio(dto.getDomicilio());
			entity.setEmail(dto.getEmail());
		//	entity.setNoticias(dto.getNoticias());
			
			try {
				entity = repository.save(entity);
				
				dto.setId(entity.getId());
				return dto;
				
				
			} catch (Exception e) {
				// TODO: handle exception
				throw new Exception();
			}
			
		}
		//------------------------------------UPDATE-----------------------------------
		
		@Transactional
		public EmpresaDto update (int id, EmpresaDto dto) throws Exception{
			
			Optional<EmpresaEntity> entityOptional = repository.findById(id);
			
			try {
				EmpresaEntity entity = entityOptional.get();
				
				entity.setId(id);
				entity.setDenominacion(dto.getDenominacion());
				entity.setTelefono(dto.getTelefono());
				entity.setHorarioAtencion(dto.getHorarioAtencion());
				entity.setQuienesSomos(dto.getQuienesSomos());
				entity.setLongitud(dto.getLongitud());
				entity.setLatitud(dto.getLatitud());
				entity.setDomicilio(dto.getDomicilio());
				entity.setEmail(dto.getEmail());
			//	entity.setNoticias(dto.getNoticias());
				
				
				repository.save(entity);
				dto.setId(entity.getId());
				return dto;
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				throw new Exception();
			}
		}
		
		//----------------------------------DELETE-------------------------------------
		
		public boolean delete (int id) throws Exception {
			try {
				if (repository.existsById(id)) {
					
					repository.deleteById(id);
					return true;
					
				}else {
					
					throw new Exception();
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				throw new Exception();	
			}
			
			
		}
		
	}
		
		

