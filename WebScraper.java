import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class WebScraper {
    public static void main(String[] args) {
        // TODO: Scrape a URL and print its title

    String url="https://bbc.com";
    Document doc = Jsoup.connect(url).get();
    
    }


}
