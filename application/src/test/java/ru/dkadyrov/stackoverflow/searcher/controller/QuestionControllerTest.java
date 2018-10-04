package ru.dkadyrov.stackoverflow.searcher.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.dkadyrov.stackoverflow.searcher.model.Question;
import ru.dkadyrov.stackoverflow.searcher.model.User;
import ru.dkadyrov.stackoverflow.searcher.services.QuestionSearcher;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QuestionController.class)
public class QuestionControllerTest {

    @MockBean
    QuestionSearcher questionService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getRequest_NotAllowed() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/search").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    @Test
    public void emptyRequestContent_badRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/search").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void searchRequestContent_Ok() throws Exception {


        List<Question> questions = getRandomQuestions(10);

        when(questionService.getQuestions(anyString()))
                .thenReturn(CompletableFuture.completedFuture(questions));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"text\":\"search\"}");

        MvcResult result = mvc.perform(requestBuilder)
                .andReturn();

        mvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[4].title", is(questions.get(4).getTitle())))
                .andExpect(jsonPath("$[2].answered", is(questions.get(2).isAnswered())));
    }

    @Test
    public void serviceThrowException_Ok() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"text\":\"search\"}");

        CompletableFuture<List<Question>> serviceResult = new CompletableFuture<>();
        serviceResult.completeExceptionally(new RuntimeException("Error"));

        when(questionService.getQuestions(anyString()))
                .thenReturn(serviceResult);

        MvcResult result = mvc.perform(requestBuilder)
                .andReturn();

        mvc.perform(asyncDispatch(result))
                .andExpect(status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());
    }

    private List<Question> getRandomQuestions(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> getRandomQuestion())
                .collect(Collectors.toList());
    }

    private Question getRandomQuestion() {
        return new Question(
                RandomStringUtils.randomAlphabetic(10),
                true,
                new User(
                        ThreadLocalRandom.current().nextLong(),
                        RandomStringUtils.randomAlphabetic(10),
                        RandomStringUtils.randomAlphabetic(10),
                        RandomStringUtils.randomAlphabetic(10)
                ),
                ThreadLocalRandom.current().nextLong(),
                RandomStringUtils.randomAlphabetic(10),
                ThreadLocalRandom.current().nextLong(),
                ThreadLocalRandom.current().nextLong(),
                ThreadLocalRandom.current().nextLong(),
                ThreadLocalRandom.current().nextLong(),
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                IntStream.range(0, ThreadLocalRandom.current().nextInt(3))
                        .mapToObj(any -> RandomStringUtils.randomAlphabetic(10))
                        .toArray(String[]::new)
        );
    }

}