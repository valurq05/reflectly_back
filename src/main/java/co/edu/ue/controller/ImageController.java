package co.edu.ue.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import co.edu.ue.entity.Entry;
import co.edu.ue.entity.Image;
import co.edu.ue.entity.Person;
import co.edu.ue.entity.User;
import co.edu.ue.service.IEntryService;
import co.edu.ue.service.IImageService;
import co.edu.ue.service.IPersonService;
import co.edu.ue.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin("*")
public class ImageController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${api.gateway.url:http://localhost:5000}")
    private String apiGatewayUrl;

    @Autowired
    IImageService imageService;

    @Autowired
    IEntryService entryService;

    @Autowired
    IUserService userService;

    @Autowired
    IPersonService personService;

    @Autowired
    private RestTemplate restTemplate;

    // Constructor para inyectar RestTemplate si no está configurado en tu
    // aplicación
    public ImageController() {
        if (this.restTemplate == null) {
            this.restTemplate = new RestTemplate();
        }
    }

    @GetMapping(value = "images", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar todas las imágenes", description = "Devuelve una lista de todas las imágenes disponibles asociadas a las entradas de diario.", tags = {
            "Entradas de Diario" })
    public ResponseEntity<List<Image>> getAllImages() {
        return new ResponseEntity<List<Image>>(imageService.listAllImages(), HttpStatus.OK);
    }

    @GetMapping(value = "image", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtener una imagen por ID", description = "Devuelve los detalles de una imagen especíica identificada por su ID.", tags = {
            "Entradas de Diario" })
    public ResponseEntity<Image> getImage(@RequestParam int id) {
        return new ResponseEntity<Image>(imageService.findByIdImage(id), HttpStatus.OK);
    }

    @PostMapping(value = "image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Registrar una nueva imagen", description = "Permite agregar una nuva imagen asociada a una entrada de diario.", tags = {
            "Entradas de Diario" })
    public ResponseEntity<?> postImage(@RequestParam MultipartFile file, @RequestParam int entryId) {
        try {
            Image image = new Image();
            String filePath = saveImage(file);
            image.setImgUrl(filePath);
            Entry entry = entryService.findByIdEntry(entryId);
            image.setEntry(entry);

            Map<String, Object> response = new HashMap<>();
            response.put("Status", true);
            response.put("Data", imageService.addImage(image));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }

    @PostMapping(value = "user/profile/img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Actualizar con una nueva imagen la imagen de perfil del usuario", description = "Permite actualizar la imagen de perfil de usuario", tags = {
            "Usuarios" })
    public ResponseEntity<?> changeProfileImage(@RequestParam MultipartFile file, @RequestParam int userId) {
        try {
            String filePath = saveImage(file);
            User user = userService.findByIdUser(userId);
            Person person = user.getPerson();
            person.setPerPhoto(filePath);
            personService.upPerson(person);

            Map<String, Object> response = new HashMap<>();
            response.put("Status", true);
            response.put("Data", filePath);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }

    @PostMapping(value = "entry/img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Añadir imagenes a las entradas", description = "Permite subir una imagen a una entrada seleccionada", tags = {
            "Entradas de Diario" })
    public ResponseEntity<?> addEntryImage(@RequestParam MultipartFile file, @RequestParam int entryId) {
        try {
            String filePath = saveImage(file);
            Image image = new Image();
            Entry entry = entryService.findByIdEntry(entryId);

            image.setImgUrl(filePath);
            image.setEntry(entry);
            imageService.addImage(image);

            Map<String, Object> response = new HashMap<>();
            response.put("Status", true);
            response.put("Data", "Imagen de perfil actualizada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }

    @PostMapping(value = "clasificar-genero", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Clasificar género de una imagen", description = "Analiza una imagen y determina si corresponde a un hombre o una mujer utilizando el servicio de clasificación.", tags = {
            "Utilidades" })
    public ResponseEntity<?> clasificarGenero(@RequestParam MultipartFile file) {
        try {
            Map<String, Object> genderClassification = classifyGenderThroughGateway(file);

            return new ResponseEntity<>(genderClassification, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al clasificar el género de la imagen: " + e.getMessage());
        }
    }

    private String saveImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> classifyGenderThroughGateway(MultipartFile file) {
        try {
            // Configurar la petición
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Crear el cuerpo de la petición con la imagen
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

            // Crear un recurso a partir de los bytes de la imagen
            ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };

            // Añadir la imagen al cuerpo de la petición con el nombre 'imagen'
            // que es el que espera el gateway
            body.add("imagen", fileResource);

            // Crear la entidad HTTP
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // Hacer la petición POST al gateway
            return restTemplate.postForObject(apiGatewayUrl + "/clasificar-genero", requestEntity, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "No se pudo clasificar el género: " + e.getMessage());
            return errorResponse;
        }
    }

    @PutMapping(value = "image", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualizar una imagen", description = "Permite actualizar los detalles de una imagen existente asociada a una entrada de diario.", tags = {
            "Entradas de Diario" })
    public ResponseEntity<Image> putImage(@RequestBody Image image) {
        return new ResponseEntity<Image>(imageService.upImage(image), HttpStatus.OK);
    }

    @GetMapping("/images/{filename}")
    @Operation(summary = "Llamar una imagen", description = "Permite llamar una imagen almacenada en el directorio de archivos del servidor.", tags = {
            "Entradas de Diario" })
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
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
}