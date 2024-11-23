package co.edu.ue.controller;

import java.util.List;

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

import co.edu.ue.entity.EmotionalLog;
import co.edu.ue.service.IEmotionalLogService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin("*")
public class EmotionalLogController {

    @Autowired
    IEmotionalLogService emotionalLogService;

    @GetMapping(value = "Emotional/Logs", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Listar todos los registros emocionales",
        description = "Devuelve una lista de todos los registros emocionales asociados con el análisis de estado de ánimo.",
        tags = {"Análisis de Estado de Ánimo"}
    )
    public ResponseEntity<List<EmotionalLog>> getAllEmotionalLogs() {
        return new ResponseEntity<List<EmotionalLog>>(emotionalLogService.listAllEmotionalLogs(), HttpStatus.OK);
    }

    @GetMapping(value = "Emotional/Log", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obtener un registro emocional por ID",
        description = "Devuelve un registro emocional específico a partir de su ID, para análisis detallado del estado de ánimo.",
        tags = {"Análisis de Estado de Ánimo"}
    )
    public ResponseEntity<EmotionalLog> getEmotionalLog(@RequestParam int id) {
        return new ResponseEntity<EmotionalLog>(emotionalLogService.findByIdEmotionalLog(id), HttpStatus.OK);
    }

    @PostMapping(value = "Emotional/Log", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Registrar un nuevo registro emocional",
        description = "Permite registrar un nuevo estado emocional en el sistema para su análisis.",
        tags = {"Análisis de Estado de Ánimo"}
    )
    public ResponseEntity<List<EmotionalLog>> postEmotionalLog(@RequestBody EmotionalLog emotionalLog) {
        return new ResponseEntity<List<EmotionalLog>>(emotionalLogService.addEmotionalLog(emotionalLog), HttpStatus.OK);
    }

    @PutMapping(value = "Emotional/Log", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Actualizar un registro emocional",
        description = "Permite actualizar un registro emocional existente, modificando detalles del estado de ánimo registrado.",
        tags = {"Análisis de Estado de Ánimo"}
    )
    public ResponseEntity<EmotionalLog> putEmotionalLog(@RequestBody EmotionalLog emotionalLog) {
        return new ResponseEntity<EmotionalLog>(emotionalLogService.upEmotionalLog(emotionalLog), HttpStatus.OK);
    }
}
