package br.com.projetafacil.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetafacil.model.Insumo;
import br.com.projetafacil.model.enums.EnumUnidade;
import br.com.projetafacil.repository.Insumos;

@Service
public class ImportacaoSinapiService {

	private static final int CODIGO_INSUMO = 0;
	private static final int NOME_INSUMO = 1;
	private static final int PRECO_MEDIO_INSUMO = 4;

	@Autowired
	private Insumos insumos;
	
	
	@Transactional
	public List<String> importar(String nomeDoArquivo) {
		
//		StringBuilder logsImportacao = new StringBuilder();
		List<String> logsImportacao = new ArrayList<String>();
		int naoEncontrados = 0;
		try{
			
			InputStream ExcelFileToRead = new FileInputStream(nomeDoArquivo);
			HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
			
			//SINAPI_Preco_Ref_Insumos_MS_052017_Desonerado.XLS
			
			String estado = nomeDoArquivo.substring(25, 27);
			System.out.println(estado);
			
			for (Row row : wb.getSheetAt(0)) {
				
				if(row.getRowNum() > 6) {
					
					if(row.getCell(NOME_INSUMO) != null && !row.getCell(NOME_INSUMO).toString().startsWith("!EM PROCESSO DE DESATIVACAO")){
						
//						System.out.println(row.getCell(CODIGO_INSUMO)); // codigo
//						System.out.print(row.getCell(1)); // nome
//						System.out.print(row.getCell(2)); // unidade
//						System.out.print(row.getCell(4)); //  preco medio
						Insumo insumoEncontrado = buscaInsumo(row);
						if(insumoEncontrado != null) {
							atualizaValorInsumo(insumoEncontrado, estado, row);
						} else {
							naoEncontrados++;
							logsImportacao.add("Insumo: " + row.getCell(CODIGO_INSUMO) + " = " + row.getCell(NOME_INSUMO));
						
							/** Criacao de Insumo a partir da tabela SINAPI */
							Insumo insumo = new Insumo();
							
							insumo.setCodigoSINAPI(row.getCell(CODIGO_INSUMO).toString().replace(".0", "").trim());
							insumo.setNome(row.getCell(NOME_INSUMO).toString().trim());
							insumo.setCodigoBIM("codigo_"+row.getRowNum());
							
							for(EnumUnidade un : EnumUnidade.values()) {
								if(un.getDescricao().equals(row.getCell(2).toString().trim())){
									insumo.setUnidade(un);
									break;
								}
							}
							insumos.save(insumo);
						
						}
						
						
					}
					
					
//					for (Cell cell : row) {
//						
//						if (cell.getCellTypeEnum() == CellType.STRING) {
//							System.out.println(cell.getStringCellValue());
//						} else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
//							System.out.println(cell.getNumericCellValue());
//						}
//						
//					}
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao importar o arquivo");
		}
		if(logsImportacao.size() > 1) {
			logsImportacao.add("Por favor inclua manualmente esses itens e tente importar essa planilha.");
			logsImportacao.add("Não foi possível atualizar o valor de " + naoEncontrados + " insumos.");
		}
		return logsImportacao;
		
	}


	private void atualizaValorInsumo(Insumo insumo, String estado, Row row) {
		
			String strPrecoMedio = row.getCell(PRECO_MEDIO_INSUMO).toString();
			String precoMedioToBigDecimal = strPrecoMedio.replace(".", "").replaceAll(",", ".");
			BigDecimal precoMedio = new BigDecimal(precoMedioToBigDecimal);
			
			
			switch(estado) {
			
				case "MS" : 
					insumo.setValorMS(precoMedio);
					break;
			
				case "AL" : 
					insumo.setValorAL(precoMedio);
					break;
					
				case "AP" : 
					insumo.setValorAP(precoMedio);
					break;
				case "AC" : 
					insumo.setValorAC(precoMedio);
					break;
				case "AA" : 
					insumo.setValorAA(precoMedio);
					break;
				case "BA" : 
					insumo.setValorBA(precoMedio);
					break;
				case "CE" : 
					insumo.setValorCE(precoMedio);
					break;
				case "DF" : 
					insumo.setValorDF(precoMedio);
					break;
				case "ES" : 
					insumo.setValorES(precoMedio);
					break;
				case "GO" : 
					insumo.setValorGO(precoMedio);
					break;
				case "MA" : 
					insumo.setValorMA(precoMedio);
					break;
				case "MT" : 
					insumo.setValorMT(precoMedio);
					break;
				case "MG" : 
					insumo.setValorMG(precoMedio);
					break;
				case "PA" : 
					insumo.setValorPA(precoMedio);
					break;
				case "PB" : 
					insumo.setValorPB(precoMedio);
					break;
				case "PI" : 
					insumo.setValorPI(precoMedio);
					break;
				case "RJ" : 
					insumo.setValorRJ(precoMedio);
					break;
				case "RN" : 
					insumo.setValorRN(precoMedio);
					break;
				case "RS" : 
					insumo.setValorRS(precoMedio);
					break;
				case "RO" : 
					insumo.setValorRO(precoMedio);
					break;
				case "RR" : 
					insumo.setValorRR(precoMedio);
					break;
				case "SC" : 
					insumo.setValorSC(precoMedio);
					break;
				case "SP" : 
					insumo.setValorSP(precoMedio);
					break;
				case "SE" : 
					insumo.setValorSE(precoMedio);
					break;
				case "TO" : 
					insumo.setValorTO(precoMedio);
					break;					
					
			}
			
			insumos.save(insumo);
			
		
	}


	private Insumo buscaInsumo(Row row) {
		String codigo = row.getCell(CODIGO_INSUMO).toString().replace(".0", "").trim();
		Insumo insumo = insumos.findByCodigoSINAPI(codigo);
		return insumo;
	}


	
}
