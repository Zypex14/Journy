package com.Roshan.Journy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private Scanner sc;
    private String input;
    private String story;
    private Path p;
    private HashMap<String, Event> hash;
    private ArrayList<String> history;
    private int wrap;


    public Main() {
        wrap = 100;
        sc = new Scanner(System.in);
        p = Paths.get("src\\com\\Roshan\\Journy\\story.txt");
        story = "";
        history = new ArrayList<>();
        history.add("Exposition");

        try {
            for (String s : Files.readAllLines(p)) {
                if (s.length() > 0) {
                    s += " ";
                }

                story += s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        hash = new HashMap<>();
        divide(story);

//        System.out.println("Length : " + hash.size());
//
//        for(Event e : hash.values()){
//            System.out.println(e.toString());
//        }

        while (true) {

            Event currentIndex = hash.get(history.get(history.size() - 1));
            int i0 = 0;
            for (String s : currentIndex.getText().split("\\[\\.\\.\\.\\]")) {
                print(s);
                if (i0 != currentIndex.getText().split("\\[\\.\\.\\.\\]").length - 1) {
                    print("[PRESS ENTER]");
                    sc.nextLine();
                }
                i0++;


            }

            sleep(500);


//            System.out.println("Length: " + currentIndex.getOptions().length);
            for (int i = 0; i < currentIndex.getOptions().length; i++) {

                String option = (hash.get(currentIndex.getOptions()[i]).getTitle().replaceAll(" ", "").equals(""))? currentIndex.getOptions()[i] : hash.get(currentIndex.getOptions()[i]).getTitle();

                print((i + 1) + ". " + option);
            }

            while (true) {
                try {
                    input = sc.nextLine();
                    int index = Integer.parseInt(input.replaceAll("[^\\d]", ""));

                    try {

                        history.add(currentIndex.getOptions()[index - 1]);

                        break;
                    } catch (IndexOutOfBoundsException e) {
                        print("That is not a possible choice");
                    }
                } catch (NumberFormatException e) {
                    print("That is not a number");
                }
            }


        }

    }

    public static void main(String[] args) {
        new Main();
    }

    public void print(String s) {

        int ms = Math.round(200 / s.length());
//        System.out.println();
        int line = 0;
        for (int i = 0; i < s.length(); i++) {
            line += 1;

            if (line > wrap && s.charAt(i) == ' ') {
                System.out.println();
                line = 0;
            }

            System.out.print(s.charAt(i));
            sleep(1);
        }

        System.out.println();

    }

    public void sleep(int ms) {

        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void divide(String s) {

        for (int i = 0; i < s.length(); i++) {

//            System.out.println("iteration");

            String index = "";
            String data = "";

            if (s.charAt(i) == '(') {

                while (s.charAt(i + 1) != ')') {
                    i++;
                    index += s.charAt(i);
                }

                i++;

                while (s.charAt(i) != '}') {
                    i++;
                    data += s.charAt(i);
                }

                Event e = new Event(data.replaceAll(" {2,}", " "));

                hash.put(index, new Event(data.replaceAll(" {2,}", " ")));
//                System.out.println("Index: " + index);

            }

        }

    }

}
