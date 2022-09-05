package com.androjavatech4u.controllers;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.androjavatech4u.entities.Book;
import com.androjavatech4u.entities.Product;
import com.androjavatech4u.helpers.ResponseHandler;
import com.androjavatech4u.services.ProductService;
import com.androjavatech4u.services.ProductServiceImp;

@RestController
@RequestMapping("/api")
public class ProductController 
{

@Autowired	
ProductServiceImp productService;



//LOCAL-SERVER URL
	private String URL="http://localhost:7000/imagesHub/";
	
	
	
	
	
	
@PostMapping("/addProduct")	
public ResponseEntity<Object> addProduct(@ModelAttribute Product product)
{

	 
	//set USEREMAIL
			//String userEmail=(String)session.getAttribute("ad_Email");
			
			List<MultipartFile> images = product.getProductImages();
			
			 
			
//			//getting the Real Path of the Directory
			try {
				//String absolutePath = new ClassPathResource("/static/imagesHub/").getFile().getPath();
				//System.out.println(absolutePath);
				
				
				String absolutePath="F:\\REST_Webservices\\RestFullCRUDServices\\src\\main\\resources\\static\\imagesHub";
				
				
			for(MultipartFile mul : images)
			{
				//Writing the Image
				byte[] bytes =mul.getBytes();
				FileOutputStream fileOutputStream=new FileOutputStream(absolutePath+File.separator+mul.getOriginalFilename());
				fileOutputStream.write(bytes);
				fileOutputStream.close();
				
							
				
				//LIVE SERVER
				//imageHub.setImageUrl(URL+absolutePath+mul.getOriginalFilename());
				
				//Setting Data to image hub single Object
				//User hub=new User();
				
				product.setProductImageNames(mul.getOriginalFilename());
				
				
				//LIVE-SERVER OR LOCAL_SERVER URL first remove-comment to upper side
				product.setProductImageUrl(URL+mul.getOriginalFilename());
				
				//hub.setUserEmail(userEmail);
				
				//SAVE IMAGE TO DATABASE
				
				 
				 		 	
		String msg=this.productService.saveProduct(product);
		

		if(msg.equals("success"))
		{
		return ResponseHandler.generateResponse(HttpStatus.OK, true, msg, product);
		}

		else if(msg.equalsIgnoreCase("fail"))
		{
			return ResponseHandler.generateResponse(HttpStatus.METHOD_FAILURE, true, msg, product);	
		}
	 
	
	}
 	
	} catch (Exception e) {
		e.printStackTrace();
	 
	}
		return ResponseHandler.generateResponse(HttpStatus.METHOD_FAILURE, true, "fail", product);	
 
	
  }



	
 


@GetMapping("/viewProduct")
public  List<Product> viewProduct()
{
	 List<Product> products=productService.getAllProduct();
		return products;
	}

	
}

	
	
	

