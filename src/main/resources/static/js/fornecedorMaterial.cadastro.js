var ProjetaFacil = ProjetaFacil || {};

ProjetaFacil.Cadastro = ProjetaFacil.Cadastro || {};

ProjetaFacil.Cadastro.TipoPessoa = { Fisica: 'FISICA', Juridica: 'JURIDICA' };

ProjetaFacil.Cadastro.SelecionaTipoPessoa = function (obj) {

	$('[not-required]').each(function () { $(this).removeAttr('not-required'); $(this).attr('required', 'required') });

	if (obj.value == ProjetaFacil.Cadastro.TipoPessoa.Fisica) {
		$('[data-pessoatipo="Fisica"]').show();
		$('[data-pessoatipo="Juridica"]').hide();
		$('[data-pessoatipo="Juridica"] [required]').each(function () { $(this).removeAttr('required'); $(this).attr('not-required', 'not-required'); });

		$('#txt_cnpj').val("");
		$('#txt_nome_fantasia').val("");
		$('#txt_razao_social').val("");
		$('#txt_setor').val("");

		$("#lbl_mensagem_cnpj").html("");
		$("#lbl_mensagem_nome_fantasia").html("");
		$("#lbl_mensagem_razao_social").html("");
		$("#lbl_mensagem_setor").html("");

	}
	else if (obj.value == ProjetaFacil.Cadastro.TipoPessoa.Juridica) {
		$('[data-pessoatipo="Juridica"]').show();
		$('[data-pessoatipo="Fisica"]').hide();
		$('[data-pessoatipo="Fisica"] [required]').each(function () { $(this).removeAttr('required'); $(this).attr('not-required', 'not-required'); });

		$('#txt_cpf').val("");
		$('#txt_atividade').val("");

		$("#lbl_mensagem_cpf").html("");
		$("#lbl_mensagem_nome").html("");
		$("#lbl_mensagem_atividade").html("");
	}
}

ProjetaFacil.Cadastro.ConsultaCEP = function () {
	var cep = $('#txt_cep').val();

	$.getJSON('http://correiosapi.apphb.com/cep/' + cep, function (data) {

		$('#txt_numero').val("");
		$('#txt_complemento').val("");


		if (data.Erro == true) {
		    $('#txt_cep')[0].setCustomValidity("invalid");
		    $('#txt_cep').data("CEP Inválido");
			$("#lbl_mensagem_cep").html("CEP Inválido");
			$('#txt_logradouro').val("");
			$('#txt_bairro').val("");
			$('#txt_cidade').val("");
			$('#txt_uf').val("");
			$('#txt_logradouro').attr('ReadOnly', 'ReadOnly');
			$('#txt_bairro').attr('ReadOnly', 'ReadOnly');

		}
		else {
		    $('#txt_cep')[0].setCustomValidity("");
		    $('#txt_cep').data("O campo CEP é obrigatório");
			$('#txt_logradouro').val(data.end);

			if (data.end != "")
				$('#txt_logradouro').attr('ReadOnly', 'ReadOnly');
			else
				$('#txt_logradouro').removeAttr('ReadOnly');


			$('#txt_bairro').val(data.bairro);
			if (data.bairro != "")
				$('#txt_bairro').attr('ReadOnly', 'ReadOnly');
			else
				$('#txt_bairro').removeAttr('ReadOnly');


			$('#txt_cidade').val(data.cidade);
			$('#txt_uf').val(data.uf);

			$("#lbl_mensagem_cep").html("");
		}
	});


}

ProjetaFacil.Cadastro.ExibeMensagem = function () {
	//$('#mdl_mensagem').modal();            
	$.notify({ message: 'Dados gravados com sucesso' }, { type: 'success' });
	$.notify({ message: 'Um e-mail foi disparado para o endereço de e-mail informado. Acesse seu e-mail para confirmar seu cadastro' }, { type: 'success' });
	setTimeout(ProjetaFacil.Cadastro.LimpaCadastro, 20);
}

ProjetaFacil.Cadastro.LimpaCadastro = function () {
	$('input[type=text]').each(function () { this.value = ''; })
	$('input[type=email]').each(function () { this.value = ''; })
	$('input[type=password]').each(function () { this.value = ''; })
	$('select').each(function () { this.value = ''; })
}

