package ru.dkadyrov.stackoverflow.searcher.internal;

import org.junit.Test;
import ru.dkadyrov.stackoverflow.searcher.model.Question;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class HttpQuestionSearcherTest {

    @Test
    public void searchByJavaQuery_returnNotEmptyResult() throws Exception {
        HttpQuestionSearcher questionSearcher = new HttpQuestionSearcher();
        List<Question> questions = questionSearcher.getQuestions("java").get(5, TimeUnit.SECONDS);
        assertNotNull(questions);
        assertFalse(questions.isEmpty());
    }

    @Test(expected = ExecutionException.class)
    public void serchByNullQuery_returnNull() throws Exception {
        HttpQuestionSearcher questionSearcher = new HttpQuestionSearcher();
        questionSearcher.getQuestions(null).get(5, TimeUnit.SECONDS);
    }
}