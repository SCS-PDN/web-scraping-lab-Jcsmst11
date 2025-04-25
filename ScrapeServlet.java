
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
public class ScrapeServlet{

    protected void {
        
        HttpSession session = request.getSession();
        Integer visitCount = (Integer) session.getAttribute("visitCount");
        if (visitCount == null) visitCount = 0;
        session.setAttribute("visitCount", visitCount + 1);

        String url = request.getParameter("url");
        String[] options = request.getParameterValues("options");

        List<String[]> scrapedData = new ArrayList<>();

        if (url != null && options != null) {
            
                Document doc = Jsoup.connect(url).get();

                for (String option : options) {
                    if (option.equals("title")) {
                        String title = doc.title();
                        scrapedData.add(new String[]{"Title", title});
                    } else if (option.equals("links")) {
                        Elements links = doc.select("a[href]");
                        for (Element link : links) {
                            scrapedData.add(new String[]{"Link", link.attr("abs:href"), link.text()});
                        }
                    } else if (option.equals("images")) {
                        Elements images = doc.select("img[src]");
                        for (Element img : images) {
                            scrapedData.add(new String[]{"Image", img.attr("abs:src")});
                        }
                    }
                }
        }

       
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        
        out.println("<p>You have visited this page " + (visitCount + 1) + " times.</p>");

       
        out.println("<table border='1'>");
        for (String[] row : scrapedData) {
            out.println("<tr>");
            for (String cell : row) {
                out.println("<td>" + cell + "</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");

       
        Gson gson = new Gson();
        String json = gson.toJson(scrapedData);

        out.println("<h3>JSON Output:</h3>");
        out.println("<pre>" + json + "</pre>");

      
        out.println("<form method='post' action='DownloadCSVServlet'>");
        out.println("<input type='hidden' name='jsonData' value='" + json.replace("'", "&#39;") + "'/>");
        out.println("<input type='submit' value='Download CSV'/>");
        out.println("</form>");
    }
}


