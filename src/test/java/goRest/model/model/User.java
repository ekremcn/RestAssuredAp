package goRest.model.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;


public class User {

    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;
    private String createdAt;
    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public String getCreatedAt() {
        return createdAt;
    }
    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
/*
{
            "id": 2108,
            "name": "Tony",
            "email": "ZV0YLN3E9ZOEA5Z5XESX@hotmail.com",
            "gender": "Male",
            "status": "Active",
            "created_at": "2020-08-31T21:45:45.513+05:30",
            "updated_at": "2020-08-31T21:45:45.513+05:30"
        }
 */