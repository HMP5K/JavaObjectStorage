package de.mineorea.src.jos.tokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.JOSTokener;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.object.JOSObject;
import de.mineorea.src.jos.object.srcJOSCompound;

public final class JOSDefaultJOSTokener extends JOSTokener {

	private Lock lock = new ReentrantLock();
	private byte version = JOSObject.VERSION_FORMAT;
	private boolean gzip;
	private boolean verbose; 
	private boolean intend;
	private JOSDefaultObjectConverter conv = new JOSDefaultObjectConverter();
	
	@Override
	public Lock getLock() {
		return lock;
	}

	@Override
	public String getName() {
		return "Default.srcJOSTokener";
	}

	@Override
	public byte version() {
		return version;
	}

	@Override
	public JOSTokener version(byte version) {
		this.version = version;
		return this;
	}

	@Override
	public boolean gzip() {
		return gzip;
	}

	@Override
	public JOSTokener gzip(boolean gzip) {
		this.gzip = gzip;
		return this;
	}

	@Override
	public boolean verbose() {
		return this.verbose;
	}

	@Override
	public JOSTokener verbose(boolean verbose) {
		this.verbose = verbose;
		return this;
	}

	@Override
	public boolean meta() {
		throw new JOSException("Not Supported!");
	}

	@Override
	public JOSTokener meta(boolean meta) {
		throw new JOSException("Not Supported!");
	}

	@Override
	public boolean intendention() {
		return this.intend;
	}

	@Override
	public JOSTokener intendention(boolean intendention) {
		this.intend = intendention;
		return this;
	}

	@Override
	public JOSFormat format() {
		return JOSFormat.JOS;
	}

	@Override
	public void save(JOSCompound data, File file) throws JOSException {
		try {
			this.save(data, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new JOSException("IO Fail!" , e);
		}
	}

	@Override
	public void save(JOSCompound data, OutputStream out) throws JOSException {
		try {
			log("Saving <" + data.getName() + "> using: GZIP=" + this.gzip + " VERSION=" + this.version + " META=?");
			Writer writer = new OutputStreamWriter(this.gzip ? new GZIPOutputStream(out) : out);
			writer.write("#" + this.version + ":" + this.getClass().getName() + "\n");
			this._save1(data.getObjects(), new BufferedWriter(writer));
			writer.flush();
			writer.close();
			depth = 0;
		} catch (IOException e) {
			throw new JOSException("IO Fail!" ,e);
		}
	}
	
	int depth = 0;
	
	public void _save1(HashMap<String , JOSObject<? , ?>> data ,BufferedWriter writer) throws IOException {
		depth++;
		String spaces = "";
		if(intend)
		  for(int i = 1 ; i < depth ; i++){
			spaces += "   ";
		}
		for(Entry<String , JOSObject<? , ?>> object : data.entrySet()){
			JOSObject<?, ? > o = object.getValue();
			if(o instanceof JOSCompound){
				log("Writing Compound: " + object.getKey());
				writer.write(spaces + o.getName() + "('" + object.getKey() + "'): \n");
				
				_save1(((JOSCompound) o).getObjects() , writer);
				writer.write(spaces + o.getName() + "_end('" + object.getKey() +  "'): \n");
				log("Done.");
				depth--;
			}else{
				log("Writing Object: " + object.getKey());
				writer.write(spaces + o.getName() + "('" + object.getKey() + "'): " + conv.doObject(o) + "\n");
			}
		}
		writer.flush();
	}

	@Override
	public JOSCompound load(File file) throws JOSException {
		try {
			return this.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new JOSException("IO Failed!" ,e);
		}
	}

	@Override
	public JOSCompound load(InputStream in) throws JOSException {
		try {
			Reader reader = new InputStreamReader(gzip ? new GZIPInputStream(in) : in);
			JOSCompound root = _load1(new BufferedReader(reader));
			return root;
		} catch (Exception e) {
			throw new JOSException("IO Failed!" , e);
		}
	}
	
	public srcJOSCompound _load1(BufferedReader reader) throws IOException{
		String meta = reader.readLine();
		meta = meta.substring(1);
		Byte version = Byte.parseByte(meta.split(":")[0]);
		if(version != this.version){
			throw new JOSException("Version Conflict!");
		}
		srcJOSCompound root = new srcJOSCompound();
		Pattern pat = Pattern.compile("(.*)\\(\\'(.*)\\'\\):(.*)" , Pattern.CASE_INSENSITIVE);
		Matcher mat;
		String line;
		while((line = reader.readLine())  != null) {
			line = line.trim();
			if(line.startsWith("#")) continue;
			mat = pat.matcher(line);
			if(mat.matches() == false) throw new RuntimeException("Illegal Line: " + line);
			String type = mat.group(1).toLowerCase().trim();
			String name = mat.group(2).trim();
			String value =  mat.group(3); 
			if(type.equalsIgnoreCase(srcJOSCompound.COMPOUND_NAME)) {
				root.DATA.put(name, _load2(root , reader));
			}else if(type.equalsIgnoreCase(srcJOSCompound.COMPOUND_NAME + "_end")){
				
			}else {
				this.conv.comp(root);
				root.DATA.put(name , conv.doObject(type , value));
			}
		}
		reader.close();
		return root;
	}

	public srcJOSCompound _load2(srcJOSCompound master , BufferedReader reader) throws IOException{
		srcJOSCompound root = new srcJOSCompound(master);
		Pattern pat = Pattern.compile("(.*)\\(\\'(.*)\\'\\):(.*)" , Pattern.CASE_INSENSITIVE);
		Matcher mat;
		String line;
		while((line = reader.readLine())  != null) {
			line = line.trim();
			if(line.startsWith("#")) continue;
			mat = pat.matcher(line);
			if(mat.matches() == false) throw new RuntimeException("Illegal Line: " + line);
			String type = mat.group(1).trim().toLowerCase();
			String name = mat.group(2).trim();
			String value =  mat.group(3); 
			if(type.equalsIgnoreCase(srcJOSCompound.COMPOUND_NAME)) {
				root.DATA.put(name, _load2(root , reader));
			}else if(type.equalsIgnoreCase(srcJOSCompound.COMPOUND_NAME +"_end")) {
				return root;
			}else {
				conv.comp(root);
				root.DATA.put(name , conv.doObject(type , value));
			}
		}
		return null;
	}
	public void log(String message){
		if(this.verbose){
			System.out.println("JOSTokener: " + message);
		}
	}
}
