ProjetaFacil.Orcamento = (function() {
	 
	function Orcamento(tabelaItens) {
		this.tabelaItens = tabelaItens;
		this.valorTotalBox = $('.js-valor-total-box');
		this.valorBDIInput = $('#valorBDI');
						
		this.valorTotalItens = this.tabelaItens.valorTotal();
		this.valorBDI = this.valorBDIInput.data('valor');
		
	}
	
	Orcamento.prototype.iniciar = function() {
		this.tabelaItens.on('tabela-itens-atualizada', onTabelaItensAtualizada.bind(this));
		this.valorBDIInput.on('keyup', onValorBDIAlterado.bind(this));	
		
		this.tabelaItens.on('tabela-itens-atualizada', onValoresAlterados.bind(this));
		this.valorBDIInput.on('keyup', onValoresAlterados.bind(this));
				
		onValoresAlterados.call(this);
	}
	
	function onTabelaItensAtualizada(evento, valorTotalItens) {
		this.valorTotalItens = valorTotalItens == null ? 0 : valorTotalItens;
	}
	
	function onValorBDIAlterado(evento) {
		this.valorBDI = ProjetaFacil.recuperarValor($(evento.target).val());
	}
	
	function onValoresAlterados() {
		console.log("Total Itens: ", numeral(this.valorTotalItens));
		console.log("BDI: ", numeral(this.valorBDI));
		console.log("percentual ", numeral(this.valorTotalItens) / 100);
		
		var valorTotal = numeral(this.valorTotalItens) + ((numeral(this.valorTotalItens) / 100) * numeral(this.valorBDI));
		this.valorTotalBox.html(ProjetaFacil.formatarMoeda(valorTotal));
	}
	
	return Orcamento;
	
}());

$(function() {
	
	var autocomplete = new ProjetaFacil.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new ProjetaFacil.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
	var orcamento = new ProjetaFacil.Orcamento(tabelaItens);
	orcamento.iniciar();
	
	
	console.log( "ready!" );
	
	
	var mostrarClasses = $('#mostrar-classes');
	var mostrarServico = $('#mostrar-servicos');
	var nenhumServicoAdd = $('.nenhumServicoAdd');
	
	var selecaoTodosTercerizado = $('#selecao-todos-tercerizados');
	var selecaoTercerizados = $('.js-selecao-tercerizado');
	
	function verificaEstados() {
		if(mostrarServico.is(':checked') && !mostrarClasses.is(':checked')) {
			$('.comServico').closest('tr').show();
			$('.classe').closest('tr').hide();
		} else if (mostrarServico.is(':checked') && mostrarClasses.is(':checked')) {
			$('.comServico').closest('tr').show();
			$('.classe').closest('tr').show();
		} else if(!mostrarServico.is(':checked') && mostrarClasses.is(':checked')) {
			$('.comServico').closest('tr').hide();
			$('.classe').closest('tr').show();
		} else if(!mostrarServico.is(':checked') && !mostrarClasses.is(':checked')) {
			$('.comServico').closest('tr').hide();
			$('.classe').closest('tr').hide();
		} else if(!mostrarServico.is(':checked') && mostrarClasses.is(':checked')) {
			$('.comServico').closest('tr').hide();
			$('.classe').closest('tr').show();
		}
		
		
		nenhumServicoAdd.hide();
	}
	
	mostrarClasses.on("click", function(){
		verificaEstados(this);
	});

	
	mostrarServico.on("click", function(){
		verificaEstados(this);
	});
	

	selecaoTercerizados.on('click', function() {
		var totalTercerizadosSelecionados = selecaoTercerizados.filter(':checked').length;
		var checked = selecaoTercerizados.length === totalTercerizadosSelecionados;
		selecaoTodosTercerizado.prop('checked', checked);
	});

	selecaoTodosTercerizado.on('click', function() {
		selecaoTercerizados.prop('checked', selecaoTodosTercerizado.prop('checked'));
	});
	
});