package ru.tinkoff.edu.java.scrapper.DTO.responses.stackOverflowClient;

import java.net.URL;

public record StackOverflowOwnerInfo(Integer reputation,
                                     Integer user_id,
                                     String user_type,
                                     Integer accept_rate,
                                     URL profile_image,
                                     String display_name,
                                     URL link) {}
