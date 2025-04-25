import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

import javax.lang.model.util.Elements;

public class WebScraper {
    public static void main(String[] args) {
        // TODO: Scrape a URL and print its title

    String url="https://bbc.com";

    Document doc = Jsoup.connect(url).get();

    System.out.println("Title: " + doc.title());
   
    for (int i = 1; i <= 6; i++) {
                Elements headings = doc.select("h" + i);
                for (Element heading : headings) {
                    System.out.println("Heading h" + i + ": " + heading.text());
                }
            }

            Elements links = doc.select("a[href]");
            for (Element link : links) {
                System.out.println("Link: " + link.attr("abs:href") + " - Text: " + link.text());
            }

     } 
    
     public class NewsItem {
        private String headline;
        private String date;
        private String author;
    
        public NewsItem(String headline, String date, String author) {
            this.headline = headline;
            this.date = date;
            this.author = author;
        }

        public String getHeadline() {
            return headline;
        }

        public void setHeadline(String headline) {
            this.headline = headline;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

    }    
}
 

