package com.project.OpenSource.controller;

import com.project.OpenSource.configuration.JWTAuthentication;
import com.project.OpenSource.model.CommonResponse;
import com.project.OpenSource.model.LoginRequest;
import com.project.OpenSource.model.Project;
import com.project.OpenSource.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@Api(value = "open-source-project", description = "open-source-project")
@RequestMapping(value = "open-source-project")
@RestController
public class OpenSourceProjectController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JWTAuthentication jWTAuthentication;

    @ApiOperation(value = "User register")
    @ApiResponse(code = 200, message = "Successfully registered user")
    @RequestMapping(value = "user/signup", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> userRegister(@RequestBody User user) {
        CommonResponse response = null;
        String query = null;
        try {
            query = "INSERT INTO opensourceproject.users (first_name, last_name, email, password, role) VALUES ( ?, ?, ?, ?, ?) ";
            jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
            response = new CommonResponse();
            response.setMessage("User got registered successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "User Login")
    @ApiResponse(code = 200, message = "Successfully LoggedIn")
    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public ResponseEntity<User> userLogin(@RequestBody LoginRequest request) {
        User response = null;
        String query = null;
        try {
            query = "SELECT id, first_name, last_name, email, role FROM opensourceproject.users where email=? AND password=?";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, request.getEmail(), request.getPassword());
            if (rows.size() > 0) {
                for (Map<String, Object> rs : rows) {
                    response = new User();
                    response.setId((Integer) rs.get("id"));
                    response.setFirstName((String) rs.get("first_name"));
                    response.setLastName((String) rs.get("last_name"));
                    response.setEmail((String) rs.get("email"));
                    response.setRole((String) rs.get("role"));
                    String jwtToken = jWTAuthentication.getToken(response.getId().toString(), response.getRole());
                    response.setJwtToken(jwtToken);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

 ////////////////////////////////////////////////////////////////////////////

    @ApiOperation(value = "Create project")
    @ApiResponse(code = 200, message = "Successfully created project")
    @RequestMapping(value = "project", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> createProject(@RequestBody Project project) {
        CommonResponse response = null;
        String query = null;
        try {
            query = "INSERT INTO opensourceproject.users (first_name, last_name, email, password, role) VALUES ( ?, ?, ?, ?, ?) ";
            jdbcTemplate.update(query, project.getFirstName(), project.getLastName(), project.getEmail(), project.getPassword(), project.getRole());
            response = new CommonResponse();
            response.setMessage("User got registered successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Update project")
    @ApiResponse(code = 200, message = "Successfully updated project")
    @RequestMapping(value = "project", method = RequestMethod.PUT)
    public ResponseEntity<CommonResponse> updateProject(@RequestBody User user) {
        CommonResponse response = null;
        String query = null;
        try {
            query = "INSERT INTO opensourceproject.users (first_name, last_name, email, password, role) VALUES ( ?, ?, ?, ?, ?) ";
            jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
            response = new CommonResponse();
            response.setMessage("User got registered successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "delete project")
    @ApiResponse(code = 200, message = "Successfully deleted project")
    @RequestMapping(value = "project", method = RequestMethod.DELETE)
    public ResponseEntity<CommonResponse> deleteProject(@RequestBody User user) {
        CommonResponse response = null;
        String query = null;
        try {
            query = "INSERT INTO opensourceproject.users (first_name, last_name, email, password, role) VALUES ( ?, ?, ?, ?, ?) ";
            jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
            response = new CommonResponse();
            response.setMessage("User got registered successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Get project")
    @ApiResponse(code = 200, message = "Successfully getting project")
    @RequestMapping(value = "project", method = RequestMethod.GET)
    public ResponseEntity<CommonResponse> getProject(@RequestBody User user) {
        CommonResponse response = null;
        String query = null;
        try {
            query = "INSERT INTO opensourceproject.users (first_name, last_name, email, password, role) VALUES ( ?, ?, ?, ?, ?) ";
            jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
            response = new CommonResponse();
            response.setMessage("User got registered successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}


    INSERT INTO opensourceproject.project ( name, description, proposed_by, status, license, created_date, approved_date, approved_by, rejection_reason, last_modified_date, rejected_date, rejected_by) VALUES (<{id: }>, <{name: }>, <{description: }>, <{proposed_by: }>, <{status: }>, <{license: }>, <{created_date: }>, <{approved_date: }>, <{approved_by: }>, <{rejection_reason: }>, <{last_modified_date: }>, <{rejected_date: }>, <{rejected_by: }>);
