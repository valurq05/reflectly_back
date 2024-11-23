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
    public ResponseEntity<List<EmotionalState>> getAllEmotionalStates() {
        return new ResponseEntity<List<EmotionalState>>(emoStateService.listAllEmotionalState(), HttpStatus.OK);
    }

    @GetMapping(value = "emotional/state", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obtener un estado emocional por ID",
        description = "Devuelve los detalles de un estado emocional específico identificado por su ID.",
        tags = {"Análisis de Estado de Ánimo"}
    )
    public ResponseEntity<EmotionalState> getEmotionalState(@RequestParam int id) {
        return new ResponseEntity<EmotionalState>(emoStateService.findByIdEmotionalState(id), HttpStatus.OK);
    }

    @PostMapping(value = "emotional/state", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Registrar un nuevo estado emocional",
        description = "Permite registrar un nuevo estado emocional en el sistema para ser utilizado en futuros análisis.",
        tags = {"Análisis de Estado de Ánimo"}
    )
    public ResponseEntity<List<EmotionalState>> postEmotionalState(@RequestBody EmotionalState emotionalState) {
        return new ResponseEntity<List<EmotionalState>>(emoStateService.addEmotionalState(emotionalState), HttpStatus.OK);
    }

    @PutMapping(value = "emotional/state", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Actualizar un estado emocional",
        description = "Permite modificar los detalles de un estado emocional existente.",
        tags = {"Análisis de Estado de Ánimo"}
    )
    public ResponseEntity<EmotionalState> putEmotionalState(@RequestBody EmotionalState emotionalState) {
        return new ResponseEntity<EmotionalState>(emoStateService.upEmotionalState(emotionalState), HttpStatus.OK);
    }
}
