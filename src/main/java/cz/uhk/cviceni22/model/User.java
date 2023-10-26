package cz.uhk.cviceni22.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Value;

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

    @AssertTrue(message = "You have to agree")
    @Column(name = "agree")
    private boolean agree;

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
