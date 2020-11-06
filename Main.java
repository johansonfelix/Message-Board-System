package com.company;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        BusinessLayer msgboard = new BusinessLayer();

        //System.out.println(msgboard.createPost("01","Hey","001",LocalDate.parse("2020-10-01")).toString());
        msgboard.createPost("01","Hey","001",LocalDate.parse("2020-10-01"));
        msgboard.createPost("02","Hey","002",LocalDate.parse("2020-11-01"));
        msgboard.createPost("03","Hey","003",LocalDate.parse("2020-10-05"));
        msgboard.createPost("04","Hey","004",LocalDate.parse("2020-10-01"));
        msgboard.createPost("05","Hey","005",LocalDate.parse("2020-10-06"));
        msgboard.createPost("06","Hey","006",LocalDate.parse("2020-04-01"));
        msgboard.createPost("07","Hey","007",LocalDate.parse("2020-10-02"));
        msgboard.createPost("08","Hey","008",LocalDate.parse("2020-11-02"));
        msgboard.createPost("09","Hey","009",LocalDate.parse("2020-10-10"));
        msgboard.createPost("10","Hey","010",LocalDate.parse("2020-10-09"));
        msgboard.deletePost("007","07");
        System.out.println(msgboard.viewRecentPosts());
    }
}
