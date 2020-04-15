ProjetaFacil = ProjetaFacil || {};

ProjetaFacil.Autocomplete = (function() {
	
	function Autocomplete() {
		this.nomeOuCodigoBimInput = $('.js-nome-codigobim-input');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(nomeOuCodigoBim) {
				this.tipoInsumo = $('#radioTipoInsumo input[name=tipoInsumoPesquisar]:checked');
				return this.nomeOuCodigoBimInput.data('url') + '?nomeOuCodigoBim=' + nomeOuCodigoBim + '&tipoInsumo=' + this.tipoInsumo.val(); 
			}.bind(this),
			getValue: 'nome',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			list: {
				onChooseEvent: onItemSelecionado.bind(this)
			},

			theme: "square"
		};
		
		this.nomeOuCodigoBimInput.easyAutocomplete(options);
	}
	
	function onItemSelecionado() {
		this.emitter.trigger('item-selecionado', this.nomeOuCodigoBimInput.getSelectedItemData());
		this.nomeOuCodigoBimInput.val('');
		this.nomeOuCodigoBimInput.focus();
	}
	
		
	return Autocomplete
	
}());

