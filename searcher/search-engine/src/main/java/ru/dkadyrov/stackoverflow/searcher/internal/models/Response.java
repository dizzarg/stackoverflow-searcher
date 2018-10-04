package ru.dkadyrov.stackoverflow.searcher.internal.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.dkadyrov.stackoverflow.searcher.model.Question;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    private final List<Question> items;
    private final boolean hasMore;

    public Response(@JsonProperty("items") List<Question> items,
                    @JsonProperty("has_more") boolean hasMore) {
        this.items = items;
        this.hasMore = hasMore;
    }

    public List<Question> getItems() {
        return items;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    @Override
    public String toString() {
        return "Response{" +
                "items=" + items +
                ", hasMore=" + hasMore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return hasMore == response.hasMore &&
                Objects.equals(items, response.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, hasMore);
    }
}
