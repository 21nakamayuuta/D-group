package jp.co.axiz.web.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class Images {
	public String imagePathSave(MultipartFile imageFile, String userName){

		if(imageFile.isEmpty()) {
			return null;
		}


		Path path = Paths.get("C:/pleiades/workspace/group_d/src/main/webapp/imgs/");
		if (!Files.exists(path)) {
		    try {
		      Files.createDirectory(path);
		    } catch (NoSuchFileException ex) {
		      System.err.println(ex);
		    } catch (IOException ex) {
		      System.err.println(ex);
		    }
		}


		//.がある場所を格納
		int dot = imageFile.getOriginalFilename().lastIndexOf(".");

		//拡張子を取得
	    String extention = "";
	    if (dot > 0) {
	    	extention = imageFile.getOriginalFilename().substring(dot).toLowerCase();
	    }

	    //fileName ここをなんの名前にするかで悩み中(現在は保存時刻と登録したユーザー名をファイル名として保存しようとしている。
	    String filename = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + userName;


	    //格納ディレクトリ,ファイル名,拡張子をパスとして取得
	    Path uploadfile = Paths
	    		.get("C:/pleiades/workspace/group_d/src/main/webapp/imgs/" + filename + extention);

	    //保存している
	    try (OutputStream os = Files.newOutputStream(uploadfile, StandardOpenOption.CREATE)) {
	    	byte[] bytes = imageFile.getBytes();
	    	os.write(bytes);
	    } catch (IOException ex) {
	    	System.err.println(ex);
	    }

	    //保存場所までのPathをreturn DAOで戻り値をDBに保存してほし
		return "/imgs/"+ filename + extention;
	}

	public void changeSize(String filepath) {
		try {
			BufferedImage original = ImageIO.read(new File("C:/pleiades/workspace/group_d/src/main/webapp/" + filepath));

		    int resizeW = 200;
		    int resizeH = 200;

		    System.out.println(resizeW);

		    // 画像サイズ変更
		    BufferedImage scaleImg = new BufferedImage(resizeW, resizeH, BufferedImage.TYPE_3BYTE_BGR);
		    scaleImg.createGraphics().drawImage(
		      original.getScaledInstance(resizeW, resizeH, Image.SCALE_AREA_AVERAGING),
		      0, 0, resizeW, resizeH, null);


		    //指定したfileに上書き
		    try (ImageOutputStream imageStream = ImageIO.createImageOutputStream(new File("C:/pleiades/workspace/group_d/src/main/webapp/" + filepath))) {
		        JPEGImageWriteParam param = new JPEGImageWriteParam(Locale.getDefault());
		        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		        param.setCompressionQuality(1f);
		        ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();
		        writer.setOutput(imageStream);
		        writer.write(null, new IIOImage(scaleImg, null, null), param);
		        imageStream.flush();
		        writer.dispose();

		    }
		}catch(Exception e) {
	    	System.out.println(e);
	    }
	}
}
