package br.com.projetafacil.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.projetafacil.repository.filter.ImportacaoFilter;
import br.com.projetafacil.service.DownloadSinapiService;
import br.com.projetafacil.service.ImportacaoSinapiService;

@Controller
@RequestMapping("/importacao")
public class DownloadImportacaoController {

	private static String DOWNLOAD_IMPORTACAO = "_admin/importacao/DownloadImportacao";
	
	
	@Autowired
	private DownloadSinapiService downloadSinapiService;
	
	@Autowired
	private ImportacaoSinapiService importacaoSinapiService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(ImportacaoFilter importacaoFilter){
		ModelAndView mv = new ModelAndView(DOWNLOAD_IMPORTACAO);
		return mv;
		
	}
	
	
	@PostMapping(path="/importar")
	public ModelAndView importar(ImportacaoFilter importacaoFilter) {
		ModelAndView mv = new ModelAndView(DOWNLOAD_IMPORTACAO);
	
		try{
//			AL*
//			AP*
//			AC*
//			AA - nao encontrado
//			BA*
//			CE*
//			DF*
//			ES* 
//			GO*
//			MA
//			MT
//			MS
//			MG
//			PA
//			PB
//			PR
//			PE
//			PI
//			RJ
//			RN
//			RS
//			RO
//			RR
//			SC
//			SP
//			SE
//			TO
//			List<String> arquivos = new ArrayList<String>();
//			
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-ma/SINAPI_ref_Insumos_Composicoes_MA_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-mt/SINAPI_ref_Insumos_Composicoes_MT_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-mg/SINAPI_ref_Insumos_Composicoes_MG_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-pa/SINAPI_ref_Insumos_Composicoes_PA_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-pb/SINAPI_ref_Insumos_Composicoes_PB_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-pr/SINAPI_ref_Insumos_Composicoes_PR_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-pe/SINAPI_ref_Insumos_Composicoes_PE_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-pi/SINAPI_ref_Insumos_Composicoes_PI_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-rj/SINAPI_ref_Insumos_Composicoes_RJ_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-rn/SINAPI_ref_Insumos_Composicoes_RN_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-ro/SINAPI_ref_Insumos_Composicoes_RO_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-rr/SINAPI_ref_Insumos_Composicoes_RR_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-sc/SINAPI_ref_Insumos_Composicoes_SC_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-se/SINAPI_ref_Insumos_Composicoes_SE_052017_Desonerado.zip");
//			arquivos.add("http://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-to/SINAPI_ref_Insumos_Composicoes_TO_052017_Desonerado.zip");
//			
//			arquivos.stream().forEach(url -> {
//				String nomeDoArquivo = downloadSinapiService.download(url);
//				String  logImportacao = importacaoSinapiService.importar(nomeDoArquivo).toString();
//				
//			});
			
			String nomeDoArquivo = downloadSinapiService.download(importacaoFilter.getUrlCompleta());
			List<String> logsImportacao = importacaoSinapiService.importar(nomeDoArquivo);
//			importacaoSinapiService.importar("SINAPI_Preco_Ref_Insumos_MS_052017_Desonerado.XLS"); PARA_TESTE
			
			mv.addObject("mensagem", "Importação Realizada com Sucesso");

			mv.addObject("logsImportacao", logsImportacao);
			
//			mv.addObject("mensagemErro", logImportacao.toString());
		} catch(Exception e) {
			mv.addObject("mensagemErro", "Importação falhou");
		}
		
		return mv;
		
	}
	
}
