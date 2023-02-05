package com.fincorp.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.util.Map;
import java.io.*;

@RestController
public class HelloWorldController 
{

@RequestMapping("/")
public String hello() throws UnknownHostException  
{

   String hostname = InetAddress.getLocalHost().getHostName();

   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now();  
   
   //String color = String.format("#%06x", Integer.parseInt(hostname));
   String color = (String.format("#%h", hostname+hostname)).substring(0, 7);

   StringBuffer sbf1 = new StringBuffer("<html><head><title>SpringBoot Demo from :" + hostname + " </title></hide><body><style>" + 

    ".styled-table { width: 850px; border-collapse: collapse; margin: 25px 0; font-size: 0.9em; font-family: sans-serif; min-width: 400px; box-shadow: 0 0 20px rgba(0, 0, 0, 0.15); margin-left: auto; margin-right: auto; } " + 
    
    ".styled-table thead tr { background-color: #009879; color: #ffffff; text-align: left; } " + 
    
    ".styled-table th, .styled-table td { padding: 12px 15px; } " + 
    
    ".styled-table tbody tr { border-bottom: 1px solid #dddddd; } " + 
    
    ".styled-table tbody tr:nth-of-type(even) { background-color: #f3f3f3; } " + 
    
    ".styled-table tbody tr:last-of-type { border-bottom: 2px solid #009879; } " + 
    ".styled-table tbody tr.active-row { font-weight: bold; color: #009879; } " + 

    "</style> " );



   sbf1.append("<center> <br><br><b> Hello </b> <br><br> From container Host: <br><b> <p style='color:" + color + ";'>" +  
   hostname + //" , color: " + color + 

   "</p><br><br>" + dtf.format(now) +
   "</b><br> <br>----------------------------<br><br>SYSTEM ENV VARs : " + 
   "<table class='styled-table'> <thead> <tr><th>Variable</th><th>Value</th></tr></thead><tbody>");


  Map<String, String> env = System.getenv();


  for(String envName : env.keySet()){
      sbf1.append("<tr><td><b>" + envName + "</b></td><td style='word-break: break-all;'>" +  env.get(envName) + "</td></tr>");
  }
 
  sbf1.append("</tbody></table>");

  
  // Check if the specified file
  // Exists or not
  try {

   // Get the file
   File file = new File(System.getenv("APP_FILE_CONFIG_PATH")); 
 
   if (file.exists())
   {
     sbf1.append("</b><br> <br>----------------------------<br><b>APP CONFIG FILE</b> :  " + file + " : <br><br>"); 
     BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

     String curLine;
     while ((curLine = bufferedReader.readLine()) != null){
            //process the line as required
         sbf1.append(curLine + "<br>"); 
     }
     bufferedReader.close();
   }          
  } catch (Exception ex) {
   sbf1.append("</b><br> <br>----------------------------<br>APP_FILE_CONFIG_PATH : NO FILE<br><br>") ; // Handle exception
  }

  sbf1.append("<br><br><br></center>");

   return sbf1.toString();
}

}
