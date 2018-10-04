package ru.dkadyrov.stackoverflow.searcher.services;

import ru.dkadyrov.stackoverflow.searcher.model.Question;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface QuestionSearcher {

    CompletableFuture<List<Question>> getQuestions(String query);

}
