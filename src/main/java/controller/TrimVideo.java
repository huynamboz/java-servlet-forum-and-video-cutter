package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/editor.jsp");
        dispatcher.forward(request, response);
        
//        String inputFilePath = "http://vjs.zencdn.net/v/oceans.mp4";
//        String outputFilePath = "C:/Users/teahu/Videos/test5.mp4";
//        String ffmpegCommand = "C:/ffmpeg/bin/ffmpeg";
//        String[] command = {
//                ffmpegCommand,
//                "-ss", "00:00:30.0",
//                "-i", inputFilePath,
//                "-c", "copy",
//                "-t", "00:00:10.0",
//                outputFilePath
//        };
//
//
//		String livestream = "/Users/videos/video_10/video_1.mp4";
//
//		String folderpth = "/Users/videos/video_10/photos";
//
//		String cmd= "\"C:\\ffmpeg\\bin\\ffmpeg\" -nostdin -ss 00:00:01.0 -i \"C:\\Users\\teahu\\Videos\\testmp42.mp4\" -c copy -t 00:00:50.0 \"C:\\Users\\teahu\\Videos\\test4.mp4\""; 
//		System.out.println(cmd);
//		Process p = Runtime.getRuntime().exec(cmd);
//		 System.out.print("converting");
//         try {
//			while ( ! p.waitFor(500, TimeUnit.MILLISECONDS)) {
//			         /* here you have the opportunity to kill the process if
//			          * it is taking too long, print something etc.. */
//			         System.out.print(".");
//			 }
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//         System.out.print("\n");
//
//         if (p.exitValue() != 0) {
//                 System.err.printf("ffmpeg failed with value %d\n", p.exitValue());
//                 return;
//         }
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream videoInputStream = null;
		 try {
	            Part videoPart = request.getPart("videoFile");
	            System.out.println("done1");
	            // Lấy tên file gốc từ Part
	            String originalFileName = extractFileName(videoPart);
	            System.out.println("done2");
	            // Tạo một tên file mới với đuôi mở rộng giống như tên file gốc
	            String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
	            String newFileName = UUID.randomUUID().toString() + extension;
	            System.out.println("done3");
	            // Lấy InputStream từ Part
	            videoInputStream = videoPart.getInputStream();
	            System.out.println("done4");
	            // Đường dẫn để lưu trữ video
	            String uploadPath = "C:/Users/teahu/videos/";
	            System.out.println("done5");
	            // Tạo thư mục nếu nó không tồn tại
//	            File uploadDir = new File(uploadPath);
//	            if (!uploadDir.exists()) {
//	                uploadDir.mkdir();
//	            }

	            // Tạo tệp trên đĩa với tên file mới
//	            File videoFile = new File(uploadPath + newFileName);

	            // Lưu InputStream vào tệp
	            videoPart.write(uploadPath + newFileName);
	           
	        } catch (Exception e) {
	            System.out.println(e);
	        }finally {
	        	response.setContentType("text/plain");
	        	response.getWriter().print("responseData"); //----Sending response
	        	System.out.println("done");
	            if (videoInputStream != null) {
	                try {
	                	videoInputStream.close();
	                } catch (IOException io) {
	                	 System.out.println(io);
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
