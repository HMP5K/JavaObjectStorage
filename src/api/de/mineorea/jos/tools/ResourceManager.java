package de.mineorea.jos.tools;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import de.mineorea.jos.JOSTokener;
import de.mineorea.jos.object.JOSCompound;

public final class ResourceManager {
	
	public static final String EXT0 = ".res";
	public static final String EXT1 = ".resx";
	
	public static final ResourceManager newManager(File zipArchive){
		return new ResourceManager(zipArchive);
	}
	
	public static final MethodHandle defineClass = a();
	static final MethodHandle a(){
		try {
			Method m = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
			m.setAccessible(true);
			return MethodHandles.lookup().unreflect(m);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
		
	private ZipFile archive = null;
	
	public ResourceManager(File zipArchive){
		try {
			this.archive = new ZipFile(zipArchive);
		} catch (Exception e) {
			throw new IOError(e);
		}
	}
	
	public InputStream getResourceAsStream(String name){
		ZipEntry e = archive.getEntry(name);
		try {
			return e == null ? null : archive.getInputStream(e);
		} catch (IOException e1) {
			throw new IOError(e1);
		}
	}
	
	public Class<?> getClass(String name) throws IOException{
		return getClass(name, name.substring(0, name.lastIndexOf(".")));
	}
	
	public Class<?> getClass(String name, String clazz_name) throws IOException{
		InputStream in = this.getResourceAsStream(name);
		if(in == null)
			return null;
		clazz_name = clazz_name.replace("/", ".").replace("\\", ".");
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024];
	    int length = 0;
	    while ((length = in.read(buffer)) != -1) {
	        baos.write(buffer, 0, length);
	    }
	    byte[] data = baos.toByteArray();
		try {
			return (Class<?>) defineClass.invokeExact(ClassLoader.getSystemClassLoader(), clazz_name, data, 0 ,data.length);
		} catch (Throwable e) {
			throw new IOException(e);
		}
		
	}
	
	public Properties getProberties(String name){
		InputStream in = this.getResourceAsStream(name);
		if(in == null) 
			return null;
		Properties prob = new Properties();
		try {
			prob.load(in);
		} catch (IOException e) {
			throw new IOError(e);
		}finally{
			try {
				in.close();
			} catch (IOException e) {
			}
		}
		return prob;
	}
	
	public Icon getIcon(String name){
		Image img = getImage(name);
		if(img == null)
			return null;
		return new ImageIcon(img);
	}
	
	public BufferedImage getImage(String name){
		try {
			InputStream in = this.getResourceAsStream(name);
			if(in == null)
				return null;
			return ImageIO.read(in);
		} catch (IOException e) {
			throw new IOError(e);
		}
	}
	
	public JOSCompound getJOS(JOSTokener tok, String name){
		InputStream in = this.getResourceAsStream(name);
		if(in == null)
			return null;
		return tok.load(in);
	}
	
	public Document getXML(String name){
		InputStream in = this.getResourceAsStream(name);
		if(in == null)
			return null;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		Document doc;
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(in);
			doc.getDocumentElement().normalize();
			in.close();
			return doc;
		} catch (Exception e) {
			throw new IOError(e);
		}
	}
	
	public ZipInputStream getZip(String name){
		InputStream in = this.getResourceAsStream(name);
		if(in == null)
			return null;
		return new ZipInputStream(in);
	}
}