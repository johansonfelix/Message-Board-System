package com.company;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        BusinessLayer msgboard = new BusinessLayer();

        //System.out.println(msgboard.createPost("01","Hey","001",LocalDate.parse("2020-10-01")).toString());
        msgboard.createPost("01","Hey","001", LocalDateTime.parse("2020-10-01"), null);
        msgboard.createPost("02","Hey","002",LocalDateTime.parse("2020-11-01"), null);
        msgboard.createPost("03","Hey","003",LocalDateTime.parse("2020-10-05"),null);
        msgboard.createPost("04","Hey","004",LocalDateTime.parse("2020-10-01"), null);
        msgboard.createPost("05","Hey","005",LocalDateTime.parse("2020-10-06"), null);
        msgboard.createPost("06","Hey","006",LocalDateTime.parse("2020-04-01"), null);
        msgboard.createPost("07","Hey","007",LocalDateTime.parse("2020-10-02"), null);
        msgboard.createPost("08","Hey","008",LocalDateTime.parse("2020-11-02"), null);
        msgboard.createPost("09","Hey","009",LocalDateTime.parse("2020-10-10"), null);
        msgboard.createPost("10","Hey","010",LocalDateTime.parse("2020-10-09"),null);
        msgboard.deletePost("007","07");
        System.out.println(msgboard.viewRecentPosts());
    }
}
