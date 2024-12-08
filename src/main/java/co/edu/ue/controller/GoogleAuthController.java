package co.edu.ue.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import co.edu.ue.entity.Person;
import co.edu.ue.entity.User;
import co.edu.ue.entity.UserRole;
import co.edu.ue.service.IPersonService;
import co.edu.ue.service.IUserRoleService;
import co.edu.ue.service.IUserService;
import co.edu.ue.utils.GoogleProperties;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/oauth")
@CrossOrigin("*")
public class GoogleAuthController {

    @Autowired
    IUserService userService;
    @Autowired
    IPersonService personService;
    @Autowired
    IUserRoleService userRoleService;
    @Autowired
    GoogleProperties googleProperties;

    @GetMapping("google")
    @Operation(
	    summary = "Inicio de sesión con Google",
	    description = "Permite a los usuarios autenticarse por medio del api de Google.",
	    tags = {"Google Log In"}
	)
    public ResponseEntity<?> handleGoogleCallback(@RequestParam String code) {

        Map<String, String> tokens = exchangeCodeForTokens(code);
        String accessToken = tokens.get("access_token");
        String refreshToken = tokens.get("refresh_token");

        Map<String, Object> userInfo = fetchUserInfo(accessToken);

        String googleEmail = (String) userInfo.get("email") != null ? (String) userInfo.get("email")
                : "emailgenerico@gmail.com";
        String googleName = (String) userInfo.get("name") != null ? (String) userInfo.get("name") : "Usuario Sin Identificar";
        String googleLastName = (String) userInfo.get("family_name") != null ? (String) userInfo.get("family_name")
                : "Apellido Sin Identificar";
        String googleSub = (String) userInfo.get("sub") != null ? (String) userInfo.get("sub") : "ID no proporcionado";
        String googlePicture = (String) userInfo.get("picture") != null ? (String) userInfo.get("picture")
                : "/images/GdXyg8gWgAAQmW1.jpg";

        User existingUser = userService.findByMailUser(googleEmail);

        if (existingUser == null){

            Person person = new Person();
            person.setPerName(googleName);
            person.setPerName((String) userInfo.get(googleName));
            person.setPerLastname((String) userInfo.get(googleLastName));
            person.setPerDocument((String) userInfo.get(googleSub));
            person.setPerPhoto((String) userInfo.get(googlePicture));
            personService.addPerson(person);

            User user = new User();
            user.setUseMail(googleEmail);
            user.setUsePassword("google");
            user.setPerson(person);
            userService.addUser(existingUser);

            UserRole newUserRole = new UserRole();
            newUserRole.setUseId(user.getUseId());
            newUserRole.setRolId(2);
            userRoleService.addUserRole(newUserRole);
        }

        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken,
                "userInfo", userInfo));
    }

    private Map<String, String> exchangeCodeForTokens(String code) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", System.getenv("GOOGLE_CLIENT_ID"));
        params.put("client_secret", System.getenv("GOOGLE_CLIENT_SECRET"));
        params.put("redirect_uri", "http://localhost:8080/oauth/google");
        params.put("grant_type", "authorization_code");

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://oauth2.googleapis.com/token",
                params,
                Map.class);

        Map<String, Object> body = response.getBody();
        String accessToken = (String) body.get("access_token");
        String refreshToken = (String) body.get("refresh_token");

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        return tokens;
    }

    private Map<String, Object> fetchUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String userInfoEndpoint = "https://openidconnect.googleapis.com/v1/userinfo";

        ResponseEntity<Map> response = restTemplate.getForEntity(
                userInfoEndpoint + "?access_token=" + accessToken,
                Map.class);

        return response.getBody();
    }
}
