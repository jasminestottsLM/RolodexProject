const baseurl = 'http://localhost:14400/cards';

function createListElement(card) {
	$('<li></li>')
		.html(`
				<a href="#" data-card-id="${card.id}">
				${card.last_name}, ${card.first_name}
				</a>
				<form class="delete-card-form" method="post" action="/cards/${card.id}">
					<button>Delete</button>
			`)
		.appendTo($('#card-list'));
}

$(document).on('submit', '.delete-card-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, {type: 'DELETE' })
		.done(()=> {
			$(this)
				.closest('li')
				.remove();
		})
		.fail(error => console.error(error));
});


$('#create-card-form').on('submit', function(e) {
	e.preventDefault();
	
	let payload = {
		first_name: $('#first_name').val(),
		last_name: $('#last_name').val(),
		person_title: $('#person_title').val(),
		person_co: $('#person_co').val(),
		picture: $('#picture').val()
	};
	
	let ajaxOptions = {
		type: 'POST',
		data: JSON.stringify(payload), 
		contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (card) {
			createListElement(card);
		})
	 .fail(error => console.error(error));
});

$(document).on('click', 'a[data-card-id]', function (e) {
	e.preventDefault();
	const cardId = $(this).data('cardId');
	
	$.getJSON(baseurl + '/' + cardId, function (data) {
		data.company = data.company || '<i>no company specified</i>';
		$('#card-detail')
			.html(`
					<h1>${data.first_name} ${data.last_name}</h1>
					<h2>${data.person_co}</h2>
					<div>Title: ${data.person_title}</div>
			`);
	});
});

$.getJSON(baseurl, function (data) {
	if (data.length) {
		for (let card of data) {
			createListElement(card);
		}
	} else {
		$('<li></li>')
			.css('color', 'red')
			.html('You have no data.')
			.appendTo($('#card-list'));
	}
});