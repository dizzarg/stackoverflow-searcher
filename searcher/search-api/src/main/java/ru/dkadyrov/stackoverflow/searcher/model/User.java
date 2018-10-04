package ru.dkadyrov.stackoverflow.searcher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private long id;
    private String profileImage;
    private String name;
    private String link;

    public User(@JsonProperty("user_id") long id,
                @JsonProperty("profile_image") String profileImage,
                @JsonProperty("display_name") String name,
                @JsonProperty("link") String link) {
        this.id = id;
        this.profileImage = profileImage;
        this.name = name;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", profileImage='" + profileImage + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User owner = (User) o;
        return id == owner.id &&
                Objects.equals(profileImage, owner.profileImage) &&
                Objects.equals(name, owner.name) &&
                Objects.equals(link, owner.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileImage, name, link);
    }
}
