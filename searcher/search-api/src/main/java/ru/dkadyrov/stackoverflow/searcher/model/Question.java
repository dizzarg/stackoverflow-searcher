package ru.dkadyrov.stackoverflow.searcher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

    private final String title;
    private final boolean answered;
    private final User owner;
    private final long creationDate;
    private final String link;
    private final String[] tags;
    private final long score;
    private final long id;
    private final long viewCount;
    private final long answerCount;
    private final long lastActivityDate;
    private final long lastEditDate;

    public Question(@JsonProperty("title") String title,
                    @JsonProperty("is_answered") boolean answered,
                    @JsonProperty("owner") User owner,
                    @JsonProperty("creation_date") Long creationDate,
                    @JsonProperty("link") String link,
                    @JsonProperty("question_id") long id,
                    @JsonProperty("score") long score,
                    @JsonProperty("view_count") long viewCount,
                    @JsonProperty("answer_count") long answerCount,
                    @JsonProperty("last_activity_date") long lastActivityDate,
                    @JsonProperty("last_edit_date") long lastEditDate,
                    @JsonProperty("tags") String[] tags) {
        this.id = id;
        this.title = title;
        this.answered = answered;
        this.owner = owner;
        this.creationDate = creationDate;
        this.link = link;
        this.score = score;
        this.viewCount = viewCount;
        this.answerCount = answerCount;
        this.lastActivityDate = lastActivityDate;
        this.lastEditDate = lastEditDate;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAnswered() {
        return answered;
    }

    public User getOwner() {
        return owner;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public String getLink() {
        return link;
    }

    public String[] getTags() {
        return tags;
    }

    public long getScore() {
        return score;
    }

    public long getId() {
        return id;
    }

    public long getViewCount() {
        return viewCount;
    }

    public long getAnswerCount() {
        return answerCount;
    }

    public long getLastActivityDate() {
        return lastActivityDate;
    }

    public long getLastEditDate() {
        return lastEditDate;
    }

    @Override
    public String toString() {
        return "Question{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", score=" + score +
                ", owner=" + owner +
                ", id=" + id +
                ", answered=" + answered +
                ", viewCount=" + viewCount +
                ", answerCount=" + answerCount +
                ", lastActivityDate=" + lastActivityDate +
                ", creationDate=" + creationDate +
                ", lastEditDate=" + lastEditDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return score == question.score &&
                id == question.id &&
                answered == question.answered &&
                viewCount == question.viewCount &&
                answerCount == question.answerCount &&
                lastActivityDate == question.lastActivityDate &&
                creationDate == question.creationDate &&
                lastEditDate == question.lastEditDate &&
                Objects.equals(title, question.title) &&
                Objects.equals(link, question.link) &&
                Arrays.equals(tags, question.tags) &&
                Objects.equals(owner, question.owner);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, link, score, owner, id, answered, viewCount, answerCount, lastActivityDate, creationDate, lastEditDate);
        result = 31 * result + Arrays.hashCode(tags);
        return result;
    }
}
