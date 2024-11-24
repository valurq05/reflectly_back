package co.edu.ue.controller;

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

import co.edu.ue.entity.EmotionalState;
import co.edu.ue.service.IEmotionalStateService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin("*")
public class EmotionalStateController {

    @Autowired
    IEmotionalStateService emoStateService;

    @GetMapping(value = "emotional/states", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Listar todos los estados emocionales",
        description = "Devuelve una lista de todos los estados emocionales disponibles para su análisis.",
        tags = {"Análisis de Estado de Ánimo"}
    )
    public ResponseEntity<?> getAllEmotionalStates() {
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", emoStateService.listAllEmotionalState());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "emotional/state", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obtener un estado emocional por ID",
        description = "Devuelve los detalles de un estado emocional específico identificado por su ID.",
        tags = {"Análisis de Estado de Ánimo"}
    )
    public ResponseEntity<?> getEmotionalState(@RequestParam int id) {
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", emoStateService.findByIdEmotionalState(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "emotional/state", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Registrar un nuevo estado emocional",
        description = "Permite registrar un nuevo estado emocional en el sistema para ser utilizado en futuros análisis.",
        tags = {"Análisis de Estado de Ánimo"}
    )
    public ResponseEntity<?> postEmotionalState(@RequestBody EmotionalState emotionalState) {
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", emoStateService.addEmotionalState(emotionalState));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "emotional/state", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Actualizar un estado emocional",
        description = "Permite modificar los detalles de un estado emocional existente.",
        tags = {"Análisis de Estado de Ánimo"}
    )
    public ResponseEntity<?> putEmotionalState(@RequestBody EmotionalState emotionalState) {
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", emoStateService.upEmotionalState(emotionalState));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
