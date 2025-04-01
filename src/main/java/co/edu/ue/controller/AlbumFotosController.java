package co.edu.ue.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.ue.entity.AlbumFoto;
import co.edu.ue.entity.User;
import co.edu.ue.service.IAlbumFotosService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin("*")
public class AlbumFotosController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private IAlbumFotosService albumFotosService;

    // Eliminamos la inyección de IUserService
    // @Autowired
    // private IUserService userService;

    @GetMapping(value = "fotos", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar todas las fotos del álbum", description = "Devuelve una lista de todas las fotos disponibles en el álbum.", tags = {
            "Álbum de Fotos" })
    public ResponseEntity<List<AlbumFoto>> getAllPhotos() {
        return new ResponseEntity<>(albumFotosService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "foto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtener una foto por ID", description = "Devuelve los detalles de una foto específica identificada por su ID.", tags = {
            "Álbum de Fotos" })
    public ResponseEntity<AlbumFoto> getPhoto(@PathVariable int id) {
        return new ResponseEntity<>(albumFotosService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "user/{userId}/fotos", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtener fotos por usuario", description = "Devuelve todas las fotos asociadas a un usuario específico.", tags = {
            "Álbum de Fotos" })
    public ResponseEntity<List<AlbumFoto>> getPhotosByUser(@PathVariable int userId) {
        return new ResponseEntity<>(albumFotosService.getByUserId(userId), HttpStatus.OK);
    }

    @PostMapping(value = "foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir una nueva foto", description = "Permite subir una nueva foto y asociarla a un usuario.", tags = {
            "Álbum de Fotos" })
    public ResponseEntity<?> uploadPhoto(
            @RequestParam MultipartFile file,
            @RequestParam int userId,
            @RequestParam(required = false) String descripcion) {
        try {
            // Crear un User temporal con solo el ID en lugar de usar userService
            User user = new User();
            user.setUseId(userId);

            String fileName = saveImage(file);

            AlbumFoto foto = new AlbumFoto();
            foto.setNombreFoto(file.getOriginalFilename());
            foto.setRutaArchivo(fileName);
            foto.setDescripcion(descripcion);
            foto.setFechaSubida(new Date());
            foto.setVisible(true);
            foto.setUser(user);

            AlbumFoto savedFoto = albumFotosService.save(foto);

            Map<String, Object> response = new HashMap<>();
            response.put("Status", true);
            response.put("Data", savedFoto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la foto: " + e.getMessage());
        }
    }

    @PutMapping(value = "foto", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualizar información de una foto", description = "Permite actualizar los detalles de una foto existente.", tags = {
            "Álbum de Fotos" })
    public ResponseEntity<?> updatePhoto(@RequestBody AlbumFoto foto) {
        AlbumFoto existingFoto = albumFotosService.getById(foto.getFotoId());
        if (existingFoto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto no encontrada");
        }

        // Mantener la ruta del archivo y el usuario
        foto.setRutaArchivo(existingFoto.getRutaArchivo());
        if (foto.getUser() == null) {
            foto.setUser(existingFoto.getUser());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", albumFotosService.save(foto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "foto/actualizar-imagen/{fotoId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Actualizar imagen de una foto existente", description = "Permite cambiar la imagen de una foto existente manteniendo el resto de información.", tags = {
            "Álbum de Fotos" })
    public ResponseEntity<?> updatePhotoImage(@PathVariable int fotoId, @RequestParam MultipartFile file) {
        try {
            AlbumFoto existingFoto = albumFotosService.getById(fotoId);
            if (existingFoto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto no encontrada");
            }

            String fileName = saveImage(file);

            existingFoto.setNombreFoto(file.getOriginalFilename());
            existingFoto.setRutaArchivo(fileName);
            existingFoto.setFechaSubida(new Date()); // Actualizar fecha de subida

            AlbumFoto updatedFoto = albumFotosService.save(existingFoto);

            Map<String, Object> response = new HashMap<>();
            response.put("Status", true);
            response.put("Data", updatedFoto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la foto: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "foto/{id}")
    @Operation(summary = "Eliminar una foto", description = "Elimina una foto específica identificada por su ID.", tags = {
            "Álbum de Fotos" })
    public ResponseEntity<?> deletePhoto(@PathVariable int id) {
        AlbumFoto existingFoto = albumFotosService.getById(id);
        if (existingFoto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto no encontrada");
        }

        albumFotosService.delete(id);

        Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Message", "Foto eliminada correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "foto/visibilidad/{id}")
    @Operation(summary = "Cambiar visibilidad de una foto", description = "Permite cambiar la visibilidad de una foto (visible/no visible).", tags = {
            "Álbum de Fotos" })
    public ResponseEntity<?> togglePhotoVisibility(@PathVariable int id, @RequestParam boolean visible) {
        AlbumFoto existingFoto = albumFotosService.getById(id);
        if (existingFoto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto no encontrada");
        }

        existingFoto.setVisible(visible);
        AlbumFoto updatedFoto = albumFotosService.save(existingFoto);

        Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", updatedFoto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/fotos/archivo/{filename}")
    @Operation(summary = "Obtener el archivo de una foto", description = "Permite obtener la imagen almacenada en el directorio de archivos del servidor.", tags = {
            "Álbum de Fotos" })
    public ResponseEntity<Resource> getPhotoFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String saveImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }
}