ProjetaFacil.Cadastro.VerificaLogin = function (tipoPessoa, login) {
	$.getJSON('/Handlers/VerificaCadastro.ashx?tipoPessoa=' + tipoPessoa + '&login=' + login, function (data) {
		if (data.ExisteCadastro) {
			if (tipoPessoa == 'PF') {
				$("#lbl_mensagem_cpf").html("CPF de usuário já cadastrado no sistema!");
			}
			else {
				$("#lbl_mensagem_cnpj").html("CNPJ de usuário já cadastrado no sistema!");
			}
		}
		else if (tipoPessoa == 'PJ') {
			//Implementar os dados do fornecedor
		}
	});
}

ProjetaFacil.Cadastro.Valida =
{
	SomenteNumero: function (e) {

		var tecla = (window.event) ? event.keyCode : e.which;

		if ((tecla > 47 && tecla < 58)) return true;
		else {
			if (tecla == 8 || tecla == 0) return true;
			else return false;
		}
	},
	CPF: function (cpf) {
		cpf = cpf.replace(/[^0-9]/g, '').toString();

		var cpfsInvalidos = ['11111111111', '22222222222', '33333333333', '44444444444', '55555555555', '66666666666', '77777777777', '88888888888', '99999999999', '00000000000'];

		if (cpf == "")
			return;

		if (cpf.length != 11)
			$('#lbl_mensagem_cpf').html('O número do CPF é inválido');

		if (cpfsInvalidos.indexOf(cpf) != -1)
			$('#lbl_mensagem_cpf').html('O número do CPF é inválido');

		var v = [];

		//Calcula o primeiro dígito de verificação.
		v[0] = 1 * cpf[0] + 2 * cpf[1] + 3 * cpf[2];
		v[0] += 4 * cpf[3] + 5 * cpf[4] + 6 * cpf[5];
		v[0] += 7 * cpf[6] + 8 * cpf[7] + 9 * cpf[8];
		v[0] = v[0] % 11;
		v[0] = v[0] % 10;

		//Calcula o segundo dígito de verificação.
		v[1] = 1 * cpf[1] + 2 * cpf[2] + 3 * cpf[3];
		v[1] += 4 * cpf[4] + 5 * cpf[5] + 6 * cpf[6];
		v[1] += 7 * cpf[7] + 8 * cpf[8] + 9 * v[0];
		v[1] = v[1] % 11;
		v[1] = v[1] % 10;

		//Retorna Verdadeiro se os dígitos de verificação são os esperados.
		if ((v[0] != cpf[9]) || (v[1] != cpf[10]))
			$('#lbl_mensagem_cpf').html('O número do CPF é inválido');

		if ($('#lbl_mensagem_cpf').html() != "") {
			$('#txt_cpf')[0].setCustomValidity('Invalid');
			$('#txt_cpf').data('error', 'O número do CPF é inválido');
			return false;
		}

		return true;


	},
	CNPJ: function (cnpj) {
		cnpj = cnpj.replace(/[^0-9]/g, '').toString();

		var cnpjsInvalidos = ['11111111111111', '22222222222222', '33333333333333', '44444444444444', '55555555555555', '66666666666666', '77777777777777', '88888888888888', '99999999999999', '00000000000000'];

		if (cnpj == '')
			return;

		if (cnpj.length != 14)
			$('#lbl_mensagem_cnpj').html('O número do CNPJ é inválido');

		if (cnpjsInvalidos.indexOf(cnpj) != -1)
			$('#lbl_mensagem_cnpj').html('O número do CNPJ é inválido');



		// Valida DVs
		tamanho = cnpj.length - 2
		numeros = cnpj.substring(0, tamanho);
		digitos = cnpj.substring(tamanho);
		soma = 0;
		pos = tamanho - 7;
		for (i = tamanho; i >= 1; i--) {
			soma += numeros.charAt(tamanho - i) * pos--;
			if (pos < 2)
				pos = 9;
		}
		resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
		if (resultado != digitos.charAt(0))
			$('#lbl_mensagem_cnpj').html('O número do CNPJ é inválido');

		tamanho = tamanho + 1;
		numeros = cnpj.substring(0, tamanho);
		soma = 0;
		pos = tamanho - 7;
		for (i = tamanho; i >= 1; i--) {
			soma += numeros.charAt(tamanho - i) * pos--;
			if (pos < 2)
				pos = 9;
		}
		resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
		if (resultado != digitos.charAt(1))
			$('#lbl_mensagem_cnpj').html('O número do CNPJ é inválido');


		if ($('#lbl_mensagem_cnpj').html() != "") {
			$('#txt_cnpj')[0].setCustomValidity('Invalid');
			$('#txt_cnpj').data('error', 'O número do CNPJ é inválido');
			return false;
		}
		else {
			$('#txt_cnpj')[0].setCustomValidity('');
			$('#txt_cnpj').data('error', 'O campo CNPJ é obrigatório');
			return true;
		}



	},	
}

