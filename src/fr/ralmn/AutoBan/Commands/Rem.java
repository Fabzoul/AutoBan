package fr.ralmn.AutoBan.Commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.util.ArrayList;

import fr.ralmn.AutoBan.AB;

public class Rem {

	
	public static void Reml(String index){
		
		int i = Integer.parseInt(index);
		
		ArrayList<String> al = new ArrayList<String>();
		try {
			File f = new File("plugins/AutoBan/user/op.dat");
			BufferedReader br = new BufferedReader(new FileReader(f));
			String size;
			new LineNumberReader(br);
			while ((size = br.readLine()) != null) {

				al.add(size);

			}

			al.remove(i);
			br.close();
			f.delete();
			f.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
			
			
			
			for (int a = 0; a < al.size(); a++){
				bw.write(al.get(a));
				bw.flush();
				bw.newLine();
			}
			
			bw.close();

			
		} catch (Exception e) {
			AB.log.severe(e + "");
		}
		
	}
	
}
