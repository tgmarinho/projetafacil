package br.com.projetafacil.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Service;

@Service
public class DownloadSinapiService {

	
	public String download(String urlDoArquivo) {
		
//		String urlDoArquivo = "http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-ms/SINAPI_ref_Insumos_Composicoes_MS_052017_Desonerado.zip";
		
		try {
			URL url = null;
			InputStream inputStream = null;
			
			url = new URL(urlDoArquivo);
			// Para não ter problemas com autenticação
			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
	
			inputStream = url.openStream();
	
			ZipInputStream zin = new ZipInputStream(inputStream);
			ZipEntry ze = null;
			
			while ((ze = zin.getNextEntry()) != null) {
				if(ze.getName().contains(".XLS") ){
					System.out.println("Unzipping " + ze.getName());
					FileOutputStream fout = new FileOutputStream(ze.getName());
					System.out.println("JUST ONE: " + ze.getName());
					
					for (int c = zin.read(); c != -1; c = zin.read()) {
						fout.write(c);
					}
					zin.closeEntry();
					fout.close();
					
					return ze.getName();
				}
				
			}
			zin.close();
	
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Problema para fazer o download");
		}catch(IOException x) 	{
			x.printStackTrace();
			throw new RuntimeException("Problema para fazer o download");
		}
		return null;
		
	}
		
	
}
