package co.edu.ue.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import co.edu.ue.dto.UpdateDailyLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.dto.DailyLogFullInfo;
import co.edu.ue.entity.Collaborator;
import co.edu.ue.entity.DailyLog;
import co.edu.ue.entity.EmotionalLog;
import co.edu.ue.entity.Entry;
import co.edu.ue.service.ICategoryService;
import co.edu.ue.service.ICollaboratorService;
import co.edu.ue.service.IDailyLogService;
import co.edu.ue.service.IEmotionalLogService;
import co.edu.ue.service.IEmotionalStateService;
import co.edu.ue.service.IEntryService;
import co.edu.ue.service.IUserService;
import co.edu.ue.validator.DailyInfoValidator;
import co.edu.ue.validator.DailyLogValidator;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
public class DailyLogController {
		@Autowired
	    IDailyLogService DailyLogService;
		@Autowired
		IEmotionalLogService emoLogService;
		@Autowired
		IUserService userService;
		@Autowired
		IEmotionalStateService emoStateService;
		@Autowired
		IEntryService entryService;
		@Autowired 
		ICollaboratorService collaboratorService;
		@Autowired
		ICategoryService categoryService;
		@Autowired 
		DailyInfoValidator dailyValidator;
		@Autowired
		DailyLogValidator dailyLogValidator;

