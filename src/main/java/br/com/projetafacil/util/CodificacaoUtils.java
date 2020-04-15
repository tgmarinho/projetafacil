package br.com.projetafacil.util;

public class CodificacaoUtils {

	private CodificacaoUtils() { }
	
	

	
	public static String obterNovoCodigoParaServico(String codigo) {
		
		if (null == codigo || "".equals(codigo)) {
			return "1";
		}
		
		Integer novoCodigoInt = Integer.valueOf(codigo);
		novoCodigoInt++;

		String novoCodigo = novoCodigoInt.toString();
		
		return novoCodigo;
	}

}
