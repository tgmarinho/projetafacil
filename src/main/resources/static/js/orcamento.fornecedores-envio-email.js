$('#fornecedores').selectize({
	plugins : [ 'remove_button' ],
	maxItems: 30,
});




















//
//
//
//
//
//
//
//var REGEX_EMAIL = '([a-z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&\'*+/=?^_`{|}~-]+)*@' +
//                  '(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)';
//
//$('#fornecedores').selectize({
//    persist: false,
//    maxItems: null,
//    valueField: 'email',
//    labelField: 'nome',
//    searchField: ['nome', 'email'],
//    options: [
//        {email: 'brian@thirdroute.com', nome: 'Brian Reavis'},
//        {email: 'nikola@tesla.com', nome: 'Nikola Tesla'},
//        {email: 'someone@gmail.com'}
//    ],
//    render: {
//        item: function(item, escape) {
//            return '<div>' +
//                (item.nome ? '<span class="name">' + escape(item.nome) + '</span>' : '') +
//                (item.email ? '<span class="email">' + escape(item.email) + '</span>' : '') +
//            '</div>';
//        },
//        option: function(item, escape) {
//            var label = item.nome || item.email;
//            var caption = item.nome ? item.email : null;
//            return '<div>' +
//                '<span class="label">' + escape(label) + '</span>' +
//                (caption ? '<span class="caption">' + escape(caption) + '</span>' : '') +
//            '</div>';
//        }
//    },
//    createFilter: function(input) {
//        var match, regex;
//
//        // email@address.com
//        regex = new RegExp('^' + REGEX_EMAIL + '$', 'i');
//        match = input.match(regex);
//        if (match) return !this.options.hasOwnProperty(match[0]);
//
//        // name <email@address.com>
//        regex = new RegExp('^([^<]*)\<' + REGEX_EMAIL + '\>$', 'i');
//        match = input.match(regex);
//        if (match) return !this.options.hasOwnProperty(match[2]);
//
//        return false;
//    },
//    create: function(input) {
//        if ((new RegExp('^' + REGEX_EMAIL + '$', 'i')).test(input)) {
//            return {email: input};
//        }
//        var match = input.match(new RegExp('^([^<]*)\<' + REGEX_EMAIL + '\>$', 'i'));
//        if (match) {
//            return {
//                email : match[2],
//                name  : $.trim(match[1])
//            };
//        }
//        alert('Invalid email address.');
//        return false;
//    }
//});