		@GetMapping(value = "daily/logs", produces = MediaType.APPLICATION_JSON_VALUE)
		@Operation(
		    summary = "Listar todos los registros diarios",
		    description = "Devuelve una lista de todos los registros diarios disponibles en el sistema.",
		    tags = {"Análisis de Estado de Ánimo"}
		)
		public ResponseEntity<?> getAllDailyLogs() {
			Map<String, Object> response = new HashMap<>();
	        response.put("Status", true);
	        response.put("Data", DailyLogService.listAllDailyLogs());
	        return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		@GetMapping(value = "daily/user/logs", produces = MediaType.APPLICATION_JSON_VALUE)
		@Operation(
		    summary = "Listar todos los registros diarios de un usuario según el día o la categoria",
		    description = "Devuelve una lista de todos los registros diarios de un usuario de un dia especifico o de"
		    		+ " una categoria especifica disponibles en el sistema. Es un filtro para las entradas, la fecha y la categoria son opcionales, el usuario no",
		    tags = {"Análisis de Estado de Ánimo"}
		)
		public ResponseEntity<?> getAllDailyLogsByUserAndDate(@RequestParam(required = false) LocalDate date, @RequestParam(required = false) Integer categoryId, @RequestParam int userId) {
			
			
			Map<String, String> errors = new HashMap<>();

		  
		    if (date != null && date.isAfter(LocalDate.now())) {
		        errors.put("date", "La fecha no puede ser mayor a la fecha actual");
		    }

		
		    if (categoryId != null && !categoryService.existsBycatId(categoryId)) {
		        errors.put("categoryId", "La categoría con el ID proporcionado no existe");
		    }
		    
		    if (!errors.isEmpty()) {
		        Map<String, Object> response = new HashMap<>();
		        response.put("Status", false);
		        response.put("Errors", errors);
		        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		    }
			Map<String, Object> response = new HashMap<>();
	        response.put("Status", true);
	        response.put("Data", DailyLogService.listAllDailyLogsByDateOrAndCategory(userId, date, categoryId).isEmpty()?
	        		"No hay registros diarios" : DailyLogService.listAllDailyLogsByDateOrAndCategory(userId, date, categoryId));
	        return new ResponseEntity<>(response, HttpStatus.OK);
		}
		

		@GetMapping(value = "daily/log/show", produces = MediaType.APPLICATION_JSON_VALUE)
		@Operation(
		    summary = "Obtener un registro diario por ID",
		    description = "Devuelve un registro diario específico a partir de su ID, para su consulta detallada.",
		    tags = {"Análisis de Estado de Ánimo"}
		)
		public ResponseEntity<?> getDailyLog(@RequestParam int id) {
			Map<String, Object> response = new HashMap<>();
			System.out.println("llego aca");
	        response.put("Status", true);
	        response.put("Data", DailyLogService.findByIdDailyLog(id));
	        return new ResponseEntity<>(response, HttpStatus.OK);
		}

		@PostMapping(value = "daily/log", produces = MediaType.APPLICATION_JSON_VALUE)
		@Operation(
		    summary = "Registrar un nuevo registro diario",
		    description = "Permite añadir un nuevo registro diario al sistema.",
		    tags = {"Análisis de Estado de Ánimo"}
		)
		public ResponseEntity<?> postDailyLog(@RequestBody DailyLog DailyLog, BindingResult result) {

			dailyLogValidator.validate(DailyLog, result);
    
			if (result.hasErrors()) {
				
				Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(
						error -> error.getField(),
						error -> error.getDefaultMessage()
					));
				Map<String, Object> response = new HashMap<>();
				response.put("Data", errors);
				response.put("Status", false);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			Map<String, Object> response = new HashMap<>();
	        response.put("Status", true);
	        response.put("Data", DailyLogService.addDailyLog(DailyLog));
	        return new ResponseEntity<>(response, HttpStatus.OK);
		}
	

		/*@PutMapping(value = "daily/log", produces = MediaType.APPLICATION_JSON_VALUE)
		@Operation(
		    summary = "Actualizar un registro diario",
		    description = "Permite actualizar un registro diario existente, modificando sus detalles.",
		    tags = {"Análisis de Estado de Ánimo"}
		)
		public ResponseEntity<?> putDailyLog(@RequestBody DailyLog DailyLog, BindingResult result) {
			dailyLogValidator.validate(DailyLog, result);
    
			if (result.hasErrors()) {
				
				Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(
						error -> error.getField(),
						error -> error.getDefaultMessage()
					));
				Map<String, Object> response = new HashMap<>();
				response.put("Data", errors);
				response.put("Status", false);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			System.out.println(DailyLog);
			Map<String, Object> response = new HashMap<>();
	        response.put("Status", true);
	        response.put("Data", DailyLogService.upDailyLog(DailyLog));
	        return new ResponseEntity<>(response, HttpStatus.OK);
		} */


	@PutMapping(value = "daily/log", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Actualizar un registro diario",
			description = "Permite actualizar un registro diario existente, modificando sus detalles.",
			tags = {"Análisis de Estado de Ánimo"}
	)
	public ResponseEntity<?> putDailyLog(@RequestBody UpdateDailyLogDTO fullInfo, @RequestParam int id) {
	//	dailyLogValidator.validate(fullInfo, result);

		DailyLog dailyLog = DailyLogService.findByIdDailyLog(id);

		System.out.println(dailyLog);


	
		return null;
	}


	@PostMapping(value ="daily/log/allinfo", produces = MediaType.APPLICATION_JSON_VALUE)
		@Operation(
			    summary = "Crear un registro diario con toda su información necesaria",
			    description = "Permite crear un registro diario registrando inmediatamente su registro emocial y su entrada",
			    tags = {"Análisis de Estado de Ánimo"}
			)
		public ResponseEntity<?> postAllDailyLogNecessaryInfo(@RequestBody DailyLogFullInfo fullInfo, BindingResult result){
			
			dailyValidator.validate(fullInfo, result);
			
			if (result.hasErrors()) {
	            Map<String, String> errors = result.getFieldErrors().stream()
	                .collect(Collectors.toMap(
	                    error -> error.getField(),
	                    error -> error.getDefaultMessage()
	                ));
	            Map<String, Object> response = new HashMap<>();
	            response.put("Data", errors);
	            response.put("Status", false);
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        }
			
			EmotionalLog newEmoLog = new EmotionalLog(); 
			newEmoLog.setEmoLogDate(new Date());
			System.out.println(newEmoLog.getEmoLogDate());
			newEmoLog.setUser(userService.findByIdUser(fullInfo.getUseId()));
			newEmoLog.setEmotionalState(emoStateService.findByIdEmotionalState(fullInfo.getEmoStaId()));
			emoLogService.addEmotionalLog(newEmoLog);
			EmotionalLog lastEmoLog = emoLogService.findByIdEmotionalLog(newEmoLog.getEmoLogId());
			System.out.println(lastEmoLog.getEmoLogId());
			Entry newEntry = new Entry();
			newEntry.setEntDate(new Date());
			newEntry.setEntText(fullInfo.getEntText());
			newEntry.setEntTitle(fullInfo.getEntTitle());
			newEntry.setEntStatus(true);
			entryService.addEntry(newEntry);
			Entry lastEntry = entryService.findByIdEntry(newEntry.getEntId());
			System.out.println(lastEntry.getEntId());
			Collaborator newCollaborator = new Collaborator();
			newCollaborator.setUser(userService.findByIdUser(fullInfo.getUseId()));
			newCollaborator.setEntry(lastEntry);
			collaboratorService.addCollaborator(newCollaborator);
			DailyLog newDailyLog = new DailyLog();
			newDailyLog.setDayLogDate(new Date());
			newDailyLog.setEmotionalLog(lastEmoLog);
			newDailyLog.setEntry(lastEntry);
			
			Map<String, Object> response = new HashMap<>();
	        response.put("Status", true);
	        response.put("Data", DailyLogService.addDailyLog(newDailyLog));
	        return new ResponseEntity<>(response, HttpStatus.OK);
			
			
			
		}


}
