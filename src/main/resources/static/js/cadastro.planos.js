var ProjetaFacil = ProjetaFacil || {};

ProjetaFacil.MaskMoney = (function() {
	
	function MaskMoney() {
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
		this.coeficiente = $('js-coeficiente');
	}
	
	MaskMoney.prototype.enable = function() {
//		this.decimal.maskMoney({ decimal: ',', thousands: '.' });
//		this.plain.maskMoney({ precision: 0, thousands: '.' });
		this.decimal.maskNumber({ decimal: ',', thousands: '.' });
		this.plain.maskNumber({ integer: true, thousands: '.' });
		
		this.coeficiente.maskMoney({
			type:'float',
			defaultValueInput:'0',
			decimalMark:'.',
			thousands: '',
			allowZero : true
		});
		
	}
	
	return MaskMoney;
	
}());

ProjetaFacil.MaskPhoneNumber = (function() {
	
	function MaskPhoneNumber() {
		this.inputPhoneNumber = $('.js-phone-number');
	}
	
	MaskPhoneNumber.prototype.enable = function() {
		var maskBehavior = function (val) {
		  return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		};
		
		var options = {
		  onKeyPress: function(val, e, field, options) {
		      field.mask(maskBehavior.apply({}, arguments), options);
		    }
		};
		
		this.inputPhoneNumber.mask(maskBehavior, options);
	}
	
	return MaskPhoneNumber;
	
}());

ProjetaFacil.MaskCep = (function() {
	
	function MaskCep() {
		this.inputCep = $('.js-cep');
	}
	
	MaskCep.prototype.enable = function() {
		this.inputCep.mask('00.000-000');
	}
	
	return MaskCep;
	
}());

ProjetaFacil.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		});
	}
	
	return MaskDate;
	
}());

//ProjetaFacil.Security = (function() {
//	
//	function Security() {
//		this.token = $('input[name=_csrf]').val();
//		this.header = $('input[name=_csrf_header]').val();
//	}
//	
//	Security.prototype.enable = function() {
//		$(document).ajaxSend(function(event, jqxhr, settings) {
//			jqxhr.setRequestHeader(this.header, this.token);
//		}.bind(this));
//	}
//	
//	return Security;
//	
//}());

numeral.language('pt-br');

ProjetaFacil.formatarMoeda = function(valor) {
	return numeral(valor).format('0,0.00');
}

ProjetaFacil.recuperarValor = function(valorFormatado) {
	return numeral().unformat(valorFormatado);
}

$(function() {
	var maskMoney = new ProjetaFacil.MaskMoney();
	maskMoney.enable();
	
	var maskPhoneNumber = new ProjetaFacil.MaskPhoneNumber();
	maskPhoneNumber.enable();
	
	var maskCep = new ProjetaFacil.MaskCep();
	maskCep.enable();
	
	var maskDate = new ProjetaFacil.MaskDate();
	maskDate.enable();
	
//	var security = new ProjetaFacil.Security();
//	security.enable();
	
});
