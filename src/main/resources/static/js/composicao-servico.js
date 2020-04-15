ProjetaFacil.Composicao = (function() {
	 
	function Composicao(tabelaItens) {
		this.tabelaItens = tabelaItens;
		this.nomeServico = $('#nomeServico');
		this.codigoBim = $('#codigoBIM');
		this.referencia = $('#referencia');
		this.containerCodigoBim = $('.js-container-codigobim');
		this.containerReferencia = $('.js-container-referencia');
		
	}
	
	Composicao.prototype.iniciar = function() {
		this.nomeServico.on('keyup', onToUpperCaseNome.bind(this));
		this.codigoBim.on('keyup', onToUpperCaseCodigoBim.bind(this));
		this.referencia.on('change', onReferenciaAlterada.bind(this));
		
		onReferenciaAlterada.call(this);
		apenasVisualizar.call(this);
	}
	
	function onToUpperCaseNome() {
		this.nomeServico.val(this.nomeServico.val().toUpperCase());
	}
	
	function apenasVisualizar() {
//		console.log(this);
	}
	
	function onToUpperCaseCodigoBim() {
		this.codigoBim.val(this.codigoBim.val().toUpperCase());
	}
	
	function onReferenciaAlterada() {
		if(this.referencia.val() === 'PROPRIA' || this.referencia.val() === 'SINAPI' ) {
			this.containerReferencia.removeClass('col-sm-2').addClass('col-sm-4');
			this.codigoBim.val('');
			this.containerCodigoBim.hide();
		} else if (this.referencia.val() === 'TCPO'){
			this.containerReferencia.removeClass('col-sm-4').addClass('col-sm-2');
			this.containerCodigoBim.show();
		}
	}
	
	return Composicao;
	
}());

$(function() {
	
	var autocomplete = new ProjetaFacil.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new ProjetaFacil.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
	var composicao = new ProjetaFacil.Composicao(tabelaItens);
	composicao.iniciar();
	
	
	var urlAtual = window.location.href;
	var procuraPelaPalavraVisualizar = urlAtual.match("/visualizar/");
	var achou = procuraPelaPalavraVisualizar;
	if(achou){
		$('#nomeServico').prop( "disabled", true );
		$('#codigoBIM').prop( "disabled", true );
		$('#referencia').prop( "disabled", true );
		$('#classe').prop( "disabled", true );
		$('#unidade').prop( "disabled", true );
		$('#round').prop( "disabled", true );
		$('#botao-acao').hide();
		$('.js-exclusao-item-btn').hide();
	}
	
});