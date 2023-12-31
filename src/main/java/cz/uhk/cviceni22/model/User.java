package cz.uhk.cviceni22.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Required")
    @Size(min = 1, message = "Required")
    @Column(name = "name")
    private String name;

    @Min(value = 10, message = "Minimum is 10")
    @Max(value = 20, message = "Maximum is 20")
    @Column(name = "age")
    private int age;

    @Column(name = "eyes")
    private String eyes;

    @Column(name = "gender")
    private String gender = "Male";

    @Column(name = "agree")
    @AssertTrue(message = "You have to agree")
    private boolean agree;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;

    public void addPost(Post post){
        if(posts == null)
            posts = new ArrayList<>();

        post.setUser(this);
        posts.add(post);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", eyes='" + eyes + '\'' +
                ", gender='" + gender + '\'' +
                ", agree=" + agree +
                '}';
    }
}
