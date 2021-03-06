package main.DTOEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import main.model.Post;
import org.springframework.cglib.core.internal.LoadingCache;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Data
public class StatisticsBlogDto
{

    Integer postsCount = 0;

    Integer likesCount = 0;

    Integer dislikesCount = 0;

    Integer viewsCount = 0;

    //@JsonProperty(value = "Первая публикация")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    LocalDateTime firstPublication = null;

    public StatisticsBlogDto(List<Post> posts)
    {
        posts.forEach(post -> {
            this.postsCount++;
            this.likesCount = this.likesCount + post.getLikesUsers().size();
            this.dislikesCount = this.getDislikesCount() + post.getDisLikesUsers().size();
            this.viewsCount = this.viewsCount + post.getViewCount();

            if(this.firstPublication == null)
            {
                this.firstPublication = post.getTime();
            }
            if(this.firstPublication.isAfter(post.getTime()))
            {
                this.firstPublication = post.getTime();
            }
        });
    }


}
