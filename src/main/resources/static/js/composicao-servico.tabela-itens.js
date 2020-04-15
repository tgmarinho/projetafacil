ProjetaFacil.TabelaItens = (function() {
	
	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaComposicoesContainer = $('.js-tabela-insumos-container');
		this.uuid = $('#uuid').val();
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
		
		bindCoeficiente.call(this);
		bindExcluirItem.call(this);
	}
	
	function onItemSelecionado(evento, item) {
		var resposta = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				idInsumo: item.id,
				uuid : this.uuid,
				tipoInsumo: $(this.autocomplete.tipoInsumo).val()
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	
	function onItemAtualizadoNoServidor(html) {
		this.tabelaComposicoesContainer.html(html);
		
		bindCoeficiente.call(this);
		bindExcluirItem.call(this);
		
	}
	
	function onCoeficienteItemAlterado(evento) {
		var input = $(evento.target);
		var coeficiente = input.val().replace(/\s/g, '');
		
		if (coeficiente < 0 || coeficiente == '') {
			input.val(1);
			coeficiente = 1;
		}
		
		var idInsumo = input.data('idinsumo');
		var tipoInsumo = input.data('tipoinsumo');

		var resposta = $.ajax({
			url: 'item/' + idInsumo,
			method: 'PUT',
			data: {
				coeficiente: coeficiente,
				tipoInsumo: tipoInsumo,
				uuid: this.uuid
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	
	function onExclusaoItemClick(evento) {
		
		var input = $(evento.currentTarget);
		
		var idInsumo = input.data('idinsumo');
		var tipoInsumo = input.data('tipoinsumo');
		
		var url = '/composicoes/excluirInsumo/'+ this.uuid + '/'+tipoInsumo +'/'+idInsumo;
		
		var resposta = $.ajax({
			url: url,
			method: 'DELETE'
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function bindCoeficiente() {
		var coeficienteItemInput = $('.js-tabela-composicao-coeficiente-item');
		coeficienteItemInput.on('blur', onCoeficienteItemAlterado.bind(this));
		coeficienteItemInput.maskMoney({
			type:'float',
			defaultValueInput:'0',
			decimalMark:'.',
			thousands: '',
			precision : 4,
			allowZero : true
		});
	}
	
	
	function bindExcluirItem() {
		var excluirItem = $('.js-exclusao-item-btn');
		excluirItem.on('click', onExclusaoItemClick.bind(this));
	}
	
	
	
	return TabelaItens;
	
}());


