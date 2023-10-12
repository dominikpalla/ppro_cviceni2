package cz.uhk.cviceni22;

import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Value;

public class User {

    @NotNull(message = "Required")
    @Size(min = 1, message = "Required")
    private String name;

    @Min(value = 10, message = "Minimum is 10")
    @Max(value = 20, message = "Maximum is 20")
    private int age;
    private String eyes;
    private String gender = "Male";

    @AssertTrue(message = "You have to agree")
    private boolean agree;

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
}
