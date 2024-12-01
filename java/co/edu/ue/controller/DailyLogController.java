package co.edu.ue.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.DailyLog;
import co.edu.ue.service.IDailyLogService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
public class DailyLogController {
		@Autowired
	    IDailyLogService DailyLogService;

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
			Map<String, Object> response = new HashMap<>();
	        response.put("Status", true);
	        response.put("Data", DailyLogService.listAllDailyLogsByDateOrAndCategory(userId, date, categoryId));
	        return new ResponseEntity<>(response, HttpStatus.OK);
		}
		

		@GetMapping(value = "daily/log", produces = MediaType.APPLICATION_JSON_VALUE)
		@Operation(
		    summary = "Obtener un registro diario por ID",
		    description = "Devuelve un registro diario específico a partir de su ID, para su consulta detallada.",
		    tags = {"Análisis de Estado de Ánimo"}
		)
		public ResponseEntity<?> getDailyLog(@RequestParam int id) {
			Map<String, Object> response = new HashMap<>();
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
		public ResponseEntity<?> postDailyLog(@RequestBody DailyLog DailyLog) {
			Map<String, Object> response = new HashMap<>();
	        response.put("Status", true);
	        response.put("Data", DailyLogService.addDailyLog(DailyLog));
	        return new ResponseEntity<>(response, HttpStatus.OK);
		}

		@PutMapping(value = "daily/log", produces = MediaType.APPLICATION_JSON_VALUE)
		@Operation(
		    summary = "Actualizar un registro diario",
		    description = "Permite actualizar un registro diario existente, modificando sus detalles.",
		    tags = {"Análisis de Estado de Ánimo"}
		)
		public ResponseEntity<?> putDailyLog(@RequestBody DailyLog DailyLog) {
			Map<String, Object> response = new HashMap<>();
	        response.put("Status", true);
	        response.put("Data", DailyLogService.upDailyLog(DailyLog));
	        return new ResponseEntity<>(response, HttpStatus.OK);
		}


}