ProjetaFacil.Cadastro.Init = function () {

	ProjetaFacil.Cadastro.RegistraEventos();
	ProjetaFacil.Cadastro.SelecionaTipoPessoa($('[data-grupo="pessoa"]:checked')[0]);
}

ProjetaFacil.Cadastro.RegistraEventos = function ()
{

	$('#txt_cpf').focus(function () {
		this.value = this.value.replace(/[^0-9]+/g, '');
		$('#lbl_mensagem_cpf').html('');

		$('#txt_cpf')[0].setCustomValidity('');
		$('#txt_cpf').data('error', 'O campo CPF é obrigatório.');
	});
	$('#txt_cpf').blur(function () {
		if (this.value == "")
			return;

		this.value = this.value.replace(/[^0-9]/g, '').toString();
		this.value = this.value.PadLeft('0', 11);
		this.value = this.value.substring(0, 3) + "." + this.value.substring(3, 6) + "." + this.value.substring(6, 9) + "-" + this.value.substring(9, 11);

		if (ProjetaFacil.Cadastro.Valida.CPF(this.value))
			ProjetaFacil.Cadastro.VerificaLogin('PF', this.value)
	});
	$('#txt_cnpj').focus(function () {
		this.value = this.value.replace(/[^0-9]+/g, '');
		$('#lbl_mensagem_cnpj').html('');
		$('#txt_cnpj')[0].setCustomValidity('');
		$('#txt_cnpj').data('error', 'O campo CNPJ é obrigatório.');
	});
	$('#txt_cnpj').blur(function () {
		if (this.value == "")
			return;

		this.value = this.value.replace(/[^0-9]/g, '').toString();
		this.value = this.value.PadLeft('0', 14);
		this.value = this.value.substring(0, 2) + "." + this.value.substring(2, 5) + "." + this.value.substring(5, 8) + "/" + this.value.substring(8, 12) + "-" + this.value.substring(12, 14);

		if (ProjetaFacil.Cadastro.Valida.CNPJ(this.value))
			ProjetaFacil.Cadastro.VerificaLogin('PJ', this.value)
	});

	$('#txt_cep').focus(function () {
		this.value = this.value.replace(/[^0-9]+/g, '');
		$('#lbl_mensagem_cep').html('');
	});
	$('#txt_cep').blur(function () {
		if (this.value == "")
			return;

		this.value = this.value.replace(/[^0-9]/g, '').toString();
		this.value = this.value.PadLeft('0', 8);
		this.value = this.value.substring(0, 5) + "-" + this.value.substring(5, 8);

	});

	$("input[type=text]").blur(function (e) {

	    //Controlo os CPFs e CNPJs através de validações customizadas
	    if (this.id == "txt_cpf" || this.id == "txt_cnpj")
	        return;

	    var valid = this.checkValidity();

	    $(this).parents(".form-group").find(".with-errors span").html("");

		this.value = this.value.ReplaceAll("  ", " ").trim();
		if (!valid && this.checkValidity()) {
			$(this).focus();
			$(this).blur();
		}
	});
	

	$("#txt_email_confimacao").bind('paste', function (e) {
		e.preventDefault();
	});

	$("#txt_ddd_opcional").bind("blur keyup focus", function () {	    
	    if ($("#txt_ddd_opcional").val() == "" && $("#txt_telefone_opcional").val() == "") {
	        $("#txt_ddd_opcional").removeAttr("required");
	        $("#txt_telefone_opcional").removeAttr("required");
	    }
	    else {
	        $("#txt_ddd_opcional").attr("required", "required");
	        $("#txt_telefone_opcional").attr("required", "required");
	    }
	});
	
	$("#txt_telefone_opcional").bind("blur keypress focus", function () {
	    if ($("#txt_ddd_opcional").val() == "" && $("#txt_telefone_opcional").val() == "") {
	        $("#txt_ddd_opcional").removeAttr("required");
	        $("#txt_telefone_opcional").removeAttr("required");	        
	    }
	    else {
	        $("#txt_ddd_opcional").attr("required", "required");
	        $("#txt_telefone_opcional").attr("required", "required");
	    }
	});
}


$(document).ready(function () {
	ProjetaFacil.Cadastro.Init();
});



