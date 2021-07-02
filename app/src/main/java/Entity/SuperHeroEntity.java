package Entity;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.net.URI;
@Entity(tableName = "Hero_Table", indices = {@Index(value = {"realName"},unique = true)})
public class SuperHeroEntity {
    @NonNull
    @PrimaryKey
    private String realName;
    private String name;
    private String team;
    private String firstAppearance;
    private String createdBy;
    private String publisher;
    private String imageUrl;
    private String bio;
    public SuperHeroEntity(){

    }
  public SuperHeroEntity(@NonNull String RealName,String Name,  String Team, String FirstAppearance,
                         String CreatedBy, String Publisher, String ImageUrl, String bio){
      this.name = Name;
      this.realName = RealName;
      this.team = Team;
      this.firstAppearance = FirstAppearance;
      this.createdBy = CreatedBy;
      this.publisher = Publisher;
      this.imageUrl = ImageUrl;
      this.bio = bio;


  }

    public String getName() {
        return this.name;
    }
    @NonNull
    public String getRealName() {
        return this.realName;
    }

    public String getTeam() {
        return this.team;
    }

    public String getFirstAppearance() {
        return this.firstAppearance;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getBio() {
        return this.bio;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @NonNull
    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
