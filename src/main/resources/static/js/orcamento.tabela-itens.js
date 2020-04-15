ProjetaFacil.TabelaItens = (function() {
	
	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaComposicoesContainer = $('.js-tabela-servicos-container');
		this.orcarPorCriterio = $("input[name='criterio']");
		this.uuid = $('#uuid').val();
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
		this.orcarPorCriterio.on('click', onOrcarPorCriterio.bind(this));
		bindQuantidade.call(this);
		bindExcluirItem.call(this);
		bindValorTotal.call(this);
	}
	
	TabelaItens.prototype.valorTotal = function() {
		return this.tabelaComposicoesContainer.data('valor');
	}
	
	function onItemSelecionado(evento, item) {
		var resposta = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				idComposicaoServico: item.id,
				uuid : this.uuid
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function bindValorTotal() {
		var tabelaItem = $('.js-tabela-item');
		return tabelaItem;
	}
	
	function onItemAtualizadoNoServidor(html) {
		this.tabelaComposicoesContainer.html(html);
		bindQuantidade.call(this);
		bindExcluirItem.call(this);
		var tabelaItem = bindValorTotal.call(this);
		this.emitter.trigger('tabela-itens-atualizada', tabelaItem.data('valor-total'));
	}
	
	function onQuantidadeItemAlterado(evento) {
		var input = $(evento.target);
		var quantidade = input.val().replace(/\s/g, '');
		var idServico= input.data('id-servico');
		if (quantidade < 0 || quantidade == '') {
			input.val(1);
			quantidade = 1;
		}
		var resposta = $.ajax({
			url: 'item/' + idServico,
			method: 'PUT',
			data: {
				quantidade: quantidade,
				uuid: this.uuid
			}
		});
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onExclusaoItemClick(evento) {
		var input = $(evento.currentTarget);
		var idServico = input.data('idservico');
		var url = '/orcamentos/excluirServico/'+ this.uuid + '/'+idServico;
		var resposta = $.ajax({
			url: url,
			method: 'DELETE'
		});
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function bindQuantidade() {
		var quantidadeItemInput = $('.js-tabela-orcamento-quantidade-item');
		quantidadeItemInput.on('blur', onQuantidadeItemAlterado.bind(this));
		quantidadeItemInput.maskMoney({
			type:'float',
			defaultValueInput:'0',
			decimalMark:'.',
			thousands: '',
			allowZero : true
		});
	}
	
	
	function bindExcluirItem() {
		var excluirItem = $('.js-exclusao-item-btn');
		excluirItem.on('click', onExclusaoItemClick.bind(this));
	}
	
	
	function onOrcarPorCriterio(evento) {
		console.log('Orcando por: ', $(evento.currentTarget).val());
		var resposta = $.ajax({
			url: '/orcamentos/orcar/',
			method: 'POST',
			data: {
				criterio: $(evento.currentTarget).val(),
				uuid: this.uuid
			}
		});
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}	
	
	
	return TabelaItens;
	
}());


