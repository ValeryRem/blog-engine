package main.response;

import main.model.entity.Post;
// искусственный коммит 9.5.22
public class UserResponse {
    private Integer id;
    private String name;
    private String photo;

    public UserResponse() {
    }

    public UserResponse(Post post) {
    }

    public UserResponse(Integer id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
