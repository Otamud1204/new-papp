
package uz.app.entity;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("unused")
@Data
@AllArgsConstructor@NoArgsConstructor
public class Post {

    @Expose
    private String category;
    @Expose
    private String content;
    @Expose
    private Long id;
    @Expose
    private String image;
    @Expose
    private String publishedAt;
    @Expose
    private String slug;
    @Expose
    private String status;
    @Expose
    private String thumbnail;
    @Expose
    private String title;
    @Expose
    private String updatedAt;
    @Expose
    private String url;
    @Expose
    private Long userId;


}
