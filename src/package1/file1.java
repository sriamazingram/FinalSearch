package package1;
 
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import ir.summarization;
import ir.tf_idf1;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class file1 extends HttpServlet {
	tf_idf1 t;summarization s;
		public void init (ServletConfig config) throws ServletException {
			super.init(config);
			try {
				t=new tf_idf1();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				s=new summarization();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String query = request.getParameter("query");
        PrintWriter out = response.getWriter();
        //t=new tf_idf1();
		//s=new summarization();
        ArrayList<Path> resultPaths=t.getpaths(query);
		ArrayList<String> resultsum = null;
		try {
			resultsum = s.getSummary(resultPaths);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Path> resultPaths1=t.getpathwopos();
		ArrayList<String> resultsum1=null;
		try {
			resultsum1=s.getSummary(resultPaths1);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String content="";
        int j=1;
        for(int i=0;i<resultPaths.size();i++)
        {
        	if(i==0)
        	{
        		content+="<h1>TF-IDF based ranking</h1><br>";
        	}
        	if(i==resultPaths.size()/2)
        	{
        		j=1;
        		content+="<h1>Cosine similarity based ranking</h1><br>";
        	}
        	content+=""+
        			"				<h4>"+"Article "+j+":"+s.retTitle(resultPaths.get(i))+"</h4><br>\n" + 
        			"				<h5>Summary</h5>"+resultsum.get(i)+"<br>\n" + 
        			"                <button class=\"accordion\">Full Article</button><div class=\"panel\"><p>" + s.retContent(resultPaths.get(i))+"</p></div>\n"+
        			"";
        	j+=1;
        }
        j=1;
        String content1="";
        for(int i=0;i<resultPaths1.size();i++)
        {
        	if(i==0)
        	{
        		content1+="<h1>TF-IDF based ranking</h1><br>";
        	}
        	if(i==resultPaths.size()/2)
        	{
        		j=1;
        		content1+="<h1>Cosine similarity based ranking</h1><br>";
        	}
        	content1+=""+
        			"				<h4>"+"Article "+j+":"+s.retTitle(resultPaths1.get(i))+"</h4><br>\n" + 
        			"				<h5>Summary</h5>"+resultsum1.get(i)+"<br>\n" + 
        			"                <button class=\"accordion\">Full Article</button><div class=\"panel\"><p>" + s.retContent(resultPaths1.get(i))+"</p></div>\n"+
        			"";
        	j+=1;
        }
        String output="<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                "http://www.w3.org/TR/html4/loose.dtd\">\n" +
            "<html> \n" +
              "<head><style>"
              + "button.accordion {\n" + 
              "    background-color: #eee;\n" + 
              "    color: #444;\n" + 
              "    cursor: pointer;\n" + 
              "    padding: 18px;\n" + 
              "    width: 100%;\n" + 
              "    border: none;\n" + 
              "    text-align: left;\n" + 
              "    outline: none;\n" + 
              "    font-size: 15px;\n" + 
              "    transition: 0.4s;\n" + 
              "}\n" + 
              "\n" + 
              "button.accordion.active, button.accordion:hover {\n" + 
              "    background-color: #ccc;\n" + 
              "}\n" + 
              "\n" + 
              "div.panel {\n" + 
              "    padding: 0 18px;\n" + 
              "    background-color: white;\n" + 
              "    max-height: 0;\n" + 
              "    overflow: hidden;\n" + 
              "    transition: max-height 0.2s ease-out;\n" + 
              "}"
              + "</style> \n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
                  "charset=ISO-8859-1\"> \n" +
              "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
              + "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>"
              + "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script></head> \n" +
              "<body background='#E03A29'>\n" +
              "<hgroup class=\"mb20\">\n" + 
              "		<h1>Search Results</h1>\n" + 
              "		<h2 class=\"lead\"><strong class=\"text-danger\">"+resultPaths.size()/2+"</strong> results were found(Considering position) for the search for <strong class=\"text-danger\">"+query+"</strong></h2>								\n" +
              "<h2 class=\"lead\"><strong class=\"text-danger\">"+resultPaths1.size()/2+"</strong> results were found(Without considering position) for the search for <strong class=\"text-danger\">"+query+"</strong></h2>								\n"+
              "	</hgroup><table><tr><th>With Position Information</th><th>Without Postion Information</th></tr>"+
              "><tr><td>"+content+"</td><td>"+content1+"</td></tr></table>"
              + "<script>\n" + 
              "var acc = document.getElementsByClassName(\"accordion\");\n" + 
              "var i;\n" + 
              "\n" + 
              "for (i = 0; i < acc.length; i++) {\n" + 
              "  acc[i].onclick = function() {\n" + 
              "    this.classList.toggle(\"active\");\n" + 
              "    var panel = this.nextElementSibling;\n" + 
              "    if (panel.style.maxHeight){\n" + 
              "      panel.style.maxHeight = null;\n" + 
              "    } else {\n" + 
              "      panel.style.maxHeight = panel.scrollHeight + \"px\";\n" + 
              "    } \n" + 
              "  }\n" + 
              "}\n" + 
              "</script>"
              + "</body> \n" +
            "</html>" ;
        out.println (output);
        resultPaths.clear();
        resultPaths=null;
        resultPaths=new ArrayList<Path>();
        }
}