package ru.tinkoff.edu.java.scrapper.DTO.responses.stackOverflowClient;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.List;

public record StackOverflowQuestionInfo(List<String> tags,
                                        StackOverflowOwnerInfo owner,
                                        Boolean is_answered,
                                        Long view_count,
                                        OffsetDateTime creation_date,
                                        OffsetDateTime last_edit_date,
                                        Long question_id,
                                        URL link) {
}
