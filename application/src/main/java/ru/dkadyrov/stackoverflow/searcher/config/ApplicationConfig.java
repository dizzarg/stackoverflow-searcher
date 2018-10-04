package ru.dkadyrov.stackoverflow.searcher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dkadyrov.stackoverflow.searcher.internal.HttpQuestionSearcher;
import ru.dkadyrov.stackoverflow.searcher.services.QuestionSearcher;

@Configuration
public class ApplicationConfig {

    @Bean
    public QuestionSearcher questionSearcher() {
        return new HttpQuestionSearcher();
    }

}
