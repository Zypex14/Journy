package com.Roshan.Journy;

public class Event {

    private String data;
    private String[] options;
    private String text;
    private String title;

    public Event(String data) {
        this.data = data;
        title = "";

        for(int i = 0; i < data.length(); i++){
            char c = data.charAt(i);

            if(c == '{'){
                break;
            }

            title += c;
//            System.out.println(c);
        }

//        System.out.println("Title: " + title);

        data = data.replaceAll(title + "\\{|\\{ ", "");

        String s = data.replaceAll("}", "").split("<>")[1];
        options = s.replaceAll(" \\(|\\) |\\(|\\)$", "").split("\\)");

//        System.out.println("Length: " + options.length);
//        for(String s1 : options){
//            System.out.println(s1);
//        }

        text = data.split("<>")[0];


    }

    public String[] getOptions() {
        return options;
    }

    public String toString() {
        return data;
    }

    public String getText() {
        return text;
    }

    public String getTitle(){
        return title;
    }


}
