package ru.dkadyrov.stackoverflow.searcher.internal;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dkadyrov.stackoverflow.searcher.internal.models.Response;
import ru.dkadyrov.stackoverflow.searcher.model.Question;
import ru.dkadyrov.stackoverflow.searcher.services.QuestionSearcher;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Http service with
 *
 * @see <a href="http://api.stackexchange.com/docs/search">StackOverflow API</a>
 *
 */
public class HttpQuestionSearcher implements QuestionSearcher, Closeable {
    private static final Logger log = LoggerFactory.getLogger(HttpQuestionSearcher.class);

    private static final URI API_URI = URI.create("http://api.stackexchange.com/2.2/search");

    private final CloseableHttpAsyncClient httpClient;
    private final Marshaller marshaller;

    public HttpQuestionSearcher() {
        httpClient = HttpAsyncClients.createDefault();
        httpClient.start();
        marshaller = new Marshaller();
    }

    public CompletableFuture<List<Question>> getQuestions(String query) {
        try {
            URI requestURI = new URIBuilder(API_URI)
                    .setParameter("order", "desc")
                    .setParameter("sort", "relevance")
                    .setParameter("intitle", query)
                    .setParameter("site", "stackoverflow")
                    .build();

            HttpGet request = new HttpGet(requestURI);

            return toCompletableFuture(f -> httpClient.execute(request, f))
                    .thenApplyAsync(this::handleResponse);

        } catch (URISyntaxException e) {
            log.error("Error occurred while preparing HTTP request", e);
            CompletableFuture<List<Question>> failed = new CompletableFuture<>();
            failed.completeExceptionally(e);
            return failed;
        }
    }

    private List<Question> handleResponse(HttpResponse httpResponse)  {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            throw new RuntimeException("Returning bad request: Status code " + statusCode);
        }
        try {
            String json = IOUtils.unzipStream(httpResponse.getEntity().getContent());
            return marshaller.marshaling(json, Response.class).getItems();
        } catch (IOException e) {
            throw new RuntimeException("Fail to read response", e);
        }
    }

    private static CompletableFuture<HttpResponse> toCompletableFuture(Consumer<FutureCallback<HttpResponse>> c) {
        CompletableFuture<HttpResponse> future = new CompletableFuture<>();

        c.accept(new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse t) {
                future.complete(t);
            }

            @Override
            public void failed(Exception e) {
                future.completeExceptionally(e);
            }

            @Override
            public void cancelled() {
                future.cancel(true);
            }
        });
        return future;
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }
}
