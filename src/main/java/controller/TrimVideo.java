package controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.EagerTransformation;
import com.cloudinary.utils.ObjectUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.el.ELException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.User;
import model.bean.Worker;
import model.bo.*;
/**
 * Servlet implementation class TrimVideo
 */

@WebServlet(name = "editor", urlPatterns = "/editor")
@MultipartConfig(maxFileSize = 104857600, maxRequestSize = 104857600)
public class TrimVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrimVideo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private static final BlockingQueue<HttpServletRequest> requestQueue = new LinkedBlockingQueue<>();

    public void init() throws ServletException {

        Thread requestProcessorThread = new Thread(() -> {
            while (true) {
                try {

                    HttpServletRequest request = requestQueue.take();

                    processRequest(request);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        requestProcessorThread.start();
    }
    
    private void processRequest(HttpServletRequest request) throws IOException {
        WorkerBO workerBo = new WorkerBO();
    	
    	String id = request.getAttribute("id").toString();
    	
    	System.out.println("id :" +request.getAttribute("id"));
    	
    	String inputFilePath = "C:/Users/teahu/Videos/" + id;
    	
    	String outputFilePath = "C:/Users/teahu/Videos/" + UUID.randomUUID().toString() + request.getAttribute("ext");
    	
    	String ffmpegCommand = "C:/ffmpeg/bin/ffmpeg";
    	
    	System.out.println(request.getParameter("start"));
 
    	String startTime = convertStringToTime(request.getParameter("start").toString());
    	
    	String endTime = convertStringToTime(request.getParameter("end").toString());
    	String[] cmd = {
              ffmpegCommand,
              "-ss", startTime,
              "-i", inputFilePath,
              "-c", "copy",
              "-t", endTime,
              outputFilePath
    	};
    	
    	String[] cmdHaveMute = {
                ffmpegCommand,
                "-ss", startTime,
                "-i", inputFilePath,
                "-c", "copy",
                "-t", endTime, "-an",
                outputFilePath
      	};
    	
    	Boolean isMute = false;
    	String mute = request.getParameter("isMute");
    	if (mute != null && mute.equals("on")) {
    		
    		isMute = true;
    	}
    	Process p;
//		String cmd= "\"C:\\ffmpeg\\bin\\ffmpeg\" -nostdin -ss 00:00:01.0 -i \"C:\\Users\\teahu\\Videos\\testmp42.mp4\" -c copy -t 00:00:50.0 \"C:\\Users\\teahu\\Videos\\test4.mp4\""; 
		System.out.println(isMute);
		if (isMute == true) {
			p = Runtime.getRuntime().exec(cmdHaveMute);
		} else {
			p = Runtime.getRuntime().exec(cmd);
		}
		
		 System.out.print("converting");
       try {
			while ( ! p.waitFor(500, TimeUnit.MILLISECONDS)) {

			         System.out.print(".");
			 }
		} catch (InterruptedException e) {

			e.printStackTrace();
        	workerBo.update(id, "error");
		}
      
       System.out.print("\n");

       if (p.exitValue() != 0) {
               System.err.printf("ffmpeg failed with value %d\n", p.exitValue());

           		workerBo.update(id, "error");
               return;
       }
       
       try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String localFilePath = outputFilePath;

      
            try (InputStream fileInputStream = new URL("file:///" + localFilePath).openStream()) {
                // Initialize Cloudinary
                Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                		 "cloud_name", "dtpqh6cau",
                         "api_key", "586895126585433",
                         "api_secret", "nbBw-gAQVXkZ0-UdsT04Vq-_318"
                ));
                
                @SuppressWarnings("unchecked")
				Map<String, Object> params = ObjectUtils.asMap(
                        "overwrite", true,
                        "resource_type", "video"
                );
                
                File fileProcessed = new File(localFilePath);
                // Upload the video file to Cloudinary
                Map<?, ?> uploadResult = cloudinary.uploader().upload( fileProcessed, params);

                // Get the URL of the uploaded video
                String publicUrl = (String) uploadResult.get("url");
                System.out.println("url: " + publicUrl);

                
            	workerBo.update(id, publicUrl);
            	
            	File outFile = new File(outputFilePath);
            	if (outFile.delete()) { 
                    System.out.println("Deleted the folder: " + outFile.getName());
                  } else {
                    System.out.println("Failed to delete the folder.");
                  } 

                File inpFile = new File(inputFilePath);
	            if (inpFile.delete()) { 
	                System.out.println("Deleted the folder: " + inpFile.getName());
	              } else {
	                System.out.println("Failed to delete the folder.");
	              } 
                
	            
            } catch (Exception e) {
               e.printStackTrace();
           		workerBo.update(id, "error");
            }
    }
    
    public static String convertStringToTime(String input) {
        try {
            int totalSeconds = Integer.parseInt(input);

            if (totalSeconds < 0) {
                return "Invalid input";
            }

            int hours = totalSeconds / 3600;
            int minutes = (totalSeconds % 3600) / 60;
            int seconds = totalSeconds % 60;

            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } catch (NumberFormatException e) {
            return "Invalid input format";
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	WorkerBO workerBo = new WorkerBO();
    	HttpSession session = request.getSession();
    	User user = (User)session.getAttribute("me");
    	
    	if (user == null) {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
    	};
    	
    	Worker[] data = workerBo.getList(user.getId()); // Replace this with actual data retrieval logic

         // Set the data as an attribute in the request
         request.setAttribute("myData", data);
         System.out.print("hihi legnth" + data.length +user.getId());
         for(int i =0; i < data.length; i++) {
         	System.out.println(i);
         	if (data[i] != null) {
         		System.out.println("k null"+ i);
         	}
         }
         RequestDispatcher dispatcher = request.getRequestDispatcher("/editor.jsp");
         dispatcher.forward(request, response);
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 WorkerBO workerBo = new WorkerBO();
		 InputStream videoInputStream = null;
		 String newFileName = "";
		 try {
	            Part videoPart = request.getPart("videoFile");

	            String originalFileName = extractFileName(videoPart);

	            
	            String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
	            
	            newFileName = UUID.randomUUID().toString() + extension;
	            
	            videoInputStream = videoPart.getInputStream();

	            String uploadPath = "C:/Users/teahu/videos/";

	            videoPart.write(uploadPath + newFileName);
	           
	            request.setAttribute("id", newFileName);
	            
	            request.setAttribute("ext", extension);

	        	HttpSession session = request.getSession();
	        	
	            User user = (User)session.getAttribute("me");
	        	
	        	Worker[] data = workerBo.getList(user.getId());
	        	
	        	if (user == null) return;
	        	
	            workerBo.createWorker(newFileName, user.getId(), "processing");
	            try {

	                requestQueue.put(request);
	                
	                response.getWriter().write("Request received. It will be processed shortly.");
	                
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	            
	        } catch (Exception e) {
	            System.out.println(e);
	              
	           	workerBo.update(newFileName, "error");
	        }finally {
	        	response.setContentType("text/plain");
	        	response.getWriter().print("responseData"); //----Sending response
	        	System.out.println("done");
	            if (videoInputStream != null) {
	                try {
	                	videoInputStream.close();
	                } catch (IOException io) {
	                	 System.out.println(io);
	                	 workerBo.update(newFileName, "error");
	                }
	            }
	        }
		
	}
	 private String extractFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        String[] tokens = contentDisp.split(";");
	        for (String token : tokens) {
	            if (token.trim().startsWith("filename")) {
	                return token.substring(token.indexOf("=") + 2, token.length() - 1);
	            }
	        }
	        return "";
	    }

}
