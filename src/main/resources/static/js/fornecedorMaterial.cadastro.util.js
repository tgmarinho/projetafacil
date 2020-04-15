//Instance 

var ProjetaFacil = ProjetaFacil || {};
ProjetaFacil.Utils = ProjetaFacil.Utils || {};

//Functions
ProjetaFacil.Utils.RemoveAutoFill = function () {    
    var message = false;
    if ($("input:-webkit-autofill").length > 0) {

        $("input:-webkit-autofill").val("");
        if (message == false) {
            $.notify({ message: 'Por questões de segurança bloqueamos a função de autocompletar.' }, { type: 'warning' });
            message = true;
        }
    }
}

//Prototypes
String.prototype.PadLeft = function (padString, length) {
    var str = this;
    while (str.length < length)
        str = padString + str;
    return str;
}

String.prototype.ReplaceAll = function (needle, replacement) {
    var str = this.toString();

    while (str.indexOf(needle) != -1) {
        str = str.replace(needle, replacement);
    }

    return str;
}

