package ru.dkadyrov.stackoverflow.searcher.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import ru.dkadyrov.stackoverflow.searcher.model.Query;
import ru.dkadyrov.stackoverflow.searcher.services.QuestionSearcher;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    private static final long REQUEST_TIMEOUT = 5_000L;
    private final QuestionSearcher questionSearcher;

    public QuestionController(QuestionSearcher questionSearcher) {
        this.questionSearcher = questionSearcher;
    }

    @PostMapping(value = "/search")
    public DeferredResult<ResponseEntity<?>> search(@RequestBody Query query) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>(REQUEST_TIMEOUT);

        if (query == null || StringUtils.isBlank(query.getText())) {
            result.setResult(ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Nothing to find."));
            return result;
        }

        questionSearcher.getQuestions(query.getText())
                .thenAccept(questions -> result.setResult(ResponseEntity.ok(questions)))
                .exceptionally(throwable -> {
                    logger.error("Error searching questions", throwable);
                    result.setResult(ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(ExceptionUtils.getStackTrace(throwable)));
                    return null;
                });

        return result;
    }

}
