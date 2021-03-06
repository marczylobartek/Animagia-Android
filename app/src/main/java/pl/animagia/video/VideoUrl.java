package pl.animagia.video;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class VideoUrl {

    public static String getUrl(String html){
        String line = getLine(html);
        int firstIndex = line.indexOf("source src=") + "source src=".length()+1;
        int last = line.lastIndexOf(" type=");

        String customString = "";
        if (line.equals("")) {
            return line;
        } else {
            customString =  line.substring(firstIndex, last-1);
        }

        return customString;
    }

    private static String getLine(String html) {
        Boolean read = true;
        String urlLine = "";
        BufferedReader reader = new BufferedReader(new StringReader(html));
        try {
            String line = reader.readLine();
            while(line != null && read){
                if(line.contains("<source src=\"")){
                    urlLine = line;
                    read = false;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return urlLine;
    }

    /**
     * return message from server when link expired which start with "Ten link wygasł"
     * otherwise return empty string
     */
    public static String getMessageIfLinkExpired(String html){
        String line = getLineMessage(html);
        int firstIndex = line.indexOf("Ten link wygasł");

        String customString = "";
        if (line.equals("")) {
            return line;
        } else {
            customString =  line.substring(firstIndex);
        }

        return customString;
    }


    private static String getLineMessage(String html) {
        Boolean read = true;
        String MessageLine = "";
        BufferedReader reader = new BufferedReader(new StringReader(html));
        try {
            String line = reader.readLine();
            while(line != null && read){
                if(line.contains("Ten link wygasł")){
                    MessageLine = line;
                    read = false;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return MessageLine;
    }
}
