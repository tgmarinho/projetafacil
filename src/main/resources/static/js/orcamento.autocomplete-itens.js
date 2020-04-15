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
				return this.nomeOuCodigoBimInput.data('url') + '?nomeOuCodigoBim=' + nomeOuCodigoBim; 
			}.bind(this),
			getValue: 'nome',
			minCharNumber: 1,
			requestDelay: 200,
			ajaxSettings: {
				contentType: 'application/json'
			},
			
			 template: {
			        type: "custom",
			        method: function(value, item) {
			        	return value + " / " + item.unidade + " - " + item.referencia;
			        }
			       
			    },
			
			
			list: {
				onChooseEvent: onItemSelecionado.bind(this)
			},

			theme: "square"
		};
		console.log(options);
		this.nomeOuCodigoBimInput.easyAutocomplete(options);
	}
	
	function onItemSelecionado() {
		this.emitter.trigger('item-selecionado', this.nomeOuCodigoBimInput.getSelectedItemData());
		this.nomeOuCodigoBimInput.val('');
		this.nomeOuCodigoBimInput.focus();
	}
	
		
	return Autocomplete
	
}());


