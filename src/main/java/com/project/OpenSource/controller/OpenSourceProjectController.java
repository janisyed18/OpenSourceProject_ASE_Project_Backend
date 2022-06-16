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
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
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


    @ApiOperation(value = "Create project")
    @ApiResponse(code = 200, message = "Successfully created project")
    @RequestMapping(value = "project", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> createProject(@RequestBody Project project) {
        CommonResponse response = null;
        String query = null;
        try {
            query = "INSERT INTO opensourceproject.project (name, description, created_by, status, license, created_date, acknowledged_date, acknowledged_by, reason, last_modified_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(query, project.getName(), project.getDescription(), project.getCreatedBy(), project.getStatus(), project.getLicense(), project.getCreatedDate(), project.getAcknowledgedDate(), project.getAcknowledgedBy(), project.getReason(), project.getLastModifiedDate());
            response = new CommonResponse();
            response.setMessage("Project got created successfully.");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Update project")
    @ApiResponse(code = 200, message = "Successfully updated project")
    @RequestMapping(value = "project/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CommonResponse> updateProject(@RequestBody Project project, @PathVariable Integer id) {
        CommonResponse response = null;
        String query = null;
        try {
            query = "UPDATE opensourceproject.project SET name = ?, description = ?, created_by = ?, status = ?, license = ?, created_date = ?, acknowledged_date = ?, acknowledged_by = ?, reason = ?, last_modified_date = ? WHERE id = ?";
            jdbcTemplate.update(query, project.getName(), project.getDescription(), project.getCreatedBy(), project.getStatus(), project.getLicense(), project.getCreatedDate(), project.getAcknowledgedDate(), project.getAcknowledgedBy(), project.getReason(), project.getLastModifiedDate(), id);
            response = new CommonResponse();
            response.setMessage("Project got updated successfully.");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "delete project")
    @ApiResponse(code = 200, message = "Successfully deleted project")
    @RequestMapping(value = "project/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CommonResponse> deleteProject(@PathVariable Integer id) {
        CommonResponse response = null;
        String query = null;
        try {
            query = "delete from opensourceproject.project where id = ?";
            jdbcTemplate.update(query, id);
            response = new CommonResponse();
            response.setMessage("Project got deleted successfully.");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Get project")
    @ApiResponse(code = 200, message = "Successfully getting project")
    @RequestMapping(value = "project/{id}", method = RequestMethod.GET)
    public ResponseEntity<Project> getProject(@PathVariable Integer id) {
        Project response = new Project();
        String sqlQuery = null;
        try {
            sqlQuery = "SELECT id, name, description, created_by, status, license, created_date, acknowledged_date, acknowledged_by, reason, last_modified_date FROM opensourceproject.project where id = ?";
            Map<String, Object> resultMap = jdbcTemplate.queryForMap(sqlQuery, id);

            response.setId((Integer) resultMap.get("id"));
            response.setName((String) resultMap.get("name"));
            response.setDescription((String) resultMap.get("description"));
            response.setCreatedBy((String) resultMap.get("created_by"));
            response.setStatus((String) resultMap.get("status"));
            response.setLicense((String) resultMap.get("license"));
            response.setCreatedDate((Timestamp) resultMap.get("created_date"));
            response.setAcknowledgedDate((Timestamp) resultMap.get("acknowledged_date"));
            response.setAcknowledgedBy((String) resultMap.get("acknowledged_by"));
            response.setReason((String) resultMap.get("reason"));
            response.setLastModifiedDate((Timestamp) resultMap.get("last_modified_date"));

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Get projects")
    @ApiResponse(code = 200, message = "Successfully getting projects")
    @RequestMapping(value = "project", method = RequestMethod.GET)
    public ResponseEntity<List<Project>> getAllProjects() {
        String sqlQuery = null;
        List<Project> projectList = new ArrayList<>();
        try {
            sqlQuery = "SELECT id, name, description, created_by, status, license, created_date, acknowledged_date, acknowledged_by, reason, last_modified_date FROM opensourceproject.project";
            List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sqlQuery);
            for(Map<String, Object> resultMap : resultList) {
                Project project = new Project();
                project.setId((Integer) resultMap.get("id"));
                project.setName((String) resultMap.get("name"));
                project.setDescription((String) resultMap.get("description"));
                project.setCreatedBy((String) resultMap.get("created_by"));
                project.setStatus((String) resultMap.get("status"));
                project.setLicense((String) resultMap.get("license"));
                project.setCreatedDate((Timestamp) resultMap.get("created_date"));
                project.setAcknowledgedDate((Timestamp) resultMap.get("acknowledged_date"));
                project.setAcknowledgedBy((String) resultMap.get("acknowledged_by"));
                project.setReason((String) resultMap.get("reason"));
                project.setLastModifiedDate((Timestamp) resultMap.get("last_modified_date"));
                projectList.add(project);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @ApiOperation(value = "Get projects using createdBy")
    @ApiResponse(code = 200, message = "Successfully getting projects")
    @RequestMapping(value = "project/createdby/{createdBy}", method = RequestMethod.GET)
    public ResponseEntity<List<Project>> getAllProjects(@PathVariable String createdBy) {
        String sqlQuery = null;
        List<Project> projectList = new ArrayList<>();
        try {
            sqlQuery = "SELECT id, name, description, created_by, status, license, created_date, acknowledged_date, acknowledged_by, reason, last_modified_date FROM opensourceproject.project where created_by = ?";
            List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sqlQuery, createdBy);
            for(Map<String, Object> resultMap : resultList) {
                Project project = new Project();
                project.setId((Integer) resultMap.get("id"));
                project.setName((String) resultMap.get("name"));
                project.setDescription((String) resultMap.get("description"));
                project.setCreatedBy((String) resultMap.get("created_by"));
                project.setStatus((String) resultMap.get("status"));
                project.setLicense((String) resultMap.get("license"));
                project.setCreatedDate((Timestamp) resultMap.get("created_date"));
                project.setAcknowledgedDate((Timestamp) resultMap.get("acknowledged_date"));
                project.setAcknowledgedBy((String) resultMap.get("acknowledged_by"));
                project.setReason((String) resultMap.get("reason"));
                project.setLastModifiedDate((Timestamp) resultMap.get("last_modified_date"));
                projectList.add(project);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }
}






